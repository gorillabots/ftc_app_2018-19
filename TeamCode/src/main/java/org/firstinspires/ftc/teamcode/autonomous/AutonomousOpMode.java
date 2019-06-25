package org.firstinspires.ftc.teamcode.autonomous;

import android.content.res.AssetManager;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.for_camera_opmodes.LinearOpModeCamera;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.tests.visionArray.Detector;
import org.firstinspires.ftc.teamcode.subsystems.old.OldGyro;
import org.firstinspires.ftc.teamcode.subsystems.Hanging;
import org.firstinspires.ftc.teamcode.subsystems.MineralCollectionMechanism;
import org.firstinspires.ftc.teamcode.subsystems.Sensors;
import org.firstinspires.ftc.teamcode.subsystems.Servos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.min;
import static org.firstinspires.ftc.teamcode.teleop.TeleOpOpMode.ENCODER_TO_DEPOSITUP;
import static org.firstinspires.ftc.teamcode.subsystems.Servos.DEPOSIT_INIT;

public abstract class AutonomousOpMode extends LinearOpModeCamera {

    public Sensors sensors;
    public Detector detector;
    public Hanging hanging;
    public Servos servos;
    public OldGyro gyro;
    public MineralCollectionMechanism minerals;
    public ElapsedTime timer;

    public DcMotor mfr;
    public DcMotor mfl;
    public DcMotor mbr;
    public DcMotor mbl;


/* Taras Bot

    Rev Hub #3

    mCollect - port 0
    mExtendVert - port 1
    mbr - port 2
    mfr - port 3

    sTeamMarkerRot - port 5
    sBackstopDep - port 4

    Rev Hub #2

    mHang - port 0
    mExtendHoriz - port 1
    mbl - port 3
    mfl - port 2

    sDepositRot - port 2
    sBackstopCol - port 0
    sCollectionRot - port 1

    Directions

    mCollect positive is reverse
    mExtendVert positive is down
    mHang positive is up
    mExtendHoriz positive is extending out

*/

    /* (Hanging Mechanism side is Up)

    Drive
                       0
                270          90
                      180

    Turning
                    ----- x ------
        positive  |               | negative
                  |               |

    */


    public static final int degreeCorrection = 180;
    public static final int ENCODER_TO_EXTEND_UP = 7350; //5.7 inches from the top

    public static final double COUNTS_PER_MOTOR_REV = 576;     //20:1
    public static final double DRIVE_GEAR_REDUCTION = 2.0;     // This is < 1.0 if geared UP
    public static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    public static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);

    public static final int ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER = 1100;
    public static final int ENCODER_TO_EXTEND_HORIZ_SIDE_MINERAL = 680;
    public static final int ENCODER_TO_EXTEND_HORIZ_MID_MINERAL = 600;


    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";

    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;

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
        servos = new Servos(hardwareMap, telemetry);
        gyro = new OldGyro(hardwareMap, telemetry);
        sensors = new Sensors(hardwareMap, telemetry);
        minerals = new MineralCollectionMechanism(hardwareMap, telemetry);
        timer = new ElapsedTime();

        minerals.mExtendHoriz.setPower(.18);

        servos.initializeServos();

        initVuforia();

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }
        telemetry.addData("Status:", "Done with initial init");
        telemetry.update();


    }


    private void initVuforia() {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.vuforiaLicenseKey = getVuforiaKey();
        parameters.cameraName = hardwareMap.get(WebcamName.class, "webcam");
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    private String getVuforiaKey() {
        try {
            AssetManager am = hardwareMap.appContext.getAssets();
            InputStream is = am.open("vuforiakey.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String key = br.readLine();
            return key;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }

//---------------------------------STANDARDIZED AUTONOMOUS FUNCTIONS--------------------------------//

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

    public int detectYellowTensor() {
        int position = 2;
        if (!isStarted() && !isStopRequested()) {
            /** Activate Tensor Flow Object Detection. */
            if (tfod != null) {
                tfod.activate();
            }

            while (!isStarted() && !isStopRequested()) {
                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        telemetry.addData("# Object Detected", updatedRecognitions.size());
                        if (updatedRecognitions.size() == 2) {
                            //Assume Right Two items
                            int goldMineralX = -1;
                            int silverMineral1X = -1;
                            int silverMineral2X = -1;
                            for (Recognition recognition : updatedRecognitions) {
                                if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                                    goldMineralX = (int) recognition.getLeft();
                                } else if (silverMineral1X == -1) {
                                    silverMineral1X = (int) recognition.getLeft();
                                } else {
                                    silverMineral2X = (int) recognition.getLeft();
                                }
                            }
                            telemetry.addData("G,S1,S2 Positions", "" + goldMineralX + "," + silverMineral1X + "," + silverMineral2X);
                            if (goldMineralX != -1 && silverMineral1X != -1) {
                                if (goldMineralX < silverMineral1X) {
                                    position = 2;
                                    //telemetry.addData("Gold Mineral Position", "Center");
                                } else if (goldMineralX > silverMineral1X) {
                                    //telemetry.addData("Gold Mineral Position", "Right");
                                    position = 3;
                                }
                            } else if (silverMineral1X != -1 && silverMineral2X != -1) {
                                //telemetry.addData("Gold Mineral Position", "Left");
                                position = 1;
                            }
                        }
                    }
                }
                telemetry.addData("position", position);
                telemetry.update();
            }
        }
        if (tfod != null) {
            tfod.shutdown();
        }
        return position;
    }

    public void unHangWithEncoder() {
        hanging.isEncoderMode(false);
        hanging.isEncoderMode(true);

        int start = hanging.mHang.getCurrentPosition();
        int end = start + ENCODER_TO_EXTEND_UP;

        hanging.setHangingPower(1);

        hanging.mHang.setTargetPosition(end);

        boolean firstTime = true;

        while (hanging.mHang.isBusy() && opModeIsActive()) {
            if (firstTime){
                minerals.mCollect.setPower(-1);

                sleep(500);

                minerals.mCollect.setPower(0);

                firstTime = false;
            }
        }
        hanging.setHangingPower(0);
        hanging.isEncoderMode(false);

    /*    hanging.mHang.setPower(.4);
        sleep(700);
        hanging.mHang.setPower(0);*/

        gyro.ResetAngle();

    }

    public void unhangWithTouch(){
        hanging.isEncoderMode(false);
        hanging.setHangingPower(1);
        timer.reset();
        timer.startTime();
        while (opModeIsActive() && sensors.hangTouch.getState() && timer.seconds() < 5)
        {}
        hanging.setHangingPower(0);
        gyro.ResetAngle();
    }

    public void dumpTeamMarker() {
        servos.setTeamMarkerFree(true);
    }
    public void raiseTeamMarker(){
        servos.setTeamMarkerFree(false);
    }

    public void extendHorizToEncoder(int encoder) {

        minerals.isEncoderModeHoriz(true);

        int start = minerals.mExtendHoriz.getCurrentPosition();
        int end = start - encoder;

        minerals.mExtendHoriz.setPower(-1);

        minerals.mExtendHoriz.setTargetPosition(end);
        timer.reset();
        timer.startTime();
        while (minerals.mExtendHoriz.isBusy() && opModeIsActive() && timer.seconds() < 3) {

        }
        minerals.mExtendHoriz.setPower(0);
        minerals.isEncoderModeHoriz(false);

    }

    public void retractHoriz() {
        minerals.isEncoderModeHoriz(false);

        servos.servoReadyToRetract();

        minerals.mExtendHoriz.setPower(.66);

        timer.reset();
        timer.startTime();

        while (opModeIsActive() && sensors.horizTouch.getState() && timer.seconds() < 3) {

        }
        minerals.mExtendHoriz.setPower(0);
    }

    public void rudimentaryWiggle(double degreeToReturnTo) {

        mfl.setPower(1);
        mbl.setPower(1);
        mfr.setPower(-1);
        mbr.setPower(-1);
        sleep(150);

        mbr.setPower(1);
        mfr.setPower(1);
        mbl.setPower(-1);
        mfl.setPower(-1);
        sleep(200);

        TurnAbsolute(degreeToReturnTo);
    }


    public void setVertExtentionUp() {

        minerals.isEncoderModeVert(true);

        int start = minerals.mExtendVert.getCurrentPosition();
        int end = start - ENCODER_TO_DEPOSITUP;

        minerals.mExtendVert.setPower(-1);

        minerals.mExtendVert.setTargetPosition(end);

        timer.reset();

        timer.startTime();

        while (opModeIsActive() && minerals.mExtendVert.isBusy() && timer.seconds() < 3) {

        }

        minerals.mExtendVert.setPower(-.2);
    }

    public void setVertExtensionDown() {

        minerals.isEncoderModeVert(false);

        timer.reset();

        timer.startTime();

        while (opModeIsActive() && sensors.vertTouch.getState() && timer.seconds() < 3) {

            minerals.mExtendVert.setPower(.6);

        }

        minerals.mExtendVert.setPower(0);


    }

    public void depositSequence(){

        servos.setDepositDump(false);
        servos.setCollectionCollect(false);

        sleep(300);

        minerals.mCollect.setPower(-1);

        servos.setBackstopColOpen(true);
        servos.setBackstopDepOpen(true);

        sleep(1000);

        minerals.mExtendHoriz.setPower(.4);
        servos.setBackstopDepOpen(false);

        setVertExtentionUp();

        minerals.mExtendHoriz.setPower(0);

        servos.setBackstopDepOpen(true);
        servos.setDepositDump(true);

        sleep(500);

        servos.setBackstopDepOpen(false);
        servos.sDepositRot.setPosition(DEPOSIT_INIT);
        servos.setBackstopColOpen(false);
        servos.setCollectionCollect(false);

        minerals.mExtendHoriz.setPower(0);

        setVertExtensionDown();

    }


//-----------------------------------MOVE UNTIL FUNCTIONS------------------------------------------//

    public void MoveUntilTime(long timeMilli, double direction, double power) {
        setDriveEncoderOn(false);
        MoveTo(direction, power);
        sleep(timeMilli);
        stopMotors();
    }

    public void MoveUntilEncoder(double distance, double degree, double power) {
        double degreeRad = Math.toRadians(degree - degreeCorrection);
        double cs = Math.cos(degreeRad);
        double sn = Math.sin(degreeRad);

        setDriveEncoderOn(true);

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

        while (mfl.isBusy() && opModeIsActive()) {
        }
        /*|| mfl.isBusy() || mbr.isBusy() || mbl.isBusy())*/
        stopMotors();
    }

    public void MoveAndExtend(double distance, double degree, double power, int encoder, int time) {

        double degreeRad = Math.toRadians(degree - degreeCorrection);
        double cs = Math.cos(degreeRad);
        double sn = Math.sin(degreeRad);

        setDriveEncoderOn(true);


        minerals.isEncoderModeHoriz(true);

        int start = minerals.mExtendHoriz.getCurrentPosition();
        int end = start - encoder;

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

        minerals.mExtendHoriz.setPower(-1);

        mfr.setTargetPosition(rightFrontEndPos);
        mfl.setTargetPosition(leftFrontEndPos);
        mbr.setTargetPosition(rightRearEndPos);
        mbl.setTargetPosition(leftRearEndPos);

        minerals.mExtendHoriz.setTargetPosition(end);

        timer.reset();
        timer.startTime();

        while ((mfl.isBusy() || minerals.mExtendHoriz.isBusy()) && opModeIsActive() && timer.seconds() < time) {

            telemetry.addData("within", "true");
            telemetry.addData("mfl.busy", mfl.isBusy());
            telemetry.addData("mExtendHoriz.busy",minerals.mExtendHoriz.isBusy());
            telemetry.update();

        }
        /*|| mfl.isBusy() || mbr.isBusy() || mbl.isBusy())*/
        stopMotors();

        minerals.mExtendHoriz.setPower(0);

        telemetry.addData("within", "false");

    }

    public void MoveAndRetract(double distance, double degree, double power, int time) {


        minerals.isEncoderModeHoriz(false);

        servos.servoReadyToRetract();

        double degreeRad = Math.toRadians(degree - degreeCorrection);
        double cs = Math.cos(degreeRad);
        double sn = Math.sin(degreeRad);

        setDriveEncoderOn(true);

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


        minerals.mExtendHoriz.setPower(1);

        timer.reset();
        timer.startTime();

        while (opModeIsActive() && sensors.horizTouch.getState() && timer.seconds() < time) {

        }
        minerals.mExtendHoriz.setPower(0);


        while (mfl.isBusy() && opModeIsActive()) {
        }
        /*|| mfl.isBusy() || mbr.isBusy() || mbl.isBusy())*/
        stopMotors();

    }


    public void MoveTo(double degree, double power) {
        double degreeRad = Math.toRadians(degree - degreeCorrection); // Convert to radians
        double cs = Math.cos(degreeRad);
        double sn = Math.sin(degreeRad);

        double fr = power * (-sn + cs);
        double fl = power * (sn + cs);
        double br = power * (sn + cs);
        double bl = power * (-sn + cs);

        mfl.setPower(fl);
        mfr.setPower(fr);
        mbl.setPower(bl);
        mbr.setPower(br);
    }

    //------------------------------------------TURN-----------------------------------------------//

    public void TurnAbsolute(double TargetDegree) {
        // clock is negative; anti-clock positive degree
        // rotate range is (-90,90)

        if (TargetDegree > 180) {
            TargetDegree = 180;
        }
        if (TargetDegree < -180) {
            TargetDegree = -180;
        }

        double MaxPower = 0.5;
        double minPower = 0.2;

        double correctionDegree = 0;
        double beginDegree;
        double currentDegree;

        double target;
        double angleDiff;
        double maxTime = 6; //seconds
        ElapsedTime runtime = new ElapsedTime();

        setDriveEncoderOn(false);

        beginDegree = gyro.getZDegree();

        runtime.reset();
        runtime.startTime();

        angleDiff = TargetDegree - beginDegree;
        while (abs(angleDiff) > 1 && runtime.seconds() < maxTime && opModeIsActive()) {
            double leftPower;
            double rightPower;
            currentDegree = gyro.getZDegree();
            angleDiff = TargetDegree - currentDegree;
            if (angleDiff > 180) {
                angleDiff = angleDiff - 360;
            }
            if (angleDiff < -180) {
                angleDiff = angleDiff + 360;
            }

            if (angleDiff < 0) {
                angleDiff = angleDiff + correctionDegree;
            }
            if (angleDiff > 0) {
                angleDiff = angleDiff - correctionDegree;
            }

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

            leftPower = Range.clip(-drive, -1.0, 1.0);
            rightPower = Range.clip(drive, -1.0, 1.0);

            mfl.setPower(rightPower);
            mbl.setPower(rightPower);
            mfr.setPower(leftPower);
            mbr.setPower(leftPower);

            telemetry.addData("Left Power", leftPower);
            telemetry.addData("right Power", rightPower);
            telemetry.addData("beginDegree", beginDegree);
            telemetry.addData("CurrentDegree", currentDegree);
            telemetry.addData("angleDiff", angleDiff);
            telemetry.update();
        }
        stopMotors();

        telemetry.addData("Current ZDegree", gyro.getZDegree());
        telemetry.update();
    }

    public void TurnFaster(double TurnDegree) {
        if (TurnDegree > 180) {
            TurnDegree = 180;
        }
        if (TurnDegree < -180) {
            TurnDegree = -180;
        }

        double beginDegree = gyro.getZDegree();
        double TargetDegree = beginDegree + TurnDegree;
        if (TargetDegree > 180) {
            TargetDegree = TargetDegree - 360;
        }
        if (TargetDegree < -180) {
            TargetDegree = TargetDegree + 360;
        }
        TurnAbsolute(TargetDegree);
    }

    public void TurnSuperFast(double TurnDegree) {
        if (TurnDegree > 180) {
            TurnDegree = 180;
        }
        if (TurnDegree < -180) {
            TurnDegree = -180;
        }

        double beginDegree = gyro.getZDegree();
        double TargetDegree = beginDegree + TurnDegree;
        if (TargetDegree > 180) {
            TargetDegree = TargetDegree - 360;
        }
        if (TargetDegree < -180) {
            TargetDegree = TargetDegree + 360;
        }
        TurnAbsoluteFAST(TargetDegree);
    }

    public void TurnAbsoluteFAST(double TargetDegree) {
        // clock is negative; anti-clock positive degree
        // rotate range is (-90,90)

        if (TargetDegree > 180) {
            TargetDegree = 180;
        }
        if (TargetDegree < -180) {
            TargetDegree = -180;
        }

        double MaxPower = 1;
        double minPower = 0.5;

        double correctionDegree = 0;
        double beginDegree;
        double currentDegree;

        double target;
        double angleDiff;
        double maxTime = 6; //seconds
        ElapsedTime runtime = new ElapsedTime();

        setDriveEncoderOn(false);

        beginDegree = gyro.getZDegree();

        runtime.reset();
        runtime.startTime();

        angleDiff = TargetDegree - beginDegree;
        while (abs(angleDiff) > 1 && runtime.seconds() < maxTime && opModeIsActive()) {
            double leftPower;
            double rightPower;
            currentDegree = gyro.getZDegree();
            angleDiff = TargetDegree - currentDegree;
            if (angleDiff > 180) {
                angleDiff = angleDiff - 360;
            }
            if (angleDiff < -180) {
                angleDiff = angleDiff + 360;
            }

            if (angleDiff < 0) {
                angleDiff = angleDiff + correctionDegree;
            }
            if (angleDiff > 0) {
                angleDiff = angleDiff - correctionDegree;
            }

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

            leftPower = Range.clip(-drive, -1.0, 1.0);
            rightPower = Range.clip(drive, -1.0, 1.0);

            mfl.setPower(rightPower);
            mbl.setPower(rightPower);
            mfr.setPower(leftPower);
            mbr.setPower(leftPower);

            telemetry.addData("Left Power", leftPower);
            telemetry.addData("right Power", rightPower);
            telemetry.addData("beginDegree", beginDegree);
            telemetry.addData("CurrentDegree", currentDegree);
            telemetry.addData("angleDiff", angleDiff);
            telemetry.update();
        }
        stopMotors();

        telemetry.addData("Current ZDegree", gyro.getZDegree());
        telemetry.update();
    }

    public void Turn(double TurnDegree) {
        // clock is negative; anti-clock positive degree
        // Maximum degree is 180
        setDriveEncoderOn(false);

        if (TurnDegree > 180) {
            TurnDegree = 180;
        }
        if (TurnDegree < -180) {
            TurnDegree = -180;
        }

        double MaxPower = -0.5;
        double minPower = -0.1;

        double correctionDegree = 5;
        Orientation angles;
        double beginDegree;
        double currentDegree;
        double target;
        double angleDiff;
        double maxTime = 5; //seconds
        ElapsedTime runtime = new ElapsedTime();


        gyro.ResetAngle();

        sleep(1000);

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
        stopMotors();

        telemetry.addData("Loop Done-Angle", gyro.getZDegree());
        telemetry.update();
    }

    public void andyTurn(double targetDegree, double minimumPower, double maximumPower, double error) {

        double originalPosition = into0to360(gyro.getZDegree());
        double currentPosition = into0to360(gyro.getZDegree());
        double target = into0to360(targetDegree);
        double motorPower;
        double x;
        double y = 0;
        double middlePosition = (Math.abs(target - originalPosition) / 2);
        double distanceLeftToGo = target - middlePosition;
        double reflectiveXValue = middlePosition - distanceLeftToGo;

        while (opModeIsActive() && Math.abs(target - currentPosition) > error) {

            currentPosition = into0to360(gyro.getZDegree());

            x = currentPosition;

            if (middlePosition > currentPosition) {
                y = maximumPower;
            } else if (middlePosition <= currentPosition) {
                y = quadraticCurve(x, middlePosition, maximumPower, target, minimumPower, reflectiveXValue, minimumPower);
            }

            motorPower = y;
            setDrivePower(motorPower);
        }
        stopMotors();
    }

    public double into0to360(double degree) {
        double withinRangeTargetDegree;

        if (degree > 180) {
            withinRangeTargetDegree = 180;
        } else if (degree < -180) {
            withinRangeTargetDegree = -180;
        } else {
            withinRangeTargetDegree = degree;
        }
        double targetDegreeInTermsOf0To360;

        if (withinRangeTargetDegree <= 180 && withinRangeTargetDegree >= 0) {
            targetDegreeInTermsOf0To360 = withinRangeTargetDegree;
        } else {
            targetDegreeInTermsOf0To360 = withinRangeTargetDegree + 360;
        }

        return targetDegreeInTermsOf0To360;
    }

    public double quadraticCurve(double x, double x1, double y1, double x2, double y2, double x3, double y3) {
        double y = ((x - x2) * (x - x3)) / ((x1 - x2) * (x1 - x3)) * y1 +
                ((x - x1) * (x - x3)) / ((x2 - x1) * (x2 - x3)) * y2 +
                ((x - x1) * (x - x2)) / ((x3 - x1) * (x3 - x2)) * y3;
        return y;
    }

    //----------------------------------------SMALL ORGANIZATIONAL STUFF---------------------------//

    public void setDrivePower(double power) {
        mfr.setPower(power);
        mfl.setPower(power);
        mbl.setPower(power);
        mbr.setPower(power);
    }

    public void stopMotors() {
        mfr.setPower(0);
        mfl.setPower(0);
        mbl.setPower(0);
        mbr.setPower(0);
    }

    public void setDriveEncoderOn(boolean on) {
        if (on) {
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
        } else {
            mfr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            mbr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            mfl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            mbl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }
}
