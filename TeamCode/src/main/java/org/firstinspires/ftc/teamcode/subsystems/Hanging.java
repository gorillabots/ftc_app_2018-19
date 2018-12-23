package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Hanging {
    public DcMotor mHang;

    public Hanging(HardwareMap hardwareMap, Telemetry telemetry) {
        mHang = hardwareMap.get(DcMotor.class, "mHang");
    }

    public void isEncoderMode(boolean encoder){
        if (encoder){
            mHang.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            mHang.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            mHang.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else{
            mHang.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }


    public void setHangingPower(double power) {
        mHang.setPower(power);
    }


}
