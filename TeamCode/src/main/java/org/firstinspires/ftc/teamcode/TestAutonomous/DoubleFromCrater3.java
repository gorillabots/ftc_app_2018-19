package org.firstinspires.ftc.teamcode.TestAutonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutonomousOpMode;

/**
 * Created by xiax on 4/23/2018.
 */

@Autonomous(name = "Double from Crater 3", group = "Autonomous")
public class DoubleFromCrater3 extends AutonomousOpMode {


    @Override
    public void runOpMode() {

        initializeAutonomous();

        int yellow = 3;

        waitForStart();

        unHangWithEncoder();

     //   scorePoints(yellow, false,true);

    }
}
