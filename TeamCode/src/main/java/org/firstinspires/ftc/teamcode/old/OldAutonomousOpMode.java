package org.firstinspires.ftc.teamcode.old;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.for_camera_opmodes.LinearOpModeCamera;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import static java.lang.Math.abs;
import static org.firstinspires.ftc.teamcode.AutonomousOpMode.degreeCorrection;

public abstract class OldAutonomousOpMode extends LinearOpModeCamera {

    public OldDriveTrain motors;
    public OldGyro gyro;
    //  public Jewel jewel;
    public OldServos Oservos;
    //  public VuMarkRecognition vuMark;
    public ModernRoboticsI2cRangeSensor snsRange;
    public DigitalChannel snsLimitSwitch; //port 1
    public DcMotor mtrRelic;

    public DcMotor mfr;
    public DcMotor mfl;
    public DcMotor mbr;
    public DcMotor mbl;

    public static final double COUNTS_PER_MOTOR_REV = 498;
    public static final double DRIVE_GEAR_REDUCTION = 1.0;     // This is < 1.0 if geared UP
    public static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    public static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    public static final double DRIVE_SPEED = 0.6;
    public static final double TURN_SPEED = 0.5;


    //jewel
    public static final double DOWN_STOW_POS = .19; //ready for init
    public static final double SWING_STOW_POS = .34;

    public static final double DOWN_EX_POS = .84; //looking for the correct ball
    public static final double SWING_EX_POS = .58;

    public static final double SWING_LEFT = .22; //swing left or right to knock the jewel off
    public static final double SWING_RIGHT = .83;

    public static final double EXPLORE_LEFT = .56;
    public static final double EXPLORE_RIGHT = .60;

    public boolean isOverTime = false;

  /* public void initit() {


        mfr = hardwareMap.get(DcMotor.class, "mfr");
        mfl = hardwareMap.get(DcMotor.class, "mfl");
        mbr = hardwareMap.get(DcMotor.class, "mbr");
        mbl = hardwareMap.get(DcMotor.class, "mbl");

        mtrRelic = hardwareMap.dcMotor.get("mtrRelic");

        mfr.setDirection(DcMotor.Direction.REVERSE);
        mfl.setDirection(DcMotor.Direction.FORWARD);
        mbr.setDirection(DcMotor.Direction.REVERSE);
        mbl.setDirection(DcMotor.Direction.FORWARD);

     //   motors = new OldDriveTrain(hardwareMap, telemetry);
//        gyro = new OldGyro(hardwareMap, telemetry);
        //jewel = new Jewel(hardwareMap, telemetry);
      //  servos = new OldServos(hardwareMap);

        snsRange = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "snsRange");
        snsLimitSwitch = hardwareMap.get(DigitalChannel.class, "snsLimitSwitch");

        // vuMark = new VuMarkRecognition(this.hardwareMap, this.telemetry);

        servos.autoInit();

        telemetry.addData("Status", "OldDriveTrain Initialized");
        telemetry.update();
    }*/

    /*
    public void moveBySensor(double arg1, double arg2) {
        while(!sensor.isDone() && opModeIsActive()) {
            motors.moveTo(arg1, arg2);
        }
    }
     */
    public void SetEncoderOff() {
        //mfr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mfr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mbr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // mfl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mfl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mbl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void SetEncoderMode() {
        mfr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mfr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mfr.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        mbr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mbr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mbr.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        mfl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mfl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mfl.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        mbl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mbl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mbl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void MoveToByTime(long time, double direction, double power) {
        SetEncoderOff();
        motors.MoveTo(direction, power);
        sleep(time);
        motors.stopMotors();
    }

    public void MoveToByRange(double distance, double direction, double power) {
        motors.MoveTo(direction, power);
        while (snsRange.cmUltrasonic() > distance && opModeIsActive()) {
            telemetry.addData("snsRange", snsRange.cmUltrasonic());
        }
        motors.stopMotors();
    }

    public void MoveToBySwitch(double direction, double power) {
        SetEncoderOff();
        motors.MoveTo(direction, power);
        double maxTime = 4; //seconds
        ElapsedTime runtime = new ElapsedTime();

        runtime.reset();
        runtime.startTime();

        while (snsLimitSwitch.getState() && opModeIsActive() && runtime.seconds() < maxTime) {
        }
        motors.stopMotors();
        if (runtime.seconds() < maxTime) {
            isOverTime = false;
        } else {
            isOverTime = true;
        }

    }


    public void MoveToByEncoder(double distance, double degree, double power) {
        double degreeRad = Math.toRadians(degree - degreeCorrection); // Convert to radians
        double cs = Math.cos(degreeRad);
        double sn = Math.sin(degreeRad);

        SetEncoderMode();
        double targetCounts = (int) (distance * COUNTS_PER_INCH);

        int rightFrontStartPos = mfr.getCurrentPosition();
        int rightRearStartPos = mbr.getCurrentPosition();
        int leftFrontStartPos = mfl.getCurrentPosition();
        int leftRearStartPos = mbl.getCurrentPosition();

        int target = (int) (distance * COUNTS_PER_INCH);

        int rightFrontEndPos = rightFrontStartPos + (int) (target * (-sn + cs));
        int leftFrontEndPos = leftFrontStartPos + (int) (target * (sn + cs));
        int rightRearEndPos = rightRearStartPos + (int) (target * (sn + cs));
        int leftRearEndPos = leftRearStartPos + (int) (target * (-sn + cs));

        double pwr = power;
        double rightFrontPower = pwr * (-sn + cs);
        double leftFrontPower = pwr * (sn + cs);
        double rightRearPower = pwr * (sn + cs);
        double leftRearPower = pwr * (-sn + cs);

        mfr.setPower(rightFrontPower);
        mfl.setPower(leftFrontPower);
        mbr.setPower(rightRearPower);
        mbl.setPower(leftRearPower);

        mfr.setTargetPosition(rightFrontEndPos);
        mfl.setTargetPosition(leftFrontEndPos);
        mbr.setTargetPosition(rightRearEndPos);
        mbl.setTargetPosition(leftRearEndPos);
        // run until the end of the match (driver presses STOP)
        //while ((Math.abs(mfl.getCurrentPosition() - end) > 100 )&& opModeIsActive()) {}
        while (mfl.isBusy() && opModeIsActive()) {
        }
        /*|| mfl.isBusy() || mbr.isBusy() || mbl.isBusy())*/
        motors.stopMotors();

    }

    public void Turn(double TurnDegree) {
        // clock is negative; anti-clock positive degree
        // Maximum degree is 180

        if (TurnDegree > 180) {
            TurnDegree = 180;
        }
        if (TurnDegree < -180) {
            TurnDegree = -180;
        }

        double MaxPower = 0.5;
        double minPower = 0.2;

        double correctionDegree = 4;
        Orientation angles;
        double beginDegree;
        double currentDegree;
        double target;
        double angleDiff;
        double maxTime = 6; //seconds
        ElapsedTime runtime = new ElapsedTime();

        SetEncoderOff();
        gyro.ResetAngle();
        //
        sleep(1000);
//
        beginDegree = gyro.getZDegree();
        if (TurnDegree < 0) {
            correctionDegree = -correctionDegree;
        }
        target = beginDegree + TurnDegree - correctionDegree;

        runtime.reset();
        runtime.startTime();

        angleDiff = TurnDegree;
        while (abs(angleDiff) > 1 && runtime.seconds() < maxTime && opModeIsActive()) {
            double leftPower;
            double rightPower;
            currentDegree = gyro.getZDegree();
            if (TurnDegree > 0) {
                if (currentDegree < -90) {
                    currentDegree = 360 + currentDegree;
                }
            }
            if (TurnDegree < 0) {
                if (currentDegree > 90) {
                    currentDegree = -360 + currentDegree;
                }
            }

            angleDiff = (currentDegree - target);
            double drive;
            drive = (angleDiff) / 100.0;

            if (abs(drive) > MaxPower) {
                drive = MaxPower * abs(drive) / drive;
            }
            if (abs(drive) < minPower) {
                if (drive > 0) {
                    drive = minPower;
                } else if (drive < 0) {
                    drive = -minPower;
                } else {
                    drive = 0;
                }
            }

            leftPower = Range.clip(drive, -1.0, 1.0);
            rightPower = Range.clip(-drive, -1.0, 1.0);
            mfl.setPower(leftPower);
            mbl.setPower(leftPower);
            mfr.setPower(rightPower);
            mbr.setPower(rightPower);

            telemetry.addData("Left Power", leftPower);
            telemetry.addData("right Power", rightPower);
            telemetry.addData("beginDegree", beginDegree);
            telemetry.addData("CurrentDegree", currentDegree);
            telemetry.addData("angleDiff", angleDiff);
            telemetry.update();
        }
        motors.stopMotors();

        telemetry.addData("Loop Done-Angle", gyro.getZDegree());
        telemetry.update();
    }

}
