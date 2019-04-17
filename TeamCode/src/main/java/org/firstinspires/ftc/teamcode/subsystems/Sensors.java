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

    public DigitalChannel hangTouch;

    public DigitalChannel horizSlowTouch;
    public DigitalChannel vertSlowTouch;

    public Sensors(HardwareMap hardwareMap, Telemetry telemetry) {
        horizTouch = hardwareMap.get(DigitalChannel.class, "horizTouch");
        vertTouch = hardwareMap.get(DigitalChannel.class , "vertTouch");

        hangTouch = hardwareMap.get(DigitalChannel.class, "hangTouch");

        horizSlowTouch = hardwareMap.get(DigitalChannel.class, "horizSlowTouch");
        vertSlowTouch = hardwareMap.get(DigitalChannel.class, "vertSlowTouch");

        horizTouch.setMode(DigitalChannel.Mode.INPUT);
        vertTouch.setMode(DigitalChannel.Mode.INPUT);

        hangTouch.setMode(DigitalChannel.Mode.INPUT);

        horizSlowTouch.setMode(DigitalChannel.Mode.INPUT);
        vertSlowTouch.setMode(DigitalChannel.Mode.INPUT);
    }


}
