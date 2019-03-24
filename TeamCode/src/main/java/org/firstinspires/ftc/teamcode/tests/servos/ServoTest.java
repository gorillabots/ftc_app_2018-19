package org.firstinspires.ftc.teamcode.tests.servos;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousOpMode;


@TeleOp(name = "servoTestBackstop", group = "a")
public class ServoTest extends AutonomousOpMode {

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

        double position;

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.a) {
                sBackstop.setPosition(0);
            }
            if (gamepad1.b) {
                sBackstop.setPosition(0.5);
            }
            if (gamepad1.y) {
                sBackstop.setPosition(1);
            }
            if (gamepad1.x) {
                sBackstop.setPosition(.25);
            }
            if (gamepad1.dpad_up) {
                sBackstop.setPosition(.75);
            }
            if (gamepad1.dpad_left) {
                sBackstop.setPosition(.13);
            }
            if (gamepad1.dpad_down) {
                sBackstop.setPosition(.38);
            }
            if (gamepad1.dpad_right) {
              sBackstop.setPosition(.63);
            }
            if (gamepad1.right_bumper) {
                sBackstop.setPosition(.88);
            }
            if (gamepad1.right_trigger > .5) {
                sBackstop.setPosition(.06);
            }
            if (gamepad1.left_bumper) {
                sBackstop.setPosition(.19);
            }
            if (gamepad1.left_trigger > .5) {
                sBackstop.setPosition(.31);
            }

            if (gamepad2.a) {
                sBackstop.setPosition(.44);
            }
            if (gamepad2.b) {
                sBackstop.setPosition(.56);
            }
            if (gamepad2.y) {
                sBackstop.setPosition(.69);
            }
            if (gamepad2.x) {
                sBackstop.setPosition(.81);
            }
            if (gamepad2.dpad_up) {
                sBackstop.setPosition(.94);
            }
            if (gamepad2.dpad_left) {
                sBackstop.setPosition(.16);
            }
            if (gamepad2.dpad_down) {
                sBackstop.setPosition(.17);
            }
            if (gamepad2.dpad_right) {
                sBackstop.setPosition(.18);
            }
            if (gamepad2.right_bumper) {
                sBackstop.setPosition(.19);
            }
            if (gamepad2.right_trigger > .5) {
                sBackstop.setPosition(.20);
            }
            if (gamepad2.left_bumper) {
                sBackstop.setPosition(.21);
            }
            if (gamepad2.left_trigger > .5) {
                sBackstop.setPosition(.22);
            }

            position = sBackstop.getPosition();

            telemetry.addData("position", position);
            telemetry.update();
        }


    }
}
