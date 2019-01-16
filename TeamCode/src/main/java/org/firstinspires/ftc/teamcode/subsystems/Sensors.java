package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Sensors {
    public DigitalChannel horizTouch;
    public DigitalChannel vertTouch;

    public Sensors(HardwareMap hardwareMap, Telemetry telemetry) {
        horizTouch = hardwareMap.get(DigitalChannel.class, "horizTouch");
        vertTouch = hardwareMap.get(DigitalChannel.class , "vertTouch");

        horizTouch.setMode(DigitalChannel.Mode.INPUT);
        vertTouch.setMode(DigitalChannel.Mode.INPUT);
    }


}
