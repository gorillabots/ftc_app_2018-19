package org.firstinspires.ftc.teamcode.autonomous.UticaAutonomous;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousOpMode;

import static org.firstinspires.ftc.teamcode.teleop.TeleOpOpMode.ENCODER_TO_DEPOSITUP;
import static org.firstinspires.ftc.teamcode.subsystems.Servos.COLLECTION_INIT;
import static org.firstinspires.ftc.teamcode.subsystems.Servos.DEPOSIT_INIT;

public abstract class CraterAutosWithMineral extends AutonomousOpMode {


    public void scoreLeftCraterTwoCycle() {
        craterBeginningWithMineral(1);
        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER);
        minerals.mCollect.setPower(-1);

        servos.setCollectionCollect(true);
        sleep(20000);

    }

    public void scoreMiddleCraterTwoCycle() {

        minerals.mExtendHoriz.setPower(.3);
        sleep(300);
        minerals.mExtendHoriz.setPower(0);
        servos.setCollectionAlmostCollect(); //score and collect mineral
        minerals.mCollect.setPower(-1);
        extendHorizToEncoder(700);
        servos.setCollectionCollect(true);
        sleep(1000);
        minerals.mCollect.setPower(0);

        servos.sCollectionRot.setPosition(COLLECTION_INIT);
        servos.sDepositRot.setPosition(DEPOSIT_INIT);

        retractHoriz();

        servos.setDepositDump(false);
        servos.setCollectionCollect(false);

        servos.setBackstopColOpen(true);
        minerals.mCollect.setPower(-1);

        TurnFaster(-30);

        minerals.mCollect.setPower(0);
        servos.setDepositComingDown();

        minerals.mExtendHoriz.setPower(-.15);
        minerals.isEncoderModeVert(true);

        int start = minerals.mExtendVert.getCurrentPosition();
        int end = start - ENCODER_TO_DEPOSITUP;


        minerals.mExtendVert.setPower(-1);

        minerals.mExtendVert.setTargetPosition(end);

        TurnAbsolute(0);

        minerals.mExtendHoriz.setPower(0);

        MoveUntilEncoder(11, 90, 1);

        MoveUntilTime(750, 0, .6);

        minerals.mExtendVert.setPower(-.2);

        servos.setDepositDump(true);

        sleep(1500);

        servos.setDepositComingDown();

        sleep(100);

        minerals.isEncoderModeVert(false);

        minerals.mExtendVert.setPower(.55);

        servos.setCollectionCollect(false);

        minerals.isEncoderModeHoriz(true);
        servos.setBackstopColOpen(false);

        minerals.mExtendHoriz.setPower(-.8);

        extendHorizToEncoder(700);

        while (opModeIsActive() && sensors.vertTouch.getState()) {
        }

        minerals.mExtendVert.setPower(0);
        minerals.mExtendHoriz.setPower(0);

        MoveUntilEncoder(10, 180, .5);
        minerals.mCollect.setPower(-1);
        servos.setCollectionCollect(true);

        MoveUntilEncoder(5, 0, .5);
        MoveUntilEncoder(10, 180, .5);
        minerals.mCollect.setPower(-1);

        TurnFaster(5);
        TurnFaster(-5);

        servos.servoReadyToRetract();
        retractHoriz();

        servos.setCollectionCollect(false);
        servos.setDepositDump(false);
        servos.setBackstopColOpen(true);

        sleep(1000);

        minerals.mCollect.setPower(0);

        servos.setDepositComingDown();
        servos.setBackstopColOpen(false);
        minerals.mExtendHoriz.setPower(-.1);
        superSpecialEncoder(14, 0, .5);

        servos.setDepositDump(true);
        sleep(1200);
        servos.setDepositComingDown();

        minerals.isEncoderModeVert(false);

        minerals.mExtendVert.setPower(.55);

        servos.setCollectionCollect(false);

        extendHorizToEncoder(1100);

        while (opModeIsActive() && sensors.vertTouch.getState()) {
        }

        minerals.mExtendVert.setPower(0);
        minerals.mExtendHoriz.setPower(0);

        minerals.mCollect.setPower(-1);

        servos.setCollectionCollect(true);
        sleep(20000);
    }

    public void scoreRightCraterTwoCycle() {
        craterBeginningWithMineral(3);
        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER);
        minerals.mCollect.setPower(-1);

        servos.setCollectionCollect(true);
        sleep(20000);
    }















    public void scoreLeftCraterOneCycle() {
        craterBeginningWithMineral(1);

        servos.setDepositDump(false);
        servos.setCollectionCollect(false);

        servos.setBackstopColOpen(true);
        minerals.mCollect.setPower(-1);

        TurnAbsolute(0);

        minerals.mCollect.setPower(0);
        servos.setDepositComingDown();

        minerals.mExtendHoriz.setPower(-.15);

        sleep(500);
        minerals.mExtendHoriz.setPower(0);
        minerals.isEncoderModeVert(true);

        int start = minerals.mExtendVert.getCurrentPosition();
        int end = start - ENCODER_TO_DEPOSITUP;


        minerals.mExtendVert.setPower(-1);

        minerals.mExtendVert.setTargetPosition(end);

        MoveUntilEncoder(11, 90, 1);

        MoveUntilTime(750, 0, .6);

        minerals.mExtendVert.setPower(-.2);
        servos.setDepositDump(true);

        sleep(1500);

        servos.setDepositComingDown();

        sleep(100);

        minerals.isEncoderModeVert(false);

        minerals.mExtendVert.setPower(.55);

        servos.setCollectionCollect(false);

        minerals.isEncoderModeHoriz(true);
        servos.setBackstopColOpen(false);

        minerals.isEncoderModeVert(false);

        TurnAbsolute(28);

        minerals.mExtendHoriz.setPower(-.8);

        extendHorizToEncoder(700);

        timer.reset();
        timer.startTime();

        while (opModeIsActive() && sensors.vertTouch.getState() && timer.seconds() < 2.5) {
        }

        minerals.mExtendVert.setPower(0);
        minerals.mExtendHoriz.setPower(0);

        MoveUntilEncoder(10,180 ,.8);

        sleep(15000);

    }


    public void scoreMiddleCraterOneCycle() {
        minerals.mExtendHoriz.setPower(.3);
        sleep(300);
        minerals.mExtendHoriz.setPower(0);
        servos.setCollectionAlmostCollect(); //score and collect mineral
        minerals.mCollect.setPower(-1);
        extendHorizToEncoder(700);
        servos.setCollectionCollect(true);
        sleep(1000);
        minerals.mCollect.setPower(0);

        servos.sCollectionRot.setPosition(COLLECTION_INIT);
        servos.sDepositRot.setPosition(DEPOSIT_INIT);

        retractHoriz();

        servos.setDepositDump(false);
        servos.setCollectionCollect(false);

        servos.setBackstopColOpen(true);
        minerals.mCollect.setPower(-1);

        TurnFaster(-30);

        minerals.mCollect.setPower(0);
        servos.setDepositComingDown();

        minerals.mExtendHoriz.setPower(-.15);
        minerals.isEncoderModeVert(true);

        int start = minerals.mExtendVert.getCurrentPosition();
        int end = start - ENCODER_TO_DEPOSITUP;


        minerals.mExtendVert.setPower(-1);

        minerals.mExtendVert.setTargetPosition(end);

        TurnAbsolute(0);

        minerals.mExtendHoriz.setPower(0);

        MoveUntilEncoder(11, 90, 1);

        MoveUntilTime(750, 0, .6);

        minerals.mExtendVert.setPower(-.2);

        servos.setDepositDump(true);

        sleep(1500);

        servos.setDepositComingDown();

        sleep(100);

        minerals.isEncoderModeVert(false);

        minerals.mExtendVert.setPower(.55);

        servos.setCollectionCollect(false);

        minerals.isEncoderModeHoriz(true);
        servos.setBackstopColOpen(false);

        minerals.mExtendHoriz.setPower(-.8);

        extendHorizToEncoder(700);

        timer.reset();
        timer.startTime();
        while (opModeIsActive() && sensors.vertTouch.getState() && timer.seconds() < 2.5) {
        }

        minerals.mExtendVert.setPower(0);
        minerals.mExtendHoriz.setPower(0);


        MoveUntilEncoder(10,180 ,.8);

        sleep(15000);
    }

    public void scoreRightCraterOneCycle() {
        craterBeginningWithMineral(3);

        servos.setDepositDump(false);
        servos.setCollectionCollect(false);

        servos.setBackstopColOpen(true);
        minerals.mCollect.setPower(-1);

        TurnAbsolute(0);

        minerals.mCollect.setPower(0);
        servos.setDepositComingDown();

        minerals.mExtendHoriz.setPower(-.15);

        sleep(500);
        minerals.mExtendHoriz.setPower(0);
        minerals.isEncoderModeVert(true);

        int start = minerals.mExtendVert.getCurrentPosition();
        int end = start - ENCODER_TO_DEPOSITUP;


        minerals.mExtendVert.setPower(-1);

        minerals.mExtendVert.setTargetPosition(end);

        MoveUntilEncoder(11, 90, 1);

        MoveUntilTime(750, 0, .6);

        minerals.mExtendVert.setPower(-.2);
        servos.setDepositDump(true);

        sleep(1500);

        servos.setDepositComingDown();

        sleep(100);

        minerals.isEncoderModeVert(false);

        minerals.mExtendVert.setPower(.55);

        servos.setCollectionCollect(false);

        minerals.isEncoderModeHoriz(true);
        servos.setBackstopColOpen(false);

        minerals.isEncoderModeVert(false);

        TurnAbsolute(-28);

        minerals.mExtendHoriz.setPower(-.8);

        extendHorizToEncoder(700);

        timer.reset();
        timer.startTime();

        while (opModeIsActive() && sensors.vertTouch.getState() && timer.seconds() < 2.5) {
        }

        minerals.mExtendVert.setPower(0);
        minerals.mExtendHoriz.setPower(0);

        MoveUntilEncoder(10,180 ,.8);

        sleep(15000);
    }


    private void craterBeginningWithMineral(int side) {
        if (side == 1) {

            minerals.mExtendHoriz.setPower(.3);
            TurnFaster(-30);
            minerals.mExtendHoriz.setPower(0);
            hanging.setHangingPower(-.5);
            TurnAbsolute(28);
            hanging.setHangingPower(0);

            servos.setCollectionAlmostCollect(); //score and collect mineral
            minerals.mCollect.setPower(-1);
            extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_SIDE_MINERAL);
            servos.setCollectionCollect(true);
            sleep(500);

            TurnFaster(10);
            TurnFaster(-20);
            minerals.mCollect.setPower(0);
            servos.setCollectionCollect(false);

            servos.sCollectionRot.setPosition(COLLECTION_INIT);
            servos.sDepositRot.setPosition(DEPOSIT_INIT);

            retractHoriz();
        } else if (side == 2) {
            minerals.mExtendHoriz.setPower(.3);
            sleep(300);
            minerals.mExtendHoriz.setPower(0);
            servos.setCollectionAlmostCollect(); //score and collect mineral
            minerals.mCollect.setPower(-1);
            extendHorizToEncoder(700);
            servos.setCollectionCollect(true);
            sleep(1000);
            minerals.mCollect.setPower(0);

            servos.setCollectionCollect(false);

            retractHoriz();

            TurnFaster(-30);
            hanging.setHangingPower(-.5);
            TurnAbsolute(0);
            hanging.setHangingPower(0);

        } else if (side == 3) {
            minerals.mExtendHoriz.setPower(.3);
            TurnAbsolute(-25);
            minerals.mExtendHoriz.setPower(0);
            servos.setCollectionAlmostCollect(); //score and collect mineral
            minerals.mCollect.setPower(-1);
            extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_SIDE_MINERAL);
            servos.setCollectionCollect(true);
            sleep(1000);

            TurnFaster(10);

            hanging.setHangingPower(-.5);
            TurnFaster(-20);
            hanging.setHangingPower(0);

            minerals.mCollect.setPower(0);

            servos.sCollectionRot.setPosition(COLLECTION_INIT);
            servos.sDepositRot.setPosition(DEPOSIT_INIT);

            retractHoriz();


        } else {
            sleep(1);
        }


    }

    private void maneuverToHalfwayPositionC() {

        TurnAbsolute(0);
        MoveUntilEncoder(13, 180, .9);
        TurnAbsolute(87);
        MoveUntilEncoder(36, 180, 1);
        TurnAbsolute(135);
        minerals.mExtendHoriz.setPower(.3);
        MoveUntilTime(1500, 270, .6);
        minerals.mExtendHoriz.setPower(0);
    }

    private void superSpecialEncoder(double distance, double degree, double power) {
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

        minerals.mExtendHoriz.setPower(-.2);

        sleep(500);

        minerals.mExtendHoriz.setPower(0);

        minerals.isEncoderModeVert(true);

        int start = minerals.mExtendVert.getCurrentPosition();
        int end = start - ENCODER_TO_DEPOSITUP;

        minerals.mExtendVert.setPower(-1);

        minerals.mExtendVert.setTargetPosition(end);

        while ((mfl.isBusy() || minerals.mExtendVert.isBusy()) && opModeIsActive()) {
        }
        /*|| mfl.isBusy() || mbr.isBusy() || mbl.isBusy())*/
        stopMotors();
    }


}
