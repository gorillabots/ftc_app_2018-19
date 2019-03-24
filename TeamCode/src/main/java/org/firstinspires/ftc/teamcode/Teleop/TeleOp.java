package org.firstinspires.ftc.teamcode.Teleop;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;

/**
 * Created by xiax on 4/23/2018.
 */
@Disabled
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Teleop", group = "yes")
public class TeleOp extends TeleOpOpMode {

    @Override
    public void runOpMode() {

        initializeTeleop();

        waitForStart();

        boolean isSlow = false;
        boolean slowWatch = false;

        boolean isDriveOpposite = false;
        boolean driveOppositeWatch = false;

        boolean isCollectionRotDown = false;
        boolean CollectionRotWatch = false;

        boolean isDepositRotDumping = false;
        boolean DepositWatch = false;

        boolean isAutonomous = false;
        boolean AutonomousWatch = false;

        while (opModeIsActive()) {
            //toggles
            if (gamepad1.x && !slowWatch) {
                isSlow = !isSlow;
            }
            slowWatch = gamepad1.x;
            telemetry.addData("slow?", isSlow);

            if (gamepad1.y && !driveOppositeWatch) {
                isDriveOpposite = !isDriveOpposite;
            }
            driveOppositeWatch = gamepad1.y;
            telemetry.addData("isDriveOpposite?", isDriveOpposite);

            if (gamepad2.y && !AutonomousWatch) {
                isAutonomous = !isAutonomous;
            }
            AutonomousWatch = gamepad1.y;
            telemetry.addData("isAutonomous?", isAutonomous);

            if (gamepad2.left_trigger > .5 && !CollectionRotWatch) {
                isCollectionRotDown = !isCollectionRotDown;
            }
            CollectionRotWatch = gamepad2.left_trigger > .5;
            telemetry.addData("isCollectionRotDown?", isCollectionRotDown);


            if (gamepad2.left_bumper && !DepositWatch) {
                isDepositRotDumping = !isDepositRotDumping;
            }
            DepositWatch = gamepad2.left_bumper;
            telemetry.addData("isCollectionRotDown?", isCollectionRotDown);

            if(isAutonomous){}
            else {
                //drive
                double x1 = Math.copySign(Math.pow(gamepad1.left_stick_x, 1), -gamepad1.left_stick_x);
                double y1 = Math.copySign(Math.pow(gamepad1.left_stick_y, 1), gamepad1.left_stick_y);
                double x2 = Math.copySign(Math.pow(gamepad1.right_stick_x, 1), -gamepad1.right_stick_x);

                if (isDriveOpposite) {
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

                minerals.mExtendHoriz.setPower(gamepad2.left_stick_y);
                minerals.mExtendVert.setPower(gamepad2.right_stick_y);

                servos.setCollectionCollect(isCollectionRotDown);

                if (gamepad2.right_trigger > .5){
                    minerals.mCollect.setPower(1);
                }
                if (gamepad2.right_bumper){
                    minerals.mCollect.setPower(-1);
                }

                servos.setDepositDump(isDepositRotDumping);

            }
            sleep(20);
        }
    }

}
