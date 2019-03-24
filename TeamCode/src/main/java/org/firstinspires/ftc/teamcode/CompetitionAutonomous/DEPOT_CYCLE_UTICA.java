package org.firstinspires.ftc.teamcode.CompetitionAutonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.DepotAutos;
import org.firstinspires.ftc.teamcode.DepotAutosWithMineral;

/**
 * Created by xiax on 4/23/2018.
 */
@Disabled
@Autonomous(name = "DEPOT 1 CYCLE", group = "Autonomous")
public class DEPOT_CYCLE_UTICA extends DepotAutosWithMineral {

    @Override
    public void runOpMode() {

        initializeAutonomous();

        int yellow = detectYellowTensor();

        unHangWithEncoder();

        switch(yellow) {
            case 1:
                scoreLeftDepotMin();
            case 2:
                scoreMiddleDepotMin();
            case 3:
                scoreRightDepotMin();

        }
    }
}
