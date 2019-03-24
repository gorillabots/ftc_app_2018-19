package org.firstinspires.ftc.teamcode.Autonomous;

import static org.firstinspires.ftc.teamcode.Teleop.TeleOpOpMode.ENCODER_TO_DEPOSITUP;
import static org.firstinspires.ftc.teamcode.subsystems.Servos.COLLECTION_INIT;
import static org.firstinspires.ftc.teamcode.subsystems.Servos.DEPOSIT_INIT;

public abstract class DepotAutosWithMineral extends AutonomousOpMode {

    public void scoreLeftDepotMin(){
        //exit hook

        minerals.mExtendHoriz.setPower(.3);
        TurnFaster(-20);
        minerals.mExtendHoriz.setPower(0);
        hanging.setHangingPower(-.5);
        TurnAbsolute(25);
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

        retractHoriz();

        TurnAbsolute(-5);

        MoveAndExtend(13, 180, .8 , 1100 , 5);



        dumpTeamMarker();
        servos.setCollectionCollect(false);

        sleep(500);
        minerals.mCollect.setPower(0);

        retractHoriz();

        scoreMineral(13);

        TurnAbsolute(80);                      //maneuver to crater
        MoveUntilEncoder(36, 180, 1);
        TurnAbsolute(135);

        MoveUntilTime(2000, 270, .5);

        servos.setCollectionCollect(false);

        extendHorizToEncoder(600);

        minerals.mCollect.setPower(-1);

        servos.setCollectionCollect(true);
        sleep(10000);

    }

    public void scoreMiddleDepotMin() {

        minerals.mExtendHoriz.setPower(.3);

        TurnFaster(-30);

        minerals.mExtendHoriz.setPower(0);

        hanging.setHangingPower(-.5);

        TurnAbsolute(-2);

        hanging.setHangingPower(0);

        servos.setCollectionAlmostCollect();
        minerals.mCollect.setPower(-1);

        MoveAndExtend(13.5, 180, .9, 1200 , 5);

        minerals.mCollect.setPower(0);

        dumpTeamMarker();
        sleep(1000);
        minerals.mCollect.setPower(0);

        servos.sCollectionRot.setPosition(COLLECTION_INIT);
        servos.sDepositRot.setPosition(DEPOSIT_INIT);

        retractHoriz();

        scoreMineral(13);

        TurnAbsolute(87);

        MoveUntilEncoder(40, 180, 1);
        TurnAbsolute(135);

        MoveUntilTime(2000, 270, .5);

        servos.setCollectionCollect(false);

        extendHorizToEncoder(600);

        minerals.mCollect.setPower(-1);

        servos.setCollectionCollect(true);

        sleep(15000);
    }

    public void scoreRightDepotMin() {

        minerals.mExtendHoriz.setPower(.3);
        TurnAbsolute(-28); //exit hook
        minerals.mExtendHoriz.setPower(0);
        servos.setCollectionAlmostCollect(); //score and collect mineral
        minerals.mCollect.setPower(-1);
        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_SIDE_MINERAL);

        sleep(500);

        TurnFaster(10);
        TurnFaster(-20);

        minerals.mCollect.setPower(0);
        servos.setCollectionCollect(false);

        retractHoriz();

        TurnAbsolute(-5);

        MoveAndExtend(9, 180, .8, 1100, 5);

        dumpTeamMarker();

        sleep(500);
        minerals.mCollect.setPower(0);

        retractHoriz();

        scoreMineral(10);

        TurnAbsolute(75);                      //maneuver to crater
        MoveUntilEncoder(36, 180, 1);
        TurnAbsolute(135);

        MoveUntilTime(2000, 270, .5);

        servos.setCollectionCollect(false);

        extendHorizToEncoder(600);

        minerals.mCollect.setPower(-1);

        servos.setCollectionCollect(true);

        sleep(10000);

    }

    public void scoreMineral(double distance){
        servos.setDepositDump(false);
        servos.setCollectionCollect(false);

        servos.setBackstopColOpen(true);
        minerals.mCollect.setPower(-1);
        minerals.mExtendHoriz.setPower(.05);
        MoveUntilEncoder(distance,0 ,.5);

        minerals.mCollect.setPower(0);
        servos.setDepositComingDown();

        minerals.mExtendHoriz.setPower(-.15);

        sleep(500);
        minerals.isEncoderModeVert(true);

        int start = minerals.mExtendVert.getCurrentPosition();
        int end = start - ENCODER_TO_DEPOSITUP;

        minerals.mExtendVert.setPower(-1);

        minerals.mExtendVert.setTargetPosition(end);
        minerals.mExtendHoriz.setPower(0);

        while(opModeIsActive() && minerals.mExtendVert.isBusy()){

        }

        servos.setDepositDump(true);
        sleep(1000);

        servos.setDepositComingDown();

        minerals.isEncoderModeVert(false);

        minerals.mExtendVert.setPower(.55);

        servos.setCollectionCollect(false);

        MoveUntilEncoder(13.5,180 ,1 );

        timer.reset();
        timer.startTime();

        while(opModeIsActive() && sensors.vertTouch.getState() && timer.seconds() < 3){}
    }

    }
