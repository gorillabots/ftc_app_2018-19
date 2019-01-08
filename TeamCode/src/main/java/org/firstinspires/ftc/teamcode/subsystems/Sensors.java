package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Sensors {
    public TouchSensor horizTouch;
    public TouchSensor vertTouch;

    public Sensors(HardwareMap hardwareMap, Telemetry telemetry) {
        horizTouch = hardwareMap.get(TouchSensor.class , "horizTouch");
        vertTouch = hardwareMap.get(TouchSensor.class , "horizTouch");
    }


}
