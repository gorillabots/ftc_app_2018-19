package org.firstinspires.ftc.teamcode.CompetitionAutonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutonomousProgramsOpMode;
import org.firstinspires.ftc.teamcode.DepotAutos;

/**
 * Created by xiax on 4/23/2018.
 */

@Autonomous(name = "DEPOT UTICA", group = "Autonomous")
public class DEPOT_UTICA extends DepotAutos {

    @Override
    public void runOpMode() {

        initializeAutonomous();

        int yellow = detectYellowTensor();

        unHangWithEncoder();

        switch(yellow) {
            case 1:
                scoreLeftDepot();
            case 2:
                scoreMiddleDepot();
            case 3:
                scoreRightDepot();

        }
    }
}
