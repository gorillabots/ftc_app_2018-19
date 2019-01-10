package org.firstinspires.ftc.teamcode;

import static java.lang.Math.abs;

public abstract class AutonomousProgramsOpMode extends AutonomousOpMode {


    /*
        //----DEPOT

        public void scoreLeftDepot() {
            MoveUntilEncoder(3, 270, 1);
            Turn(40);
            MoveUntilEncoder(36, 180, 1);
            TurnFaster(-90);
            dumpTeamMarker();
            MoveUntilTime(1200, 90, 1);
            MoveUntilEncoder(3, 270, .5);
            MoveUntilEncoder(30, 180, 1);
            driveToCraterFromDepot();
        }

        public void scoreMiddleDepot() {
            MoveUntilEncoder(3, 270, 1);
            Turn(40);
            hanging.setHangingPower(.2);
            Turn(-35);
            hanging.setHangingPower(0);
            dumpTeamMarker();
            MoveUntilEncoder(55, 180, 1);
            Turn(-45);
            MoveUntilTime(1000, 90, 1);
            driveToCraterFromDepot();
        }

        public void scoreRightDepot() {
            MoveUntilEncoder(3, 270, 1);
            TurnFaster(40);
            TurnAbsolute(-40);
            MoveUntilEncoder(36, 180, 1);
            TurnFaster(90);
            dumpTeamMarker();
            MoveUntilTime(750,270 ,.6 );
            MoveUntilEncoder(24, 180, 1);

            TurnFaster(-90);
            MoveUntilTime(1000, 90, 1);
            driveToCraterFromDepot();
        }

        public void driveToCraterFromDepot() {
            MoveUntilEncoder(60, 4, 1);
            MoveUntilTime(500, 90, .6);
            MoveUntilTime(100, 270, .5);
            MoveUntilEncoder(27, 0, 1);
        }

        //----DEPOT

        //----CRATER

        public void scoreLeftCrater() {
            MoveUntilEncoder(3, 270, 1);
            TurnFaster(45);
            TurnFaster(-10);
            MoveUntilEncoder(30, 180, .5);
            TurnFaster(60);
            MoveUntilEncoder(20, 180, 1);
            TurnFaster(45);
            MoveUntilTime(1000, 270, .75);
            MoveUntilEncoder(2, 90, .5);
            sleep(1); //wait for other team
            dumpTeamMarker();
            MoveUntilEncoder(70, 184, 1);
            MoveUntilEncoder(85, 358, 1);
        }

        public void scoreMiddleCrater() {
            MoveUntilEncoder(3, 270, 1);
            TurnFaster(45);
            hanging.setHangingPower(.2);
            TurnAbsolute(0);
            hanging.setHangingPower(0);
            MoveUntilEncoder(23.5, 180, .6);
            MoveUntilEncoder(11, 0, .6);
            TurnAbsolute(88);
            MoveUntilEncoder(41, 180, 1);
            TurnFaster(45);
            MoveUntilTime(1000, 270, .7);
            MoveUntilEncoder(2, 90, .5);
            sleep(1); //wait for other team
            dumpTeamMarker();
            MoveUntilEncoder(50, 184, 1);
            MoveUntilEncoder(75, 358, 1);
        }

        public void scoreRightCrater() {
            MoveUntilEncoder(3, 270, 1);
            TurnFaster(45);
            hanging.setHangingPower(.2);
            TurnAbsolute(-40);
            MoveUntilEncoder(30, 180, .6);
            MoveUntilEncoder(13, 0, .6);
            TurnAbsolute(87);
            MoveUntilEncoder(41, 180, 1);
            TurnFaster(45);
            MoveUntilTime(1000, 270, .7);
            MoveUntilEncoder(2, 90, .5);
            sleep(1); //wait for other team
            dumpTeamMarker();
            MoveUntilEncoder(50, 184, 1);

            MoveUntilEncoder(80, 0, 1);
        }

        //----CRATER

        //----DOUBLE
        public void scoreLeftDouble() { //success
            MoveUntilEncoder(3, 270, 1);
            TurnFaster(36);
            MoveUntilEncoder(28, 180, .5);
            MoveUntilEncoder(5,0 ,.8);
            TurnFaster(60);
            MoveUntilEncoder(20, 180, 1);
            TurnFaster(45);
            MoveUntilTime(750, 270, .7);
            MoveUntilEncoder(2, 90, .5);
            sleep(1); //wait for other team
            MoveUntilEncoder(49, 180, 1);
            dumpTeamMarker();
            MoveUntilTime(1000, 180, .7);
            MoveUntilEncoder(5, 0, 1);
            TurnFaster(-90);
            MoveUntilEncoder(5, 0, 1);
            MoveUntilEncoder(30, 0, 1);
            MoveUntilEncoder(9.5, 270, 1);
            MoveUntilTime(900, 90, .7);
            MoveUntilTime(100, 270, 1);
            MoveUntilEncoder(40, 4, 1);

        }

        public void scoreMiddleDouble() { //success
            MoveUntilEncoder(3, 270, 1);
            TurnFaster(22.5);
            hanging.setHangingPower(.2);
            TurnAbsolute(0);
            hanging.setHangingPower(0);
            MoveUntilEncoder(23.5, 180, 1);
            MoveUntilEncoder(11, 0, .9);
            TurnAbsolute(87);
            MoveUntilEncoder(41, 180, 1);
            TurnFaster(45);
            MoveUntilTime(500, 270, 1);
            MoveUntilEncoder(2, 90, .5);
            MoveUntilEncoder(46, 184, 1);
            dumpTeamMarker();
            MoveUntilTime(1000, 180, .6);
            MoveUntilEncoder(20, 0, 1);
            TurnFaster(90);
            MoveUntilEncoder(20, 180, 1);
            MoveUntilTime(1000, 0, 1);
            MoveUntilTime(400, 0, .5);
            TurnFaster(-90);
            MoveUntilTime(400, 270, .7);
            MoveUntilTime(100, 90, 1);
            MoveUntilEncoder(80, 3, 1);
        }

        public void scoreRightDouble() {

            MoveUntilEncoder(3, 270, 1);
            TurnFaster(20);
            hanging.setHangingPower(.2);
            TurnAbsolute(-40);
            hanging.setHangingPower(0);
            MoveUntilEncoder(30, 180, 1);
            MoveUntilEncoder(12, 0, 1);
            TurnAbsolute(87);
            MoveUntilEncoder(43, 180, 1);
            TurnFaster(43);
            sleep(1); //wait for other team
            MoveUntilEncoder(24, 180, 1);
            MoveUntilTime(1200, 270, .7);
            dumpTeamMarker();
            MoveUntilEncoder(2, 90, 1);
            MoveUntilEncoder(28, 180, 1);
            sleep(1500);
            MoveUntilEncoder(81, 0, 1);

        }
        //----DOUBLE

  */
    public void scorePoints(int yellow, boolean isDepot, boolean isDouble) {
        if (yellow == 1) {
            if (isDepot) {
                scoreLeftDepot();
            } else if (isDouble) {
                scoreLeftDouble();
            } else {
                scoreLeftCrater();
            }
        } else if (yellow == 2) {
            if (isDepot) {
                scoreMiddleDepot();
            } else if (isDouble) {
                scoreMiddleDouble();
            } else {
                scoreMiddleCrater();
            }
        } else {
            if (isDepot) {
                scoreRightDepot();
            } else if (isDouble) {
                scoreRightDouble();
            } else {
                scoreRightCrater();
            }
        }
    }

    public void scoreMorePoints(int yellow, boolean isDepot, boolean isDouble, boolean withoutTeamMarker) {
        if (yellow == 1) {
            if (isDepot) {
                scoreLeftDepot();
            } else if (isDouble) {
                scoreLeftDouble();
            } else if (withoutTeamMarker) {
                scoreLeftCraterWithoutTeam();
            } else {
                scoreLeftCrater();
            }

        } else if (yellow == 2) {
            if (isDepot) {
                scoreMiddleDepot();
            } else if (isDouble) {
                scoreMiddleDouble();
            } else if (withoutTeamMarker) {
                scoreMiddleCraterWithoutTeam();
            } else {
                scoreMiddleCrater();
            }
        } else {
            if (isDepot) {
                scoreRightDepot();
            } else if (isDouble) {
                scoreRightDouble();
            } else if (withoutTeamMarker) {
                scoreRightCraterWithoutTeam();
            } else {
                scoreRightCrater();
            }
        }
    }


    public void scoreLeftDepot() {
        unhangWithExtension(); //unhang / deposit team marker

        MoveUntilEncoder(3, 270, 1); // exit hook
        TurnAbsolute(45);

        endingOfLeftRightDepot();

    }

    public void scoreRightDepot() {
        unhangWithExtension(); //unhang / deposit team marker

        MoveUntilEncoder(3, 270, 1); // exit hook
        TurnFaster(30);
        TurnAbsolute(-45);

        endingOfLeftRightDepot();
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
        unHangWithEncoder();                                //score and collect mineral / unhang / deposit team marker
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

        craterBeginning(1);

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

        craterBeginning(2);

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
        craterBeginning(3);

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
        craterBeginning(1);

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
        craterBeginning(2);

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
        craterBeginning(3);

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


    public void scoreLeftCraterWithoutTeam() {
        craterBeginning(1);
    }


    public void scoreMiddleCraterWithoutTeam() {
        craterBeginning(2);
    }


    public void scoreRightCraterWithoutTeam() {
        craterBeginning(3);
    }


    public void craterBeginning(int side) {
        unHangWithEncoder();


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