package org.firstinspires.ftc.teamcode.TestAutonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutonomousOpMode;

/**
 * Created by xiax on 4/23/2018.
 */

@Autonomous(name = "Blue Depot2", group = "Autonomous")
public class BlueDepot2 extends AutonomousOpMode {

    @Override
    public void runOpMode() {

        initializeAutonomous();

        int yellow = 2;

        waitForStart();

        unHangWithEncoder();

        servos.setCollectionCollect(true);
        minerals.mCollect.setPower(-1); //collect
        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER);
        servos.setCollectionCollect(false);
        minerals.mCollect.setPower(0);
        dumpTeamMarker();
        sleep(1000);
        retractHoriz();
        minerals.mCollect.setPower(0);

        TurnFaster(-30);

        TurnAbsolute(-10);

        MoveUntilEncoder(13,180 ,1 );

        TurnFaster(80);
         //maneuver to crater
        MoveUntilEncoder(40, 180, 1);
        TurnFaster(45);

        extendHorizToEncoder(600);
    }
}
