package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.old.OldHolonomicDrivebase;
import org.firstinspires.ftc.teamcode.subsystems.Hanging;

/**
 * Created by xiax on 4/23/2018.
 */

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Teleop", group = "yes")
public class TeleOp extends AutonomousOpMode {

    OldHolonomicDrivebase drive;
    Hanging hang;
    ElapsedTime timer;

    public DcMotor mExtend;
    public DcMotor mPivotAndy;
    public DcMotor mPivotRev;

    @Override
    public void runOpMode() {

        drive = new OldHolonomicDrivebase(hardwareMap, telemetry);

        hang = new Hanging(hardwareMap, telemetry);

        mExtend = hardwareMap.get(DcMotor.class, "mExtend");

        mPivotAndy = hardwareMap.get(DcMotor.class, "mPivotAndy");
        mPivotAndy.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        mPivotRev = hardwareMap.get(DcMotor.class, "mPivotRev");

        timer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

        waitForStart();

        boolean isSlow = false;
        boolean slowWatch = false;

        double revPivotPower = 0;


        while (opModeIsActive()) {
            double x1 = Math.copySign(Math.pow(gamepad1.left_stick_x, 1), -gamepad1.left_stick_x);
            double y1 = Math.copySign(Math.pow(gamepad1.left_stick_y, 1), gamepad1.left_stick_y);
            double x2 = Math.copySign(Math.pow(gamepad1.right_stick_x, 1), -gamepad1.right_stick_x);

            if (isSlow) {
                drive.driveArcade(x1, y1, x2, 2);
            } else {
                drive.driveArcade(x1, y1, x2, 1);
            }
            if (gamepad1.a && !slowWatch) {
                isSlow = !isSlow;
            }
            slowWatch = gamepad1.a;

            telemetry.addData("slow?", isSlow);

            if (gamepad1.dpad_up && isSlow) {
                hang.setHangingPower(-.3);
            } else if (gamepad1.dpad_down && isSlow) {
                hang.setHangingPower(.3);
            } else if (gamepad1.dpad_up) {
                hang.setHangingPower(-1);
            } else if (gamepad1.dpad_down) {
                hang.setHangingPower(1);
            } else {
                hang.setHangingPower(.0);
            }

            mPivotAndy.setPower(gamepad2.left_stick_y);

            if(gamepad2.right_stick_y != 0){

                if(gamepad2.right_stick_y > 0){
                    revPivotPower = gamepad2.right_stick_y*.1; //going down is positive
                }
                else{
                    revPivotPower = gamepad2.right_stick_y*.75; //going up is negative
                }
                mPivotRev.setPower(revPivotPower);
            }
            else{
                mPivotRev.setPower(-.2);
            }

            telemetry.addData("power" , revPivotPower);
            telemetry.update();

            if (gamepad2.dpad_up) {
                mExtend.setPower(-.75);
            } else if (gamepad2.dpad_down) {
                mExtend.setPower(.75);
            } else {
                mExtend.setPower(0);
            }

            /*switch (revPivotState) {
                case 0:
                    // when the gamepad is released, we switch into the waiting state to let the motor
                    // move to its rough target
                    if (gamepad2.right_stick_y == 0.0) {
                        timer.reset();
                        revPivotState = 1;
                    }
                    break;
                case 1:
                    // after the waiting state has elapsed, set our new encoder target and do a position function ig
                    if (timer.milliseconds() >= 100) {
                        revPivotState = 2;
                        revPivotTarget = mPivotRev.getCurrentPosition();
                    }
                    break;
                case 2:
                    double error = revPivotTarget - mPivotRev.getCurrentPosition();
                    mPivotRev.setPower(Range.clip(error * 0.001, -1, 1));
                    break;
            }*/
        }
    }
}
