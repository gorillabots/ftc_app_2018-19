package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.AutonomousOpMode;

public class MecanumDrive implements Abstractions.DriveInterface
{
    DcMotor mFR;
    DcMotor mFL;
    DcMotor mBR;
    DcMotor mBL;

    public MecanumDrive(HardwareMap hardwareMap, Telemetry telemetry)
    {
        mFR = hardwareMap.dcMotor.get("mfr");
        mFL = hardwareMap.dcMotor.get("mfl");
        mBR = hardwareMap.dcMotor.get("mbr");
        mBL = hardwareMap.dcMotor.get("mbl");
    }

    @Override
    public void move(double fb, double rl, double rot)
    {
        mFR.setPower(fb - rl - rot); //Assuming + -> Clockwise looking into motor from axle
        mFL.setPower(-fb - rl - rot);
        mBR.setPower(fb + rl - rot);
        mBL.setPower(-fb + rl - rot);
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
