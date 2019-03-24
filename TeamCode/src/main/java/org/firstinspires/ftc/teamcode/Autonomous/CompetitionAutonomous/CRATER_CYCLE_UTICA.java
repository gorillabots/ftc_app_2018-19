package org.firstinspires.ftc.teamcode.Autonomous.CompetitionAutonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.CraterAutosWithMineral;

/**
 * Created by xiax on 4/23/2018.
 */

@Autonomous(name = "CRATER 1 CYCLE", group = "Autonomous")
public class CRATER_CYCLE_UTICA extends CraterAutosWithMineral {

    @Override
    public void runOpMode() {

        initializeAutonomous();

        int yellow = detectYellowTensor();

        unHangWithEncoder();

        switch(yellow) {
            case 1:
                scoreLeftCraterOneCycle();
            case 2:
                scoreMiddleCraterOneCycle();
            case 3:
                scoreRightCraterOneCycle();

        }
    }
}
