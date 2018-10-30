package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Hanging {
    public DcMotor hanging;

    public static final double ENCODER_TOGO_DOWN = 1;

    public Hanging(HardwareMap hardwareMap, Telemetry telemetry) {
        hanging = hardwareMap.get(DcMotor.class,"hanging");
    }
    public void setHangingPower(double power){
        hanging.setPower(power);
    }

}
