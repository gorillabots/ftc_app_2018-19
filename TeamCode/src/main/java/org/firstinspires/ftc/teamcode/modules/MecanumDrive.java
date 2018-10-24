package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MecanumDrive implements Abstractions.DriveInterface
{
    DcMotor mFR;
    DcMotor mFL;
    DcMotor mBR;
    DcMotor mBL;

    public MecanumDrive(HardwareMap hardwareMap, Telemetry telemetry)
    {
        mFR = hardwareMap.dcMotor.get("mFR");
        mFL = hardwareMap.dcMotor.get("mFL");
        mBR = hardwareMap.dcMotor.get("mBR");
        mBL = hardwareMap.dcMotor.get("mBL");
    }

    @Override
    public void move(double fb, double rl, double rot)
    {
        mFR.setPower(-fb - rl - rot); //Assuming + -> Clockwise looking into motor from axle
        mFL.setPower(fb - rl - rot);  //^^^ FAKE NEWS ^^^
        mBR.setPower(-fb + rl - rot);
        mBL.setPower(fb + rl - rot);
    }

    @Override
    public void stop()
    {
        mFR.setPower(0);
        mFL.setPower(0);
        mBR.setPower(0);
        mBL.setPower(0);
    }
}
