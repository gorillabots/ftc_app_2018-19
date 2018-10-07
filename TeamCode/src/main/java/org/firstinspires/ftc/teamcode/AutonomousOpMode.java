package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.teamcode.old.OldAutonomousOpMode;

public abstract class AutonomousOpMode extends OldAutonomousOpMode {
    protected ColorSensor color;

    public void initializeAutonomous() {
        color = hardwareMap.get(ColorSensor.class, "color");
    }

    public void unhang() {
        //THIRTY POINTS!!!!!!!!!!!!!
    }

    public boolean isRedGround() {
        return color.red() >= 1000;
    }

    public boolean isBlueGround() {
        return color.blue() >= 1000;
    }

    public void MoveToUntil(boolean isTrue, double direction, double power) {
        while (!isTrue && opModeIsActive()) {
            motors.MoveTo(direction, power);
            
        }
        motors.stopMotors();
    }

}
