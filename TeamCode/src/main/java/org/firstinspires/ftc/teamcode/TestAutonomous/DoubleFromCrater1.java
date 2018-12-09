package org.firstinspires.ftc.teamcode.TestAutonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutonomousOpMode;

/**
 * Created by xiax on 4/23/2018.
 */

@Autonomous(name = "Double from Crater 1", group = "Autonomous")
public class DoubleFromCrater1 extends AutonomousOpMode {


    @Override
    public void runOpMode() {

        initializeAutonomous();

        int yellow = 1;

        waitForStart();

        unHangWithEncoder();

        scorePoints(yellow, false,true);

    }
}
