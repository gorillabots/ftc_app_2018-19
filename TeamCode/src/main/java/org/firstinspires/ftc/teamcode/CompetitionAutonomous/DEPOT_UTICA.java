package org.firstinspires.ftc.teamcode.CompetitionAutonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutonomousProgramsOpMode;

/**
 * Created by xiax on 4/23/2018.
 */

@Autonomous(name = "DEPOT UTICA", group = "Autonomous")
public class DEPOT_UTICA extends AutonomousProgramsOpMode {


    @Override
    public void runOpMode() {

        initializeAutonomous();

        int yellow = detectYellowTensor();

        scoreMorePoints(yellow, true,false,false);

    }
}
