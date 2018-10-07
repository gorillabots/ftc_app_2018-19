package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Sensors {
    public ColorSensor color;
    public Sensors(HardwareMap hardwareMap, Telemetry telemetry) {
        color = hardwareMap.get(ColorSensor.class, "color");
    }

    public boolean isRedGround() {
        return color.red() >= 1000;
    }

    public boolean isBlueGround() {
        return color.blue() >= 1000;
    }

}
