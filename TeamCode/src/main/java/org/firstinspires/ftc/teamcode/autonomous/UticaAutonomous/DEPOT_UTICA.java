package org.firstinspires.ftc.teamcode.autonomous.UticaAutonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

/**
 * Created by xiax on 4/23/2018.
 */
@Disabled
@Autonomous(name = "DEPOT", group = "Autonomous")
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