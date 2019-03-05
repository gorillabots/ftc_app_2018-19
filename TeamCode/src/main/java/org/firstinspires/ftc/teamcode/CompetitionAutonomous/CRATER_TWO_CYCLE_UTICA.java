package org.firstinspires.ftc.teamcode.CompetitionAutonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutonomousProgramsOpMode;
import org.firstinspires.ftc.teamcode.CraterAutos;
import org.firstinspires.ftc.teamcode.CraterAutosWithMineral;

/**
 * Created by xiax on 4/23/2018.
 */

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
