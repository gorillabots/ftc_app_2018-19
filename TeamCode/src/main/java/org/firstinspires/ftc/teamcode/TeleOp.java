package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.old.OldHolonomicDrivebase;
import org.firstinspires.ftc.teamcode.subsystems.Hanging;
import org.firstinspires.ftc.teamcode.subsystems.Servos;

/**
 * Created by xiax on 4/23/2018.
 */

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Teleop", group = "yes")
public class TeleOp extends LinearOpMode {

    OldHolonomicDrivebase drive;
    Hanging hang;

    @Override
    public void runOpMode() {

        drive = new OldHolonomicDrivebase(hardwareMap, telemetry);

        hang = new Hanging(hardwareMap, telemetry);

        waitForStart();

        boolean isSlow = false;
        boolean slowWatch = false;

        boolean driveOpposite = false;
        boolean driveOppositeWatch = false;

        while (opModeIsActive()) {
            //toggles
            if (gamepad1.x && !slowWatch) {
                isSlow = !isSlow;
            }
            slowWatch = gamepad1.x;
            telemetry.addData("slow?", isSlow);

            if (gamepad1.y && !driveOppositeWatch) {
                driveOpposite = !driveOpposite;
            }
            driveOppositeWatch = gamepad1.y;
            telemetry.addData("driveOpposite?", driveOpposite);

            //drive
            double x1 = Math.copySign(Math.pow(gamepad1.left_stick_x, 1), -gamepad1.left_stick_x);
            double y1 = Math.copySign(Math.pow(gamepad1.left_stick_y, 1), gamepad1.left_stick_y);
            double x2 = Math.copySign(Math.pow(gamepad1.right_stick_x, 1), -gamepad1.right_stick_x);

            if (driveOpposite) {
                if (isSlow) {
                    drive.driveArcade(-x1, -y1, x2, 2);
                } else {
                    drive.driveArcade(-x1, -y1, x2, 1);
                }
            } else {
                if (isSlow) {
                    drive.driveArcade(x1, y1, x2, 2);
                } else {
                    drive.driveArcade(x1, y1, x2, 1);
                }
            }

            //hang
            if (gamepad1.dpad_up && isSlow) {
                hang.setHangingPower(-.5);
            } else if (gamepad1.dpad_down && isSlow) {
                hang.setHangingPower(.5);
            } else if (gamepad1.dpad_up) {
                hang.setHangingPower(-1);
            } else if (gamepad1.dpad_down) {
                hang.setHangingPower(1);
            } else {
                hang.setHangingPower(.0);
            }


            sleep(20);

        }
    }
}
