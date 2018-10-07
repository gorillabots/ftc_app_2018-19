package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.teamcode.old.OldAutonomousOpMode;
import org.firstinspires.ftc.teamcode.subsystems.Sensors;

public abstract class AutonomousOpMode extends OldAutonomousOpMode {
    public Sensors sensor;

    public void initializeAutonomous() {
        sensor = new Sensors(hardwareMap, telemetry);
    }

    public void unhang() {
        //THIRTY POINTS!!!!!!!!!!!!!
    }

    public int getYellowPosition() {
        return 1;
    }

    public void completeSampling(int sample) {
        if (sample == 1) {

        } else if (sample == 3) {

        } else {

        }
    }

    public void MoveUntilRed(double direction, double power) {
        while (!sensor.isRedGround() && opModeIsActive()) {
            motors.MoveTo(direction, power);
        }
        motors.stopMotors();
    }

    public void MoveUntilBlue(double direction, double power) {
        while (!sensor.isBlueGround() && opModeIsActive()) {
            motors.MoveTo(direction, power);
        }
        motors.stopMotors();
    }
}
