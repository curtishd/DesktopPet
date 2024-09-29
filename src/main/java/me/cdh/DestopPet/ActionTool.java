package me.cdh.DestopPet;

import me.cdh.Main;
import me.cdh.Control.UserData;
import me.cdh.DrawControl.MyFrame;

import java.awt.*;
import java.util.ArrayList;

public class ActionTool {

    public ActionTool(){
        this.Init();
    }

    //--------------------------------构造方法--------------------------------------



    //--------------------------------函数指针--------------------------------------



    //--------------------------------final常量--------------------------------------



    //--------------------------------属性--------------------------------------

    public void Init(){
        //当前动作设为null
        List_Action_FaceEmoji = new ArrayList<>();
        List_Action_EarFootsShake = new ArrayList<>();
        List_Action_Move = new ArrayList<>();
        List_Special_FoodAppeared = new ArrayList<>();
        List_Special_FoodEaten = new ArrayList<>();
        //链表初始化
        Global_Lock_EarFootsShake = false;
        Global_Lock_FaceEmoji = false;
        Global_Lock_Move = false;
        Global_Lock_FoodAppeared = false;
        Global_Lock_FoodEaten = false;
        //未上锁
    }

    //--------------------------------初始化--------------------------------------

    public static boolean Global_Lock_FaceEmoji;//上锁：CheckApplication()；解锁：ActionObject.FPS.Unlock_ByType()
    public static boolean Global_Lock_EarFootsShake;//上锁：CheckApplication()；解锁：ActionObject.FPS.Unlock_ByType()
    public static boolean Global_Lock_Move;//上锁：CheckApplication()；解锁：ActionObject.FPS.Unlock_ByType()
    public static boolean Global_Lock_FoodAppeared;//食物出现 上锁：CheckFoodExist()；解锁：
    public static boolean Global_Lock_FoodEaten;//食物被吃 上锁：CheckEatAction()；解锁：
    //全局动作锁

    public ArrayList<ActionObject> List_Action_FaceEmoji;
    public ArrayList<ActionObject> List_Action_EarFootsShake;
    public ArrayList<ActionObject> List_Action_Move;
    public ArrayList<ActionObject> List_Special_FoodAppeared;
    public ArrayList<ActionObject> List_Special_FoodEaten;
    //只要有一个动作在执行，就将改为禁止访问

    public void Add_RegisterAction(ActionObject o){
        if(o.ActionType == ActionObject.ActionType_FaceEmoji){
            if(List_Action_FaceEmoji != null){
                List_Action_FaceEmoji.add(o);
            }
        }
        else if (o.ActionType == ActionObject.ActionType_EarFootsShake) {
            if(List_Action_EarFootsShake != null){
                List_Action_EarFootsShake.add(o);
            }
        }
        else if(o.ActionType == ActionObject.ActionType_Move){
            if(List_Action_Move != null){
                List_Action_Move.add(o);
            }
        }
        else if(o.ActionType == ActionObject.ActionType_FoodAppeared){
            if(List_Special_FoodAppeared != null){
                List_Special_FoodAppeared.add(o);
            }
        }
        else if(o.ActionType == ActionObject.ActionType_Eaten){
            if(List_Special_FoodEaten != null){
                List_Special_FoodEaten.add(o);
            }
        }
    }
    //添加一个对象

    //删除一个对象

    //通用删除

    public static void Global_AddAllAction(ActionTool Action_Tool){
        Robot.Global_Blink = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_Blink_Operation,ActionObject.ActionType_FaceEmoji);
        Robot.Global_Shake = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_Shake_Operation,ActionObject.ActionType_EarFootsShake);
        Robot.Global_Squint = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_Squint_Operation,ActionObject.ActionType_FaceEmoji);
        Robot.Global_Doubt = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_Doubt_Operation,ActionObject.ActionType_FaceEmoji);
        Robot.Global_Dizzy = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_Dizzy_Operation,ActionObject.ActionType_FaceEmoji);
        Robot.Global_Dizzy.dizzy = new ActionObject.Dizzy();
        Robot.Global_WonderEar = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_WonderEar_Operation,ActionObject.ActionType_FoodAppeared);
        Robot.Global_WonderEye = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_WonderEye_Operation,ActionObject.ActionType_FoodAppeared);
        Robot.Global_StarEyes = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_StarEyes_Operation,ActionObject.ActionType_FoodAppeared);
        Robot.Global_ShakeEarHappy = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_ShakeEarHappy_Operation,ActionObject.ActionType_FoodAppeared);
        Robot.Global_EyesFollow = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_EyesFollow_Operation,ActionObject.ActionType_FoodAppeared);
        Robot.Global_DoubtBeforeFollow = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_DoubtBeforeFollow_Operation,ActionObject.ActionType_FoodAppeared);
        Robot.Global_SquintStruggle = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_SquintStruggle_Operation,ActionObject.ActionType_FoodAppeared);
        Robot.Global_ShakeEar_Struggle = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_ShakeEar_Struggle_Operation,ActionObject.ActionType_FoodAppeared);
        Robot.Global_Angry = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_Angry_Operation,ActionObject.ActionType_FoodAppeared);
        Robot.Global_SquintStruggle1 = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_SquintStruggle_Operation1,ActionObject.ActionType_FoodAppeared);
        Robot.Global_ShakeEar_Struggle1 = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_ShakeEar_Struggle_Operation1,ActionObject.ActionType_FoodAppeared);
        Robot.Global_Sad = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_Sad_Operation,ActionObject.ActionType_FoodAppeared);
        Robot.Global_SadEar = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_SadEar_Operation,ActionObject.ActionType_FoodAppeared);
        Robot.Global_GrievanceSquint = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_GrievanceSquint_Operation,ActionObject.ActionType_FoodAppeared);
        Robot.Global_ShakeEar_Struggle2 = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_ShakeEar_Struggle_Operation2,ActionObject.ActionType_FoodAppeared);
        Robot.Global_GrievanceAngry = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_GrievanceAngry_Operation,ActionObject.ActionType_FoodAppeared);
        Robot.Global_GrievanceSquint2 = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_GrievanceSquint_Operation2,ActionObject.ActionType_FoodAppeared);
        Robot.Global_ShakeEar_Struggle3 = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_ShakeEar_Struggle_Operation3,ActionObject.ActionType_FoodAppeared);
        Robot.Global_SadEyesFollow = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_SadEyesFollow_Operation,ActionObject.ActionType_FoodAppeared);
        Robot.Global_SadEar2 = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_SadEar_Operation2,ActionObject.ActionType_FoodAppeared);
        Robot.Global_LoveEye = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_LoveEye_Operation,ActionObject.ActionType_Eaten);
        Robot.Global_ShakeEarHappy2 = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_ShakeEarHappy_Operation2,ActionObject.ActionType_Eaten);
        Robot.Global_EatenHappy = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_EatenHappy_Operation,ActionObject.ActionType_Eaten);
        Robot.Global_FoodFollow = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_FoodFollow_Operation,ActionObject.ActionType_Eaten);
        Robot.Global_TriggerHappy = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_TriggerHappy_Operation,ActionObject.ActionType_FaceEmoji);
        Robot.Global_TriggerSad = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_TriggerSad_Operation,ActionObject.ActionType_FaceEmoji);
        Robot.Global_TriggerAngry = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_TriggerAngry_Operation,ActionObject.ActionType_FaceEmoji);
        Robot.Global_TriggerXX = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_TriggerXX_Operation,ActionObject.ActionType_FaceEmoji);
        Robot.Global_TriggerDoubt = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_TriggerDoubt_Operation,ActionObject.ActionType_FaceEmoji);
        Robot.Global_TriggerSquintStruggle = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_TriggerSquintStruggle_Operation,ActionObject.ActionType_FaceEmoji);
        Robot.Global_TriggerGrievanceSquintStruggle = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_TriggerGrievanceSquintStruggle_Operation,ActionObject.ActionType_FaceEmoji);
        Robot.Global_TriggerGrievanceAngry = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_TriggerGrievanceAngry_Operation,ActionObject.ActionType_FaceEmoji);
        Robot.Global_TriggerGrievanceSad = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_TriggerGrievanceSad_Operation,ActionObject.ActionType_FaceEmoji);
        Robot.Global_BlinkSad = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_BlinkSad_Operation,ActionObject.ActionType_FaceEmoji);
        Robot.Global_BlinkGrievanceSad = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_BlinkGrievanceSad_Operation,ActionObject.ActionType_FaceEmoji);
        Robot.Global_BasicBodyMove = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_BasicBodyMove_Operation,ActionObject.ActionType_Move);
        Robot.Global_TriggerGrievanceSadEar = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_TriggerGrievanceSadEar_Operation,ActionObject.ActionType_EarFootsShake);
        Robot.Global_HappyShakeEar_Simple = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_HappyShakeEar_Simple_Operation,ActionObject.ActionType_EarFootsShake);
        Robot.Global_StruggleShakeEar_Simple = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_StruggleShakeEar_Simple_Operation,ActionObject.ActionType_EarFootsShake);
        Robot.Global_LookingForScreen = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_LookingForScreen_Operation,ActionObject.ActionType_FaceEmoji);
        Robot.Global_LookingForScreen_Sad = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_LookingForScreen_Sad_Operation,ActionObject.ActionType_FaceEmoji);
        Robot.Global_LookingForScreen_Angry = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_LookingForScreen_Angry_Operation,ActionObject.ActionType_FaceEmoji);
        Robot.Global_LookingForScreen_GrievanceSad = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_LookingForScreen_GrievanceSad_Operation,ActionObject.ActionType_FaceEmoji);
        Robot.Global_LookingForScreen_GrievanceAngry = new ActionObject(ActionObject.FrameRateTool.Type_SingleDataProgress
                , Robot.Global_LookingForScreen_GrievanceAngry_Operation,ActionObject.ActionType_FaceEmoji);
        //数据赋值


        Action_Tool.Add_RegisterAction(Robot.Global_Blink);
        Action_Tool.Add_RegisterAction(Robot.Global_Shake);
        Action_Tool.Add_RegisterAction(Robot.Global_Squint);
        Action_Tool.Add_RegisterAction(Robot.Global_Doubt);
        Action_Tool.Add_RegisterAction(Robot.Global_Dizzy);
        Action_Tool.Add_RegisterAction(Robot.Global_WonderEar);
        Action_Tool.Add_RegisterAction(Robot.Global_WonderEye);
        Action_Tool.Add_RegisterAction(Robot.Global_StarEyes);
        Action_Tool.Add_RegisterAction(Robot.Global_ShakeEarHappy);
        Action_Tool.Add_RegisterAction(Robot.Global_EyesFollow);
        Action_Tool.Add_RegisterAction(Robot.Global_DoubtBeforeFollow);
        Action_Tool.Add_RegisterAction(Robot.Global_SquintStruggle);
        Action_Tool.Add_RegisterAction(Robot.Global_ShakeEar_Struggle);
        Action_Tool.Add_RegisterAction(Robot.Global_Angry);
        Action_Tool.Add_RegisterAction(Robot.Global_SquintStruggle1);
        Action_Tool.Add_RegisterAction(Robot.Global_ShakeEar_Struggle1);
        Action_Tool.Add_RegisterAction(Robot.Global_Sad);
        Action_Tool.Add_RegisterAction(Robot.Global_SadEar);
        Action_Tool.Add_RegisterAction(Robot.Global_GrievanceSquint);
        Action_Tool.Add_RegisterAction(Robot.Global_ShakeEar_Struggle2);
        Action_Tool.Add_RegisterAction(Robot.Global_GrievanceAngry);
        Action_Tool.Add_RegisterAction(Robot.Global_GrievanceSquint2);
        Action_Tool.Add_RegisterAction(Robot.Global_ShakeEar_Struggle3);
        Action_Tool.Add_RegisterAction(Robot.Global_SadEyesFollow);
        Action_Tool.Add_RegisterAction(Robot.Global_SadEar2);
        Action_Tool.Add_RegisterAction(Robot.Global_LoveEye);
        Action_Tool.Add_RegisterAction(Robot.Global_ShakeEarHappy2);
        Action_Tool.Add_RegisterAction(Robot.Global_EatenHappy);
        Action_Tool.Add_RegisterAction(Robot.Global_FoodFollow);
        Action_Tool.Add_RegisterAction(Robot.Global_TriggerHappy);
        Action_Tool.Add_RegisterAction(Robot.Global_TriggerSad);
        Action_Tool.Add_RegisterAction(Robot.Global_TriggerAngry);
        Action_Tool.Add_RegisterAction(Robot.Global_TriggerXX);
        Action_Tool.Add_RegisterAction(Robot.Global_TriggerDoubt);
        Action_Tool.Add_RegisterAction(Robot.Global_TriggerSquintStruggle);
        Action_Tool.Add_RegisterAction(Robot.Global_TriggerGrievanceSquintStruggle);
        Action_Tool.Add_RegisterAction(Robot.Global_TriggerGrievanceAngry);
        Action_Tool.Add_RegisterAction(Robot.Global_TriggerGrievanceSad);
        Action_Tool.Add_RegisterAction(Robot.Global_BlinkSad);
        Action_Tool.Add_RegisterAction(Robot.Global_BlinkGrievanceSad);
        Action_Tool.Add_RegisterAction(Robot.Global_BasicBodyMove);
        Action_Tool.Add_RegisterAction(Robot.Global_TriggerGrievanceSadEar);
        Action_Tool.Add_RegisterAction(Robot.Global_HappyShakeEar_Simple);
        Action_Tool.Add_RegisterAction(Robot.Global_StruggleShakeEar_Simple);
        Action_Tool.Add_RegisterAction(Robot.Global_LookingForScreen);
        Action_Tool.Add_RegisterAction(Robot.Global_LookingForScreen_Sad);
        Action_Tool.Add_RegisterAction(Robot.Global_LookingForScreen_Angry);
        Action_Tool.Add_RegisterAction(Robot.Global_LookingForScreen_GrievanceSad);
        Action_Tool.Add_RegisterAction(Robot.Global_LookingForScreen_GrievanceAngry);
        //加入注册表
    }
    //添加所有Action对象：ActionObject的注册表
    ;
    //通用注册
    ;
    //--------------------------------注册列表+动作锁--------------------------------------
    public static long CountTimeGap_ClickEars = (long) (0.5F * 1000L);
    public static long BeginTimeGap_ClickEars = (long) (4.5F * 1000L);
    public static int TotalCountNumber_ClickEars = 3;
    public ActionObject_Operation.Timer_Counter TimeCounter_ClickEars;
    public static long CountTimeGap_ClickEyes = (long) (0.5F * 1000L);
    public static long BeginTimeGap_ClickEyes = (long) (4.5F * 1000L);
    public static int TotalCountNumber_ClickEyes = 2;
    public ActionObject_Operation.Timer_Counter TimeCounter_ClickEyes;
    public static long CountTimeGap_DraggedEars = (long) (0.5F * 1000L);
    public static long BeginTimeGap_DraggedEars = (long) (4.5F * 1000L);
    public static int TotalCountNumber_DraggedEars = 2;
    public ActionObject_Operation.Timer_Counter TimeCounter_DraggedEars;

    public ActionObject_Operation.Timer_Timing Timing_EyesBlink;
    public static long Timing_BodyMove_Lower = (long) (3.0F * 60.0F * 1000L);
    public static long Timing_BodyMove_Upper = (long) (6.5F * 60.0F * 1000L);
    public ActionObject_Operation.Timer_Timing Timing_BodyMove;
    public static long Timing_EarShake_Lower = (long) (1.5F * 60.0F * 1000L);
    public static long Timing_EarShake_Upper = (long) (3.5F * 60.0F * 1000L);
    public ActionObject_Operation.Timer_Timing Timing_EarShake;
    public static long Timing_EyesFollow_Lower = (long) (3.0F * 60.0F * 1000L);
    public static long Timing_EyesFollow_Upper = (long) (6.0F * 60.0F * 1000L);
    public ActionObject_Operation.Timer_Timing Timing_EyesFollow;

    public static void Global_ActionTool_Timer_Init(){
        Robot.Global_FishRoeAction_Tool.TimeCounter_ClickEars = new ActionObject_Operation.Timer_Counter(
                BeginTimeGap_ClickEars,TotalCountNumber_ClickEars,CountTimeGap_ClickEars,ActionObject_Operation.Timer_Counter.Type_TimeArrive_End,
                Robot.Global_FishRoeAction_Tool::TimeCounter_ClickEars_Progress);
        Robot.Global_FishRoeAction_Tool.TimeCounter_ClickEyes = new ActionObject_Operation.Timer_Counter(
                BeginTimeGap_ClickEyes,TotalCountNumber_ClickEyes,CountTimeGap_ClickEyes,ActionObject_Operation.Timer_Counter.Type_TimeArrive_End,
                Robot.Global_FishRoeAction_Tool::TimeCounter_ClickEyes_Progress);
        Robot.Global_FishRoeAction_Tool.TimeCounter_DraggedEars = new ActionObject_Operation.Timer_Counter(
                BeginTimeGap_DraggedEars,TotalCountNumber_DraggedEars,CountTimeGap_DraggedEars,ActionObject_Operation.Timer_Counter.Type_TimeArrive_End,
                Robot.Global_FishRoeAction_Tool::TimeCounter_DraggedEars_Progress);

        Robot.Global_FishRoeAction_Tool.Timing_EyesBlink = new ActionObject_Operation.Timer_Timing(Robot.Time_Blink_Lower, Robot.Time_Blink_Upper,
                Robot.Global_FishRoeAction_Tool::Timing_EyesBlink_Progress);
        Robot.Global_FishRoeAction_Tool.Timing_BodyMove = new ActionObject_Operation.Timer_Timing(Timing_BodyMove_Lower,Timing_BodyMove_Upper,
                Robot.Global_FishRoeAction_Tool::Timing_BodyMove_Progress);
        Robot.Global_FishRoeAction_Tool.Timing_EarShake = new ActionObject_Operation.Timer_Timing(Timing_EarShake_Lower,Timing_EarShake_Upper,
                Robot.Global_FishRoeAction_Tool::Timing_EarShake_Progress);
        Robot.Global_FishRoeAction_Tool.Timing_EyesFollow = new ActionObject_Operation.Timer_Timing(Timing_EyesFollow_Lower,Timing_EyesFollow_Upper,
                Robot.Global_FishRoeAction_Tool::Timing_EyesFollow_Progress);
    }

    public void TimeCounter_ClickEars_Progress(){
        if(Main.userData.moodSystem.CurrentMood == UserData.Mood.CurrentMood_Happy){
            Robot.Global_TriggerHappy_Operation[0].Begin();
        }
        else if(Main.userData.moodSystem.CurrentMood == UserData.Mood.CurrentMood_Simple){
            Robot.Global_TriggerSad_Operation[0].Begin();
        }
        else if(Main.userData.moodSystem.CurrentMood == UserData.Mood.CurrentMood_Sad){
            int order = Random.Get_Int_SetRange(0,3);
            if(order == 0){
                Robot.Global_TriggerAngry_Operation[0].Begin();
            }
            else {
                for(ActionObject_Operation o : Robot.Global_TriggerGrievanceSadEar_Operation){
                    o.Begin();
                }
            }
        }
        else if(Main.userData.moodSystem.CurrentMood == UserData.Mood.CurrentMood_Range){
            int order = Random.Get_Int_SetRange(0,3);
            if(order == 0){
                Robot.Global_TriggerXX_Operation[0].Begin();
            }
            else {
                for(ActionObject_Operation o : Robot.Global_TriggerGrievanceSadEar_Operation){
                    o.Begin();
                }
            }
        }
    }
    public void TimeCounter_ClickEyes_Progress(){
        if(Main.userData.moodSystem.CurrentMood == UserData.Mood.CurrentMood_Happy){
            int order = Random.Get_Int_SetRange(0,10);
            if(order <= 3){
                Robot.Global_TriggerHappy_Operation[0].Begin();
            }
            else if (order == 4) {
                Robot.Global_BlinkGrievanceSad_Operation[0].Begin();
            }
            else{
                Robot.Global_TriggerDoubt_Operation[0].Begin();
            }
        }
        else if(Main.userData.moodSystem.CurrentMood == UserData.Mood.CurrentMood_Simple){
            int order = Random.Get_Int_SetRange(0,4);
            if(order == 0){
                Robot.Global_TriggerDoubt_Operation[0].Begin();
            }
            else{
                Robot.Global_TriggerSquintStruggle_Operation[0].Begin();
            }
        }
        else if(Main.userData.moodSystem.CurrentMood == UserData.Mood.CurrentMood_Sad){
            int order = Random.Get_Int_SetRange(0,5);
            if(order == 0){
                Robot.Global_TriggerSad_Operation[0].Begin();
                for(ActionObject_Operation o : Robot.Global_TriggerGrievanceSadEar_Operation){
                    o.Begin();
                }
            }
            else if(order == 1){
                Robot.Global_TriggerAngry_Operation[0].Begin();
            }
            else{
                Robot.Global_TriggerSquintStruggle_Operation[0].Begin();
            }
        }
        else if(Main.userData.moodSystem.CurrentMood == UserData.Mood.CurrentMood_Range){
            int order = Random.Get_Int_SetRange(0,7);
            if(order == 0){
                Robot.Global_TriggerGrievanceSad_Operation[0].Begin();
                for(ActionObject_Operation o : Robot.Global_TriggerGrievanceSadEar_Operation){
                    o.Begin();
                }
            }
            else if(order == 1){
                Robot.Global_TriggerGrievanceAngry_Operation[0].Begin();
            }
            else if(order >= 2 && order <= 4){
                Robot.Global_TriggerXX_Operation[0].Begin();
            }
            else{
                Robot.Global_TriggerGrievanceSquintStruggle_Operation[0].Begin();
            }
        }
    }
    public void TimeCounter_DraggedEars_Progress(){
        if(Main.userData.moodSystem.CurrentMood == UserData.Mood.CurrentMood_Happy){
            int order = Random.Get_Int_SetRange(0,3);
            if(order == 0){
                Robot.Global_TriggerHappy_Operation[0].Begin();
            }
            else{
                Robot.Global_TriggerDoubt_Operation[0].Begin();
            }
        }
        else if(Main.userData.moodSystem.CurrentMood == UserData.Mood.CurrentMood_Simple){
            int order = Random.Get_Int_SetRange(0,4);
            if(order == 0){
                Robot.Global_TriggerDoubt_Operation[0].Begin();
            }
            else{
                Robot.Global_TriggerSquintStruggle_Operation[0].Begin();
            }
        }
        else if(Main.userData.moodSystem.CurrentMood == UserData.Mood.CurrentMood_Sad){
            int order = Random.Get_Int_SetRange(0,5);
            if(order == 0){
                Robot.Global_TriggerSad_Operation[0].Begin();
                for(ActionObject_Operation o : Robot.Global_TriggerGrievanceSadEar_Operation){
                    o.Begin();
                }
            }
            else if(order == 1){
                Robot.Global_TriggerAngry_Operation[0].Begin();
            }
            else{
                Robot.Global_TriggerSquintStruggle_Operation[0].Begin();
            }
        }
        else if(Main.userData.moodSystem.CurrentMood == UserData.Mood.CurrentMood_Range){
            int order = Random.Get_Int_SetRange(0,7);
            if(order == 0){
                Robot.Global_TriggerGrievanceSad_Operation[0].Begin();
                for(ActionObject_Operation o : Robot.Global_TriggerGrievanceSadEar_Operation){
                    o.Begin();
                }
            }
            else if(order == 1){
                Robot.Global_TriggerGrievanceAngry_Operation[0].Begin();
            }
            else if(order >= 2 && order <= 4){
                Robot.Global_TriggerXX_Operation[0].Begin();
            }
            else{
                Robot.Global_TriggerGrievanceSquintStruggle_Operation[0].Begin();
            }
        }
    }
    public void Timing_EyesBlink_Progress(){
        if(Main.userData.moodSystem.CurrentMood == UserData.Mood.CurrentMood_Sad){
            Robot.Global_BlinkSad_Operation[0].Begin();
        }
        else if(Main.userData.moodSystem.CurrentMood == UserData.Mood.CurrentMood_Range){
            Robot.Global_BlinkGrievanceSad_Operation[0].Begin();
        }
    }
    public void Timing_BodyMove_Progress(){
        if(Main.userData.moodSystem.CurrentMood == UserData.Mood.CurrentMood_Happy ||
                Main.userData.moodSystem.CurrentMood == UserData.Mood.CurrentMood_Simple){
            Robot.Global_BasicBodyMove_Operation[0].Begin();
        }
    }
    public void Timing_EarShake_Progress(){
        if(Main.userData.moodSystem.CurrentMood == UserData.Mood.CurrentMood_Happy){
            for(ActionObject_Operation a : Robot.Global_HappyShakeEar_Simple_Operation){
                a.Begin();
            }
        }
        else if(Main.userData.moodSystem.CurrentMood == UserData.Mood.CurrentMood_Simple){
            for(ActionObject_Operation a : Robot.Global_HappyShakeEar_Simple_Operation){
                a.Begin();
            }
        }
        else if(Main.userData.moodSystem.CurrentMood == UserData.Mood.CurrentMood_Sad){
            int order = Random.Get_Int_SetRange(0,3);
            if(order == 0){
                for(ActionObject_Operation a : Robot.Global_StruggleShakeEar_Simple_Operation){
                    a.Begin();
                }
            }
            else {
                for(ActionObject_Operation o : Robot.Global_TriggerGrievanceSadEar_Operation){
                    o.Begin();
                }
            }
        }
        else if(Main.userData.moodSystem.CurrentMood == UserData.Mood.CurrentMood_Range){
            int order = Random.Get_Int_SetRange(0,3);
            if(order == 0){
                for(ActionObject_Operation o : Robot.Global_TriggerGrievanceSadEar_Operation){
                    o.Begin();
                }
            }
            else {
                for(ActionObject_Operation a : Robot.Global_StruggleShakeEar_Simple_Operation){
                    a.Begin();
                }
            }
        }
    }
    public void Timing_EyesFollow_Progress(){
        if(Main.userData.moodSystem.CurrentMood == UserData.Mood.CurrentMood_Happy){
            Robot.Global_LookingForScreen_Operation[0].Begin();
        }
        else if(Main.userData.moodSystem.CurrentMood == UserData.Mood.CurrentMood_Simple){
            int order = Random.Get_Int_SetRange(0,3);
            if(order == 0){
                Robot.Global_LookingForScreen_Sad_Operation[0].Begin();
            }
            else {
                Robot.Global_LookingForScreen_Operation[0].Begin();
            }

        }
        else if(Main.userData.moodSystem.CurrentMood == UserData.Mood.CurrentMood_Sad){
            int order = Random.Get_Int_SetRange(0,5);
            if(order == 0){
                Robot.Global_LookingForScreen_Angry_Operation[0].Begin();
            }
            else if(order <= 3){
                Robot.Global_LookingForScreen_Sad_Operation[0].Begin();
            }
            else {
                Robot.Global_LookingForScreen_GrievanceAngry_Operation[0].Begin();
            }
        }
        else if(Main.userData.moodSystem.CurrentMood == UserData.Mood.CurrentMood_Range){
            int order = Random.Get_Int_SetRange(0,5);
            if(order == 0){
                Robot.Global_LookingForScreen_Sad_Operation[0].Begin();
            }
            else if(order <= 3){
                Robot.Global_LookingForScreen_GrievanceSad_Operation[0].Begin();
            }
            else {
                Robot.Global_LookingForScreen_Angry_Operation[0].Begin();
            }
        }
    }

    //--------------------------------计时器+计数器--------------------------------------
    public static void Check_FoodAppeared_Switch(ActionObject object){
        if(Global_Lock_FoodAppeared && !Global_Lock_FoodEaten){
            Check_FoodAppeared_SwitchCommon(Robot.Global_WonderEye, Robot.Global_StarEyes_Operation,object);
            Check_FoodAppeared_SwitchCommon(Robot.Global_WonderEar, Robot.Global_ShakeEarHappy_Operation,object);
            Check_FoodAppeared_SwitchCommon(Robot.Global_ShakeEarHappy, Robot.Global_EyesFollow_Operation,object);
            Check_FoodAppeared_SwitchCommon(Robot.Global_EyesFollow, Robot.Global_DoubtBeforeFollow_Operation,object);
            Check_FoodAppeared_SwitchCommon(Robot.Global_DoubtBeforeFollow, Robot.Global_SquintStruggle_Operation,object);
            Check_FoodAppeared_SwitchCommon(Robot.Global_DoubtBeforeFollow, Robot.Global_ShakeEar_Struggle_Operation,object);
            Check_FoodAppeared_SwitchCommon(Robot.Global_ShakeEar_Struggle, Robot.Global_Angry_Operation,object);
            Check_FoodAppeared_SwitchCommon(Robot.Global_Angry, Robot.Global_SquintStruggle_Operation1,object);
            Check_FoodAppeared_SwitchCommon(Robot.Global_Angry, Robot.Global_ShakeEar_Struggle_Operation1,object);
            Check_FoodAppeared_SwitchCommon(Robot.Global_SquintStruggle1, Robot.Global_Sad_Operation,object);
            Check_FoodAppeared_SwitchCommon(Robot.Global_ShakeEar_Struggle1, Robot.Global_SadEar_Operation,object);
            Check_FoodAppeared_SwitchCommon(Robot.Global_Sad, Robot.Global_GrievanceSquint_Operation,object);
            Check_FoodAppeared_SwitchCommon(Robot.Global_SadEar, Robot.Global_ShakeEar_Struggle_Operation2,object);
            Check_FoodAppeared_SwitchCommon(Robot.Global_ShakeEar_Struggle2, Robot.Global_GrievanceAngry_Operation,object);
            Check_FoodAppeared_SwitchCommon(Robot.Global_GrievanceAngry, Robot.Global_GrievanceSquint_Operation2,object);
            Check_FoodAppeared_SwitchCommon(Robot.Global_GrievanceAngry, Robot.Global_ShakeEar_Struggle_Operation3,object);
            Check_FoodAppeared_SwitchCommon(Robot.Global_GrievanceSquint2, Robot.Global_SadEyesFollow_Operation,object);
            Check_FoodAppeared_SwitchCommon(Robot.Global_ShakeEar_Struggle3, Robot.Global_SadEar_Operation2,object);
        }
        //食物出现

        else if (Global_Lock_FoodEaten) {
            Check_FoodAppeared_SwitchCommon(Robot.Global_LoveEye, Robot.Global_EatenHappy_Operation,object);
            Check_FoodAppeared_SwitchCommon(Robot.Global_LoveEye, Robot.Global_FoodFollow_Operation,object);
        }
        //食物被吃

        //测试
    }
    //查询出现食物系列的切换

    public static void Check_FoodAppeared_SwitchCommon(ActionObject a,ActionObject_Operation[] o,ActionObject object){
        if(a == object && o != null){
            for(ActionObject_Operation o1 : o){
                o1.Begin();
            }
        }
    }
    //查询出现食物系列的切换

    public void CheckFoodExist(){
        if(InterfaceButton.Global_Food != null){
            boolean judge = false;
            for(InterfaceButton.Food food : InterfaceButton.Global_Food){
                if(food.Judge_DisplayPicture){
                    judge = true;
                    break;
                }
            }
            Global_Lock_FoodAppeared = judge;
        }
        //上锁
    }
    //检擦点开食物

    public void CheckEatAction(){
        if(InterfaceButton.Global_Food != null){
            boolean judge = false;
            for(InterfaceButton.Food food : InterfaceButton.Global_Food){
                if(food.Eaten){
                    judge = true;
                    break;
                }
            }
            Global_Lock_FoodEaten = judge;
        }
        //上锁
    }
    //检查进食动作

    public void CheckDizzyBegin(){
        Robot.Global_Dizzy.dizzy.MainCheck();
    }//检查是否该开始眩晕了
    //--------------------------------特殊检查--------------------------------------

    public void CheckApplication(){
        CheckFoodExist();
        CheckEatAction();
        if(Global_Lock_FoodEaten){
            CheckApplication_Common(List_Special_FoodEaten);//ActionObject申请检查
            for(ActionObject current : List_Special_FoodEaten){
                if (current.CurrentState == ActionObject.State_Applying) {
                    current.CurrentState = ActionObject.State_Running;
                    //赋予许可，自己去运行
                }
            }
            return;
        }
        else if(Global_Lock_FoodAppeared){
            CheckApplication_Common(List_Special_FoodAppeared);//ActionObject申请检查
            for(ActionObject current : List_Special_FoodAppeared){
                if (current.CurrentState == ActionObject.State_Applying) {
                    current.CurrentState = ActionObject.State_Running;
                    //赋予许可，自己去运行
                }
            }
            return;
            //不处理申请了
        }
        //检查食物：高优先级
        CheckDizzyBegin();
        //特殊动作检查
        if(!Main.robot.BasicEyes.eventLock.isOnce_Display()){
            CheckApplication_Common(List_Action_FaceEmoji);
        }
        CheckApplication_Common(List_Action_EarFootsShake);
        CheckApplication_Common(List_Action_Move);
        //ActionObject的申请检查
        if(!Global_Lock_FaceEmoji && !Main.robot.BasicEyes.eventLock.isOnce_Display()){//没上锁
            for (ActionObject current : List_Action_FaceEmoji) {
                if (current.CurrentState == ActionObject.State_Applying) {
                    current.CurrentState = ActionObject.State_Running;
                    //赋予许可，自己去运行
                    Global_Lock_FaceEmoji = true;//有一个表情之后就不允许有第二个表情了
                    break;
                }
            }
        }
        if(!Global_Lock_EarFootsShake){//没上锁
            for (ActionObject current : List_Action_EarFootsShake) {
                if (current.CurrentState == ActionObject.State_Applying) {
                    current.CurrentState = ActionObject.State_Running;
                    Global_Lock_EarFootsShake = true;//移动之后不允许抖动
                    //赋予许可，自己去运行
                    break;
                }
            }
        }
        if(!Global_Lock_Move){//没上锁
            for (ActionObject current : List_Action_Move) {
                if (current.CurrentState == ActionObject.State_Applying) {
                    current.CurrentState = ActionObject.State_Running;
                    //赋予许可，自己去运行
                    Global_Lock_Move = true;//移动之后不允许其他移动行为
                    break;
                }
            }
        }
        //申请处理
    }
    //检查申请(放在最初)

    public void CheckApplication_Common(ArrayList<ActionObject> list){
        if(list != null && !list.isEmpty()){
            for(ActionObject o : list){
                o.CheckApplication();
            }
        }
    }
    //通用ActionObject申请检查

    public void Action_ObjectRunningCheck(){

        if(Global_Lock_FoodEaten){
            for (ActionObject o : List_Special_FoodEaten){
                o.Running();
                if (o.CurrentState == ActionObject.State_Running) {
                    Action_ActionObjectDataProgress(o);
                    //Running则进行数据处理
                }
            }
        }
        //第一优先级
        else if(Global_Lock_FoodAppeared){
            for (ActionObject o : List_Special_FoodAppeared){
                o.Running();
                if (o.CurrentState == ActionObject.State_Running) {
                    Action_ActionObjectDataProgress(o);
                    //Running则进行数据处理
                }
            }
        }
        //第二优先级
        else{
            for (ActionObject o : List_Action_FaceEmoji){
                o.Running();
            }
            for (ActionObject o : List_Action_EarFootsShake){
                o.Running();
            }
            for (ActionObject o : List_Action_Move){
                o.Running();
            }
            //获得申请的-》运行

            if(Global_Lock_FaceEmoji){
                for (ActionObject o : List_Action_FaceEmoji) {
                    if (o.CurrentState == ActionObject.State_Running) {
                        Action_ActionObjectDataProgress(o);
                        //Running则进行数据处理
                    }
                }
            }
            //表情数据处理

            for (ActionObject o : List_Action_EarFootsShake) {
                if (o.CurrentState == ActionObject.State_Running) {
                    Action_ActionObjectDataProgress(o);
                    //Running则进行数据处理
                }
            }
            //抖动数据处理

            if(Global_Lock_Move){//移动之后不允许其他移动行为，不允许抖动
                for (ActionObject o : List_Action_Move) {
                    if (o.CurrentState == ActionObject.State_Running) {
                        Action_ActionObjectDataProgress(o);
                        //Running则进行数据处理
                    }
                }
            }
            //移动数据处理
        }
    }
    //运行：查询

    public void Action_ActionObjectDataProgress(ActionObject o){
        if(o.CurrentState == ActionObject.State_Running){//正在运行
            if(o.BeforeState != o.CurrentState){
                o.FPS_Tool.CurrentState = ActionObject.FrameRateTool.State_Running;
            }
            //首次加载，将Tool的CurrentState改为Run
            if(o.FPS_Tool.Tool_Type == ActionObject.FrameRateTool.Type_MultipleSwitch){
                o.FPS_Tool.DataProgress_PictureSwitch();
                //数据处理
            }
            else if (o.FPS_Tool.Tool_Type == ActionObject.FrameRateTool.Type_SingleDataProgress) {
                o.FPS_Tool.DataProgress_SinglePicture();
                //动画处理
            }
            //数据处理
        }
    }
    //运行：数据处理：启动FPS_Tool

    public void Display_ObjectRunning(Graphics2D g, MyFrame frame){
        if(!Global_Lock_FoodAppeared && !Global_Lock_FoodEaten){
            Display_Common(g,frame,List_Action_FaceEmoji);
            Display_Common(g,frame,List_Action_EarFootsShake);
            Display_Common(g,frame,List_Action_Move);
        }
        //为进食情况下绘制
        else if(Global_Lock_FoodEaten){//吃了
            Display_Common(g,frame,List_Special_FoodEaten);
        }
        else {//出现没吃
            Display_Common(g,frame,List_Special_FoodAppeared);
        }
        //进食绘制

        if(Global_Lock_FoodAppeared){
            Check_MakeFpsToolSleep(List_Action_FaceEmoji);
            Check_MakeFpsToolSleep(List_Action_EarFootsShake);
            Check_MakeFpsToolSleep(List_Action_Move);
        }
        if(Global_Lock_FoodEaten){
            Check_MakeFpsToolSleep(List_Action_FaceEmoji);
            Check_MakeFpsToolSleep(List_Action_EarFootsShake);
            Check_MakeFpsToolSleep(List_Action_Move);
            Check_MakeFpsToolSleep(List_Special_FoodAppeared);
        }

        //立刻退出
    }
    //运行：绘制

    public void Check_MakeFpsToolSleep(ArrayList<ActionObject> list){
        if(list != null && !list.isEmpty()){
            for (ActionObject o : list) {
                for (ActionObject_Operation a : o.Array_SinglePictureOperation) {//处理Exit
                    a.Reset();
                    //重置
                    a.Unlock();
                    //单个解锁
                }
                if(o.FPS_Tool.Judge_OperationExit_Reset()){
                    o.FPS_Tool.CurrentState = ActionObject.FrameRateTool.State_Exiting;//FPS_Tool退出
                }
                //操作结束，退出绘制
                if(o.FPS_Tool.CurrentState == ActionObject.FrameRateTool.State_Exiting){
                    ActionTool.Check_FoodAppeared_Switch(o);//食物特殊检查
                    o.FPS_Tool.CurrentState = ActionObject.FrameRateTool.State_Sleep;//工具沉睡
                    o.Reset();//重置Object的数据
                    o.FPS_Tool.Unlock_ByType();//给行为管理对象解锁
                }
            }
        }
    }
    //查询FPS状况：立刻退出操作

    public void Display_Common(Graphics2D g, MyFrame frame, ArrayList<ActionObject> list){
        if(list != null && !list.isEmpty()){
            if(list != List_Action_Move){
                for (ActionObject o : list) {
                    o.FPS_Tool.Display(g, frame);
                }
            }
            else{
                for (ActionObject o : list) {
                    if(o != Robot.Global_BasicBodyMove){//限制
                        o.FPS_Tool.Display(g, frame);
                    }
                    else{
                        for (ActionObject_Operation o1 : o.Array_SinglePictureOperation) {
                            if(o1.CurrentState == ActionObject_Operation.State_Exit){
                                o1.Reset();
                                //重置
                                o1.Unlock();
                                //单个解锁
                                o.FPS_Tool.CurrentState = ActionObject.FrameRateTool.State_Sleep;
                                o.CurrentState = ActionObject.State_Sleep;
                                o.Reset();//重置Object的数据
                                o.FPS_Tool.Unlock_ByType();//给行为管理对象解锁
                            }
                            //单个检查就重置了
                        }
                    }
                }
            }
        }
    }
    //通用绘制

    public void Refresh_AllActionObject(){
        Refresh_Common(List_Action_FaceEmoji);
        Refresh_Common(List_Action_EarFootsShake);
        Refresh_Common(List_Action_Move);
    }
    //刷新：刷新每一个ActionObject(放在末尾)
    public void Refresh_Common(ArrayList<ActionObject> list){
        if(list != null && !list.isEmpty()){
            for (ActionObject a : list) {
                a.Refresh();
            }
        }
    }
    //通用刷新
    ;
    //--------------------------------检查队列，运行维护，刷新--------------------------------------

    public static class Random{
        public static int Get_Int_SetRange(int LowerBound,int UpperBound){
            if(LowerBound != UpperBound){
                final int enlarge = 100000000;
                int random = (int) (Math.random() * enlarge);
                int Gap = UpperBound - LowerBound;
                return (random % Gap) + LowerBound;
            }
            else{
                return LowerBound;
            }
        }

        public static long Get_Long_SetRange(long LowerBound,long UpperBound){
            if(LowerBound != UpperBound){
                final long enlarge = 100000000000000000L;
                long random = (long) (Math.random() * enlarge);
                long Gap = UpperBound - LowerBound;
                return (random % Gap) + LowerBound;
            }
            else{
                return LowerBound;
            }
        }
    }

    //--------------------------------随机事件管理系统--------------------------------------

    public void timerMain(){
        Timer_Common(List_Action_FaceEmoji);
        Timer_Common(List_Action_EarFootsShake);
        Timer_Common(List_Action_Move);
        if(this.TimeCounter_ClickEars != null){
            this.TimeCounter_ClickEars.Main();
        }
        if(this.TimeCounter_ClickEyes != null){
            this.TimeCounter_ClickEyes.Main();
        }
        if(this.TimeCounter_DraggedEars != null){
            this.TimeCounter_DraggedEars.Main();
        }
        if(this.Timing_EyesBlink != null){
            this.Timing_EyesBlink.Main();
        }
        if(this.Timing_BodyMove != null){
            this.Timing_BodyMove.Main();
        }
        if(this.Timing_EarShake != null){
            this.Timing_EarShake.Main();
        }
        if(this.Timing_EyesFollow != null){
            this.Timing_EyesFollow.Main();
        }
    }

    public void Timer_Common(ArrayList<ActionObject> list){
        if(list != null && !list.isEmpty()) {
            for (ActionObject o : list) {
                o.Timer_Main();
            }
        }
    }
    //通用时间管理
    ;

    //--------------------------------时间管理系统--------------------------------------
}
