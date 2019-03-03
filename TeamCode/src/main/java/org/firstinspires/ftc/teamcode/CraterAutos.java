package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.TeleOpOpMode.ENCODER_TO_DEPOSITUP;
import static org.firstinspires.ftc.teamcode.subsystems.Servos.COLLECTION_INIT;
import static org.firstinspires.ftc.teamcode.subsystems.Servos.DEPOSIT_INIT;

public abstract class CraterAutos extends AutonomousOpMode {

    //SINGLE CRATER --------------------------------------------------------------------------------------------
    public void scoreLeftCrater() {
        craterBeginning(1);

        maneuverToHalfwayPositionC();

        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER);

        dumpTeamMarker();
        sleep(1000);
        retractHoriz();

        TurnFaster(180);

        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER);

        minerals.mCollect.setPower(-1);

        servos.setCollectionCollect(true);

        sleep(15000);
    }


    public void scoreMiddleCrater() {
        craterBeginning(2);

        maneuverToHalfwayPositionC();

        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER);
        dumpTeamMarker();
        sleep(1000);
        retractHoriz();

        TurnFaster(180);

        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER);

        minerals.mCollect.setPower(-1);

        servos.setCollectionCollect(true);
        sleep(15000);
    }


    public void scoreRightCrater() {
        craterBeginning(3);


        TurnAbsolute(0);
        MoveUntilEncoder(11.5, 180, .9);
        TurnAbsolute(87);
        MoveUntilEncoder(36, 180, 1);
        TurnAbsolute(135);
        minerals.mExtendHoriz.setPower(.3);
        MoveUntilTime(1500, 270, .6);
        minerals.mExtendHoriz.setPower(0);
        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER);

        dumpTeamMarker();

        sleep(1000);

        retractHoriz();

        TurnFaster(180);

        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER);
        minerals.mCollect.setPower(-1);

        servos.setCollectionCollect(true);
        sleep(15000);
    }

    //SINGLE CRATER -----------------------------------------------------------------------------------------------------------------------

    //DOUBLE CRATER ------------------------------------------------------------------------------------------------------

    public void scoreLeftDouble() {

        TurnFaster(-20);
        hanging.setHangingPower(-.5);
        TurnAbsolute(28);
        hanging.setHangingPower(0);

        minerals.mCollect.setPower(-1);

        servos.setCollectionCollect(true);
        MoveUntilEncoder(28, 180, .6);

        minerals.mCollect.setPower(0);

        servos.setCollectionCollect(false);

        MoveUntilEncoder(5, 0, .8); //alignment
        TurnFaster(60);
        MoveUntilEncoder(20, 180, 1);
        TurnAbsolute(135);
        MoveUntilTime(750, 270, .7);
        MoveUntilEncoder(2, 90, .5);
        sleep(1); //wait for other team

        MoveUntilEncoder(51, 180, 1); //NEEDS TESTING

        dumpTeamMarker();

        sleep(1500);

        TurnAbsolute(-135);
        MoveUntilTime(100, 0, 1);
        servos.setCollectionLongrange();
        minerals.mCollect.setPower(-1);
        extendHorizToEncoder(850);

        rudimentaryWiggle(-135);

        servos.setCollectionCollect(false);
        minerals.mCollect.setPower(0);
        retractHoriz();

        TurnAbsolute(-45);
        MoveUntilTime(200, 90, .5);

        MoveAndExtend(55, 180, 1, 600, 10);

        minerals.mCollect.setPower(-1);

        servos.setCollectionCollect(true);
        sleep(10000);
    }

    public void scoreMiddleDouble() {

        TurnFaster(-22.5);
        hanging.setHangingPower(-.5);
        TurnAbsolute(0);
        hanging.setHangingPower(0);

        minerals.mCollect.setPower(-1);

        servos.setCollectionCollect(true);
        MoveUntilEncoder(23.5, 180, 1);

        minerals.mCollect.setPower(0);
        servos.setCollectionCollect(false);

        MoveUntilEncoder(8, 0, .9);
        TurnAbsolute(87);
        MoveUntilEncoder(41, 180, 1);
        TurnAbsolute(135);
        MoveUntilTime(500, 270, .7);
        MoveUntilEncoder(2, 90, 1);

        MoveUntilEncoder(46, 184, 1);

        MoveUntilTime(1500, 180, .8);

        MoveUntilEncoder(10, 0, 1);
        dumpTeamMarker();
        MoveUntilEncoder(10, 0, 1);
        TurnFaster(90);

        minerals.mCollect.setPower(-1);
        servos.setCollectionCollect(true);
        MoveUntilEncoder(20, 180, 1);

        minerals.mCollect.setPower(0);
        servos.setCollectionCollect(false);

        MoveUntilTime(700, 0, 1);
        MoveUntilTime(300, 0, .4);
        TurnFaster(90);
        MoveUntilTime(300, 90, .6);
        MoveUntilTime(100, 270, 1);
        MoveAndExtend(40, 180, 1, 600, 10);
        minerals.mCollect.setPower(-1);

        servos.setCollectionCollect(true);
        sleep(10000);

    }

    public void scoreRightDouble() {

        TurnAbsolute(-28);

        servos.setCollectionCollect(true);
        minerals.mCollect.setPower(-1);
        MoveUntilEncoder(30, 180, 1);

        minerals.mCollect.setPower(0);
        servos.setCollectionCollect(false);

        MoveUntilEncoder(12, 0, 1);
        TurnAbsolute(87);
        MoveUntilEncoder(43, 180, 1);
        TurnAbsolute(135);
        sleep(1); //wait for other team

        servos.setCollectionCollect(true);
        minerals.mCollect.setPower(-1);

        MoveUntilEncoder(20, 180, 1);

        servos.setCollectionCollect(false);
        minerals.mCollect.setPower(0);
        MoveUntilTime(1200, 270, .7);
        MoveUntilEncoder(2, 90, 1);
        MoveUntilEncoder(26, 180, 1);
        dumpTeamMarker();
        sleep(300);
        MoveUntilEncoder(20, 0, 1);
        retractHoriz();
        TurnFaster(180);
        MoveAndExtend(30, 180, 1, 600, 10);
        minerals.mCollect.setPower(-1);

        servos.setCollectionCollect(true);
        sleep(10000);

    }


//DOUBLE CRATER ------------------------------------------------------------------------------------------


//CRATER WITHOUT TEAM MARKER --------------------------------------------------------

    public void scoreLeftCraterNoTeam() {
        craterBeginning(1);
        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER);
        minerals.mCollect.setPower(-1);

        servos.setCollectionCollect(true);
        sleep(20000);

    }

    public void scoreMiddleCraterNoTeam() {

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

        MoveUntilEncoder(12,90 ,1 );

        MoveUntilTime(750,0 ,.6);

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

        while(opModeIsActive() && sensors.vertTouch.getState()){}

        minerals.mExtendVert.setPower(0);
        minerals.mExtendHoriz.setPower(0);

        MoveUntilEncoder(10,180 ,.5 );
        minerals.mCollect.setPower(-1);
        servos.setCollectionCollect(true);

        MoveUntilEncoder(5,0 ,.5 );
        MoveUntilEncoder(10,180 ,.5 );
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
        superSpecialEncoder(14,0 ,.5);

        servos.setDepositDump(true);
        sleep(1200);
        servos.setDepositComingDown();

        minerals.isEncoderModeVert(false);

        minerals.mExtendVert.setPower(.55);

        servos.setCollectionCollect(false);

        extendHorizToEncoder(1100);

        while(opModeIsActive() && sensors.vertTouch.getState()){}

        minerals.mExtendVert.setPower(0);
        minerals.mExtendHoriz.setPower(0);

        minerals.mCollect.setPower(-1);

        servos.setCollectionCollect(true);
        sleep(20000);
    }

    public void scoreRightCraterNoTeam() {
        craterBeginning(3);
        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER);
        minerals.mCollect.setPower(-1);

        servos.setCollectionCollect(true);
        sleep(20000);
    }


    private void craterBeginning(int side) {
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
            sleep(1000);


            TurnFaster(10);
            TurnFaster(-20);
            minerals.mCollect.setPower(0);
            servos.setCollectionCollect(false);

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
            TurnAbsolute(-28);
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

            servos.setCollectionCollect(false);

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
