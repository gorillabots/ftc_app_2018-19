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
    ElapsedTime timer;
    //Servos servo;

    DcMotor mExtend;
    DcMotor mPivot1;
    DcMotor mPivot2;
    CRServo sBin;
    Servo sCollect;

    @Override
    public void runOpMode() {

        drive = new OldHolonomicDrivebase(hardwareMap, telemetry);

        hang = new Hanging(hardwareMap, telemetry);

        //servo = new Servos(hardwareMap, telemetry);

        mExtend = hardwareMap.get(DcMotor.class, "mExtend");

        mPivot1 = hardwareMap.get(DcMotor.class, "mPivotAndy");
        mPivot1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        mPivot2 = hardwareMap.get(DcMotor.class, "mPivotRev");
        mPivot2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        sBin = hardwareMap.get(CRServo.class, "sCan");

        sCollect = hardwareMap.get(Servo.class, "sCollect");

        waitForStart();

        boolean isSlow = false;
        boolean slowWatch = false;

        boolean isInitialMovement = true;
        boolean initialMovementWatch = false;

        boolean isSmartMovement = false;
        boolean smartMovementWatch = false;

        boolean hasItMoved = false;
        int holdingPosition;

        boolean isCanCollecting = false;
        boolean canCollectingWatch = false;

        double revPivotPower;

        //holdingPosition = mPivotRev.getCurrentPosition();

        while (opModeIsActive()) {

            //toggles
            if (gamepad1.x && !slowWatch) {
                isSlow = !isSlow;
            }
            slowWatch = gamepad1.x;
            telemetry.addData("slow?", isSlow);

            if (gamepad2.a && !initialMovementWatch) {
                isInitialMovement = !isInitialMovement;
            }
            initialMovementWatch = gamepad2.a;
            telemetry.addData("initial arm movement?", isInitialMovement);

            if (gamepad2.x && !smartMovementWatch) {
                isSmartMovement = !isSmartMovement;
            }
            smartMovementWatch = gamepad2.x;
            telemetry.addData("smart movement?", isSmartMovement);

            if (gamepad2.right_bumper && !canCollectingWatch) {
                isCanCollecting = !isCanCollecting;
            }
            canCollectingWatch = gamepad2.right_bumper;
            telemetry.addData("collect?", isCanCollecting);

            //drive
            double x1 = Math.copySign(Math.pow(gamepad1.left_stick_x, 1), -gamepad1.left_stick_x);
            double y1 = Math.copySign(Math.pow(gamepad1.left_stick_y, 1), gamepad1.left_stick_y);
            double x2 = Math.copySign(Math.pow(gamepad1.right_stick_x, 1), -gamepad1.right_stick_x);

            if (isSlow) {
                drive.driveArcade(-x1, -y1, x2, 2);
            } else {
                drive.driveArcade(-x1, -y1, x2, 1);
            }

            //hang
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

            //anderson arm

            if (gamepad2.right_trigger > .75) {
                //mPivotRev.setPower(1);

                hasItMoved = true;
            }

            if (isInitialMovement) { //gamepad2.a

                //mPivotRev.setPower(0); //let it hang down

                //mPivot1.setPower(-gamepad2.left_stick_y);

                //servo.initializeServos();

            } else if (isSmartMovement) { //gamepad2.x

                //mPivot1.setPower(gamepad2.left_stick_y);

                if (gamepad2.right_stick_y != 0) {

                    //mPivotRev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

                    //mPivotRev.setPower(gamepad2.right_stick_y * .75);

                    hasItMoved = true;

                } else { //in holding

                    //mPivotRev.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    //mPivotRev.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    if (hasItMoved) {
                        //holdingPosition = mPivotRev.getCurrentPosition();
                    }

                    hasItMoved = false;

                    //mPivotRev.setPower(1);

                    //mPivotRev.setTargetPosition(holdingPosition);

                }

            } else { //control via both joysticks
              /*  mPivotAndy.setPower(gamepad2.left_stick_y);

                if (gamepad2.right_stick_y != 0) {

                    if (gamepad2.right_stick_y > 0) {
                        revPivotPower = gamepad2.right_stick_y * .1; //going down is positive
                    } else {
                        revPivotPower = gamepad2.right_stick_y * .9; //going up is negative
                    }
                    mPivotRev.setPower(revPivotPower);
                } else {
                    mPivotRev.setPower(-.3);
                } */
            }


            //collection

            if (!isInitialMovement) {
                //servo.setCanPosition(isCanCollecting);
            }

            //extension
            if (gamepad2.right_bumper) {
                mExtend.setPower(-.5);
            } else if (gamepad2.left_bumper) {
                mExtend.setPower(.5);
            } else {
                mExtend.setPower(0);
            }

            mPivot1.setPower(gamepad2.left_stick_y);

            mPivot2.setPower(gamepad2.right_stick_y / 4);

            double triggers = gamepad2.right_trigger - gamepad2.left_trigger;

            sBin.setPower(-triggers / 2 + 0.5);

            if(gamepad2.dpad_up)
            {
                sCollect.setPosition(1);
            }
            else if(gamepad2.dpad_down)
            {
                sCollect.setPosition(0);
            }
            else
            {
                sCollect.setPosition(0.5);
            }

            sleep(20);

        }
    }
}
