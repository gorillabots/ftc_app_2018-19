package org.firstinspires.ftc.teamcode;

public abstract class AutonomousProgramsOpMode extends AutonomousOpMode {

    public void scoreLeftDepotStart() {
        MoveUntilEncoder(3, 270, 1); // exit hook
        TurnAbsolute(45);
    }

    public void scoreRightDepotStart() {
        MoveUntilEncoder(3, 270, 1); // exit hook
        TurnFaster(30);
        TurnAbsolute(-45);
    }

    public void endingOfLeftRightDepot() {
        servos.setCollectionCollect(true); //score and collect mineral
        minerals.mCollect.setPower(-1);
        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_SIDE_MINERAL);
        sleep(300);
        servos.setCollectionCollect(false);
        retractHoriz();
        servos.setBackstopColOpen(true);

        TurnAbsolute(0); //score mineral into the lander - setup
        minerals.mCollect.setPower(0);
        MoveUntilEncoder(5, 180, 1);


        //internal transition
        servos.setBackstopDepOpen(true);
        setVertExtentionUp();
        servos.setDepositDump(true);
        sleep(1000);
        servos.setDepositDump(false);
        sleep(500);
        setVertExtensionDown();

        TurnAbsolute(-90);                      //maneuver to crater
        MoveUntilEncoder(36, 180, 1);
        TurnFaster(45);

        extendHorizToEncoder(555);                                // collect any minerals for teleop

        servos.setCollectionCollect(true);
        minerals.mCollect.setPower(-1);
        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER);

        sleep(3000);
    }

    public void scoreMiddleDepot() {
        servos.setCollectionCollect(true);
        minerals.mCollect.setPower(-1); //collect
        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER);
        servos.setCollectionCollect(false);
        dumpTeamMarker();
        retractHoriz();
        minerals.mCollect.setPower(0);

        MoveUntilEncoder(3, 270, 1); //exit hook
        TurnFaster(40);
        hanging.setHangingPower(.4);
        TurnAbsolute(0);
        hanging.setHangingPower(0);

        servos.setBackstopColOpen(true);
        minerals.mCollect.setPower(-1);
        MoveUntilEncoder(8, 180, .5); //deposit mineral
        minerals.mCollect.setPower(0);

        servos.setBackstopDepOpen(true);
        setVertExtentionUp();
        servos.setDepositDump(true);
        sleep(1000);
        servos.setDepositDump(false);
        sleep(500);
        setVertExtensionDown();

        TurnAbsolute(-90);                      //maneuver to crater
        MoveUntilEncoder(36, 180, 1);
        TurnFaster(45);

        extendHorizToEncoder(555);                                // collect any minerals for teleop

        servos.setCollectionCollect(true);
        minerals.mCollect.setPower(-1);
        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER);

        sleep(3000);

    }


    public void scoreLeftDouble() {

        maneuverToHalfwayPositionDC();

        MoveUntilEncoder(42,180 ,1 );

        dumpTeamMarker();

        TurnFaster(90);

        servos.setCollectionCollect(true);
        minerals.mCollect.setPower(-1);

        extendHorizToEncoder(777);
        sleep(300);

        servos.setCollectionCollect(false);
        minerals.mCollect.setPower(0);

        retractHoriz();

        TurnFaster(90);

        MoveUntilEncoder(42,180 ,1 );

        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER);

    }

    public void scoreMiddleDouble() {

        maneuverToHalfwayPositionDC();

        MoveUntilEncoder(33,180 ,1 );

        dumpTeamMarker();

        TurnFaster(90);

        servos.setCollectionCollect(true);
        minerals.mCollect.setPower(-1);

        extendHorizToEncoder(555);
        sleep(300);

        servos.setCollectionCollect(false);
        minerals.mCollect.setPower(0);

        retractHoriz();

        TurnFaster(90);

        MoveUntilEncoder(33,180 ,1 );

        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER);

    }

    public void scoreRightDouble() {

        maneuverToHalfwayPositionDC();

        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER);
        dumpTeamMarker();
        retractHoriz();
        TurnFaster(45);

        minerals.mCollect.setPower(-1);
        servos.setCollectionCollect(true);

        extendHorizToEncoder(555); //collect other mineral

        sleep(300);

        servos.setCollectionCollect(false);
        minerals.mCollect.setPower(0);
        retractHoriz();

        TurnFaster(135);

        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER);
    }


    public void scoreLeftCrater() {

        minerals.mCollect.setPower(-1);
        servos.setBackstopColOpen(true);

        MoveUntilEncoder(8, 90,.5);

        minerals.mCollect.setPower(0);
        servos.setBackstopDepOpen(true);
        setVertExtentionUp();
        servos.setDepositDump(true);
        sleep(500);
        servos.setDepositDump(false);
        setVertExtensionDown();

       maneuverToHalfwayPositionC();

        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER);
        dumpTeamMarker();
        retractHoriz();

        TurnFaster(180);

        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER);
    }


    public void scoreMiddleCrater() {

        MoveUntilEncoder(8, 90,.5);

        minerals.mCollect.setPower(0);
        servos.setBackstopDepOpen(true);
        setVertExtentionUp();
        servos.setDepositDump(true);
        sleep(500);
        servos.setDepositDump(false);
        setVertExtensionDown();

        maneuverToHalfwayPositionC();

        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER);
        dumpTeamMarker();
        retractHoriz();

        TurnFaster(180);

        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER);
    }


    public void scoreRightCrater() {

        MoveUntilEncoder(8, 90,.5);

        minerals.mCollect.setPower(0);
        servos.setBackstopDepOpen(true);
        setVertExtentionUp();
        servos.setDepositDump(true);
        sleep(500);
        servos.setDepositDump(false);
        setVertExtensionDown();

        maneuverToHalfwayPositionC();

        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER);
        dumpTeamMarker();
        retractHoriz();

        TurnFaster(180);

        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER);
    }


    public void craterBeginning(int side) {
        if (side == 1) {

            MoveUntilEncoder(3, 270, 1); //exit hook
            TurnFaster(36);

            servos.setCollectionCollect(true);
            minerals.mCollect.setPower(-1);

            extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_SIDE_MINERAL);
            sleep(300);
            servos.setCollectionCollect(false);
            retractHoriz();
            minerals.mCollect.setPower(0);

        } else if (side == 2) {

            servos.setCollectionCollect(true);
            minerals.mCollect.setPower(-1);

            extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_MID_MINERAL);
            sleep(300);
            servos.setCollectionCollect(false);
            retractHoriz();
            minerals.mCollect.setPower(0);

            MoveUntilEncoder(3, 270, 1);
            TurnFaster(30);
            TurnAbsolute(0);

        } else if (side == 3) {

            MoveUntilEncoder(3, 270, 1); //exit hook
            TurnFaster(30);
            TurnAbsolute(-40);

            servos.setCollectionCollect(true);
            minerals.mCollect.setPower(-1);

            extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_SIDE_MINERAL);
            sleep(300);
            servos.setCollectionCollect(false);
            retractHoriz();
            minerals.mCollect.setPower(0);

        } else {
        }


    }

    public void maneuverToHalfwayPositionDC() {

        TurnAbsolute(0);
        MoveUntilEncoder(5, 180, 1);
        TurnAbsolute(-80);
        MoveUntilEncoder(48, 180, 1);
        TurnFaster(45);

        MoveUntilTime(1500, 270, .6);

        MoveUntilEncoder(3, 90, 1);
    }
    public void maneuverToHalfwayPositionC(){

        MoveUntilEncoder(5,180 ,1 );
        TurnFaster(-80);
        MoveUntilEncoder(30, 180, 1);
        TurnFaster(45);
        MoveUntilTime(1500, 270, .6);
        MoveUntilEncoder(3, 90, 1);





    }

}
