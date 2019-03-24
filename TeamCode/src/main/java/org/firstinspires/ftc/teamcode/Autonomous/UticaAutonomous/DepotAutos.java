package org.firstinspires.ftc.teamcode.Autonomous.UticaAutonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousOpMode;

public abstract class DepotAutos extends AutonomousOpMode {

    public void scoreLeftDepot(){
        //exit hook

        minerals.mExtendHoriz.setPower(.3);
        TurnFaster(-20);
        minerals.mExtendHoriz.setPower(0);
        hanging.setHangingPower(-.5);
        TurnAbsolute(25);
        hanging.setHangingPower(0);

        servos.setCollectionLongrange(); //score and collect mineral
        minerals.mCollect.setPower(-1);
        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_SIDE_MINERAL);
        servos.setCollectionCollect(true);
        sleep(1000);

        TurnFaster(10);
        TurnFaster(-20);

        minerals.mCollect.setPower(0);

        servos.setCollectionCollect(false);

        retractHoriz();

        TurnAbsolute(-5);

        MoveAndExtend(13, 180, .8 , 1100 , 5);

        servos.setCollectionCollect(true);

        minerals.mCollect.setPower(1);
        sleep(300);

        dumpTeamMarker();
        servos.setCollectionCollect(false);

        sleep(500);
        minerals.mCollect.setPower(0);
        servos.setTeamMarkerFree(false);

        retractHoriz();

        TurnAbsolute(76);                      //maneuver to crater
        MoveUntilEncoder(36, 180, 1);
        TurnAbsolute(135);

        MoveUntilTime(2000, 270, .5);

        servos.setCollectionCollect(false);

        extendHorizToEncoder(600);

        sleep(10000);

    }

    public void scoreMiddleDepot() {

        minerals.mExtendHoriz.setPower(.3);

        TurnFaster(-30);

        minerals.mExtendHoriz.setPower(0);

        hanging.setHangingPower(-.5);

        TurnAbsolute(-2);

        hanging.setHangingPower(0);

        servos.setCollectionLongrange();
        minerals.mCollect.setPower(-1);

        MoveAndExtend(13.5, 180, .9, 1200 , 5);

        minerals.mCollect.setPower(0);


        minerals.mCollect.setPower(1);
        dumpTeamMarker();
        sleep(1000);
        minerals.mCollect.setPower(0);
        servos.setTeamMarkerFree(false);
        retractHoriz();


        TurnAbsolute(87);

        MoveUntilEncoder(40, 180, 1);
        TurnAbsolute(135);

        MoveUntilTime(2000, 270, .5);

        servos.setCollectionCollect(false);

        extendHorizToEncoder(600);

        sleep(15000);
    }

    public void scoreRightDepot(){

        minerals.mExtendHoriz.setPower(.3);
        TurnAbsolute(-28); //exit hook
        minerals.mExtendHoriz.setPower(0);
        servos.setCollectionLongrange(); //score and collect mineral
        minerals.mCollect.setPower(-1);
        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_SIDE_MINERAL);

        sleep(1000);

        TurnFaster(10);
        TurnFaster(-20);

        minerals.mCollect.setPower(0);
        servos.setCollectionCollect(false);

        retractHoriz();

        TurnAbsolute(-5);

        MoveAndExtend(9, 180, .8 , 1100 , 5);

        minerals.mCollect.setPower(1);
        servos.setCollectionCollect(true);
        sleep(300);

        dumpTeamMarker();

        sleep(500);
        servos.setCollectionCollect(false);
        minerals.mCollect.setPower(0);
        servos.setTeamMarkerFree(false);
        sleep(200);

        retractHoriz();

        TurnAbsolute(75);                      //maneuver to crater
        MoveUntilEncoder(36, 180, 1);
        TurnAbsolute(135);

        MoveUntilTime(2000, 270, .5);

        servos.setCollectionCollect(false);

        extendHorizToEncoder(600);

        sleep(10000);


    }

}
