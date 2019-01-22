package org.firstinspires.ftc.teamcode;

public abstract class DepotAutos extends AutonomousOpMode {

    public void scoreLeftDepot(){
//exit hook
        TurnFaster(-20);
        hanging.setHangingPower(-.5);
        TurnAbsolute(25);
        hanging.setHangingPower(0);

        servos.setCollectionCollect(true); //score and collect mineral
        minerals.mCollect.setPower(-1);
        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_SIDE_MINERAL);
        sleep(1000);


        TurnFaster(10);
        TurnFaster(-20);

        minerals.mCollect.setPower(0);

        servos.setCollectionCollect(false);

        retractHoriz();

        TurnAbsolute(-5);

        MoveAndExtend(13, 180, .8 , 1100 , 5);

        sleep(300);

        dumpTeamMarker();

        sleep(500);

        retractHoriz();

        TurnAbsolute(80);                      //maneuver to crater
        MoveUntilEncoder(36, 180, 1);
        TurnAbsolute(135);

        MoveUntilTime(2000, 270, .5);

        servos.setCollectionCollect(false);

        extendHorizToEncoder(600);

        sleep(10000);

    }

    public void scoreMiddleDepot() {

        TurnFaster(-30);

        hanging.setHangingPower(-.5);

        TurnAbsolute(-2);

        hanging.setHangingPower(0);

        servos.setCollectionCollect(true);
        minerals.mCollect.setPower(-1);

        MoveAndExtend(13, 180, .9, 1100 , 5);

        servos.setCollectionCollect(false);
        minerals.mCollect.setPower(0);

        TurnSuperFast(-2);
        dumpTeamMarker();
        sleep(1000);
        retractHoriz();
        minerals.mCollect.setPower(0);

        TurnAbsolute(87);

        MoveUntilEncoder(40, 180, 1);
        TurnAbsolute(135);

        MoveUntilTime(2000, 270, .5);

        servos.setCollectionCollect(false);

        extendHorizToEncoder(600);

        sleep(15000);
    }

    public void scoreRightDepot(){

        TurnAbsolute(-28); //exit hook

        servos.setCollectionCollect(true); //score and collect mineral
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

        sleep(300);

        dumpTeamMarker();

        sleep(500);

        retractHoriz();

        TurnAbsolute(81);                      //maneuver to crater
        MoveUntilEncoder(36, 180, 1);
        TurnAbsolute(135);

        MoveUntilTime(2000, 270, .5);

        servos.setCollectionCollect(false);

        extendHorizToEncoder(600);

        sleep(10000);


    }

}
