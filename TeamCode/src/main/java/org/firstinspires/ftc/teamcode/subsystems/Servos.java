package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Servos {

    public Servo sBackstopCol;
    public Servo sCollectionRot;
    public Servo sDepositRot;
    public Servo sTeamMarkerRot;
    public Servo sBackstopDep;

    public static final double BACKSTOPCOL_INIT = .72;
    public static final double BACKSTOPCOL_CLOSED = 0.72;
    public static final double BACKSTOPCOL_OPEN = 0.3;

    public static final double BACKSTOPDEP_INIT = 0;
    public static final double BACKSTOPDEP_CLOSED = 0.31;
    public static final double BACKSTOPDEP_OPEN = 0;

    public static final double COLLECTION_INIT = .06;
    public static final double COLLECTION_COLLECT = .89; //.85
    public static final double COLLECTION_ALMOST_COLLECT = .78;
    public static final double COLLECTION_DUMP = .46;//.48
    public static final double COLLECTION_LONGRANGE = .80;

    public static final double DEPOSIT_INIT = .28; //goooood  //.4
    public static final double DEPOSIT_COLLECT = .08; //.56
    public static final double DEPOSIT_SCORE = .76; //0
    public static final double DEPOSIT_COMING_DOWN = .20; //.44
    public static final double DEPOSIT_HALFWAY = .45;

    public static final double TEAMMARKER_INIT = 0; //
    public static final double TEAMMARKER_TELEOP = 0;
    public static final double TEAMMARKER_DEPOSIT = .75;




    public Servos(HardwareMap hardwareMap, Telemetry telemetry) {
        sBackstopCol = hardwareMap.get(Servo.class, "sBackstopCol");
        sCollectionRot = hardwareMap.get(Servo.class, "sCollectionRot");
        sDepositRot = hardwareMap.get(Servo.class, "sDepositRot");
        sTeamMarkerRot = hardwareMap.get(Servo.class, "sTeamMarkerRot");
        sBackstopDep = hardwareMap.get(Servo.class , "sBackstopDep");
    }

    public void initializeServos() {
        sBackstopCol.setPosition(BACKSTOPCOL_INIT);
        sCollectionRot.setPosition(COLLECTION_INIT);
        sDepositRot.setPosition(DEPOSIT_INIT);
        sTeamMarkerRot.setPosition(TEAMMARKER_INIT);
        sBackstopDep.setPosition(BACKSTOPDEP_INIT);
    }

    public void setBackstopColOpen(boolean open) {
        if (open) {
            sBackstopCol.setPosition(BACKSTOPCOL_OPEN);
        } else {
            sBackstopCol.setPosition(BACKSTOPCOL_CLOSED);
        }
    }

    public void setBackstopDepOpen(boolean open) {
        if (open) {
            sBackstopDep.setPosition(BACKSTOPDEP_OPEN);
        } else {
            sBackstopDep.setPosition(BACKSTOPDEP_CLOSED);
        }
    }

    public void setCollectionCollect(boolean collect) {
        if (collect) {
            sCollectionRot.setPosition(COLLECTION_COLLECT);
        } else {
            sCollectionRot.setPosition(COLLECTION_DUMP);
        }
    }
    public void setCollectionLongrange(){
        sCollectionRot.setPosition(COLLECTION_LONGRANGE);
    }

    public void setCollectionAlmostCollect(){
        sCollectionRot.setPosition(COLLECTION_ALMOST_COLLECT);
    }
    public void setDepositDump(boolean dump) {
        if (dump) {
            sDepositRot.setPosition(DEPOSIT_SCORE);
        } else {
            sDepositRot.setPosition(DEPOSIT_COLLECT);
        }
    }

    public void setDepositComingDown(){
        sDepositRot.setPosition(DEPOSIT_COMING_DOWN);
    }

    public void setDepositHalfway(){sDepositRot.setPosition(DEPOSIT_HALFWAY);}

    public void setTeamMarkerFree(boolean dump){
        if (dump){
            sTeamMarkerRot.setPosition(TEAMMARKER_DEPOSIT);
        }
        else{
            sTeamMarkerRot.setPosition(TEAMMARKER_INIT);
        }
    }

    public void setTeammarkerTeleop(){
        sTeamMarkerRot.setPosition(TEAMMARKER_TELEOP);
    }

    public void servoReadyToRetract(){
        sDepositRot.setPosition(DEPOSIT_INIT);
        sCollectionRot.setPosition(COLLECTION_INIT);
        setBackstopColOpen(false);
    }
}
