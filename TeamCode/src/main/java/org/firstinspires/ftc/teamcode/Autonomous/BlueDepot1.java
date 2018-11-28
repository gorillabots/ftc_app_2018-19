package org.firstinspires.ftc.teamcode.Autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutonomousOpMode;

import java.util.Random;

/**
 * Created by xiax on 4/23/2018.
 */

@Autonomous(name = "Blue Depot 1", group = "Autonomous")
public class BlueDepot1 extends AutonomousOpMode {

    Random random;

    @Override
    public void runOpMode() {

        initializeAutonomous();

        int yellow = 1;

        waitForStart();

        unHangWithEncoder();

        scorePoints(yellow, true);

    }
}
