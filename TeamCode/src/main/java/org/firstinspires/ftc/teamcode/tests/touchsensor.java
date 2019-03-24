package org.firstinspires.ftc.teamcode.tests;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousOpMode;

/**
 * Created by xiax on 4/23/2018.
 */

@TeleOp(name = "touch horiz", group = "test")
public class touchsensor extends AutonomousOpMode {

    @Override
    public void runOpMode() {

        initializeAutonomous();

        waitForStart();


        while (opModeIsActive()) {



            telemetry.addData("hello", "hello");
            telemetry.addData("isPressed", sensors.horizTouch.getState());
            telemetry.addData("getValue", sensors.horizTouch.getState());
            telemetry.update();

        }
    }

}
