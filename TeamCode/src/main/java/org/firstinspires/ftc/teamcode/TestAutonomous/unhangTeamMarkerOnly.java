package org.firstinspires.ftc.teamcode.TestAutonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutonomousProgramsOpMode;

/**
 * Created by xiax on 4/23/2018.
 */

@Autonomous(name = "unhangwithteam", group = "Autonomous")
public class unhangTeamMarkerOnly extends AutonomousProgramsOpMode {

    @Override
    public void runOpMode() {

        initializeAutonomous();

        waitForStart();

        unHangWithEncoder();

        TurnFaster(-30);

        TurnAbsolute(-10);
                              //score and collect mineral / unhang / deposit team marker
        servos.setCollectionCollect(true);
        minerals.mCollect.setPower(-1); //collect
        extendHorizToEncoder(ENCODER_TO_EXTEND_HORIZ_TEAM_MARKER);
        servos.setCollectionCollect(false);
        minerals.mCollect.setPower(0);
        dumpTeamMarker();
        sleep(1000);
        retractHoriz();
        minerals.mCollect.setPower(0);
    }
}
