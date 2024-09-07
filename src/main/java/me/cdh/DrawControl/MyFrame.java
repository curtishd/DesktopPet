package me.cdh.DrawControl;

import me.cdh.CustomMouseEvent;
import me.cdh.Draw.Picture;
import me.cdh.Main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;


//封装：可读，可重用，可扩展

public class MyFrame extends JFrame {

    //私有化构造器

    //--------------------------------构造函数--------------------------------------

    public ArrayList<Picture> MouseClickEvent = new ArrayList<>();

    public ArrayList<Picture> MousePressEvent = new ArrayList<>();

    public ArrayList<Picture> MouseDraggedEvent = new ArrayList<>();

    public ArrayList<Picture> MouseMovedEvent = new ArrayList<>();

    public ArrayList<Picture> MouseReleasedEvent = new ArrayList<>();
    //定义窗口的事件集合

    //--------------------------------属性--------------------------------------

    public static double[] GetScreenWH() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        double width = dim.getWidth();
        double height = dim.getHeight();
        return new double[]{width, height};
    }
    //获取屏幕的宽高

    public static class SetTool {
        private SetTool() {
        }
        //工具类阉割对象创建


        //用于提供选择的常量
        public static void SetNewFrame(MyFrame frame, String Title,//窗口+标题
                                       int width, int height, int Location_x, int Location_y,//窗口大小和起始位置
                                       boolean Resizable, boolean AlwaysOnTop, boolean Visible, boolean Undecorated,//窗口的一些布尔选项
                                       final int EXIT_OPTION,
                                       Color Background) {
            frame.setTitle(Title);
            //创建+标题

            frame.setSize(width, height);
            frame.setLocation(Location_x, Location_y);
            //大小和位置

            frame.setResizable(Resizable);//是否可变大小
            frame.setAlwaysOnTop(AlwaysOnTop);//是否总是在最前面
            frame.setUndecorated(Undecorated);

            if (Undecorated) {
                frame.setBackground(Background);//设置透明必须需要Undecorated
            } else {
                frame.setBackground(new Color(Background.getRed(), Background.getGreen(), Background.getBlue()));
                //设置透明必须需要Undecorated
            }
            //设定没有边框才能使用透明A

            EventListen.WindowEventListener.AddWindowsExit(frame, EXIT_OPTION);
            //Windows监听器

            frame.setVisible(Visible);//窗口是否可见
        }

        //重写，提供更多参数

    }
    //创建工具类，单纯为了收紧代码

    //继承JFrame的嵌套类(类本身继承的Frame，让嵌套类继承JFrame)

    //初始化

    @Override
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            return; //直接返回，阻止默认动作，阻止窗口关闭
        }
        super.processWindowEvent(e);
    }
    //阻止关闭
    ;
    //--------------------------------工具方法，类--------------------------------------

    public void Refresh_PictureList() {
        //System.out.println(List_MouseClickEvent_FramePicture.size());
        if (this.MouseClickEvent != null) {
            while (!this.MouseClickEvent.isEmpty()) {
                this.MouseClickEvent.removeFirst();
            }
            //清空刷新队列
        }

        if (this.MouseDraggedEvent != null) {
            while (!this.MouseDraggedEvent.isEmpty()) {
                this.MouseDraggedEvent.removeFirst();
            }
            //清空刷新队列
        }

        if (this.MouseMovedEvent != null) {
            while (!this.MouseMovedEvent.isEmpty()) {
                this.MouseMovedEvent.removeFirst();
            }
            //清空刷新队列
        }

        if (this.MousePressEvent != null) {
            while (!this.MousePressEvent.isEmpty()) {
                this.MousePressEvent.removeFirst();
            }
            //清空刷新队列
        }
        if (this.MouseReleasedEvent != null) {
            while (!this.MouseReleasedEvent.isEmpty()) {
                this.MouseReleasedEvent.removeFirst();
            }
            //清空刷新队列
        }

    }
    //刷新图片事件

    public void FrameMain_EventListener(CustomMouseEvent FunPoint_Click,
                                        CustomMouseEvent FunPoint_Press,
                                        CustomMouseEvent FunPoint_Release,
                                        CustomMouseEvent FunPoint_Dragged,
                                        CustomMouseEvent FunPoint_Moved) {
        this.addMouseListener(EventListen.MouseEventListener.AddMouseClick(
                FunPoint_Click
        ));
        this.addMouseListener(EventListen.MouseEventListener.AddMousePress(
                FunPoint_Press));
        this.addMouseListener(EventListen.MouseEventListener.AddMouseReleased(
                FunPoint_Release));
        this.addMouseMotionListener(
                EventListen.MouseEventListener.AddMouseDraggedAndMoved(
                        FunPoint_Dragged,
                        FunPoint_Moved
                ));
    }
    //窗口主事件监听

    //--------------------------------事件监听--------------------------------------

    public static void MainFrame_EventListener_MouseClick(MouseEvent e) {
        Check_MouseEvent(e, Main.MainFrame.MouseClickEvent, CHOOSE_Check_MouseEvent_Click);
    }
    //MainFrame处理MouseClick的方法

    public static void MainFrame_EventListener_MousePress(MouseEvent e) {
        Check_MouseEvent(e, Main.MainFrame.MousePressEvent, CHOOSE_Check_MouseEvent_Press);
    }
    //MainFrame处理MouseClick的方法

    public static void MainFrame_EventListener_MouseDragged(MouseEvent e) {
        Check_MouseEvent(e, Main.MainFrame.MouseDraggedEvent, CHOOSE_Check_MouseEvent_Dragged);
    }
    //MainFrame处理MouseDragged的方法

    public static void MainFrame_EventListener_MouseMoved(MouseEvent e) {
        Check_MouseEvent(e, Main.MainFrame.MouseMovedEvent, CHOOSE_Check_MouseEvent_Moved);
    }
    //MainFrame处理MouseMoved的方法

    public static void MainFrame_EventListener_MouseReleased(MouseEvent e) {
        Check_MouseEvent(e, Main.MainFrame.MouseReleasedEvent, CHOOSE_Check_MouseEvent_Release);
        //检查其他应该要执行的方法
        Main.robot.MouseReleased_AllUnlock();
        //必须执行的方法
    }
    //MainFrame处理MouseMoved的方法

    public static final int CHOOSE_Check_MouseEvent_Click = 1,
            CHOOSE_Check_MouseEvent_Press = 2,
            CHOOSE_Check_MouseEvent_Dragged = 3,
            CHOOSE_Check_MouseEvent_Moved = 4,
            CHOOSE_Check_MouseEvent_Release = 5;
    //给Check_MouseEvent函数判定用的

    public static void Check_MouseEvent(MouseEvent e, ArrayList<Picture> List_Picture,
                                        final int CHOOSE_EventFunPoint) {
        if (List_Picture != null && !List_Picture.isEmpty()) {//监听集合不为空且有操作函数
            for (int Num = List_Picture.size(); Num > 0; Num--) {
                //倒序检查，因为要先触发在图像最上面的

                Picture CurrentTemp = List_Picture.get(Num - 1);//倒叙之后必须从Num - 1开始
                if (CurrentTemp.EventJudgeRect.judgePointInRect(e.getX(), e.getY())) {//现在改为了JudgeRect：判断区域自己给予

                    ArrayList<CustomMouseEvent> Temp_List_FunPoint;
                    if (CHOOSE_EventFunPoint == CHOOSE_Check_MouseEvent_Click) {
                        if (CurrentTemp.eventLock.isMouse_Click()) {
                            return;
                        } else {
                            Temp_List_FunPoint = CurrentTemp.mouseClick;
                        }
                    } else if (CHOOSE_EventFunPoint == CHOOSE_Check_MouseEvent_Dragged) {
                        if (CurrentTemp.eventLock.isMouse_Dragged()) {//如果已经上锁
                            return;
                        } else {
                            Temp_List_FunPoint = CurrentTemp.mouseDragged;
                        }
                    } else if (CHOOSE_EventFunPoint == CHOOSE_Check_MouseEvent_Moved) {
                        if (CurrentTemp.eventLock.isMouse_Move()) {
                            return;
                        } else {
                            Temp_List_FunPoint = CurrentTemp.mouseMoved;
                        }
                    } else if (CHOOSE_EventFunPoint == CHOOSE_Check_MouseEvent_Press) {
                        if (CurrentTemp.eventLock.isMouse_Pressed()) {
                            return;
                        } else {
                            Temp_List_FunPoint = CurrentTemp.mousePress;
                        }
                    } else if (CHOOSE_EventFunPoint == CHOOSE_Check_MouseEvent_Release) {
                        if (CurrentTemp.eventLock.isMouse_Released()) {
                            return;
                        } else {
                            Temp_List_FunPoint = CurrentTemp.mouseRelease;
                        }
                    } else {
                        Temp_List_FunPoint = null;
                    }
                    //给CurrentTemp附通用值

                    if (Temp_List_FunPoint != null && !Temp_List_FunPoint.isEmpty()) {
                        if (CHOOSE_EventFunPoint == CHOOSE_Check_MouseEvent_Dragged) {
                            for (int i = 0; i < List_Picture.size(); i++) {
                                if (i != Num - 1) {//把不是此函数的锁上
                                    List_Picture.get(i).eventLock.setMouse_Dragged(true);//上锁
                                }
                            }
                            //给其他拖拽上锁
                        }
                        //给其他拖拽上锁
                        for (CustomMouseEvent f : Temp_List_FunPoint) {
                            f.event(e);
                        }
                        //循环执行完毕集合中的方法
                        return;
                        //退出
                    }
                }
            }
        }
        //检查指定的方法中是否有指定的事件
    }
    //--------------------------------专属：MainFrame事件监听方法--------------------------------------
}
