package org.firstinspires.ftc.teamcode.subsystems2;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Depositor2
{
    private DcMotor extend;

    private Servo angle;
    private Servo backstop;

    public Depositor2(HardwareMap hm)
    {
        extend = hm.get(DcMotor.class, "mExtendVert");

        angle = hm.get(Servo.class, "sDepositRot");
    }
}
