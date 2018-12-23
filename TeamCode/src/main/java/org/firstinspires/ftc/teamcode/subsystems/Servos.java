package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Servos {

    public Servo sBackstop;
    public Servo sCollectionRot;
    public Servo sDepositRot;
    public Servo sTeamMarkerRot;

    public static final double BACKSTOP_INIT = 0;
    public static final double BACKSTOP_CLOSED = 0;
    public static final double BACKSTOP_OPEN = 0;

    public static final double COLLECTION_INIT = 0;
    public static final double COLLECTION_COLLECT = 0;
    public static final double COLLECTION_DUMP = 0;

    public static final double DEPOSIT_INIT = 0;
    public static final double DEPOSIT_COLLECT = 0;
    public static final double DEPOSIT_SCORE = 0;

    public static final double TEAMMARKER_INIT = 0;
    public static final double TEAMMARKER_DEPOSIT = 0;


    public Servos(HardwareMap hardwareMap, Telemetry telemetry) {
        sBackstop = hardwareMap.get(Servo.class, "sBackStop");
        sCollectionRot = hardwareMap.get(Servo.class, "sCollectionRot");
        sDepositRot = hardwareMap.get(Servo.class, "sDepositRot");
        sTeamMarkerRot = hardwareMap.get(Servo.class, "sTeamMarkerRot");
    }

    public void initializeServos() {
        sBackstop.setPosition(BACKSTOP_INIT);
        sCollectionRot.setPosition(COLLECTION_INIT);
        sDepositRot.setPosition(DEPOSIT_INIT);
        sTeamMarkerRot.setPosition(TEAMMARKER_INIT);
    }

    public void setBackstopOpen(boolean open) {
        if (open) {
            sBackstop.setPosition(BACKSTOP_OPEN);
        } else {
            sBackstop.setPosition(BACKSTOP_CLOSED);
        }
    }

    public void setCollectionCollect(boolean collect) {
        if (collect) {
            sCollectionRot.setPosition(COLLECTION_COLLECT);
        } else {
            sCollectionRot.setPosition(COLLECTION_DUMP);
        }
    }

    public void setDepositDump(boolean dump) {
        if (dump) {
            sDepositRot.setPosition(DEPOSIT_SCORE);
        } else {
            sDepositRot.setPosition(DEPOSIT_COLLECT);
        }
    }

    public void setTeamMarkerFree(boolean dump){
        if (dump){
            sTeamMarkerRot.setPosition(TEAMMARKER_DEPOSIT);
        }
        else{
            sTeamMarkerRot.setPosition(TEAMMARKER_INIT);
        }
    }
}
