package me.cdh.Control;

import me.cdh.DestopPet.ActionTool;
import me.cdh.DestopPet.Interface_Button;
import me.cdh.Draw.Display;
import me.cdh.Main.MainAWT;

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
        this.foods = Interface_Button.Global_Food;
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
            if (Interface_Button.Global_Food != null) {
                Interface_Button.Global_Food[i - 2].FoodNumber = data[i];
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
    public Interface_Button.Food[] foods = new Interface_Button.Food[Interface_Button.Food_Picture_Num];//食物数据
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
            int size = 200;
            if (robotSize == RobotSize.SIZE_100) {
                size = 100;
            } else if (robotSize == RobotSize.SIZE_150) {
                size = 150;
            } else if (robotSize == RobotSize.SIZE_250) {
                size = 250;
            } else if (robotSize == RobotSize.SIZE_300) {
                size = 300;
            } else if (robotSize == RobotSize.SIZE_350) {
                size = 350;
            } else if (robotSize == RobotSize.SIZE_400) {
                size = 400;
            } else if (robotSize == RobotSize.SIZE_450) {
                size = 450;
            } else if (robotSize == RobotSize.SIZE_500) {
                size = 500;
            } else if (robotSize == RobotSize.SIZE_25) {
                size = 25;
            } else if (robotSize == RobotSize.SIZE_50) {
                size = 50;
            } else if (robotSize == RobotSize.SIZE_75) {
                size = 75;
            }

            return size;
        }

        public static RobotSize NumToEnum(int size) {
            RobotSize robotSize1 = RobotSize.SIZE_200;
            if (size == 100) {
                robotSize1 = RobotSize.SIZE_100;
            } else if (size == 150) {
                robotSize1 = RobotSize.SIZE_150;
            } else if (size == 200) {
                robotSize1 = RobotSize.SIZE_200;
            } else if (size == 250) {
                robotSize1 = RobotSize.SIZE_250;
            } else if (size == 300) {
                robotSize1 = RobotSize.SIZE_300;
            } else if (size == 350) {
                robotSize1 = RobotSize.SIZE_350;
            } else if (size == 400) {
                robotSize1 = RobotSize.SIZE_400;
            } else if (size == 450) {
                robotSize1 = RobotSize.SIZE_450;
            } else if (size == 500) {
                robotSize1 = RobotSize.SIZE_500;
            } else if (size == 25) {
                robotSize1 = RobotSize.SIZE_25;
            } else if (size == 50) {
                robotSize1 = RobotSize.SIZE_50;
            } else if (size == 75) {
                robotSize1 = RobotSize.SIZE_75;
            }
            return robotSize1;
        }

        public static int EnumToOrder(RobotSize robotSize) {
            int size = 5;
            if (robotSize == RobotSize.SIZE_100) {
                size = 3;
            } else if (robotSize == RobotSize.SIZE_150) {
                size = 4;
            } else if (robotSize == RobotSize.SIZE_200) {
                size = 5;
            } else if (robotSize == RobotSize.SIZE_250) {
                size = 6;
            } else if (robotSize == RobotSize.SIZE_300) {
                size = 7;
            } else if (robotSize == RobotSize.SIZE_350) {
                size = 8;
            } else if (robotSize == RobotSize.SIZE_400) {
                size = 9;
            } else if (robotSize == RobotSize.SIZE_450) {
                size = 10;
            } else if (robotSize == RobotSize.SIZE_500) {
                size = 11;
            } else if (robotSize == RobotSize.SIZE_25) {
                size = 0;
            } else if (robotSize == RobotSize.SIZE_50) {
                size = 1;
            } else if (robotSize == RobotSize.SIZE_75) {
                size = 2;
            }

            return size;
        }

        public static RobotSize OrderToEnum(int order) {
            RobotSize robotSize1 = RobotSize.SIZE_200;
            if (order == 3) {
                robotSize1 = RobotSize.SIZE_100;
            } else if (order == 4) {
                robotSize1 = RobotSize.SIZE_150;
            } else if (order == 5) {
                robotSize1 = RobotSize.SIZE_200;
            } else if (order == 6) {
                robotSize1 = RobotSize.SIZE_250;
            } else if (order == 7) {
                robotSize1 = RobotSize.SIZE_300;
            } else if (order == 8) {
                robotSize1 = RobotSize.SIZE_350;
            } else if (order == 9) {
                robotSize1 = RobotSize.SIZE_400;
            } else if (order == 10) {
                robotSize1 = RobotSize.SIZE_450;
            } else if (order == 11) {
                robotSize1 = RobotSize.SIZE_500;
            } else if (order == 0) {
                robotSize1 = RobotSize.SIZE_25;
            } else if (order == 1) {
                robotSize1 = RobotSize.SIZE_50;
            } else if (order == 2) {
                robotSize1 = RobotSize.SIZE_75;
            }
            return robotSize1;
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
            if (MainAWT.userData != null) {
                MainAWT.userData.WriteData();
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
                MainAWT.robot.Ears[0].TargetDegree = -MainAWT.robot.Ears_StartTargetDegree - BasicGap;
                MainAWT.robot.Ears[1].TargetDegree = MainAWT.robot.Ears_StartTargetDegree + BasicGap;
                MainAWT.robot.Foots[0].TargetDegree = MainAWT.robot.Foots_StartTargetDegree - BasicGap;
                MainAWT.robot.Foots[1].TargetDegree = -MainAWT.robot.Foots_StartTargetDegree + BasicGap;
            } else if (CurrentMood == CurrentMood_Range) {
                MainAWT.robot.Ears[0].TargetDegree = -MainAWT.robot.Ears_StartTargetDegree - BasicGap1;
                MainAWT.robot.Ears[1].TargetDegree = MainAWT.robot.Ears_StartTargetDegree + BasicGap1;
                MainAWT.robot.Foots[0].TargetDegree = MainAWT.robot.Foots_StartTargetDegree - BasicGap1;
                MainAWT.robot.Foots[1].TargetDegree = -MainAWT.robot.Foots_StartTargetDegree + BasicGap1;
            } else {
                MainAWT.robot.Ears[0].TargetDegree = -MainAWT.robot.Ears_StartTargetDegree;
                MainAWT.robot.Ears[1].TargetDegree = MainAWT.robot.Ears_StartTargetDegree;
                MainAWT.robot.Foots[0].TargetDegree = MainAWT.robot.Foots_StartTargetDegree;
                MainAWT.robot.Foots[1].TargetDegree = -MainAWT.robot.Foots_StartTargetDegree;
            }
        }

        //----------------数值处理----------------

        public static void Refresh_MoodBar_Text(Interface_Button.Button Bar, Interface_Button.Button Bar_Empty, Display.Text text) {
            int CurrentValue = MainAWT.userData.moodSystem.MoodValue;
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
            if (LastCostTime == -1L) {
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
            if (LastAddTime == -1L) {
                LastAddTime = ThreadControl.ThreadStartTime;
            }
            //初始化

            if (System.currentTimeMillis() - LastAddTime >= AddTime) {
                int Order = ActionTool.Random.Get_Int_SetRange(0, 3);
                Interface_Button.Global_Food[Order].FoodNumber++;
                MainAWT.userData.foods[Order].FoodNumber = Interface_Button.Global_Food[Order].FoodNumber;
                LastAddTime = System.currentTimeMillis();
                MainAWT.userData.WriteData();//存档
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

    public void WriteData() {
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
        try{
            if(!f.exists()){//不存在
                f.createNewFile();
            }
            //不存在则创建
            int[] Data = this.Get_writeData();
            //获取按顺序的结构数组
            FileWriter fw = new FileWriter(f);
            for(int data : Data){
                fw.write(data + "\t");
            }
            fw.write("\r\n");
            fw.close();
        }
        catch (IOException e){
            throw new RuntimeException(e);
            //抛出异常
        }
    }
    //存档：按照结构数组存储

    public int[] ReadData() {
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
                Data = this.CreateNewEmpty(f);
            } catch (IOException e) {
                throw new RuntimeException(e);
                //抛出异常
            }
        }
        //不存在则创建
        else {
            try {
                Reader r = new FileReader(f);
                BufferedReader br = new BufferedReader(r);
                String readLine;//光标
                String[] Recorder;//存放被劈开的数组
                while ((readLine = br.readLine()) != null) {
                    Recorder = readLine.split("\t");//跳过\t
                    Data = new int[Recorder.length];//初始化
                    for (int i = 0; i < Recorder.length; i++) {
                        Data[i] = Integer.parseInt(Recorder[i]);
                    }
                }
                br.close();
                r.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
                //抛出异常
            }
        }
        return Data;
    }
    //读档：按照文章获取结构数组

    private int[] CreateNewEmpty(File f) throws IOException {
        if (this.setUp.robotSize == null || this.moodSystem == null || this.foods == null || this.clothes == null) {
            this.BasicInit();
        }
        this.foods = Interface_Button.Global_Food;
        for (Interface_Button.Food food : this.foods) {
            food.FoodNumber = InitialQuantity_Food;
        }
        //没有数据就数据初始化
        int[] Data = this.Get_writeData();
        //获取按顺序的结构数组
        FileWriter fw = new FileWriter(f);
        for (int data : Data) {
            fw.write(data + "\t");
        }
        fw.write("\r\n");
        fw.close();
        return Data;
    }
    //创建一个空的(初次加载)
    ;
    //--------------------------------数据I/O--------------------------------
}
