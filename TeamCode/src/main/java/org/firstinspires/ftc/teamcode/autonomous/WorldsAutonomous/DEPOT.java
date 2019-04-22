package org.firstinspires.ftc.teamcode.autonomous.WorldsAutonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousOpMode;

import static org.firstinspires.ftc.teamcode.teleop.TeleOpOpMode.ENCODER_TO_DEPOSITUP;

/**
 * Created by xiax on 4/23/2018.
 */

@Autonomous(name = "DEPOT", group = "aAutonomous")
public class DEPOT extends AutonomousOpMode {

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
        sleep(500);

        TurnFaster(10);
        TurnFaster(-20);

        minerals.mCollect.setPower(0);

        servos.setCollectionCollect(false);

        retractHoriz();

        servos.setDepositDump(false);
        servos.setCollectionCollect(false);

        minerals.mCollect.setPower(1);

        servos.setBackstopColOpen(true);
        sleep(1000);

        minerals.mCollect.setPower(0);

        TurnAbsolute(-5);

        MoveAndExtend(13, 180, .8 , 1100 , 5);

        dumpTeamMarker();
        servos.setCollectionCollect(false);

        sleep(700);
        minerals.mCollect.setPower(0);
        servos.setTeamMarkerFree(false);

        servos.setCollectionCollect(false);
        servos.setDepositDump(false);

        minerals.isEncoderModeHoriz(false);

        timer.reset();
        timer.startTime();

        while (opModeIsActive() && sensors.horizSlowTouch.getState()) {
            minerals.mExtendHoriz.setPower(1);
        }

        while (opModeIsActive() && sensors.horizTouch.getState() && timer.seconds() < 1.5) {
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

        sleep(1000);

        servos.setDepositComingDown();
        servos.setBackstopColOpen(false);
        servos.setCollectionCollect(false);

        minerals.mExtendHoriz.setPower(0);

        minerals.isEncoderModeVert(true);
        //
        minerals.mExtendVert.setPower(.5); minerals.mExtendVert.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        int startSecond = minerals.mExtendVert.getCurrentPosition();
        int endSecond = startSecond + ENCODER_TO_DEPOSITUP;

        minerals.mExtendVert.setTargetPosition(endSecond);

        timer.reset();
        timer.startTime();

        while (opModeIsActive() && sensors.vertTouch.getState() && minerals.mExtendVert.isBusy() && timer.seconds() < 2.5){}
        minerals.mExtendVert.setPower(0);

        MoveUntilEncoder(13.5,180 ,1);

        TurnAbsolute(76);                      //maneuver to crater
        MoveUntilEncoder(29, 180, 1);
        TurnAbsolute(135);

        MoveUntilTime(1000, 270, .5);

        servos.setCollectionCollect(false);

        MoveUntilEncoder(5,180 ,1 );

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

        timer.reset();
        timer.startTime();

        while (opModeIsActive() && sensors.horizSlowTouch.getState()) {
            minerals.mExtendHoriz.setPower(1);
        }

        while (opModeIsActive() && sensors.horizTouch.getState() && timer.seconds() < 1.5) {
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

        TurnFaster(10);
        servos.setDepositDump(true);

        sleep(300);

        TurnAbsolute(0);

        servos.setDepositComingDown();
        servos.setBackstopColOpen(false);
        servos.setCollectionCollect(false);

        minerals.mExtendHoriz.setPower(0);

        minerals.isEncoderModeVert(true);
        minerals.mExtendVert.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        minerals.mExtendVert.setPower(.5);//

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

        hanging.setHangingPower(-.5);
        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_SIDE_MINERAL);
        hanging.setHangingPower(0);
        sleep(500);

        TurnFaster(10);
        TurnFaster(-20);

        minerals.mCollect.setPower(0);
        servos.setCollectionCollect(false);

        retractHoriz();

        servos.setDepositDump(false);
        servos.setCollectionCollect(false);

        minerals.mCollect.setPower(1);

        servos.setBackstopColOpen(true);
        sleep(1000);

        minerals.mCollect.setPower(0);

        TurnAbsolute(-5);

        MoveAndExtend(9, 180, .8 , 1100 , 5);

        dumpTeamMarker();

        sleep(600);

        servos.setCollectionCollect(false);
        minerals.mCollect.setPower(0);
        servos.setTeamMarkerFree(false);


        minerals.mExtendHoriz.setPower(0);
        minerals.mExtendVert.setPower(0);
        minerals.mCollect.setPower(.4);

        servos.setCollectionCollect(false);
        servos.setDepositDump(false);

        minerals.isEncoderModeHoriz(false);

        timer.reset();
        timer.startTime();

        while (opModeIsActive() && sensors.horizSlowTouch.getState()) {
            minerals.mExtendHoriz.setPower(1);
        }

        while (opModeIsActive() && sensors.horizTouch.getState() && timer.seconds() < 1.5) {
            minerals.mExtendHoriz.setPower(.5);
        }

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

        sleep(750);
        TurnAbsolute(-30);

        servos.setDepositComingDown();
        servos.setBackstopColOpen(false);
        servos.setCollectionCollect(false);

        minerals.mExtendHoriz.setPower(0);

        minerals.isEncoderModeVert(true);//
        minerals.mExtendVert.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        minerals.mExtendVert.setPower(.5);

        int startSecond = minerals.mExtendVert.getCurrentPosition();
        int endSecond = startSecond + ENCODER_TO_DEPOSITUP;

        minerals.mExtendVert.setTargetPosition(endSecond);

        timer.reset();
        timer.startTime();

        while (opModeIsActive() && sensors.vertTouch.getState() && minerals.mExtendVert.isBusy() && timer.seconds() < 2.5){}
        minerals.mExtendVert.setPower(0);

        MoveUntilEncoder(13,180 ,1);

        TurnAbsolute(87);                      //maneuver to crater
        MoveUntilEncoder(38, 180, 1);
        TurnAbsolute(135);

        MoveUntilTime(900, 270, .5);

        servos.setCollectionCollect(false);

        extendHorizToEncoder(600);

        sleep(20000);
    }
}
