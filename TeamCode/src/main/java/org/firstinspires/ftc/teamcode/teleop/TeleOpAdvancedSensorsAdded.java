package org.firstinspires.ftc.teamcode.teleop;


/**
 * Created by xiax on 4/23/2018.
 */

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleopAdvancedNewSA", group = "zzza")
public class TeleOpAdvancedSensorsAdded extends TeleOpOpMode {

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

        boolean endgameRetract = false;
        boolean endgameRetractWatch = false;

        int intakeStage = 2;
        boolean intakeStageWatch = false;

        boolean isCollecting = false;

        boolean depositHalfway = false;
        boolean depositStageWatch = false;

        boolean didWeReset = false;

        minerals.isEncoderModeVert(false);
        minerals.isEncoderModeHoriz(false);

        servos.setCollectionCollect(false);

        servos.setTeammarkerTeleop();

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


            if ((gamepad2.dpad_up || gamepad1.dpad_up) && sensors.hangTouch.getState()) {
                hang.setHangingPower(1);
            } else if (gamepad2.dpad_down || gamepad1.dpad_down) {
                hang.setHangingPower(-1);
            } else {
                hang.setHangingPower(0);
            }

            if (stage == 1) {

                servos.setBackstopColOpen(false);

                if (gamepad1.y && !endgameRetractWatch){
                    endgameRetract = !endgameRetract;
                }
                endgameRetractWatch = gamepad1.y;

                if (endgameRetract){
                    minerals.mExtendHoriz.setPower( .3);
                }

                //picking up stuff --- will go through many times --- use switch stage
                isDriveOpposite = true;
                isSlow = false;

                servos.setDepositComingDown();

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
                    minerals.mCollect.setPower(1);
                    isCollecting = true;
                } else if (intakeStage == 2) {
                    minerals.mCollect.setPower(0);
                    isCollecting = false;
                } else {
                }
                if (gamepad1.left_trigger > .5) {
                    minerals.mCollect.setPower(-1);
                    isCollecting = true;
                }

                minerals.mExtendHoriz.setPower(gamepad1.right_stick_y);


                if (collectionToggle && isCollecting) {
                    servos.setCollectionCollect(true);
                } else if (collectionToggle && !isCollecting) {
                    servos.setCollectionAlmostCollect();
                } else {
                    servos.setCollectionCollect(false);
                }

                // servos.setCollectionCollect(collectionToggle);

                minerals.mExtendVert.setPower(gamepad2.right_stick_y * .5);

                if (gamepad1.a) { //PROBLEMATIC

                    boolean manualA = false;

                    minerals.isEncoderModeVert(false);

                    timer.reset();
                    timer.startTime();

                    while (opModeIsActive() && sensors.vertTouch.getState() && !manualA && timer.seconds() < 1.5) {
                        x1 = Math.copySign(Math.pow(gamepad1.left_stick_x, 1), -gamepad1.left_stick_x);
                        y1 = Math.copySign(Math.pow(gamepad1.left_stick_y, 1), gamepad1.left_stick_y);
                        x2 = Math.copySign(Math.pow(gamepad1.right_stick_x, 1), -gamepad1.right_stick_x);

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

                        minerals.mExtendVert.setPower(-.2);

                        minerals.mExtendHoriz.setPower(gamepad1.right_stick_y * 1);

                        manualA = (gamepad1.x || gamepad2.a);

                        minerals.mCollect.setPower(1);

                        if (gamepad1.left_bumper && !collectionToggleWatch) {
                            collectionToggle = !collectionToggle;
                        }
                        collectionToggleWatch = gamepad1.left_bumper;
                        telemetry.addData("collectionToggle", collectionToggle);

                        servos.setCollectionCollect(collectionToggle);

                        didWeReset = true;
                        intakeStage = 1;
                    }


                }
                if (gamepad1.b) {//PROBLEM IS PROBLEMATIC
                    boolean manualB = false;
                    minerals.isEncoderModeVert(false);
                    timer.reset();
                    timer.startTime();
                    while (opModeIsActive() && sensors.vertTouch.getState() && !manualB && timer.seconds() < 1) {
                        x1 = Math.copySign(Math.pow(gamepad1.left_stick_x, 1), -gamepad1.left_stick_x);
                        y1 = Math.copySign(Math.pow(gamepad1.left_stick_y, 1), gamepad1.left_stick_y);
                        x2 = Math.copySign(Math.pow(gamepad1.right_stick_x, 1), -gamepad1.right_stick_x);

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

                        minerals.mExtendVert.setPower(.16);

                        minerals.mExtendHoriz.setPower(gamepad1.right_stick_y * 1);

                        manualB = (gamepad1.x || gamepad2.a);

                        minerals.mCollect.setPower(1);

                        if (gamepad1.left_bumper && !collectionToggleWatch) {
                            collectionToggle = !collectionToggle;
                        }
                        collectionToggleWatch = gamepad1.left_bumper;
                        telemetry.addData("collectionToggle", collectionToggle);

                        servos.setCollectionCollect(collectionToggle);

                        intakeStage = 1;

                    }
                }

            } else if (stage == 2) {

                //will only go through once --- automatic switch stage

                minerals.mExtendHoriz.setPower(0);
                minerals.mExtendVert.setPower(0);
                minerals.mCollect.setPower(.4);

                servos.setCollectionComingIn();
                servos.setDepositDump(false);

                minerals.isEncoderModeHoriz(false);

                boolean manualOverrideH = false;

                while (opModeIsActive() && sensors.horizSlowTouch.getState() && !manualOverrideH) {

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


                    minerals.mExtendHoriz.setPower(1);

                    telemetry.addData("stage", "2 loop 1");
                    telemetry.update();

                    manualOverrideH = gamepad1.a;

                    minerals.mExtendVert.setPower(gamepad2.right_stick_y * .5);
                }

                minerals.mExtendHoriz.setPower(.2);

                sleep(200);

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


                    minerals.mExtendHoriz.setPower(.5);

                    telemetry.addData("stage", "2 loop 1");
                    telemetry.update();

                    manualOverrideH = gamepad1.a;

                    minerals.mExtendVert.setPower(gamepad2.right_stick_y * .5);
                }

                minerals.mExtendVert.setPower(0);

                servos.setDepositDump(false);

                servos.setCollectionCollect(false);

                minerals.mCollect.setPower(1);

                servos.setBackstopColOpen(true);
                servos.setBackstopDepOpen(true);

                boolean doneTransferring = false;
                boolean backUpBudYoureBadAtDriving = false;

                while (opModeIsActive() && !doneTransferring && !backUpBudYoureBadAtDriving) {

                    doneTransferring = gamepad1.a;
                    backUpBudYoureBadAtDriving = gamepad1.y;

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

                if (backUpBudYoureBadAtDriving) {
                    stage = 1;
                }
                if (doneTransferring) {
                    minerals.mExtendVert.setPower(0);

                    servos.setDepositComingDown();

                    minerals.mExtendHoriz.setPower(-.0);
                    sleep(75);
                    servos.setBackstopDepOpen(false);

                    minerals.isEncoderModeVert(true);

                    int start = minerals.mExtendVert.getCurrentPosition();
                    int end;
                    if (didWeReset) {
                        end = start - ENCODER_TO_DEPOSITUP;
                    } else {
                        end = start - ENCODER_TO_DEPOSITUP_MORE;
                    }


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
                    minerals.mExtendHoriz.setPower(gamepad1.right_stick_y);

                    stage = stage + 1;
                }
            } else if (stage == 3) { // auto switch stage

                servos.setDepositHalfway();
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

                minerals.mExtendHoriz.setPower(gamepad1.right_stick_y);

            } else if (stage == 5) {
                depositHalfway = false;
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


                if (gamepad1.right_bumper && !depositStageWatch) {
                    depositHalfway = !depositHalfway;
                }
                depositStageWatch = gamepad1.right_bumper;
                telemetry.addData("dep", depositHalfway);

                if (depositHalfway){
                    servos.setDepositHalfway();
                }
                else {
                    servos.setDepositDump(true);
                }



            } else if (stage == 7) { // switch stage auto

                servos.setBackstopDepOpen(false);
                servos.setDepositComingDown();
                servos.setBackstopColOpen(false);
                servos.setCollectionCollect(false);

                didWeReset = false;

                collectionToggle = false;

                sleep(300);

                stage = stage + 1;

            } else if (stage == 8) { // auto switch stage

                minerals.mExtendHoriz.setPower(0);

                minerals.isEncoderModeVert(true);

                int start = minerals.mExtendVert.getCurrentPosition();
                int end;
                if (didWeReset) {
                    end = start + ENCODER_TO_DEPOSITUP;
                } else {
                    end = start + ENCODER_TO_DEPOSITUP_MORE;
                }


                minerals.mExtendVert.setTargetPosition(end);

                timer.reset();

                timer.startTime();

                boolean manualOverrideV = false;

                while (opModeIsActive() && sensors.vertTouch.getState() && !manualOverrideV && minerals.mExtendVert.isBusy()) {
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

                    if (gamepad2.dpad_up || gamepad1.dpad_up) {
                        hang.setHangingPower(1);
                    } else if (gamepad2.dpad_down || gamepad1.dpad_down) {
                        hang.setHangingPower(-1);
                    } else {
                        hang.setHangingPower(0);
                    }

                    minerals.mExtendVert.setPower(.3);

                    minerals.mExtendHoriz.setPower(gamepad1.right_stick_y * 1);

                    manualOverrideV = (gamepad1.b || gamepad2.b);

                    minerals.mCollect.setPower(1);

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
