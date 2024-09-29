package me.cdh.DestopPet;

import me.cdh.Main;
import me.cdh.Control.RobotSize;
import me.cdh.Control.ThreadControl;
import me.cdh.Control.UserData;
import me.cdh.Draw.Display;
import me.cdh.Draw.Load;
import me.cdh.Draw.Picture;
import me.cdh.DrawControl.MyFrame;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Robot {

    public Robot() {
        this.Init();
    }

    //--------------------------------构造器--------------------------------------

    public Picture BasicBody;
    public boolean Judge_BasicBodyEventRectChange;
    //主要身体
    public Picture HighLight;
    //高光
    public Picture BasicEyes;
    //最基础的眼睛
    public Picture[] Ears;
    //耳朵：注意有两个，因为有两个事件监听
    public Picture[] Foots;
    //脚脚：注意有两个，因为有两个事件监听
    public Picture Squint;
    //眯眼
    public Picture Doubt;
    //疑惑
    public Picture[] LoveEyes1;
    //爱心眼
    public Picture[] StarEyes;
    //星星眼
    public Picture Surprised;
    //惊叹
    public Picture AngryEye;
    //惊叹
    public Picture SadEye;
    //伤心
    public Picture Grievance_Angry;
    //生气到委屈
    public Picture Grievance_Squint;
    //眯眼委屈
    public Picture Grievance_Sad;
    //伤心到委屈
    public Picture HappyEye;
    //开心眼睛
    public Picture[] BasicHatch;
    //舱门
    public Picture[] Dizzy;
    //眩晕
    public Picture XX;
    //叉叉眼睛
    public Picture Helmet;
    //头盔


    //--------------------------------属性（图片对象）--------------------------------------

    public double BasicSize;
    //基础大小
    public double Basic_Zoom = BasicSize / 785.0;
    final int Eyes_Relative_Y = 250, EyesHappy_Relative_Y = 315;
    final int Eyes_Relative_X_left = 288, Eyes_Relative_X_right = 496;
    //True
    public int Ears_Left_Relative_X, Ears_Left_Relative_Y, Ears_Right_Relative_X, Ears_Right_Relative_Y, Foots_Left_Relative_X,
            Foots_Left_Relative_Y, Foots_Right_Relative_X, Foots_Right_Relative_Y;
    public static final int Helmet_Sakura_Relative_Y = 200, Helmet_SakuraNull_Relative_Y = 150;
    //Exp
    final int Hatch_Relative_Y = 591;
    public final double Ears_Zoom = 0.75;
    public final double Foots_Zoom = 0.95;
    //放缩

    final double FollowSpeed_Final = 2, FollowSpeed_Helmet = 1.2;
    final double AngleSpeed = 3;//角度回复的快慢
    double AngleMoveRate = 2.0;//y = kx (k = AngleMoveRate)      角度受影响的大小
    final int Ears_StartCurrentDegree = 100;
    final int Foots_StartCurrentDegree = 100;
    public final int Ears_StartTargetDegree = 38;
    public final int Foots_StartTargetDegree = 42;
    //加载属性
    final int Judge_BasicBody_x = 1600, Judge_BasicBody_y = 1600, Judge_BasicBody_w = 785 + 2 * Judge_BasicBody_x, Judge_BasicBody_h = Judge_BasicBody_w;
    //True
    public int Judge_Ears1_x, Judge_Ears1_y, Judge_Ears1_w, Judge_Ears1_h, Judge_Ears2_x, Judge_Ears2_y, Judge_Ears2_w, Judge_Ears2_h,
            Judge_Foots1_x, Judge_Foots1_y, Judge_Foots1_w, Judge_Foots1_h, Judge_Foots2_x, Judge_Foots2_y, Judge_Foots2_w, Judge_Foots2_h;
    //Exp
    final int Judge_Eye_x = 50, Judge_Eye_y = 50, Judge_Eye_w = 260 + 100, Judge_Eye_h = 155 + 100;
    //判断图片的Rect

    public int PictureNum_Blink = 1;
    public int PictureNum_Shake = 4;
    //动作图片张数数据

    public static long Time_Blink_Lower = 8L * 1000, Time_Blink_Upper = 15 * 1000;
    public static long Time_Shake_Static = 15 * 1000;
    //随机时间设置

    //--------------------------------final常量--------------------------------------

    public void Init() {//这个类暂时就创造一个对象，所以暂时多有都为一个对象服务，不需要实现封装

        this.Init_Picture();
        //图片加载

        this.Init_PictureForClothes();
        this.Init_AfterSetUp();
        //数据加载：设置后刷新

        this.Init_LoadOnce();
        //数据加载：一次加载
    }
    //全部初始化

    public void Init_Picture() {
        this.BasicBody = new Picture("Picture/BasicBody.png");
        this.HighLight = new Picture("Picture/HighLight.png");
        this.BasicEyes = new Picture("Picture/BasicEyes.png");
        this.Ears = new Picture[2];
        this.Ears[0] = new Picture("Picture/Ears.png");
        this.Ears[1] = new Picture("Picture/Ears.png");
        this.Foots = new Picture[2];
        this.Foots[0] = new Picture("Picture/Foot.png");
        this.Foots[1] = new Picture("Picture/Foot.png");
        this.Squint = new Picture("Picture/Squint.png");
        this.Doubt = new Picture("Picture/Doubt.png");
        this.LoveEyes1 = new Picture[2];
        this.LoveEyes1[0] = new Picture("Picture/LoveEyes1.png");
        this.LoveEyes1[1] = new Picture("Picture/LoveEyes1.png");
        this.StarEyes = new Picture[2];
        this.StarEyes[0] = new Picture("Picture/StarEyes1.png");
        this.StarEyes[1] = new Picture("Picture/StarEyes2.png");
        this.Surprised = new Picture("Picture/Surprised.png");
        this.AngryEye = new Picture("Picture/Angry.png");
        this.SadEye = new Picture("Picture/Sad.png");
        this.Grievance_Angry = new Picture("Picture/Grievance1.png");
        this.Grievance_Squint = new Picture("Picture/Grievance2.png");
        this.Grievance_Sad = new Picture("Picture/Grievance3.png");
        this.HappyEye = new Picture("Picture/Happy.png");
        this.BasicHatch = new Picture[2];
        this.BasicHatch[0] = new Picture("Picture/Eat/Hatch.png");
        this.BasicHatch[1] = new Picture("Picture/Eat/Hatch.png");
        this.Dizzy = new Picture[2];
        this.Dizzy[0] = new Picture("Picture/Dizzy1.png");
        this.Dizzy[1] = new Picture("Picture/Dizzy2.png");
        this.XX = new Picture("Picture/XX.png");
        //加载图片
    }
    //图片加载

    public void Init_PictureForClothes() {
        if (Main.userData.clothes.ClothesType == UserData.Clothes.ClothesType_Basic) {
            Ears_Left_Relative_X = 55;
            Ears_Left_Relative_Y = 55;
            Ears_Right_Relative_X = 784 - Ears_Left_Relative_X;
            Ears_Right_Relative_Y = Ears_Left_Relative_Y;
            Foots_Left_Relative_X = 20;
            Foots_Left_Relative_Y = 785 - 50;
            Foots_Right_Relative_X = 784 - Foots_Left_Relative_X;
            Foots_Right_Relative_Y = Foots_Left_Relative_Y;
            //偏移量
            Judge_Ears1_x = 600 + 600;
            Judge_Ears1_y = -100 + 600;
            Judge_Ears1_w = 600 + 95 + 600;
            Judge_Ears1_h = Judge_Ears1_w;
            Judge_Ears2_x = -65;
            Judge_Ears2_y = Judge_Ears1_y;
            Judge_Ears2_w = Judge_Ears1_w;
            Judge_Ears2_h = Judge_Ears1_h;
            Judge_Foots1_x = 400 - 95;
            Judge_Foots1_y = 20;
            Judge_Foots1_w = 400;
            Judge_Foots1_h = Judge_Foots1_w;
            Judge_Foots2_x = -20;
            Judge_Foots2_y = Judge_Foots1_y;
            Judge_Foots2_w = Judge_Foots1_w;
            Judge_Foots2_h = Judge_Foots2_w;
            //判定区域
            InterfaceButton.Button.Switch_Off = Load.loadImage("Picture/Switch1.png");
        } else if (Main.userData.clothes.ClothesType == UserData.Clothes.ClothesType_Fat) {
            Ears_Left_Relative_X = 60;
            Ears_Left_Relative_Y = 60;
            Ears_Right_Relative_X = 784 - Ears_Left_Relative_X;
            Ears_Right_Relative_Y = Ears_Left_Relative_Y;
            Foots_Left_Relative_X = 70;
            Foots_Left_Relative_Y = 785 - 70;
            Foots_Right_Relative_X = 784 - Foots_Left_Relative_X;
            Foots_Right_Relative_Y = Foots_Left_Relative_Y;
            //偏移量
            Judge_Ears1_x = 600 + 555;
            Judge_Ears1_y = -100 + 600;
            Judge_Ears1_w = 600 + 95 + 600;
            Judge_Ears1_h = Judge_Ears1_w;
            Judge_Ears2_x = -65;
            Judge_Ears2_y = Judge_Ears1_y;
            Judge_Ears2_w = Judge_Ears1_w;
            Judge_Ears2_h = Judge_Ears1_h;
            Judge_Foots1_x = 400 - 95;
            Judge_Foots1_y = 30;
            Judge_Foots1_w = 400;
            Judge_Foots1_h = Judge_Foots1_w;
            Judge_Foots2_x = -20;
            Judge_Foots2_y = Judge_Foots1_y;
            Judge_Foots2_w = Judge_Foots1_w;
            Judge_Foots2_h = Judge_Foots2_w;
            //判定区域
            this.Ears[0] = new Picture("Picture/Clothes/Fat/Ears.png");
            this.Ears[1] = new Picture("Picture/Clothes/Fat/Ears.png");
            this.Foots[0] = new Picture("Picture/Clothes/Fat/Foot.png");
            this.Foots[1] = new Picture("Picture/Clothes/Fat/Foot.png");
            InterfaceButton.Button.Switch_Off = Load.loadImage("Picture/Switch1.png");
            //图片对象
        } else if (Main.userData.clothes.ClothesType == UserData.Clothes.ClothesType_Null) {
            Ears_Left_Relative_X = 55;
            Ears_Left_Relative_Y = 55;
            Ears_Right_Relative_X = 784 - Ears_Left_Relative_X;
            Ears_Right_Relative_Y = Ears_Left_Relative_Y;
            Foots_Left_Relative_X = 20;
            Foots_Left_Relative_Y = 785 - 50;
            Foots_Right_Relative_X = 784 - Foots_Left_Relative_X;
            Foots_Right_Relative_Y = Foots_Left_Relative_Y;
            //偏移量
            Judge_Ears1_x = 600 + 600;
            Judge_Ears1_y = -100 + 600;
            Judge_Ears1_w = 600 + 95 + 600;
            Judge_Ears1_h = Judge_Ears1_w;
            Judge_Ears2_x = -65;
            Judge_Ears2_y = Judge_Ears1_y;
            Judge_Ears2_w = Judge_Ears1_w;
            Judge_Ears2_h = Judge_Ears1_h;
            Judge_Foots1_x = 400 - 95;
            Judge_Foots1_y = 20;
            Judge_Foots1_w = 400;
            Judge_Foots1_h = Judge_Foots1_w;
            Judge_Foots2_x = -20;
            Judge_Foots2_y = Judge_Foots1_y;
            Judge_Foots2_w = Judge_Foots1_w;
            Judge_Foots2_h = Judge_Foots2_w;
            //判定区域
            this.Ears[0] = new Picture("Picture/Clothes/Null/Null.png");
            this.Ears[1] = new Picture("Picture/Clothes/Null/Null.png");
            this.Foots[0] = new Picture("Picture/Clothes/Null/Null.png");
            this.Foots[1] = new Picture("Picture/Clothes/Null/Null.png");
            InterfaceButton.Button.Switch_Off = Load.loadImage("Picture/Switch1.png");
            //图片对象
        } else if (Main.userData.clothes.ClothesType == UserData.Clothes.ClothesType_Sakura) {
            Ears_Left_Relative_X = 55;
            Ears_Left_Relative_Y = -80;
            Ears_Right_Relative_X = 784 - Ears_Left_Relative_X;
            Ears_Right_Relative_Y = Ears_Left_Relative_Y;
            Foots_Left_Relative_X = 50;
            Foots_Left_Relative_Y = 785 - 100;
            Foots_Right_Relative_X = 784 - Foots_Left_Relative_X;
            Foots_Right_Relative_Y = Foots_Left_Relative_Y;
            //偏移量
            Judge_Ears1_x = 300;
            Judge_Ears1_y = -100 + 300;
            Judge_Ears1_w = 300 + 95 + 300;
            Judge_Ears1_h = Judge_Ears1_w;
            Judge_Ears2_x = 90;
            Judge_Ears2_y = Judge_Ears1_y;
            Judge_Ears2_w = Judge_Ears1_w;
            Judge_Ears2_h = Judge_Ears1_h;
            Judge_Foots1_x = 400 - 135;
            Judge_Foots1_y = 50;
            Judge_Foots1_w = 400;
            Judge_Foots1_h = Judge_Foots1_w;
            Judge_Foots2_x = 20;
            Judge_Foots2_y = Judge_Foots1_y;
            Judge_Foots2_w = Judge_Foots1_w;
            Judge_Foots2_h = Judge_Foots2_w;
            //判定区域
            this.BasicBody = new Picture("Picture/Clothes/Sakura/BasicBody.png");
            this.BasicEyes = new Picture("Picture/Clothes/Sakura/BasicEyes.png");
            this.Ears[0] = new Picture("Picture/Clothes/Sakura/Ears1.png");
            this.Ears[1] = new Picture("Picture/Clothes/Sakura/Ears2.png");
            this.Foots[0] = new Picture("Picture/Clothes/Sakura/Foot.png");
            this.Foots[1] = new Picture("Picture/Clothes/Sakura/Foot.png");
            this.Squint = new Picture("Picture/Clothes/Sakura/Squint.png");
            this.Doubt = new Picture("Picture/Clothes/Sakura/Doubt.png");
            this.LoveEyes1[0] = new Picture("Picture/Clothes/Sakura/LoveEyes1.png");
            this.LoveEyes1[1] = new Picture("Picture/Clothes/Sakura/LoveEyes1.png");
            this.StarEyes[0] = new Picture("Picture/Clothes/Sakura/StarEyes1.png");
            this.StarEyes[1] = new Picture("Picture/Clothes/Sakura/StarEyes2.png");
            this.Surprised = new Picture("Picture/Clothes/Sakura/Surprised.png");
            this.AngryEye = new Picture("Picture/Clothes/Sakura/Angry.png");
            this.SadEye = new Picture("Picture/Clothes/Sakura/Sad.png");
            this.Grievance_Angry = new Picture("Picture/Clothes/Sakura/Grievance1.png");
            this.Grievance_Squint = new Picture("Picture/Clothes/Sakura/Grievance2.png");
            this.Grievance_Sad = new Picture("Picture/Clothes/Sakura/Grievance3.png");
            this.HappyEye = new Picture("Picture/Clothes/Sakura/Happy.png");
            this.Dizzy[0] = new Picture("Picture/Clothes/Sakura/Dizzy1.png");
            this.Dizzy[1] = new Picture("Picture/Clothes/Sakura/Dizzy2.png");
            this.XX = new Picture("Picture/Clothes/Sakura/XX.png");
            this.Helmet = new Picture("Picture/Clothes/Sakura/Helmet2.png");
            this.BasicHatch[1] = new Picture("Picture/Clothes/Sakura/Hatch.png");
            InterfaceButton.Button.Switch_Off = Load.loadImage("Picture/Clothes/Sakura/Switch1.png");
            //图片对象
        } else if (Main.userData.clothes.ClothesType == UserData.Clothes.ClothesType_SakuraNull) {
            Ears_Left_Relative_X = 55;
            Ears_Left_Relative_Y = -80;
            Ears_Right_Relative_X = 784 - Ears_Left_Relative_X;
            Ears_Right_Relative_Y = Ears_Left_Relative_Y;
            Foots_Left_Relative_X = 50;
            Foots_Left_Relative_Y = 785 - 100;
            Foots_Right_Relative_X = 784 - Foots_Left_Relative_X;
            Foots_Right_Relative_Y = Foots_Left_Relative_Y;
            //偏移量
            Judge_Ears1_x = 300;
            Judge_Ears1_y = -100 + 300;
            Judge_Ears1_w = 300 + 95 + 300;
            Judge_Ears1_h = Judge_Ears1_w;
            Judge_Ears2_x = 90;
            Judge_Ears2_y = Judge_Ears1_y;
            Judge_Ears2_w = Judge_Ears1_w;
            Judge_Ears2_h = Judge_Ears1_h;
            Judge_Foots1_x = 400 - 135;
            Judge_Foots1_y = 50;
            Judge_Foots1_w = 400;
            Judge_Foots1_h = Judge_Foots1_w;
            Judge_Foots2_x = 20;
            Judge_Foots2_y = Judge_Foots1_y;
            Judge_Foots2_w = Judge_Foots1_w;
            Judge_Foots2_h = Judge_Foots2_w;
            //判定区域
            this.Ears[0] = new Picture("Picture/Clothes/Null/Null.png");
            this.Ears[1] = new Picture("Picture/Clothes/Null/Null.png");
            this.Foots[0] = new Picture("Picture/Clothes/Null/Null.png");
            this.Foots[1] = new Picture("Picture/Clothes/Null/Null.png");
            this.BasicBody = new Picture("Picture/Clothes/Sakura/BasicBody.png");
            this.BasicEyes = new Picture("Picture/Clothes/Sakura/BasicEyes.png");
            this.Squint = new Picture("Picture/Clothes/Sakura/Squint.png");
            this.Doubt = new Picture("Picture/Clothes/Sakura/Doubt.png");
            this.LoveEyes1[0] = new Picture("Picture/Clothes/Sakura/LoveEyes1.png");
            this.LoveEyes1[1] = new Picture("Picture/Clothes/Sakura/LoveEyes1.png");
            this.StarEyes[0] = new Picture("Picture/Clothes/Sakura/StarEyes1.png");
            this.StarEyes[1] = new Picture("Picture/Clothes/Sakura/StarEyes2.png");
            this.Surprised = new Picture("Picture/Clothes/Sakura/Surprised.png");
            this.AngryEye = new Picture("Picture/Clothes/Sakura/Angry.png");
            this.SadEye = new Picture("Picture/Clothes/Sakura/Sad.png");
            this.Grievance_Angry = new Picture("Picture/Clothes/Sakura/Grievance1.png");
            this.Grievance_Squint = new Picture("Picture/Clothes/Sakura/Grievance2.png");
            this.Grievance_Sad = new Picture("Picture/Clothes/Sakura/Grievance3.png");
            this.HappyEye = new Picture("Picture/Clothes/Sakura/Happy.png");
            this.Dizzy[0] = new Picture("Picture/Clothes/Sakura/Dizzy1.png");
            this.Dizzy[1] = new Picture("Picture/Clothes/Sakura/Dizzy2.png");
            this.XX = new Picture("Picture/Clothes/Sakura/XX.png");
            this.Helmet = new Picture("Picture/Clothes/Sakura/Helmet1.png");
            this.BasicHatch[1] = new Picture("Picture/Clothes/Sakura/Hatch.png");
            InterfaceButton.Button.Switch_Off = Load.loadImage("Picture/Clothes/Sakura/Switch1.png");
            //图片对象
        }
    }
    //皮肤图片加载

    public void Init_AfterSetUp() {
        BasicSize = UserData.SetUp.EnumToNum(Main.userData.setUp.robotSize);
        Basic_Zoom = BasicSize / this.BasicBody.Image_B.getWidth();
        //UserData数据BasicSize

        final int Body_StartTargetDegree = 0, HighLight_StartTargetDegree = 0, EyesStartTargetDegree = 0;
        //基础数据加载

        this.BasicBody.Src = new Display.Rect(0, 0, this.BasicBody.Image_B.getWidth(), this.BasicBody.Image_B.getHeight());
        this.BasicBody.SrcCentre = new Point(this.BasicBody.Image_B.getWidth() / 2, this.BasicBody.Image_B.getHeight() / 2);
        this.BasicBody.GetNewDst_AppointSrc_In_AppointDstBufferedImage(this.BasicBody.Src,
                this.BasicBody.SrcCentre,
                new Point((int) MyFrame.GetScreenWH()[0] / 2, (int) MyFrame.GetScreenWH()[1] / 2),
                (int) BasicSize, (int) BasicSize);
        this.BasicBody.CurrentDegree = Body_StartTargetDegree;
        //Robot的基础身体图片数据

        //----------------BasicBody数据----------------
        if (Main.userData.clothes.ClothesType != UserData.Clothes.ClothesType_Basic &&
                Main.userData.clothes.ClothesType != UserData.Clothes.ClothesType_Fat &&
                Main.userData.clothes.ClothesType != UserData.Clothes.ClothesType_Null) {
            this.Helmet.Src = new Display.Rect(0, 0, this.Helmet.Image_B.getWidth(), this.Helmet.Image_B.getHeight());
            this.Helmet.SrcCentre = new Point(this.Helmet.Src.width / 2, this.Helmet.Src.height / 2);
            this.Helmet.CurrentDegree = 0;
            this.Helmet.FollowSpeed = FollowSpeed_Helmet;
        }
        //皮肤头盔
        ;
        //----------------BasicBody数据----------------

        this.HappyEye.Src = new Display.Rect(0, 0, this.HappyEye.Image_B.getWidth(), this.HappyEye.Image_B.getHeight());
        this.HappyEye.SrcCentre = new Point(this.HappyEye.Src.width / 2, this.HappyEye.Src.height / 2);
        this.HappyEye.CurrentDegree = EyesStartTargetDegree;
        //HappyEye部分的图片数据

        this.XX.Src = new Display.Rect(0, 0, this.XX.Image_B.getWidth(), this.XX.Image_B.getHeight());
        this.XX.SrcCentre = new Point(this.XX.Src.width / 2, this.XX.Src.height / 2);
        this.XX.CurrentDegree = EyesStartTargetDegree;
        //XX部分的图片数据

/*        this.BasicHatch[0].Src = new Display.Rect(0,0,this.BasicHatch[0].Image_B.getWidth(),this.BasicHatch[0].Image_B.getHeight());
        this.BasicHatch[0].SrcCentre = new Point(this.BasicHatch[0].Image_B.getWidth()/2,this.BasicHatch[0].Image_B.getHeight()/2);
        this.BasicHatch[0].CurrentDegree = Body_StartTargetDegree;*/
        //Hatch的基础身体图片数据

        this.BasicHatch[1].Src = new Display.Rect(0, 0, this.BasicHatch[1].Image_B.getWidth(), this.BasicHatch[1].Image_B.getHeight());
        this.BasicHatch[1].SrcCentre = new Point(this.BasicHatch[1].Image_B.getWidth() / 2, this.BasicHatch[1].Image_B.getHeight() / 2);
        this.BasicHatch[1].CurrentDegree = Body_StartTargetDegree;
        //Hatch的基础身体图片数据

        this.BasicEyes.Src = new Display.Rect(0, 0, this.BasicEyes.Image_B.getWidth(), this.BasicEyes.Image_B.getHeight());
        this.BasicEyes.SrcCentre = new Point(this.BasicEyes.Src.width / 2, this.BasicEyes.Src.height / 2);
        this.BasicEyes.CurrentDegree = EyesStartTargetDegree;
        //Eyes部分的图片数据

        this.Surprised.Src = new Display.Rect(0, 0, this.Surprised.Image_B.getWidth(), this.Surprised.Image_B.getHeight());
        this.Surprised.SrcCentre = new Point(this.Surprised.Src.width / 2, this.Surprised.Src.height / 2);
        this.Surprised.CurrentDegree = EyesStartTargetDegree;
        //Surprised部分的图片数据

        this.AngryEye.Src = new Display.Rect(0, 0, this.AngryEye.Image_B.getWidth(), this.AngryEye.Image_B.getHeight());
        this.AngryEye.SrcCentre = new Point(this.AngryEye.Src.width / 2, this.AngryEye.Src.height / 2);
        this.AngryEye.CurrentDegree = EyesStartTargetDegree;
        //AngryEye部分的图片数据

        this.SadEye.Src = new Display.Rect(0, 0, this.SadEye.Image_B.getWidth(), this.SadEye.Image_B.getHeight());
        this.SadEye.SrcCentre = new Point(this.SadEye.Src.width / 2, this.SadEye.Src.height / 2);
        this.SadEye.CurrentDegree = EyesStartTargetDegree;
        //SadEye部分的图片数据

        this.Grievance_Angry.Src = new Display.Rect(0, 0, this.Grievance_Angry.Image_B.getWidth(), this.Grievance_Angry.Image_B.getHeight());
        this.Grievance_Angry.SrcCentre = new Point(this.Grievance_Angry.Src.width / 2, this.Grievance_Angry.Src.height / 2);
        this.Grievance_Angry.CurrentDegree = EyesStartTargetDegree;
        //Grievance_Angry部分的图片数据

        this.Grievance_Sad.Src = new Display.Rect(0, 0, this.Grievance_Sad.Image_B.getWidth(), this.Grievance_Sad.Image_B.getHeight());
        this.Grievance_Sad.SrcCentre = new Point(this.Grievance_Sad.Src.width / 2, this.Grievance_Sad.Src.height / 2);
        this.Grievance_Sad.CurrentDegree = EyesStartTargetDegree;
        //Grievance_Sad部分的图片数据

        this.Grievance_Squint.Src = new Display.Rect(0, 0, this.Grievance_Squint.Image_B.getWidth(), this.Grievance_Squint.Image_B.getHeight());
        this.Grievance_Squint.SrcCentre = new Point(this.Grievance_Squint.Src.width / 2, this.Grievance_Squint.Src.height / 2);
        this.Grievance_Squint.CurrentDegree = EyesStartTargetDegree;
        //Grievance_Squint部分的图片数据

        this.LoveEyes1[0].Src = new Display.Rect(0, 0, this.LoveEyes1[0].Image_B.getWidth(), this.LoveEyes1[0].Image_B.getHeight());
        this.LoveEyes1[0].SrcCentre = new Point(this.LoveEyes1[0].Src.width / 2, this.LoveEyes1[0].Src.height / 2);
        this.LoveEyes1[0].CurrentDegree = EyesStartTargetDegree;

        this.LoveEyes1[1].Src = new Display.Rect(0, 0, this.LoveEyes1[1].Image_B.getWidth(), this.LoveEyes1[1].Image_B.getHeight());
        this.LoveEyes1[1].SrcCentre = new Point(this.LoveEyes1[1].Src.width / 2, this.LoveEyes1[1].Src.height / 2);
        this.LoveEyes1[1].CurrentDegree = EyesStartTargetDegree;
        //LoveEyes部分数据

        this.Dizzy[0].Src = new Display.Rect(0, 0, this.Dizzy[0].Image_B.getWidth(), this.Dizzy[0].Image_B.getHeight());
        this.Dizzy[0].SrcCentre = new Point(this.Dizzy[0].Src.width / 2, this.Dizzy[0].Src.height / 2);
        this.Dizzy[0].CurrentDegree = EyesStartTargetDegree;

        this.Dizzy[1].Src = new Display.Rect(0, 0, this.Dizzy[1].Image_B.getWidth(), this.Dizzy[1].Image_B.getHeight());
        this.Dizzy[1].SrcCentre = new Point(this.Dizzy[1].Src.width / 2, this.Dizzy[1].Src.height / 2);
        this.Dizzy[1].CurrentDegree = EyesStartTargetDegree;
        //Dizzy部分数据

        this.StarEyes[0].Src = new Display.Rect(0, 0, this.StarEyes[0].Image_B.getWidth(), this.StarEyes[0].Image_B.getHeight());
        this.StarEyes[0].SrcCentre = new Point(this.StarEyes[0].Src.width / 2, this.StarEyes[0].Src.height / 2);
        this.StarEyes[0].CurrentDegree = EyesStartTargetDegree;

        this.StarEyes[1].Src = new Display.Rect(0, 0, this.StarEyes[1].Image_B.getWidth(), this.StarEyes[1].Image_B.getHeight());
        this.StarEyes[1].SrcCentre = new Point(this.StarEyes[1].Src.width / 2, this.StarEyes[1].Src.height / 2);
        this.StarEyes[1].CurrentDegree = EyesStartTargetDegree;
        //StarEyes部分数据

        this.Squint.Src = new Display.Rect(0, 0, this.Squint.Image_B.getWidth(), this.Squint.Image_B.getHeight());
        this.Squint.SrcCentre = new Point(this.Squint.Src.width / 2, this.Squint.Src.height / 2);
        this.Squint.CurrentDegree = EyesStartTargetDegree;
        //Squint部分的图片数据

        this.Doubt.Src = new Display.Rect(0, 0, this.Doubt.Image_B.getWidth(), this.Doubt.Image_B.getHeight());
        this.Doubt.SrcCentre = new Point(this.Doubt.Src.width / 2, this.Doubt.Src.height / 2);
        this.Doubt.CurrentDegree = EyesStartTargetDegree;
        //Doubt部分的图片数据

        this.HighLight.Src = new Display.Rect(0, 0, this.HighLight.Image_B.getWidth(), this.HighLight.Image_B.getHeight());
        this.HighLight.SrcCentre = new Point(this.HighLight.Src.width / 2, this.HighLight.Src.height / 2);
        this.HighLight.CurrentDegree = HighLight_StartTargetDegree;
        //高光图片数据

        this.Ears[0].Src = new Display.Rect(0, 0, this.Ears[0].Image_B.getWidth(), this.Ears[0].Image_B.getHeight());
        this.Ears[1].Src = new Display.Rect(0, 0, this.Ears[1].Image_B.getWidth(), this.Ears[1].Image_B.getHeight());
        this.Ears[0].SrcCentre = new Point(this.Ears[0].Src.width / 2, this.Ears[0].Src.height);
        this.Ears[1].SrcCentre = new Point(this.Ears[1].Src.width / 2, this.Ears[1].Src.height);
        //Ears数据

        this.Foots[0].Src = new Display.Rect(0, 0, this.Foots[0].Image_B.getWidth(), this.Foots[0].Image_B.getHeight());
        this.Foots[1].Src = new Display.Rect(0, 0, this.Foots[1].Image_B.getWidth(), this.Foots[1].Image_B.getHeight());
        this.Foots[0].SrcCentre = new Point(this.Foots[0].Src.width / 2, 0);
        this.Foots[1].SrcCentre = new Point(this.Foots[1].Src.width / 2, 0);

        //Foots数据
        ;
        //----------------Src和SrcCentre数据----------------
        this.Init_Reset_PictureDst();
        //----------------Dst数据----------------
    }
    //数据加载：设置后刷新

    public void Init_LoadOnce() {
        this.Ears[0].CurrentDegree = -Ears_StartCurrentDegree;
        this.Ears[1].CurrentDegree = Ears_StartCurrentDegree;
        this.Ears[0].TargetDegree = -Ears_StartTargetDegree;
        this.Ears[1].TargetDegree = Ears_StartTargetDegree;
        //Ears数据
        this.Foots[0].CurrentDegree = Foots_StartCurrentDegree;
        this.Foots[1].CurrentDegree = -Foots_StartCurrentDegree;
        this.Foots[0].TargetDegree = Foots_StartTargetDegree;
        this.Foots[1].TargetDegree = -Foots_StartTargetDegree;
        //Foots数据
        this.Ears[0].FollowSpeed = FollowSpeed_Final;
        this.Ears[1].FollowSpeed = FollowSpeed_Final;
        this.Foots[0].FollowSpeed = FollowSpeed_Final;
        this.Foots[1].FollowSpeed = FollowSpeed_Final;
        //Ears和Foots的跟随速度
    }
    //数据加载：一次加载

    public void Init_AfterSetUp2(int x, int y) {
        BasicSize = UserData.SetUp.EnumToNum(Main.userData.setUp.robotSize);
        Basic_Zoom = BasicSize / 785.0;
        //UserData数据BasicSize

        this.BasicBody.Dst.x = x;
        this.BasicBody.Dst.y = y;
        //Robot的基础身体图片数据

        this.Init_Reset_PictureDst();
        //----------------Dst数据----------------
        this.Ears[0].CurrentDegree = this.Ears[0].TargetDegree;
        this.Ears[1].CurrentDegree = this.Ears[1].TargetDegree;
        //Ears数据
        this.Foots[0].CurrentDegree = this.Foots[0].TargetDegree;
        this.Foots[1].CurrentDegree = this.Foots[1].TargetDegree;
        //Foots数据
    }
    //数据加载：设置后刷新

    public void Init_Reset_PictureDst() {
        Basic_Zoom = BasicSize / 785.0;
        //数据BasicSize

        final int HighLight_Relative_X = 250, HighLight_Relative_Y = 165;

        this.HappyEye.Dst = Get_PictureRelativeLocation_OfBasicBody(this.HappyEye.Src,
                this.HappyEye.SrcCentre,
                new Point(this.BasicBody.Dst.width / 2, (int) (EyesHappy_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.HappyEye.Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.HappyEye.Src.height * BasicSize / this.BasicBody.Src.height));
        //HappyEye部分的图片数据

        if (Main.userData.clothes.ClothesType == UserData.Clothes.ClothesType_Sakura) {
            this.Helmet.Dst = Get_PictureRelativeLocation_OfBasicBody(this.Helmet.Src,
                    this.Helmet.SrcCentre,
                    new Point(this.BasicBody.Dst.width / 2, (int) (Helmet_Sakura_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                    (int) (this.Helmet.Src.width * BasicSize / this.BasicBody.Src.width),
                    (int) (this.Helmet.Src.height * BasicSize / this.BasicBody.Src.height));
        } else if (Main.userData.clothes.ClothesType == UserData.Clothes.ClothesType_SakuraNull) {
            this.Helmet.Dst = Get_PictureRelativeLocation_OfBasicBody(this.Helmet.Src,
                    this.Helmet.SrcCentre,
                    new Point(this.BasicBody.Dst.width / 2, (int) (Helmet_SakuraNull_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                    (int) (this.Helmet.Src.width * BasicSize / this.BasicBody.Src.width),
                    (int) (this.Helmet.Src.height * BasicSize / this.BasicBody.Src.height));
        }
        //Helmet部分的图片数据

        //this.BasicHatch[0].Dst = this.BasicBody.Dst;
        //Hatch的基础身体图片数据

        this.BasicHatch[1].GetNewDst_AppointSrc_In_AppointDstBufferedImage(this.BasicHatch[1].Src,
                this.BasicHatch[1].SrcCentre,
                new Point((int) (this.BasicBody.Dst.x + this.BasicBody.Dst.width / 2.0),
                        (int) (this.BasicBody.Dst.y + Hatch_Relative_Y * Basic_Zoom)),
                (int) (this.BasicHatch[1].Src.width * Basic_Zoom), (int) (this.BasicHatch[1].Src.height * Basic_Zoom));
        //Hatch的基础身体图片数据

        this.BasicEyes.Dst = Get_PictureRelativeLocation_OfBasicBody(this.BasicEyes.Src,
                this.BasicEyes.SrcCentre,
                new Point(this.BasicBody.Dst.width / 2, (int) (Eyes_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.BasicEyes.Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.BasicEyes.Src.height * BasicSize / this.BasicBody.Src.height));
        //Eyes部分的图片数据

        this.Surprised.Dst = Get_PictureRelativeLocation_OfBasicBody(this.Surprised.Src,
                this.Surprised.SrcCentre,
                new Point(this.BasicBody.Dst.width / 2, (int) (Eyes_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.Surprised.Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.Surprised.Src.height * BasicSize / this.BasicBody.Src.height));
        //Surprised部分的图片数据

        this.XX.Dst = Get_PictureRelativeLocation_OfBasicBody(this.XX.Src,
                this.XX.SrcCentre,
                new Point(this.BasicBody.Dst.width / 2, (int) ((EyesHappy_Relative_Y - 50) * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.XX.Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.XX.Src.height * BasicSize / this.BasicBody.Src.height));
        //XX部分的图片数据

        this.AngryEye.Dst = Get_PictureRelativeLocation_OfBasicBody(this.AngryEye.Src,
                this.AngryEye.SrcCentre,
                new Point(this.BasicBody.Dst.width / 2, (int) (Eyes_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.AngryEye.Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.AngryEye.Src.height * BasicSize / this.BasicBody.Src.height));
        //AngryEye部分的图片数据

        this.SadEye.Dst = Get_PictureRelativeLocation_OfBasicBody(this.SadEye.Src,
                this.SadEye.SrcCentre,
                new Point(this.BasicBody.Dst.width / 2, (int) (Eyes_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.SadEye.Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.SadEye.Src.height * BasicSize / this.BasicBody.Src.height));
        //SadEye部分的图片数据

        this.Grievance_Angry.Dst = Get_PictureRelativeLocation_OfBasicBody(this.Grievance_Angry.Src,
                this.Grievance_Angry.SrcCentre,
                new Point(this.BasicBody.Dst.width / 2, (int) (Eyes_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.Grievance_Angry.Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.Grievance_Angry.Src.height * BasicSize / this.BasicBody.Src.height));
        //Grievance_Angry部分的图片数据

        this.Grievance_Sad.Dst = Get_PictureRelativeLocation_OfBasicBody(this.Grievance_Sad.Src,
                this.Grievance_Sad.SrcCentre,
                new Point(this.BasicBody.Dst.width / 2, (int) (Eyes_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.Grievance_Sad.Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.Grievance_Sad.Src.height * BasicSize / this.BasicBody.Src.height));
        //Grievance_Sad部分的图片数据

        this.Grievance_Squint.Dst = Get_PictureRelativeLocation_OfBasicBody(this.Grievance_Squint.Src,
                this.Grievance_Squint.SrcCentre,
                new Point(this.BasicBody.Dst.width / 2, (int) (Eyes_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.Grievance_Squint.Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.Grievance_Squint.Src.height * BasicSize / this.BasicBody.Src.height));
        //Grievance_Squint部分的图片数据

        this.LoveEyes1[0].Dst = Get_PictureRelativeLocation_OfBasicBody(this.LoveEyes1[0].Src,
                this.LoveEyes1[0].SrcCentre,
                new Point((int) (Eyes_Relative_X_left * BasicSize / this.BasicBody.Image_B.getWidth()),
                        (int) (Eyes_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.LoveEyes1[0].Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.LoveEyes1[0].Src.height * BasicSize / this.BasicBody.Src.height));

        this.LoveEyes1[1].Dst = Get_PictureRelativeLocation_OfBasicBody(this.LoveEyes1[1].Src,
                this.LoveEyes1[1].SrcCentre,
                new Point((int) (Eyes_Relative_X_right * BasicSize / this.BasicBody.Image_B.getWidth()),
                        (int) (Eyes_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.LoveEyes1[1].Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.LoveEyes1[1].Src.height * BasicSize / this.BasicBody.Src.height));
        ;
        //LoveEyes部分数据

        this.Dizzy[0].Dst = Get_PictureRelativeLocation_OfBasicBody(this.Dizzy[0].Src,
                this.Dizzy[0].SrcCentre,
                new Point((int) (Eyes_Relative_X_left * BasicSize / this.BasicBody.Image_B.getWidth()),
                        (int) (Eyes_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.Dizzy[0].Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.Dizzy[0].Src.height * BasicSize / this.BasicBody.Src.height));

        this.Dizzy[1].Dst = Get_PictureRelativeLocation_OfBasicBody(this.Dizzy[1].Src,
                this.Dizzy[1].SrcCentre,
                new Point((int) (Eyes_Relative_X_right * BasicSize / this.BasicBody.Image_B.getWidth()),
                        (int) (Eyes_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.Dizzy[1].Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.Dizzy[1].Src.height * BasicSize / this.BasicBody.Src.height));
        //Dizzy部分数据

        this.StarEyes[0].Dst = Get_PictureRelativeLocation_OfBasicBody(this.StarEyes[0].Src,
                this.StarEyes[0].SrcCentre,
                new Point((int) (Eyes_Relative_X_left * BasicSize / this.BasicBody.Image_B.getWidth()),
                        (int) (Eyes_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.StarEyes[0].Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.StarEyes[0].Src.height * BasicSize / this.BasicBody.Src.height));

        this.StarEyes[1].Dst = Get_PictureRelativeLocation_OfBasicBody(this.StarEyes[1].Src,
                this.StarEyes[1].SrcCentre,
                new Point((int) (Eyes_Relative_X_right * BasicSize / this.BasicBody.Image_B.getWidth()),
                        (int) (Eyes_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.StarEyes[1].Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.StarEyes[1].Src.height * BasicSize / this.BasicBody.Src.height));
        //StarEyes部分数据

        this.Squint.Dst = Get_PictureRelativeLocation_OfBasicBody(this.Squint.Src,
                this.Squint.SrcCentre,
                new Point(this.BasicBody.Dst.width / 2, (int) (Eyes_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.Squint.Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.Squint.Src.height * BasicSize / this.BasicBody.Src.height));
        //Squint部分的图片数据

        this.Doubt.Dst = Get_PictureRelativeLocation_OfBasicBody(this.Doubt.Src,
                this.Doubt.SrcCentre,
                new Point(this.BasicBody.Dst.width / 2, (int) (Eyes_Relative_Y * Basic_Zoom)),
                (int) (this.Doubt.Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.Doubt.Src.height * BasicSize / this.BasicBody.Src.height));
        //Doubt部分的图片数据

        this.HighLight.Dst = Get_PictureRelativeLocation_OfBasicBody(this.HighLight.Src,
                this.HighLight.SrcCentre,
                new Point((int) (HighLight_Relative_X * BasicSize / this.BasicBody.Image_B.getWidth()),
                        (int) (HighLight_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.HighLight.Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.HighLight.Src.height * BasicSize / this.BasicBody.Src.height));
        //高光图片数据

        this.Ears[0].Dst = Get_PictureRelativeLocation_OfBasicBody(this.Ears[0].Src,
                this.Ears[0].SrcCentre,
                new Point((int) (Ears_Left_Relative_X * BasicSize / this.BasicBody.Image_B.getWidth()),
                        (int) (Ears_Left_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.Ears[0].Src.width * Ears_Zoom * BasicSize / this.BasicBody.Src.width),
                (int) (this.Ears[0].Src.height * Ears_Zoom * BasicSize / this.BasicBody.Src.height));
        this.Ears[1].Dst = Get_PictureRelativeLocation_OfBasicBody(this.Ears[1].Src,
                this.Ears[1].SrcCentre,
                new Point((int) (Ears_Right_Relative_X * BasicSize / this.BasicBody.Image_B.getWidth()),
                        (int) (Ears_Right_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.Ears[1].Src.width * Ears_Zoom * BasicSize / this.BasicBody.Src.width),
                (int) (this.Ears[1].Src.height * Ears_Zoom * BasicSize / this.BasicBody.Src.height));
        //Ears数据

        this.Foots[0].Dst = Get_PictureRelativeLocation_OfBasicBody(this.Foots[0].Src,
                this.Foots[0].SrcCentre,
                new Point((int) (Foots_Left_Relative_X * BasicSize / this.BasicBody.Image_B.getWidth()),
                        (int) (Foots_Left_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.Foots[0].Src.width * Foots_Zoom * BasicSize / this.BasicBody.Src.width),
                (int) (this.Foots[0].Src.height * Foots_Zoom * BasicSize / this.BasicBody.Src.height));
        this.Foots[1].Dst = Get_PictureRelativeLocation_OfBasicBody(this.Foots[1].Src,
                this.Foots[1].SrcCentre,
                new Point((int) (Foots_Right_Relative_X * BasicSize / this.BasicBody.Image_B.getWidth()),
                        (int) (Foots_Right_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.Foots[1].Src.width * Foots_Zoom * BasicSize / this.BasicBody.Src.width),
                (int) (this.Foots[1].Src.height * Foots_Zoom * BasicSize / this.BasicBody.Src.height));
        //Foots数据
    }
    //Dst的加载
    ;
    //--------------------------------初始化--------------------------------------

    public void DataProgress_ForFishRoe() {

        this.Refresh_Display();
        //绘制锁刷新

        int[] Gap;
        Gap = Follow(this.Ears[0],
                new Point(this.Ears[0].Src.width / 2, this.Ears[0].Src.height),
                new Point((Ears_Left_Relative_X), (Ears_Left_Relative_Y)), Ears_Zoom);
        Angle_BodyMoved(this.Ears[0], Direction.Left_Up, -Ears_StartTargetDegree, Gap[0], Gap[1]);

        Gap = Follow(this.Ears[1],
                new Point(this.Ears[1].Src.width / 2, this.Ears[1].Src.height),
                new Point((Ears_Right_Relative_X), (Ears_Right_Relative_Y)), Ears_Zoom);
        Angle_BodyMoved(this.Ears[1], Direction.Right_Up, Ears_StartTargetDegree, Gap[0], Gap[1]);

        Gap = Follow(this.Foots[0],
                new Point(this.Foots[0].Src.width / 2, 0),
                new Point((Foots_Left_Relative_X), (Foots_Left_Relative_Y)), Foots_Zoom);
        Angle_BodyMoved(this.Foots[0], Direction.Left_Down, Foots_StartTargetDegree, Gap[0], Gap[1]);

        Gap = Follow(this.Foots[1],
                new Point(this.Foots[1].Src.width / 2, 0),
                new Point((Foots_Right_Relative_X), (Foots_Right_Relative_Y)), Foots_Zoom);
        Angle_BodyMoved(this.Foots[1], Direction.Right_Down, -Foots_StartTargetDegree, Gap[0], Gap[1]);

        if (Main.userData.clothes.ClothesType == UserData.Clothes.ClothesType_Sakura) {
            Gap = Follow(this.Helmet,
                    new Point(this.Helmet.Src.width / 2, this.Helmet.Src.height / 2),
                    new Point(this.BasicBody.Image_B.getWidth() / 2, (Helmet_Sakura_Relative_Y)), 1);
        } else if (Main.userData.clothes.ClothesType == UserData.Clothes.ClothesType_SakuraNull) {
            Gap = Follow(this.Helmet,
                    new Point(this.Helmet.Src.width / 2, this.Helmet.Src.height / 2),
                    new Point(this.BasicBody.Image_B.getWidth() / 2, (Helmet_SakuraNull_Relative_Y)), 1);
        }

        //跟随+//移动角度变化

        AngleGradient(this.Ears[0]);
        AngleGradient(this.Ears[1]);
        AngleGradient(this.Foots[0]);
        AngleGradient(this.Foots[1]);
        //添加角度跟随
        ;
        //--------------------------------角度跟随--------------------------------------


        if (this.Judge_BasicBodyEventRectChange) {
            this.BasicBody.Set_EventJudgeRect((int) (Judge_BasicBody_w * BasicSize / this.BasicBody.Image_B.getWidth()),
                    (int) (Judge_BasicBody_h * BasicSize / this.BasicBody.Image_B.getHeight()),
                    (int) (Judge_BasicBody_x * BasicSize / this.BasicBody.Image_B.getWidth()),
                    (int) (Judge_BasicBody_y * BasicSize / this.BasicBody.Image_B.getHeight()));
        } else {
            this.BasicBody.Set_EventJudgeRect(this.BasicBody.Dst.width, this.BasicBody.Dst.height, 0, 0);
        }
        this.BasicEyes.Set_EventJudgeRect((int) (Judge_Eye_w * BasicSize / this.BasicBody.Image_B.getWidth()),
                (int) (Judge_Eye_h * BasicSize / this.BasicBody.Image_B.getHeight()),
                (int) (Judge_Eye_x * BasicSize / this.BasicBody.Image_B.getWidth()),
                (int) (Judge_Eye_y * BasicSize / this.BasicBody.Image_B.getHeight()));
        this.SadEye.Set_EventJudgeRect((int) (Judge_Eye_w * BasicSize / this.BasicBody.Image_B.getWidth()),
                (int) (Judge_Eye_h * BasicSize / this.BasicBody.Image_B.getHeight()),
                (int) (Judge_Eye_x * BasicSize / this.BasicBody.Image_B.getWidth()),
                (int) (Judge_Eye_y * BasicSize / this.BasicBody.Image_B.getHeight()));
        this.Grievance_Sad.Set_EventJudgeRect((int) (Judge_Eye_w * BasicSize / this.BasicBody.Image_B.getWidth()),
                (int) (Judge_Eye_h * BasicSize / this.BasicBody.Image_B.getHeight()),
                (int) (Judge_Eye_x * BasicSize / this.BasicBody.Image_B.getWidth()),
                (int) (Judge_Eye_y * BasicSize / this.BasicBody.Image_B.getHeight()));
        this.Ears[0].Set_EventJudgeRect((int) (Judge_Ears1_w * Ears_Zoom * BasicSize / this.BasicBody.Image_B.getWidth()),
                (int) (Judge_Ears1_h * Ears_Zoom * BasicSize / this.BasicBody.Image_B.getHeight()),
                (int) (Judge_Ears1_x * Ears_Zoom * BasicSize / this.BasicBody.Image_B.getWidth()),
                (int) (Judge_Ears1_y * Ears_Zoom * BasicSize / this.BasicBody.Image_B.getHeight()));
        this.Ears[1].Set_EventJudgeRect((int) (Judge_Ears2_w * Ears_Zoom * BasicSize / this.BasicBody.Image_B.getWidth()),
                (int) (Judge_Ears2_h * Ears_Zoom * BasicSize / this.BasicBody.Image_B.getHeight()),
                (int) (Judge_Ears2_x * Ears_Zoom * BasicSize / this.BasicBody.Image_B.getWidth()),
                (int) (Judge_Ears2_y * Ears_Zoom * BasicSize / this.BasicBody.Image_B.getHeight()));
        this.Foots[0].Set_EventJudgeRect((int) (Judge_Foots1_w * Foots_Zoom * BasicSize / this.BasicBody.Image_B.getWidth()),
                (int) (Judge_Foots1_h * Foots_Zoom * BasicSize / this.BasicBody.Image_B.getHeight()),
                (int) (Judge_Foots1_x * Foots_Zoom * BasicSize / this.BasicBody.Image_B.getWidth()),
                (int) (Judge_Foots1_y * Foots_Zoom * BasicSize / this.BasicBody.Image_B.getHeight()));
        this.Foots[1].Set_EventJudgeRect((int) (Judge_Foots2_w * Foots_Zoom * BasicSize / this.BasicBody.Image_B.getWidth()),
                (int) (Judge_Foots2_h * Foots_Zoom * BasicSize / this.BasicBody.Image_B.getHeight()),
                (int) (Judge_Foots2_x * Foots_Zoom * BasicSize / this.BasicBody.Image_B.getWidth()),
                (int) (Judge_Foots2_y * Foots_Zoom * BasicSize / this.BasicBody.Image_B.getHeight()));
        //FishRoe
        InterfaceButton.Global_SetUpSwitch.Set_EventJudgeRect(InterfaceButton.Global_SetUpSwitch.Dst.width + 10,
                InterfaceButton.Global_SetUpSwitch.Dst.height + 10, 5, 5);
        InterfaceButton.Global_SetUpButton.Set_EventJudgeRect(InterfaceButton.Global_SetUpButton.Dst.width + 10,
                InterfaceButton.Global_SetUpButton.Dst.height + 10, 5, 5);
        InterfaceButton.Global_BatteryButton.Set_EventJudgeRect(InterfaceButton.Global_BatteryButton.Dst.width + 10,
                InterfaceButton.Global_BatteryButton.Dst.height + 10, 5, 5);
        InterfaceButton.Global_LoveButton.Set_EventJudgeRect(InterfaceButton.Global_LoveButton.Dst.width + 10,
                InterfaceButton.Global_LoveButton.Dst.height + 10, 5, 5);
        InterfaceButton.Global_ExitButton.Set_EventJudgeRect(InterfaceButton.Global_ExitButton.Dst.width + 10,
                InterfaceButton.Global_ExitButton.Dst.height + 10, 5, 5);
        InterfaceButton.Global_ClothesButton.Set_EventJudgeRect(InterfaceButton.Global_ClothesButton.Dst.width + 10,
                InterfaceButton.Global_ClothesButton.Dst.height + 10, 5, 5);
        InterfaceButton.Global_SetUp_Interface.EventJudgeRect = InterfaceButton.Global_SetUp_Interface.Dst;
        InterfaceButton.Global_MoodSystem_Interface.EventJudgeRect = InterfaceButton.Global_MoodSystem_Interface.Dst;
        InterfaceButton.Global_FoodSystem_Interface.EventJudgeRect = InterfaceButton.Global_FoodSystem_Interface.Dst;
        InterfaceButton.Global_Exit_Interface.EventJudgeRect = InterfaceButton.Global_Exit_Interface.Dst;
        InterfaceButton.Global_Clothes_Interface.EventJudgeRect = InterfaceButton.Global_Clothes_Interface.Dst;
        if (InterfaceButton.Global_SetUp_Interface.List_Button != null && !InterfaceButton.Global_SetUp_Interface.List_Button.isEmpty()) {
            for (InterfaceButton.Button button : InterfaceButton.Global_SetUp_Interface.List_Button) {
                button.EventJudgeRect = button.Dst;
            }
        }
        if (InterfaceButton.Global_FoodSystem_Interface.List_Button != null && !InterfaceButton.Global_FoodSystem_Interface.List_Button.isEmpty()) {
            for (InterfaceButton.Button button : InterfaceButton.Global_FoodSystem_Interface.List_Button) {
                button.EventJudgeRect = button.Dst;
            }
        }
        if (InterfaceButton.Global_Exit_Interface.List_Button != null && !InterfaceButton.Global_Exit_Interface.List_Button.isEmpty()) {
            for (InterfaceButton.Button button : InterfaceButton.Global_Exit_Interface.List_Button) {
                button.EventJudgeRect = button.Dst;
            }
        }
        if (InterfaceButton.Global_Clothes_Interface.List_Button != null && !InterfaceButton.Global_Clothes_Interface.List_Button.isEmpty()) {
            for (InterfaceButton.Button button : InterfaceButton.Global_Clothes_Interface.List_Button) {
                button.EventJudgeRect = button.Dst;
            }
        }
        //Button、Interface
        ;
        //非Dragged
        for (int i = 0; i < InterfaceButton.Food_Picture_Num; i++) {
            int FoodEventWidth = 1000;
            if (InterfaceButton.Global_Food[i].Judge_EventRectChange) {
                InterfaceButton.Global_Food[i].picture.Set_EventJudgeRect(InterfaceButton.Global_Food[i].picture.Dst.width + FoodEventWidth,
                        InterfaceButton.Global_Food[i].picture.Dst.height + FoodEventWidth, FoodEventWidth / 2, FoodEventWidth / 2);
            }
            //锁
            else {
                InterfaceButton.Global_Food[i].picture.Set_EventJudgeRect(InterfaceButton.Global_Food[i].picture.Dst.width,
                        InterfaceButton.Global_Food[i].picture.Dst.height, 0, 0);
            }
            //不锁
        }
        //Dragged
        ;
        //EventJudgeRect赋值
        ;
        //--------------------------------区域数据--------------------------------------

        Refresh_TextContext();
        //刷新文本
        ;
        //--------------------------------Text刷新数据--------------------------------------
        ;
        //Global_FishRoeAction_Tool.Timer_Main();
        ;
        //--------------------------------根据随机时间申请时间--------------------------------------

        Global_FishRoeAction_Tool.CheckApplication();//object->Apply;object->Running
        Global_FishRoeAction_Tool.Action_ObjectRunningCheck();//FPS->Running
        if (ActionTool.Global_Lock_FoodAppeared && !ActionTool.Global_Lock_FoodEaten) {
            Global_FishRoeAction_Tool.CheckApplication_Common(Global_FishRoeAction_Tool.List_Special_FoodAppeared);//operation->Running
        } else if (ActionTool.Global_Lock_FoodEaten) {
            Global_FishRoeAction_Tool.CheckApplication_Common(Global_FishRoeAction_Tool.List_Special_FoodEaten);//operation->Running
        }

        //--------------------------------检查绘制申请+数据处理--------------------------------------

        Global_FishRoeAction_Tool.Refresh_AllActionObject();
        //绘制申请刷新
        ;
        //--------------------------------绘制状态刷新--------------------------------------
    }

    //行为函数+数据变换
    public void Refresh_TextContext() {
        UserData.Mood.Refresh_MoodBar_Text(InterfaceButton.Global_MoodBar[1], InterfaceButton.Global_MoodBar[0],
                InterfaceButton.Global_MoodSystem_Interface.List_Texts.getFirst());
        for (int i = 0; i < InterfaceButton.Food_Picture_Num; i++) {
            InterfaceButton.Global_Food_SelectButton[i].text.Content = "剩余:" + InterfaceButton.Global_Food[i].FoodNumber;
        }
    }

    //刷新文本内容
    public void Refresh_Display() {
        BasicBody.eventLock.setOnce_Display(false);
        HighLight.eventLock.setOnce_Display(false);
        BasicEyes.eventLock.setOnce_Display(false);
        Ears[0].eventLock.setOnce_Display(false);
        Ears[1].eventLock.setOnce_Display(false);
        Foots[0].eventLock.setOnce_Display(false);
        Foots[1].eventLock.setOnce_Display(false);
    }
    //数据刷新
    ;
    //--------------------------------专属：FishRoe的Action方法--------------------------------------

    public void Display_Main(Graphics2D g) {

        this.DataProgress_ForFishRoe();

        //--------------------------------数据处理--------------------------------------

        this.Display_FixedPainting(g);

        //--------------------------------图片绘制：固定图像--------------------------------------

        this.Display_VaryingPainting_Action(g);
        //行为动作
        this.Display_VaryingPaint_FixedParts(g);
        //Robot固定零件

        //--------------------------------图片绘制：变换图像--------------------------------------

        Display_Interface_Button(g);

        //--------------------------------图片绘制：界面绘制--------------------------------------

        this.Display_Experiment(g);

        //--------------------------------测试--------------------------------------
    }
    //主绘制函数

    public void Display_FixedPainting(Graphics2D g) {
        this.BasicBody.Draw(g, Main.MainFrame, Image.SCALE_SMOOTH);
        if (Main.userData.clothes.ClothesType != UserData.Clothes.ClothesType_Basic &&
                Main.userData.clothes.ClothesType != UserData.Clothes.ClothesType_Fat &&
                Main.userData.clothes.ClothesType != UserData.Clothes.ClothesType_Null) {
            this.Helmet.Draw(g, Main.MainFrame, Image.SCALE_SMOOTH);
        }
    }
    //固定绘画

    public void Display_VaryingPainting_Action(Graphics2D g) {
        Global_FishRoeAction_Tool.Display_ObjectRunning(g, Main.MainFrame);
    }
    //变化绘图：行为动作

    public void Display_VaryingPaint_FixedParts(Graphics2D g) {
        if (!this.BasicEyes.eventLock.isOnce_Display() && !this.BasicEyes.eventLock.isDisplay_Conventional() &&
                !ActionTool.Global_Lock_FoodAppeared && !ActionTool.Global_Lock_FoodEaten) {//没有绘制过+允许常规绘制(未上锁：false)
            if (!ActionTool.Global_Lock_FaceEmoji) {
                if (Main.userData.moodSystem.CurrentMood == UserData.Mood.CurrentMood_Sad) {
                    this.SadEye.Draw(g, Main.MainFrame, Image.SCALE_SMOOTH);
                    this.SadEye.eventLock.setOnce_Display(true);
                } else if (Main.userData.moodSystem.CurrentMood == UserData.Mood.CurrentMood_Range) {
                    this.Grievance_Sad.Draw(g, Main.MainFrame, Image.SCALE_SMOOTH);
                    this.Grievance_Sad.eventLock.setOnce_Display(true);
                } else {
                    this.BasicEyes.Draw(g, Main.MainFrame, Image.SCALE_SMOOTH);
                    this.BasicEyes.eventLock.setOnce_Display(true);
                }
            }
        }

        this.HighLight.Draw(g, Main.MainFrame, Image.SCALE_SMOOTH);//高光本来是固定绘画，但是顺序原因必须放在此处

        if (!this.Ears[0].eventLock.isOnce_Display() && !this.Ears[0].eventLock.isDisplay_Conventional()) {//没有绘制过+允许常规绘制(未上锁：false)
            this.Ears[0].Draw_SinglePictureRotate(g, Main.MainFrame, Image.SCALE_SMOOTH, this.Ears[0].CurrentDegree);
        }
        if (!this.Ears[1].eventLock.isOnce_Display() && !this.Ears[1].eventLock.isDisplay_Conventional()) {//没有绘制过+允许常规绘制(未上锁：false)
            this.Ears[1].Draw_SinglePictureRotate(g, Main.MainFrame, Image.SCALE_SMOOTH, this.Ears[1].CurrentDegree);
        }
        if (!this.Foots[0].eventLock.isOnce_Display() && !this.Foots[0].eventLock.isDisplay_Conventional()) {//没有绘制过+允许常规绘制(未上锁：false)
            this.Foots[0].Draw_SinglePictureRotate(g, Main.MainFrame, Image.SCALE_SMOOTH, this.Foots[0].CurrentDegree);
        }
        if (!this.Foots[1].eventLock.isOnce_Display() && !this.Foots[1].eventLock.isDisplay_Conventional()) {//没有绘制过+允许常规绘制(未上锁：false)
            this.Foots[1].Draw_SinglePictureRotate(g, Main.MainFrame, Image.SCALE_SMOOTH, this.Foots[1].CurrentDegree);
        }

        if (Global_EatenHappy_Operation[0].FoodFollow) {
            this.BasicHatch[1].Draw(g, Main.MainFrame, Image.SCALE_SMOOTH);
        }
    }
    //变化绘图：Robot固定零件

    public void Display_Interface_Button(Graphics2D g) {

        if (!Global_EatenHappy_Operation[0].FoodFollow) {
            InterfaceButton.Display_Button(InterfaceButton.Global_SetUpSwitch,
                    g, Main.MainFrame);
        }
        InterfaceButton.Display_Button(InterfaceButton.Global_SetUpButton,
                g, Main.MainFrame);
        InterfaceButton.Display_Button(InterfaceButton.Global_BatteryButton,
                g, Main.MainFrame);
        InterfaceButton.Display_Button(InterfaceButton.Global_LoveButton,
                g, Main.MainFrame);
        InterfaceButton.Display_Button(InterfaceButton.Global_ExitButton,
                g, Main.MainFrame);
        InterfaceButton.Display_Button(InterfaceButton.Global_ClothesButton,
                g, Main.MainFrame);
        //Button_1

        InterfaceButton.Display_PictureObject(g);
        //食物绘制
        if (Global_EatenHappy_Operation[0].FoodFollow) {
            this.BasicHatch[1].Draw(g, Main.MainFrame, Image.SCALE_SMOOTH);
        }
        //食物容器

        InterfaceButton.Display_Interface(InterfaceButton.Global_MoodSystem_Interface,
                g, Main.MainFrame);
        InterfaceButton.Display_Texts(InterfaceButton.Global_MoodSystem_Interface, g);
        //Mood
        InterfaceButton.Display_Interface(InterfaceButton.Global_FoodSystem_Interface,
                g, Main.MainFrame);
        InterfaceButton.Display_Texts(InterfaceButton.Global_FoodSystem_Interface, g);
        InterfaceButton.Display_Interface(InterfaceButton.Global_Exit_Interface,
                g, Main.MainFrame);
        InterfaceButton.Display_Texts(InterfaceButton.Global_Exit_Interface, g);
        InterfaceButton.Display_Interface(InterfaceButton.Global_Clothes_Interface,
                g, Main.MainFrame);
        InterfaceButton.Display_Texts(InterfaceButton.Global_Clothes_Interface, g);
        //Food
        InterfaceButton.Display_Interface(InterfaceButton.Global_SetUp_Interface,
                g, Main.MainFrame);
        InterfaceButton.Display_Texts(InterfaceButton.Global_SetUp_Interface, g);
        //SetUp
        ;
        //Interface
    }
    //界面，按钮绘制

    public void Display_Experiment(Graphics2D g) {
//        this.BasicBody.EventJudgeRect.DisplayRect(g);
//        this.BasicEyes.EventJudgeRect.DisplayRect(g);
//        this.Ears[0].EventJudgeRect.DisplayRect(g);
//        this.Foots[0].EventJudgeRect.DisplayRect(g);
//        this.Ears[1].EventJudgeRect.DisplayRect(g);
//        this.Foots[1].EventJudgeRect.DisplayRect(g);
//        Interface_Button.Global_SetUpSwitch.EventJudgeRect.DisplayRect(g);
//        Interface_Button.Global_SetUpButton.EventJudgeRect.DisplayRect(g);
//        Interface_Button.Global_SetUp_Interface.EventJudgeRect.DisplayRect(g);
//        Interface_Button.Global_Food[0].picture.EventJudgeRect.DisplayRect(g);
//        Interface_Button.Global_ExitButton.Dst.DisplayRect(g);
    }
    //测试图像

    public Display.Rect Get_PictureRelativeLocation_OfBasicBody(Display.Rect Src, Point Src_Point, Point Dst_Point,
                                                                int Dst_Width, int Dst_Height) {//其中Dst_Point是希望在主控件上的相对位置
        Display.Rect Dst = new Display.Rect(0, 0, 0, 0);
        if (Src_Point.x >= Src.x && Src_Point.x <= Src.x + Src.width &&
                Src_Point.y >= Src.y && Src_Point.y <= Src.y + Src.height) {
            int Distance_x = Dst_Width * Src_Point.x / Src.width;
            int Distance_y = Dst_Height * Src_Point.y / Src.height;
            int Object_x = Dst_Point.x - Distance_x + this.BasicBody.Dst.x;
            int Object_y = Dst_Point.y - Distance_y + this.BasicBody.Dst.y;
            Dst = new Display.Rect(Object_x, Object_y, Dst_Width, Dst_Height);
        } else {
            System.out.println("Draw_SrcPoint_To_DstPoint Src_Point Error");
        }
        return Dst;
    }
    //给后来的图片设置相对于MainBody的位置
    ;
    //后期设置绘制队列，方便管理绘制层次
    ;
    //--------------------------------专属：FishRoe的Display方法--------------------------------------

    public int GetFollowSpeed(int gap, Picture p) {
        int speed;
        speed = (int) (gap / p.FollowSpeed);
        return speed;
    }
    //获得速度

    public int[] Follow(Picture picture, Point Picture_Centre, Point Body_Centre, double PictureSecond_Zoom) {//Centre填写图片内的相对位置
        double Body_Zoom = BasicSize / this.BasicBody.Image_B.getHeight();
        double Picture_Zoom = PictureSecond_Zoom * BasicSize / this.BasicBody.Image_B.getHeight();
        int gap_x = (int) ((this.BasicBody.Dst.x + Body_Centre.x * Body_Zoom) - (picture.Dst.x + Picture_Centre.x * Picture_Zoom));
        int gap_y = (int) ((this.BasicBody.Dst.y + Body_Centre.y * Body_Zoom) - (picture.Dst.y + Picture_Centre.y * Picture_Zoom));
        int Speed_x = GetFollowSpeed(gap_x, picture);
        int Speed_y = GetFollowSpeed(gap_y, picture);
        if (!picture.eventLock.isMove_Follow()) {
            picture.Dst.x = picture.Dst.x + Speed_x;
            picture.Dst.y = picture.Dst.y + Speed_y;
        }
        //只有在主图片的事件锁没上锁才能使用
        int[] GetGap = new int[2];
        GetGap[0] = gap_x;
        GetGap[1] = gap_y;
        return GetGap;
    }
    //跟随

    private int GetAngleSpeed(int AngleGap) {
        int speed;
        speed = (int) (AngleGap / AngleSpeed);
        return speed;
    }
    //获取角速度

    public void AngleGradient(Picture picture) {
        if (!picture.eventLock.isMove_Angle()) {
            int gap = picture.TargetDegree - picture.CurrentDegree;
            if (gap == 0) {
                return;
            }
            int AngleSpeed = GetAngleSpeed(gap);
            if (picture.TargetDegree > picture.CurrentDegree) {
                picture.CurrentDegree = Math.min(picture.CurrentDegree + AngleSpeed, picture.TargetDegree);
            } else {
                picture.CurrentDegree = Math.max(picture.CurrentDegree + AngleSpeed, picture.TargetDegree);
            }
            picture.AngleFollowSpeed = AngleSpeed;
        }
    }
    //角度渐变方法

    public void Angle_BodyMoved(Picture picture, Direction direction,
                                int startTargetAngle, int gap_x, int gap_y) {//gap_x>0：向右；gap_y>0：向下
        if (!picture.eventLock.isMove_Angle()) {
            int gapAngle;
            int gap = 0;

            if (direction == Direction.Left_Up) {
                gap = gap_y - gap_x;
            } else if (direction == Direction.Right_Up) {
                gap = -gap_x - gap_y;
            } else if (direction == Direction.Left_Down) {
                gap = gap_x + gap_y;
            } else if (direction == Direction.Right_Down) {
                gap = gap_x - gap_y;
            }
            //四个方向分类讨论

            if (gap == 0) {
                return;
            }
            //角度没有差距，回去

            gapAngle = (int) (gap * AngleMoveRate);
            int tempTargetAngle;
            //获得角度差距

            tempTargetAngle = startTargetAngle + gapAngle;
            if (direction == Direction.Left_Up) {
                if (tempTargetAngle > 0) {
                    tempTargetAngle = 0;
                } else if (tempTargetAngle < -90) {
                    tempTargetAngle = -90;
                }
            } else if (direction == Direction.Right_Up) {
                if (tempTargetAngle > 90) {
                    tempTargetAngle = 90;
                } else if (tempTargetAngle < 0) {
                    tempTargetAngle = 0;
                }
            } else if (direction == Direction.Left_Down) {
                if (tempTargetAngle < 0) {
                    tempTargetAngle = 0;
                } else if (tempTargetAngle > 90) {
                    tempTargetAngle = 90;
                }
            } else {
                if (tempTargetAngle > 0) {
                    tempTargetAngle = 0;
                } else if (tempTargetAngle < -90) {
                    tempTargetAngle = -90;
                }
            }
            //获得临时目标角度

            picture.TargetDegree = tempTargetAngle;
            //角度跟随
        }
        //角度锁没有上锁才能角度移动
    }
    //移动时候的角度变化

    public enum Direction {
        Left_Up, Right_Up, Left_Down, Right_Down, Left, Right, Up, Down;
    }

    //枚举：方向
    public void Move_BasicBody(int Speed_x, int Speed_y) {
        this.BasicBody.Dst.x += Speed_x;
        this.BasicBody.Dst.y += Speed_y;
        if (Speed_x != 0 || Speed_y != 0) {
            this.Move_BasicBody_ResetDst();
        }
        if (Global_mouseDragged_BasicBody.List_LockedTexts != null && !Global_mouseDragged_BasicBody.List_LockedTexts.isEmpty()) {
            for (Display.Text t : Global_mouseDragged_BasicBody.List_LockedTexts) {
                t.Location.x += Speed_x;
                t.Location.y += Speed_y;
            }
        }
    }

    public void Move_BasicBody_ResetDst() {
        Basic_Zoom = BasicSize / 785.0;
        //数据BasicSize

        final int HighLight_Relative_X = 250, HighLight_Relative_Y = 165;

        this.HappyEye.Dst = Get_PictureRelativeLocation_OfBasicBody(this.HappyEye.Src,
                this.HappyEye.SrcCentre,
                new Point(this.BasicBody.Dst.width / 2, (int) (EyesHappy_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.HappyEye.Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.HappyEye.Src.height * BasicSize / this.BasicBody.Src.height));
        //HappyEye部分的图片数据

        this.BasicHatch[0].Dst = this.BasicBody.Dst;
        //Hatch的基础身体图片数据

        this.BasicHatch[1].GetNewDst_AppointSrc_In_AppointDstBufferedImage(this.BasicHatch[1].Src,
                this.BasicHatch[1].SrcCentre,
                new Point((int) (this.BasicBody.Dst.x + this.BasicBody.Dst.width / 2.0),
                        (int) (this.BasicBody.Dst.y + Hatch_Relative_Y * Basic_Zoom)),
                (int) (this.BasicHatch[1].Src.width * Basic_Zoom), (int) (this.BasicHatch[1].Src.height * Basic_Zoom));
        //Hatch的基础身体图片数据

        this.BasicEyes.Dst = Get_PictureRelativeLocation_OfBasicBody(this.BasicEyes.Src,
                this.BasicEyes.SrcCentre,
                new Point(this.BasicBody.Dst.width / 2, (int) (Eyes_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.BasicEyes.Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.BasicEyes.Src.height * BasicSize / this.BasicBody.Src.height));
        //Eyes部分的图片数据

        this.Surprised.Dst = Get_PictureRelativeLocation_OfBasicBody(this.Surprised.Src,
                this.Surprised.SrcCentre,
                new Point(this.BasicBody.Dst.width / 2, (int) (Eyes_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.Surprised.Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.Surprised.Src.height * BasicSize / this.BasicBody.Src.height));
        //Surprised部分的图片数据

        this.XX.Dst = Get_PictureRelativeLocation_OfBasicBody(this.XX.Src,
                this.XX.SrcCentre,
                new Point(this.BasicBody.Dst.width / 2, (int) ((EyesHappy_Relative_Y - 50) * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.XX.Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.XX.Src.height * BasicSize / this.BasicBody.Src.height));
        //XX部分的图片数据

        this.AngryEye.Dst = Get_PictureRelativeLocation_OfBasicBody(this.AngryEye.Src,
                this.AngryEye.SrcCentre,
                new Point(this.BasicBody.Dst.width / 2, (int) (Eyes_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.AngryEye.Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.AngryEye.Src.height * BasicSize / this.BasicBody.Src.height));
        //AngryEye部分的图片数据

        this.SadEye.Dst = Get_PictureRelativeLocation_OfBasicBody(this.SadEye.Src,
                this.SadEye.SrcCentre,
                new Point(this.BasicBody.Dst.width / 2, (int) (Eyes_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.SadEye.Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.SadEye.Src.height * BasicSize / this.BasicBody.Src.height));
        //SadEye部分的图片数据

        this.Grievance_Angry.Dst = Get_PictureRelativeLocation_OfBasicBody(this.Grievance_Angry.Src,
                this.Grievance_Angry.SrcCentre,
                new Point(this.BasicBody.Dst.width / 2, (int) (Eyes_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.Grievance_Angry.Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.Grievance_Angry.Src.height * BasicSize / this.BasicBody.Src.height));
        //Grievance_Angry部分的图片数据

        this.Grievance_Sad.Dst = Get_PictureRelativeLocation_OfBasicBody(this.Grievance_Sad.Src,
                this.Grievance_Sad.SrcCentre,
                new Point(this.BasicBody.Dst.width / 2, (int) (Eyes_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.Grievance_Sad.Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.Grievance_Sad.Src.height * BasicSize / this.BasicBody.Src.height));
        //Grievance_Sad部分的图片数据

        this.Grievance_Squint.Dst = Get_PictureRelativeLocation_OfBasicBody(this.Grievance_Squint.Src,
                this.Grievance_Squint.SrcCentre,
                new Point(this.BasicBody.Dst.width / 2, (int) (Eyes_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.Grievance_Squint.Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.Grievance_Squint.Src.height * BasicSize / this.BasicBody.Src.height));
        //Grievance_Squint部分的图片数据

        this.LoveEyes1[0].Dst = Get_PictureRelativeLocation_OfBasicBody(this.LoveEyes1[0].Src,
                this.LoveEyes1[0].SrcCentre,
                new Point((int) (Eyes_Relative_X_left * BasicSize / this.BasicBody.Image_B.getWidth()),
                        (int) (Eyes_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.LoveEyes1[0].Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.LoveEyes1[0].Src.height * BasicSize / this.BasicBody.Src.height));

        this.LoveEyes1[1].Dst = Get_PictureRelativeLocation_OfBasicBody(this.LoveEyes1[1].Src,
                this.LoveEyes1[1].SrcCentre,
                new Point((int) (Eyes_Relative_X_right * BasicSize / this.BasicBody.Image_B.getWidth()),
                        (int) (Eyes_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.LoveEyes1[1].Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.LoveEyes1[1].Src.height * BasicSize / this.BasicBody.Src.height));
        ;
        //LoveEyes部分数据

        this.Dizzy[0].Dst = Get_PictureRelativeLocation_OfBasicBody(this.Dizzy[0].Src,
                this.Dizzy[0].SrcCentre,
                new Point((int) (Eyes_Relative_X_left * BasicSize / this.BasicBody.Image_B.getWidth()),
                        (int) (Eyes_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.Dizzy[0].Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.Dizzy[0].Src.height * BasicSize / this.BasicBody.Src.height));

        this.Dizzy[1].Dst = Get_PictureRelativeLocation_OfBasicBody(this.Dizzy[1].Src,
                this.Dizzy[1].SrcCentre,
                new Point((int) (Eyes_Relative_X_right * BasicSize / this.BasicBody.Image_B.getWidth()),
                        (int) (Eyes_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.Dizzy[1].Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.Dizzy[1].Src.height * BasicSize / this.BasicBody.Src.height));
        //Dizzy部分数据

        this.StarEyes[0].Dst = Get_PictureRelativeLocation_OfBasicBody(this.StarEyes[0].Src,
                this.StarEyes[0].SrcCentre,
                new Point((int) (Eyes_Relative_X_left * BasicSize / this.BasicBody.Image_B.getWidth()),
                        (int) (Eyes_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.StarEyes[0].Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.StarEyes[0].Src.height * BasicSize / this.BasicBody.Src.height));

        this.StarEyes[1].Dst = Get_PictureRelativeLocation_OfBasicBody(this.StarEyes[1].Src,
                this.StarEyes[1].SrcCentre,
                new Point((int) (Eyes_Relative_X_right * BasicSize / this.BasicBody.Image_B.getWidth()),
                        (int) (Eyes_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.StarEyes[1].Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.StarEyes[1].Src.height * BasicSize / this.BasicBody.Src.height));
        //StarEyes部分数据

        this.Squint.Dst = Get_PictureRelativeLocation_OfBasicBody(this.Squint.Src,
                this.Squint.SrcCentre,
                new Point(this.BasicBody.Dst.width / 2, (int) (Eyes_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.Squint.Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.Squint.Src.height * BasicSize / this.BasicBody.Src.height));
        //Squint部分的图片数据

        this.Doubt.Dst = Get_PictureRelativeLocation_OfBasicBody(this.Doubt.Src,
                this.Doubt.SrcCentre,
                new Point(this.BasicBody.Dst.width / 2, (int) (Eyes_Relative_Y * Basic_Zoom)),
                (int) (this.Doubt.Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.Doubt.Src.height * BasicSize / this.BasicBody.Src.height));
        //Doubt部分的图片数据

        this.HighLight.Dst = Get_PictureRelativeLocation_OfBasicBody(this.HighLight.Src,
                this.HighLight.SrcCentre,
                new Point((int) (HighLight_Relative_X * BasicSize / this.BasicBody.Image_B.getWidth()),
                        (int) (HighLight_Relative_Y * BasicSize / this.BasicBody.Image_B.getHeight())),
                (int) (this.HighLight.Src.width * BasicSize / this.BasicBody.Src.width),
                (int) (this.HighLight.Src.height * BasicSize / this.BasicBody.Src.height));
        //高光图片数据

        InterfaceButton.Global_SetUpSwitch.Dst = this.Get_PictureRelativeLocation_OfBasicBody(InterfaceButton.Global_SetUpSwitch.Src,
                InterfaceButton.Global_SetUpSwitch.SrcCentre,
                new Point(Main.robot.BasicBody.Dst.width / 2, (int) (InterfaceButton.Button.Switch_Relative_Y * Basic_Zoom)),
                (int) (InterfaceButton.Global_SetUpSwitch.Src.width * Basic_Zoom),
                (int) (InterfaceButton.Global_SetUpSwitch.Src.height * Basic_Zoom));
        ;
        InterfaceButton.Global_SetUpButton.Dst = Main.robot.Get_PictureRelativeLocation_OfBasicBody(InterfaceButton.Global_SetUpButton.Src,
                InterfaceButton.Global_SetUpButton.SrcCentre,
                new Point(Main.robot.BasicBody.Dst.width + InterfaceButton.SetUpButton_X,
                        (int) (Main.robot.BasicBody.Dst.height / 2.0)),
                (int) (InterfaceButton.Global_SetUpButton.Src.width * InterfaceButton.Zoom_Buttons),
                (int) (InterfaceButton.Global_SetUpButton.Src.height * InterfaceButton.Zoom_Buttons));
        InterfaceButton.Global_BatteryButton.Dst = Main.robot.Get_PictureRelativeLocation_OfBasicBody(InterfaceButton.Global_BatteryButton.Src,
                InterfaceButton.Global_BatteryButton.SrcCentre,
                new Point(Main.robot.BasicBody.Dst.width + InterfaceButton.BatteryButton_X,
                        (int) (Main.robot.BasicBody.Dst.height / 2.0)),
                (int) (InterfaceButton.Global_BatteryButton.Src.width * InterfaceButton.Zoom_Buttons),
                (int) (InterfaceButton.Global_BatteryButton.Src.height * InterfaceButton.Zoom_Buttons));
        InterfaceButton.Global_LoveButton.Dst = Main.robot.Get_PictureRelativeLocation_OfBasicBody(InterfaceButton.Global_LoveButton.Src,
                InterfaceButton.Global_LoveButton.SrcCentre,
                new Point(Main.robot.BasicBody.Dst.width + InterfaceButton.LoveButton_X,
                        (int) (Main.robot.BasicBody.Dst.height / 2.0)),
                (int) (InterfaceButton.Global_LoveButton.Src.width * InterfaceButton.Zoom_Buttons),
                (int) (InterfaceButton.Global_LoveButton.Src.height * InterfaceButton.Zoom_Buttons));
        InterfaceButton.Global_ExitButton.Dst = Main.robot.Get_PictureRelativeLocation_OfBasicBody(InterfaceButton.Global_ExitButton.Src,
                InterfaceButton.Global_ExitButton.SrcCentre,
                new Point(Main.robot.BasicBody.Dst.width + InterfaceButton.ExitButton_X,
                        (int) (Main.robot.BasicBody.Dst.height / 2.0)),
                (int) (InterfaceButton.Global_ExitButton.Src.width * InterfaceButton.Zoom_Buttons),
                (int) (InterfaceButton.Global_ExitButton.Src.height * InterfaceButton.Zoom_Buttons));
        InterfaceButton.Global_ClothesButton.Dst = Main.robot.Get_PictureRelativeLocation_OfBasicBody(InterfaceButton.Global_ClothesButton.Src,
                InterfaceButton.Global_ClothesButton.SrcCentre,
                new Point(Main.robot.BasicBody.Dst.width + InterfaceButton.ClothesButton_X,
                        (int) (Main.robot.BasicBody.Dst.height / 2.0)),
                (int) (InterfaceButton.Global_ClothesButton.Src.width * InterfaceButton.Zoom_Buttons),
                (int) (InterfaceButton.Global_ClothesButton.Src.height * InterfaceButton.Zoom_Buttons));
        //Button数据
    }

    //鱼籽移动
    public boolean Check_IsInScreenCentre() {
        double[] ScreenCentre = MyFrame.GetScreenWH();
        int BodyLeft = this.BasicBody.Dst.x;
        int BodyRight = this.BasicBody.Dst.x + this.BasicBody.Dst.width;
        int BodyUp = this.BasicBody.Dst.y;
        int BodyDown = this.BasicBody.Dst.y + this.BasicBody.Dst.height;
        int Speed_x = 0, Speed_y = 0;
        if (BodyLeft < 0) {
            Speed_x = 1;
        } else if (BodyRight > ScreenCentre[0]) {
            Speed_x = -1;
        }
        if (BodyUp < 0) {
            Speed_y = 1;
        } else if (BodyDown > ScreenCentre[1]) {
            Speed_y = -1;
        }
        if (Speed_x != 0 || Speed_y != 0) {
            this.Move_BasicBody(Speed_x, Speed_y);
            return true;
        } else {
            return false;
        }
    }
    //检查是否在桌面中心
    ;
    //--------------------------------Follow+Gradient方法--------------------------------------

    public static class MouseDragged {
        public MouseDragged(Display.Rect Dst) {
            this.Dst = Dst;
            List_LockedPicture = new ArrayList<>();
        }

        public MouseDragged(Picture p, Direction D) {
            this.picture_object = p;
            this.direction = D;
        }

        private int beforeX;
        private int beforeY;
        //鼠标按下获取的值

        private Display.Rect Dst;
        //变换的窗口

        private ArrayList<Picture> List_LockedPicture;
        //被锁定的图像
        public ArrayList<Display.Text> List_LockedTexts;

        //--------------------------------属性--------------------------------------

        public void Lock_AddLockPicture(Picture picture) {
            if (List_LockedPicture != null) {
                List_LockedPicture.add(picture);
            } else {
                System.out.println("Error:Lock_AddLockedPicture:List_LockedPicture != null!");
            }
        }
        //获取被锁定的图像


        public void Lock_AddLockText(Display.Text text) {
            if (List_LockedTexts != null) {
                List_LockedTexts.add(text);
            } else {
                System.out.println("Error:Lock_AddLockedPicture:List_LockedPicture != null!");
            }
        }
        //获取被锁定的图像


        //--------------------------------队列增删方法--------------------------------------

        public void DraggedPicture_ForBasicBody(MouseEvent e) {
            ThreadControl.PaintSleepController.eventAwake = true;
            int current_x = e.getX();
            int current_y = e.getY();
            Main.robot.Judge_BasicBodyEventRectChange = true;
            Dst.x = Dst.x + (current_x - beforeX);
            Dst.y = Dst.y + (current_y - beforeY);
            for (Picture picture_object : List_LockedPicture) {
                Lock_OnBody(picture_object, current_x, current_y);
            }
            //Lock
            Global_Squint_Operation[0].Begin();
            //开始眯眼
            beforeX = current_x;
            beforeY = current_y;
        }
        //鼠标拖拽：BasicBody的专用拖拽函数

        public void DraggedPicture_ForInterface(MouseEvent e) {
            ThreadControl.PaintSleepController.eventAwake = true;
            int current_x = e.getX();
            int current_y = e.getY();
            Dst.x = Dst.x + (current_x - beforeX);
            Dst.y = Dst.y + (current_y - beforeY);
            if (List_LockedPicture != null && !List_LockedPicture.isEmpty()) {
                for (var picture_object : List_LockedPicture) {
                    Lock_OnBody(picture_object, current_x, current_y);
                }
                //Lock
            }
            if (List_LockedTexts != null && !List_LockedTexts.isEmpty()) {
                for (var text : List_LockedTexts) {
                    Lock_OnBody_Text(text, current_x, current_y);
                }
            }
            beforeX = current_x;
            beforeY = current_y;
        }

        private void Lock_OnBody(Picture picture, int current_x, int current_y) {
            picture.Dst.x = picture.Dst.x + (current_x - beforeX);
            picture.Dst.y = picture.Dst.y + (current_y - beforeY);
        }

        private void Lock_OnBody_Text(Display.Text text, int current_x, int current_y) {
            text.Location.x = text.Location.x + (current_x - beforeX);
            text.Location.y = text.Location.y + (current_y - beforeY);
        }
        //锁在Body上

        public void Pressed_GetPoint(MouseEvent e) {
            beforeX = e.getX();
            beforeY = e.getY();
            ThreadControl.PaintSleepController.eventAwake = true;
        }
        //鼠标按下

        public void Pressed_GetPoint_ChangeEventRect(MouseEvent e) {
            beforeX = e.getX();
            beforeY = e.getY();
            ThreadControl.PaintSleepController.eventAwake = true;
            if (InterfaceButton.Global_Food != null) {
                for (int i = InterfaceButton.Food_Picture_Num - 1; i >= 0; i--) {//从后往前
                    if (InterfaceButton.Global_Food[i].picture.EventJudgeRect.judgePointInRect(e.getX(), e.getY()) &&//区域内
                            InterfaceButton.Global_Food[i].Judge_DisplayPicture) {//已绘制
                        InterfaceButton.Global_Food[i].Judge_EventRectChange = true;
                        break;
                    }
                }
            }
        }
        //--------------------------------锁定Body事件方法--------------------------------------

        public void EarsFoots_MouseDragged(Picture picture, int current_x, int current_y, Direction direction) {
            ThreadControl.PaintSleepController.eventAwake = true;
            int gap_x = current_x - picture.Get_DstCentre(picture.SrcCentre).x;
            int gap_y = current_y - picture.Get_DstCentre(picture.SrcCentre).y;
            int Degree, TargetDegree = 0;
            if (gap_x != 0) {
                Degree = (int) Math.toDegrees(Math.atan2(Math.abs(gap_y), Math.abs(gap_x)));
            } else if (gap_y != 0) {
                Degree = 90;
            } else {
                picture.eventLock.setMove_Angle(false);
                //解锁
                return;
            }
            //用反tan函数计算角度

            if (direction == Direction.Left_Up || direction == Direction.Right_Up) {
                if (gap_x > 0 && gap_y > 0) {
                    TargetDegree = Degree + 90;
                } else if (gap_x > 0 && gap_y < 0) {
                    TargetDegree = 90 - Degree;
                } else if (gap_x < 0 && gap_y > 0) {
                    TargetDegree = -(90 + Degree);
                } else if (gap_x < 0 && gap_y < 0) {
                    TargetDegree = -(90 - Degree);
                } else if (gap_x == 0) {
                    if (gap_y > 0) {
                        TargetDegree = -180;
                    }
                }
            } else if (direction == Direction.Left_Down || direction == Direction.Right_Down) {
                if (gap_x > 0 && gap_y > 0) {
                    TargetDegree = Degree - 90;
                } else if (gap_x > 0 && gap_y < 0) {
                    TargetDegree = -(90 + Degree);
                } else if (gap_x < 0 && gap_y > 0) {
                    TargetDegree = 90 - Degree;
                } else if (gap_x < 0 && gap_y < 0) {
                    TargetDegree = Degree + 90;
                } else if (gap_x == 0) {
                    if (gap_y > 0) {
                        TargetDegree = -180;
                    }
                }
            }
            //处理角度

            picture.CurrentDegree = TargetDegree;
            //角度赋予
        }
        //耳朵拖拽（数据不能公用得创建新的对象）

        public Picture picture_object;
        Direction direction;

        public void DraggedPicture_ForEarsFoots(MouseEvent e) {
            ThreadControl.PaintSleepController.eventAwake = true;
            int current_x = e.getX();
            int current_y = e.getY();


            EarsFoots_MouseDragged(picture_object, current_x, current_y, direction);
            //角度拖拽
        }
        //耳朵的拖拽函数

        public void Pressed_LockAngleFollow(MouseEvent e) {
            ThreadControl.PaintSleepController.eventAwake = true;
            Global_FishRoeAction_Tool.TimeCounter_DraggedEars.CountAdd();
            picture_object.eventLock.setMove_Angle(true);
            //上锁
        }
        //给上锁

        //解锁
        ;
        //--------------------------------拖拽Ears,Foots事件方法--------------------------------------
    }
    //鼠标拖动

    public static class MouseClick {

        public MouseClick(ActionObject_Operation o) {
            this.operation = o;
        }
        //构造器

        public ActionObject_Operation operation;
        //操作对象

        public void Clicked(MouseEvent e) {
            operation.Begin();
            //申请
            if (operation == Global_Blink_Operation[0] || operation == Global_BlinkSad_Operation[0] || operation == Global_BlinkGrievanceSad_Operation[0]) {
                //operation.timer_Timing.OperationTime = -1L;
                Global_FishRoeAction_Tool.Timing_EyesBlink.OperationTime = -1L;
                Global_FishRoeAction_Tool.TimeCounter_ClickEyes.CountAdd();
            } else if (operation == Global_Shake_Operation[0] ||
                    operation == Global_Shake_Operation[1] ||
                    operation == Global_Shake_Operation[2] ||
                    operation == Global_Shake_Operation[3]) {
                for (ActionObject_Operation a : Global_Shake_Operation) {
                    a.timer_Timing.OperationTime = -1L;
                }
                operation.Speed_AngleBegin = Action_ShakeEarsFoots.Final_ClickedShake_Speed;
                operation.GapDegree = Action_ShakeEarsFoots.Final_ClickedShake_GapDegree;
                Global_FishRoeAction_Tool.TimeCounter_ClickEars.CountAdd();
                //增加数据
            }
            ThreadControl.PaintSleepController.eventAwake = true;
            //重置timer+数据重置
        }

    }
    //鼠标点击

    public void MouseReleased_AllUnlock() {
        this.Ears[0].eventLock.setMove_Angle(false);
        this.Ears[1].eventLock.setMove_Angle(false);
        this.Foots[0].eventLock.setMove_Angle(false);
        this.Foots[1].eventLock.setMove_Angle(false);
        //角度锁
        this.Ears[0].eventLock.setMove_Follow(false);
        this.Ears[1].eventLock.setMove_Follow(false);
        this.Foots[0].eventLock.setMove_Follow(false);
        this.Foots[1].eventLock.setMove_Follow(false);
        //跟随锁
        this.Ears[0].eventLock.setMouse_Dragged(false);
        this.Ears[1].eventLock.setMouse_Dragged(false);
        this.Foots[0].eventLock.setMouse_Dragged(false);
        this.Foots[1].eventLock.setMouse_Dragged(false);
        this.BasicBody.eventLock.setMouse_Dragged(false);
        Global_Squint.Unlock_all();//解锁
        Global_Squint.Reset();//重置
        InterfaceButton.Global_SetUp_Interface.eventLock.setMouse_Dragged(false);
        InterfaceButton.Global_MoodSystem_Interface.eventLock.setMouse_Dragged(false);
        InterfaceButton.Global_FoodSystem_Interface.eventLock.setMouse_Dragged(false);
        InterfaceButton.Global_Exit_Interface.eventLock.setMouse_Dragged(false);
        InterfaceButton.Global_Clothes_Interface.eventLock.setMouse_Dragged(false);
        for (int i = 0; i < InterfaceButton.Food_Picture_Num; i++) {
            InterfaceButton.Global_Food[i].picture.eventLock.setMouse_Dragged(false);
        }
        Main.robot.Judge_BasicBodyEventRectChange = false;
        //拖拽锁
        if (InterfaceButton.Global_Food != null) {
            for (int i = 0; i < InterfaceButton.Food_Picture_Num; i++) {
                InterfaceButton.Global_Food[i].Judge_EventRectChange = false;
            }
        }
        //EventRectChange锁
    }
    //放开所有的锁

    public static void MouseReleased_BeginEat(MouseEvent e) {//1.把食物托给Roe    2.把Roe拖到食物旁边
        if (Main.robot.BasicBody != null && !ActionTool.Global_Lock_FoodEaten) {
            ThreadControl.PaintSleepController.eventAwake = true;
            //没有吃到才能吃
            double ratio = 7;
            Display.Rect tempRect = new Display.Rect(Main.robot.BasicBody.Dst.x, Main.robot.BasicBody.Dst.y,
                    Main.robot.BasicBody.Dst.width, Main.robot.BasicBody.Dst.height);
            tempRect.x += (int) (Main.robot.BasicBody.Dst.width / ratio);
            tempRect.y += (int) (Main.robot.BasicBody.Dst.height / ratio);
            tempRect.width = (int) (Main.robot.BasicBody.Dst.width * (ratio - 2) / ratio);
            tempRect.height = (int) (Main.robot.BasicBody.Dst.height * (ratio - 2) / ratio);
            if (tempRect.judgePointInRect(e.getX(), e.getY())) {
                //1.松开的区域在BasicBody的Dst
                if (InterfaceButton.Global_Food != null && InterfaceButton.Global_Food.length > 0) {
                    for (int i = InterfaceButton.Food_Picture_Num - 1; i >= 0; i--) {//从后往前
                        if (InterfaceButton.Global_Food[i].Judge_EventRectChange) {
                            InterfaceButton.Global_Food[i].Eaten = true;
                            //食物被吃了
                            if (InterfaceButton.Global_Food[i].FoodNumber > 0) {
                                InterfaceButton.Global_Food[i].FoodNumber--;
                                Main.userData.foods[i].FoodNumber = InterfaceButton.Global_Food[i].FoodNumber;
                            }
                            int moodValue = Main.userData.moodSystem.getMoodValue();
                            moodValue += InterfaceButton.FoodAddMoodValue;
                            Main.userData.moodSystem.setMoodValue(moodValue);
                            //食物减，心情加
                            Main.userData.writeData();
                            //存档
                            if (Global_LoveEye_Operation != null) {
                                for (Action_StarEyes o : Global_LoveEye_Operation) {
                                    o.Begin();
                                }
                            }
                            if (Global_ShakeEarHappy_Operation2 != null) {
                                for (ActionShakeEarHappy o : Global_ShakeEarHappy_Operation2) {
                                    o.Begin();
                                }
                            }
                            //初始化事件
                            break;
                        }
                    }
                }
            }
        }
    }
    //松开食物，开始吃:注意，此处用到了判断锁，在AllUnlock中解锁，所以此函数必须在AllUnlock之前


    public static void MouseClick_BeginBodyMove(MouseEvent e) {
        int order = ActionTool.Random.Get_Int_SetRange(0, 12);
        if (order == 2) {
            Global_BasicBodyMove_Operation[0].Begin();
        }
    }
    //--------------------------------专属：FishRoe的鼠标监听事件方法--------------------------------------

    public static ActionTool Global_FishRoeAction_Tool;
    //创建动作管理对象
    ;
    //--------------------------------ActionObject对象--------------------------------------

    public static ActionObject Global_Blink;
    public static Action_Blink[] Global_Blink_Operation;
    //眨眼

    public static class Action_Blink extends ActionObject_Operation {
        public Action_Blink(Picture p) {
            super(p);
        }
        //构造方法

        public static final int State_Back = 4;//当前状态：0~3已注册
        //final常量

        @Override
        public void Init() {
            super.Init();
        }
        //初始数据

        @Override
        public void Operation() {
            super.Operation();
            if (this.CurrentState == State_Begin) {
                if (this.TempSrc.hf <= 0) {//到头了
                    this.CurrentState = State_Back;
                }
            }
            if (this.CurrentState == State_Back) {
                if (this.CurrentState != this.BeforeState) {//只执行一次
                    this.Speed_Src_y = -this.Speed_Src_y;
                    this.Speed_Src_h = -this.Speed_Src_h;
                    this.Speed_Dst_y = -this.Speed_Dst_y;
                    this.Speed_Dst_h = -this.Speed_Dst_h;
                }
                //速度反向
                if (this.TempSrc.hf >= this.picture.Src.height) {//回去了
                    this.CurrentState = State_Exit;//退出：已经到了，先退出，再绘制
                    return;
                }
            }

            this.Move();
            //移动

            this.Refresh();
            //刷新数据
        }

        @Override
        protected void reset_init() {
            super.reset_init();

            this.CurrentState = State_Sleep;
            this.BeforeState = this.CurrentState;
            //状态
            this.Speed_Src_y = 20;
            this.Speed_Src_h = -2 * this.Speed_Src_y;
            float Zoom_Dst_Src = this.picture.Dst.height / (float) this.picture.Src.height;
            this.Speed_Dst_y = this.Speed_Src_y * Zoom_Dst_Src;
            this.Speed_Dst_h = this.Speed_Src_h * Zoom_Dst_Src;
            //速度
            this.ImmediatelyEndCondition = (ActionTool.Global_Lock_FoodEaten || ActionTool.Global_Lock_FoodAppeared);
            //boolean条件
        }

        @Override
        public void Reset() {
            super.Reset();
        }

        @Override
        public void Lock() {
            super.Lock();
            picture.eventLock.setDisplay_Conventional(true);
            picture.eventLock.setMouse_Click(true);
        }

        @Override
        public void Unlock() {
            super.Unlock();
            picture.eventLock.setDisplay_Conventional(false);
            picture.eventLock.setMouse_Click(false);
        }

    }

    //------------------眨眼类------------------
    public static ActionObject Global_BlinkSad;
    public static Action_Blink[] Global_BlinkSad_Operation;
    //眨眼

    //------------------眨眼类------------------
    public static ActionObject Global_BlinkGrievanceSad;
    public static Action_Blink[] Global_BlinkGrievanceSad_Operation;
    //眨眼
    ;
    //------------------眨眼类------------------

    public static ActionObject Global_Shake;
    public static Action_ShakeEarsFoots[] Global_Shake_Operation;
    //抖耳朵，脚

    public static class Action_ShakeEarsFoots extends ActionObject_Operation {

        public Action_ShakeEarsFoots(Picture p, Direction direction) {
            super(p);
            this.direction = direction;
            this.Reset();
        }
        //构造方法

        public static final int State_Back1 = 4, State_Back2 = 5, State_Back3 = 6, State_Back4 = 7;//当前状态：0~3已注册
        //final常量
        public Direction direction;//方向

        //属性

        public static final int Final_ClickedShake_Speed = 15, Final_ClickedShake_GapDegree = 15;
        public static final int Final_AutoShake_Speed = 5, Final_AutoShake_GapDegree = 5;
        //final常量

        @Override
        public void Init() {
            super.Init();
        }
        //初始数据

        @Override
        public void Operation() {
            super.Operation();
            if (direction == Direction.Left_Up || direction == Direction.Right_Down) {//左半轴
                if (this.CurrentState == State_Begin) {
                    if (this.TempDegree <= this.TargetDegree) {//到达目的地了：速度为负，选择<=
                        this.CurrentState = State_Back1;
                    }
                }
                if (this.CurrentState == State_Back1) {
                    if (this.CurrentState != this.BeforeState) {//只执行一次
                        this.AngleSpeed = -this.AngleSpeed;//负方向
                        this.TargetDegree = picture.TargetDegree + this.GapDegree;
                    }
                    if (this.TempDegree >= this.TargetDegree) {//到达目的地了：速度为正，选择>=
                        this.CurrentState = State_Back2;
                    }
                }
                if (this.CurrentState == State_Back2) {
                    if (this.CurrentState != this.BeforeState) {//只执行一次
                        this.AngleSpeed = -this.AngleSpeed;//负方向
                        this.TargetDegree = picture.TargetDegree - this.GapDegree;
                    }
                    if (this.TempDegree <= this.TargetDegree) {//到达目的地了：速度为负，选择<=
                        this.CurrentState = State_Back3;
                    }
                }
                if (this.CurrentState == State_Back3) {
                    if (this.CurrentState != this.BeforeState) {//只执行一次
                        this.AngleSpeed = -this.AngleSpeed;//负方向
                        this.TargetDegree = picture.TargetDegree + this.GapDegree;
                    }
                    if (this.TempDegree >= this.TargetDegree) {//到达目的地了：速度为正，选择>=
                        this.CurrentState = State_Back4;
                    }
                }
                if (this.CurrentState == State_Back4) {
                    if (this.CurrentState != this.BeforeState) {//只执行一次
                        this.AngleSpeed = -this.AngleSpeed;//负方向
                        this.TargetDegree = picture.TargetDegree;
                    }
                    if (this.TempDegree <= this.TargetDegree) {//到达目的地了：速度为负，选择<=
                        this.CurrentState = State_Exit;
                    }
                }
            } else if (direction == Direction.Right_Up || direction == Direction.Left_Down) {//右半轴
                if (this.CurrentState == State_Begin) {
                    if (this.TempDegree >= this.TargetDegree) {//到达目的地了：速度为正，选择>=
                        this.CurrentState = State_Back1;
                    }
                }
                if (this.CurrentState == State_Back1) {
                    if (this.CurrentState != this.BeforeState) {//只执行一次
                        this.AngleSpeed = -this.AngleSpeed;//负方向
                        this.TargetDegree = picture.TargetDegree - this.GapDegree;
                    }
                    if (this.TempDegree <= this.TargetDegree) {//到达目的地了：速度为负，选择<=
                        this.CurrentState = State_Back2;
                    }
                }
                if (this.CurrentState == State_Back2) {
                    if (this.CurrentState != this.BeforeState) {//只执行一次
                        this.AngleSpeed = -this.AngleSpeed;//负方向
                        this.TargetDegree = picture.TargetDegree + this.GapDegree;
                    }
                    if (this.TempDegree >= this.TargetDegree) {//到达目的地了：速度为正，选择>=
                        this.CurrentState = State_Back3;
                    }
                }
                if (this.CurrentState == State_Back3) {
                    if (this.CurrentState != this.BeforeState) {//只执行一次
                        this.AngleSpeed = -this.AngleSpeed;//负方向
                        this.TargetDegree = picture.TargetDegree - this.GapDegree;
                    }
                    if (this.TempDegree <= this.TargetDegree) {//到达目的地了：速度为负，选择<=
                        this.CurrentState = State_Back4;
                    }
                }
                if (this.CurrentState == State_Back4) {
                    if (this.CurrentState != this.BeforeState) {//只执行一次
                        this.AngleSpeed = -this.AngleSpeed;//负方向
                        this.TargetDegree = picture.TargetDegree;
                    }
                    if (this.TempDegree >= this.TargetDegree) {//到达目的地了：速度为正，选择>=
                        this.CurrentState = State_Exit;
                    }
                }
            }

            this.Move();
            this.AngleMove();
            //移动

            this.Refresh();
            //刷新数据
        }
        //操作

        @Override
        protected void reset_init() {
            super.reset_init();
            this.CurrentState = State_Sleep;
            this.BeforeState = this.CurrentState;
            this.TempDegree = picture.TargetDegree;
            //状态

            this.Speed_AngleBegin = Final_AutoShake_Speed;
            this.GapDegree = Final_AutoShake_GapDegree;
            //变量
            this.ImmediatelyEndCondition = (ActionTool.Global_Lock_FoodEaten || ActionTool.Global_Lock_FoodAppeared);
            //boolean条件
        }

        @Override
        public void Reset() {
            super.Reset();

            if (direction == Direction.Left_Up || direction == Direction.Right_Down) {
                this.AngleSpeed = -Speed_AngleBegin;
                this.TargetDegree = picture.TargetDegree - this.GapDegree;
            } else if (direction == Direction.Right_Up || direction == Direction.Left_Down) {
                this.AngleSpeed = Speed_AngleBegin;
                this.TargetDegree = picture.TargetDegree + this.GapDegree;
            }
            //角速度 + 角度差距
        }
        //重置

        @Override
        public void Lock() {
            super.Lock();
            picture.eventLock.setDisplay_Conventional(true);//绘制
            picture.eventLock.setMouse_Click(true);//点击
            picture.eventLock.setMouse_Dragged(true);//拖拽
            picture.eventLock.setMouse_Pressed(true);//按下
            picture.eventLock.setMove_Angle(true);//角度跟随锁
            picture.FollowSpeed = 1;
        }
        //上锁

        @Override
        public void Unlock() {
            super.Unlock();
            picture.eventLock.setDisplay_Conventional(false);//绘制
            picture.eventLock.setMouse_Click(false);//点击
            picture.eventLock.setMouse_Dragged(false);//拖拽
            picture.eventLock.setMouse_Pressed(false);//按下
            picture.eventLock.setMove_Angle(false);//角度跟随锁
            picture.FollowSpeed = Main.robot.FollowSpeed_Final;
        }
        //解锁

        @Override
        public void Begin() {
            super.Begin();
        }
    }

    //------------------抖耳类------------------

    public static ActionObject Global_Squint;

    public static Action_Squint[] Global_Squint_Operation;
    //眯眼

    public static class Action_Squint extends ActionObject_Operation {
        public Action_Squint(Picture picture) {
            super(picture);
            this.Init();
        }

        public boolean Lock_MouseDragged;//上锁：MouseDragged::Pressed_GetPoint  解锁：AllUnlock_MouseReleased
        //鼠标拖拽锁
        public float Bound_x;
        //边界位置：相对于初始图片(根据Basic_Zoom比例变换)
        public Direction direction, Before_direction;
        //当前朝向

        //------------------属性------------------

        public static final int Speed_final = 14, Bound_x_final = 45;

        //------------------final常量------------------

        @Override
        public void Init() {
            super.Init();
            this.reset_init();
            this.TempDst = new Display.Rect((float) picture.Dst.x,
                    (float) picture.Dst.y,
                    (float) picture.Dst.width,
                    (float) picture.Dst.height);
        }
        //初始化函数

        @Override
        public void reset_init() {
            super.reset_init();
            this.direction = Direction.Left;
            this.Before_direction = this.direction;
            this.Speed_Dst_x = (float) (-Speed_final * Main.robot.Basic_Zoom);
            this.Bound_x = (float) (-Bound_x_final * Main.robot.Basic_Zoom);
            //根据比例变换
            this.Lock_MouseDragged = false;
            this.Temp_Dst_x = 0;//初始的距离差距未0
        }
        //初始化和重置的重复函数

        @Override
        public void Reset() {
            super.Reset();
            this.reset_init();
        }
        //重置函数：在Dragged里面的Release进行重置

        @Override
        public void Operation() {
            super.Operation();
            if (this.Lock_MouseDragged) {//状态是正在被拖拽+表情锁上目前没有其他的表情
                if (this.direction == Direction.Left) {
                    if (this.Speed_Dst_x + this.Temp_Dst_x > this.Bound_x) {//负数
                        this.Temp_Dst_x += this.Speed_Dst_x;
                    } else {
                        this.Temp_Dst_x = this.Bound_x;
                        this.direction = Direction.Right;
                        //切换方向
                        this.Bound_x = -this.Bound_x;
                        this.Speed_Dst_x = -this.Speed_Dst_x;
                        //速度，边界切换
                    }
                    //方向切换判定
                }
                if (this.direction == Direction.Right) {
                    if (this.Speed_Dst_x + this.Temp_Dst_x < this.Bound_x) {//正数
                        this.Temp_Dst_x += this.Speed_Dst_x;
                    } else {
                        this.Temp_Dst_x = this.Bound_x;
                        this.direction = Direction.Left;
                        //切换方向
                        this.Bound_x = -this.Bound_x;
                        this.Speed_Dst_x = -this.Speed_Dst_x;
                        //速度，边界切换
                    }
                    //方向切换判定
                }
                //数据处理

                this.Move();
                //移动

                this.Refresh();
                //重置
            }
        }
        //操作函数

        @Override
        public void Refresh() {
            super.Refresh();
        }
        //重置

        @Override
        public void Lock() {
            super.Lock();
            this.Lock_MouseDragged = true;
        }
        //上锁

        @Override
        public void Unlock() {
            super.Unlock();
            this.Lock_MouseDragged = false;
            this.Reset();
            this.CurrentState = ActionObject_Operation.State_Sleep;//状态为退出
        }
        //解锁：特殊：根据Lock重置与退出

    }

    //------------------眯眼类------------------


    public static ActionObject Global_Doubt;

    public static Action_Doubt[] Global_Doubt_Operation;

    public static class Action_Doubt extends ActionObject_Operation {
        public Action_Doubt(Picture p) {
            super(p);
        }

        //------------------属性------------------

        public static final int State_Back1 = 4, State_Back2 = 5;

        //------------------final常量------------------

        @Override
        public void Init() {
            super.Init();
            this.reset_init();
            this.TempDst = new Display.Rect((float) picture.Dst.x,
                    (float) picture.Dst.y,
                    (float) picture.Dst.width,
                    (float) picture.Dst.height);
        }
        //初始化函数

        @Override
        public void reset_init() {
            super.reset_init();
            this.Speed_AngleBegin = 6;
            this.GapDegree = 30;
            this.AngleSpeed = -this.Speed_AngleBegin;
            this.TargetDegree = -this.GapDegree;
            this.TempDegree = 0;
        }
        //初始化和重置的重复函数

        @Override
        public void Reset() {
            super.Reset();
            this.reset_init();
        }
        //重置函数：在Dragged里面的Release进行重置

        @Override
        public void Operation() {
            super.Operation();
            if (this.CurrentState == State_Begin) {
                if (this.CurrentState != this.BeforeState) {
                    this.AngleSpeed = -this.Speed_AngleBegin;
                    this.TargetDegree = -this.GapDegree;
                }
                //初始化
                if (this.TempDegree + this.AngleSpeed > this.TargetDegree) {
                    this.AngleSpeed = -this.Speed_AngleBegin;
                } else {
                    this.AngleSpeed = 0;
                    this.TempDegree = this.TargetDegree;
                    this.CurrentState = State_Back1;
                }
            }
            if (this.CurrentState == State_Back1) {
                if (this.CurrentState != this.BeforeState) {
                    this.AngleSpeed = this.Speed_AngleBegin;
                    this.TargetDegree = this.GapDegree;
                }
                //初始化
                if (this.TempDegree + this.AngleSpeed < this.TargetDegree) {
                    this.AngleSpeed = this.Speed_AngleBegin;
                } else {
                    this.AngleSpeed = 0;
                    this.TempDegree = this.TargetDegree;
                    this.CurrentState = State_Back2;
                }
            }
            if (this.CurrentState == State_Back2) {
                if (this.CurrentState != this.BeforeState) {
                    this.AngleSpeed = -this.Speed_AngleBegin;
                    this.TargetDegree = -this.GapDegree;
                }
                //初始化
                if (this.TempDegree + this.AngleSpeed > this.TargetDegree) {
                    this.AngleSpeed = -this.Speed_AngleBegin;
                } else {
                    this.AngleSpeed = 0;
                    this.TempDegree = this.TargetDegree;
                    this.CurrentState = State_Exit;
                }
            }
            //数据处理

            this.AngleMove();
            //角度移动

            this.Move();
            //移动

            this.Refresh();
            //重置
        }
        //操作函数

        @Override
        public void Refresh() {
            super.Refresh();
        }
        //重置

        @Override
        public void Lock() {
            super.Lock();
        }
        //上锁

        @Override
        public void Unlock() {
            super.Unlock();
        }
        //解锁：特殊：根据Lock重置与退出
    }

    //------------------疑惑类------------------

    public static ActionObject Global_Dizzy;

    public static Action_Dizzy[] Global_Dizzy_Operation;

    public static long Global_ActionDuration_Dizzy = (long) (4.0 * 1000L);

    public static class Action_Dizzy extends ActionObject_Operation {
        public Action_Dizzy(Picture p, Direction direction) {
            super(p);
            this.direction = direction;
            this.Reset();
        }

        private final Direction direction;

        //------------------属性------------------


        //------------------final常量------------------

        @Override
        public void Init() {
            super.Init();
            this.reset_init();
            this.TempDst = new Display.Rect((float) picture.Dst.x,
                    (float) picture.Dst.y,
                    (float) picture.Dst.width,
                    (float) picture.Dst.height);
        }
        //初始化函数

        @Override
        public void reset_init() {
            super.reset_init();
            this.Speed_AngleBegin = 12;
            this.TempDegree = 0;
            if (this.direction == Direction.Left) {
                this.AngleSpeed = this.Speed_AngleBegin;
            } else if (this.direction == Direction.Right) {
                this.AngleSpeed = -this.Speed_AngleBegin;
            }
        }
        //初始化和重置的重复函数

        @Override
        public void Reset() {
            super.Reset();
            this.reset_init();
        }
        //重置函数：在Dragged里面的Release进行重置

        @Override
        public void Operation() {
            super.Operation();
            this.Set_EndTime(this.Time_EndTime);
            //数据处理

            this.AngleMove();
            //角度移动

            this.Move();
            //移动

            this.Refresh();
            //重置
        }
        //操作函数

        @Override
        public void Refresh() {
            super.Refresh();
        }
        //重置

        @Override
        public void Lock() {
            super.Lock();
            picture.eventLock.setDisplay_Conventional(true);
            picture.eventLock.setMouse_Click(true);
            Main.robot.BasicEyes.eventLock.setDisplay_Conventional(true);//通用绘制解锁
        }
        //上锁

        @Override
        public void Unlock() {
            super.Unlock();
            picture.eventLock.setDisplay_Conventional(false);
            picture.eventLock.setMouse_Click(false);
            Main.robot.BasicEyes.eventLock.setDisplay_Conventional(false);//通用绘制解锁
        }
        //解锁：特殊：根据Lock重置与退出
    }

    //------------------眩晕类------------------

    public static ActionObject Global_WonderEye;
    public static ActionObject Global_WonderEar;
    public static Action_WonderEyes[] Global_WonderEye_Operation;

    public static Action_ShakeEar_Wonder[] Global_WonderEar_Operation;

    public static long Global_ActionDuration_Wonder = (long) (1.5 * 1000L);

    public static class Action_ShakeEar_Wonder extends ActionObject_Operation {
        public Action_ShakeEar_Wonder(Picture p, Direction direction) {
            super(p);
            this.direction = direction;
            this.Reset();
        }
        //构造方法

        //final常量
        public Direction direction;//方向
        //属性

        public static final int Final_AutoShake_Speed = 10;
        //final常量

        @Override
        public void Init() {
            super.Init();
            this.reset_init();
        }
        //初始数据

        @Override
        public void Operation() {
            super.Operation();
            this.Set_EndTime(Global_ActionDuration_Wonder);
            if (direction == Direction.Left_Up || direction == Direction.Right_Up) {//上半轴
                this.TargetDegree = 0;
                if (direction == Direction.Left_Up) {
                    if (this.TempDegree >= this.TargetDegree) {
                        this.AngleSpeed = 0;
                        this.TempDegree = this.TargetDegree;
                    } else {
                        this.AngleSpeed = Final_AutoShake_Speed;
                    }
                } else {
                    if (this.TempDegree <= this.TargetDegree) {
                        this.AngleSpeed = 0;
                        this.TempDegree = this.TargetDegree;
                    } else {
                        this.AngleSpeed = -Final_AutoShake_Speed;
                    }
                }
            } else if (direction == Direction.Left_Down) {
                this.TargetDegree = 90;
                if (this.TempDegree >= this.TargetDegree) {
                    this.AngleSpeed = 0;
                    this.TempDegree = this.TargetDegree;
                } else {
                    this.AngleSpeed = Final_AutoShake_Speed;
                }
            } else if (direction == Direction.Right_Down) {
                this.TargetDegree = -90;
                if (this.TempDegree <= this.TargetDegree) {
                    this.AngleSpeed = 0;
                    this.TempDegree = this.TargetDegree;
                } else {
                    this.AngleSpeed = -Final_AutoShake_Speed;
                }
            }

            this.Move();
            this.AngleMove();
            //移动

            this.Refresh();
            //刷新数据
        }
        //操作

        @Override
        protected void reset_init() {
            super.reset_init();
            this.CurrentState = State_Sleep;
            this.BeforeState = this.CurrentState;
            this.TempDegree = picture.TargetDegree;
            //状态

            this.Speed_AngleBegin = Final_AutoShake_Speed;
            //变量
        }

        @Override
        public void Reset() {
            super.Reset();
            this.reset_init();

            if (direction == Direction.Left_Up || direction == Direction.Right_Down) {
                this.AngleSpeed = -Speed_AngleBegin;
                this.TargetDegree = picture.TargetDegree - this.GapDegree;
            } else if (direction == Direction.Right_Up || direction == Direction.Left_Down) {
                this.AngleSpeed = Speed_AngleBegin;
                this.TargetDegree = picture.TargetDegree + this.GapDegree;
            }
            //角速度 + 角度差距
        }
        //重置

        @Override
        public void Lock() {
            super.Lock();
            picture.eventLock.setDisplay_Conventional(true);//绘制
            picture.eventLock.setMouse_Click(true);//点击
            picture.eventLock.setMouse_Dragged(true);//拖拽
            picture.eventLock.setMouse_Pressed(true);//按下
            picture.eventLock.setMove_Angle(true);//角度跟随锁
            picture.eventLock.setMouse_Released(true);//不准释放
            Main.robot.BasicEyes.eventLock.setDisplay_Conventional(true);//通用绘制上锁
            picture.FollowSpeed = 1;
        }
        //上锁

        @Override
        public void Unlock() {
            super.Unlock();
            picture.eventLock.setDisplay_Conventional(false);//绘制
            picture.eventLock.setMouse_Click(false);//点击
            picture.eventLock.setMouse_Dragged(false);//拖拽
            picture.eventLock.setMouse_Pressed(false);//按下
            picture.eventLock.setMove_Angle(false);//角度跟随锁
            picture.eventLock.setMouse_Released(false);
            //MainAWT.FishRoe.BasicEyes.eventLock.setDisplay_Conventional(false);//通用绘制解锁
            picture.FollowSpeed = Main.robot.FollowSpeed_Final;
        }
        //解锁

        @Override
        public void Begin() {
            super.Begin();
        }

        @Override
        public void End() {
            super.End();
            this.picture.CurrentDegree = this.TargetDegree;
        }
    }
    //立耳：惊叹

    public static class Action_WonderEyes extends ActionObject_Operation {
        public Action_WonderEyes(Picture p) {
            super(p);
        }
        //构造方法

        public static final int State_Keep = 4;//当前状态：0~3已注册
        //final常量

        @Override
        public void Init() {
            super.Init();
            this.reset_init();
        }
        //初始数据

        @Override
        public void Operation() {
            super.Operation();
            this.Set_EndTime(Global_ActionDuration_Wonder);
            if (this.CurrentState == State_Begin) {
                if (this.TempDst.hf >= this.picture.Dst.height) {//到了
                    this.CurrentState = State_Keep;//退出：已经到了，先退出，再绘制
                }
            }
            if (this.CurrentState == State_Keep) {
                if (this.CurrentState != this.BeforeState) {
                    this.Speed_Dst_y = 0;
                    this.Speed_Dst_h = 0;
                }
            }

            this.Move();
            //移动

            this.Refresh();
            //刷新数据
        }

        @Override
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

        @Override
        protected void Move_Dst_y() {
            this.Temp_Dst_y += this.Speed_Dst_y;
            this.TempDst.yf = this.picture.Dst.y + this.picture.Dst.height + this.Temp_Dst_y;
        }

        @Override
        protected void reset_init() {
            super.reset_init();
            float Zoom_Dst_Src = this.picture.Dst.width / (float) this.picture.Src.width;

            this.TempDst.hf = 0;
            //Picture数据
            this.CurrentState = State_Sleep;
            this.BeforeState = this.CurrentState;
            //状态
            int speed = 50;
            this.Speed_Dst_y = -speed * Zoom_Dst_Src;
            this.Speed_Dst_h = speed * Zoom_Dst_Src;
            //速度
            this.ImmediatelyEndCondition = (ActionTool.Global_Lock_FoodEaten || ActionTool.Global_Lock_FoodAppeared);
            //boolean条件
        }

        @Override
        public void Reset() {
            super.Reset();
            this.reset_init();
        }

        @Override
        public void Lock() {
            super.Lock();
            picture.eventLock.setDisplay_Conventional(true);
            picture.eventLock.setMouse_Click(true);
        }

        @Override
        public void Unlock() {
            super.Unlock();
            picture.eventLock.setDisplay_Conventional(false);
            picture.eventLock.setMouse_Click(false);
        }

    }
    //惊叹：叹号跳起来
    ;
    //------------------惊叹 + 立耳------------------

    public static ActionObject Global_StarEyes;
    public static ActionObject Global_ShakeEarHappy;
    public static Action_StarEyes[] Global_StarEyes_Operation;
    public static ActionShakeEarHappy[] Global_ShakeEarHappy_Operation;
    public static long Global_ActionDuration_StarHappy = (long) (3.5 * 1000L);

    public static class Action_StarEyes extends ActionObject_Operation {
        public Action_StarEyes(Picture p) {
            super(p);
        }
        //构造方法

        public static final int State_Back = 4, State_Go = 5;//当前状态：0~3已注册
        //final常量

        @Override
        public void Init() {
            super.Init();
            this.reset_init();
        }
        //初始数据

        @Override
        public void Operation() {
            super.Operation();
            this.Set_EndTime(this.Time_EndTime);
            if (this.CurrentState == State_Begin || this.CurrentState == State_Go) {
                if (this.CurrentState != this.BeforeState) {
                    this.Speed_Dst_x = -speed;
                    this.Speed_Dst_y = -speed;
                    this.Speed_Dst_w = 2 * speed;
                    this.Speed_Dst_h = 2 * speed;
                }
                float zoom = 1.5F;
                if (this.TempDst.hf >= this.picture.Dst.height * zoom) {//到头了
                    this.CurrentState = State_Back;
                }
            }
            if (this.CurrentState == State_Back) {
                if (this.CurrentState != this.BeforeState) {//只执行一次
                    this.Speed_Dst_x = speed;
                    this.Speed_Dst_y = speed;
                    this.Speed_Dst_w = -2 * speed;
                    this.Speed_Dst_h = -2 * speed;
                }
                //速度反向
                if (this.TempDst.hf <= this.picture.Dst.height) {//回去了
                    this.CurrentState = State_Go;//退出：已经到了，先退出，再绘制
                    return;
                }
            }

            this.Move();
            //移动

            this.Refresh();
            //刷新数据
        }

        private final float speed = (float) (15 * Main.robot.Basic_Zoom);

        @Override
        protected void reset_init() {
            super.reset_init();

            this.CurrentState = State_Sleep;
            this.BeforeState = this.CurrentState;
            //状态
            this.TempDst = new Display.Rect((float) this.picture.Dst.x, (float) this.picture.Dst.y, (float) this.picture.Dst.width, (float) this.picture.Dst.height);

            this.Speed_Dst_x = -speed;
            this.Speed_Dst_y = -speed;
            this.Speed_Dst_w = 2 * speed;
            this.Speed_Dst_h = 2 * speed;
            //速度
            this.ImmediatelyEndCondition = (ActionTool.Global_Lock_FoodEaten || ActionTool.Global_Lock_FoodAppeared);
            //boolean条件
        }

        @Override
        public void Reset() {
            super.Reset();
            this.reset_init();
        }

        @Override
        public void Lock() {
            super.Lock();
            picture.eventLock.setDisplay_Conventional(true);
            picture.eventLock.setMouse_Click(true);
            Main.robot.BasicEyes.eventLock.setDisplay_Conventional(true);//通用绘制解锁
        }

        @Override
        public void Unlock() {
            super.Unlock();
            picture.eventLock.setDisplay_Conventional(false);
            picture.eventLock.setMouse_Click(false);
        }

    }

    //星星眼
    public static class ActionShakeEarHappy extends ActionObject_Operation {
        public ActionShakeEarHappy(Picture p, Direction direction) {
            super(p);
            this.direction = direction;
            this.Reset();
        }
        //构造方法

        public static final int State_Back = 4, State_Go = 5;//当前状态：0~3已注册
        //final常量
        public Direction direction;//方向
        //属性

        public static final int Final_AutoShake_Speed = 10;
        //final常量

        @Override
        public void Init() {
            super.Init();
            this.reset_init();
        }
        //初始数据

        @Override
        public void Operation() {
            super.Operation();
            this.Set_EndTime(this.Time_EndTime);

            if (this.CurrentState != this.BeforeState && this.CurrentState == State_Begin) {
                this.TempDegree = this.picture.CurrentDegree;
            }
            //初始化角度

            if (direction == Direction.Left_Up) {
                if (this.CurrentState == State_Begin || this.CurrentState == State_Go) {
                    this.TargetDegree = 0;
                    this.AngleSpeed = this.Speed_AngleBegin;
                    if (this.TempDegree >= this.TargetDegree) {
                        this.CurrentState = State_Back;
                    }
                }
                if (this.CurrentState == State_Back) {
                    this.TargetDegree = -45;
                    this.AngleSpeed = -this.Speed_AngleBegin;
                    if (this.TempDegree <= this.TargetDegree) {
                        this.CurrentState = State_Go;
                    }
                }
            } else if (direction == Direction.Right_Up) {
                if (this.CurrentState == State_Begin || this.CurrentState == State_Go) {
                    this.TargetDegree = 0;
                    this.AngleSpeed = -this.Speed_AngleBegin;
                    if (this.TempDegree <= this.TargetDegree) {
                        this.CurrentState = State_Back;
                    }
                }
                if (this.CurrentState == State_Back) {
                    this.TargetDegree = 45;
                    this.AngleSpeed = this.Speed_AngleBegin;
                    if (this.TempDegree >= this.TargetDegree) {
                        this.CurrentState = State_Go;
                    }
                }
            } else if (direction == Direction.Left_Down) {
                if (this.CurrentState == State_Begin || this.CurrentState == State_Go) {
                    this.TargetDegree = 90;
                    this.AngleSpeed = this.Speed_AngleBegin;
                    if (this.TempDegree >= this.TargetDegree) {
                        this.CurrentState = State_Back;
                    }
                }
                if (this.CurrentState == State_Back) {
                    this.TargetDegree = 45;
                    this.AngleSpeed = -this.Speed_AngleBegin;
                    if (this.TempDegree <= this.TargetDegree) {
                        this.CurrentState = State_Go;
                    }
                }
            } else if (direction == Direction.Right_Down) {
                if (this.CurrentState == State_Begin || this.CurrentState == State_Go) {
                    this.TargetDegree = -90;
                    this.AngleSpeed = -this.Speed_AngleBegin;
                    if (this.TempDegree <= this.TargetDegree) {
                        this.CurrentState = State_Back;
                    }
                }
                if (this.CurrentState == State_Back) {
                    this.TargetDegree = -45;
                    this.AngleSpeed = this.Speed_AngleBegin;
                    if (this.TempDegree >= this.TargetDegree) {
                        this.CurrentState = State_Go;
                    }
                }
            }

            this.Move();
            this.AngleMove();
            //移动

            this.Refresh();
            //刷新数据
        }
        //操作
        //注意，退出条件目前被取消了

        @Override
        protected void reset_init() {
            super.reset_init();
            this.CurrentState = State_Sleep;
            this.BeforeState = this.CurrentState;
            this.TempDegree = picture.CurrentDegree;
            //状态

            this.Speed_AngleBegin = Final_AutoShake_Speed;
            //变量
        }

        @Override
        public void Reset() {
            super.Reset();
            this.reset_init();

            if (direction == Direction.Left_Up || direction == Direction.Right_Down) {
                this.AngleSpeed = -Speed_AngleBegin;
                this.TargetDegree = picture.TargetDegree - this.GapDegree;
            } else if (direction == Direction.Right_Up || direction == Direction.Left_Down) {
                this.AngleSpeed = Speed_AngleBegin;
                this.TargetDegree = picture.TargetDegree + this.GapDegree;
            }
            //角速度 + 角度差距
        }
        //重置

        @Override
        public void Lock() {
            super.Lock();
            picture.eventLock.setDisplay_Conventional(true);//绘制
            picture.eventLock.setMouse_Click(true);//点击
            picture.eventLock.setMouse_Dragged(true);//拖拽
            picture.eventLock.setMouse_Pressed(true);//按下
            picture.eventLock.setMove_Angle(true);//角度跟随锁
            picture.eventLock.setMouse_Released(true);//不准释放
            picture.FollowSpeed = 1;
        }
        //上锁

        @Override
        public void Unlock() {
            super.Unlock();
            picture.eventLock.setDisplay_Conventional(false);//绘制
            picture.eventLock.setMouse_Click(false);//点击
            picture.eventLock.setMouse_Dragged(false);//拖拽
            picture.eventLock.setMouse_Pressed(false);//按下
            picture.eventLock.setMove_Angle(false);//角度跟随锁
            picture.eventLock.setMouse_Released(false);
            picture.FollowSpeed = Main.robot.FollowSpeed_Final;
        }
        //解锁

        @Override
        public void Begin() {
            super.Begin();
        }

        @Override
        public void End() {
            super.End();
            this.picture.CurrentDegree = this.TargetDegree;
        }
    }
    //摇耳朵：开心：有次数限制
    ;
    //------------------星星眼 + 开心耳------------------

    public static int FollowEyes_Relative_X = 301, FollowEyes_Relative_Y = 206;
    public static int FollowEyes_Relative_W = (785 / 2 - FollowEyes_Relative_X) * 2,
            FollowEyes_Relative_H = 291 - FollowEyes_Relative_Y;
    //跟随区域范围

    public static long Global_ActionDuration_EyesFollow =  5500L;

    public static ActionObject Global_EyesFollow;
    public static Action_EyesFollow[] Global_EyesFollow_Operation;
    //眼睛跟随

    public static class Action_EyesFollow extends ActionObject_Operation {
        public Action_EyesFollow(Picture p) {
            super(p);
        }
        //构造方法

        @Override
        public void Init() {
            super.Init();
            this.reset_init();
        }
        //初始数据

        public Picture FollowedObject;//此处应该自行赋值
        //跟踪过的对象

        @Override
        public void Operation() {
            super.Operation();
            if (this.Time_EndTime >= 0) {
                this.Set_EndTime(this.Time_EndTime);
            }
            if (this.CurrentState == State_Begin) {
                if (InterfaceButton.Global_Food != null) {
                    for (int i = 0; i < InterfaceButton.Food_Picture_Num; i++) {
                        if (InterfaceButton.Global_Food[i].Judge_EventRectChange) {//被拖动的
                            FollowedObject = InterfaceButton.Global_Food[i].picture;
                        }
                    }
                }
                //Follow选择
                int centre_x = FollowedObject.Dst.x + FollowedObject.Dst.width / 2;
                int centre_y = FollowedObject.Dst.y + FollowedObject.Dst.height / 2;
                this.Follow(centre_x, centre_y);
            }

            //移动

            this.Refresh();
            //刷新数据
        }

        public void Follow(int x, int y) {
            int BodyCentre_x = Main.robot.BasicBody.Dst.x + Main.robot.BasicBody.Dst.width / 2;
            int BodyCentre_y = (int) (Main.robot.BasicBody.Dst.y + Main.robot.Eyes_Relative_Y * Main.robot.Basic_Zoom);
            int Ratio = 5;
            int Gap_x = x - BodyCentre_x;
            int Gap_y = y - BodyCentre_y;
            int Gap_x_Ratio = Gap_x / Ratio;
            int Gap_y_Ratio = Gap_y / Ratio;
            int Boundary_Left = (int) (Main.robot.BasicBody.Dst.x + FollowEyes_Relative_X * Main.robot.Basic_Zoom);
            int Boundary_Right = (int) (Main.robot.BasicBody.Dst.x + (FollowEyes_Relative_X + FollowEyes_Relative_W) * Main.robot.Basic_Zoom);
            int Boundary_Up = (int) (Main.robot.BasicBody.Dst.y + FollowEyes_Relative_Y * Main.robot.Basic_Zoom);
            int Boundary_Down = (int) (Main.robot.BasicBody.Dst.y + (FollowEyes_Relative_Y + FollowEyes_Relative_H) * Main.robot.Basic_Zoom);
            int Offset_x, Offset_y;//偏移量
            if (Gap_x_Ratio >= 0) {
                Offset_x = Math.min(Gap_x_Ratio, Boundary_Right - BodyCentre_x);
            } else {
                Offset_x = Math.max(Gap_x_Ratio, Boundary_Left - BodyCentre_x);
            }
            if (Gap_y_Ratio >= 0) {
                Offset_y = Math.min(Gap_y_Ratio, Boundary_Down - BodyCentre_y);
            } else {
                Offset_y = Math.max(Gap_y_Ratio, Boundary_Up - BodyCentre_y);
            }
            //计算出偏移量

            int TempCentre_x = (int) (this.TempDst.xf + this.TempDst.wf / 2);//this.TempDst.xf的数值再Move中被改变了，如果不改变的话：将会偏移Body->不用Move
            int TempCentre_y = (int) (this.TempDst.yf + this.TempDst.hf / 2);
            int SpeedRatio = 4;
            this.Speed_Dst_x = Offset_x / (float) SpeedRatio;
            this.Speed_Dst_y = Offset_y / (float) SpeedRatio;
            if (this.Speed_Dst_x > 0) {
                if (this.Speed_Dst_x + TempCentre_x > Boundary_Right) {
                    this.Speed_Dst_x = Boundary_Right - TempCentre_x;
                }
            } else {
                if (this.Speed_Dst_x + TempCentre_x < Boundary_Left) {
                    this.Speed_Dst_x = Boundary_Left - TempCentre_x;
                }
            }
            if (this.Speed_Dst_y > 0) {
                if (this.Speed_Dst_y + TempCentre_y > Boundary_Down) {
                    this.Speed_Dst_y = Boundary_Down - TempCentre_y;
                }
            } else {
                if (this.Speed_Dst_y + TempCentre_y < Boundary_Up) {
                    this.Speed_Dst_y = Boundary_Up - TempCentre_y;
                }
            }
            //速度设置

            this.Temp_Dst_x += this.Speed_Dst_x;
            this.TempDst.xf = this.picture.Dst.x + this.Temp_Dst_x;
            this.Temp_Dst_y += this.Speed_Dst_y;
            this.TempDst.yf = this.picture.Dst.y + this.Temp_Dst_y;
            //移动

            if (this.TempDst.xf + this.TempDst.wf / 2 < Boundary_Left) {
                this.TempDst.xf = Boundary_Left - this.TempDst.wf / 2;
            } else if (this.TempDst.xf + this.TempDst.wf / 2 > Boundary_Right) {
                this.TempDst.xf = Boundary_Right - this.TempDst.wf / 2;
            }
            if (this.TempDst.yf + this.TempDst.hf / 2 < Boundary_Up) {
                this.TempDst.yf = Boundary_Up - this.TempDst.hf / 2;
            } else if (this.TempDst.yf + this.TempDst.hf / 2 > Boundary_Down) {
                this.TempDst.yf = Boundary_Down - this.TempDst.hf / 2;
            }
            //边界调正
        }
        //跟随函数：返回眼睛现在应该再窗口中的位置

        @Override
        protected void reset_init() {
            super.reset_init();

            this.CurrentState = State_Sleep;
            this.BeforeState = this.CurrentState;
            //状态
            this.TempDst = new Display.Rect((float) this.picture.Dst.x, (float) this.picture.Dst.y, (float) this.picture.Dst.width, (float) this.picture.Dst.height);
            this.Speed_Dst_x = 0;
            this.Speed_Dst_y = 0;
            //速度
        }

        @Override
        public void Reset() {
            super.Reset();
            this.reset_init();
        }

        @Override
        public void Lock() {
            super.Lock();
            picture.eventLock.setDisplay_Conventional(true);
            picture.eventLock.setMouse_Click(true);
            Main.robot.BasicEyes.eventLock.setDisplay_Conventional(true);//通用绘制解锁
        }

        @Override
        public void Unlock() {
            super.Unlock();
            picture.eventLock.setDisplay_Conventional(false);
            picture.eventLock.setMouse_Click(false);
            Main.robot.BasicEyes.eventLock.setDisplay_Conventional(false);//通用绘制解锁
        }

    }
    //眼睛跟随
    ;
    //------------------眼睛跟随------------------

    public static ActionObject Global_DoubtBeforeFollow;
    public static Action_Doubt[] Global_DoubtBeforeFollow_Operation;

    //------------------跟随后的问号------------------

    public static ActionObject Global_SquintStruggle;
    public static ActionObject Global_ShakeEar_Struggle;
    public static ActionObject Global_SquintStruggle1;
    public static ActionObject Global_ShakeEar_Struggle1;
    public static Action_SquintStruggle[] Global_SquintStruggle_Operation;
    public static Action_ShakeEar_Struggle[] Global_ShakeEar_Struggle_Operation;
    public static Action_SquintStruggle[] Global_SquintStruggle_Operation1;
    public static Action_ShakeEar_Struggle[] Global_ShakeEar_Struggle_Operation1;

    public static long Global_ActionDuration_Struggle = (long) (3.0 * 1000L);

    public static class Action_SquintStruggle extends ActionObject_Operation {
        public Action_SquintStruggle(Picture picture) {
            super(picture);
            this.Init();
        }

        public boolean Lock_MouseDragged;//上锁：MouseDragged::Pressed_GetPoint  解锁：AllUnlock_MouseReleased
        //鼠标拖拽锁
        public float Bound_x;
        //边界位置：相对于初始图片(根据Basic_Zoom比例变换)
        public Direction direction, Before_direction;
        //当前朝向

        //------------------属性------------------

        public static final int Speed_final = 14, Bound_x_final = 45;

        //------------------final常量------------------

        @Override
        public void Init() {
            super.Init();
            this.reset_init();
            this.TempDst = new Display.Rect((float) picture.Dst.x,
                    (float) picture.Dst.y,
                    (float) picture.Dst.width,
                    (float) picture.Dst.height);
        }
        //初始化函数

        @Override
        public void reset_init() {
            super.reset_init();
            this.direction = Direction.Left;
            this.Before_direction = this.direction;
            this.Speed_Dst_x = (float) (-Speed_final * Main.robot.Basic_Zoom);
            this.Bound_x = (float) (-Bound_x_final * Main.robot.Basic_Zoom);
            //根据比例变换
            this.Lock_MouseDragged = false;
            this.Temp_Dst_x = 0;//初始的距离差距未0
        }
        //初始化和重置的重复函数

        @Override
        public void Reset() {
            super.Reset();
            this.reset_init();
        }
        //重置函数：在Dragged里面的Release进行重置

        @Override
        public void Operation() {
            super.Operation();
            this.Set_EndTime(this.Time_EndTime);
            if (this.direction == Direction.Left) {
                if (this.Speed_Dst_x + this.Temp_Dst_x > this.Bound_x) {//负数
                    this.Temp_Dst_x += this.Speed_Dst_x;
                } else {
                    this.Temp_Dst_x = this.Bound_x;
                    this.direction = Direction.Right;
                    //切换方向
                    this.Bound_x = -this.Bound_x;
                    this.Speed_Dst_x = -this.Speed_Dst_x;
                    //速度，边界切换
                }
                //方向切换判定
            }
            if (this.direction == Direction.Right) {
                if (this.Speed_Dst_x + this.Temp_Dst_x < this.Bound_x) {//正数
                    this.Temp_Dst_x += this.Speed_Dst_x;
                } else {
                    this.Temp_Dst_x = this.Bound_x;
                    this.direction = Direction.Left;
                    //切换方向
                    this.Bound_x = -this.Bound_x;
                    this.Speed_Dst_x = -this.Speed_Dst_x;
                    //速度，边界切换
                }
                //方向切换判定
            }
            //数据处理

            this.Move();
            //移动

            this.Refresh();
            //重置
        }
        //操作函数

        @Override
        public void Refresh() {
            super.Refresh();
        }
        //重置

        @Override
        public void Lock() {
            super.Lock();
            this.Lock_MouseDragged = true;
        }
        //上锁

        @Override
        public void Unlock() {
            super.Unlock();
            this.Lock_MouseDragged = false;
            this.Reset();
            this.CurrentState = ActionObject_Operation.State_Sleep;//状态为退出
        }
        //解锁：特殊：根据Lock重置与退出

    }

    //眯眼
    public static class Action_ShakeEar_Struggle extends ActionObject_Operation {
        public Action_ShakeEar_Struggle(Picture p, Direction direction) {
            super(p);
            this.direction = direction;
            this.Reset();
        }
        //构造方法

        public static final int State_Back = 4, State_Go = 5;//当前状态：0~3已注册
        //final常量
        public Direction direction;//方向
        //属性

        public static final int Final_AutoShake_Speed = 10;
        //final常量

        @Override
        public void Init() {
            super.Init();
            this.reset_init();
        }
        //初始数据

        @Override
        public void Operation() {
            super.Operation();
            this.Set_EndTime(this.Time_EndTime);

            if (this.CurrentState != this.BeforeState && this.CurrentState == State_Begin) {
                this.TempDegree = this.picture.CurrentDegree;
            }
            //初始化角度

            if (direction == Direction.Left_Up) {
                if (this.CurrentState == State_Begin || this.CurrentState == State_Go) {
                    this.TargetDegree = -45;
                    this.AngleSpeed = this.Speed_AngleBegin;
                    if (this.TempDegree >= this.TargetDegree) {
                        this.CurrentState = State_Back;
                    }
                }
                if (this.CurrentState == State_Back) {
                    this.TargetDegree = -90;
                    this.AngleSpeed = -this.Speed_AngleBegin;
                    if (this.TempDegree <= this.TargetDegree) {
                        this.CurrentState = State_Go;
                    }
                }
            } else if (direction == Direction.Right_Up) {
                if (this.CurrentState == State_Begin || this.CurrentState == State_Go) {
                    this.TargetDegree = 45;
                    this.AngleSpeed = -this.Speed_AngleBegin;
                    if (this.TempDegree <= this.TargetDegree) {
                        this.CurrentState = State_Back;
                    }
                }
                if (this.CurrentState == State_Back) {
                    this.TargetDegree = 90;
                    this.AngleSpeed = this.Speed_AngleBegin;
                    if (this.TempDegree >= this.TargetDegree) {
                        this.CurrentState = State_Go;
                    }
                }
            } else if (direction == Direction.Left_Down) {
                if (this.CurrentState == State_Begin || this.CurrentState == State_Go) {
                    this.TargetDegree = 90;
                    this.AngleSpeed = this.Speed_AngleBegin;
                    if (this.TempDegree >= this.TargetDegree) {
                        this.CurrentState = State_Back;
                    }
                }
                if (this.CurrentState == State_Back) {
                    this.TargetDegree = 45;
                    this.AngleSpeed = -this.Speed_AngleBegin;
                    if (this.TempDegree <= this.TargetDegree) {
                        this.CurrentState = State_Go;
                    }
                }
            } else if (direction == Direction.Right_Down) {
                if (this.CurrentState == State_Begin || this.CurrentState == State_Go) {
                    this.TargetDegree = -90;
                    this.AngleSpeed = -this.Speed_AngleBegin;
                    if (this.TempDegree <= this.TargetDegree) {
                        this.CurrentState = State_Back;
                    }
                }
                if (this.CurrentState == State_Back) {
                    this.TargetDegree = -45;
                    this.AngleSpeed = this.Speed_AngleBegin;
                    if (this.TempDegree >= this.TargetDegree) {
                        this.CurrentState = State_Go;
                    }
                }
            }

            this.Move();
            this.AngleMove();
            //移动

            this.Refresh();
            //刷新数据
        }
        //操作
        //注意，退出条件目前被取消了

        @Override
        protected void reset_init() {
            super.reset_init();
            this.CurrentState = State_Sleep;
            this.BeforeState = this.CurrentState;
            this.TempDegree = picture.CurrentDegree;
            //状态

            this.Speed_AngleBegin = Final_AutoShake_Speed;
            //变量
        }

        @Override
        public void Reset() {
            super.Reset();
            this.reset_init();

            if (direction == Direction.Left_Up || direction == Direction.Right_Down) {
                this.AngleSpeed = -Speed_AngleBegin;
                this.TargetDegree = picture.TargetDegree - this.GapDegree;
            } else if (direction == Direction.Right_Up || direction == Direction.Left_Down) {
                this.AngleSpeed = Speed_AngleBegin;
                this.TargetDegree = picture.TargetDegree + this.GapDegree;
            }
            //角速度 + 角度差距
        }
        //重置

        @Override
        public void Lock() {
            super.Lock();
            picture.eventLock.setDisplay_Conventional(true);//绘制
            picture.eventLock.setMouse_Click(true);//点击
            picture.eventLock.setMouse_Dragged(true);//拖拽
            picture.eventLock.setMouse_Pressed(true);//按下
            picture.eventLock.setMove_Angle(true);//角度跟随锁
            picture.eventLock.setMouse_Released(true);//不准释放
            picture.FollowSpeed = 1;
        }
        //上锁

        @Override
        public void Unlock() {
            super.Unlock();
            picture.eventLock.setDisplay_Conventional(false);//绘制
            picture.eventLock.setMouse_Click(false);//点击
            picture.eventLock.setMouse_Dragged(false);//拖拽
            picture.eventLock.setMouse_Pressed(false);//按下
            picture.eventLock.setMove_Angle(false);//角度跟随锁
            picture.eventLock.setMouse_Released(false);
            picture.FollowSpeed = Main.robot.FollowSpeed_Final;
        }
        //解锁

        @Override
        public void Begin() {
            super.Begin();
        }

        @Override
        public void End() {
            super.End();
            this.picture.CurrentDegree = this.TargetDegree;
        }
    }
    //摇耳朵：挣扎

    //------------------眯眼+挣扎------------------
    public static long Global_ActionDuration_Angry = (long) (2.5 * 1000L);

    public static ActionObject Global_Angry;
    public static Action_Angry[] Global_Angry_Operation;

    public static class Action_Angry extends ActionObject_Operation {
        public Action_Angry(Picture picture) {
            super(picture);
            this.Init();
        }

        //边界位置：相对于初始图片(根据Basic_Zoom比例变换)

        //------------------属性------------------


        //------------------final常量------------------

        @Override
        public void Init() {
            super.Init();
            this.reset_init();
            this.TempDst = new Display.Rect((float) picture.Dst.x,
                    (float) picture.Dst.y,
                    (float) picture.Dst.width,
                    (float) picture.Dst.height);
        }
        //初始化函数

        @Override
        public void reset_init() {
            super.reset_init();
            //根据比例变换
            this.Temp_Dst_x = 0;//初始的距离差距未0
        }
        //初始化和重置的重复函数

        @Override
        public void Reset() {
            super.Reset();
            this.reset_init();
        }
        //重置函数：在Dragged里面的Release进行重置

        @Override
        public void Operation() {
            super.Operation();
            this.Set_EndTime(this.Time_EndTime);

            //数据处理

            this.Move();
            //移动

            this.Refresh();
            //重置
        }
        //操作函数

        @Override
        public void Refresh() {
            super.Refresh();
        }
        //重置

        @Override
        public void Lock() {
            super.Lock();
            Main.robot.BasicEyes.eventLock.setDisplay_Conventional(true);//通用绘制解锁
        }
        //上锁

        @Override
        public void Unlock() {
            super.Unlock();
            this.Reset();
            Main.robot.BasicEyes.eventLock.setDisplay_Conventional(false);//通用绘制解锁
            this.CurrentState = ActionObject_Operation.State_Sleep;//状态为退出
        }
        //解锁：特殊：根据Lock重置与退出

    }

    //------------------生气------------------

    public static long Global_ActionDuration_Sad = (long) (2.5 * 1000L);
    public static ActionObject Global_Sad;
    public static ActionObject Global_SadEar;
    public static Action_Sad[] Global_Sad_Operation;
    public static Action_SadEar[] Global_SadEar_Operation;

    public static class Action_Sad extends ActionObject_Operation {

        public Action_Sad(Picture picture) {
            super(picture);
            this.Init();
        }

        //边界位置：相对于初始图片(根据Basic_Zoom比例变换)

        //------------------属性------------------


        //------------------final常量------------------

        @Override
        public void Init() {
            super.Init();
            this.reset_init();
            this.TempDst = new Display.Rect((float) picture.Dst.x,
                    (float) picture.Dst.y,
                    (float) picture.Dst.width,
                    (float) picture.Dst.height);
        }
        //初始化函数

        @Override
        public void reset_init() {
            super.reset_init();
            //根据比例变换
            this.Temp_Dst_x = 0;//初始的距离差距未0
        }
        //初始化和重置的重复函数

        @Override
        public void Reset() {
            super.Reset();
            this.reset_init();
        }
        //重置函数：在Dragged里面的Release进行重置

        @Override
        public void Operation() {
            super.Operation();
            this.Set_EndTime(this.Time_EndTime);

            //数据处理

            this.Move();
            //移动

            this.Refresh();
            //重置
        }
        //操作函数

        @Override
        public void Refresh() {
            super.Refresh();
        }
        //重置

        @Override
        public void Lock() {
            super.Lock();
        }
        //上锁

        @Override
        public void Unlock() {
            super.Unlock();
            this.Reset();
            this.CurrentState = ActionObject_Operation.State_Sleep;//状态为退出
        }
        //解锁：特殊：根据Lock重置与退出

    }

    public static class Action_SadEar extends ActionObject_Operation {
        public Action_SadEar(Picture p, Direction direction) {
            super(p);
            this.direction = direction;
            this.Reset();
        }
        //构造方法

        //final常量
        public Direction direction;//方向
        //属性

        public static final int Final_AutoShake_Speed = 13;
        //final常量

        @Override
        public void Init() {
            super.Init();
            this.reset_init();
        }
        //初始数据

        @Override
        public void Operation() {
            super.Operation();
            if (this.Time_EndTime >= 0) {
                this.Set_EndTime(this.Time_EndTime);
            }
            if (this.CurrentState != this.BeforeState && this.CurrentState == State_Begin) {
                this.TempDegree = this.picture.CurrentDegree;
            }
            //初始化角度

            if (direction == Direction.Left_Up) {
                if (this.CurrentState == State_Begin) {
                    this.TargetDegree = -25 - 90;
                    this.AngleSpeed = -this.Speed_AngleBegin;
                    if (this.TempDegree <= this.TargetDegree) {
                        this.TempDegree = this.TargetDegree;
                        this.AngleSpeed = 0;
                    }
                }
            } else if (direction == Direction.Right_Up) {
                if (this.CurrentState == State_Begin) {
                    this.TargetDegree = 25 + 90;
                    this.AngleSpeed = this.Speed_AngleBegin;
                    if (this.TempDegree >= this.TargetDegree) {
                        this.TempDegree = this.TargetDegree;
                        this.AngleSpeed = 0;
                    }
                }
            } else if (direction == Direction.Left_Down) {
                if (this.CurrentState == State_Begin) {
                    this.TargetDegree = 0;
                    this.AngleSpeed = -this.Speed_AngleBegin;
                    if (this.TempDegree <= this.TargetDegree) {
                        this.TempDegree = this.TargetDegree;
                        this.AngleSpeed = 0;
                    }
                }
            } else if (direction == Direction.Right_Down) {
                if (this.CurrentState == State_Begin) {
                    this.TargetDegree = 0;
                    this.AngleSpeed = this.Speed_AngleBegin;
                    if (this.TempDegree >= this.TargetDegree) {
                        this.TempDegree = this.TargetDegree;
                        this.AngleSpeed = 0;
                    }
                }
            }

            this.Move();
            this.AngleMove();
            //移动

            this.Refresh();
            //刷新数据
        }
        //操作
        //注意，退出条件目前被取消了


        @Override
        public void AngleMove() {
            if (this.AngleSpeed > 0) {
                if (this.AngleSpeed + this.TempDegree < this.TargetDegree) {
                    this.TempDegree += this.AngleSpeed;
                } else {
                    this.TempDegree = this.TargetDegree;
                }
            } else {
                if (this.AngleSpeed + this.TempDegree > this.TargetDegree) {
                    this.TempDegree += this.AngleSpeed;
                } else {
                    this.TempDegree = this.TargetDegree;
                }
            }
        }

        @Override
        protected void reset_init() {
            super.reset_init();
            this.CurrentState = State_Sleep;
            this.BeforeState = this.CurrentState;
            this.TempDegree = picture.CurrentDegree;
            //状态

            this.Speed_AngleBegin = Final_AutoShake_Speed;
            //变量
        }

        @Override
        public void Reset() {
            super.Reset();
            this.reset_init();

            if (direction == Direction.Left_Up || direction == Direction.Right_Down) {
                this.AngleSpeed = -Speed_AngleBegin;
                this.TargetDegree = picture.TargetDegree - this.GapDegree;
            } else if (direction == Direction.Right_Up || direction == Direction.Left_Down) {
                this.AngleSpeed = Speed_AngleBegin;
                this.TargetDegree = picture.TargetDegree + this.GapDegree;
            }
            //角速度 + 角度差距
        }
        //重置

        @Override
        public void Lock() {
            super.Lock();
            picture.eventLock.setDisplay_Conventional(true);//绘制
            picture.eventLock.setMouse_Click(true);//点击
            picture.eventLock.setMouse_Dragged(true);//拖拽
            picture.eventLock.setMouse_Pressed(true);//按下
            picture.eventLock.setMove_Angle(true);//角度跟随锁
            picture.eventLock.setMouse_Released(true);//不准释放
            picture.FollowSpeed = 1;
        }
        //上锁

        @Override
        public void Unlock() {
            super.Unlock();
            picture.eventLock.setDisplay_Conventional(false);//绘制
            picture.eventLock.setMouse_Click(false);//点击
            picture.eventLock.setMouse_Dragged(false);//拖拽
            picture.eventLock.setMouse_Pressed(false);//按下
            picture.eventLock.setMove_Angle(false);//角度跟随锁
            picture.eventLock.setMouse_Released(false);
            picture.FollowSpeed = Main.robot.FollowSpeed_Final;
        }
        //解锁

        @Override
        public void Begin() {
            super.Begin();
        }

        @Override
        public void End() {
            super.End();
            this.picture.CurrentDegree = this.TargetDegree;
        }
    }

    //------------------伤心------------------

    public static ActionObject Global_GrievanceSquint;
    public static Action_SquintStruggle[] Global_GrievanceSquint_Operation;
    public static ActionObject Global_ShakeEar_Struggle2;
    public static Action_ShakeEar_Struggle[] Global_ShakeEar_Struggle_Operation2;

    //------------------委屈1------------------

    public static long Global_ActionDuration_GrievanceAngry = (long) (3.5 * 1000L);
    public static ActionObject Global_GrievanceAngry;
    public static Action_Angry[] Global_GrievanceAngry_Operation;

    //------------------委屈2------------------

    public static ActionObject Global_GrievanceSquint2;
    public static Action_SquintStruggle[] Global_GrievanceSquint_Operation2;
    public static ActionObject Global_ShakeEar_Struggle3;
    public static Action_ShakeEar_Struggle[] Global_ShakeEar_Struggle_Operation3;

    //------------------挣扎2------------------

    public static ActionObject Global_SadEyesFollow;
    public static Action_EyesFollow[] Global_SadEyesFollow_Operation;
    public static ActionObject Global_SadEar2;
    public static Action_SadEar[] Global_SadEar_Operation2;

    //------------------委屈3------------------

    public static ActionObject Global_LoveEye;
    public static Action_StarEyes[] Global_LoveEye_Operation;
    public static ActionObject Global_ShakeEarHappy2;
    public static ActionShakeEarHappy[] Global_ShakeEarHappy_Operation2;

    //------------------吃到开心------------------

    public static ActionObject Global_EatenHappy;
    public static Action_EatenHappy[] Global_EatenHappy_Operation;

    public static class Action_EatenHappy extends ActionObject_Operation {
        public Action_EatenHappy(Picture picture) {
            super(picture);
            this.Init();
        }

        //边界位置：相对于初始图片(根据Basic_Zoom比例变换)

        public boolean HappyChange;
        public boolean FoodFollow;

        //------------------属性------------------


        //------------------final常量------------------


        //------------------特殊功能------------------

        @Override
        public void Init() {
            super.Init();
            this.reset_init();
            this.TempDst = new Display.Rect((float) picture.Dst.x,
                    (float) picture.Dst.y,
                    (float) picture.Dst.width,
                    (float) picture.Dst.height);
        }
        //初始化函数

        @Override
        public void reset_init() {
            super.reset_init();
            this.HappyChange = false;
            this.FoodFollow = false;
            //根据比例变换
            this.Temp_Dst_x = 0;//初始的距离差距未0
        }
        //初始化和重置的重复函数

        @Override
        public void Reset() {
            super.Reset();
            this.reset_init();
        }
        //重置函数：在Dragged里面的Release进行重置

        @Override
        public void Operation() {
            super.Operation();
            if (this.HappyChange) {
                this.End();
            }

            //数据处理

            this.Move();
            //移动

            this.Refresh();
            //重置
        }
        //操作函数

        @Override
        public void Refresh() {
            super.Refresh();
        }
        //重置

        @Override
        public void Lock() {
            super.Lock();
            this.FoodFollow = true;
        }
        //上锁

        @Override
        public void Unlock() {
            super.Unlock();
            this.Reset();
            this.CurrentState = ActionObject_Operation.State_Sleep;//状态为退出
            ActionTool.Global_Lock_FoodAppeared = false;
            ActionTool.Global_Lock_FoodEaten = false;
            this.FoodFollow = false;
        }
        //解锁：特殊：根据Lock重置与退出

    }

    //------------------最终吃类------------------

    public static ActionObject Global_FoodFollow;
    public static Action_FoodFollow[] Global_FoodFollow_Operation;

    public static class Action_FoodFollow extends ActionObject_Operation {
        public Action_FoodFollow(Picture p) {
            super(p);
        }
        //构造方法
        ;
        //final常量

        public InterfaceButton.Food food;//场外初始化

        @Override
        public void Init() {
            super.Init();
            this.reset_init();
        }
        //初始数据

        @Override
        public void Operation() {
            super.Operation();
            if (this.CurrentState == State_Begin && this.BeforeState != this.CurrentState) {
                this.TempDst.xf = this.picture.Dst.x;
                this.TempDst.yf = this.picture.Dst.y;
            }


            this.Follow();
            //移动

            this.Refresh();
            //刷新数据
        }

        private void Follow() {
            int Centre_x = Main.robot.BasicHatch[1].Dst.x + Main.robot.BasicHatch[1].Dst.width / 2;
            int Centre_y = Main.robot.BasicHatch[1].Dst.y + Main.robot.BasicHatch[1].Dst.height / 2;
            this.Temp_Centre_x = (int) (this.TempDst.xf + this.TempDst.wf / 2);
            this.Temp_Centre_y = (int) (this.TempDst.yf + this.TempDst.hf / 2);
            int Gap_x = this.Temp_Centre_x - Centre_x;
            int Gap_y = this.Temp_Centre_y - Centre_y;
            float ratio = 6;
            this.Speed_Dst_x = -(float) Gap_x / ratio;
            this.Speed_Dst_y = -(float) Gap_y / ratio;
            this.Move_Dst_x();
            this.Move_Dst_y();
            if (this.Speed_Dst_x == 0 && this.Speed_Dst_y == 0) {
                this.food.Judge_DisplayPicture = false;
                this.End();
            }
        }

        @Override
        protected void reset_init() {
            super.reset_init();

            this.CurrentState = State_Sleep;
            this.BeforeState = this.CurrentState;
            //状态
            float zoom = 0.7F;
            this.TempDst = new Display.Rect((float) this.picture.Dst.x, (float) this.picture.Dst.y, (float) this.picture.Dst.width * zoom, (float) this.picture.Dst.height * zoom);

            //速度
            this.ImmediatelyEndCondition = false;
            //boolean条件
        }

        @Override
        public void Reset() {
            super.Reset();
            this.reset_init();
        }

        @Override
        public void Lock() {
            super.Lock();
            picture.eventLock.setDisplay_Conventional(true);
            picture.eventLock.setMouse_Click(true);
            Main.robot.BasicEyes.eventLock.setDisplay_Conventional(true);//通用绘制解锁
            for (InterfaceButton.Food f : InterfaceButton.Global_Food) {
                f.picture.eventLock.setOnce_Display(true);//绘制过一次
                f.picture.eventLock.setDisplay_Conventional(true);//常规绘制上锁
                f.picture.eventLock.setMouse_Pressed(true);
                f.picture.eventLock.setMouse_Click(true);
                f.picture.eventLock.setMouse_Dragged(true);
                f.picture.eventLock.setMouse_Move(true);
                f.picture.eventLock.setMouse_Released(true);
                f.picture.eventLock.setAllMouse_Lock(true);
            }
        }

        @Override
        public void Unlock() {
            super.Unlock();
            picture.eventLock.setDisplay_Conventional(false);
            picture.eventLock.setMouse_Click(false);
            //解锁
            Eaten_End_UnlockALL();
            //全部解锁
        }

        @Override
        public void Begin() {
            if (this.food.Eaten) {
                if (this.CurrentState == State_Sleep) {//当前是休眠变为申请
                    this.CurrentState = State_Applying;
                }
            }
        }
    }

    public static void Eaten_End_UnlockALL() {
        Main.robot.BasicEyes.eventLock.setDisplay_Conventional(false);//通用绘制解锁
        Global_EatenHappy_Operation[0].FoodFollow = false;
        for (InterfaceButton.Food f : InterfaceButton.Global_Food) {
            f.picture.eventLock.setOnce_Display(false);//绘制过一次
            f.picture.eventLock.setDisplay_Conventional(false);//常规绘制上锁
            f.picture.eventLock.setMouse_Pressed(false);
            f.picture.eventLock.setMouse_Click(false);
            f.picture.eventLock.setMouse_Dragged(false);
            f.picture.eventLock.setMouse_Move(false);
            f.picture.eventLock.setMouse_Released(false);
            f.picture.eventLock.setAllMouse_Lock(false);
            f.Judge_DisplayPicture = false;
            f.Eaten = false;
            f.Judge_EventRectChange = false;
        }
        int Current_x = Main.robot.BasicBody.Dst.x;
        int Current_y = Main.robot.BasicBody.Dst.y;

        InterfaceButton.Global_Food = new InterfaceButton.Food[InterfaceButton.Food_Picture_Num];
        for (int i = 0; i < InterfaceButton.Food_Picture_Num; i++) {
            InterfaceButton.Global_Food[i] = new InterfaceButton.Food(new Picture());
        }
        //初始化Food，以便读取数据
        Main.userData.Get_readData(Main.userData.readData());//数据获取
        //用户数据
        ThreadControl.Global_ThreadInit();
        //线程初始化
        Global_FishRoeAction_Tool = new ActionTool();//申请ActionTool
        //ActionTool.Global_ActionTool_Init();
        //初始化动作对象管理工具
        Main.robot = new Robot();
        Main.robot.Init_AfterSetUp2(Current_x, Current_y);
        //鱼籽对象初始化
        InterfaceButton.Global_Interface_Button_Data_Init();
        //界面，按钮对象初始化
        //------------------最先------------------
        globalInitActionOperation();
        //------------------动作Operation------------------
        globalInitActionObjectRegister();
        //------------------操作注册------------------
        //--------------------------------内存初始化--------------------------------------
        Robot.Global_Blink.CurrentState = ActionObject.State_Applying;
        //----------------数据状态初始化----------------
        globalInitEventListen();
        //----------------事件添加----------------
        globalInitSetTimer();
        //----------------Timer_Timing----------------
    }
    //全部解锁      //特别注意，初始化改了之后需要该另外两个地方：Global_ResetFirst()；Eaten_End_UnlockALL()
    ;
    //------------------食物跟随------------------
    public static ActionObject Global_TriggerHappy;
    public static Action_Angry[] Global_TriggerHappy_Operation;
    //------------------触发表情：开心------------------
    public static ActionObject Global_TriggerSad;
    public static Action_Angry[] Global_TriggerSad_Operation;
    //------------------触发表情：无语------------------
    public static ActionObject Global_TriggerAngry;
    public static Action_Angry[] Global_TriggerAngry_Operation;
    //------------------触发表情：伤心------------------
    public static ActionObject Global_TriggerXX;
    public static Action_Angry[] Global_TriggerXX_Operation;
    //------------------触发表情：叉叉眼睛------------------
    public static ActionObject Global_TriggerDoubt;
    public static Action_Doubt[] Global_TriggerDoubt_Operation;
    //------------------触发表情：疑惑眼睛------------------
    public static ActionObject Global_TriggerSquintStruggle;
    public static Action_SquintStruggle[] Global_TriggerSquintStruggle_Operation;
    //------------------触发表情：挣扎------------------
    public static ActionObject Global_TriggerGrievanceSquintStruggle;
    public static Action_SquintStruggle[] Global_TriggerGrievanceSquintStruggle_Operation;
    //------------------触发表情：委屈挣扎------------------
    public static ActionObject Global_TriggerGrievanceAngry;
    public static Action_Angry[] Global_TriggerGrievanceAngry_Operation;
    //------------------触发表情：委屈生气------------------
    public static ActionObject Global_TriggerGrievanceSad;
    public static Action_Angry[] Global_TriggerGrievanceSad_Operation;
    //------------------触发表情：委屈伤心------------------
    public static long Global_ActionDuration_TriggerGrievanceSadEar = (long) (5.0 * 1000L);
    public static ActionObject Global_TriggerGrievanceSadEar;
    public static Action_SadEar[] Global_TriggerGrievanceSadEar_Operation;
    //------------------触发表情：伤心耳朵------------------

    public static ActionObject Global_BasicBodyMove;
    public static Action_BasicBodyMove[] Global_BasicBodyMove_Operation;

    public static class Action_BasicBodyMove extends ActionObject_Operation {
        public Action_BasicBodyMove(Picture p) {
            super(p);
        }
        //构造方法

        @Override
        public void Init() {
            super.Init();
            this.reset_init();
        }
        //初始数据


        public final int State_Back = 4;
        public final int Type_UpDown = 0, Type_LeftRight = 1;
        //Final
        public int TotalCount, CurrentCount;
        public int Type;
        int speed = 10;
        int Gap = 50;
        private int Begin_x, Begin_y;
        //属性

        @Override
        public void Operation() {
            super.Operation();
            if (this.CurrentCount >= this.TotalCount) {
                this.CurrentState = State_Exit;
            }
            //退出条件
            if (this.CurrentState == State_Begin) {
                if (this.Type == Type_UpDown) {
                    this.Speed_Dst_y = -speed;
                    if (this.picture.Dst.y <= this.Begin_y - this.Gap) {
                        this.Begin_y = this.picture.Dst.y;
                        this.CurrentState = State_Back;
                    }
                } else if (this.Type == Type_LeftRight) {
                    this.Speed_Dst_x = -speed;
                    if (this.picture.Dst.x <= this.Begin_x - this.Gap) {
                        this.Begin_x = this.picture.Dst.x;
                        this.CurrentState = State_Back;
                    }
                }
            }
            if (this.CurrentState == State_Back) {
                if (this.Type == Type_UpDown) {
                    this.Speed_Dst_y = speed;
                    if (this.picture.Dst.y >= this.Begin_y + this.Gap) {
                        this.Begin_y = this.picture.Dst.y;
                        this.CurrentState = State_Begin;
                        CurrentCount++;
                    }
                } else if (this.Type == Type_LeftRight) {
                    this.Speed_Dst_x = speed;
                    if (this.picture.Dst.x >= this.Begin_x + this.Gap) {
                        this.Begin_x = this.picture.Dst.x;
                        this.CurrentState = State_Begin;
                        CurrentCount++;
                    }
                }
            }

            if (this.CurrentState == State_Begin || this.CurrentState == State_Back) {
                this.Move();
            }
            //移动

            this.Refresh();
            //刷新数据
        }

        @Override
        public void Move() {
            Main.robot.Move_BasicBody((int) this.Speed_Dst_x, (int) this.Speed_Dst_y);
        }

        @Override
        protected void reset_init() {
            super.reset_init();

            this.CurrentState = State_Sleep;
            this.BeforeState = this.CurrentState;
            //图片
            this.Begin_x = this.picture.Dst.x;
            this.Begin_y = this.picture.Dst.y;
            //位置数据
            int type = ActionTool.Random.Get_Int_SetRange(0, 2);
            if (type == 0) {
                this.Type = Type_UpDown;
            } else {
                this.Type = Type_LeftRight;
            }
            //类型
            this.TotalCount = ActionTool.Random.Get_Int_SetRange(2, 5);
            this.CurrentCount = 0;
            //计数器
        }

        @Override
        public void Reset() {
            super.Reset();
            this.reset_init();
        }

        @Override
        public void Lock() {
            super.Lock();
            Main.robot.AngleMoveRate = 2000.0;
        }

        @Override
        public void Unlock() {
            super.Unlock();
            Main.robot.AngleMoveRate = 2.0;
        }

    }
    //本体移动
    ;
    //------------------移动方法，耳朵根据移动跟随------------------
    public static long Global_ActionDuration_HappyShakeEar_Simple = (long) (2.5 * 1000L);
    public static ActionObject Global_HappyShakeEar_Simple;
    public static ActionShakeEarHappy[] Global_HappyShakeEar_Simple_Operation;
    //------------------开心摇耳朵------------------
    public static ActionObject Global_StruggleShakeEar_Simple;
    public static Action_ShakeEar_Struggle[] Global_StruggleShakeEar_Simple_Operation;
    //------------------伤心摇耳朵------------------
    public static long Global_ActionDuration_EyesFollow2 = (long) (10.0 * 1000L);
    public static ActionObject Global_LookingForScreen;
    public static ActionObject Global_LookingForScreen_Sad;
    public static ActionObject Global_LookingForScreen_Angry;
    public static ActionObject Global_LookingForScreen_GrievanceSad;
    public static ActionObject Global_LookingForScreen_GrievanceAngry;
    public static Action_LookingForScreen[] Global_LookingForScreen_Operation;
    public static Action_LookingForScreen[] Global_LookingForScreen_Sad_Operation;
    public static Action_LookingForScreen[] Global_LookingForScreen_Angry_Operation;
    public static Action_LookingForScreen[] Global_LookingForScreen_GrievanceSad_Operation;
    public static Action_LookingForScreen[] Global_LookingForScreen_GrievanceAngry_Operation;

    public static class Action_LookingForScreen extends ActionObject_Operation {
        public Action_LookingForScreen(Picture p) {
            super(p);
        }
        //构造方法

        @Override
        public void Init() {
            super.Init();
            this.reset_init();
        }
        //初始数据

        int centre_x;
        int centre_y;
        //跟踪过的对象

        @Override
        public void Operation() {
            super.Operation();
            if (this.Time_EndTime >= 0) {
                this.Set_EndTime(this.Time_EndTime);
            }
            if (this.CurrentState == State_Begin && this.BeforeState != this.CurrentState) {
                int range = 250;
                //范围
                int point_x, point_y;
                int Point_x = ActionTool.Random.Get_Int_SetRange(0, 2);
                if (Point_x == 0) {
                    point_x = -1;
                } else {
                    point_x = 1;
                }
                int Point_y = ActionTool.Random.Get_Int_SetRange(0, 2);
                if (Point_y == 0) {
                    point_y = -1;
                } else {
                    point_y = 1;
                }
                //正负号
                int Random_x = ActionTool.Random.Get_Int_SetRange(0, range);
                int Random_y = ActionTool.Random.Get_Int_SetRange(0, range);
                centre_x = (int) (MyFrame.GetScreenWH()[0] / 2.0 + (point_x * Random_x));
                centre_y = (int) (MyFrame.GetScreenWH()[1] / 2.0 + (point_y * Random_y));
                //偏移量
            }

            this.Follow(centre_x, centre_y);
            //移动

            this.Refresh();
            //刷新数据
        }

        public void Follow(int x, int y) {
            int BodyCentre_x = Main.robot.BasicBody.Dst.x + Main.robot.BasicBody.Dst.width / 2;
            int BodyCentre_y = (int) (Main.robot.BasicBody.Dst.y + Main.robot.Eyes_Relative_Y * Main.robot.Basic_Zoom);
            int Ratio = 5;
            int Gap_x = x - BodyCentre_x;
            int Gap_y = y - BodyCentre_y;
            int Gap_x_Ratio = Gap_x / Ratio;
            int Gap_y_Ratio = Gap_y / Ratio;
            int Boundary_Left = (int) (Main.robot.BasicBody.Dst.x + FollowEyes_Relative_X * Main.robot.Basic_Zoom);
            int Boundary_Right = (int) (Main.robot.BasicBody.Dst.x + (FollowEyes_Relative_X + FollowEyes_Relative_W) * Main.robot.Basic_Zoom);
            int Boundary_Up = (int) (Main.robot.BasicBody.Dst.y + FollowEyes_Relative_Y * Main.robot.Basic_Zoom);
            int Boundary_Down = (int) (Main.robot.BasicBody.Dst.y + (FollowEyes_Relative_Y + FollowEyes_Relative_H) * Main.robot.Basic_Zoom);
            int Offset_x, Offset_y;//偏移量
            if (Gap_x_Ratio >= 0) {
                Offset_x = Math.min(Gap_x_Ratio, Boundary_Right - BodyCentre_x);
            } else {
                Offset_x = Math.max(Gap_x_Ratio, Boundary_Left - BodyCentre_x);
            }
            if (Gap_y_Ratio >= 0) {
                Offset_y = Math.min(Gap_y_Ratio, Boundary_Down - BodyCentre_y);
            } else {
                Offset_y = Math.max(Gap_y_Ratio, Boundary_Up - BodyCentre_y);
            }
            //计算出偏移量

            int TempCentre_x = (int) (this.TempDst.xf + this.TempDst.wf / 2);//this.TempDst.xf的数值再Move中被改变了，如果不改变的话：将会偏移Body->不用Move
            int TempCentre_y = (int) (this.TempDst.yf + this.TempDst.hf / 2);
            int SpeedRatio = 4;
            this.Speed_Dst_x = Offset_x / (float) SpeedRatio;
            this.Speed_Dst_y = Offset_y / (float) SpeedRatio;
            if (this.Speed_Dst_x > 0) {
                if (this.Speed_Dst_x + TempCentre_x > Boundary_Right) {
                    this.Speed_Dst_x = Boundary_Right - TempCentre_x;
                }
            } else {
                if (this.Speed_Dst_x + TempCentre_x < Boundary_Left) {
                    this.Speed_Dst_x = Boundary_Left - TempCentre_x;
                }
            }
            if (this.Speed_Dst_y > 0) {
                if (this.Speed_Dst_y + TempCentre_y > Boundary_Down) {
                    this.Speed_Dst_y = Boundary_Down - TempCentre_y;
                }
            } else {
                if (this.Speed_Dst_y + TempCentre_y < Boundary_Up) {
                    this.Speed_Dst_y = Boundary_Up - TempCentre_y;
                }
            }
            //速度设置

            this.Temp_Dst_x += this.Speed_Dst_x;
            this.TempDst.xf = this.picture.Dst.x + this.Temp_Dst_x;
            this.Temp_Dst_y += this.Speed_Dst_y;
            this.TempDst.yf = this.picture.Dst.y + this.Temp_Dst_y;
            //移动

            if (this.TempDst.xf + this.TempDst.wf / 2 < Boundary_Left) {
                this.TempDst.xf = Boundary_Left - this.TempDst.wf / 2;
            } else if (this.TempDst.xf + this.TempDst.wf / 2 > Boundary_Right) {
                this.TempDst.xf = Boundary_Right - this.TempDst.wf / 2;
            }
            if (this.TempDst.yf + this.TempDst.hf / 2 < Boundary_Up) {
                this.TempDst.yf = Boundary_Up - this.TempDst.hf / 2;
            } else if (this.TempDst.yf + this.TempDst.hf / 2 > Boundary_Down) {
                this.TempDst.yf = Boundary_Down - this.TempDst.hf / 2;
            }
            //边界调正
        }
        //跟随函数：返回眼睛现在应该再窗口中的位置

        @Override
        protected void reset_init() {
            super.reset_init();

            this.CurrentState = State_Sleep;
            this.BeforeState = this.CurrentState;
            //状态
            this.TempDst = new Display.Rect((float) this.picture.Dst.x, (float) this.picture.Dst.y, (float) this.picture.Dst.width, (float) this.picture.Dst.height);
            this.Speed_Dst_x = 0;
            this.Speed_Dst_y = 0;
            //速度
        }

        @Override
        public void Reset() {
            super.Reset();
            this.reset_init();
        }

        @Override
        public void Lock() {
            super.Lock();
            picture.eventLock.setDisplay_Conventional(true);
            picture.eventLock.setMouse_Click(true);
            Main.robot.BasicEyes.eventLock.setDisplay_Conventional(true);//通用绘制解锁
        }

        @Override
        public void Unlock() {
            super.Unlock();
            picture.eventLock.setDisplay_Conventional(false);
            picture.eventLock.setMouse_Click(false);
            Main.robot.BasicEyes.eventLock.setDisplay_Conventional(false);//通用绘制解锁
        }

    }
    //------------------看屏幕中心------------------
    ;
    //--------------------------------ActionObject方法--------------------------------------

    public static void Global_Init() {

        globalInitFirst();

        //------------------最先------------------

        globalInitActionOperation();

        //------------------动作Operation------------------

        globalInitActionObjectRegister();

        //------------------操作注册------------------
        ;
        //--------------------------------内存初始化--------------------------------------

        Robot.Global_Blink.CurrentState = ActionObject.State_Applying;

        //----------------数据状态初始化----------------

        globalInitEventListen();

        //----------------事件添加----------------

        globalInitSetTimer();

        //----------------Timer_Timing----------------
        ;
        //--------------------------------数据初始化--------------------------------------
    }
    //全局对象数据初始化：Action中的图像，数据全在此处加载     //特别注意，初始化改了之后需要该另外两个地方：Global_ResetFirst()；Eaten_End_UnlockALL()

    public static void globalInitFirst() {
        InterfaceButton.Global_Food = new InterfaceButton.Food[InterfaceButton.Food_Picture_Num];
        for (int i = 0; i < InterfaceButton.Food_Picture_Num; i++) {
            InterfaceButton.Global_Food[i] = new InterfaceButton.Food(new Picture());
        }
        //初始化Food，以便读取数据
        Main.userData = new UserData(RobotSize.SIZE_200, 100, Main.RESOURCE_PATH + "UserData/SetUp.data");
        Main.userData.Get_readData(Main.userData.readData());//数据获取
        //用户数据
        ThreadControl.Global_ThreadInit();
        //线程初始化
        Global_FishRoeAction_Tool = new ActionTool();//申请ActionTool
        //初始化动作对象管理工具
        Main.robot = new Robot();
        InterfaceButton.Button.Global_Init_Button_Interface();//Button图片初始化
        //图片初始化
        InterfaceButton.Global_Interface_Button_Data_Init();
        //界面，按钮对象初始化
    }
    //最先的

    public static void globalInitActionOperation() {

        Global_Blink_Operation = new Action_Blink[Main.robot.PictureNum_Blink];//初始化ActionPicture数组
        Global_Blink_Operation[0] = new Action_Blink(Main.robot.BasicEyes);//申请ActionPicture

        //------------------Blink------------------
        Global_BlinkSad_Operation = new Action_Blink[Main.robot.PictureNum_Blink];//初始化ActionPicture数组
        Global_BlinkSad_Operation[0] = new Action_Blink(Main.robot.SadEye);//申请ActionPicture

        //------------------Blink------------------
        Global_BlinkGrievanceSad_Operation = new Action_Blink[Main.robot.PictureNum_Blink];//初始化ActionPicture数组
        Global_BlinkGrievanceSad_Operation[0] = new Action_Blink(Main.robot.Grievance_Sad);//申请ActionPicture

        //------------------Blink------------------

        Global_Shake_Operation = new Action_ShakeEarsFoots[Main.robot.PictureNum_Shake];
        Global_Shake_Operation[0] = new Action_ShakeEarsFoots(Main.robot.Ears[0], Direction.Left_Up);
        Global_Shake_Operation[1] = new Action_ShakeEarsFoots(Main.robot.Ears[1], Direction.Right_Up);
        Global_Shake_Operation[2] = new Action_ShakeEarsFoots(Main.robot.Foots[0], Direction.Left_Down);
        Global_Shake_Operation[3] = new Action_ShakeEarsFoots(Main.robot.Foots[1], Direction.Right_Down);

        //------------------Shake------------------

        Global_Squint_Operation = new Action_Squint[1];
        Global_Squint_Operation[0] = new Action_Squint(Main.robot.Squint);
        Global_Squint_Operation[0].ImmediatelyEndCondition = (ActionTool.Global_Lock_FoodEaten || ActionTool.Global_Lock_FoodAppeared);

        //------------------Squint------------------

        Global_Doubt_Operation = new Action_Doubt[1];
        Global_Doubt_Operation[0] = new Action_Doubt(Main.robot.Doubt);
        Global_Doubt_Operation[0].ImmediatelyEndCondition = (ActionTool.Global_Lock_FoodEaten || ActionTool.Global_Lock_FoodAppeared);

        //------------------Doubt------------------

        Global_Dizzy_Operation = new Action_Dizzy[2];
        Global_Dizzy_Operation[0] = new Action_Dizzy(Main.robot.Dizzy[0], Direction.Left);
        Global_Dizzy_Operation[1] = new Action_Dizzy(Main.robot.Dizzy[1], Direction.Right);
        for (int i = 0; i < 2; i++) {
            Global_Dizzy_Operation[i].Time_EndTime = Global_ActionDuration_Dizzy;
            Global_Dizzy_Operation[i].ListType = ActionObject_Operation.ListType_Buffer;
            Global_Dizzy_Operation[i].ImmediatelyEndCondition = (ActionTool.Global_Lock_FoodEaten || ActionTool.Global_Lock_FoodAppeared);
        }

        //------------------Dizzy------------------

        Global_WonderEye_Operation = new Action_WonderEyes[1];
        Global_WonderEye_Operation[0] = new Action_WonderEyes(Main.robot.Surprised);
        Global_WonderEye_Operation[0].ImmediatelyEndCondition = ActionTool.Global_Lock_FoodEaten;
        Global_WonderEar_Operation = new Action_ShakeEar_Wonder[Main.robot.PictureNum_Shake];
        Global_WonderEar_Operation[0] = new Action_ShakeEar_Wonder(Main.robot.Ears[0], Direction.Left_Up);
        Global_WonderEar_Operation[1] = new Action_ShakeEar_Wonder(Main.robot.Ears[1], Direction.Right_Up);
        Global_WonderEar_Operation[2] = new Action_ShakeEar_Wonder(Main.robot.Foots[0], Direction.Left_Down);
        Global_WonderEar_Operation[3] = new Action_ShakeEar_Wonder(Main.robot.Foots[1], Direction.Right_Down);
        for (int i = 0; i < Main.robot.PictureNum_Shake; i++) {
            Global_WonderEar_Operation[i].ImmediatelyEndCondition = ActionTool.Global_Lock_FoodEaten;
        }//结束条件+定时结束

        //------------------Wonder------------------

        Global_StarEyes_Operation = new Action_StarEyes[2];
        for (int i = 0; i < 2; i++) {
            Global_StarEyes_Operation[i] = new Action_StarEyes(Main.robot.StarEyes[i]);
            Global_StarEyes_Operation[i].ImmediatelyEndCondition = ActionTool.Global_Lock_FoodEaten;
            Global_StarEyes_Operation[i].Time_EndTime = Global_ActionDuration_StarHappy;//结束时间设置
        }
        Global_ShakeEarHappy_Operation = new ActionShakeEarHappy[Main.robot.PictureNum_Shake];
        Global_ShakeEarHappy_Operation[0] = new ActionShakeEarHappy(Main.robot.Ears[0], Direction.Left_Up);
        Global_ShakeEarHappy_Operation[1] = new ActionShakeEarHappy(Main.robot.Ears[1], Direction.Right_Up);
        Global_ShakeEarHappy_Operation[2] = new ActionShakeEarHappy(Main.robot.Foots[0], Direction.Left_Down);
        Global_ShakeEarHappy_Operation[3] = new ActionShakeEarHappy(Main.robot.Foots[1], Direction.Right_Down);
        for (int i = 0; i < Main.robot.PictureNum_Shake; i++) {
            Global_ShakeEarHappy_Operation[i].ImmediatelyEndCondition = ActionTool.Global_Lock_FoodEaten;
            Global_ShakeEarHappy_Operation[i].Time_EndTime = Global_ActionDuration_StarHappy;//结束时间设置
        }//结束条件+定时结束

        //------------------StarEyes+Happy------------------

        Global_EyesFollow_Operation = new Action_EyesFollow[1];
        Global_EyesFollow_Operation[0] = new Action_EyesFollow(Main.robot.BasicEyes);//申请ActionPicture
        Global_EyesFollow_Operation[0].ImmediatelyEndCondition = ActionTool.Global_Lock_FoodEaten;
        Global_EyesFollow_Operation[0].Time_EndTime = Global_ActionDuration_EyesFollow;//结束时间设置

        //------------------EyesFollow------------------

        Global_DoubtBeforeFollow_Operation = new Action_Doubt[1];
        Global_DoubtBeforeFollow_Operation[0] = new Action_Doubt(Main.robot.Doubt);
        Global_DoubtBeforeFollow_Operation[0].ImmediatelyEndCondition = ActionTool.Global_Lock_FoodEaten;

        //------------------DoubtBeforeFollow------------------

        Global_SquintStruggle_Operation = new Action_SquintStruggle[1];
        Global_SquintStruggle_Operation[0] = new Action_SquintStruggle(Main.robot.Squint);
        Global_SquintStruggle_Operation[0].ImmediatelyEndCondition = ActionTool.Global_Lock_FoodEaten;
        Global_SquintStruggle_Operation[0].Time_EndTime = Global_ActionDuration_Struggle;
        Global_ShakeEar_Struggle_Operation = new Action_ShakeEar_Struggle[Main.robot.PictureNum_Shake];
        Global_ShakeEar_Struggle_Operation[0] = new Action_ShakeEar_Struggle(Main.robot.Ears[0], Direction.Left_Up);
        Global_ShakeEar_Struggle_Operation[1] = new Action_ShakeEar_Struggle(Main.robot.Ears[1], Direction.Right_Up);
        Global_ShakeEar_Struggle_Operation[2] = new Action_ShakeEar_Struggle(Main.robot.Foots[0], Direction.Left_Down);
        Global_ShakeEar_Struggle_Operation[3] = new Action_ShakeEar_Struggle(Main.robot.Foots[1], Direction.Right_Down);
        for (int i = 0; i < Main.robot.PictureNum_Shake; i++) {
            Global_ShakeEar_Struggle_Operation[i].ImmediatelyEndCondition = ActionTool.Global_Lock_FoodEaten;
            Global_ShakeEar_Struggle_Operation[i].Time_EndTime = Global_ActionDuration_Struggle;
        }

        Global_SquintStruggle_Operation1 = new Action_SquintStruggle[1];
        Global_SquintStruggle_Operation1[0] = new Action_SquintStruggle(Main.robot.Squint);
        Global_SquintStruggle_Operation1[0].ImmediatelyEndCondition = ActionTool.Global_Lock_FoodEaten;
        Global_SquintStruggle_Operation1[0].Time_EndTime = Global_ActionDuration_Struggle;
        Global_ShakeEar_Struggle_Operation1 = new Action_ShakeEar_Struggle[Main.robot.PictureNum_Shake];
        Global_ShakeEar_Struggle_Operation1[0] = new Action_ShakeEar_Struggle(Main.robot.Ears[0], Direction.Left_Up);
        Global_ShakeEar_Struggle_Operation1[1] = new Action_ShakeEar_Struggle(Main.robot.Ears[1], Direction.Right_Up);
        Global_ShakeEar_Struggle_Operation1[2] = new Action_ShakeEar_Struggle(Main.robot.Foots[0], Direction.Left_Down);
        Global_ShakeEar_Struggle_Operation1[3] = new Action_ShakeEar_Struggle(Main.robot.Foots[1], Direction.Right_Down);
        for (int i = 0; i < Main.robot.PictureNum_Shake; i++) {
            Global_ShakeEar_Struggle_Operation1[i].ImmediatelyEndCondition = ActionTool.Global_Lock_FoodEaten;
            Global_ShakeEar_Struggle_Operation1[i].Time_EndTime = Global_ActionDuration_Struggle;
        }

        //------------------Struggle------------------

        Global_Angry_Operation = new Action_Angry[1];
        Global_Angry_Operation[0] = new Action_Angry(Main.robot.AngryEye);
        Global_Angry_Operation[0].ImmediatelyEndCondition = ActionTool.Global_Lock_FoodEaten;
        Global_Angry_Operation[0].Time_EndTime = Global_ActionDuration_Angry;

        //------------------Angry------------------

        Global_Sad_Operation = new Action_Sad[1];
        Global_Sad_Operation[0] = new Action_Sad(Main.robot.SadEye);
        Global_Sad_Operation[0].ImmediatelyEndCondition = ActionTool.Global_Lock_FoodEaten;
        Global_Sad_Operation[0].Time_EndTime = Global_ActionDuration_Sad;

        Global_SadEar_Operation = new Action_SadEar[Main.robot.PictureNum_Shake];
        Global_SadEar_Operation[0] = new Action_SadEar(Main.robot.Ears[0], Direction.Left_Up);
        Global_SadEar_Operation[1] = new Action_SadEar(Main.robot.Ears[1], Direction.Right_Up);
        Global_SadEar_Operation[2] = new Action_SadEar(Main.robot.Foots[0], Direction.Left_Down);
        Global_SadEar_Operation[3] = new Action_SadEar(Main.robot.Foots[1], Direction.Right_Down);
        for (int i = 0; i < Main.robot.PictureNum_Shake; i++) {
            Global_SadEar_Operation[i].ImmediatelyEndCondition = ActionTool.Global_Lock_FoodEaten;
            Global_SadEar_Operation[i].Time_EndTime = Global_ActionDuration_Sad;
        }

        //------------------Sad------------------

        Global_GrievanceSquint_Operation = new Action_SquintStruggle[1];
        Global_GrievanceSquint_Operation[0] = new Action_SquintStruggle(Main.robot.Grievance_Squint);
        Global_GrievanceSquint_Operation[0].ImmediatelyEndCondition = ActionTool.Global_Lock_FoodEaten;
        Global_GrievanceSquint_Operation[0].Time_EndTime = Global_ActionDuration_Struggle;

        Global_ShakeEar_Struggle_Operation2 = new Action_ShakeEar_Struggle[Main.robot.PictureNum_Shake];
        Global_ShakeEar_Struggle_Operation2[0] = new Action_ShakeEar_Struggle(Main.robot.Ears[0], Direction.Left_Up);
        Global_ShakeEar_Struggle_Operation2[1] = new Action_ShakeEar_Struggle(Main.robot.Ears[1], Direction.Right_Up);
        Global_ShakeEar_Struggle_Operation2[2] = new Action_ShakeEar_Struggle(Main.robot.Foots[0], Direction.Left_Down);
        Global_ShakeEar_Struggle_Operation2[3] = new Action_ShakeEar_Struggle(Main.robot.Foots[1], Direction.Right_Down);
        for (int i = 0; i < Main.robot.PictureNum_Shake; i++) {
            Global_ShakeEar_Struggle_Operation2[i].ImmediatelyEndCondition = ActionTool.Global_Lock_FoodEaten;
            Global_ShakeEar_Struggle_Operation2[i].Time_EndTime = Global_ActionDuration_Struggle;
        }
        //Squint

        Global_GrievanceAngry_Operation = new Action_Angry[1];
        Global_GrievanceAngry_Operation[0] = new Action_Angry(Main.robot.Grievance_Angry);
        Global_GrievanceAngry_Operation[0].ImmediatelyEndCondition = ActionTool.Global_Lock_FoodEaten;
        Global_GrievanceAngry_Operation[0].Time_EndTime = Global_ActionDuration_GrievanceAngry;
        //Angry

        Global_GrievanceSquint_Operation2 = new Action_SquintStruggle[1];
        Global_GrievanceSquint_Operation2[0] = new Action_SquintStruggle(Main.robot.Grievance_Squint);
        Global_GrievanceSquint_Operation2[0].ImmediatelyEndCondition = ActionTool.Global_Lock_FoodEaten;
        Global_GrievanceSquint_Operation2[0].Time_EndTime = Global_ActionDuration_Struggle;

        Global_ShakeEar_Struggle_Operation3 = new Action_ShakeEar_Struggle[Main.robot.PictureNum_Shake];
        Global_ShakeEar_Struggle_Operation3[0] = new Action_ShakeEar_Struggle(Main.robot.Ears[0], Direction.Left_Up);
        Global_ShakeEar_Struggle_Operation3[1] = new Action_ShakeEar_Struggle(Main.robot.Ears[1], Direction.Right_Up);
        Global_ShakeEar_Struggle_Operation3[2] = new Action_ShakeEar_Struggle(Main.robot.Foots[0], Direction.Left_Down);
        Global_ShakeEar_Struggle_Operation3[3] = new Action_ShakeEar_Struggle(Main.robot.Foots[1], Direction.Right_Down);
        for (int i = 0; i < Main.robot.PictureNum_Shake; i++) {
            Global_ShakeEar_Struggle_Operation3[i].ImmediatelyEndCondition = ActionTool.Global_Lock_FoodEaten;
            Global_ShakeEar_Struggle_Operation3[i].Time_EndTime = Global_ActionDuration_Struggle;
        }
        //Squint2

        Global_SadEyesFollow_Operation = new Action_EyesFollow[1];
        Global_SadEyesFollow_Operation[0] = new Action_EyesFollow(Main.robot.Grievance_Sad);//申请ActionPicture
        Global_SadEyesFollow_Operation[0].ImmediatelyEndCondition = ActionTool.Global_Lock_FoodEaten;
        Global_SadEyesFollow_Operation[0].Time_EndTime = -1L;//结束时间设置

        Global_SadEar_Operation2 = new Action_SadEar[Main.robot.PictureNum_Shake];
        Global_SadEar_Operation2[0] = new Action_SadEar(Main.robot.Ears[0], Direction.Left_Up);
        Global_SadEar_Operation2[1] = new Action_SadEar(Main.robot.Ears[1], Direction.Right_Up);
        Global_SadEar_Operation2[2] = new Action_SadEar(Main.robot.Foots[0], Direction.Left_Down);
        Global_SadEar_Operation2[3] = new Action_SadEar(Main.robot.Foots[1], Direction.Right_Down);
        for (int i = 0; i < Main.robot.PictureNum_Shake; i++) {
            Global_SadEar_Operation2[i].ImmediatelyEndCondition = ActionTool.Global_Lock_FoodEaten;
            Global_SadEar_Operation2[i].Time_EndTime = -1L;
        }
        //Sad
        ;
        //------------------Grievance------------------

        Global_LoveEye_Operation = new Action_StarEyes[2];
        for (int i = 0; i < 2; i++) {
            Global_LoveEye_Operation[i] = new Action_StarEyes(Main.robot.LoveEyes1[i]);
            Global_LoveEye_Operation[i].ImmediatelyEndCondition = false;//不需要退出
            Global_LoveEye_Operation[i].Time_EndTime = Global_ActionDuration_StarHappy;//结束时间设置
        }
        Global_ShakeEarHappy_Operation2 = new ActionShakeEarHappy[Main.robot.PictureNum_Shake];
        Global_ShakeEarHappy_Operation2[0] = new ActionShakeEarHappy(Main.robot.Ears[0], Direction.Left_Up);
        Global_ShakeEarHappy_Operation2[1] = new ActionShakeEarHappy(Main.robot.Ears[1], Direction.Right_Up);
        Global_ShakeEarHappy_Operation2[2] = new ActionShakeEarHappy(Main.robot.Foots[0], Direction.Left_Down);
        Global_ShakeEarHappy_Operation2[3] = new ActionShakeEarHappy(Main.robot.Foots[1], Direction.Right_Down);
        for (int i = 0; i < Main.robot.PictureNum_Shake; i++) {
            Global_ShakeEarHappy_Operation2[i].ImmediatelyEndCondition = false;
            Global_ShakeEarHappy_Operation2[i].Time_EndTime = Global_ActionDuration_StarHappy;//结束时间设置
        }//结束条件+定时结束

        //------------------LoveEye------------------

        Global_EatenHappy_Operation = new Action_EatenHappy[1];
        Global_EatenHappy_Operation[0] = new Action_EatenHappy(Main.robot.HappyEye);
        Global_EatenHappy_Operation[0].ImmediatelyEndCondition = false;//不需要退出

        //------------------EatenHappy------------------

        Global_FoodFollow_Operation = new Action_FoodFollow[InterfaceButton.Food_Picture_Num];
        for (int i = 0; i < InterfaceButton.Food_Picture_Num; i++) {
            Global_FoodFollow_Operation[i] = new Action_FoodFollow(InterfaceButton.Global_Food[i].picture);
            Global_FoodFollow_Operation[i].food = InterfaceButton.Global_Food[i];
            Global_FoodFollow_Operation[i].ImmediatelyEndCondition = false;
        }

        //------------------FoodFollow------------------

        Global_TriggerHappy_Operation = new Action_Angry[1];
        Global_TriggerHappy_Operation[0] = new Action_Angry(Main.robot.HappyEye);
        Global_TriggerHappy_Operation[0].ImmediatelyEndCondition = (ActionTool.Global_Lock_FoodEaten || ActionTool.Global_Lock_FoodAppeared);
        Global_TriggerHappy_Operation[0].Time_EndTime = Global_ActionDuration_Angry;
        Global_TriggerHappy_Operation[0].ListType = ActionObject_Operation.ListType_Buffer;

        //------------------TriggerHappy------------------

        Global_TriggerSad_Operation = new Action_Angry[1];
        Global_TriggerSad_Operation[0] = new Action_Angry(Main.robot.SadEye);
        Global_TriggerSad_Operation[0].ImmediatelyEndCondition = (ActionTool.Global_Lock_FoodEaten || ActionTool.Global_Lock_FoodAppeared);
        Global_TriggerSad_Operation[0].Time_EndTime = Global_ActionDuration_Angry;
        Global_TriggerSad_Operation[0].ListType = ActionObject_Operation.ListType_Buffer;

        //------------------TriggerSad------------------

        Global_TriggerAngry_Operation = new Action_Angry[1];
        Global_TriggerAngry_Operation[0] = new Action_Angry(Main.robot.AngryEye);
        Global_TriggerAngry_Operation[0].ImmediatelyEndCondition = (ActionTool.Global_Lock_FoodEaten || ActionTool.Global_Lock_FoodAppeared);
        Global_TriggerAngry_Operation[0].Time_EndTime = Global_ActionDuration_Angry;
        Global_TriggerAngry_Operation[0].ListType = ActionObject_Operation.ListType_Buffer;

        //------------------TriggerAngry------------------

        Global_TriggerXX_Operation = new Action_Angry[1];
        Global_TriggerXX_Operation[0] = new Action_Angry(Main.robot.XX);
        Global_TriggerXX_Operation[0].ImmediatelyEndCondition = (ActionTool.Global_Lock_FoodEaten || ActionTool.Global_Lock_FoodAppeared);
        Global_TriggerXX_Operation[0].Time_EndTime = Global_ActionDuration_Angry;
        Global_TriggerXX_Operation[0].ListType = ActionObject_Operation.ListType_Buffer;

        //------------------TriggerXX------------------

        Global_TriggerDoubt_Operation = new Action_Doubt[1];
        Global_TriggerDoubt_Operation[0] = new Action_Doubt(Main.robot.Doubt);
        Global_TriggerDoubt_Operation[0].ImmediatelyEndCondition = (ActionTool.Global_Lock_FoodEaten || ActionTool.Global_Lock_FoodAppeared);
        Global_TriggerDoubt_Operation[0].ListType = ActionObject_Operation.ListType_Buffer;

        //------------------TriggerDoubt------------------

        Global_TriggerSquintStruggle_Operation = new Action_SquintStruggle[1];
        Global_TriggerSquintStruggle_Operation[0] = new Action_SquintStruggle(Main.robot.Squint);
        Global_TriggerSquintStruggle_Operation[0].ImmediatelyEndCondition = (ActionTool.Global_Lock_FoodEaten || ActionTool.Global_Lock_FoodAppeared);
        Global_TriggerSquintStruggle_Operation[0].ListType = ActionObject_Operation.ListType_Buffer;
        Global_TriggerSquintStruggle_Operation[0].Time_EndTime = Global_ActionDuration_Angry;

        //------------------TriggerSquintStruggle------------------

        Global_TriggerGrievanceSquintStruggle_Operation = new Action_SquintStruggle[1];
        Global_TriggerGrievanceSquintStruggle_Operation[0] = new Action_SquintStruggle(Main.robot.Grievance_Squint);
        Global_TriggerGrievanceSquintStruggle_Operation[0].ImmediatelyEndCondition = (ActionTool.Global_Lock_FoodEaten || ActionTool.Global_Lock_FoodAppeared);
        Global_TriggerGrievanceSquintStruggle_Operation[0].ListType = ActionObject_Operation.ListType_Buffer;
        Global_TriggerGrievanceSquintStruggle_Operation[0].Time_EndTime = Global_ActionDuration_Angry;

        //------------------TriggerGrievanceSquintStruggle------------------

        Global_TriggerGrievanceAngry_Operation = new Action_Angry[1];
        Global_TriggerGrievanceAngry_Operation[0] = new Action_Angry(Main.robot.Grievance_Angry);
        Global_TriggerGrievanceAngry_Operation[0].ImmediatelyEndCondition = (ActionTool.Global_Lock_FoodEaten || ActionTool.Global_Lock_FoodAppeared);
        Global_TriggerGrievanceAngry_Operation[0].Time_EndTime = Global_ActionDuration_Angry;
        Global_TriggerGrievanceAngry_Operation[0].ListType = ActionObject_Operation.ListType_Buffer;

        //------------------TriggerGrievanceAngry------------------

        Global_TriggerGrievanceSad_Operation = new Action_Angry[1];
        Global_TriggerGrievanceSad_Operation[0] = new Action_Angry(Main.robot.Grievance_Sad);
        Global_TriggerGrievanceSad_Operation[0].ImmediatelyEndCondition = (ActionTool.Global_Lock_FoodEaten || ActionTool.Global_Lock_FoodAppeared);
        Global_TriggerGrievanceSad_Operation[0].Time_EndTime = Global_ActionDuration_Angry;
        Global_TriggerGrievanceSad_Operation[0].ListType = ActionObject_Operation.ListType_Buffer;

        //------------------TriggerGrievanceSad------------------

        Global_BasicBodyMove_Operation = new Action_BasicBodyMove[1];
        Global_BasicBodyMove_Operation[0] = new Action_BasicBodyMove(Main.robot.BasicBody);
        Global_BasicBodyMove_Operation[0].ImmediatelyEndCondition = (ActionTool.Global_Lock_FoodEaten || ActionTool.Global_Lock_FoodAppeared);

        //------------------BasicBodyMove------------------

        Global_TriggerGrievanceSadEar_Operation = new Action_SadEar[Main.robot.PictureNum_Shake];
        Global_TriggerGrievanceSadEar_Operation[0] = new Action_SadEar(Main.robot.Ears[0], Direction.Left_Up);
        Global_TriggerGrievanceSadEar_Operation[1] = new Action_SadEar(Main.robot.Ears[1], Direction.Right_Up);
        Global_TriggerGrievanceSadEar_Operation[2] = new Action_SadEar(Main.robot.Foots[0], Direction.Left_Down);
        Global_TriggerGrievanceSadEar_Operation[3] = new Action_SadEar(Main.robot.Foots[1], Direction.Right_Down);
        for (int i = 0; i < Main.robot.PictureNum_Shake; i++) {
            Global_TriggerGrievanceSadEar_Operation[i].ImmediatelyEndCondition = (ActionTool.Global_Lock_FoodEaten || ActionTool.Global_Lock_FoodAppeared);
            Global_TriggerGrievanceSadEar_Operation[i].Time_EndTime = Global_ActionDuration_TriggerGrievanceSadEar;
            Global_TriggerGrievanceSadEar_Operation[i].ListType = ActionObject_Operation.ListType_Buffer;
        }

        //------------------TriggerGrievanceSadEar------------------

        Global_HappyShakeEar_Simple_Operation = new ActionShakeEarHappy[Main.robot.PictureNum_Shake];
        Global_HappyShakeEar_Simple_Operation[0] = new ActionShakeEarHappy(Main.robot.Ears[0], Direction.Left_Up);
        Global_HappyShakeEar_Simple_Operation[1] = new ActionShakeEarHappy(Main.robot.Ears[1], Direction.Right_Up);
        Global_HappyShakeEar_Simple_Operation[2] = new ActionShakeEarHappy(Main.robot.Foots[0], Direction.Left_Down);
        Global_HappyShakeEar_Simple_Operation[3] = new ActionShakeEarHappy(Main.robot.Foots[1], Direction.Right_Down);
        for (int i = 0; i < Main.robot.PictureNum_Shake; i++) {
            Global_HappyShakeEar_Simple_Operation[i].ImmediatelyEndCondition = (ActionTool.Global_Lock_FoodEaten || ActionTool.Global_Lock_FoodAppeared);
            Global_HappyShakeEar_Simple_Operation[i].Time_EndTime = Global_ActionDuration_HappyShakeEar_Simple;
        }

        //------------------HappyShakeEar_Simple------------------

        Global_StruggleShakeEar_Simple_Operation = new Action_ShakeEar_Struggle[Main.robot.PictureNum_Shake];
        Global_StruggleShakeEar_Simple_Operation[0] = new Action_ShakeEar_Struggle(Main.robot.Ears[0], Direction.Left_Up);
        Global_StruggleShakeEar_Simple_Operation[1] = new Action_ShakeEar_Struggle(Main.robot.Ears[1], Direction.Right_Up);
        Global_StruggleShakeEar_Simple_Operation[2] = new Action_ShakeEar_Struggle(Main.robot.Foots[0], Direction.Left_Down);
        Global_StruggleShakeEar_Simple_Operation[3] = new Action_ShakeEar_Struggle(Main.robot.Foots[1], Direction.Right_Down);
        for (int i = 0; i < Main.robot.PictureNum_Shake; i++) {
            Global_StruggleShakeEar_Simple_Operation[i].ImmediatelyEndCondition = (ActionTool.Global_Lock_FoodEaten || ActionTool.Global_Lock_FoodAppeared);
            Global_StruggleShakeEar_Simple_Operation[i].Time_EndTime = Global_ActionDuration_HappyShakeEar_Simple;
        }

        //------------------HappyShakeEar_Simple------------------
        Global_LookingForScreen_Operation = new Action_LookingForScreen[1];
        ;
        Global_LookingForScreen_Operation[0] = new Action_LookingForScreen(Main.robot.BasicEyes);
        Global_LookingForScreen_Operation[0].ImmediatelyEndCondition = (ActionTool.Global_Lock_FoodEaten || ActionTool.Global_Lock_FoodAppeared);
        Global_LookingForScreen_Operation[0].Time_EndTime = Global_ActionDuration_EyesFollow2;
        Global_LookingForScreen_Operation[0].ListType = ActionObject_Operation.ListType_Buffer;
        Global_LookingForScreen_Sad_Operation = new Action_LookingForScreen[1];
        ;
        Global_LookingForScreen_Sad_Operation[0] = new Action_LookingForScreen(Main.robot.SadEye);
        Global_LookingForScreen_Sad_Operation[0].ImmediatelyEndCondition = (ActionTool.Global_Lock_FoodEaten || ActionTool.Global_Lock_FoodAppeared);
        Global_LookingForScreen_Sad_Operation[0].Time_EndTime = Global_ActionDuration_EyesFollow2;
        Global_LookingForScreen_Sad_Operation[0].ListType = ActionObject_Operation.ListType_Buffer;
        Global_LookingForScreen_Angry_Operation = new Action_LookingForScreen[1];
        ;
        Global_LookingForScreen_Angry_Operation[0] = new Action_LookingForScreen(Main.robot.AngryEye);
        Global_LookingForScreen_Angry_Operation[0].ImmediatelyEndCondition = (ActionTool.Global_Lock_FoodEaten || ActionTool.Global_Lock_FoodAppeared);
        Global_LookingForScreen_Angry_Operation[0].Time_EndTime = Global_ActionDuration_EyesFollow2;
        Global_LookingForScreen_Angry_Operation[0].ListType = ActionObject_Operation.ListType_Buffer;
        Global_LookingForScreen_GrievanceSad_Operation = new Action_LookingForScreen[1];
        ;
        Global_LookingForScreen_GrievanceSad_Operation[0] = new Action_LookingForScreen(Main.robot.Grievance_Sad);
        Global_LookingForScreen_GrievanceSad_Operation[0].ImmediatelyEndCondition = (ActionTool.Global_Lock_FoodEaten || ActionTool.Global_Lock_FoodAppeared);
        Global_LookingForScreen_GrievanceSad_Operation[0].Time_EndTime = Global_ActionDuration_EyesFollow2;
        Global_LookingForScreen_GrievanceSad_Operation[0].ListType = ActionObject_Operation.ListType_Buffer;
        Global_LookingForScreen_GrievanceAngry_Operation = new Action_LookingForScreen[1];
        ;
        Global_LookingForScreen_GrievanceAngry_Operation[0] = new Action_LookingForScreen(Main.robot.Grievance_Angry);
        Global_LookingForScreen_GrievanceAngry_Operation[0].ImmediatelyEndCondition = (ActionTool.Global_Lock_FoodEaten || ActionTool.Global_Lock_FoodAppeared);
        Global_LookingForScreen_GrievanceAngry_Operation[0].Time_EndTime = Global_ActionDuration_EyesFollow2;
        Global_LookingForScreen_GrievanceAngry_Operation[0].ListType = ActionObject_Operation.ListType_Buffer;
        //------------------EyesFollow------------------
    }
    //图片动作操作

    public static void globalInitActionObjectRegister() {
        ActionTool.Global_AddAllAction(Robot.Global_FishRoeAction_Tool);//申请Action
        //注册表：初始化所有动作对象
    }

    //操作注册
    public static MouseDragged Global_mouseDragged_BasicBody;

    public static void globalInitEventListen() {
        Global_mouseDragged_BasicBody = new MouseDragged(Main.robot.BasicBody.Dst);
        Global_mouseDragged_BasicBody.Lock_AddLockPicture(Main.robot.BasicEyes);
        Global_mouseDragged_BasicBody.Lock_AddLockPicture(Main.robot.HighLight);
        Global_mouseDragged_BasicBody.Lock_AddLockPicture(Main.robot.Squint);
        Global_mouseDragged_BasicBody.Lock_AddLockPicture(Main.robot.Doubt);
        Global_mouseDragged_BasicBody.Lock_AddLockPicture(Main.robot.LoveEyes1[0]);
        Global_mouseDragged_BasicBody.Lock_AddLockPicture(Main.robot.LoveEyes1[1]);
        Global_mouseDragged_BasicBody.Lock_AddLockPicture(Main.robot.StarEyes[0]);
        Global_mouseDragged_BasicBody.Lock_AddLockPicture(Main.robot.StarEyes[1]);
        Global_mouseDragged_BasicBody.Lock_AddLockPicture(Main.robot.Surprised);
        Global_mouseDragged_BasicBody.Lock_AddLockPicture(Main.robot.AngryEye);
        Global_mouseDragged_BasicBody.Lock_AddLockPicture(Main.robot.SadEye);
        Global_mouseDragged_BasicBody.Lock_AddLockPicture(Main.robot.Grievance_Angry);
        Global_mouseDragged_BasicBody.Lock_AddLockPicture(Main.robot.Grievance_Squint);
        Global_mouseDragged_BasicBody.Lock_AddLockPicture(Main.robot.Grievance_Sad);
        Global_mouseDragged_BasicBody.Lock_AddLockPicture(Main.robot.BasicHatch[1]);
        Global_mouseDragged_BasicBody.Lock_AddLockPicture(Main.robot.HappyEye);
        Global_mouseDragged_BasicBody.Lock_AddLockPicture(Main.robot.Dizzy[0]);
        Global_mouseDragged_BasicBody.Lock_AddLockPicture(Main.robot.Dizzy[1]);
        Global_mouseDragged_BasicBody.Lock_AddLockPicture(Main.robot.XX);
        //FishRoe的
        Global_mouseDragged_BasicBody.Lock_AddLockPicture(InterfaceButton.Global_SetUpSwitch);
        Global_mouseDragged_BasicBody.Lock_AddLockPicture(InterfaceButton.Global_SetUpButton);
        Global_mouseDragged_BasicBody.Lock_AddLockPicture(InterfaceButton.Global_BatteryButton);
        Global_mouseDragged_BasicBody.Lock_AddLockPicture(InterfaceButton.Global_LoveButton);
        Global_mouseDragged_BasicBody.Lock_AddLockPicture(InterfaceButton.Global_ExitButton);
        Global_mouseDragged_BasicBody.Lock_AddLockPicture(InterfaceButton.Global_ClothesButton);
        //Button的
        ;
        //添加一个相对本体静止的图片对象

        Main.robot.BasicBody.EventAdd_MousePress(Global_mouseDragged_BasicBody::Pressed_GetPoint);
        Main.robot.BasicBody.EventAdd_MousePress(Global_Dizzy.dizzy::Event_Dizzy_PressedMouse);
        Main.robot.BasicBody.EventAdd_MouseDragged(Global_mouseDragged_BasicBody::DraggedPicture_ForBasicBody);
        Main.robot.BasicBody.EventAdd_MouseRelease(Global_Dizzy.dizzy::Event_Dizzy_ReleasedMouse);
        Main.robot.BasicBody.EventAdd_MouseClick(Robot::MouseClick_BeginBodyMove);
        Main.robot.BasicBody.EventAdd_MousePress(Robot::MouseClick_BeginBodyMove);

        //------------------Body------------------

        MouseDragged mouseDragged_ear1 = new MouseDragged(Main.robot.Ears[0], Direction.Left_Up);
        MouseDragged mouseDragged_ear2 = new MouseDragged(Main.robot.Ears[1], Direction.Right_Up);
        MouseDragged mouseDragged_Foot1 = new MouseDragged(Main.robot.Foots[0], Direction.Left_Down);
        MouseDragged mouseDragged_Foot2 = new MouseDragged(Main.robot.Foots[1], Direction.Right_Down);
        //创建拖拽管控对象

        Main.robot.Ears[0].EventAdd_MousePress(mouseDragged_ear1::Pressed_LockAngleFollow);
        Main.robot.Ears[1].EventAdd_MousePress(mouseDragged_ear2::Pressed_LockAngleFollow);
        Main.robot.Foots[0].EventAdd_MousePress(mouseDragged_Foot1::Pressed_LockAngleFollow);
        Main.robot.Foots[1].EventAdd_MousePress(mouseDragged_Foot2::Pressed_LockAngleFollow);

        Main.robot.Ears[0].EventAdd_MouseDragged(mouseDragged_ear1::DraggedPicture_ForEarsFoots);
        Main.robot.Ears[1].EventAdd_MouseDragged(mouseDragged_ear2::DraggedPicture_ForEarsFoots);
        Main.robot.Foots[0].EventAdd_MouseDragged(mouseDragged_Foot1::DraggedPicture_ForEarsFoots);
        Main.robot.Foots[1].EventAdd_MouseDragged(mouseDragged_Foot2::DraggedPicture_ForEarsFoots);

        MouseClick Click_Ear1 = new MouseClick(Global_Shake_Operation[0]);
        MouseClick Click_Ear2 = new MouseClick(Global_Shake_Operation[1]);
        MouseClick Click_Foot1 = new MouseClick(Global_Shake_Operation[2]);
        MouseClick Click_Foot2 = new MouseClick(Global_Shake_Operation[3]);
        Main.robot.Ears[0].EventAdd_MouseClick(Click_Ear1::Clicked);
        Main.robot.Ears[1].EventAdd_MouseClick(Click_Ear2::Clicked);
        Main.robot.Foots[0].EventAdd_MouseClick(Click_Foot1::Clicked);
        Main.robot.Foots[1].EventAdd_MouseClick(Click_Foot2::Clicked);
        //抖耳

        MouseClick Click_Ear3 = new MouseClick(Global_Doubt_Operation[0]);
        MouseClick Click_Ear4 = new MouseClick(Global_Doubt_Operation[0]);
        MouseClick Click_Foot3 = new MouseClick(Global_Doubt_Operation[0]);
        MouseClick Click_Foot4 = new MouseClick(Global_Doubt_Operation[0]);
        Main.robot.Ears[0].EventAdd_MouseClick(Click_Ear3::Clicked);
        Main.robot.Ears[1].EventAdd_MouseClick(Click_Ear4::Clicked);
        Main.robot.Foots[0].EventAdd_MouseClick(Click_Foot3::Clicked);
        Main.robot.Foots[1].EventAdd_MouseClick(Click_Foot4::Clicked);
        //疑惑
        ;
        //------------------EarsFoots------------------


        MouseClick Click_Eyes = new MouseClick(Global_Blink_Operation[0]);
        Main.robot.BasicEyes.EventAdd_MouseClick(Click_Eyes::Clicked);
        MouseClick Click_Eyes1 = new MouseClick(Global_BlinkSad_Operation[0]);
        Main.robot.SadEye.EventAdd_MouseClick(Click_Eyes1::Clicked);
        MouseClick Click_Eyes2 = new MouseClick(Global_BlinkGrievanceSad_Operation[0]);
        Main.robot.Grievance_Sad.EventAdd_MouseClick(Click_Eyes2::Clicked);

        //------------------Eyes------------------

        InterfaceButton.Global_Interface_Button_Event_Init();

        //------------------Button+Interface------------------
        ;
        //Exp
    }

    public static void globalInitSetTimer() {
        ActionTool.Global_ActionTool_Timer_Init();
        //初始化动作对象管理工具
        ;
        //Global_Blink_Operation[0].Set_Timing(Time_Blink_Lower,Time_Blink_Upper);
        Global_Shake_Operation[0].Set_Timing(Time_Shake_Static, Time_Shake_Static, Global_Shake_Operation[0]::Begin);
        Global_Shake_Operation[1].Set_Timing(Time_Shake_Static, Time_Shake_Static, Global_Shake_Operation[1]::Begin);
        Global_Shake_Operation[2].Set_Timing(Time_Shake_Static, Time_Shake_Static, Global_Shake_Operation[2]::Begin);
        Global_Shake_Operation[3].Set_Timing(Time_Shake_Static, Time_Shake_Static, Global_Shake_Operation[3]::Begin);
        //ActionPicture的Timer申请
    }//设置时间监听

    //--------------------------------全局变量初始化--------------------------------------
    public static void Global_ResetAfterSetUp() {

        Global_ResetFirst();

        //------------------最先------------------

        globalInitActionOperation();

        //------------------动作Operation------------------

        globalInitActionObjectRegister();

        //------------------操作注册------------------
        ;
        //--------------------------------内存初始化--------------------------------------

        Robot.Global_Blink.CurrentState = ActionObject.State_Applying;

        //----------------数据状态初始化----------------

        globalInitEventListen();

        //----------------事件添加----------------

        globalInitSetTimer();

        //----------------Timer_Timing----------------
        ;
        //--------------------------------数据初始化--------------------------------------
    }//设置后的初始化

    //特别注意，初始化改了之后需要该另外两个地方：Global_ResetFirst()；Eaten_End_UnlockALL()
    public static void Global_ResetFirst() {
        InterfaceButton.Global_Food = new InterfaceButton.Food[InterfaceButton.Food_Picture_Num];
        for (int i = 0; i < InterfaceButton.Food_Picture_Num; i++) {
            InterfaceButton.Global_Food[i] = new InterfaceButton.Food(new Picture());
        }
        //初始化Food，以便读取数据
        Main.userData.Get_readData(Main.userData.readData());//数据获取
        //用户数据
        ThreadControl.Global_ThreadInit();
        //线程初始化
        Global_FishRoeAction_Tool = new ActionTool();//申请ActionTool
        //初始化动作对象管理工具
        Main.robot = new Robot();
        //鱼籽对象初始化
        InterfaceButton.Global_Interface_Button_Data_Init();
        //界面，按钮对象初始化
    }//首要

    //--------------------------------全局变量Reset--------------------------------------
}
