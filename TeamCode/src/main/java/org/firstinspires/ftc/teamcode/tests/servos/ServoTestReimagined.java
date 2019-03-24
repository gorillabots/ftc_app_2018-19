package org.firstinspires.ftc.teamcode.tests.servos;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousOpMode;


@TeleOp(name = "servoTestReImagined", group = "a")
public class ServoTestReimagined extends AutonomousOpMode {

    Servo sBackstop;
    Servo sCollectionRot;
    Servo sDepositRot;
    Servo sTeamMarkerRot;

    @Override
    public void runOpMode() {

        sBackstop = hardwareMap.servo.get("sBackstopCol");
        sCollectionRot = hardwareMap.servo.get("sCollectionRot");
        sDepositRot = hardwareMap.servo.get("sDepositRot");
        sTeamMarkerRot = hardwareMap.servo.get("sTeamMarkerRot");

        double position = 0.5;

        boolean increase = false;
        boolean increaseWatch = false;

        boolean decrease = false;
        boolean decreaseWatch = false;

        waitForStart();

        sDepositRot.setPosition(0.5);

        while (opModeIsActive()) {

            if (gamepad1.right_bumper && !increaseWatch) {
                position = position + .01;
            }
            increaseWatch = gamepad1.right_bumper;

            if (gamepad1.left_bumper && !decreaseWatch) {
                position = position - .01;
            }
            decreaseWatch = gamepad1.left_bumper;

            if (gamepad1.right_trigger > .5) {
                position = position + .1;
            }
            if (gamepad1.left_trigger > .5) {
                position = position - .1;
            }

            if (position > 1) {
                position = 1;
            }
            if (position < 0) {
                position = 0;
            }

            sDepositRot.setPosition(position);


            telemetry.addData("position", position);
            telemetry.update();
        }


    }
}
