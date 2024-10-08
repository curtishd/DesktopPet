package me.cdh;

import me.cdh.Control.ThreadControl;
import me.cdh.Control.UserData;
import me.cdh.DestopPet.Robot;
import me.cdh.Draw.Display;
import me.cdh.DrawControl.MyFrame;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static final String RESOURCE_PATH = "target/classes/me/cdh/";

    public static void main(String[] args) {
        MainFrame = new MyFrame();
        MyFrame.SetTool.SetNewFrame(MainFrame,"window",
                (int) MyFrame.GetScreenWH()[0],(int) MyFrame.GetScreenWH()[1],0,0,
                false,true,false,true,
                JFrame.EXIT_ON_CLOSE,
                new Color(0,0,0,0));

        //--------------------------------窗口初始化--------------------------------------

        Main.Init();
        //全部数据初始化位置

        //--------------------------------全局变量初始化--------------------------------------

        MainFrame.add(PaintTool_MainFrame);
        MainFrame.setVisible(true);

        //--------------------------------绘制图像工具初始化--------------------------------------

        MainFrame.FrameMain_EventListener(MyFrame::MainFrame_EventListener_MouseClick,
                MyFrame::MainFrame_EventListener_MousePress,
                MyFrame::MainFrame_EventListener_MouseReleased,
                MyFrame::MainFrame_EventListener_MouseDragged,
                MyFrame::MainFrame_EventListener_MouseMoved);

        //--------------------------------事件监听--------------------------------------
        threadControl = new ThreadControl(ThreadControl::MainLoop_ForMainFrame);
        threadControl.run();
        //线程

        //MainFrame.setVisible(true);
        //--------------------------------创建时间控制器--------------------------------------



        //--------------------------------Experiment--------------------------------------
    }

    public static MyFrame MainFrame;//作为一个属性方便其他class文件调用
    //主窗口

    public static Display.Paint PaintTool_MainFrame;
    //MainFrame的绘图工具

    public static ThreadControl threadControl;
    //循环线程

    public static Robot robot;
    //鱼籽

    public static UserData userData;

    //取消多线程，以后再学习优化

    public static void Init(){
        PaintTool_MainFrame = new Display.Paint();
        //创建MainFrame的绘图工具
        //初始化绘图的数据
        Robot.Global_Init();
    }
}
