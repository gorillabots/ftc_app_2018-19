package org.firstinspires.ftc.teamcode.Autonomous.CompetitionAutonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousOpMode;
import org.firstinspires.ftc.teamcode.Autonomous.UticaAutonomous.CraterAutos;

/**
 * Created by xiax on 4/23/2018.
 */

@Autonomous(name = "Crater: No Minerals", group = "Autonomous")
public class CraterNoMinerals extends AutonomousOpMode {

    @Override
    public void runOpMode() {

        initializeAutonomous();

        int yellow = detectYellowTensor();

        unHangWithEncoder();

        switch(yellow) {
            case 1:
                scoreLeft();
            case 2:
                scoreCenter();
            case 3:
                scoreRight();
        }


        sleep(10000);
    }
    private void scoreLeft(){}
    private void scoreCenter(){}
    private void scoreRight(){}
}
