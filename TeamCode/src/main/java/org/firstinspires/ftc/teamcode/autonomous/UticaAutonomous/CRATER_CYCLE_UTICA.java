package org.firstinspires.ftc.teamcode.autonomous.UticaAutonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

/**
 * Created by xiax on 4/23/2018.
 */
@Disabled
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
