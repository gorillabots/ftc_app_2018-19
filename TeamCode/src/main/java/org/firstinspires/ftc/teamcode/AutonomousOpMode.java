package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Vision.Detector;
import org.firstinspires.ftc.teamcode.old.OldAutonomousOpMode;
import org.firstinspires.ftc.teamcode.old.OldGyro;
import org.firstinspires.ftc.teamcode.subsystems.Hanging;
import org.firstinspires.ftc.teamcode.subsystems.Sensors;

public abstract class AutonomousOpMode extends OldAutonomousOpMode {
    public Sensors sensor;
    public Detector detector;
    public Hanging hanging;

    public DcMotor mExtend;
    public DcMotor mPivotAndy;
    public DcMotor mPivotRev;

/* Saquoit

    Rev Hub #10
    hang 2 - mHang
    extend 3 - mExtend
    pivot andymark 1 - mPivotAndy
    pivot rev 0  - mPivotRev

    servo 5 - sCan

    Rev Hub #2
    front right 3 - mfr
    back right 0 - mbr
    back left 1 - mbl
    front left 2 - mfl

    Directions
    mHang - Negative is up
    mExtend - Negative is extend

*/


    public static final int degreeCorrection = 0;
    /* Default (Hanging Mechanism is Up)
                       90
                 180          0
                      270

    */

    public static final int ENCODER_TO_EXTEND_UP = 1;

    public void initializeAutonomous() {
        mfr = hardwareMap.get(DcMotor.class, "mfr");
        mfl = hardwareMap.get(DcMotor.class, "mfl");
        mbr = hardwareMap.get(DcMotor.class, "mbr");
        mbl = hardwareMap.get(DcMotor.class, "mbl");

        mfr.setDirection(DcMotor.Direction.REVERSE);
        mfl.setDirection(DcMotor.Direction.FORWARD);
        mbr.setDirection(DcMotor.Direction.REVERSE);
        mbl.setDirection(DcMotor.Direction.FORWARD);

        hanging = new Hanging(hardwareMap, telemetry);

        mExtend = hardwareMap.get(DcMotor.class, "mExtend");
        mPivotAndy = hardwareMap.get(DcMotor.class, "mPivotAndy");
        mPivotRev = hardwareMap.get(DcMotor.class, "mPivotRev");


        //sensor = new Sensors(hardwareMap,telemetry);

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
        hanging.isEncoderMode(true);

        int start = hanging.mHang.getCurrentPosition();
        int end = start + ENCODER_TO_EXTEND_UP;

        hanging.setHangingPower(1);

        hanging.mHang.setTargetPosition(end);

        while (hanging.mHang.isBusy() && opModeIsActive()) {

        }
        hanging.setHangingPower(0);
        hanging.isEncoderMode(false);

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
