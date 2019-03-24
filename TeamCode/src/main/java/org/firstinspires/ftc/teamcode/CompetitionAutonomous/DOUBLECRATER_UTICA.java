package org.firstinspires.ftc.teamcode.CompetitionAutonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.AutonomousProgramsOpMode;
import org.firstinspires.ftc.teamcode.CraterAutos;

/**
 * Created by xiax on 4/23/2018.
 */
@Disabled
@Autonomous(name = "DOUBLE CRATER", group = "Autonomous")
public class DOUBLECRATER_UTICA extends CraterAutos {


    @Override
    public void runOpMode() {

        initializeAutonomous();

        int yellow = detectYellowTensor();

        unHangWithEncoder();

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
