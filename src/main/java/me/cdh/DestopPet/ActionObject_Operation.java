package me.cdh.DestopPet;

import me.cdh.Draw.Display;
import me.cdh.Draw.Picture;
import me.cdh.EmptyArgument;
import me.cdh.Main.MainAWT;

public abstract class ActionObject_Operation {
    public ActionObject_Operation(Picture p) {
        this.picture = p;
        this.TempDegree = p.CurrentDegree;
        this.TempDst = new Display.Rect((float) p.Dst.x, (float) p.Dst.y, (float) p.Dst.width, (float) p.Dst.height);
        this.TempSrc = new Display.Rect((float) p.Src.x, (float) p.Src.y, (float) p.Src.width, (float) p.Src.height);
        this.Init();
        this.ListType = ListType_Immediately;//默认都是非缓冲，除非自己后期改为缓冲
    }
    //构造器

    public static final int State_Sleep = 0, State_Applying = 1, State_Begin = 2, State_Exit = 3;//Exit用于绘制最后一帧呢，绘制结束变为Sleep
    public static final int ListType_Immediately = 666,ListType_Buffer = 999;//用于判定每次对象的申请是无视还是留在缓冲区
    //final常量

    public int ListType;
    public long Time_EndTime;
    public boolean ObjectLock;//单独判断某个object是否可以使用
    public int CurrentState;//当前状态
    public int BeforeState;//之前的状态
    public Picture picture;
    public Display.Rect TempDst;
    public Display.Rect TempSrc;
    public float Temp_Dst_x, Temp_Dst_y;
    public int TempDegree;
    public Timer_Timing timer_Timing;//预定时间计时器
    public int TargetDegree;
    public int Speed_AngleBegin;
    public int GapDegree;
    public long Time_CurrentTime;
    public boolean ImmediatelyEndCondition;

    //属性

    public float Speed_Src_x;
    public float Speed_Src_y;
    public float Speed_Src_w;
    public float Speed_Src_h;
    public float Speed_Dst_x;
    public float Speed_Dst_y;
    public float Speed_Dst_w;
    public float Speed_Dst_h;
    //速度变量

    public int AngleSpeed;
    //角速度变量

    public int Temp_Centre_x, Temp_Centre_y;
    //角度中心和位置中心

    public void Init() {
        reset_init();
    }

    protected void reset_init() {
        this.Speed_Src_x = 0;
        this.Speed_Src_y = 0;
        this.Speed_Src_w = 0;
        this.Speed_Src_h = 0;
        this.Speed_Dst_x = 0;
        this.Speed_Dst_y = 0;
        this.Speed_Dst_w = 0;
        this.Speed_Dst_h = 0;
        this.CurrentState = State_Sleep;
        this.BeforeState = this.CurrentState;
        this.Temp_Dst_x = 0;
        this.Temp_Dst_y = 0;
        this.AngleSpeed = 0;
        this.ObjectLock = false;
        this.Time_CurrentTime = 0;
    }

    public void Reset() {
        reset_init();
    }
    //初始化+重置

    public void Move() {
        if (this.Speed_Src_x != 0) {
            this.Move_Src_x();
        }
        if (this.Speed_Src_y != 0) {
            this.Move_Src_y();
        }
        if (this.Speed_Src_w != 0) {
            this.Move_Src_w();
        }
        if (this.Speed_Src_h != 0) {
            this.Move_Src_h();
        }
        this.Move_Dst_x();
        this.Move_Dst_y();
        if (this.Speed_Dst_w != 0) {
            this.Move_Dst_w();
        }
        if (this.Speed_Dst_h != 0) {
            this.Move_Dst_h();
        }

    }
    //根据速度移动

    public void AngleMove() {
        this.TempDegree += this.AngleSpeed;
    }
    //角度移动

    protected void Move_Src_x() {
        if (Speed_Src_x > 0) {
            if (this.TempSrc.xf + this.Speed_Src_x < this.picture.Src.x + this.picture.Src.width) {
                TempSrc.xf += this.Speed_Src_x;
            } else {
                TempSrc.xf = this.picture.Src.x + this.picture.Src.width;
            }
        } else {
            if (this.TempSrc.xf + this.Speed_Src_x > this.picture.Src.x) {
                TempSrc.xf += this.Speed_Src_x;
            } else {
                TempSrc.xf = this.picture.Src.x;
            }
        }
    }

    protected void Move_Src_y() {
        if (Speed_Src_y > 0) {
            if (this.TempSrc.yf + this.Speed_Src_y < this.picture.Src.y + this.picture.Src.height) {
                TempSrc.yf += this.Speed_Src_y;
            } else {
                TempSrc.yf = this.picture.Src.y + this.picture.Src.height;
            }
        } else {
            if (this.TempSrc.yf + this.Speed_Src_y > this.picture.Src.y) {
                TempSrc.yf += this.Speed_Src_y;
            } else {
                TempSrc.yf = this.picture.Src.y;
            }
        }
    }

    protected void Move_Src_w() {
        if (Speed_Src_w > 0) {
            if (this.TempSrc.xf + this.TempSrc.wf + this.Speed_Src_w < this.picture.Src.x + this.picture.Src.width) {
                TempSrc.wf += this.Speed_Src_w;
            } else {
                TempSrc.wf = this.picture.Src.x + this.picture.Src.width - (this.TempSrc.xf);
            }
        } else {
            if (this.TempSrc.wf + this.Speed_Src_w > 0) {
                TempSrc.wf += this.Speed_Src_w;
            } else {
                TempSrc.wf = 0;
            }
        }
    }

    protected void Move_Src_h() {
        if (Speed_Src_h > 0) {
            if (this.TempSrc.yf + this.TempSrc.hf + this.Speed_Src_h < this.picture.Src.y + this.picture.Src.height) {
                TempSrc.hf += this.Speed_Src_h;
            } else {
                TempSrc.hf = this.picture.Src.y + this.picture.Src.height - (this.TempSrc.yf);
            }
        } else {
            if (this.TempSrc.hf + this.Speed_Src_h > 0) {
                TempSrc.hf += this.Speed_Src_h;
            } else {
                TempSrc.hf = 0;
            }
        }
    }

    protected void Move_Dst_x() {
        this.Temp_Dst_x += this.Speed_Dst_x;
        this.TempDst.xf = this.picture.Dst.x + this.Temp_Dst_x;
    }

    protected void Move_Dst_y() {
        this.Temp_Dst_y += this.Speed_Dst_y;
        this.TempDst.yf = this.picture.Dst.y + this.Temp_Dst_y;
    }

    protected void Move_Dst_w() {
        if (this.Speed_Dst_w > 0) {
            this.TempDst.wf += this.Speed_Dst_w;
        } else {
            if (this.TempDst.wf + this.Speed_Dst_w > 0) {
                this.TempDst.wf += this.Speed_Dst_w;
            } else {
                this.TempDst.wf = 0;
            }
        }
    }

    protected void Move_Dst_h() {
        if (this.Speed_Dst_h > 0) {
            this.TempDst.hf += this.Speed_Dst_h;
        } else {
            if (this.TempDst.hf + this.Speed_Dst_h > 0) {
                this.TempDst.hf += this.Speed_Dst_h;
            } else {
                this.TempDst.hf = 0;
            }
        }
    }
    //移动分量

    public void Operation() {
        this.Check_SpecialImmediatelyEnd();
    }
    //数据处理

    public void Check_SpecialImmediatelyEnd(){
        if(this.ImmediatelyEndCondition){
            this.CurrentState = State_Exit;
        }
    }
    //特殊立即结束检查

    public void Set_EndTime(long Millisecond){
        this.Time_CurrentTime += 1;
        int FPS = MainAWT.threadControl.FPS;
        if(this.Time_CurrentTime >= FPS*Millisecond / 1000L){
            this.End();
        }
    }
    //设置以事件进行结束

    public void Refresh() {
        this.BeforeState = this.CurrentState;
    }
    //状态记录

    public void Refresh_State() {
        if (this.CurrentState == State_Applying) {
            this.CurrentState = State_Sleep;
        }
    }
    //没申请上就暂时别申请了

    //刷新

    public void Lock() {
        this.ObjectLock = true;
    }
    //上锁：（在ActionObject::Running）

    public void Unlock() {
        this.ObjectLock = false;
    }
    //解锁：（在ActionObject::Judge_OperationExit_Reset）

    public void Begin() {
        if (this.CurrentState == State_Sleep) {//当前是休眠变为申请
            this.CurrentState = State_Applying;
        }
    }
    //开始

    public void End(){
        this.CurrentState = State_Exit;
    }
    //退出

    public abstract static class Timer {
        public Timer() {

        }

        public long CurrentTime;//当前时间
        public long OperationTime;//目标时间：-1L为休眠(解除休眠：Set_OperationTime；设置休眠：Judge_OperationTimeUp)

        public EmptyArgument funPoint_Operation;
        //函数指针

        public void Init() {
            this.CurrentTime = System.currentTimeMillis();
            this.OperationTime = -1L;
        }

        public void Refresh_CurrentTime() {
            this.CurrentTime = System.currentTimeMillis();
        }
        //刷新当前的时间

        public void Set_OperationTime(long TimeGap) {
            this.OperationTime = this.CurrentTime + TimeGap;
        }
        //设置目标时间

        public boolean Judge_OperationTimeUp() {
            boolean Judge = false;
            if (OperationTime != -1L) {//休眠状态
                if (this.CurrentTime >= this.OperationTime) {//时间到了||超过了
                    this.OperationTime = -1L;
                    Judge = true;
                }
            }
            return Judge;
        }
        //检查是否到达目标时间

        public void Main() {
            this.Refresh_CurrentTime();
            //刷新当前时间
        }
        //放在主函数中用来控制随机时间
    }
    public static class Timer_Timing extends Timer{
        public Timer_Timing(long LowerBound, long UpperBound, EmptyArgument funPoint_Operation) {
            super();
            this.Init(LowerBound, UpperBound);
            this.funPoint_Operation = funPoint_Operation;
        }
        public long LowerBound, UpperBound;//随机时间：上限下限

        public void Init(long LowerBound, long UpperBound) {
            super.Init();
            this.CurrentTime = System.currentTimeMillis();
            this.OperationTime = -1L;
            this.LowerBound = LowerBound;
            this.UpperBound = UpperBound;
        }
        //初始化

        public void Set_RandomOperationTime() {
            if (this.OperationTime == -1L) {//休眠的时候才能设置
                this.Set_OperationTime(ActionTool.Random.Get_Long_SetRange(LowerBound, UpperBound));
            }
        }
        //设定随机目标时间

        @Override
        public void Main() {
            super.Main();
            //刷新当前时间

            this.Set_RandomOperationTime();
            //设置随机时间

            if (this.Judge_OperationTimeUp()) {
                this.funPoint_Operation.empty();
                //申请开始操作
            }
            //时间到达判断
        }
        //放在主函数中用来控制随机时间
    }

    //延时计时器
    public static class Timer_Counter extends Timer{
        public Timer_Counter(long SetBeginTimeGap, int TotalCount, long CountTimeGap, int Type_TimeArrive, EmptyArgument funPoint_Operation){
            super();
            this.Init(SetBeginTimeGap,TotalCount,CountTimeGap);
            this.funPoint_Operation = funPoint_Operation;
            this.Type_TimeArrive = Type_TimeArrive;
        }
        public static final int Type_TimeArrive_End = 33,Type_TimeArrive_Begin = 66;

        public int Type_TimeArrive;
        public long SetBeginTimeGap,CountTimeGap;//时间差距
        public int TotalCount,CurrentCount;//总共次数和当前次数
        public long ClickTime;//记录点击的时间

        public void Init(long SetBeginTimeGap,int TotalCount,long CountTimeGap) {
            super.Init();
            this.Reset();
            this.SetBeginTimeGap = SetBeginTimeGap;
            this.TotalCount = Math.max(TotalCount, 1);
            this.CountTimeGap = CountTimeGap;
        }
        //初始化

        public void CountAdd(){
            if(this.CurrentTime - this.ClickTime >= this.CountTimeGap){
                if(this.ClickTime == -1L){
                    this.Trigger_Begin();
                }
                //初始化
                this.CurrentCount++;
                this.ClickTime = this.CurrentTime;
            }
        }
        //Count触发：只有Sleep触发才会++

        public void Trigger_Begin(){
            Set_OperationTime(this.SetBeginTimeGap);
        }
        //触发条件：此函数外部调用，相当于Begin

        private void Reset(){
            this.OperationTime = -1L;
            this.CurrentCount = 0;
            this.ClickTime = -1L;
        }
        //重置

        @Override
        public boolean Judge_OperationTimeUp() {
            boolean Judge = false;
            if (OperationTime != -1L) {//休眠状态
                if (this.CurrentTime >= this.OperationTime) {//时间到了||超过了
                    this.Reset();
                    if(this.Type_TimeArrive == Type_TimeArrive_Begin){
                        Judge = true;
                    }
                }
            }
            return Judge;
        }
        //到达判断+重置

        @Override
        public void Main() {
            super.Main();
            //刷新当前时间

            if(this.CurrentCount >= this.TotalCount &&
                    this.CurrentTime <= this.OperationTime){
                this.Reset();
                this.funPoint_Operation.empty();
                return;
            }
            //数量到达且时间未到

            if (this.Judge_OperationTimeUp()) {
                if(this.CurrentCount >= this.TotalCount){
                    this.funPoint_Operation.empty();
                    //申请开始操作
                }
            }
            //时间到达判断
        }
        //放在主函数中用来控制随机时间
    }
    //规定时间内计次器：延时，满足条件触发
    public void Set_Timing(long LowerBound, long UpperBound, EmptyArgument funPoint_Operation) {
        this.timer_Timing = new Timer_Timing(LowerBound, UpperBound,funPoint_Operation);
    }
    //--------------------------------时间管理系统--------------------------------------
}
