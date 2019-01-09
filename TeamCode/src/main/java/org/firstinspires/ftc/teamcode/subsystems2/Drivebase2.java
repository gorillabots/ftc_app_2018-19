package org.firstinspires.ftc.teamcode.subsystems2;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drivebase2
{
    private DcMotor mtrFL;
    private DcMotor mtrFR;
    private DcMotor mtrBL;
    private DcMotor mtrBR;

    public Drivebase2(HardwareMap hm)
    {
        mtrFL = hm.dcMotor.get("mfl");
        mtrFR = hm.dcMotor.get("mfr");
        mtrBL = hm.dcMotor.get("mbl");
        mtrBR = hm.dcMotor.get("mbr");

        mtrFL.setDirection(DcMotor.Direction.FORWARD);
        mtrFR.setDirection(DcMotor.Direction.REVERSE);
        mtrBL.setDirection(DcMotor.Direction.FORWARD);
        mtrBR.setDirection(DcMotor.Direction.REVERSE);
    }

    public void drive(double fb, double lr, double turn)
    {
        double fl = fb + lr + turn;
        double fr = fb - lr - turn;
        double bl = fb - lr + turn;
        double br = fb + lr - turn;

        mtrFL.setPower(fl);
        mtrFR.setPower(fr);
        mtrBL.setPower(bl);
        mtrBR.setPower(br);
    }
}
