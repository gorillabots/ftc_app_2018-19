package org.firstinspires.ftc.teamcode.tests.sensor;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousOpMode;

/**
 * Created by xiax on 4/23/2018.
 */

@TeleOp(name = "touch test", group = "test")
public class touchTest extends AutonomousOpMode {

    @Override
    public void runOpMode() {

        initializeAutonomous();

        waitForStart();

        boolean vertWatch = false;

        boolean horizWatch = false;

        while (opModeIsActive()) {

            if (gamepad1.y && !vertWatch) {

                setVertExtensionDown();

            }

            vertWatch = gamepad1.y;

            if (gamepad1.b && !horizWatch){
                retractHoriz();
            }
            horizWatch = gamepad1.b;


        }
    }

}
