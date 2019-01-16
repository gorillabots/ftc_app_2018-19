package org.firstinspires.ftc.teamcode;


/**
 * Created by xiax on 4/23/2018.
 */

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleopAutomationTest", group = "yes")
public class TeleopAutomationTest extends TeleOpOpMode {

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

        int intakeStage = 1;
        boolean intakeStageWatch = false;

        int depositStage = 1;
        boolean depositStageWatch = false;

        minerals.isEncoderModeVert(false);
        minerals.isEncoderModeHoriz(false);

        boolean firstCycle= true;
        boolean afirstCycle= true;
        boolean bfirstCycle= true;
        boolean cfirstCycle= true;
        boolean dfirstCycle= true;
        boolean efirstCycle= true;
        boolean ffirstCycle= true;

        while (opModeIsActive()) {

            if (gamepad1.right_trigger > .5 && !SwitchStageWatch) {
                stage = stage + 1;
            }
            SwitchStageWatch = gamepad1.right_trigger > .5;
            telemetry.addData("stage", stage);

            if (gamepad1.dpad_up){

              firstCycle= true;
              afirstCycle= true;
              bfirstCycle= true;
              cfirstCycle= true;
              dfirstCycle= true;
              efirstCycle= true;
              ffirstCycle= true;
            }

            if (gamepad1.left_trigger > .5){
                minerals.mCollect.setPower(0);
            }
            if (stage == 1 && firstCycle) {

                minerals.mCollect.setPower(-1);

                servos.setCollectionCollect(false);

                firstCycle = false;
            }
            if (stage == 2 && afirstCycle){
                while (opModeIsActive() && !sensors.horizTouch.getState()) {

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

                    minerals.mExtendHoriz.setPower(-.5);

                    telemetry.addData("stage", "2 loop 1");
                    telemetry.update();
                }
                afirstCycle = false;
            }
            if (stage == 3 && bfirstCycle){

                servos.setBackstopColOpen(true);
                servos.setBackstopDepOpen(true);

                sleep(1000);

                minerals.mExtendHoriz.setPower(.2);
                servos.setBackstopDepOpen(false);

                bfirstCycle = false;

            }
            if (stage == 4 && cfirstCycle){

                minerals.isEncoderModeVert(true);

                int start = minerals.mExtendVert.getCurrentPosition();
                int end = start - ENCODER_TO_DEPOSITUP;

                minerals.mExtendVert.setPower(-1);

                minerals.mExtendVert.setTargetPosition(end);

                while (opModeIsActive() && minerals.mExtendVert.isBusy()) {

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

                    minerals.mExtendHoriz.setPower(gamepad2.right_stick_y);

                }

                minerals.mExtendVert.setPower(-.2);
                minerals.mExtendHoriz.setPower(0);

                minerals.isEncoderModeVert(false);







            }

        }
    }
}
