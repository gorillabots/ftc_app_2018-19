package org.firstinspires.ftc.teamcode.TestAutonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutonomousOpMode;

/**
 * Created by xiax on 4/23/2018.
 */

@Autonomous(name = "Dump", group = "Autonomous")
public class dump extends AutonomousOpMode {


    @Override
    public void runOpMode() {

        initializeAutonomous();

        waitForStart();

        servos.moveCollector(true, false);

    }
}
