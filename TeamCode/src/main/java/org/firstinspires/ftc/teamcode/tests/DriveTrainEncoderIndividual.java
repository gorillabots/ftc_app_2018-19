package org.firstinspires.ftc.teamcode.tests;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousOpMode;

/**
 * Created by xiax on 4/23/2018.
 */

@TeleOp(name = " Drive Encoder Test", group = "test")
public class DriveTrainEncoderIndividual extends AutonomousOpMode {

    @Override
    public void runOpMode() {

        initializeAutonomous();

        waitForStart();

        boolean a = false;
        boolean aWatch = false;

        boolean b = false;
        boolean bWatch = false;

        boolean y = false;
        boolean yWatch = false;

        boolean x = false;
        boolean xWatch = false;


        while (opModeIsActive()) {

            if (gamepad1.a && !aWatch) {
                mbl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                mbl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                mbl.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                int start = mbl.getCurrentPosition();
                int end = start + 1000;

                mbl.setPower(1);

                mbl.setTargetPosition(end);

                while (mbl.isBusy() && opModeIsActive()) {

                }
                mbl.setPower(0);

            }
            gamepad1.a = aWatch;


            if (gamepad1.b && !bWatch) {
                mbr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                mbr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                mbr.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                int start = mbr.getCurrentPosition();
                int end = start + 1000;

                mbr.setPower(1);

                mbr.setTargetPosition(end);

                while (mbr.isBusy() && opModeIsActive()) {

                }
                mbr.setPower(0);
            }
            gamepad1.b = bWatch;


            if (gamepad1.x && !xWatch) {
                mfl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                mfl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                mfl.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                int start = mfl.getCurrentPosition();
                int end = start + 1000;

                mfl.setPower(1);

                mfl.setTargetPosition(end);

                while (mfl.isBusy() && opModeIsActive()) {

                }
                mfl.setPower(0);
            }
            gamepad1.x = xWatch;


            if (gamepad1.y && !yWatch) {
                mfr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                mfr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                mfr.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                int start = mfr.getCurrentPosition();
                int end = start + 1000;

                mfr.setPower(1);

                mfr.setTargetPosition(end);

                while (mfr.isBusy() && opModeIsActive()) {

                }
                mfr.setPower(0);
            }
            gamepad1.y = yWatch;


        }
    }

}
