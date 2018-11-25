package org.firstinspires.ftc.teamcode.tests;


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
        boolean isAndy = false;
        boolean andyWatch = false;
        while (opModeIsActive()) {

            if (gamepad1.left_stick_button && !andyWatch) {
                isAndy = !isAndy;
            }
            andyWatch = gamepad1.left_stick_button;

            telemetry.addData("andy?", isAndy);

            if (!isAndy) {
                if (gamepad1.a) {
                    Turn(90);
                }
                if (gamepad1.b) {
                    Turn(-90);
                }
                if (gamepad1.y) {
                    Turn(45); //particle 1 40
                }
                if (gamepad1.x) {
                    Turn(-45);
                }
                if (gamepad2.a) {
                    Turn(180);
                }
                if (gamepad1.b) {
                    Turn(-180);
                }
                if (gamepad2.y) {
                    Turn(15);
                }
                if (gamepad2.x) {
                    Turn(-15); //particle 3
                }
            } else {
                if (gamepad1.a) {
                    andyTurn(90, .15, 1, 1);
                }
                if (gamepad1.b) {
                    andyTurn(-90, .15, 1, 1);
                }
                if (gamepad1.x) {
                    andyTurn(45, .15, 1, 1);
                }
                if (gamepad1.y) {
                    andyTurn(-45, .15, 1, 1);
                }
                if (gamepad2.a) {
                    andyTurn(180, .15, 1, 1);
                }
                if (gamepad2.b) {
                    andyTurn(-180, .15, 1, 1);
                }
                if (gamepad2.x) {
                    andyTurn(15, .15, 1, 1);
                }
                if (gamepad2.y) {
                    andyTurn(15, .15, 1, 1);
                }
            }

        }
    }

}
