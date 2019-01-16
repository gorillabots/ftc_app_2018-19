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

        unHangWithEncoder();

        craterBeginning(yellow);

        switch(yellow) {
            case 1:
                scoreLeftDouble();
            case 2:
                scoreMiddleDouble();
            case 3:
                scoreRightDouble();

        }
    }
}
