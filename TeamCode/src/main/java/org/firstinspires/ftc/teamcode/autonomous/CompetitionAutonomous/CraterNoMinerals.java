package org.firstinspires.ftc.teamcode.autonomous.CompetitionAutonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousOpMode;

/**
 * Created by xiax on 4/23/2018.
 */

@Autonomous(name = "Crater: No Minerals", group = "aAutonomous")
public class CraterNoMinerals extends AutonomousOpMode {

    @Override
    public void runOpMode() {

        initializeAutonomous();

        int yellow = detectYellowTensor(); //detect where yellow is

        unhangWithTouch(); //detach from lander

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
        minerals.mExtendHoriz.setPower(.3); //make sure that horizontal slides don't slide out

        TurnFaster(-30); //turn out of hook

        minerals.mExtendHoriz.setPower(0);


        hanging.setHangingPower(-.5); //make hang lower so we don't actually re hang


        TurnAbsolute(28); //turn to mineral

        hanging.setHangingPower(0);

        servos.setCollectionLongrange(); //score and collect mineral
        minerals.mCollect.setPower(1);
        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_SIDE_MINERAL);
        servos.setCollectionCollect(true);
        sleep(1000);

        TurnFaster(10); //account for gyro error
        TurnFaster(-20);
        minerals.mCollect.setPower(0);
        servos.setCollectionCollect(false);

        retractHoriz();//done collecting

        TurnAbsolute(0); //maneuver to team marker scoring position
        MoveUntilEncoder(11, 180, .9);
        TurnAbsolute(84);
        MoveUntilEncoder(36, 180, 1);
        TurnAbsolute(135);

        minerals.mExtendHoriz.setPower(.3); //make sure horizontal is all the way in
        MoveUntilTime(1500, 270, .6);//align with wall
        minerals.mExtendHoriz.setPower(0);

        MoveUntilEncoder(5, 180, .7);//forward for consistency

        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER);

        servos.setCollectionCollect(true); //deposit mineral into depot
        minerals.mCollect.setPower(-1);

        dumpTeamMarker(); //deposit team marker into depot
        sleep(1000);

        servos.setCollectionCollect(false); //get ready to retract
        servos.setTeamMarkerFree(false);
        minerals.mCollect.setPower(0);
        sleep(750);

        retractHoriz(); //retract

        TurnFaster(180); //park into crater

        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER);

        sleep(10000);
    }

    private void scoreCenter() {

        minerals.mExtendHoriz.setPower(.3);//make sure that the horizontal slides don't come out
        sleep(300);
        minerals.mExtendHoriz.setPower(0);

        servos.setCollectionLongrange(); //score and collect mineral
        minerals.mCollect.setPower(1);
        extendHorizToEncoder(700);
        servos.setCollectionCollect(true);
        sleep(1000);
        minerals.mCollect.setPower(0);
        servos.setCollectionCollect(false);

        retractHoriz();

        TurnFaster(-30);//turn out of hook
        hanging.setHangingPower(-.5);//lower hanging so we don't accidentally re hang
        TurnAbsolute(0);
        hanging.setHangingPower(0);

        MoveUntilEncoder(12, 180, .9);//maneuver to depot scoring position
        TurnAbsolute(87);
        MoveUntilEncoder(36, 180, 1);
        TurnAbsolute(135);
        minerals.mExtendHoriz.setPower(.3);
        MoveUntilTime(1500, 270, .6);
        minerals.mExtendHoriz.setPower(0);

        MoveUntilEncoder(5,180 ,.7);//drive forward for stability

        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER);//deposit team marker and mineral into depot

        servos.setCollectionCollect(true);
        minerals.mCollect.setPower(-1);

        dumpTeamMarker();
        sleep(1000);

        servos.setCollectionCollect(false);//prepare retraction
        servos.setTeamMarkerFree(false);
        minerals.mCollect.setPower(0);
        sleep(750);

        retractHoriz();

        TurnFaster(180); //park in crater

        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER);

        sleep(15000);
    }

    private void scoreRight() { //see above
        minerals.mExtendHoriz.setPower(.3);
        TurnAbsolute(-25);
        minerals.mExtendHoriz.setPower(0);
        servos.setCollectionLongrange(); //score and collect mineral
        minerals.mCollect.setPower(1);
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

        TurnAbsolute(0);
        MoveUntilEncoder(10.5, 180, .9);
        TurnAbsolute(84);
        MoveUntilEncoder(36, 180, 1);
        TurnAbsolute(135);
        minerals.mExtendHoriz.setPower(.3);
        MoveUntilTime(1500, 270, .6);
        minerals.mExtendHoriz.setPower(0);

        MoveUntilEncoder(5,180 ,.7 );
        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER);

        servos.setCollectionCollect(true);
        minerals.mCollect.setPower(-1);

        dumpTeamMarker();
        sleep(1000);

        servos.setCollectionCollect(false);
        servos.setTeamMarkerFree(false);
        minerals.mCollect.setPower(0);
        sleep(750);

        retractHoriz();

        TurnFaster(180);

        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER);

        sleep(10000);
    }
}