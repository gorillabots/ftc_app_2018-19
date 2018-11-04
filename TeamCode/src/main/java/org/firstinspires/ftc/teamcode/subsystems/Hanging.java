package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Hanging{
    public DcMotor hanging;

    public Hanging(HardwareMap hardwareMap, Telemetry telemetry) {
        hanging = hardwareMap.get(DcMotor.class,"hanging");
}
    public void setHangingPower(double power){
        hanging.setPower(power);
    }

}
