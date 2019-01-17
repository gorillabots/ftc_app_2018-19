package org.firstinspires.ftc.teamcode;


/**
 * Created by xiax on 4/23/2018.
 */

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "ResetAutonomousTeleop", group = "yes")
public class ResetAutonomousTeleop extends TeleOpOpMode {

    @Override
    public void runOpMode() {

        initializeTeleop();

        boolean isSlow = false;

        waitForStart();

        while (opModeIsActive()) {
            double x1 = Math.copySign(Math.pow(gamepad1.left_stick_x, 1), -gamepad1.left_stick_x);
            double y1 = Math.copySign(Math.pow(gamepad1.left_stick_y, 1), gamepad1.left_stick_y);
            double x2 = Math.copySign(Math.pow(gamepad1.right_stick_x, 1), -gamepad1.right_stick_x);

            drive.driveArcade(-x1, -y1, x2, 1);

            if (gamepad1.dpad_up && isSlow) {
                hang.setHangingPower(.5);
            } else if (gamepad1.dpad_down && isSlow) {
                hang.setHangingPower(-.5);
            } else if (gamepad1.dpad_up) {
                hang.setHangingPower(1);
            } else if (gamepad1.dpad_down) {
                hang.setHangingPower(-1);
            } else {
                hang.setHangingPower(.0);
            }


        }
    }
}