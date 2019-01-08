package org.firstinspires.ftc.teamcode;


import org.firstinspires.ftc.teamcode.subsystems.MineralCollectionMechanism;
import org.firstinspires.ftc.teamcode.subsystems.Servos;

/**
 * Created by xiax on 4/23/2018.
 */

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleopAdvanced", group = "yes")
public class TeleOpAdvanced extends TeleOpOpMode {

    @Override
    public void runOpMode() {

        initializeTeleop();

        waitForStart();

        int stage = 1;
        boolean SwitchStageWatch = false;

        boolean isSlow = false;
        boolean slowWatch = false;

        boolean isDriveOpposite = false;
        boolean driveOppositeWatch = false;

        int intakeStage = 1;
        boolean intakeStageWatch = false;

        int depositStage = 1;
        boolean depositStageWatch = false;

        minerals.isEncoderModeVert(false);
        minerals.isEncoderModeHoriz(false);

        while (opModeIsActive()) {

            if (gamepad1.right_trigger > .5 && !SwitchStageWatch) {
                stage = stage + 1;
            }
            SwitchStageWatch = gamepad1.right_trigger > .5;
            telemetry.addData("stage", stage);

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

            if (stage == 1) {

                //picking up stuff --- will go through many times

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

                if (gamepad1.right_bumper && !intakeStageWatch) {
                    intakeStage = intakeStage + 1;
                }
                intakeStageWatch = gamepad1.right_bumper;
                telemetry.addData("intakeStage", intakeStage);

                if (intakeStage == 4) {
                    intakeStage = 1;
                }

                if (intakeStage == 1) { //intake config
                    minerals.mCollect.setPower(-1);
                } else if (intakeStage == 2) {
                    minerals.mCollect.setPower(1);
                } else {
                    minerals.mCollect.setPower(0);
                }

                minerals.mExtendHoriz.setPower(gamepad1.right_stick_y);

            }

            if (stage == 2) {

                //will only go through once
                minerals.mCollect.setPower(-1);

                servos.setCollectionCollect(false);

                while (opModeIsActive() && !sensors.horizTouch.isPressed()) {

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

                    minerals.mExtendHoriz.setPower(-.75);
                }

                servos.setBackstopColOpen(true);

                sleep(600);

                minerals.mExtendHoriz.setPower(.3);

                minerals.isEncoderModeVert(true);

                int start = minerals.mExtendVert.getCurrentPosition();
                int end = start - ENCODER_TO_DEPOSITUP;

                minerals.mExtendVert.setPower(-1);

                minerals.mExtendVert.setTargetPosition(end);

                while(opModeIsActive() && minerals.mExtendVert.isBusy()){

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

                }

                minerals.mExtendVert.setPower(0);
                minerals.mExtendHoriz.setPower(0);

                minerals.isEncoderModeVert(false);

                stage = stage + 1;
            }
            if(stage == 3){

                depositStage = 1;

                stage = stage + 1;
            }
            if(stage == 4){

                //will go through many times

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

                if (gamepad1.right_bumper && !depositStageWatch) {
                    depositStage = depositStage + 1;
                }
                slowWatch = gamepad1.right_bumper;
                telemetry.addData("deposit Stage", depositStage);

               

            }


        }


    }


}
