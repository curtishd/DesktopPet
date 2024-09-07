package me.cdh.Draw;

/*
思考鼠标监听怎么组件化，在指定组件上点击才能产生效果
    1.绘制的每一张图都要记录它的对象，对象保存它的图片，它的鼠标事件的函数指针
    2.定义类class Picture_Object包括：
        单例路径：file；
        展示(事件)区域：Dst
        出发事件的指针函数：FunPoint_Use      可以是触发程序，也可以是绘图，指针函数自己运行
    3.Event中的FunPoint应该拆分到哪里？放到图片对象里
*/


import me.cdh.CustomMouseEvent;
import me.cdh.DrawControl.EventListen;
import me.cdh.DrawControl.MyFrame;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Picture {

    public Picture() {
    }

    public Picture(String Path) {
        Image_B = Display.Load.LoadImage(Path);
    }

    //--------------------------------构造函数--------------------------------------

    public BufferedImage Image_B;
    //图片对象

    public Display.Rect Src;
    public Display.Rect Dst;
    public Display.Rect EventJudgeRect = new Display.Rect(0, 0, 0, 0);
    //裁剪区域+绘制区域+事件判断区域

    public ArrayList<CustomMouseEvent> List_FunPoint_MouseClick = new ArrayList<>();
    public ArrayList<CustomMouseEvent> List_FunPoint_MousePress = new ArrayList<>();
    public ArrayList<CustomMouseEvent> List_FunPoint_MouseDragged = new ArrayList<>();
    public ArrayList<CustomMouseEvent> List_FunPoint_MouseMoved = new ArrayList<>();
    public ArrayList<CustomMouseEvent> List_FunPoint_MouseRelease = new ArrayList<>();
    //存储触发事件后调用的方法

    public int CurrentDegree;//当前角度
    public int TargetDegree;//目标角度
    //角度属性

    public EventListen.EventLock eventLock = new EventListen.EventLock();
    //事件锁

    public Point SrcCentre;

    public double FollowSpeed;
    public int AngleFollowSpeed = 1;
    //跟随速度

    //--------------------------------属性--------------------------------------

    //全部初始化

    //初始化链表

    //初始化事件锁

    //--------------------------------初始化--------------------------------------

    public void Draw(Graphics2D g, MyFrame frame, final int ScaleMethod) {
        this.DrawAddEvent(frame);
        //绘制了就要检查事件队列并加上

        if (this.Dst != null && this.Src != null) {
            Display.Paint.Draw_ChooseSrc_DrawDst(Image_B, Src, Dst, ScaleMethod, g);
            //绘制
        } else if (this.Src == null && this.Dst != null) {
            this.Src = new Display.Rect(0, 0, this.Image_B.getWidth(), this.Image_B.getWidth());
            Display.Paint.Draw_ChooseSrc_DrawDst(Image_B, Src, Dst, ScaleMethod, g);
        } else {
            System.out.println("Picture_Object Draw Error Dst Is Null!!");
        }

    }
    //绘制函数+添加事件队列函数

    public void DrawAddEvent(MyFrame frame) {
        if (!this.List_FunPoint_MouseClick.isEmpty()) {
            frame.List_MouseClickEvent_FramePicture.add(this);
        }
        if (!this.List_FunPoint_MouseDragged.isEmpty()) {
            frame.List_MouseDraggedEvent_FramePicture.add(this);
        }
        if (!this.List_FunPoint_MouseMoved.isEmpty()) {
            frame.List_MouseMovedEvent_FramePicture.add(this);
        }
        if (!this.List_FunPoint_MousePress.isEmpty()) {
            frame.List_MousePressEvent_FramePicture.add(this);
        }
        if (!this.List_FunPoint_MouseRelease.isEmpty()) {
            frame.List_MouseReleasedEvent_FramePicture.add(this);
        }
    }
    //绘制添加事件

    public void Reset_Dst(Point Dst_Point, Point Dst_xy, double Zoom) {
        this.Dst = Display.Paint.Get_PictureRelativeLocation_OfChoseDst(this.Src, this.SrcCentre, Dst_Point,
                (int) (this.Src.width * Zoom), (int) (this.Src.height * Zoom),
                Dst_xy);
    }
    //获得指定的Dst

    public void Draw_SinglePictureRotate(Graphics2D g, MyFrame frame, final int ScaleMethod, int Degree) {
        int Centre_x = (int) (this.Dst.x + this.SrcCentre.x * this.Dst.width / (float) this.Src.width);
        int Centre_y = (int) (this.Dst.y + this.SrcCentre.y * this.Dst.height / (float) this.Src.height);
        g.rotate(Math.toRadians(Degree), Centre_x, Centre_y);
        Draw(g, frame, ScaleMethod);
        g.rotate(Math.toRadians(-Degree), Centre_x, Centre_y);
    }
    //旋转绘图：通过旋转两次来实现单次绘图

    public void GetNewDst_AppointSrc_In_AppointDstBufferedImage(Display.Rect Src, Point Src_Point,
                                                                Point Dst_Point, int Dst_Width, int Dst_Height) {
        if (Src == null) {
            Src = new Display.Rect(0, 0, this.Image_B.getWidth(), this.Image_B.getWidth());
        }
        if (Src_Point.x >= Src.x && Src_Point.x <= Src.x + Src.width &&
                Src_Point.y >= Src.y && Src_Point.y <= Src.y + Src.height) {//所选的Src_Point在Src范围内
            int Distance_x = Dst_Width * Src_Point.x / Src.width;
            int Distance_y = Dst_Height * Src_Point.y / Src.height;
            int Object_x = Dst_Point.x - Distance_x;
            int Object_y = Dst_Point.y - Distance_y;
            this.Dst = new Display.Rect(Object_x, Object_y, Dst_Width, Dst_Height);
        } else {
            System.out.println("Draw_SrcPoint_To_DstPoint Src_Point Error");
        }
    }
    //将图片的Dst根据Src的指定点和Dst的指定点得出

    //--------------------------------绘制--------------------------------------

    public void EventAdd_MouseClick(CustomMouseEvent Mouse_FunPoint) {
        if (this.List_FunPoint_MouseClick != null) {//已经被Init函数初始化了才能添加
            List_FunPoint_MouseClick.add(Mouse_FunPoint);
        }
        //功能记录
    }
    //添加鼠标点击

    //删除鼠标点击

    public void EventAdd_MouseRelease(CustomMouseEvent Mouse_FunPoint) {
        if (this.List_FunPoint_MouseRelease != null) {//已经被Init函数初始化了才能添加
            List_FunPoint_MouseRelease.add(Mouse_FunPoint);
        }
        //功能记录
    }
    //添加鼠标松开

    //删除鼠标松开

    public void EventAdd_MousePress(CustomMouseEvent Mouse_FunPoint) {
        if (this.List_FunPoint_MousePress != null) {//已经被Init函数初始化了才能添加
            List_FunPoint_MousePress.add(Mouse_FunPoint);
        }
        //功能记录
    }
    //添加鼠标按下

    //删除鼠标按下

    public void EventAdd_MouseDragged(CustomMouseEvent Mouse_FunPoint) {
        if (this.List_FunPoint_MouseDragged != null) {//已经被Init函数初始化了才能添加
            List_FunPoint_MouseDragged.add(Mouse_FunPoint);
        }
        //功能记录

    }

    public Point Get_DstCentre(Point SrcCentre) {
        int x = (int) (Dst.x + SrcCentre.x * Dst.width / (double) Src.width);
        int y = (int) (Dst.y + SrcCentre.y * Dst.height / (double) Src.height);
        return new Point(x, y);
    }
    //获取窗口中此图片的设定中心

    public void Set_EventJudgeRect(int Width, int Height, int gap_x, int gap_y) {
        //judge_x,y的计算方法：x = dst - gap
        this.EventJudgeRect.x = this.Dst.x - gap_x;
        this.EventJudgeRect.y = this.Dst.y - gap_y;
        this.EventJudgeRect.width = Width;
        this.EventJudgeRect.height = Height;
    }
    //从新设置判断区域

    //--------------------------------功能--------------------------------------
}
