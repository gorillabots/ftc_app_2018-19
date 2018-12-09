package org.firstinspires.ftc.teamcode.CompetitionAutonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutonomousOpMode;

import java.util.Random;

/**
 * Created by xiax on 4/23/2018.
 */

@Autonomous(name = "DEPOT", group = "Autonomous")
public class DEPOT extends AutonomousOpMode {


    @Override
    public void runOpMode() {

        initializeAutonomous();

        int yellow = detectYellowTensor();

        unHangWithEncoder();

        scorePoints(yellow, true,false);

    }
}
