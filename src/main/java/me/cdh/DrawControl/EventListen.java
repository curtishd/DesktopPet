package me.cdh.DrawControl;

import javax.swing.*;
import java.awt.event.*;

//思考如何结局Java函数指针的问题

public class EventListen {
    private EventListen() {
    }
    //工具类，阉割对象创建

    /*
       封装：可重用，可扩展：
       1.不是所有开发者都要用所有的功能，把功能单独提出自己添加。
       2.每个调用方法都应该让开发者自己传入需要操作的方法；不能通过复制粘贴写大量无用的方法
    */

    public static class WindowEventListener {
        private WindowEventListener(){}
        //私有构造器

        public static final int EXIT_CLOSE = JFrame.EXIT_ON_CLOSE;
        public static final int EXIT_NOT_CLOSE = JFrame.DISPOSE_ON_CLOSE;
        //窗口关闭类型选择

        public static void AddWindowsExit(NewFrame frame, final int EXIT_OPTION) {
            if (EXIT_OPTION == 0) {
                return;
            }//为空无任何操纵
            else {
                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {   //windowClosing方法对应的就是窗口关闭事件
                        frame.dispose();    //当我们点击X号关闭窗口时，就会自动执行此方法了
                        //使用dispose方法来关闭当前窗口
                    }
                    //当以后企图关闭窗口（也就是点击X号）时被调用

                    @Override
                    public void windowClosed(WindowEvent e) {   //对应窗口已关闭事件
                        if (EXIT_OPTION == EXIT_CLOSE) {
                            //MainAWT.LoopTimer.CurrentOperation = MainAWT.LoopTimer.CurrentOperation_Exit;
                            //退出循环
                            System.exit(0);    //窗口关闭后退出当前Java程序
                        }
                        else if (EXIT_OPTION == EXIT_NOT_CLOSE) {
                            //什么也不做
                            System.out.println("Window has been closed!");
                            //当窗口成功关闭后，会执行这里重写的内容
                        }

                    }
                    //窗口被我们成功关闭之后被调用
                });
            }
        }
        //添加窗口关闭监听
    }
    //窗口监听

    //键盘监听

    public static class MouseEventListener {
        //私有化构造器

        public interface FunPoint_MouseEvent {
            void Objective_Fun(MouseEvent e);
        }
        //定义函数指针：定义规则(类型：FunPoint函数指针，作用事件：MouseEvent，功能：略)

        //定义函数指针：定义规则(类型：FunPoint函数指针，作用事件：MouseWheelEvent，功能：略)


        //--------------------------------函数指针--------------------------------------

        public static MouseListener AddMouseClick(FunPoint_MouseEvent Fun_Point_Click){
            return new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if(Fun_Point_Click != null){
                        Fun_Point_Click.Objective_Fun(e);
                    }
                }
            };
        }
        //鼠标点击事件

        public static MouseListener AddMousePress(FunPoint_MouseEvent Fun_Point_Press){
            return new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mouseClicked(e);
                    if(Fun_Point_Press != null){
                        Fun_Point_Press.Objective_Fun(e);
                    }
                }
            };
        }
        //鼠标按下事件

        public static MouseListener AddMouseReleased(FunPoint_MouseEvent Fun_Point_Released){
            return new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    super.mouseClicked(e);
                    if(Fun_Point_Released != null){
                        Fun_Point_Released.Objective_Fun(e);
                    }
                }
            };
        }
        //鼠标释放事件

        //鼠标滚轮事件

        //鼠标进入事件

        public static MouseMotionListener AddMouseDraggedAndMoved(FunPoint_MouseEvent Fun_Point_Dragged,
        FunPoint_MouseEvent Fun_Point_Moved){
            return new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    if(Fun_Point_Dragged != null){
                        Fun_Point_Dragged.Objective_Fun(e);
                    }
                }

                @Override
                public void mouseMoved(MouseEvent e) {
                    if(Fun_Point_Moved != null){
                        Fun_Point_Moved.Objective_Fun(e);
                    }
                }
            };
        }
        //鼠标拖拽事件移动事件

        //--------------------------------重写监听方法--------------------------------------
        //--------------------------------监听方法目标函数--------------------------------------
    }
    //鼠标监听

    public static class EventLock{



        //--------------------------------构造器--------------------------------------

        private boolean Mouse_Move;
        private boolean Mouse_Dragged;
        private boolean Mouse_Pressed;
        private boolean Mouse_Click;
        private boolean Mouse_Released;
        //鼠标锁：为true上锁
        private boolean AllMouse_Lock;

        private boolean Move_Follow;
        private boolean Move_Angle;
        //移动锁：为true上锁

        private boolean Display_Conventional;//常规绘画锁
        private boolean Once_Display;//单次绘画锁:为了已经绘画不要重复绘画
        //绘画锁：为true上锁

        //--------------------------------属性--------------------------------------



        //--------------------------------初始化--------------------------------------


        //--------------------------------刷新--------------------------------------

        public boolean isMouse_Move() {
            return Mouse_Move;
        }

        public void setMouse_Move(boolean mouse_Move) {
            if(!AllMouse_Lock){
                Mouse_Move = mouse_Move;
            }
        }

        public boolean isMouse_Dragged() {
            return Mouse_Dragged;
        }

        public void setMouse_Dragged(boolean mouse_Dragged) {
            if(!AllMouse_Lock){
                Mouse_Dragged = mouse_Dragged;
            }
        }

        public boolean isMouse_Pressed() {
            return Mouse_Pressed;
        }

        public void setMouse_Pressed(boolean mouse_Pressed) {
            if(!AllMouse_Lock){
                Mouse_Pressed = mouse_Pressed;
            }
        }

        public boolean isMouse_Click() {
            return Mouse_Click;
        }

        public void setMouse_Click(boolean mouse_Click) {
            if(!AllMouse_Lock){
                Mouse_Click = mouse_Click;
            }
        }

        public boolean isMouse_Released() {
            return Mouse_Released;
        }

        public void setMouse_Released(boolean mouse_Released) {
            if(!AllMouse_Lock){
                Mouse_Released = mouse_Released;
            }
        }

        public boolean isMove_Follow() {
            return Move_Follow;
        }

        public void setMove_Follow(boolean move_Follow) {
            Move_Follow = move_Follow;
        }

        public boolean isMove_Angle() {
            return Move_Angle;
        }

        public void setMove_Angle(boolean move_Angle) {
            Move_Angle = move_Angle;
        }

        public boolean isDisplay_Conventional() {
            return Display_Conventional;
        }

        public void setDisplay_Conventional(boolean display_Conventional) {
            Display_Conventional = display_Conventional;
        }

        public boolean isOnce_Display() {
            return Once_Display;
        }

        public void setOnce_Display(boolean once_Display) {
            Once_Display = once_Display;
        }



        public void setAllMouse_Lock(boolean allMouse_Lock) {
            AllMouse_Lock = allMouse_Lock;
        }

        //--------------------------------set+get私有属性--------------------------------------
    }
    //事件锁

}
//Frame事件监听

