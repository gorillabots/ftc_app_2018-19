package org.firstinspires.ftc.teamcode.autonomous.CompetitionAutonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousOpMode;

import static org.firstinspires.ftc.teamcode.teleop.TeleOpOpMode.ENCODER_TO_DEPOSITUP;

/**
 * Created by xiax on 4/23/2018.
 */

@Autonomous(name = "Depot: One Mineral", group = "aAutonomous")
public class DepotOneMineral extends AutonomousOpMode {

    @Override
    public void runOpMode() {

        initializeAutonomous();

        int yellow = detectYellowTensor();

        unhangWithTouch();

        switch (yellow) {
            case 1:
                scoreLeft();
            case 2:
                scoreCenter();
            case 3:
                scoreRight();
        }


        sleep(10000);
    }

    private void scoreLeft() {

        minerals.mExtendHoriz.setPower(.3);
        TurnFaster(-20);
        minerals.mExtendHoriz.setPower(0);
        hanging.setHangingPower(-.5);
        TurnAbsolute(25);
        hanging.setHangingPower(0);

        servos.setCollectionLongrange(); //score and collect mineral
        minerals.mCollect.setPower(1);
        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_SIDE_MINERAL);
        servos.setCollectionCollect(true);
        sleep(1000);

        TurnFaster(10);
        TurnFaster(-20);

        minerals.mCollect.setPower(0);

        servos.setCollectionCollect(false);

        retractHoriz();

        servos.setDepositDump(false);
        servos.setCollectionCollect(false);

        minerals.mCollect.setPower(1);

        servos.setBackstopColOpen(true);
        sleep(1500);

        minerals.mCollect.setPower(0);

        TurnAbsolute(-5);

        MoveAndExtend(13, 180, .8 , 1100 , 5);

        dumpTeamMarker();
        servos.setCollectionCollect(false);

        sleep(500);
        minerals.mCollect.setPower(0);
        servos.setTeamMarkerFree(false);

        servos.setCollectionCollect(false);
        servos.setDepositDump(false);

        minerals.isEncoderModeHoriz(false);

        while (opModeIsActive() && sensors.horizSlowTouch.getState()) {
            minerals.mExtendHoriz.setPower(1);
        }
        minerals.mExtendHoriz.setPower(.2);
        sleep(200);
        while (opModeIsActive() && sensors.horizTouch.getState()) {
            minerals.mExtendHoriz.setPower(.5);
        }

        minerals.mExtendVert.setPower(0);

        servos.setDepositDump(false);
        servos.setCollectionCollect(false);

        minerals.mExtendVert.setPower(0);
        servos.setDepositComingDown();
        servos.setBackstopDepOpen(false);

        minerals.isEncoderModeVert(true);

        int start = minerals.mExtendVert.getCurrentPosition();
        int end = start - ENCODER_TO_DEPOSITUP;

        minerals.mExtendVert.setPower(-1);
        minerals.mExtendVert.setTargetPosition(end);

        timer.reset();
        timer.startTime();

        while (opModeIsActive() && minerals.mExtendVert.isBusy() && timer.seconds() < 3){}

        servos.setDepositHalfway();
        minerals.mCollect.setPower(0);

        MoveUntilEncoder(14, 0, .7);
        TurnAbsolute(20);

        servos.setDepositDump(true);

        sleep(1200);

        servos.setDepositComingDown();
        servos.setBackstopColOpen(false);
        servos.setCollectionCollect(false);

        minerals.mExtendHoriz.setPower(0);

        minerals.isEncoderModeVert(true);

        int startSecond = minerals.mExtendVert.getCurrentPosition();
        int endSecond = startSecond + ENCODER_TO_DEPOSITUP;

        minerals.mExtendVert.setTargetPosition(endSecond);

        timer.reset();
        timer.startTime();

        while (opModeIsActive() && sensors.vertTouch.getState() && minerals.mExtendVert.isBusy() && timer.seconds() < 2.5){}
        minerals.mExtendVert.setPower(0);

        MoveUntilEncoder(13.5,180 ,1);

        TurnAbsolute(76);                      //maneuver to crater
        MoveUntilEncoder(36, 180, 1);
        TurnAbsolute(135);

        MoveUntilTime(2000, 270, .5);

        servos.setCollectionCollect(false);

        extendHorizToEncoder(600);

        sleep(10000);
    }

    private void scoreCenter() {

        minerals.mExtendHoriz.setPower(.3);

        TurnFaster(-30);

        minerals.mExtendHoriz.setPower(0);

        hanging.setHangingPower(-.5);

        TurnAbsolute(-2);

        hanging.setHangingPower(0);

        servos.setCollectionLongrange();
        minerals.mCollect.setPower(1);

        MoveAndExtend(13.5, 180, .9, 1200, 5);

        minerals.mCollect.setPower(0);

        dumpTeamMarker();
        sleep(500);
        minerals.mCollect.setPower(0);
        servos.setTeamMarkerFree(false);

        minerals.mExtendHoriz.setPower(0);
        minerals.mExtendVert.setPower(0);
        minerals.mCollect.setPower(.4);

        servos.setCollectionComingIn();
        servos.setDepositDump(false);

        minerals.isEncoderModeHoriz(false);

        while (opModeIsActive() && sensors.horizSlowTouch.getState()) {
            minerals.mExtendHoriz.setPower(1);
        }
        minerals.mExtendHoriz.setPower(.2);
        sleep(200);
        while (opModeIsActive() && sensors.horizTouch.getState()) {
            minerals.mExtendHoriz.setPower(.5);
        }

        minerals.mExtendVert.setPower(0);

        servos.setDepositDump(false);
        servos.setCollectionCollect(false);

        minerals.mCollect.setPower(1);

        servos.setBackstopColOpen(true);
        sleep(1500);

        minerals.mExtendVert.setPower(0);
        servos.setDepositComingDown();
        servos.setBackstopDepOpen(false);

        minerals.isEncoderModeVert(true);

        int start = minerals.mExtendVert.getCurrentPosition();
        int end = start - ENCODER_TO_DEPOSITUP;

        minerals.mExtendVert.setPower(-1);
        minerals.mExtendVert.setTargetPosition(end);

        timer.reset();
        timer.startTime();

        while (opModeIsActive() && minerals.mExtendVert.isBusy() && timer.seconds() < 3){}

        servos.setDepositHalfway();
        minerals.mCollect.setPower(0);

        MoveUntilEncoder(14, 0, .7);

        servos.setDepositDump(true);

        sleep(1200);

        servos.setDepositComingDown();
        servos.setBackstopColOpen(false);
        servos.setCollectionCollect(false);

        minerals.mExtendHoriz.setPower(0);

        minerals.isEncoderModeVert(true);

        int startSecond = minerals.mExtendVert.getCurrentPosition();
        int endSecond = startSecond + ENCODER_TO_DEPOSITUP;

        minerals.mExtendVert.setTargetPosition(endSecond);

        timer.reset();
        timer.startTime();

        while (opModeIsActive() && sensors.vertTouch.getState() && minerals.mExtendVert.isBusy() && timer.seconds() < 2.5){}
        minerals.mExtendVert.setPower(0);

        MoveUntilEncoder(13,180 ,1);

        TurnAbsolute(87);

        MoveUntilEncoder(40, 180, 1);
        TurnAbsolute(135);

        MoveUntilTime(2000, 270, .5);

        servos.setCollectionCollect(false);

        extendHorizToEncoder(600);

        sleep(20000);


    }

    private void scoreRight() {

        minerals.mExtendHoriz.setPower(.3);
        TurnAbsolute(-27); //exit hook
        minerals.mExtendHoriz.setPower(0);
        servos.setCollectionLongrange(); //score and collect mineral
        minerals.mCollect.setPower(1);
        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_SIDE_MINERAL);

        sleep(1000);

        TurnFaster(10);
        TurnFaster(-20);

        minerals.mCollect.setPower(0);
        servos.setCollectionCollect(false);

        retractHoriz();

        servos.setDepositDump(false);
        servos.setCollectionCollect(false);

        minerals.mCollect.setPower(1);

        servos.setBackstopColOpen(true);
        sleep(1500);

        minerals.mCollect.setPower(0);

        TurnAbsolute(-5);

        MoveAndExtend(9, 180, .8 , 1100 , 5);

        dumpTeamMarker();

        sleep(500);
        servos.setCollectionCollect(false);
        minerals.mCollect.setPower(0);
        servos.setTeamMarkerFree(false);
        sleep(200);

        minerals.mExtendHoriz.setPower(0);
        minerals.mExtendVert.setPower(0);
        minerals.mCollect.setPower(.4);

        servos.setCollectionCollect(false);
        servos.setDepositDump(false);

        minerals.isEncoderModeHoriz(false);

        while (opModeIsActive() && sensors.horizSlowTouch.getState()) {
            minerals.mExtendHoriz.setPower(1);
        }
        minerals.mExtendHoriz.setPower(.2);
        sleep(200);
        while (opModeIsActive() && sensors.horizTouch.getState()) {
            minerals.mExtendHoriz.setPower(.5);
        }

        minerals.mExtendVert.setPower(0);


        minerals.mExtendVert.setPower(0);
        servos.setDepositComingDown();
        servos.setBackstopDepOpen(false);

        minerals.isEncoderModeVert(true);

        int start = minerals.mExtendVert.getCurrentPosition();
        int end = start - ENCODER_TO_DEPOSITUP;

        minerals.mExtendVert.setPower(-1);
        minerals.mExtendVert.setTargetPosition(end);

        timer.reset();
        timer.startTime();

        while (opModeIsActive() && minerals.mExtendVert.isBusy() && timer.seconds() < 3){}

        servos.setDepositHalfway();
        minerals.mCollect.setPower(0);

        MoveUntilEncoder(14, 0, .7);
        TurnAbsolute(10);

        servos.setDepositDump(true);

        sleep(1200);
        TurnAbsolute(-20);

        servos.setDepositComingDown();
        servos.setBackstopColOpen(false);
        servos.setCollectionCollect(false);

        minerals.mExtendHoriz.setPower(0);

        minerals.isEncoderModeVert(true);

        int startSecond = minerals.mExtendVert.getCurrentPosition();
        int endSecond = startSecond + ENCODER_TO_DEPOSITUP;

        minerals.mExtendVert.setTargetPosition(endSecond);

        timer.reset();
        timer.startTime();

        while (opModeIsActive() && sensors.vertTouch.getState() && minerals.mExtendVert.isBusy() && timer.seconds() < 2.5){}
        minerals.mExtendVert.setPower(0);

        MoveUntilEncoder(13,180 ,1);

        TurnAbsolute(75);                      //maneuver to crater
        MoveUntilEncoder(36, 180, 1);
        TurnAbsolute(135);

        MoveUntilTime(2000, 270, .5);

        servos.setCollectionCollect(false);

        extendHorizToEncoder(600);

        sleep(20000);
    }
}
