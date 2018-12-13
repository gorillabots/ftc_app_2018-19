package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Servos {
    public Servo sCan;
    public Servo sCollect;

    public static final double CAN_DUMP_POSITION = .04;
    public static final double CAN_COLLECT_POSITION = 0.04;
    public static final double CAN_INIT_POSITION = 0;

    public double pos = .5;

    public Servos(HardwareMap hardwareMap, Telemetry telemetry) {
        sCan = hardwareMap.get(Servo.class, "sCan");
        sCollect = hardwareMap.get(Servo.class, "sCollect");
    }

    public void initializeServos() {
        sCan.setPosition(CAN_INIT_POSITION);
        moveCollector(false,false );
    }

    public void setCanPosition(boolean collect) {
        if (collect) {
            sCan.setPosition(CAN_COLLECT_POSITION);
        } else {
            sCan.setPosition(CAN_DUMP_POSITION);
        }
    }

    public void moveCollector(boolean isMove, boolean collect) {
        if (!isMove) {
            sCollect.setPosition(.5);
        } else {
            if (collect) {
                sCollect.setPosition(1);
            } else {
                sCollect.setPosition(0);
            }
        }
    }

    public void setPositionDelta(double value) {
        //pos += value;

        /*if(pos > 1)
        {
            pos = 1;
        }
        else if(pos < 0)
        {
            pos = 0;
        }*/

        sCan.setPosition(value);
    }

}
