package org.firstinspires.ftc.teamcode.CompetitionAutonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutonomousOpMode;
import org.firstinspires.ftc.teamcode.AutonomousProgramsOpMode;

/**
 * Created by xiax on 4/23/2018.
 */

@Autonomous(name = "CRATER", group = "Autonomous")
public class CRATER extends AutonomousProgramsOpMode {

    @Override
    public void runOpMode() {

        initializeAutonomous();

        int yellow = detectYellowTensor();

        unHangWithEncoder();

        scorePoints(yellow, false,false);
    }
}
