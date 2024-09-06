package me.cdh.DestopPet;

import me.cdh.Draw.Display;
import me.cdh.Draw.Picture;
import me.cdh.DrawControl.NewFrame;
import me.cdh.Main.MainAWT;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ActionObject {

    public ActionObject(int ToolType, ActionObject_Operation[] operation, int ActionType) {
        this.operation = operation;
        this.ActionType = ActionType;
        this.Init(ToolType, operation);
    }

    //--------------------------------构造方法--------------------------------------

    //动作对象执行的指定方法

    public interface FunPoint_Empty_Argument {
        void object();
    }
    //无参函数指针

    public ActionObject_Operation[] Array_SinglePictureOperation;//单个图片操作+图像

    //--------------------------------函数指针--------------------------------------

    public static final int State_Sleep = 0, State_Running = 1, State_Applying = 2;
    public static final int ActionType_FaceEmoji = 0, ActionType_EarFootsShake = 1, ActionType_Move = 2,
            ActionType_FoodAppeared = 3, ActionType_Eaten = 4;

    //--------------------------------final常量--------------------------------------

    public int ActionType;
    //Object的Action类型
    public int CurrentState;
    //判断当前执行Action的状态
    public int BeforeState;
    //之前状态，用来改变数据

    FrameRateTool FPS_Tool;
    //图片绘制工具

    public ActionObject This;
    //存储This,给FPS_Tool使用

    public ActionObject_Operation[] operation;//操作对象：给单个绘制图片使用

    //--------------------------------属性--------------------------------------

    public void Init(int ToolType, ActionObject_Operation[] operation) {
        this.Array_SinglePictureOperation = operation;
        this.CurrentState = State_Sleep;
        this.BeforeState = CurrentState;
        this.This = this;
        this.FPS_Tool = new FrameRateTool(ToolType);
    }

    public void Refresh() {
        if (this.CurrentState != State_Running && this.Array_SinglePictureOperation[0].ListType == ActionObject_Operation.ListType_Immediately) {
            this.CurrentState = State_Sleep;
            for (ActionObject_Operation a : Array_SinglePictureOperation) {
                a.Refresh_State();
                //Operation没申请上就别申请了
            }
        } else if (this.CurrentState != State_Running && this.Array_SinglePictureOperation[0].ListType == ActionObject_Operation.ListType_Buffer) {
            this.CurrentState = State_Sleep;
            //继续申请
        }
        //没申请上就等会儿申请
        this.BeforeState = this.CurrentState;
        //记录当前的状态
    }
    //刷新函数：防止上锁后接收数据，在解锁后又进行下一个动作了；

    public void Reset() {
        this.CurrentState = ActionObject.State_Sleep;//行为沉睡
        this.BeforeState = ActionObject.State_Sleep;//行为沉睡
    }//结束，重置数据

    //--------------------------------初始化+刷新+重置--------------------------------------

    public void Timer_Main() {
        for (ActionObject_Operation o : Array_SinglePictureOperation) {
            if (o.timer_Timing != null) {//有些图片不需要申请
                o.timer_Timing.Main();
            }
        }
    }

    //--------------------------------时间管理--------------------------------------
    public Dizzy dizzy;

    public static class Dizzy {
        public Dizzy() {
            Reset();
        }

        public int Current_x, Current_y, Before_x, Before_y;
        public static final int Left = 0, Right = 1, Up = 2, Down = 3, Middle = 4;
        public boolean MouseReleased, MousePressed, Dizzied;
        public int Direction_x, Direction_y, BeforeDirection_x, BeforeDirection_y;
        private int DraggedCount_x, DraggedCount_y;

        public void Event_Dizzy_PressedMouse(MouseEvent e) {
            this.Reset();//刷新数据
            this.MousePressed = true;
        }//按下鼠标开始记录眩晕

        public void Event_Dizzy_ReleasedMouse(MouseEvent e) {
            this.MouseReleased = true;
            this.MousePressed = false;
        }//松开鼠标开始检查眩晕

        public void DirectionChange() {
            Current_x = MainAWT.FishRoe.BasicBody.Dst.x;
            Current_y = MainAWT.FishRoe.BasicBody.Dst.y;
            if (Current_x - Before_x > 0) {
                Direction_x = Left;
            } else if (Current_x - Before_x < 0) {
                Direction_x = Right;
            } else {
                Direction_x = Middle;
            }
            if (Current_y - Before_y > 0) {
                Direction_y = Down;
            } else if (Current_y - Before_y < 0) {
                Direction_y = Up;
            } else {
                Direction_y = Middle;
            }
        }//方向切换

        public void CountDirection() {
            if (Direction_x != Middle && Direction_x != BeforeDirection_x) {
                DraggedCount_x++;
            }
            if (Direction_y != Middle && Direction_y != BeforeDirection_y) {
                DraggedCount_y++;
            }
        }//记录方向

        public void RecordDizzy() {
            if (this.MousePressed) {//按下之后开始记录
                DirectionChange();
                CountDirection();
                Refresh();
            }
        }//记录眩晕

        public void CheckDizzy() {
            if (this.MouseReleased) {
                if (DraggedCount_x + DraggedCount_y >= 10) {
                    Dizzied = true;
                }
            }
        }//检查眩晕

        public void MainCheck() {
            this.RecordDizzy();
            this.CheckDizzy();
            if (this.Dizzied && Robot.Global_Squint.CurrentState == State_Sleep) {
                for (ActionObject_Operation o : Robot.Global_Dizzy_Operation) {
                    o.Begin();
                }
                this.Dizzied = false;
                this.MousePressed = false;
                this.MouseReleased = false;
            }
        }//主函数

        private void Refresh() {
            Before_y = Current_y;
            Before_x = Current_x;
            BeforeDirection_x = Direction_x;
            BeforeDirection_y = Direction_y;
        }//刷新方向

        private void Reset() {
            Current_x = 0;
            Current_y = 0;
            Before_x = 0;
            Before_y = 0;
            this.Direction_x = Middle;
            this.Direction_y = Middle;
            MouseReleased = false;
            MousePressed = false;
            DraggedCount_x = 0;
            DraggedCount_y = 0;
            BeforeDirection_x = 0;
            BeforeDirection_y = 0;
        }//重置
    }//眩晕
    //--------------------------------特殊动作--------------------------------------

    public void CheckApplication() {
        if (Array_SinglePictureOperation != null && Array_SinglePictureOperation.length > 0) {
            for (ActionObject_Operation o : Array_SinglePictureOperation) {
                if (o.CurrentState == ActionObject_Operation.State_Applying && !o.ObjectLock) {//申请且未上锁
                    if (this.CurrentState != State_Running) {
                        this.CurrentState = State_Applying;
                    }
                    //自己申请
                    else {
                        o.CurrentState = ActionObject_Operation.State_Begin;
                        o.Lock();
                        //上锁
                    }
                    //自己在运行
                }
            }
        }
    }
    //检查申请(放在最初)
    ;
    //有问题：此处的check只有single图片

    public void Running() {
        if (this.CurrentState == State_Running) {
            for (ActionObject_Operation o : Array_SinglePictureOperation) {
                if (!o.ObjectLock && o.CurrentState == ActionObject_Operation.State_Applying) {
                    o.CurrentState = ActionObject_Operation.State_Begin;
                    //申请准了
                    o.Lock();
                    //上锁
                }
            }
        }
    }//申请准许

    //--------------------------------检查队列，运行维护，刷新--------------------------------------

    public void Lock_all() {
        for (ActionObject_Operation a : Array_SinglePictureOperation) {
            a.Lock();
        }
    }

    //全部上锁
    public void Unlock_all() {
        for (ActionObject_Operation a : Array_SinglePictureOperation) {
            a.Unlock();
        }
    }//全部解锁

    //--------------------------------Lock--------------------------------------

    public class FrameRateTool {

        public FrameRateTool(int ToolType, int PictureNum, int SwitchFPS,
                             FunPoint_Empty_Argument lock,
                             FunPoint_Empty_Argument unlock) {
            this.Tool_Type = ToolType;
            this.Init(PictureNum, SwitchFPS, lock, unlock);
        }

        ;

        //绘制多张图片的工具构造器
        public FrameRateTool(int ToolType) {
            this.Tool_Type = ToolType;
            this.Init();
        }
        //绘制单个图片动作的构造器
        ;
        //--------------------------------构造方法--------------------------------------

        public FunPoint_Empty_Argument FunPoint_Lock;//：上锁在：Display
        public FunPoint_Empty_Argument FunPoint_Unlock;//：解锁在：Display
        //上锁和解锁函数：一个行为在初始加载好之后就不会再变了(特供多图切换)
        ;
        //--------------------------------函数指针--------------------------------------

        public final static int State_Sleep = 0, State_Running = 1, State_Exiting = 2;
        //状态：沉睡，正在运行，切换图片，正在退出
        public final static int Type_MultipleSwitch = 0, Type_SingleDataProgress = 1;

        //--------------------------------final常量--------------------------------------

        public int Tool_Type;
        //类型

        public int CurrentFPS;
        //当前的帧
        public int TotalFPS;
        //总共的帧数：通过Num和Switch计算出来
        public int SwitchFPS;
        //多少帧切换一次图片
        public int PictureNum;
        //图片的数量
        public int CurrentPicture;
        //当前图像

        public int CurrentState;
        //状态

        public ActionObject ThisAction;
        //记录动作对象

        //--------------------------------属性--------------------------------------

        public ArrayList<Picture>[] Array_PictureList;
        //Array中的元素：每一帧中的图像

        public void Set_PictureArray(int PictureNum) {
            for (int i = 0; i < PictureNum; i++) {
                Array_PictureList[i] = new ArrayList<Picture>();
            }
        }
        //设置Array的大小

        //--------------------------------List--------------------------------------

        public void Init(int PictureNum, int SwitchFPS,
                         FunPoint_Empty_Argument Lock,
                         FunPoint_Empty_Argument UnLock) {
            this.CurrentFPS = 0;
            this.PictureNum = PictureNum;
            this.TotalFPS = PictureNum * SwitchFPS;
            this.SwitchFPS = SwitchFPS;
            this.CurrentState = State_Sleep;
            this.ThisAction = This;
            this.FunPoint_Lock = Lock;
            this.FunPoint_Unlock = UnLock;
            this.Array_PictureList = new ArrayList[PictureNum];
            this.Set_PictureArray(PictureNum);
        }

        //初始化：多个图片
        public void Init() {
            this.CurrentFPS = 0;//目前不知道什么时候结束
            this.ThisAction = This;
            this.CurrentState = State_Sleep;
        }
        //初始化：单个图片数据变化

        private void Init_PictureGet() {

        }
        //图片初始化
        ;
        //--------------------------------初始化--------------------------------------

        public void DataProgress_PictureSwitch() {
            if ((this.CurrentFPS + 1) % this.SwitchFPS == 0) {
                //余数为整数的时候切换;排除CurrentFPS为0的情况，否则上来就切换
                this.CurrentPicture++;
                //切换图片:换为下一个
            }
            this.CurrentFPS++;
            //最后自加
            if (this.CurrentFPS >= this.TotalFPS) {//达到目标了
                this.CurrentFPS = 0;
                this.CurrentPicture = 0;
                this.CurrentState = State_Exiting;
                //正在退出，在Display真正画完之后真正退出
            }
            //自加后结束判断
        }
        //自加+切换信号(注意，数据处理放在Robot::Display中，因为线程的帧率跟Display的帧率不一样)

        public void DataProgress_SinglePicture() {
            for (ActionObject_Operation a : this.ThisAction.Array_SinglePictureOperation) {
                if (a.ObjectLock) {//运行之后上锁
                    a.Operation();
                }
            }
            //数据处理
        }
        //图片动画处理

        public boolean Judge_OperationExit_Reset() {
            boolean exit = true;
            for (ActionObject_Operation o : This.Array_SinglePictureOperation) {
                if (o.CurrentState == ActionObject_Operation.State_Exit) {
                    o.Reset();
                    //重置
                    o.Unlock();
                    //单个解锁
                }
                //单个检查就重置了
                if (o.CurrentState != ActionObject_Operation.State_Sleep) {
                    exit = false;
                }
            }
            return exit;
        }
        //判断是否全是退出+刷新对象
        ;
        //--------------------------------数据处理--------------------------------------

        public void Display(Graphics2D g, NewFrame frame) {
            if (this.CurrentState == State_Running) {
                if (Tool_Type == Type_MultipleSwitch) {
                    Lock_ToolDisplayAndLockMainDisplay(FunPoint_Lock);
                    //上锁，确保对象不会被重复绘制

                    Display_PaintCurrentFPS_Picture(g, this.CurrentPicture, frame);
                    //绘制当前帧

                    if (this.CurrentState == State_Exiting) {
                        this.CurrentState = State_Sleep;//工具沉睡
                        this.ThisAction.CurrentState = ActionObject.State_Sleep;//行为沉睡
                        Unlock_ByType();//给行为管理对象解锁：只有表情是图片切换型
                        Unlock_UnlockMainDisplay(FunPoint_Unlock);
                        //解锁，被上锁的图像能够绘制了
                    }
                    //最后一帧：退出
                } else if (Tool_Type == Type_SingleDataProgress) {
                    //上锁：单图上锁在：Running

                    if (this.Judge_OperationExit_Reset()) {
                        this.CurrentState = State_Exiting;//FPS_Tool退出
                    }
                    //操作结束，退出绘制


                    if (this.CurrentState == State_Exiting) {
                        ActionTool.Check_FoodAppeared_Switch(this.ThisAction);//食物特殊检查
                        this.CurrentState = State_Sleep;//工具沉睡
                        Reset();//重置Object的数据
                        Unlock_ByType();//给行为管理对象解锁
                        return;
                    }
                    //最后一帧：退出


                    Display_SinglePictureDataProgress(g, frame);
                    //数据处理+绘制
                }
            }
        }
        //工具绘制函数：多个图像/单个图像，数据变换

        public void Display_PaintCurrentFPS_Picture(Graphics2D g, int currentPicture, NewFrame frame) {
            for (int i = 0; i < Array_PictureList[currentPicture].size(); i++) {
                Picture p = Array_PictureList[currentPicture].get(i);
                p.Draw_SinglePictureRotate(g, frame, Image.SCALE_SMOOTH, p.CurrentDegree);
                p.eventLock.setOnce_Display(true);
                //已经绘制过了，不要再绘制了
            }
        }
        //多图切换：绘制当前帧

        public void Display_SinglePictureDataProgress(Graphics2D g, NewFrame frame) {
            for (ActionObject_Operation o : This.Array_SinglePictureOperation) {
                if (o.ObjectLock) {//上锁再绘制
                    Picture p = o.picture;
                    Display.Paint.Draw_ChooseSrcDegree_DrawDst(p, o.TempDegree,
                            o.TempSrc, o.TempDst,
                            Image.SCALE_SMOOTH, g, frame);
                    p.eventLock.setOnce_Display(true);
                    //已经绘制过了，不要再绘制了
                }
            }
            //图片绘制
        }
        //单个图片数据处理：单个图片数据处理绘制
        ;
        //--------------------------------图像绘制--------------------------------------

        public void Lock_ToolDisplayAndLockMainDisplay(FunPoint_Empty_Argument lock) {
            lock.object();
        }
        //多图切换：锁上主函数中的绘制：上锁在：Display

        public void Unlock_UnlockMainDisplay(FunPoint_Empty_Argument unlock) {
            unlock.object();
        }
        //多图切换：对主绘制事件进行解锁：解锁在：Display

        public void Unlock_ByType() {
            if (This.ActionType == ActionType_FaceEmoji) {
                ActionTool.Global_Lock_FaceEmoji = false;
            } else if (This.ActionType == ActionType_Move) {
                ActionTool.Global_Lock_Move = false;
            } else if (This.ActionType == ActionType_EarFootsShake) {
                ActionTool.Global_Lock_EarFootsShake = false;
            }
        }
        //根据Action类型不同进行不同的解锁
        ;
        //--------------------------------绘制+事件Lock--------------------------------------
    }

    //--------------------------------------帧率绘制--------------------------------------

}
