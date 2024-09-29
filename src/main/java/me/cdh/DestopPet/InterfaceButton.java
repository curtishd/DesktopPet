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
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class InterfaceButton extends Picture {
    public InterfaceButton(int State) {
        super();
        this.State = State;
        this.Zoom = 1.0;
    }
    //--------------------------------构造器--------------------------------------

    public ArrayList<Button> List_Button = new ArrayList<>();
    //按钮列表
    public int State;
    public double Zoom;
    //放缩
    public ArrayList<Display.Text> List_Texts = new ArrayList<>();
    //一个界面的多个文本
    ;
    //--------------------------------属性--------------------------------------

    //--------------------------------初始化--------------------------------------

    public void List_Button_Add(Button b) {
        if (this.List_Button != null) {
            this.List_Button.add(b);
        }
    }
    //添加一个按钮

    //删除一个按钮

    public void List_Text_Add(Display.Text t) {
        if (this.List_Texts != null) {
            this.List_Texts.add(t);
        }
    }
    //添加一个按钮

    //删除一个按钮
    ;
    //--------------------------------List--------------------------------------

    public void Reset_Dst(Point Dst_Point, Point Dst_xy) {
        this.Dst = Display.Paint.getPictureRelativeLocationOfChoseDst(this.Src, this.SrcCentre, Dst_Point,
                (int) (this.Src.width * this.Zoom), (int) (this.Src.height * this.Zoom),
                Dst_xy);
    }

    public void Reset_Dst_WithWH(Point Dst_Point, Point Dst_xy, int w, int h) {
        this.Dst = Display.Paint.getPictureRelativeLocationOfChoseDst(this.Src, this.SrcCentre, Dst_Point,
                w, h,
                Dst_xy);
    }

    public static void Refresh_SetUpButtonState() {
        int Order = Main.userData.setUp.TempOrder;
        for (int i = 0; i < 12; i++) {
            if (i != Order) {
                Global_SetUp_Button[i].State = Button.State_NotChose;
            } else {
                Global_SetUp_Button[i].State = Button.State_Chose;
            }
        }
    }
    //刷新设置状态

    public static void Reset_resetBasicSize() {
        RobotSize robotSize1 = UserData.SetUp.OrderToEnum(Main.userData.setUp.TempOrder);
        if (Main.userData.setUp.robotSize != robotSize1) {
            Main.userData.setUp.robotSize = robotSize1;
            //临时数据变为真实数据
            Main.userData.writeData();
            //写出数据
            Robot.Global_ResetAfterSetUp();
            //数据刷新
        }
    }

    //重置基础大小
    public static void Reset_SwitchClothes() {
        if (Main.userData.clothes.ClothesType != Clothes_CurrentOrder) {
            Main.userData.clothes.ClothesType = Clothes_CurrentOrder;
            //临时数据变为真实数据
            Main.userData.writeData();
            //写出数据
            Robot.Global_ResetAfterSetUp();
            //数据刷新
        }
    }
    //皮肤切换
    ;
    //--------------------------------Reset--------------------------------------

    public static BufferedImage Interface_Image;

    public static InterfaceButton Global_SetUp_Interface;
    //设置界面
    public static InterfaceButton Global_MoodSystem_Interface;
    //心情系统
    public static InterfaceButton Global_FoodSystem_Interface;
    //喂食系统
    public static InterfaceButton Global_Exit_Interface;
    //退出系统
    public static InterfaceButton Global_Clothes_Interface;
    //皮肤系统
    ;

    //--------------------------------全局界面--------------------------------------
    public static class Button extends Picture {
        public Button(int ImageNum, int State) {
            super();
            Array_Image_B = new BufferedImage[ImageNum];
            this.State = State;
        }

        //---------------构造器--------------------

        public BufferedImage[] Array_Image_B;
        public int State;
        public Display.Text text;
        public double Zoom = 1.0;
        //放缩
        ;
        //---------------属性--------------------

        public static final int State_NotDisplay = 0, State_NotChose = 1, State_Chose = 2;//0为not，1为chose
        public static final int Switch_Relative_Y = 520;

        //---------------final常量--------------------

        //---------------初始化--------------------

        public void Reset_Dst(Point Dst_Point, Point Dst_xy) {
            this.Dst = Display.Paint.getPictureRelativeLocationOfChoseDst(this.Src, this.SrcCentre, Dst_Point,
                    (int) (this.Src.width * this.Zoom), (int) (this.Src.height * this.Zoom),
                    Dst_xy);
        }

        //--------------------------------Reset--------------------------------------

        public static BufferedImage Button_NotChose, Button_Chose;//未选择的按钮+已选择的按钮
        public static BufferedImage Switch_Off, Switch_On;//开关
        public static BufferedImage SetUp_NotChose, SetUp_Chose;//设置按钮
        public static BufferedImage Love_NotChose, Love_Chose;//爱心
        public static BufferedImage Battery_NotChose, Battery_Chose;//电池
        public static BufferedImage MoodBar_Empty, MoodBar_Filled;//数值条
        public static BufferedImage SelectBox_Chose, SelectBox_NotChose;//选择框框
        public static BufferedImage Food_Arrow, Food_Battery, Food_Slug;//食物
        public static BufferedImage Exit_NotChose, Exit_Chose;//退出选择
        public static BufferedImage Clothes_NotChose, Clothes_Chose;//退出选择
        public static BufferedImage[] Clothes_ObjectPicture;

        public static void Global_Init_Button_Interface() {

            Button_NotChose = Load.loadImage("Picture/Frame/Button_NotChose.png");
            Button_Chose = Load.loadImage("Picture/Frame/Button_Chose.png");
            //按钮
            if (Main.userData.clothes.ClothesType != UserData.Clothes.ClothesType_Sakura &&
                    Main.userData.clothes.ClothesType != UserData.Clothes.ClothesType_SakuraNull) {
                Switch_Off = Load.loadImage("Picture/Switch1.png");
            }
            Switch_On = Load.loadImage("Picture/Switch2.png");
            //开关
            SetUp_NotChose = Load.loadImage("Picture/Frame/SetUp_NotChose.png");
            SetUp_Chose = Load.loadImage("Picture/Frame/SetUp_Chose.png");
            //设置
            Love_NotChose = Load.loadImage("Picture/Frame/Love_NotChose.png");
            Love_Chose = Load.loadImage("Picture/Frame/Love_Chose.png");
            //心情
            Battery_NotChose = Load.loadImage("Picture/Frame/Battery_NotChose.png");
            Battery_Chose = Load.loadImage("Picture/Frame/Battery_Chose.png");
            //电池
            MoodBar_Empty = Load.loadImage("Picture/Frame/MoodBar_Empty.png");
            MoodBar_Filled = Load.loadImage("Picture/Frame/MoodBar_Filled.png");
            //心情条
            SelectBox_Chose = Load.loadImage("Picture/Frame/SelectBox_Chose.png");
            SelectBox_NotChose = Load.loadImage("Picture/Frame/SelectBox_NotChose.png");
            //选择框框
            Food_Arrow = Load.loadImage("Picture/Food/Arrow.png");
            Food_Battery = Load.loadImage("Picture/Food/Battery.png");
            Food_Slug = Load.loadImage("Picture/Food/Slug.png");
            //食物
            Exit_Chose = Load.loadImage("Picture/Frame/Exit_Chose.png");
            Exit_NotChose = Load.loadImage("Picture/Frame/Exit_NotChose.png");
            //退出
            Clothes_Chose = Load.loadImage("Picture/Frame/Clothes_Chose.png");
            Clothes_NotChose = Load.loadImage("Picture/Frame/Clothes_NotChose.png");
            //皮肤
            Clothes_ObjectPicture = new BufferedImage[Clothes_Num];
            Clothes_ObjectPicture[0] = Load.loadImage("Picture/Clothes/Basic.png");
            Clothes_ObjectPicture[1] = Load.loadImage("Picture/Clothes/Fat.png");
            Clothes_ObjectPicture[2] = Load.loadImage("Picture/Clothes/Null.png");
            Clothes_ObjectPicture[3] = Load.loadImage("Picture/Clothes/Sakura.png");
            Clothes_ObjectPicture[4] = Load.loadImage("Picture/Clothes/SakuraNull.png");
            //皮肤对象
            ;
            //选择框框
            ;
            //按钮
            InterfaceButton.Interface_Image = Load.loadImage("Picture/Frame/Interface.png");
            //界面
            ;
            //加载图片
        }

        //---------------全局变量--------------------
    }

    //--------------------------------按钮类--------------------------------------

    public static void Event_ButtonTwoImageSwitch(Button b) {
        if (b.State == Button.State_NotChose) {
            b.State = Button.State_Chose;
        } else if (b.State == Button.State_Chose) {
            b.State = Button.State_NotChose;
        }
    }
    //图片切换

    public static void Event_SetUpSwitch_Click(MouseEvent e) {
        ThreadControl.PaintSleepController.eventAwake = true;
        Event_ButtonTwoImageSwitch(Global_SetUpSwitch);
        //点击切换状态
        if (Global_SetUpSwitch.State == Button.State_Chose) {
            Global_SetUpButton.State = Button.State_NotChose;
            Global_BatteryButton.State = Button.State_NotChose;
            Global_LoveButton.State = Button.State_NotChose;
            Global_ExitButton.State = Button.State_NotChose;
            Global_ClothesButton.State = Button.State_NotChose;
        } else if (Global_SetUpSwitch.State == Button.State_NotChose) {
            Global_SetUpButton.State = Button.State_NotDisplay;
            Global_BatteryButton.State = Button.State_NotDisplay;
            Global_LoveButton.State = Button.State_NotDisplay;
            Global_ExitButton.State = Button.State_NotDisplay;
            Global_ClothesButton.State = Button.State_NotDisplay;
            //关闭按钮
            InterfaceButton.Global_SetUp_Interface.State = Button.State_NotDisplay;
            InterfaceButton.Global_MoodSystem_Interface.State = Button.State_NotDisplay;
            InterfaceButton.Global_FoodSystem_Interface.State = Button.State_NotDisplay;
            Global_Exit_Interface.State = Button.State_NotDisplay;
            Global_Clothes_Interface.State = Button.State_NotDisplay;
            //关闭界面
        }
        //不绘制变为：准备绘制
    }

    //点击开关的事件
    public static void Event_SetUpButton_Click(MouseEvent e) {
        ThreadControl.PaintSleepController.eventAwake = true;
        Event_ButtonTwoImageSwitch(Global_SetUpButton);
        //点击切换状态
        if (Global_SetUpButton.State == Button.State_NotChose) {
            InterfaceButton.Global_SetUp_Interface.State = Button.State_NotDisplay;
            //关闭界面
        } else if (Global_SetUpButton.State == Button.State_Chose) {
            InterfaceButton.Global_SetUp_Interface.State = Button.State_Chose;
        }
        Main.userData.setUp.TempOrder = UserData.SetUp.EnumToOrder(Main.userData.setUp.robotSize);
        Refresh_SetUpButtonState();
        //更新TempOrder
    }

    //设置齿轮
    public static void Event_BatteryButton_Click(MouseEvent e) {
        ThreadControl.PaintSleepController.eventAwake = true;
        Event_ButtonTwoImageSwitch(Global_BatteryButton);
        //点击切换状态
        if (Global_BatteryButton.State == Button.State_NotChose) {
            InterfaceButton.Global_FoodSystem_Interface.State = Button.State_NotDisplay;
            //关闭界面
        } else if (Global_BatteryButton.State == Button.State_Chose) {
            InterfaceButton.Global_FoodSystem_Interface.State = Button.State_Chose;
        }
    }

    //电池
    public static void Event_LoveButton_Click(MouseEvent e) {
        ThreadControl.PaintSleepController.eventAwake = true;
        Event_ButtonTwoImageSwitch(Global_LoveButton);
        //点击切换状态
        if (Global_LoveButton.State == Button.State_NotChose) {
            InterfaceButton.Global_MoodSystem_Interface.State = Button.State_NotDisplay;
            //关闭界面
        } else if (Global_LoveButton.State == Button.State_Chose) {
            InterfaceButton.Global_MoodSystem_Interface.State = Button.State_Chose;
        }
    }

    //爱心
    public static void Event_ExitButton_Click(MouseEvent e) {
        ThreadControl.PaintSleepController.eventAwake = true;
        Event_ButtonTwoImageSwitch(Global_ExitButton);
        //点击切换状态
        if (Global_ExitButton.State == Button.State_NotChose) {
            Global_Exit_Interface.State = Button.State_NotDisplay;
            //关闭界面
        } else if (Global_ExitButton.State == Button.State_Chose) {
            Global_Exit_Interface.State = Button.State_Chose;
        }
    }

    //退出
    public static void Event_ClothesButton_Click(MouseEvent e) {
        ThreadControl.PaintSleepController.eventAwake = true;
        Event_ButtonTwoImageSwitch(Global_ClothesButton);
        //点击切换状态
        if (Global_ClothesButton.State == Button.State_NotChose) {
            Global_Clothes_Interface.State = Button.State_NotDisplay;
            //关闭界面
        } else if (Global_ClothesButton.State == Button.State_Chose) {
            Global_Clothes_Interface.State = Button.State_Chose;
        }
    }

    //皮肤
    public static void Event_SetSize_Click(MouseEvent e) {
        ThreadControl.PaintSleepController.eventAwake = true;
        if (Global_SetUp_Button != null) {
            Button ClickedButton;
            for (int i = 0; i < 12; i++) {
                Button button = Global_SetUp_Button[i];
                if (button.EventJudgeRect.judgePointInRect(e.getX(), e.getY())) {
                    ClickedButton = button;
                    ClickedButton.State = Button.State_Chose;
                    //开启
                    Main.userData.setUp.TempOrder = i;
                    //Temp变量改变
                    for (int j = 0; j < 12; j++) {
                        Button button1 = Global_SetUp_Button[j];
                        if (button1 != ClickedButton) {
                            button1.State = Button.State_NotChose;
                        }
                        //关闭
                    }
                    return;
                }
            }
        }
    }

    //设置大小按钮
    public static void Event_Exit_Click(MouseEvent e) {
        ThreadControl.PaintSleepController.eventAwake = true;
        if (Global_Exit_Button != null) {
            for (int i = 0; i < 2; i++) {
                Button button = Global_Exit_Button[i];
                if (button.EventJudgeRect.judgePointInRect(e.getX(), e.getY())) {
                    //开启
                    if (i == 0) {
                        Main.threadControl.CurrentOperation = ThreadControl.CurrentOperation_Exit;
                    } else {
                        Global_Exit_Interface.State = Button.State_NotDisplay;
                        Global_ExitButton.State = Button.State_NotChose;
                    }
                    return;
                }
            }
        }
    }

    //按钮：关机
    public static void Event_FoodSelectBoxSwitch(MouseEvent e) {
        ThreadControl.PaintSleepController.eventAwake = true;
        if (Global_Food_SelectButton != null) {
            Button ClickedButton;
            for (int i = 0; i < Food_Picture_Num; i++) {
                Button button = Global_Food_SelectButton[i];
                if (button.EventJudgeRect.judgePointInRect(e.getX(), e.getY())) {
                    ClickedButton = button;
                    ClickedButton.State = Button.State_Chose;
                    //开启
                    Main.userData.setUp.TempOrder = i;
                    Food_CurrentOrder = i;
                    //顺序记录
                    ;
                    //Temp变量改变
                    for (int j = 0; j < Food_Picture_Num; j++) {
                        Button button1 = Global_Food_SelectButton[j];
                        if (button1 != ClickedButton) {
                            button1.State = Button.State_NotChose;
                        }
                        //关闭
                    }
                    return;
                }
            }
            Food_CurrentOrder = -1;
        }
    }

    public static void Event_ClothesSelectBoxSwitch(MouseEvent e) {
        ThreadControl.PaintSleepController.eventAwake = true;
        if (Global_Clothes_SelectButton != null) {
            Button ClickedButton;
            for (int i = 0; i < Clothes_Num; i++) {
                Button button = Global_Clothes_SelectButton[i];
                if (button.EventJudgeRect.judgePointInRect(e.getX(), e.getY())) {
                    ClickedButton = button;
                    ClickedButton.State = Button.State_Chose;
                    //开启
                    Main.userData.setUp.TempOrder = i;
                    Clothes_CurrentOrder = i;
                    //顺序记录
                    //Temp变量改变
                    for (int j = 0; j < Clothes_Num; j++) {
                        Button button1 = Global_Clothes_SelectButton[j];
                        if (button1 != ClickedButton) {
                            button1.State = Button.State_NotChose;
                        }
                        //关闭
                    }
                    return;
                }
            }
            Clothes_CurrentOrder = -1;
        }
    }

    //选择框框
    public static void Event_SetFinal_SetUp_Click(MouseEvent e) {
        ThreadControl.PaintSleepController.eventAwake = true;
        Event_ButtonTwoImageSwitch(Global_SetUp_Button[SetUp_Button_Num - 1]);
        Global_SetUp_Interface.State = Button.State_NotDisplay;
        Global_SetUpButton.State = Button.State_NotChose;
        Reset_resetBasicSize();
    }

    public static void Event_SetFinal_ChooseFood_Click(MouseEvent e) {
        ThreadControl.PaintSleepController.eventAwake = true;
        if (Food_CurrentOrder != -1) {//不能没选择
            if (Global_Food_SelectButton != null && Global_Food[Food_CurrentOrder].FoodNumber > 0) {
                Event_ButtonTwoImageSwitch(Global_Food_SelectButton[Food_Picture_Num]);
                Global_FoodSystem_Interface.State = Button.State_NotDisplay;
                Global_BatteryButton.State = Button.State_NotChose;
                Global_Food[Food_CurrentOrder].Judge_DisplayPicture = true;
                Global_Food[Food_CurrentOrder].picture.Dst.x = Global_Food_SelectButton[Food_Picture_Num].Dst.x;
                Global_Food[Food_CurrentOrder].picture.Dst.y = Global_Food_SelectButton[Food_Picture_Num].Dst.y;
                //选择数据
                if (!ActionTool.Global_Lock_FoodAppeared) {//首次出现
                    if (Robot.Global_WonderEye_Operation != null && Robot.Global_WonderEye_Operation.length > 0) {
                        for (ActionObject_Operation o : Robot.Global_WonderEye_Operation) {
                            o.Begin();
                        }
                    }
                    if (Robot.Global_WonderEar_Operation != null && Robot.Global_WonderEar_Operation.length > 0) {
                        for (ActionObject_Operation o : Robot.Global_WonderEar_Operation) {
                            o.Begin();
                        }
                    }
                }
                //想吃事件开始
                Robot.Global_EyesFollow_Operation[0].FollowedObject = InterfaceButton.Global_Food[Food_CurrentOrder].picture;
                Robot.Global_SadEyesFollow_Operation[0].FollowedObject = InterfaceButton.Global_Food[Food_CurrentOrder].picture;
                //指定跟随的食物
            }
        }
    }

    //界面
    public static void Event_SetFinal_ChooseClothes_Click(MouseEvent e) {
        ThreadControl.PaintSleepController.eventAwake = true;
        if (Clothes_CurrentOrder != -1) {//不能没选择
            if (Global_Clothes_SelectButton != null) {
                Event_ButtonTwoImageSwitch(Global_Clothes_SelectButton[Clothes_Num]);
                Global_Clothes_Interface.State = Button.State_NotDisplay;
                Global_BatteryButton.State = Button.State_NotChose;
                //选择数据
                Global_ClothesButton.State = Button.State_NotChose;
                //图标
                Reset_SwitchClothes();
                //皮肤切换判断
            }
        }
    }
    //皮肤
    ;
    //--------------------------------全局事件--------------------------------------

    public static void Display_Button(Button button, Graphics2D g, MyFrame frame) {
        if (button.State != Button.State_NotDisplay) {
            if (button.State == Button.State_NotChose) {
                button.Image_B = button.Array_Image_B[0];
            } else if (button.State == Button.State_Chose) {
                button.Image_B = button.Array_Image_B[1];
            }
            button.Draw(g, frame, Image.SCALE_SMOOTH);
        }
    }

    //绘制按钮
    public static void Display_Interface(InterfaceButton face, Graphics2D g, MyFrame frame) {
        if (face.State == Button.State_Chose) {
            face.Draw(g, frame, Image.SCALE_SMOOTH);
            if (face.List_Button != null && !face.List_Button.isEmpty()) {
                for (Button button : face.List_Button) {
                    Display_Button(button, g, frame);
                }
            }
        }
    }

    //绘制界面
    public static void Display_Texts(InterfaceButton face, Graphics2D g) {
        if (face.State == Button.State_Chose) {
            if (face.List_Texts != null && !face.List_Texts.isEmpty()) {
                for (Display.Text text : face.List_Texts) {
                    if (text.State == Display.Text.State_Display) {
                        text.Display(g);
                    }
                }
            }
            if (face.List_Button != null && face.List_Button.size() > 0) {
                for (Button button : face.List_Button) {
                    if (button.State != Button.State_NotDisplay) {
                        if (button.text != null) {
                            button.text.Display(g);
                        }
                    }
                }
            }
        }
    }

    public static void Display_Texts_Button(Button button, Graphics2D g) {
        if (button.State != Button.State_NotDisplay) {
            if (button.text != null) {
                if (button.text.State == Display.Text.State_Display) {
                    button.text.Display(g);

                }
            }
        }
    }

    //绘制Text
    public static void Display_PictureObject(Graphics2D g) {
        for (int i = 0; i < Food_Picture_Num; i++) {
            if (Global_Food[i].Judge_DisplayPicture &&
                    !Global_Food[i].picture.eventLock.isOnce_Display() &&
                    !Global_Food[i].picture.eventLock.isDisplay_Conventional()) {
                Global_Food[i].picture.Draw(g, Main.MainFrame, Image.SCALE_SMOOTH);
            }
        }
    }
    //绘制PictureObject
    ;
    //--------------------------------全局绘制--------------------------------------

    public static Button Global_SetUpSwitch;
    //FishRoe身上的开关
    public static Button Global_SetUpButton;
    //设置按钮
    public static Button Global_BatteryButton;
    //电池
    public static Button Global_LoveButton;
    //心情
    public static Button Global_ExitButton;
    //退出
    public static Button Global_ClothesButton;
    //皮肤
    public static Button[] Global_SetUp_Button;
    public static int SetUp_Button_Num = 13;
    //按钮
    public static Button[] Global_MoodBar;
    //心情条
    public static Button[] Global_Food_Object;
    public static Button[] Global_Food_SelectButton;
    public static int Food_Picture_Num = 3;
    public static Food[] Global_Food;
    //食物
    public static Button[] Global_Exit_Button;
    //结束
    ;
    //食物图片对象
    public static int Food_Button_Num = Food_Picture_Num + 1;
    public static int Food_CurrentOrder;
    //食物按钮
    public static int Clothes_Num = 5;
    public static int Clothes_CurrentOrder;
    public static Button[] Global_Clothes_Object = new Button[Clothes_Num];
    public static Button[] Global_Clothes_SelectButton = new Button[Clothes_Num + 1];
    //皮肤对象+选择框
    ;
    //--------------------------------全局变量--------------------------------------

    public static class Food {
        public Food(Picture picture) {
            this.picture = picture;
            this.Judge_EventRectChange = false;
            this.Eaten = false;
            this.Judge_DisplayPicture = false;
        }

        //构造器

        public Picture picture;
        public boolean Judge_EventRectChange;
        public boolean Eaten;
        public boolean Judge_DisplayPicture;
        public int FoodNumber;
        //属性

    }

    public static int FoodAddMoodValue = 25;//吃一个食物加25点心情

    //--------------------------------食物类--------------------------------------
    public static final double Zoom_Buttons = 0.15;
    public static final int GapOfButton = 15;
    public static final int SetUpButton_X = 50;
    public static final int BatteryButton_X = (int) (SetUpButton_X + 200 * Zoom_Buttons + GapOfButton);
    public static final int LoveButton_X = (int) (BatteryButton_X + 200 * Zoom_Buttons + GapOfButton);
    public static final int ClothesButton_X = (int) (LoveButton_X + 200 * Zoom_Buttons + GapOfButton);
    public static final int ExitButton_X = (int) (ClothesButton_X + 200 * Zoom_Buttons + GapOfButton);

    //--------------------------------Button数据--------------------------------------

    public static void Global_Interface_Button_Data_Init() {

        Global_Interface_Button_Picture_Init();

        //----------------------------------------图片----------------------------------------

        Global_Interface_Button_LocationData_Init();

        //----------------------------------------数据布局----------------------------------------

        Global_Interface_Button_Text_Init();

        //----------------------------------------文本----------------------------------------

    }

    //图片，界面初始化
    public static void Global_Interface_Button_Picture_Init() {
        Global_SetUpSwitch = new Button(2, Button.State_NotChose);
        Global_SetUpSwitch.Array_Image_B[0] = Button.Switch_Off;
        Global_SetUpSwitch.Array_Image_B[1] = Button.Switch_On;
        Global_SetUpSwitch.Image_B = Global_SetUpSwitch.Array_Image_B[0];
        //开关
        Global_SetUpButton = new Button(2, Button.State_NotDisplay);
        Global_SetUpButton.Array_Image_B[0] = Button.SetUp_NotChose;
        Global_SetUpButton.Array_Image_B[1] = Button.SetUp_Chose;
        Global_SetUpButton.Image_B = Global_SetUpButton.Array_Image_B[0];
        //按钮
        Global_BatteryButton = new Button(2, Button.State_NotDisplay);
        Global_BatteryButton.Array_Image_B[0] = Button.Battery_NotChose;
        Global_BatteryButton.Array_Image_B[1] = Button.Battery_Chose;
        Global_BatteryButton.Image_B = Global_BatteryButton.Array_Image_B[0];
        //电池
        Global_LoveButton = new Button(2, Button.State_NotDisplay);
        Global_LoveButton.Array_Image_B[0] = Button.Love_NotChose;
        Global_LoveButton.Array_Image_B[1] = Button.Love_Chose;
        Global_LoveButton.Image_B = Global_LoveButton.Array_Image_B[0];
        //心情
        Global_ExitButton = new Button(2, Button.State_NotDisplay);
        Global_ExitButton.Array_Image_B[0] = Button.Exit_NotChose;
        Global_ExitButton.Array_Image_B[1] = Button.Exit_Chose;
        Global_ExitButton.Image_B = Global_ExitButton.Array_Image_B[0];
        //心情
        Global_ClothesButton = new Button(2, Button.State_NotDisplay);
        Global_ClothesButton.Array_Image_B[0] = Button.Clothes_NotChose;
        Global_ClothesButton.Array_Image_B[1] = Button.Clothes_Chose;
        Global_ClothesButton.Image_B = Global_ClothesButton.Array_Image_B[0];
        //皮肤
        Global_SetUp_Button = new Button[SetUp_Button_Num];
        //1~12是设置大小
        for (int i = 0; i < SetUp_Button_Num; i++) {
            Global_SetUp_Button[i] = new Button(2, Button.State_NotChose);
            Global_SetUp_Button[i].Array_Image_B[0] = Button.Button_NotChose;
            Global_SetUp_Button[i].Array_Image_B[1] = Button.Button_Chose;
            Global_SetUp_Button[i].Image_B = Global_SetUp_Button[i].Array_Image_B[0];
            if (i == SetUp_Button_Num - 1) {
                Global_SetUp_Button[i].Image_B = Global_SetUp_Button[i].Array_Image_B[1];
                Global_SetUp_Button[i].State = Button.State_Chose;
            }
        }
        Global_SetUp_Button[UserData.SetUp.EnumToOrder(Main.userData.setUp.robotSize)].State = Button.State_Chose;//读取原来的设置
        //13是完成
        Global_MoodBar = new Button[2];
        for (int i = 0; i < 2; i++) {
            Global_MoodBar[i] = new Button(1, Button.State_NotChose);
            if (i == 0) {
                Global_MoodBar[i].Array_Image_B[0] = Button.MoodBar_Empty;
            } else {
                Global_MoodBar[i].Array_Image_B[0] = Button.MoodBar_Filled;
            }
            Global_MoodBar[i].Image_B = Global_MoodBar[i].Array_Image_B[0];
        }
        //1~2是条

        Global_Exit_Button = new Button[2];
        for (int i = 0; i < 2; i++) {
            Global_Exit_Button[i] = new Button(1, Button.State_NotChose);
            Global_Exit_Button[i].Array_Image_B[0] = Button.Button_NotChose;
            Global_Exit_Button[i].Image_B = Global_Exit_Button[i].Array_Image_B[0];
        }
        //退出

        Global_Food_SelectButton = new Button[Food_Button_Num];
        //1~3是选择框
        for (int i = 0; i < Food_Picture_Num; i++) {
            Global_Food_SelectButton[i] = new Button(2, Button.State_NotChose);
            Global_Food_SelectButton[i].Array_Image_B[0] = Button.SelectBox_NotChose;
            Global_Food_SelectButton[i].Array_Image_B[1] = Button.SelectBox_Chose;
            Global_Food_SelectButton[i].Image_B = Global_Food_SelectButton[i].Array_Image_B[0];
        }
        Global_Food_SelectButton[Food_Picture_Num] = new Button(2, Button.State_NotChose);
        Global_Food_SelectButton[Food_Picture_Num].Array_Image_B[0] = Button.Button_NotChose;
        Global_Food_SelectButton[Food_Picture_Num].Array_Image_B[1] = Button.Button_Chose;
        Global_Food_SelectButton[Food_Picture_Num].Image_B = Global_Food_SelectButton[Food_Picture_Num].Array_Image_B[0];
        //4是选择按钮

        Global_Food_Object = new Button[Food_Picture_Num];
        //Global_Food = new Food[Food_Picture_Num];
        for (int i = 0; i < Food_Picture_Num; i++) {
            //Global_Food[i] = new Food(new Picture_Object());
            Global_Food_Object[i] = new Button(1, Button.State_NotChose);
            Global_Food[i].Judge_DisplayPicture = false;
        }
        Global_Food_Object[0].Image_B = Button.Food_Arrow;
        Global_Food[0].picture.Image_B = Button.Food_Arrow;
        Global_Food_Object[1].Image_B = Button.Food_Battery;
        Global_Food[1].picture.Image_B = Button.Food_Battery;
        Global_Food_Object[2].Image_B = Button.Food_Slug;
        Global_Food[2].picture.Image_B = Button.Food_Slug;
        Global_Food_Object[0].Array_Image_B[0] = Global_Food_Object[0].Image_B;
        Global_Food_Object[1].Array_Image_B[0] = Global_Food_Object[1].Image_B;
        Global_Food_Object[2].Array_Image_B[0] = Global_Food_Object[2].Image_B;
        //食物图片

        for (int i = 0; i < Clothes_Num; i++) {
            Global_Clothes_Object[i] = new Button(1, Button.State_NotChose);
            Global_Clothes_Object[i].Image_B = Button.Clothes_ObjectPicture[i];
            Global_Clothes_Object[i].Array_Image_B[0] = Global_Clothes_Object[i].Image_B;
        }
        for (int i = 0; i < Clothes_Num; i++) {
            Global_Clothes_SelectButton[i] = new Button(2, Button.State_NotChose);
            Global_Clothes_SelectButton[i].Array_Image_B[0] = Button.SelectBox_NotChose;
            Global_Clothes_SelectButton[i].Array_Image_B[1] = Button.SelectBox_Chose;
            Global_Clothes_SelectButton[i].Image_B = Global_Clothes_SelectButton[i].Array_Image_B[0];
        }
        Global_Clothes_SelectButton[Clothes_Num] = new Button(2, Button.State_NotChose);
        Global_Clothes_SelectButton[Clothes_Num].Array_Image_B[0] = Button.Button_NotChose;
        Global_Clothes_SelectButton[Clothes_Num].Array_Image_B[1] = Button.Button_Chose;
        Global_Clothes_SelectButton[Clothes_Num].Image_B = Global_Clothes_SelectButton[Clothes_Num].Array_Image_B[0];
        //皮肤图片
        ;
        //---------------Button--------------------

        Global_SetUp_Interface = new InterfaceButton(Button.State_NotDisplay);
        Global_SetUp_Interface.Image_B = InterfaceButton.Interface_Image;
        Global_MoodSystem_Interface = new InterfaceButton(Button.State_NotDisplay);
        Global_MoodSystem_Interface.Image_B = InterfaceButton.Interface_Image;
        Global_FoodSystem_Interface = new InterfaceButton(Button.State_NotDisplay);
        Global_FoodSystem_Interface.Image_B = InterfaceButton.Interface_Image;
        Global_Exit_Interface = new InterfaceButton(Button.State_NotDisplay);
        Global_Exit_Interface.Image_B = InterfaceButton.Interface_Image;
        Global_Clothes_Interface = new InterfaceButton(Button.State_NotDisplay);
        Global_Clothes_Interface.Image_B = InterfaceButton.Interface_Image;

        //---------------Interface--------------------
    }

    //图片
    public static void Global_Interface_Button_LocationData_Init() {
        double BasicZoom = Main.robot.BasicSize / Main.robot.BasicBody.Image_B.getHeight();
        Food_CurrentOrder = -1;//-1为未选择
        Clothes_CurrentOrder = -1;//-1为未选择

        Global_SetUpSwitch.Src = new Display.Rect(0, 0, Global_SetUpSwitch.Image_B.getWidth(), Global_SetUpSwitch.Image_B.getHeight());
        Global_SetUpSwitch.SrcCentre = new Point(Global_SetUpSwitch.Src.width / 2, Global_SetUpSwitch.Src.height / 2);
        Global_SetUpSwitch.Dst = Main.robot.Get_PictureRelativeLocation_OfBasicBody(Global_SetUpSwitch.Src,
                Global_SetUpSwitch.SrcCentre,
                new Point(Main.robot.BasicBody.Dst.width / 2, (int) (Button.Switch_Relative_Y * BasicZoom)),
                (int) (Global_SetUpSwitch.Src.width * BasicZoom),
                (int) (Global_SetUpSwitch.Src.height * BasicZoom));
        Global_SetUpSwitch.CurrentDegree = 0;
        //开关
        Global_SetUpButton.Src = new Display.Rect(0, 0, Global_SetUpButton.Image_B.getWidth(), Global_SetUpButton.Image_B.getHeight());
        Global_SetUpButton.SrcCentre = new Point(Global_SetUpButton.Src.width / 2, Global_SetUpButton.Src.height / 2);
        Global_SetUpButton.Dst = Main.robot.Get_PictureRelativeLocation_OfBasicBody(Global_SetUpButton.Src,
                Global_SetUpButton.SrcCentre,
                new Point(Main.robot.BasicBody.Dst.width + SetUpButton_X,
                        (int) (Main.robot.BasicBody.Dst.height / 2.0)),
                (int) (Global_SetUpButton.Src.width * Zoom_Buttons),
                (int) (Global_SetUpButton.Src.height * Zoom_Buttons));
        Global_SetUpButton.CurrentDegree = 0;
        //按钮
        Global_BatteryButton.Src = new Display.Rect(0, 0, Global_BatteryButton.Image_B.getWidth(), Global_BatteryButton.Image_B.getHeight());
        Global_BatteryButton.SrcCentre = new Point(Global_BatteryButton.Src.width / 2, Global_BatteryButton.Src.height / 2);
        Global_BatteryButton.Dst = Main.robot.Get_PictureRelativeLocation_OfBasicBody(Global_BatteryButton.Src,
                Global_BatteryButton.SrcCentre,
                new Point(Main.robot.BasicBody.Dst.width + BatteryButton_X,
                        (int) (Main.robot.BasicBody.Dst.height / 2.0)),
                (int) (Global_BatteryButton.Src.width * Zoom_Buttons),
                (int) (Global_BatteryButton.Src.height * Zoom_Buttons));
        Global_BatteryButton.CurrentDegree = 0;
        //电池
        Global_LoveButton.Src = new Display.Rect(0, 0, Global_LoveButton.Image_B.getWidth(), Global_LoveButton.Image_B.getHeight());
        Global_LoveButton.SrcCentre = new Point(Global_LoveButton.Src.width / 2, Global_LoveButton.Src.height / 2);
        Global_LoveButton.Dst = Main.robot.Get_PictureRelativeLocation_OfBasicBody(Global_LoveButton.Src,
                Global_LoveButton.SrcCentre,
                new Point(Main.robot.BasicBody.Dst.width + LoveButton_X,
                        (int) (Main.robot.BasicBody.Dst.height / 2.0)),
                (int) (Global_LoveButton.Src.width * Zoom_Buttons),
                (int) (Global_LoveButton.Src.height * Zoom_Buttons));
        Global_LoveButton.CurrentDegree = 0;
        //Love
        Global_ExitButton.Src = new Display.Rect(0, 0, Global_ExitButton.Image_B.getWidth(), Global_ExitButton.Image_B.getHeight());
        Global_ExitButton.SrcCentre = new Point(Global_ExitButton.Src.width / 2, Global_ExitButton.Src.height / 2);
        Global_ExitButton.Dst = Main.robot.Get_PictureRelativeLocation_OfBasicBody(Global_ExitButton.Src,
                Global_ExitButton.SrcCentre,
                new Point(Main.robot.BasicBody.Dst.width + ExitButton_X,
                        (int) (Main.robot.BasicBody.Dst.height / 2.0)),
                (int) (Global_ExitButton.Src.width * Zoom_Buttons),
                (int) (Global_ExitButton.Src.height * Zoom_Buttons));
        Global_ExitButton.CurrentDegree = 0;
        //Exit
        Global_ClothesButton.Src = new Display.Rect(0, 0, Global_ClothesButton.Image_B.getWidth(), Global_ClothesButton.Image_B.getHeight());
        Global_ClothesButton.SrcCentre = new Point(Global_ClothesButton.Src.width / 2, Global_ClothesButton.Src.height / 2);
        Global_ClothesButton.Dst = Main.robot.Get_PictureRelativeLocation_OfBasicBody(Global_ClothesButton.Src,
                Global_ClothesButton.SrcCentre,
                new Point(Main.robot.BasicBody.Dst.width + ClothesButton_X,
                        (int) (Main.robot.BasicBody.Dst.height / 2.0)),
                (int) (Global_ClothesButton.Src.width * Zoom_Buttons),
                (int) (Global_ClothesButton.Src.height * Zoom_Buttons));
        Global_ClothesButton.CurrentDegree = 0;
        //Clothes
        ;
        //---------------Button--------------------

        Global_SetUp_Interface.Src = new Display.Rect(0, 0, Global_SetUp_Interface.Image_B.getWidth(), Global_SetUp_Interface.Image_B.getHeight());
        Global_SetUp_Interface.SrcCentre = new Point(Global_SetUp_Interface.Src.width / 2, Global_SetUp_Interface.Src.height / 2);
        Global_SetUp_Interface.Reset_Dst(new Point((int) (MyFrame.GetScreenWH()[0] / 2.0),
                        (int) (MyFrame.GetScreenWH()[1] / 2.0)),
                new Point(0, 0));
        //Dst
        Global_SetUp_Interface.CurrentDegree = 0;
        //设置界面

        Global_MoodSystem_Interface.Src = new Display.Rect(0, 0, Global_MoodSystem_Interface.Image_B.getWidth(), Global_MoodSystem_Interface.Image_B.getHeight());
        Global_MoodSystem_Interface.SrcCentre = new Point(0, Global_MoodSystem_Interface.Src.height);
        Global_MoodSystem_Interface.Reset_Dst_WithWH(new Point((int) (MyFrame.GetScreenWH()[0] / 2.0),
                        (int) (MyFrame.GetScreenWH()[1] / 2.0)),
                new Point(200, 0), 320, 350);
        Global_MoodSystem_Interface.CurrentDegree = 0;
        //心情界面

        Global_FoodSystem_Interface.Src = new Display.Rect(0, 0, Global_FoodSystem_Interface.Image_B.getWidth(), Global_FoodSystem_Interface.Image_B.getHeight());
        Global_FoodSystem_Interface.SrcCentre = new Point(0, Global_FoodSystem_Interface.Src.height);
        Global_FoodSystem_Interface.Reset_Dst_WithWH(new Point((int) (MyFrame.GetScreenWH()[0] / 2.0),
                        (int) (MyFrame.GetScreenWH()[1] / 2.0)),
                new Point(150, 320), 300, 300);
        Global_FoodSystem_Interface.CurrentDegree = 0;
        //食物界面

        Global_Exit_Interface.Src = new Display.Rect(0, 0, Global_Exit_Interface.Image_B.getWidth(), Global_Exit_Interface.Image_B.getHeight());
        Global_Exit_Interface.SrcCentre = new Point(0, Global_Exit_Interface.Src.height);
        int Exit_w = 280;
        int Exit_y = 150;
        Global_Exit_Interface.Reset_Dst_WithWH(new Point((int) (MyFrame.GetScreenWH()[0] / 2.0),
                        (int) (MyFrame.GetScreenWH()[1] / 2.0)),
                new Point(-Exit_w / 2, Exit_y / 2), Exit_w, Exit_y);
        Global_Exit_Interface.CurrentDegree = 0;
        //退出界面

        Global_Clothes_Interface.Src = new Display.Rect(0, 0, Global_Clothes_Interface.Image_B.getWidth(), Global_Clothes_Interface.Image_B.getHeight());
        Global_Clothes_Interface.SrcCentre = new Point(0, Global_Clothes_Interface.Src.height);
        Global_Clothes_Interface.Reset_Dst_WithWH(new Point((int) (MyFrame.GetScreenWH()[0] / 2.0),
                        (int) (MyFrame.GetScreenWH()[1] / 2.0)),
                new Point(150, -120), 350, 250);
        Global_Clothes_Interface.CurrentDegree = 0;
        //皮肤界面
        ;
        //---------------Interface--------------------

        for (int i = 0; i < 2; i++) {
            Global_MoodBar[i].Src = new Display.Rect(0, 0, Global_MoodBar[i].Image_B.getWidth(), Global_MoodBar[i].Image_B.getHeight());
            Global_MoodBar[i].SrcCentre = new Point(Global_MoodBar[i].Src.width / 2, Global_MoodBar[i].Src.height / 2);
            Global_MoodBar[i].Zoom = 0.5;
            Global_MoodBar[i].Reset_Dst(new Point(Global_FoodSystem_Interface.Dst.width / 2,
                            (int) (Global_MoodSystem_Interface.Dst.height / 2.0 - 50)),
                    new Point(Global_MoodSystem_Interface.Dst.x, Global_MoodSystem_Interface.Dst.y));
            Global_MoodBar[i].CurrentDegree = 0;
            //数据
            Global_MoodSystem_Interface.List_Button_Add(Global_MoodBar[i]);
            //添加到界面
        }
        //MoodBar
        int Gap_x_FoodButton = 70;
        for (int i = 0; i < Food_Picture_Num; i++) {
            Global_Food_SelectButton[i].Src = new Display.Rect(0, 0, Global_Food_SelectButton[i].Image_B.getWidth(), Global_Food_SelectButton[i].Image_B.getHeight());
            Global_Food_SelectButton[i].SrcCentre = new Point(Global_Food_SelectButton[i].Src.width / 2, Global_Food_SelectButton[i].Src.height / 2);
            Global_Food_SelectButton[i].Zoom = 0.2;
            Global_Food_SelectButton[i].Reset_Dst(new Point(40 + Gap_x_FoodButton * i, 50),
                    new Point(Global_FoodSystem_Interface.Dst.x, Global_FoodSystem_Interface.Dst.y));
            Global_Food_SelectButton[i].CurrentDegree = 0;
            //数据
            Global_FoodSystem_Interface.List_Button_Add(Global_Food_SelectButton[i]);
            //添加到界面
        }
        Global_Food_SelectButton[Food_Picture_Num].Src = new Display.Rect(0, 0, Global_Food_SelectButton[Food_Picture_Num].Image_B.getWidth(), Global_Food_SelectButton[Food_Picture_Num].Image_B.getHeight());
        Global_Food_SelectButton[Food_Picture_Num].SrcCentre = new Point(Global_Food_SelectButton[Food_Picture_Num].Src.width / 2, Global_Food_SelectButton[Food_Picture_Num].Src.height / 2);
        Global_Food_SelectButton[Food_Picture_Num].Zoom = 0.5;
        Global_Food_SelectButton[Food_Picture_Num].Reset_Dst(new Point(Global_FoodSystem_Interface.Dst.width / 2, Global_FoodSystem_Interface.Dst.height - 30),
                new Point(Global_FoodSystem_Interface.Dst.x, Global_FoodSystem_Interface.Dst.y));
        Global_Food_SelectButton[Food_Picture_Num].CurrentDegree = 0;
        //数据
        Global_FoodSystem_Interface.List_Button_Add(Global_Food_SelectButton[Food_Picture_Num]);
        //添加到界面

        for (int i = 0; i < Food_Picture_Num; i++) {
            Global_Food_Object[i].Src = new Display.Rect(0, 0, Global_Food_Object[i].Image_B.getWidth(), Global_Food_Object[i].Image_B.getHeight());
            Global_Food_Object[i].SrcCentre = new Point(Global_Food_Object[i].Src.width / 2, Global_Food_Object[i].Src.height / 2);
            Global_Food_Object[i].Zoom = 0.2;
            Global_Food_Object[i].Reset_Dst(new Point(Global_Food_SelectButton[i].Dst.width / 2, Global_Food_SelectButton[i].Dst.height / 2),
                    new Point(Global_Food_SelectButton[i].Dst.x, Global_Food_SelectButton[i].Dst.y));
            Global_Food_Object[i].CurrentDegree = 0;
            //数据
            Global_FoodSystem_Interface.List_Button_Add(Global_Food_Object[i]);
            //添加到界面
            Global_Food[i].picture.Src = new Display.Rect(0, 0, Global_Food[i].picture.Image_B.getWidth(), Global_Food[i].picture.Image_B.getHeight());
            Global_Food[i].picture.SrcCentre = new Point(Global_Food[i].picture.Src.width / 2, Global_Food[i].picture.Src.height / 2);
            double basicZoom = 0.8;
            double finalZoom = Main.robot.Basic_Zoom * basicZoom;
            Global_Food[i].picture.Reset_Dst(new Point(0, 0),
                    new Point(Global_Food_SelectButton[Food_Picture_Num].Dst.x, Global_Food_SelectButton[Food_Picture_Num].Dst.y), finalZoom);
            Global_Food[i].picture.CurrentDegree = 0;
            //数据
        }
        //食物
        int Gap_x_ClothesButton = 70;
        for (int i = 0; i < Clothes_Num; i++) {
            Global_Clothes_SelectButton[i].Src = new Display.Rect(0, 0, Global_Clothes_SelectButton[i].Image_B.getWidth(), Global_Clothes_SelectButton[i].Image_B.getHeight());
            Global_Clothes_SelectButton[i].SrcCentre = new Point(Global_Clothes_SelectButton[i].Src.width / 2, Global_Clothes_SelectButton[i].Src.height / 2);
            Global_Clothes_SelectButton[i].Zoom = 0.2;
            Global_Clothes_SelectButton[i].Reset_Dst(new Point(40 + Gap_x_ClothesButton * i, 50),
                    new Point(Global_Clothes_Interface.Dst.x, Global_Clothes_Interface.Dst.y));
            Global_Clothes_SelectButton[i].CurrentDegree = 0;
            //数据
            Global_Clothes_Interface.List_Button_Add(Global_Clothes_SelectButton[i]);
            //添加到界面
        }
        Global_Clothes_SelectButton[Clothes_Num].Src = new Display.Rect(0, 0, Global_Clothes_SelectButton[Clothes_Num].Image_B.getWidth(), Global_Clothes_SelectButton[Clothes_Num].Image_B.getHeight());
        Global_Clothes_SelectButton[Clothes_Num].SrcCentre = new Point(Global_Clothes_SelectButton[Clothes_Num].Src.width / 2, Global_Clothes_SelectButton[Clothes_Num].Src.height / 2);
        Global_Clothes_SelectButton[Clothes_Num].Zoom = 0.5;
        Global_Clothes_SelectButton[Clothes_Num].Reset_Dst(new Point(Global_Clothes_Interface.Dst.width / 2, Global_Clothes_Interface.Dst.height - 30),
                new Point(Global_Clothes_Interface.Dst.x, Global_Clothes_Interface.Dst.y));
        Global_Clothes_SelectButton[Clothes_Num].CurrentDegree = 0;
        //数据
        Global_Clothes_Interface.List_Button_Add(Global_Clothes_SelectButton[Clothes_Num]);
        //添加到界面

        for (int i = 0; i < Clothes_Num; i++) {
            Global_Clothes_Object[i].Src = new Display.Rect(0, 0, Global_Clothes_Object[i].Image_B.getWidth(), Global_Clothes_Object[i].Image_B.getHeight());
            Global_Clothes_Object[i].SrcCentre = new Point(Global_Clothes_Object[i].Src.width / 2, Global_Clothes_Object[i].Src.height / 2);
            Global_Clothes_Object[i].Zoom = 0.2;
            Global_Clothes_Object[i].Reset_Dst(new Point(Global_Clothes_SelectButton[i].Dst.width / 2, Global_Clothes_SelectButton[i].Dst.height / 2),
                    new Point(Global_Clothes_SelectButton[i].Dst.x, Global_Clothes_SelectButton[i].Dst.y));
            Global_Clothes_Object[i].CurrentDegree = 0;
            //数据
            Global_Clothes_Interface.List_Button_Add(Global_Clothes_Object[i]);
            //添加到界面
        }
        //皮肤
        ;
        //Button2
    }

    //数据布局
    public static void Global_Interface_Button_Text_Init() {

        int ButtonGap_x = 72, Start_x = 180, Start_y = 60, ButtonGap_y = 45;
        int FinalButton_x = 0, FinalButton_y = 50;
        double ButtonZoom = 0.4, FinalButtonZoom = 0.5;

        for (int i = 0; i < 12; i++) {
            Global_SetUp_Button[i].Src = new Display.Rect(0, 0, Global_SetUp_Button[0].Image_B.getWidth(), Global_SetUp_Button[0].Image_B.getHeight());
            Global_SetUp_Button[i].SrcCentre = new Point(Global_SetUp_Button[0].Src.width / 2, Global_SetUp_Button[0].Src.height / 2);
            Global_SetUp_Button[i].Zoom = ButtonZoom;
            if (i < 6) {
                Global_SetUp_Button[i].Reset_Dst(new Point(Start_x + ButtonGap_x * i, Start_y),
                        new Point(Global_SetUp_Interface.Dst.x, Global_SetUp_Interface.Dst.y));
            } else {
                Global_SetUp_Button[i].Reset_Dst(new Point(Start_x + ButtonGap_x * (i - 6), Start_y + ButtonGap_y),
                        new Point(Global_SetUp_Interface.Dst.x, Global_SetUp_Interface.Dst.y));
            }
            Global_SetUp_Button[i].CurrentDegree = 0;
        }

        Global_SetUp_Button[SetUp_Button_Num - 1].Src = new Display.Rect(0, 0, Global_SetUp_Button[SetUp_Button_Num - 1].Image_B.getWidth(), Global_SetUp_Button[SetUp_Button_Num - 1].Image_B.getHeight());
        Global_SetUp_Button[SetUp_Button_Num - 1].SrcCentre = new Point(Global_SetUp_Button[SetUp_Button_Num - 1].Src.width / 2, Global_SetUp_Button[SetUp_Button_Num - 1].Src.height / 2);
        Global_SetUp_Button[SetUp_Button_Num - 1].Zoom = FinalButtonZoom;
        Global_SetUp_Button[SetUp_Button_Num - 1].Reset_Dst(new Point((int) (Global_SetUp_Interface.Dst.width / 2.0 - FinalButton_x),
                        Global_SetUp_Interface.Dst.height - FinalButton_y),
                new Point(Global_SetUp_Interface.Dst.x, Global_SetUp_Interface.Dst.y));
        Global_SetUp_Button[SetUp_Button_Num - 1].CurrentDegree = 0;

        for (int i = 0; i < 13; i++) {
            Global_SetUp_Interface.List_Button_Add(Global_SetUp_Button[i]);
        }

        for (int i = 0; i < 2; i++) {
            Global_Exit_Button[i].Src = new Display.Rect(0, 0, Global_Exit_Button[0].Image_B.getWidth(), Global_Exit_Button[0].Image_B.getHeight());
            Global_Exit_Button[i].SrcCentre = new Point(Global_Exit_Button[0].Src.width / 2, Global_Exit_Button[0].Src.height / 2);
            Global_Exit_Button[i].Zoom = ButtonZoom;
            int start_x = 80;
            int buttonGap_x = 120;
            int start_y = 100;
            Global_Exit_Button[i].Reset_Dst(new Point(start_x + buttonGap_x * i, start_y),
                    new Point(Global_Exit_Interface.Dst.x, Global_Exit_Interface.Dst.y));
            Global_Exit_Button[i].CurrentDegree = 0;
            Global_Exit_Interface.List_Button_Add(Global_Exit_Button[i]);
        }
        //Exit
        ;
        //Button2

        Display.Text Text_SetUp_Interface = new Display.Text(20, "设置");
        Text_SetUp_Interface.State = Display.Text.State_Display;
        Text_SetUp_Interface.Location = new Point((int) (Global_SetUp_Interface.Dst.x + Global_SetUp_Interface.Dst.width / 2.0 - 30),
                Global_SetUp_Interface.Dst.y + 30);
        Display.Text Text2_SetUp_Interface = new Display.Text(20, "设置大小");
        Text2_SetUp_Interface.State = Display.Text.State_Display;
        Text2_SetUp_Interface.Location = new Point((int) (Global_SetUp_Interface.Dst.x + 50),
                Global_SetUp_Interface.Dst.y + Start_y + ButtonGap_y / 2);
        Global_SetUp_Interface.List_Text_Add(Text_SetUp_Interface);
        Global_SetUp_Interface.List_Text_Add(Text2_SetUp_Interface);

        String[] ButtonContent = {"25px", "50px", "75px", "100px", "150px", "200px", "250px", "300px", "350px", "400px", "450px", "500px"};
        Display.Text[] Button_Text = new Display.Text[12];
        int Button_start_x = 13, Button_start_y = 25;
        for (int i = 0; i < 12; i++) {
            Button_Text[i] = new Display.Text(18, ButtonContent[i]);
            Button_Text[i].State = Display.Text.State_Display;
            Button_Text[i].Location = new Point((int) (Global_SetUp_Button[i].Dst.x + Button_start_x),
                    Global_SetUp_Button[i].Dst.y + Button_start_y);
            Global_SetUp_Button[i].text = Button_Text[i];
        }
        Display.Text Final_Button_Text = new Display.Text(20, "完成");
        Final_Button_Text.State = Display.Text.State_Display;
        Final_Button_Text.Location = new Point((int) (Global_SetUp_Button[SetUp_Button_Num - 1].Dst.x + Button_start_x * FinalButtonZoom / ButtonZoom),
                (int) (Global_SetUp_Button[SetUp_Button_Num - 1].Dst.y + Button_start_y * FinalButtonZoom / ButtonZoom));
        Global_SetUp_Button[SetUp_Button_Num - 1].text = Final_Button_Text;

        int moodValue = Main.userData.moodSystem.getMoodValue();
        String stringValue = Integer.toString(moodValue);
        String stringMoodContent = "当前心情:" + stringValue;
        Display.Text moodValue_Text = new Display.Text(18, stringMoodContent);
        moodValue_Text.State = Display.Text.State_Display;
        moodValue_Text.Location = new Point(Global_MoodSystem_Interface.Dst.x + 50,
                Global_MoodBar[1].Dst.y + Global_MoodBar[1].Dst.height + 20);
        Global_MoodSystem_Interface.List_Text_Add(moodValue_Text);
        Display.Text Explain_Text1 = new Display.Text(16, "NoNo的心情值会影响它的动作");
        Explain_Text1.State = Display.Text.State_Display;
        Explain_Text1.Location = new Point(Global_MoodSystem_Interface.Dst.x + 20,
                moodValue_Text.Location.y + 20);
        Display.Text Explain_Text2 = new Display.Text(16, "长时间让NoNo独处会降低心情");
        Explain_Text2.State = Display.Text.State_Display;
        Explain_Text2.Location = new Point(Explain_Text1.Location.x,
                Explain_Text1.Location.y + 20);
        Display.Text Explain_Text3 = new Display.Text(16, "投喂NoNo零件可以增加其心情");
        Explain_Text3.State = Display.Text.State_Display;
        Explain_Text3.Location = new Point(Explain_Text1.Location.x,
                Explain_Text2.Location.y + 20);
        Display.Text Explain_Text4 = new Display.Text(16, "每隔一段时间会自动获得投喂NoNo的零件");
        Explain_Text4.State = Display.Text.State_Display;
        Explain_Text4.Location = new Point(Explain_Text1.Location.x,
                Explain_Text3.Location.y + 20);
        Global_MoodSystem_Interface.List_Text_Add(Explain_Text1);
        Global_MoodSystem_Interface.List_Text_Add(Explain_Text2);
        Global_MoodSystem_Interface.List_Text_Add(Explain_Text3);
        Global_MoodSystem_Interface.List_Text_Add(Explain_Text4);

        Display.Text[] Exit_Text = new Display.Text[3];
        Exit_Text[0] = new Display.Text(18, "您确定要抛下NoNo离去吗？QAQ");
        Exit_Text[1] = new Display.Text(18, "确定");
        Exit_Text[2] = new Display.Text(18, "取消");
        Exit_Text[0].State = Display.Text.State_Display;
        Exit_Text[0].Location = new Point(Global_Exit_Interface.Dst.x + Button_start_x,
                Global_Exit_Interface.Dst.y + Button_start_y);
        for (int i = 1; i < 3; i++) {
            Exit_Text[i].State = Display.Text.State_Display;
            Exit_Text[i].Location = new Point(Global_Exit_Button[i - 1].Dst.x + Button_start_x,
                    Global_Exit_Button[i - 1].Dst.y + Button_start_y);
            Global_Exit_Button[i - 1].text = Exit_Text[i];
        }
        Global_Exit_Interface.List_Text_Add(Exit_Text[0]);
        //Exit

        Display.Text[] Text_FoodNumber = new Display.Text[Food_Picture_Num];
        for (int i = 0; i < Food_Picture_Num; i++) {
            String FoodContend = "剩余:";
            Text_FoodNumber[i] = new Display.Text(18, FoodContend + Global_Food[i].FoodNumber);
            ;
            Text_FoodNumber[i].State = Display.Text.State_Display;
            Text_FoodNumber[i].Location = new Point(Global_Food_SelectButton[i].Dst.x + 1,
                    Global_Food_SelectButton[i].Dst.y + Global_Food_SelectButton[i].Dst.height + 20);
            Global_Food_SelectButton[i].text = Text_FoodNumber[i];
        }

        Display.Text Text_FoodFinalSelect = new Display.Text(20, "选择");
        Text_FoodFinalSelect.State = Display.Text.State_Display;
        Text_FoodFinalSelect.Location = new Point(Global_Food_SelectButton[Food_Picture_Num].Dst.x + 18,
                Global_Food_SelectButton[Food_Picture_Num].Dst.y + 29);
        Global_Food_SelectButton[Food_Picture_Num].text = Text_FoodFinalSelect;

        Display.Text Text_ClothesFinalSelect = new Display.Text(20, "选择");
        Text_ClothesFinalSelect.State = Display.Text.State_Display;
        Text_ClothesFinalSelect.Location = new Point(Global_Clothes_SelectButton[Clothes_Num].Dst.x + 18,
                Global_Clothes_SelectButton[Clothes_Num].Dst.y + 29);
        Global_Clothes_SelectButton[Clothes_Num].text = Text_ClothesFinalSelect;
    }

    //文本
    public static void Global_Interface_Button_Event_Init() {
        Global_SetUpSwitch.EventAdd_MouseClick(InterfaceButton::Event_SetUpSwitch_Click);
        Global_SetUpButton.EventAdd_MouseClick(InterfaceButton::Event_SetUpButton_Click);
        Global_BatteryButton.EventAdd_MouseClick(InterfaceButton::Event_BatteryButton_Click);
        Global_LoveButton.EventAdd_MouseClick(InterfaceButton::Event_LoveButton_Click);
        Global_ExitButton.EventAdd_MouseClick(InterfaceButton::Event_ExitButton_Click);
        Global_ClothesButton.EventAdd_MouseClick(InterfaceButton::Event_ClothesButton_Click);
        Robot.MouseDragged[] mouseDragged_Food = new Robot.MouseDragged[Food_Picture_Num];
        for (int i = 0; i < Food_Picture_Num; i++) {
            Global_Food[i].Judge_EventRectChange = false;
            mouseDragged_Food[i] = new Robot.MouseDragged(Global_Food[i].picture.Dst);
            mouseDragged_Food[i].List_LockedTexts = new ArrayList<>();
            Global_Food[i].picture.EventAdd_MousePress(mouseDragged_Food[i]::Pressed_GetPoint_ChangeEventRect);
            Global_Food[i].picture.EventAdd_MouseDragged(mouseDragged_Food[i]::DraggedPicture_ForInterface);
            Global_Food[i].picture.EventAdd_MouseRelease(Robot::MouseReleased_BeginEat);
        }
        //Button

        Robot.MouseDragged mouseDragged_SetUp_Interface = new Robot.MouseDragged(InterfaceButton.Global_SetUp_Interface.Dst);
        mouseDragged_SetUp_Interface.List_LockedTexts = new ArrayList<>();
        Robot.MouseDragged mouseDragged_MoodSystem_Interface = new Robot.MouseDragged(InterfaceButton.Global_MoodSystem_Interface.Dst);
        mouseDragged_MoodSystem_Interface.List_LockedTexts = new ArrayList<>();
        Robot.MouseDragged mouseDragged_FoodSystem_Interface = new Robot.MouseDragged(InterfaceButton.Global_FoodSystem_Interface.Dst);
        mouseDragged_FoodSystem_Interface.List_LockedTexts = new ArrayList<>();
        Robot.MouseDragged mouseDragged_Exit_Interface = new Robot.MouseDragged(InterfaceButton.Global_Exit_Interface.Dst);
        mouseDragged_Exit_Interface.List_LockedTexts = new ArrayList<>();
        Robot.MouseDragged mouseDragged_Clothes_Interface = new Robot.MouseDragged(InterfaceButton.Global_Clothes_Interface.Dst);
        mouseDragged_Clothes_Interface.List_LockedTexts = new ArrayList<>();
        //管理对象申请
        InterfaceButton.Global_SetUp_Interface.EventAdd_MousePress(mouseDragged_SetUp_Interface::Pressed_GetPoint);
        InterfaceButton.Global_SetUp_Interface.EventAdd_MouseDragged(mouseDragged_SetUp_Interface::DraggedPicture_ForInterface);
        InterfaceButton.Global_MoodSystem_Interface.EventAdd_MousePress(mouseDragged_MoodSystem_Interface::Pressed_GetPoint);
        InterfaceButton.Global_MoodSystem_Interface.EventAdd_MouseDragged(mouseDragged_MoodSystem_Interface::DraggedPicture_ForInterface);
        InterfaceButton.Global_FoodSystem_Interface.EventAdd_MousePress(mouseDragged_FoodSystem_Interface::Pressed_GetPoint);
        InterfaceButton.Global_FoodSystem_Interface.EventAdd_MouseDragged(mouseDragged_FoodSystem_Interface::DraggedPicture_ForInterface);
        InterfaceButton.Global_Exit_Interface.EventAdd_MousePress(mouseDragged_Exit_Interface::Pressed_GetPoint);
        InterfaceButton.Global_Exit_Interface.EventAdd_MouseDragged(mouseDragged_Exit_Interface::DraggedPicture_ForInterface);
        InterfaceButton.Global_Clothes_Interface.EventAdd_MousePress(mouseDragged_Clothes_Interface::Pressed_GetPoint);
        InterfaceButton.Global_Clothes_Interface.EventAdd_MouseDragged(mouseDragged_Clothes_Interface::DraggedPicture_ForInterface);
        if (InterfaceButton.Global_SetUp_Interface.List_Button != null && !InterfaceButton.Global_SetUp_Interface.List_Button.isEmpty()) {
            for (Button button : InterfaceButton.Global_SetUp_Interface.List_Button) {
                mouseDragged_SetUp_Interface.Lock_AddLockPicture(button);
                if (button.text != null) {
                    mouseDragged_SetUp_Interface.Lock_AddLockText(button.text);
                }
            }
        }
        if (InterfaceButton.Global_MoodSystem_Interface.List_Button != null && InterfaceButton.Global_MoodSystem_Interface.List_Button.size() > 0) {
            for (Button button : InterfaceButton.Global_MoodSystem_Interface.List_Button) {
                mouseDragged_MoodSystem_Interface.Lock_AddLockPicture(button);
                if (button.text != null) {
                    mouseDragged_MoodSystem_Interface.Lock_AddLockText(button.text);
                }
            }
        }
        if (InterfaceButton.Global_FoodSystem_Interface.List_Button != null && InterfaceButton.Global_FoodSystem_Interface.List_Button.size() > 0) {
            for (Button button : InterfaceButton.Global_FoodSystem_Interface.List_Button) {
                mouseDragged_FoodSystem_Interface.Lock_AddLockPicture(button);
                if (button.text != null) {
                    mouseDragged_FoodSystem_Interface.Lock_AddLockText(button.text);
                }
            }
        }
        if (InterfaceButton.Global_Exit_Interface.List_Button != null && !InterfaceButton.Global_Exit_Interface.List_Button.isEmpty()) {
            for (Button button : InterfaceButton.Global_Exit_Interface.List_Button) {
                mouseDragged_Exit_Interface.Lock_AddLockPicture(button);
                if (button.text != null) {
                    mouseDragged_Exit_Interface.Lock_AddLockText(button.text);
                }
            }
        }
        if (InterfaceButton.Global_Clothes_Interface.List_Button != null && !InterfaceButton.Global_Clothes_Interface.List_Button.isEmpty()) {
            for (Button button : InterfaceButton.Global_Clothes_Interface.List_Button) {
                mouseDragged_Clothes_Interface.Lock_AddLockPicture(button);
                if (button.text != null) {
                    mouseDragged_Clothes_Interface.Lock_AddLockText(button.text);
                }
            }
        }
        //添加锁定Button+Text
        if (InterfaceButton.Global_SetUp_Interface.List_Texts != null && !InterfaceButton.Global_SetUp_Interface.List_Texts.isEmpty()) {
            for (Display.Text text : InterfaceButton.Global_SetUp_Interface.List_Texts) {
                mouseDragged_SetUp_Interface.Lock_AddLockText(text);
            }
        }
        if (InterfaceButton.Global_MoodSystem_Interface.List_Texts != null && !InterfaceButton.Global_MoodSystem_Interface.List_Texts.isEmpty()) {
            for (Display.Text text : InterfaceButton.Global_MoodSystem_Interface.List_Texts) {
                mouseDragged_MoodSystem_Interface.Lock_AddLockText(text);
            }
        }
        if (InterfaceButton.Global_FoodSystem_Interface.List_Texts != null && !InterfaceButton.Global_FoodSystem_Interface.List_Texts.isEmpty()) {
            for (Display.Text text : InterfaceButton.Global_FoodSystem_Interface.List_Texts) {
                mouseDragged_FoodSystem_Interface.Lock_AddLockText(text);
            }
        }
        if (InterfaceButton.Global_Exit_Interface.List_Texts != null && !InterfaceButton.Global_Exit_Interface.List_Texts.isEmpty()) {
            for (Display.Text text : InterfaceButton.Global_Exit_Interface.List_Texts) {
                mouseDragged_Exit_Interface.Lock_AddLockText(text);
            }
        }
        if (InterfaceButton.Global_Clothes_Interface.List_Texts != null && !InterfaceButton.Global_Clothes_Interface.List_Texts.isEmpty()) {
            for (Display.Text text : InterfaceButton.Global_Clothes_Interface.List_Texts) {
                mouseDragged_Clothes_Interface.Lock_AddLockText(text);
            }
        }
        //添加锁定Text
        ;
        //Interface
        if (Global_SetUp_Button != null) {
            for (int i = 0; i < 12; i++) {
                Button button = Global_SetUp_Button[i];
                button.EventAdd_MouseClick(InterfaceButton::Event_SetSize_Click);
            }
            Global_SetUp_Button[SetUp_Button_Num - 1].EventAdd_MouseClick(InterfaceButton::Event_SetFinal_SetUp_Click);
        }
        //设置
        if (Global_Food_SelectButton != null) {
            for (int i = 0; i < Food_Picture_Num; i++) {
                Global_Food_SelectButton[i].EventAdd_MouseClick(InterfaceButton::Event_FoodSelectBoxSwitch);
            }
            Global_Food_SelectButton[Food_Picture_Num].EventAdd_MouseClick(InterfaceButton::Event_SetFinal_ChooseFood_Click);
        }
        //食物
        if (Global_Clothes_SelectButton != null) {
            for (int i = 0; i < Clothes_Num; i++) {
                Global_Clothes_SelectButton[i].EventAdd_MouseClick(InterfaceButton::Event_ClothesSelectBoxSwitch);
            }
            Global_Clothes_SelectButton[Clothes_Num].EventAdd_MouseClick(InterfaceButton::Event_SetFinal_ChooseClothes_Click);
        }
        //皮肤
        if (Global_Exit_Button != null) {
            for (int i = 0; i < 2; i++) {
                Button button = Global_Exit_Button[i];
                button.EventAdd_MouseClick(InterfaceButton::Event_Exit_Click);
            }
        }
        //退出
        ;
        //Button2
    }
    //事件初始化
    ;
    //设置完毕后
    ;
    //--------------------------------按钮全局变量--------------------------------------
}
