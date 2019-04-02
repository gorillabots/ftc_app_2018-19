package org.firstinspires.ftc.teamcode.tests;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousOpMode;

/**
 * Created by xiax on 4/23/2018.
 */

@TeleOp(name = "Drive Test.", group = "test")
public class DriveTest extends AutonomousOpMode {

    @Override
    public void runOpMode() {

        initializeAutonomous();

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.a) {
                MoveUntilEncoder(10, 0, .5);
            }
            if (gamepad1.b) {
                MoveUntilEncoder(20, 0, .5);
            }
            if (gamepad1.y) {
                MoveUntilEncoder(40, 0, .5);
            }
            if (gamepad1.x) {
                MoveUntilEncoder(80, 0, .5);
            }
            if (gamepad2.a) {
                MoveUntilEncoder(10, 180, .5);
            }
            if (gamepad2.b) {
                MoveUntilEncoder(20, 180, .5);
            }
            if (gamepad2.y) {
                MoveUntilEncoder(40, 180, .5);
            }
            if (gamepad2.x) {
                MoveUntilEncoder(80, 180, .5);
            }


        }
    }

}
