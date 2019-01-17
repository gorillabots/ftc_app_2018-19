package org.firstinspires.ftc.teamcode;

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
    }


    public void scoreRightCrater() {
        craterBeginning(3);

        maneuverToHalfwayPositionC();

        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER);

        dumpTeamMarker();

        sleep(1000);

        retractHoriz();

        TurnFaster(180);

        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER);
    }

    //SINGLE CRATER -----------------------------------------------------------------------------------------------------------------------










    //DOUBLE CRATER ------------------------------------------------------------------------------------------------------------------------

    public void scoreLeftDouble() {

        TurnFaster(-30);
        TurnAbsolute(28);

        servos.setCollectionCollect(true);
        minerals.mCollect.setPower(-1);
        MoveAndExtend(25, 180,1 ,300 ,4 );
        minerals.mCollect.setPower(0);

        MoveAndRetract(5, 0, .8 , 2);
        TurnFaster(60);
        MoveUntilEncoder(20, 180, 1);
        TurnFaster(45);
        MoveUntilTime(500, 270, .7);
        MoveUntilEncoder(2, 90, .5);

        MoveAndExtend(36, 180, 1 , 700, 6);
        dumpTeamMarker();
        sleep(300);
        MoveAndRetract(10, 180,1 ,2);

        TurnFaster(90);

        servos.setCollectionCollect(true);
        minerals.mCollect.setPower(-1);
        MoveAndExtend(30, 180, 1,300 ,4 );
        minerals.mCollect.setPower(0);

        MoveAndRetract(30, 0,1 ,3);

        TurnFaster(90);

        MoveAndExtend(40,180 ,1 ,600 ,10 );


    }

    public void scoreMiddleDouble() {

        TurnFaster(-22.5);
        hanging.setHangingPower(-.5);
        TurnAbsolute(0);
        hanging.setHangingPower(0);

        servos.setCollectionCollect(true);
        minerals.mCollect.setPower(-1);
        MoveAndExtend(20, 180, 1, 400,4 );
        minerals.mCollect.setPower(0);
        MoveAndRetract(7.5,0 ,1 ,2);

        TurnAbsolute(90);
        MoveUntilEncoder(41, 180, 1);
        TurnFaster(45);
        MoveUntilTime(500, 270, .75);
        MoveUntilEncoder(2, 90, .5);

        MoveAndExtend(26, 180,1 ,600 ,4 );

        dumpTeamMarker();

        sleep(400);

        retractHoriz();

        TurnFaster(90);

        MoveAndExtend(15, 180, 1 , 600 , 4);
        MoveAndRetract(15, 0,1 ,2 );
        TurnFaster(90);
        MoveUntilTime(400, 270, .7);
        MoveUntilTime(100, 90, 1);
        MoveAndExtend(60, 180, 1 , 600 , 10);
    }

    public void scoreRightDouble() {

        TurnAbsolute(-28);

        servos.setCollectionCollect(true);
        minerals.mCollect.setPower(-1);

        MoveAndExtend(20, 180, 1, 450, 4);

        minerals.mCollect.setPower(0);

        MoveAndRetract(8, 0,1 ,2 );

        TurnAbsolute(87);
        MoveUntilEncoder(43, 180, 1);
        TurnFaster(43);

        servos.setCollectionCollect(true);
        minerals.mCollect.setPower(-1);
        MoveAndExtend(18, 180, 1,400 ,4 );
        minerals.mCollect.setPower(0);

        servos.setCollectionCollect(false);

        MoveUntilTime(1200, 270, .7);
        MoveUntilEncoder(2, 90, 1);

        MoveUntilEncoder(20, 180, 1);

        dumpTeamMarker();

        retractHoriz();

        TurnFaster(180);

        MoveAndExtend(40,180 ,1 ,600 ,10 );

    }


//DOUBLE CRATER ------------------------------------------------------------------------------------------


































//CRATER WITHOUT TEAM MARKER --------------------------------------------------------

    public void scoreLeftCraterNoTeam(){
        craterBeginning(1);
    }
    public void scoreMiddleCraterNoTeam(){
        craterBeginning(2);
    }
    public void scoreRightCraterNoTeam(){
        craterBeginning(3);
    }














































































































    private void craterBeginning(int side) {
        if (side == 1) {

            TurnFaster(-30);
            TurnAbsolute(28);

            servos.setCollectionCollect(true); //score and collect mineral
            minerals.mCollect.setPower(-1);
            extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_SIDE_MINERAL);
            sleep(1000);
            minerals.mCollect.setPower(0);

            TurnFaster(10);
            TurnFaster(-20);
            servos.setCollectionCollect(false);

            retractHoriz();
        } else if (side == 2) {

            servos.setCollectionCollect(true); //score and collect mineral
            minerals.mCollect.setPower(-1);
            extendHorizToEncoder(700);
            sleep(1000);
            minerals.mCollect.setPower(0);

            servos.setCollectionCollect(false);

            retractHoriz();

            TurnFaster(-30);
            TurnAbsolute(0);

        } else if (side == 3) {

            TurnAbsolute(-28);

            servos.setCollectionCollect(true); //score and collect mineral
            minerals.mCollect.setPower(-1);
            extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_SIDE_MINERAL);
            sleep(1000);
            minerals.mCollect.setPower(0);

            TurnFaster(10);
            TurnFaster(-20);
            servos.setCollectionCollect(false);

            retractHoriz();

        } else {
            sleep(1);
        }


    }

    private void maneuverToHalfwayPositionC() {

        MoveUntilEncoder(11, 180, 1);
        TurnAbsolute(90);
        MoveUntilEncoder(36, 180, 1);
        TurnFaster(45);
        MoveUntilTime(1500, 270, .6);

    }



}
