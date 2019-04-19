package org.firstinspires.ftc.teamcode.autonomous.CompetitionAutonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousOpMode;

/**
 * Created by xiax on 4/23/2018.
 */

@Autonomous(name = "Crater: One Mineral", group = "aAutonomous")
public class CraterOneMineral extends AutonomousOpMode {

    @Override
    public void runOpMode() {

        initializeAutonomous();

        int yellow = detectYellowTensor();

        unhangWithTouch();

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
