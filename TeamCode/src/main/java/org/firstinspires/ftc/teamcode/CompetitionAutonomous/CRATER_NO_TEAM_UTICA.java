package org.firstinspires.ftc.teamcode.CompetitionAutonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutonomousProgramsOpMode;

/**
 * Created by xiax on 4/23/2018.
 */

@Autonomous(name = "CRATER UTICA WITHOUT TEAM", group = "Autonomous")
public class CRATER_NO_TEAM_UTICA extends AutonomousProgramsOpMode {

    @Override
    public void runOpMode() {

        initializeAutonomous();

        int yellow = detectYellowTensor();

        unHangWithEncoder();

        craterBeginning(yellow);
    }
}
