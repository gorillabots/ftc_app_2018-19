package org.firstinspires.ftc.teamcode.CompetitionAutonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutonomousProgramsOpMode;

/**
 * Created by xiax on 4/23/2018.
 */

@Autonomous(name = "DOUBLE CRATER UTICA", group = "Autonomous")
public class DOUBLECRATER_UTICA extends AutonomousProgramsOpMode {


    @Override
    public void runOpMode() {

        initializeAutonomous();

        int yellow = detectYellowTensor();

        scoreMorePoints(yellow, false,true,false);

    }
}
