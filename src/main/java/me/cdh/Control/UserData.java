package me.cdh.Control;

import me.cdh.Main;
import me.cdh.DestopPet.ActionTool;
import me.cdh.DestopPet.InterfaceButton;
import me.cdh.Draw.Display;

import java.io.*;

public class UserData {

    public UserData(RobotSize SetSize, int MoodValue, String DataPath) {
        this.Init(SetSize, MoodValue, DataPath);
    }

    //--------------------------------构造器--------------------------------

    private void Init(RobotSize SetSize, int MoodValue, String DataPath) {
        this.setUp = new SetUp(SetSize);
        this.moodSystem = new Mood(MoodValue);
        this.DataPath = DataPath;
        this.foods = InterfaceButton.Global_Food;
        this.clothes = new Clothes();
        //记录数据
    }
    //初始化

    public void BasicInit() {
        this.setUp = new SetUp(RobotSize.SIZE_200);
        this.moodSystem = new Mood(100);
        this.clothes = new Clothes();
    }
    //无数据基础初始化

    public int[] Get_writeData() {
        if (this.setUp.robotSize == null || this.moodSystem == null) {
            BasicInit();
        }
        int size = SetUp.EnumToNum(this.setUp.robotSize);
        int[] Data = new int[2 + this.foods.length + 1];
        Data[0] = size;
        Data[1] = this.moodSystem.MoodValue;
        for (int i = 2; i < this.foods.length + 2; i++) {
            Data[i] = this.foods[i - 2].FoodNumber;
        }
        Data[(2 + this.foods.length + 1) - 1] = this.clothes.ClothesType;
        return Data;
    }

    //获取写出Write数据
    public void Get_readData(int[] data) {
        this.setUp.robotSize = SetUp.NumToEnum(data[0]);
        this.moodSystem.MoodValue = data[1];
        for (int i = 2; i < this.foods.length + 2; i++) {
            this.foods[i - 2].FoodNumber = data[i];
            if (InterfaceButton.Global_Food != null) {
                InterfaceButton.Global_Food[i - 2].FoodNumber = data[i];
            }
        }
        this.clothes.ClothesType = data[(2 + this.foods.length + 1) - 1];
    }
    //获取写入Read数据:设置数据
    ;
    //--------------------------------初始化--------------------------------

    public SetUp setUp;//设置
    public Mood moodSystem;//心情系统
    public String DataPath;//文件路径 = "UserData/SetUp.txt"
    public InterfaceButton.Food[] foods = new InterfaceButton.Food[InterfaceButton.Food_Picture_Num];//食物数据
    public Clothes clothes;
    public static int InitialQuantity_Food = 10;

    //--------------------------------属性--------------------------------

    public static class SetUp {
        public SetUp(RobotSize SetSize) {
            Init(SetSize);
        }
        //----------------构造器----------------

        public RobotSize robotSize;
        //设置大小
        public int TempOrder;

        //----------------属性----------------

        public void Init(RobotSize SetSize) {
            this.robotSize = SetSize;
        }

        //----------------初始化----------------

        public static int EnumToNum(RobotSize robotSize) {
            return switch (robotSize) {
                case SIZE_25 -> 25;
                case SIZE_50 -> 50;
                case SIZE_75 -> 75;
                case SIZE_100 -> 100;
                case SIZE_150 -> 150;
                case SIZE_200 -> 200;
                case SIZE_250 -> 250;
                case SIZE_300 -> 300;
                case SIZE_350 -> 350;
                case SIZE_400 -> 400;
                case SIZE_450 -> 450;
                case SIZE_500 -> 500;
            };
        }

        public static RobotSize NumToEnum(int size) {
            return switch (size) {
                case 25 -> RobotSize.SIZE_25;
                case 50 -> RobotSize.SIZE_50;
                case 75 -> RobotSize.SIZE_75;
                case 100 -> RobotSize.SIZE_100;
                case 150 -> RobotSize.SIZE_150;
                case 250 -> RobotSize.SIZE_250;
                case 300 -> RobotSize.SIZE_300;
                case 350 -> RobotSize.SIZE_350;
                case 400 -> RobotSize.SIZE_400;
                case 450 -> RobotSize.SIZE_450;
                case 500 -> RobotSize.SIZE_500;
                default -> RobotSize.SIZE_200;
            };
        }

        public static int EnumToOrder(RobotSize robotSize) {
            return switch (robotSize) {
                case SIZE_25 -> 0;
                case SIZE_50 -> 1;
                case SIZE_75 -> 2;
                case SIZE_100 -> 3;
                case SIZE_150 -> 4;
                case SIZE_200 -> 5;
                case SIZE_250 -> 6;
                case SIZE_300 -> 7;
                case SIZE_350 -> 8;
                case SIZE_400 -> 9;
                case SIZE_450 -> 10;
                case SIZE_500 -> 11;
            };
        }

        public static RobotSize OrderToEnum(int order) {
            return switch (order) {
                case 0 -> RobotSize.SIZE_25;
                case 1 -> RobotSize.SIZE_50;
                case 2 -> RobotSize.SIZE_75;
                case 3 -> RobotSize.SIZE_100;
                case 4 -> RobotSize.SIZE_150;
                case 6 -> RobotSize.SIZE_250;
                case 7 -> RobotSize.SIZE_300;
                case 8 -> RobotSize.SIZE_350;
                case 9 -> RobotSize.SIZE_400;
                case 10 -> RobotSize.SIZE_450;
                case 11 -> RobotSize.SIZE_500;
                default -> RobotSize.SIZE_200;
            };
        }

        //----------------关系转换----------------
    }

    //--------------------------------设置类--------------------------------

    public static class Mood {
        public Mood(int MoodValue) {
            this.setMoodValue(MoodValue);
            Judge_CurrentMood();
        }
        //----------------构造器----------------

        public static int CurrentMood_Happy = 333, CurrentMood_Simple = 666, CurrentMood_Sad = 999, CurrentMood_Range = 1200;

        //----------------final常量----------------
        public int CurrentMood;
        private int MoodValue;

        //----------------属性----------------

        public int getMoodValue() {
            return MoodValue;
        }

        //获取数据
        public void setMoodValue(int moodValue) {
            if (moodValue >= 0 && moodValue <= 100) {
                this.MoodValue = moodValue;
            } else if (moodValue < 0) {
                this.MoodValue = 0;
            } else {
                this.MoodValue = 100;
            }
            if (Main.userData != null) {
                Main.userData.writeData();
            }
        }
        //设置数据0~100

        public void Judge_CurrentMood() {
            if (MoodValue <= 25) {
                this.CurrentMood = CurrentMood_Range;
            } else if (MoodValue <= 50) {
                this.CurrentMood = CurrentMood_Sad;
            } else if (MoodValue <= 75) {
                this.CurrentMood = CurrentMood_Simple;
            } else {
                CurrentMood = CurrentMood_Happy;
            }
        }

        public void EarAngleProgress() {
            int BasicGap = 10, BasicGap1 = 20;
            if (CurrentMood == CurrentMood_Sad) {
                Main.robot.Ears[0].TargetDegree = -Main.robot.Ears_StartTargetDegree - BasicGap;
                Main.robot.Ears[1].TargetDegree = Main.robot.Ears_StartTargetDegree + BasicGap;
                Main.robot.Foots[0].TargetDegree = Main.robot.Foots_StartTargetDegree - BasicGap;
                Main.robot.Foots[1].TargetDegree = -Main.robot.Foots_StartTargetDegree + BasicGap;
            } else if (CurrentMood == CurrentMood_Range) {
                Main.robot.Ears[0].TargetDegree = -Main.robot.Ears_StartTargetDegree - BasicGap1;
                Main.robot.Ears[1].TargetDegree = Main.robot.Ears_StartTargetDegree + BasicGap1;
                Main.robot.Foots[0].TargetDegree = Main.robot.Foots_StartTargetDegree - BasicGap1;
                Main.robot.Foots[1].TargetDegree = -Main.robot.Foots_StartTargetDegree + BasicGap1;
            } else {
                Main.robot.Ears[0].TargetDegree = -Main.robot.Ears_StartTargetDegree;
                Main.robot.Ears[1].TargetDegree = Main.robot.Ears_StartTargetDegree;
                Main.robot.Foots[0].TargetDegree = Main.robot.Foots_StartTargetDegree;
                Main.robot.Foots[1].TargetDegree = -Main.robot.Foots_StartTargetDegree;
            }
        }

        //----------------数值处理----------------

        public static void Refresh_MoodBar_Text(InterfaceButton.Button Bar, InterfaceButton.Button Bar_Empty, Display.Text text) {
            int CurrentValue = Main.userData.moodSystem.MoodValue;
            double Ratio = CurrentValue / 100.0;
            int Gap_height = (int) (Bar_Empty.Src.height * (1 - Ratio));
            Bar.Src.y = Gap_height;
            Bar.Src.height = Bar.Image_B.getHeight() - Gap_height;
            Bar.Dst.y = (int) (Bar_Empty.Dst.y + Gap_height * Bar.Zoom);
            Bar.Dst.height = (int) (Bar_Empty.Dst.height - Gap_height * Bar.Zoom);
            text.Content = "当前心情:" + CurrentValue;
        }

        //----------------刷新心情条和String数据----------------

        public static long CostTime = 1000L * 60 * 10;//10分钟减去1
        public static long AddTime = 1000L * 60 * 60;//60分钟获得一个25
        public static long LastCostTime = -1L;
        public static long LastAddTime = -1L;

        public void Cost() {
            if (LastCostTime == -1) {
                LastCostTime = ThreadControl.ThreadStartTime;
            }
            //初始化
            if (System.currentTimeMillis() - LastCostTime >= CostTime) {
                int moodValue = this.MoodValue;
                moodValue--;
                this.setMoodValue(moodValue);
                LastCostTime = System.currentTimeMillis();
            }
            //心情减去
        }//心情减

        public void FoodAdd() {
            if (LastAddTime == -1) {
                LastAddTime = ThreadControl.ThreadStartTime;
            }
            //初始化

            if (System.currentTimeMillis() - LastAddTime >= AddTime) {
                int Order = ActionTool.Random.Get_Int_SetRange(0, 3);
                InterfaceButton.Global_Food[Order].FoodNumber++;
                Main.userData.foods[Order].FoodNumber = InterfaceButton.Global_Food[Order].FoodNumber;
                LastAddTime = System.currentTimeMillis();
                Main.userData.writeData();//存档
            }
        }//食物加

        //----------------时间系统----------------

        public void mainMood() {
            this.Cost();
            this.FoodAdd();
            this.Judge_CurrentMood();
            this.EarAngleProgress();
        }

        //----------------主要管理----------------
    }

    //--------------------------------心情类--------------------------------

    public static class Clothes {
        public Clothes() {
            this.ClothesType = ClothesType_Basic;
        }

        public int ClothesType;
        public static final int ClothesType_Basic = 0, ClothesType_Fat = 1, ClothesType_Null = 2, ClothesType_Sakura = 3, ClothesType_SakuraNull = 4;//按照Order顺序
        //Final属性
    }

    //--------------------------------皮肤类--------------------------------

    public void writeData() {
//        File file = new File("target/classes/me/cdh/UserData/Setup.data");
//        int[] data = this.Get_writeData();
//        try (
//                var input = UserData.class.getResourceAsStream("/me/cdh/" + DataPath);
//                var inputStream = new BufferedReader(new InputStreamReader(Objects.requireNonNull(input)));
//                var output = new BufferedWriter(new FileWriter(file))
//        ) {
//            while (inputStream.readLine() != null) {
//                for (int datum : data) {
//                    output.write(datum + "\t");
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        File f = new File(DataPath);
        try {
            if (!f.exists()) {//不存在
                f.createNewFile();
            }
            //不存在则创建
            int[] Data = this.Get_writeData();
            //获取按顺序的结构数组
            try (FileWriter fw = new FileWriter(f)) {
                for (int data : Data) {
                    fw.write(data + "\t");
                }
                fw.write("\r\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
            //抛出异常
        }
    }
    //存档：按照结构数组存储

    public int[] readData() {
//        String readLine;
//        String[] recorder;
//        int[] data = new int[0];
//        try (
//                InputStream input = UserData.class.getResourceAsStream("/me/cdh/" + DataPath);
//                BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(input)));
//        ) {
//            while ((readLine = reader.readLine()) != null) {
//                recorder = readLine.split("\t");
//                data = new int[recorder.length];
//                for (int i = 0; i < recorder.length; i++) {
//                    data[i] = Integer.parseInt(recorder[i]);
//                }
//            }
//            return data;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        File f = new File(DataPath);
        int[] Data = new int[0];
        if (!f.exists()) {//不存在的情况
            try {
                f.createNewFile();
                Data = this.createNewEmpty(f);
            } catch (IOException e) {
                throw new RuntimeException(e);
                //抛出异常
            }
        }
        //不存在则创建
        else {
            try (
                    Reader r = new FileReader(f);
                    BufferedReader br = new BufferedReader(r)
            ) {
                String readLine;//光标
                String[] Recorder;//存放被劈开的数组
                while ((readLine = br.readLine()) != null) {
                    Recorder = readLine.split("\t");//跳过\t
                    Data = new int[Recorder.length];//初始化
                    for (int i = 0; i < Recorder.length; i++) {
                        Data[i] = Integer.parseInt(Recorder[i]);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
                //抛出异常
            }
        }
        return Data;
    }
    //读档：按照文章获取结构数组

    private int[] createNewEmpty(File f) throws IOException {
        if (this.setUp.robotSize == null || this.moodSystem == null || this.foods == null || this.clothes == null) {
            this.BasicInit();
        }
        this.foods = InterfaceButton.Global_Food;
        for (var food : this.foods) {
            food.FoodNumber = InitialQuantity_Food;
        }
        //没有数据就数据初始化
        int[] Data = this.Get_writeData();
        //获取按顺序的结构数组
        try (var fw = new FileWriter(f)) {
            for (int data : Data) {
                fw.write(data + "\t");
            }
            fw.write("\r\n");
        }
        return Data;
    }
    //创建一个空的(初次加载)
    ;
    //--------------------------------数据I/O--------------------------------
}
