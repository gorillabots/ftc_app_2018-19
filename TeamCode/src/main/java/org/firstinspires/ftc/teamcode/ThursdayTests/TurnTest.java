package org.firstinspires.ftc.teamcode.ThursdayTests;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.AutonomousOpMode;

/**
 * Created by xiax on 4/23/2018.
 */

@TeleOp(name = "Turn Test", group = "test")
public class TurnTest extends AutonomousOpMode {

    @Override
    public void runOpMode() {

        initializeAutonomous();

        waitForStart();
        boolean isFast = false;
        boolean andyWatch = false;
        while (opModeIsActive()) {

            if (gamepad1.left_stick_button && !andyWatch) {
                isFast = !isFast;
            }
            andyWatch = gamepad1.left_stick_button;

            telemetry.addData("fast?", isFast);

            if (!isFast) {
                if (gamepad1.a) {
                    TurnAbsolute(90);
                }
                if (gamepad1.b) {
                    TurnAbsolute(-90);
                }
                if (gamepad1.y) {
                    TurnAbsolute(45);
                }
                if (gamepad1.x) {
                    TurnAbsolute(-45);
                }
                if (gamepad2.a) {
                    TurnAbsolute(180);
                }
                if (gamepad2.b) {
                    TurnAbsolute(-180);
                }
                if (gamepad2.y) {
                    TurnAbsolute(0);
                }
                if (gamepad2.x) {
                    TurnAbsolute(-15);
                }
            } else {
                if (gamepad1.a) {
                    TurnFaster(90);
                }
                if (gamepad1.a) {
                    TurnFaster(90);
                }
                if (gamepad1.b) {
                    TurnFaster(-90);
                }
                if (gamepad1.y) {
                    TurnFaster(45);
                }
                if (gamepad1.x) {
                    TurnFaster(-45);
                }
                if (gamepad2.a) {
                    TurnFaster(180);
                }
                if (gamepad2.b) {
                    TurnFaster(-180);
                }
                if (gamepad2.y) {
                    TurnFaster(0);
                }
                if (gamepad2.x) {
                    TurnFaster(-15);
                }

            }

        }
    }

}
