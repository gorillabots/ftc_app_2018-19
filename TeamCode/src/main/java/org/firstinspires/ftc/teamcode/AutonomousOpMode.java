package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Vision.Detector;
import org.firstinspires.ftc.teamcode.old.OldAutonomousOpMode;
import org.firstinspires.ftc.teamcode.old.OldDriveTrain;
import org.firstinspires.ftc.teamcode.old.OldGyro;
import org.firstinspires.ftc.teamcode.subsystems.Hanging;
import org.firstinspires.ftc.teamcode.subsystems.Sensors;

public abstract class AutonomousOpMode extends OldAutonomousOpMode {
    public Sensors sensor;
    public OldGyro gyro;
    public Detector detector;
    public Hanging hanging;

    public static final int ENCODER_TO_EXTEND_UP = 1;

    public void initializeAutonomous() {
      /*  mtrFR = hardwareMap.get(DcMotor.class, "mfr");
        mtrFL = hardwareMap.get(DcMotor.class, "mfl");
        mtrBR = hardwareMap.get(DcMotor.class, "mbr");
        mtrBL = hardwareMap.get(DcMotor.class, "mbl");

        mtrFR.setDirection(DcMotor.Direction.REVERSE);
        mtrFL.setDirection(DcMotor.Direction.FORWARD);
        mtrBR.setDirection(DcMotor.Direction.REVERSE);
        mtrBL.setDirection(DcMotor.Direction.FORWARD);*/

    }

    public int detectYellow(boolean isCrater) {

        detector = new Detector(this);
        detector.startCamera();
        Detector.YellowCubePosition tempPosition = Detector.YellowCubePosition.UNKNOWN;

        int yellowPosition = 0; //unknown

        boolean notSeen = true;
        int cycles = 0;

        while (/*opModeIsActive() && notSeen && */!isStarted()) {

            tempPosition = detector.computeYellowCubePosition(isCrater);

            if (tempPosition != Detector.YellowCubePosition.UNKNOWN) {

                if (tempPosition == Detector.YellowCubePosition.LEFT) {
                    yellowPosition = 1;
                    telemetry.addData("Camera", "%s visible", "left");
                }

                if (tempPosition == Detector.YellowCubePosition.CENTER) {
                    yellowPosition = 2;
                    telemetry.addData("Camera", "%s visible", "center");
                }

                if (tempPosition == Detector.YellowCubePosition.RIGHT) {
                    yellowPosition = 3;
                    telemetry.addData("Camera", "%s visible", "right");
                }
                telemetry.update();
                notSeen = false;
            } else {
                telemetry.addData("Camera", "not visible");
                telemetry.update();
                sleep(200);
            }
        }
        if (tempPosition == Detector.YellowCubePosition.UNKNOWN) {
            yellowPosition = 3; //right
            telemetry.addData("Camera", "%s visible", "right");
        }

        telemetry.addData("Result>", yellowPosition);
        telemetry.update();
        sleep(5000);
        detector.stopCamera();

        return yellowPosition;
    }

    public void unHangWithEncoder() {
        hanging.hanging.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hanging.hanging.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hanging.hanging.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        int start = hanging.hanging.getCurrentPosition();
        int end = start + ENCODER_TO_EXTEND_UP;

        hanging.setHangingPower(1);

        hanging.hanging.setTargetPosition(end);

        while (hanging.hanging.isBusy() && opModeIsActive()) {

        }
        hanging.setHangingPower(0);
        hanging.hanging.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
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
