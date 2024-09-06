package me.cdh.Control;

import me.cdh.DestopPet.*;
import me.cdh.DestopPet.Robot;
import me.cdh.Draw.Picture;
import me.cdh.Main.MainAWT;

import java.awt.*;
import java.util.ArrayList;

public class ThreadControl implements Runnable{

    public ThreadControl(FunPoint_Run funPoint_run){
        this.CurrentOperation = CurrentOperation_Loop;
        this.funPoint_run = funPoint_run;
        CreateThread();
    }

    public void CreateThread(){
        NewThread = new Thread(this);
        NewThread.start();//启动线程
    }
    //创建一个子线程并运行
;
    //--------------------------------构造函数--------------------------------------

    public interface FunPoint_Run{
         void Object_Fun();
    }
    //目的：向没法接收参数的run传参
;
    //--------------------------------函数指针--------------------------------------

    public FunPoint_Run funPoint_run;
    //用来暂存需要操作的函数

    public final int FPS = 15;

    public int CurrentOperation;
    //当前的情况

    public static final int CurrentOperation_Loop = 1;//当前情况为循环
    public static final int CurrentOperation_Exit = 2;//当前情况为退出

    public Thread NewThread;

    public static long ThreadStartTime;

    //--------------------------------属性--------------------------------------

    @Override
    public void run() {
        Run_Main();
    }
    //重写运行函数

    public void Run_Main(){
        long BeforeTime,AfterTime,SleepTime,DTime;
        long Period = 1000L/FPS;

        ThreadStartTime = System.currentTimeMillis();//记录开始时间

        while (CurrentOperation == CurrentOperation_Loop){
            //设置帧率为30帧

            BeforeTime = System.currentTimeMillis();//获取操作前的时间
            //帧率控制

            this.funPoint_run.Object_Fun();
            //线程操作的方法

            AfterTime = System.currentTimeMillis();//获取操作后的时间
            DTime = AfterTime - BeforeTime;
/*            if(DTime > 0){
                System.out.println(1000L/DTime);
            }*/
            //前帧率
/*            if(DTime > Period){
                System.out.println(1000L/DTime);
            }else{
                System.out.println(1000L/Period);
            }*/
            //后帧率
            if(DTime < Period){
                SleepTime = Period - DTime;
                try{
                    Thread.sleep(SleepTime);
                }catch (InterruptedException exception){
                    BeforeTime = System.nanoTime();
                }

            }
            //帧率大于FPS时候sleep，小于FPS的时候无操作
        }
        if(CurrentOperation == CurrentOperation_Exit){
            System.exit(0);
        }
    }
    //实现向run函数传参
;
    //--------------------------------Run函数重写--------------------------------------
;

    //--------------------------------功能方法--------------------------------------

    public static void MainLoop_ForMainFrame(){
        Robot.Global_FishRoeAction_Tool.timerMain();
        //定时事件
        MainAWT.userData.moodSystem.mainMood();
        //时间事件+心情事件
        PaintSleepController.CheckAwake();
        //唤醒测试
        if(!PaintSleepController.judgeSleep){
            MainAWT.PaintTool_MainFrame.repaint();
            //重绘函数
        }
        //绘制
    }

    public static class Controller_PaintSleep{


        public boolean judgeSleep;
        public boolean eventAwake;

        public void CheckAwake(){
            if(MainAWT.FishRoe.Check_IsInScreenCentre()){
                judgeSleep = false;//唤醒
                return;
            }
            if(CheckAwake_MoveFollow()){
                judgeSleep = false;//唤醒
                return;
            }
            if(CheckAwake_AngleFollow()){
                judgeSleep = false;//唤醒
                return;
            }
            if(CheckAwake_Action()){//有行为动作的时候
                judgeSleep = false;//唤醒
                return;
            }
            if(eventAwake){
                judgeSleep = false;//唤醒
                eventAwake = false;
                return;
            }
            judgeSleep = true;//沉睡
        }
        //唤醒测试

        private boolean CheckAwake_MoveFollow(){
            int[] Speed;

            Speed = CheckMoveSpeed(MainAWT.FishRoe.Ears[0],
                    new Point(MainAWT.FishRoe.Ears[0].Src.width/2,MainAWT.FishRoe.Ears[0].Src.height),
                    new Point((MainAWT.FishRoe.Ears_Left_Relative_X), (MainAWT.FishRoe.Ears_Left_Relative_Y)),MainAWT.FishRoe.Ears_Zoom);
            if(Speed[0] != 0 || Speed[1] != 0){
                return true;
            }
            Speed = CheckMoveSpeed(MainAWT.FishRoe.Ears[1],
                    new Point(MainAWT.FishRoe.Ears[1].Src.width/2,MainAWT.FishRoe.Ears[1].Src.height),
                    new Point((MainAWT.FishRoe.Ears_Right_Relative_X), (MainAWT.FishRoe.Ears_Right_Relative_Y)),MainAWT.FishRoe.Ears_Zoom);
            if(Speed[0] != 0 || Speed[1] != 0){
                return true;
            }
            Speed = CheckMoveSpeed(MainAWT.FishRoe.Foots[0],
                    new Point(MainAWT.FishRoe.Foots[0].Src.width/2,0),
                    new Point((MainAWT.FishRoe.Foots_Left_Relative_X),(MainAWT.FishRoe.Foots_Left_Relative_Y)),MainAWT.FishRoe.Foots_Zoom);
            if(Speed[0] != 0 || Speed[1] != 0){
                return true;
            }
            Speed = CheckMoveSpeed(MainAWT.FishRoe.Foots[1],
                    new Point(MainAWT.FishRoe.Foots[1].Src.width/2,0),
                    new Point((MainAWT.FishRoe.Foots_Right_Relative_X),(MainAWT.FishRoe.Foots_Right_Relative_Y)),MainAWT.FishRoe.Foots_Zoom);
            if(Speed[0] != 0 || Speed[1] != 0){
                return true;
            }
            return false;
        }
        private int[] CheckMoveSpeed(Picture picture, Point Picture_Centre, Point Body_Centre, double PictureSecond_Zoom){
            double Body_Zoom = MainAWT.FishRoe.BasicSize/MainAWT.FishRoe.BasicBody.Image_B.getHeight();
            double Picture_Zoom = PictureSecond_Zoom*MainAWT.FishRoe.BasicSize/MainAWT.FishRoe.BasicBody.Image_B.getHeight();
            int gap_x = (int)((MainAWT.FishRoe.BasicBody.Dst.x + Body_Centre.x * Body_Zoom) - (picture.Dst.x + Picture_Centre.x * Picture_Zoom));
            int gap_y = (int)((MainAWT.FishRoe.BasicBody.Dst.y + Body_Centre.y * Body_Zoom) - (picture.Dst.y + Picture_Centre.y * Picture_Zoom));
            int[] Speed = new int[2];
            Speed[0] = MainAWT.FishRoe.GetFollowSpeed(gap_x,picture);
            Speed[1] = MainAWT.FishRoe.GetFollowSpeed(gap_y,picture);
            return Speed;
        }
        //跟随唤醒

        private boolean CheckAwake_AngleFollow(){
            if(CheckAngleGap(MainAWT.FishRoe.Ears[0])){
                return true;
            }
            if(CheckAngleGap(MainAWT.FishRoe.Ears[1])){
                return true;
            }
            if(CheckAngleGap(MainAWT.FishRoe.Foots[0])){
                return true;
            }
            return CheckAngleGap(MainAWT.FishRoe.Foots[1]);
        }
        //角度唤醒
        private boolean CheckAngleGap(Picture p){
            return p.AngleFollowSpeed != 0;
        }

        private boolean CheckAwake_Action(){
            if(CheckActionBegin(Robot.Global_FishRoeAction_Tool.List_Action_FaceEmoji)){
                return true;
            }
            if(CheckActionBegin(Robot.Global_FishRoeAction_Tool.List_Action_EarFootsShake)){
                return true;
            }
            if(CheckActionBegin(Robot.Global_FishRoeAction_Tool.List_Action_Move)){
                return true;
            }
            if(CheckActionBegin(Robot.Global_FishRoeAction_Tool.List_Special_FoodAppeared)){
                return true;
            }
            return CheckActionBegin(Robot.Global_FishRoeAction_Tool.List_Special_FoodEaten);
        }
        private boolean CheckActionBegin(ArrayList<ActionObject> list){
            if(list != null && !list.isEmpty()){
                for(ActionObject a : list){
                    if(a.Array_SinglePictureOperation != null){
                        for(ActionObject_Operation o : a.Array_SinglePictureOperation){
                            if(o.CurrentState != ActionObject_Operation.State_Sleep){
                                return true;//唤醒
                            }
                        }
                    }
                }
            }
            return false;
        }
        //行为唤醒（包括计时器）

        //事件唤醒
    }

    public static Controller_PaintSleep PaintSleepController;

    public static void Global_ThreadInit(){
        PaintSleepController = new Controller_PaintSleep();
    }

    //--------------------------------专属：MainFrame的主循环--------------------------------------


}
