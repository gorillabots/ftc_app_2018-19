package org.firstinspires.ftc.teamcode.TestAutonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutonomousOpMode;

/**
 * Created by xiax on 4/23/2018.
 */

@Autonomous(name = "Red Crater 2", group = "Autonomous")
public class RedCrater2 extends AutonomousOpMode {

    @Override
    public void runOpMode() {

        initializeAutonomous();

        int yellow = 2;

        waitForStart();

        unHangWithEncoder();

        scorePoints(yellow, false,false);
    }
}
