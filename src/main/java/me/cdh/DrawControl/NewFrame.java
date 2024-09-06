package me.cdh.DrawControl;

import me.cdh.Draw.Picture;
import me.cdh.Main.MainAWT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;


//封装：可读，可重用，可扩展

public class NewFrame extends JFrame {

    public NewFrame() {
        super();
        Init();
    }
    //私有化构造器

    //--------------------------------构造函数--------------------------------------

    public ArrayList<Picture> List_MouseClickEvent_FramePicture;

    public ArrayList<Picture> List_MousePressEvent_FramePicture;

    public ArrayList<Picture> List_MouseDraggedEvent_FramePicture;

    public ArrayList<Picture> List_MouseMovedEvent_FramePicture;

    public ArrayList<Picture> List_MouseReleasedEvent_FramePicture;
    //定义窗口的事件集合

    //--------------------------------属性--------------------------------------

    public static class ColorRBGA extends Color {
        private final int r;
        private final int g;
        private final int b;

        public ColorRBGA(int r, int g, int b, int a) {
            super(r, g, b, a);
            this.r = r;
            this.g = g;
            this.b = b;
            //静态类可以创建非static属性，但是不能使用外部非static属性
        }
    }
    //构建自己的颜色

    public static double[] GetScreenWH() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        double width = dim.getWidth();
        double height = dim.getHeight();
        double[] WH = new double[2];
        WH[0] = width;
        WH[1] = height;
        return WH;
    }
    //获取屏幕的宽高

    public static class SetTool {
        private SetTool() {
        }
        //工具类阉割对象创建


        //用于提供选择的常量
        public static void SetNewFrame(NewFrame frame, String Title,//窗口+标题
                                       int width, int height, int Location_x, int Location_y,//窗口大小和起始位置
                                       boolean Resizable, boolean AlwaysOnTop, boolean Visible, boolean Undecorated,//窗口的一些布尔选项
                                       final int EXIT_OPTION,
                                       ColorRBGA Background) {
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
            }
            else {
                frame.setBackground(new Color(Background.r, Background.g, Background.b));
                //设置透明必须需要Undecorated
            }
            //设定没有边框才能使用透明A

            EventListen.WindowEventListener.AddWindowsExit(frame, EXIT_OPTION);
            //Windows监听器

            frame.setVisible(Visible);//窗口是否可见
        }

        public static void SetNewJFrame(NewJFrame frame, String Title,//窗口+标题
                                        int width, int height, int Location_x, int Location_y,//窗口大小和起始位置
                                        boolean Resizable, boolean AlwaysOnTop, boolean Visible, boolean Undecorated,//窗口的一些布尔选项
                                        int DefaultCloseOperation,//设置常量选项
                                        ColorRBGA Background) {
            frame.setTitle(Title);
            //创建+标题

            frame.setSize(width, height);
            frame.setLocation(Location_x, Location_y);
            //大小和位置

            frame.setResizable(Resizable);//是否可变大小
            frame.setAlwaysOnTop(AlwaysOnTop);//是否总是在最前面

            frame.setDefaultCloseOperation(DefaultCloseOperation);
            //设置默认的关闭方法
            frame.setUndecorated(Undecorated);
            if (Undecorated) {
                frame.setBackground(Background);//设置透明必须需要Undecorated
            } else {
                frame.setBackground(new Color(Background.r, Background.g, Background.b));
                //设置透明必须需要Undecorated
            }
            //设定没有边框才能使用透明A

            frame.setVisible(Visible);//窗口是否可见
        }
        //重写，提供更多参数

    }
    //创建工具类，单纯为了收紧代码

    public static class NewJFrame extends JFrame {
        public NewJFrame() {
            super();
        }
    }
    //继承JFrame的嵌套类(类本身继承的Frame，让嵌套类继承JFrame)

    public void Init(){
        List_MouseClickEvent_FramePicture = new ArrayList<>();
        List_MousePressEvent_FramePicture = new ArrayList<>();
        List_MouseDraggedEvent_FramePicture = new ArrayList<>();
        List_MouseMovedEvent_FramePicture = new ArrayList<>();
        List_MouseReleasedEvent_FramePicture = new ArrayList<>();
    }
    //初始化

    @Override
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING){
            return; //直接返回，阻止默认动作，阻止窗口关闭
        }
        super.processWindowEvent(e);
    }
    //阻止关闭
;
    //--------------------------------工具方法，类--------------------------------------

    public void Refresh_PictureList(){
        //System.out.println(List_MouseClickEvent_FramePicture.size());
        if(this.List_MouseClickEvent_FramePicture != null){
            while (!this.List_MouseClickEvent_FramePicture.isEmpty()){
                this.List_MouseClickEvent_FramePicture.removeFirst();
            }
            //清空刷新队列
        }

        if(this.List_MouseDraggedEvent_FramePicture != null){
            while (!this.List_MouseDraggedEvent_FramePicture.isEmpty()){
                this.List_MouseDraggedEvent_FramePicture.removeFirst();
            }
            //清空刷新队列
        }

        if(this.List_MouseMovedEvent_FramePicture != null){
            while (!this.List_MouseMovedEvent_FramePicture.isEmpty()){
                this.List_MouseMovedEvent_FramePicture.removeFirst();
            }
            //清空刷新队列
        }

        if(this.List_MousePressEvent_FramePicture != null){
            while (!this.List_MousePressEvent_FramePicture.isEmpty()){
                this.List_MousePressEvent_FramePicture.removeFirst();
            }
            //清空刷新队列
        }
        if(this.List_MouseReleasedEvent_FramePicture != null){
            while (!this.List_MouseReleasedEvent_FramePicture.isEmpty()){
                this.List_MouseReleasedEvent_FramePicture.remove(0);
            }
            //清空刷新队列
        }

    }
    //刷新图片事件

    public void FrameMain_EventListener(EventListen.MouseEventListener.FunPoint_MouseEvent FunPoint_Click,
                                        EventListen.MouseEventListener.FunPoint_MouseEvent FunPoint_Press,
                                        EventListen.MouseEventListener.FunPoint_MouseEvent FunPoint_Release,
                                        EventListen.MouseEventListener.FunPoint_MouseEvent FunPoint_Dragged,
                                        EventListen.MouseEventListener.FunPoint_MouseEvent FunPoint_Moved){
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

    public static void MainFrame_EventListener_MouseClick(MouseEvent e){
        Check_MouseEvent(e,MainAWT.MainFrame.List_MouseClickEvent_FramePicture,CHOOSE_Check_MouseEvent_Click);
    }
    //MainFrame处理MouseClick的方法

    public static void MainFrame_EventListener_MousePress(MouseEvent e){
        Check_MouseEvent(e,MainAWT.MainFrame.List_MousePressEvent_FramePicture,CHOOSE_Check_MouseEvent_Press);
    }
    //MainFrame处理MouseClick的方法

    public static void MainFrame_EventListener_MouseDragged(MouseEvent e){
        Check_MouseEvent(e,MainAWT.MainFrame.List_MouseDraggedEvent_FramePicture,CHOOSE_Check_MouseEvent_Dragged);
    }
    //MainFrame处理MouseDragged的方法

    public static void MainFrame_EventListener_MouseMoved(MouseEvent e){
        Check_MouseEvent(e,MainAWT.MainFrame.List_MouseMovedEvent_FramePicture,CHOOSE_Check_MouseEvent_Moved);
    }
    //MainFrame处理MouseMoved的方法

    public static void MainFrame_EventListener_MouseReleased(MouseEvent e){
        Check_MouseEvent(e,MainAWT.MainFrame.List_MouseReleasedEvent_FramePicture,CHOOSE_Check_MouseEvent_Release);
        //检查其他应该要执行的方法
        MainAWT.FishRoe.MouseReleased_AllUnlock();
        //必须执行的方法
    }
    //MainFrame处理MouseMoved的方法

    public static final int CHOOSE_Check_MouseEvent_Click = 1,
            CHOOSE_Check_MouseEvent_Press = 2,
            CHOOSE_Check_MouseEvent_Dragged = 3,
            CHOOSE_Check_MouseEvent_Moved = 4,
            CHOOSE_Check_MouseEvent_Release = 5;
    //给Check_MouseEvent函数判定用的

    public static void Check_MouseEvent(MouseEvent e,ArrayList<Picture> List_Picture,
    final int CHOOSE_EventFunPoint) {
        if (List_Picture != null && !List_Picture.isEmpty()) {//监听集合不为空且有操作函数
            for (int Num = List_Picture.size(); Num > 0; Num--) {
                //倒序检查，因为要先触发在图像最上面的

                Picture CurrentTemp = List_Picture.get(Num - 1);//倒叙之后必须从Num - 1开始
                if (CurrentTemp.EventJudgeRect.Judge_PointInRect(e.getX(), e.getY())) {//现在改为了JudgeRect：判断区域自己给予

                    ArrayList<EventListen.MouseEventListener.FunPoint_MouseEvent> Temp_List_FunPoint;
                    if (CHOOSE_EventFunPoint == CHOOSE_Check_MouseEvent_Click) {
                        if(CurrentTemp.eventLock.isMouse_Click()){
                            return;
                        }else{
                            Temp_List_FunPoint = CurrentTemp.List_FunPoint_MouseClick;
                        }
                    }
                    else if (CHOOSE_EventFunPoint == CHOOSE_Check_MouseEvent_Dragged) {
                        if (CurrentTemp.eventLock.isMouse_Dragged()) {//如果已经上锁
                            return;
                        } else {
                            Temp_List_FunPoint = CurrentTemp.List_FunPoint_MouseDragged;
                        }
                    }
                    else if (CHOOSE_EventFunPoint == CHOOSE_Check_MouseEvent_Moved) {
                        if(CurrentTemp.eventLock.isMouse_Move()){
                            return;
                        }else {
                            Temp_List_FunPoint = CurrentTemp.List_FunPoint_MouseMoved;
                        }
                    }
                    else if (CHOOSE_EventFunPoint == CHOOSE_Check_MouseEvent_Press) {
                        if(CurrentTemp.eventLock.isMouse_Pressed()){
                            return;
                        }
                        else{
                            Temp_List_FunPoint = CurrentTemp.List_FunPoint_MousePress;
                        }
                    }
                    else if (CHOOSE_EventFunPoint == CHOOSE_Check_MouseEvent_Release) {
                        if(CurrentTemp.eventLock.isMouse_Released()){
                            return;
                        }
                        else{
                            Temp_List_FunPoint = CurrentTemp.List_FunPoint_MouseRelease;
                        }
                    }
                    else {
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
                        for (EventListen.MouseEventListener.FunPoint_MouseEvent f : Temp_List_FunPoint) {
                            f.Objective_Fun(e);
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
