package org.firstinspires.ftc.teamcode;


import static org.firstinspires.ftc.teamcode.subsystems.Servos.COLLECTION_INIT;
import static org.firstinspires.ftc.teamcode.subsystems.Servos.DEPOSIT_INIT;

/**
 * Created by xiax on 4/23/2018.
 */

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleopAdvancedNew", group = "yes")
public class TeleOpAdvancedNew extends TeleOpOpMode {

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

        boolean collectionToggle = true;
        boolean collectionToggleWatch = false;

        int intakeStage = 2;
        boolean intakeStageWatch = false;

        boolean isCollecting = false;

        int depositStage = 1;
        boolean depositStageWatch = false;

        minerals.isEncoderModeVert(false);
        minerals.isEncoderModeHoriz(false);

        servos.setCollectionCollect(false);

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


            if (gamepad2.dpad_up || gamepad1.dpad_up) {
                hang.setHangingPower(1);
            } else if (gamepad2.dpad_down || gamepad1.dpad_down) {
                hang.setHangingPower(-1);
            } else {
                hang.setHangingPower(0);
            }

            if (stage == 1) {

                //picking up stuff --- will go through many times --- use switch stage
                isDriveOpposite = true;
                isSlow = false;

                servos.setDepositDump(false);

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

                if (gamepad1.left_bumper && !collectionToggleWatch) {
                    collectionToggle = !collectionToggle;
                }
                collectionToggleWatch = gamepad1.left_bumper;
                telemetry.addData("collectionToggle", collectionToggle);

                if (intakeStage == 3) {
                    intakeStage = 1;
                }

                if (intakeStage == 1) { //intake config
                    minerals.mCollect.setPower(-1);
                    isCollecting = true;
                } else if (intakeStage == 2) {
                    minerals.mCollect.setPower(0);
                    isCollecting = false;
                } else {
                }
                if (gamepad1.left_trigger > .5) {
                    minerals.mCollect.setPower(1);
                    isCollecting = true;
                }

                minerals.mExtendHoriz.setPower(-gamepad1.right_stick_y);


                if (collectionToggle && isCollecting) {
                    servos.setCollectionCollect(true);
                } else if (collectionToggle && !isCollecting) {
                    servos.setCollectionAlmostCollect();
                } else {
                    servos.setCollectionCollect(false);
                }

                // servos.setCollectionCollect(collectionToggle);

                minerals.mExtendVert.setPower(gamepad2.right_stick_y * .5);

            } else if (stage == 2) {

                //will only go through once --- automatic switch stage

                minerals.mExtendHoriz.setPower(0);
                minerals.mExtendVert.setPower(0);
                minerals.mCollect.setPower(0);

                servos.sCollectionRot.setPosition(COLLECTION_INIT);
                servos.sDepositRot.setPosition(DEPOSIT_INIT);

                minerals.isEncoderModeHoriz(false);

                boolean manualOverrideH = false;

                while (opModeIsActive() && sensors.horizTouch.getState() && !manualOverrideH) {

                    isDriveOpposite = true;

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


                    minerals.mExtendHoriz.setPower(-.8);

                    telemetry.addData("stage", "2 loop 1");
                    telemetry.update();

                    manualOverrideH = gamepad1.a;

                    minerals.mExtendVert.setPower(gamepad2.right_stick_y * .5);
                }

                minerals.mExtendVert.setPower(0);

                servos.setDepositDump(false);

                servos.setCollectionCollect(false);

                sleep(300);

                minerals.mCollect.setPower(-1);

                servos.setBackstopColOpen(true);
                servos.setBackstopDepOpen(true);

                boolean doneTransferring = false;

                while (opModeIsActive() && !doneTransferring) {

                    doneTransferring = gamepad1.a;

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

                    minerals.mExtendVert.setPower(gamepad2.right_stick_y);

                }

                minerals.mExtendVert.setPower(0);

                minerals.mExtendHoriz.setPower(.4);
                servos.setBackstopDepOpen(false);

                minerals.isEncoderModeVert(true);

                int start = minerals.mExtendVert.getCurrentPosition();
                int end = start - ENCODER_TO_DEPOSITUP;

                minerals.mExtendVert.setPower(-1);

                minerals.mExtendVert.setTargetPosition(end);

                timer.reset();

                timer.startTime();

                while (opModeIsActive() && minerals.mExtendVert.isBusy() && timer.seconds() < 3) {

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

                minerals.mExtendVert.setPower(-.2);
                minerals.mExtendHoriz.setPower(0);

                stage = stage + 1;
            } else if (stage == 3) { // auto switch stage

                servos.setDepositDump(false);
                minerals.mCollect.setPower(0);

                stage = stage + 1;
            } else if (stage == 4) {

                //will go through many times --- manual switch stage

                isDriveOpposite = true;
                isSlow = true;

                double x1 = Math.copySign(Math.pow(gamepad1.left_stick_x, 1), -gamepad1.left_stick_x);
                double y1 = Math.copySign(Math.pow(gamepad1.left_stick_y, 1), gamepad1.left_stick_y);
                double x2 = Math.copySign(Math.pow(gamepad1.right_stick_x, 1), -gamepad1.right_stick_x);

                if (isDriveOpposite) {
                    if (isSlow) {
                        drive.driveArcade(-x1, -y1, x2, 1.5);
                    } else {
                        drive.driveArcade(-x1, -y1, x2, 1);
                    }
                } else {
                    if (isSlow) {
                        drive.driveArcade(x1, y1, x2, 1.5);
                    } else {
                        drive.driveArcade(x1, y1, x2, 1);
                    }
                }

            } else if (stage == 5) {
                servos.setDepositDump(true);
                sleep(10);
                stage = 6;
            } else if (stage == 6) {

                minerals.mCollect.setPower(0);

                //will go through many times --- manual switch stage

                isDriveOpposite = true;
                isSlow = true;

                double x1 = Math.copySign(Math.pow(gamepad1.left_stick_x, 1), -gamepad1.left_stick_x);
                double y1 = Math.copySign(Math.pow(gamepad1.left_stick_y, 1), gamepad1.left_stick_y);
                double x2 = Math.copySign(Math.pow(gamepad1.right_stick_x, 1), -gamepad1.right_stick_x);

                if (isDriveOpposite) {
                    if (isSlow) {
                        drive.driveArcade(-x1, -y1, x2, 1.5);
                    } else {
                        drive.driveArcade(-x1, -y1, x2, 1);
                    }
                } else {
                    if (isSlow) {
                        drive.driveArcade(x1, y1, x2, 1.5);
                    } else {
                        drive.driveArcade(x1, y1, x2, 1);
                    }
                }


            } else if (stage == 7) { // switch stage auto

                servos.setBackstopDepOpen(false);
                servos.setDepositComingDown();
                servos.setBackstopColOpen(false);
                servos.setCollectionCollect(false);

                collectionToggle = false;

                sleep(300);

                stage = stage + 1;

            } else if (stage == 8) { // auto switch stage

                minerals.mExtendHoriz.setPower(0);

                minerals.isEncoderModeVert(false);

                boolean manualOverrideV = false;

                while (opModeIsActive() && sensors.vertTouch.getState() && !manualOverrideV) {
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

                    minerals.mExtendVert.setPower(.4);

                    minerals.mExtendHoriz.setPower(-gamepad1.right_stick_y * 1);

                    manualOverrideV = (gamepad1.a || gamepad2.a);

                    minerals.mCollect.setPower(-1);

                    if (gamepad1.left_bumper && !collectionToggleWatch) {
                        collectionToggle = !collectionToggle;
                    }
                    collectionToggleWatch = gamepad1.left_bumper;
                    telemetry.addData("collectionToggle", collectionToggle);

                    servos.setCollectionCollect(collectionToggle);

                }

                minerals.mExtendVert.setPower(0);

                stage = 1;
            }


        }


    }


}
