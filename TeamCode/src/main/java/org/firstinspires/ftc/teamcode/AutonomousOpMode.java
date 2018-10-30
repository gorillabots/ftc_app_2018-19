package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.old.OldAutonomousOpMode;
import org.firstinspires.ftc.teamcode.old.OldDriveTrain;
import org.firstinspires.ftc.teamcode.old.OldGyro;
import org.firstinspires.ftc.teamcode.subsystems.Sensors;

public abstract class AutonomousOpMode extends OldAutonomousOpMode {
    public Sensors sensor;
    public OldDriveTrain motors;
    public OldGyro gyro;

    public void initializeAutonomous() {
        mtrFR = hardwareMap.get(DcMotor.class, "mfr");
        mtrFL = hardwareMap.get(DcMotor.class, "mfl");
        mtrBR = hardwareMap.get(DcMotor.class, "mbr");
        mtrBL = hardwareMap.get(DcMotor.class, "mbl");

        mtrFR.setDirection(DcMotor.Direction.REVERSE);
        mtrFL.setDirection(DcMotor.Direction.FORWARD);
        mtrBR.setDirection(DcMotor.Direction.REVERSE);
        mtrBL.setDirection(DcMotor.Direction.FORWARD);

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
