package org.firstinspires.ftc.teamcode.Autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutonomousOpMode;

import java.util.Random;

/**
 * Created by xiax on 4/23/2018.
 */

@Autonomous(name = "Blue Depot3", group = "Autonomous")
public class BlueDepot3 extends AutonomousOpMode {

    Random random;

    @Override
    public void runOpMode() {

        initializeAutonomous();

        int yellow = 3;

        waitForStart();

        unHangWithEncoder();

        MoveUntilEncoder(3, 270, 1);

        Turn(40);

        if (yellow == 1) {
            scoreLeftDepot();
        } else if (yellow == 2) {
            scoreMiddleDepot();
        } else {
            scoreRightDepot();
        }

    }
}
