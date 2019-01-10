package org.firstinspires.ftc.teamcode.CompetitionAutonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutonomousOpMode;
import org.firstinspires.ftc.teamcode.AutonomousProgramsOpMode;

import java.util.Random;

/**
 * Created by xiax on 4/23/2018.
 */

@Autonomous(name = "DOUBLE CRATER", group = "Autonomous")
public class DOUBLECRATER extends AutonomousProgramsOpMode {


    @Override
    public void runOpMode() {

        initializeAutonomous();

        int yellow = detectYellowTensor();

        unHangWithEncoder();

        scorePoints(yellow, false,true);

    }
}
