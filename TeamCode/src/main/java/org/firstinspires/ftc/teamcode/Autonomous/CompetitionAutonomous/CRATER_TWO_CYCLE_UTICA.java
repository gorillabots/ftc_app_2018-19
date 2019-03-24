package org.firstinspires.ftc.teamcode.Autonomous.CompetitionAutonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Autonomous.CraterAutosWithMineral;

/**
 * Created by xiax on 4/23/2018.
 */
@Disabled
@Autonomous(name = "CRATER UTICA 2 CYCLE", group = "Autonomous")
public class CRATER_TWO_CYCLE_UTICA extends CraterAutosWithMineral {

    @Override
    public void runOpMode() {

        initializeAutonomous();

        int yellow = detectYellowTensor();

        unHangWithEncoder();

        switch(yellow) {
            case 1:
                scoreLeftCraterTwoCycle();
            case 2:
                scoreMiddleCraterTwoCycle();
            case 3:
                scoreRightCraterTwoCycle();
        }

    }
}
