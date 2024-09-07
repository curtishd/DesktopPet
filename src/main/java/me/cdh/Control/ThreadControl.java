package me.cdh.Control;

import me.cdh.DestopPet.*;
import me.cdh.DestopPet.Robot;
import me.cdh.Draw.Picture;
import me.cdh.Main.Main;
import me.cdh.Runner;

import java.awt.*;
import java.util.ArrayList;

public class ThreadControl implements Runnable {

    public ThreadControl(Runner funPoint_run) {
        this.CurrentOperation = CurrentOperation_Loop;
        this.runner = funPoint_run;
        CreateThread();
    }

    public void CreateThread() {
        NewThread = new Thread(this);
        NewThread.start();//启动线程
    }
    //创建一个子线程并运行

    //--------------------------------构造函数--------------------------------------
    //目的：向没法接收参数的run传参

    //--------------------------------函数指针--------------------------------------

    public Runner runner;
    //用来暂存需要操作的函数

    public final int FPS = 30;

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

    public void Run_Main() {
        long BeforeTime, AfterTime, SleepTime, DTime;
        long Period = 1000L / FPS;

        ThreadStartTime = System.currentTimeMillis();//记录开始时间

        while (CurrentOperation == CurrentOperation_Loop) {
            //设置帧率为30帧

            BeforeTime = System.currentTimeMillis();//获取操作前的时间
            //帧率控制

            runner.run();
            //线程操作的方法

            AfterTime = System.currentTimeMillis();//获取操作后的时间
            DTime = AfterTime - BeforeTime;
            if (DTime < Period) {
                SleepTime = Period - DTime;
                try {
                    Thread.sleep(SleepTime);
                } catch (InterruptedException ignore) {
                }
            }
            //帧率大于FPS时候sleep，小于FPS的时候无操作
        }
        if (CurrentOperation == CurrentOperation_Exit) {
            System.exit(0);
        }
    }

    //--------------------------------Run函数重写--------------------------------------


    //--------------------------------功能方法--------------------------------------

    public static void MainLoop_ForMainFrame() {
        Robot.Global_FishRoeAction_Tool.timerMain();
        //定时事件
        Main.userData.moodSystem.mainMood();
        //时间事件+心情事件
        PaintSleepController.CheckAwake();
        //唤醒测试
        if (!PaintSleepController.judgeSleep) {
            Main.PaintTool_MainFrame.repaint();
            //重绘函数
        }
        //绘制
    }

    public static class Controller_PaintSleep {


        public boolean judgeSleep;
        public boolean eventAwake;

        public void CheckAwake() {
            if (Main.robot.Check_IsInScreenCentre()|| checkAwakeMoveFollow()||CheckAwake_AngleFollow()||CheckAwake_Action()) {
                judgeSleep = false;//唤醒
                return;
            }
            if (eventAwake) {
                judgeSleep = false;//唤醒
                eventAwake = false;
                return;
            }
            judgeSleep = true;//沉睡
        }
        //唤醒测试

        private boolean checkAwakeMoveFollow() {
            int[] Speed;

            Speed = checkMoveSpeed(Main.robot.Ears[0],
                    new Point(Main.robot.Ears[0].Src.width / 2, Main.robot.Ears[0].Src.height),
                    new Point((Main.robot.Ears_Left_Relative_X), (Main.robot.Ears_Left_Relative_Y)), Main.robot.Ears_Zoom);
            if (Speed[0] != 0 || Speed[1] != 0) {
                return true;
            }
            Speed = checkMoveSpeed(Main.robot.Ears[1],
                    new Point(Main.robot.Ears[1].Src.width / 2, Main.robot.Ears[1].Src.height),
                    new Point((Main.robot.Ears_Right_Relative_X), (Main.robot.Ears_Right_Relative_Y)), Main.robot.Ears_Zoom);
            if (Speed[0] != 0 || Speed[1] != 0) {
                return true;
            }
            Speed = checkMoveSpeed(Main.robot.Foots[0],
                    new Point(Main.robot.Foots[0].Src.width / 2, 0),
                    new Point((Main.robot.Foots_Left_Relative_X), (Main.robot.Foots_Left_Relative_Y)), Main.robot.Foots_Zoom);
            if (Speed[0] != 0 || Speed[1] != 0) {
                return true;
            }
            Speed = checkMoveSpeed(Main.robot.Foots[1],
                    new Point(Main.robot.Foots[1].Src.width / 2, 0),
                    new Point((Main.robot.Foots_Right_Relative_X), (Main.robot.Foots_Right_Relative_Y)), Main.robot.Foots_Zoom);
            return Speed[0] != 0 || Speed[1] != 0;
        }

        private int[] checkMoveSpeed(Picture picture, Point pictureCentre, Point bodyCentre, double pictureSecondZoom) {
            double Body_Zoom = Main.robot.BasicSize / Main.robot.BasicBody.Image_B.getHeight();
            double Picture_Zoom = pictureSecondZoom * Main.robot.BasicSize / Main.robot.BasicBody.Image_B.getHeight();
            int gap_x = (int) ((Main.robot.BasicBody.Dst.x + bodyCentre.x * Body_Zoom) - (picture.Dst.x + pictureCentre.x * Picture_Zoom));
            int gap_y = (int) ((Main.robot.BasicBody.Dst.y + bodyCentre.y * Body_Zoom) - (picture.Dst.y + pictureCentre.y * Picture_Zoom));
            int[] Speed = new int[2];
            Speed[0] = Main.robot.GetFollowSpeed(gap_x, picture);
            Speed[1] = Main.robot.GetFollowSpeed(gap_y, picture);
            return Speed;
        }
        //跟随唤醒

        private boolean CheckAwake_AngleFollow() {
            return checkAngleGap(Main.robot.Ears[0])
                    || checkAngleGap(Main.robot.Ears[1])
                    || checkAngleGap(Main.robot.Foots[0])
                    || checkAngleGap(Main.robot.Foots[1]);
        }

        //角度唤醒
        private boolean checkAngleGap(Picture p) {
            return p.AngleFollowSpeed != 0;
        }

        private boolean CheckAwake_Action() {
            return checkActionBegin(Robot.Global_FishRoeAction_Tool.List_Action_FaceEmoji)
                    || checkActionBegin(Robot.Global_FishRoeAction_Tool.List_Action_EarFootsShake)
                    || checkActionBegin(Robot.Global_FishRoeAction_Tool.List_Action_Move)
                    || checkActionBegin(Robot.Global_FishRoeAction_Tool.List_Special_FoodAppeared)
                    || checkActionBegin(Robot.Global_FishRoeAction_Tool.List_Special_FoodEaten);
        }

        private boolean checkActionBegin(ArrayList<ActionObject> list) {
            if (list != null && !list.isEmpty()) {
                for (var a : list) {
                    if (a.Array_SinglePictureOperation != null) {
                        for (var o : a.Array_SinglePictureOperation) {
                            if (o.CurrentState != ActionObject_Operation.State_Sleep) {
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

    public static void Global_ThreadInit() {
        PaintSleepController = new Controller_PaintSleep();
    }

    //--------------------------------专属：MainFrame的主循环--------------------------------------


}
