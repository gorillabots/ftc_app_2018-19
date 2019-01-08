package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.AutonomousOpMode;


@TeleOp(name = "servoTestDepositRot", group = "a")
public class ServoTest3 extends AutonomousOpMode {


Servo sCollectionRot;
Servo sDepositRot;
Servo sTeamMarkerRot;

    @Override
    public void runOpMode() {

        sCollectionRot = hardwareMap.servo.get("sCollectionRot");
        sDepositRot = hardwareMap.servo.get("sDepositRot");
        sTeamMarkerRot = hardwareMap.servo.get("sTeamMarkerRot");

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.a) {
                sDepositRot.setPosition(0);
            }
            if (gamepad1.b) {
                sDepositRot.setPosition(0.5);
            }
            if (gamepad1.y) {
                sDepositRot.setPosition(1);
            }
            if (gamepad1.x) {
                sDepositRot.setPosition(.25);
            }
            if (gamepad1.dpad_up) {
                sDepositRot.setPosition(.75);
            }
            if (gamepad1.dpad_left) {
                sDepositRot.setPosition(.13);
            }
            if (gamepad1.dpad_down) {
                sDepositRot.setPosition(.38);
            }
            if (gamepad1.dpad_right) {
              sDepositRot.setPosition(.63);
            }
            if (gamepad1.right_bumper) {
                sDepositRot.setPosition(.88);
            }
            if (gamepad1.right_trigger > .5) {
                sDepositRot.setPosition(.06);
            }
            if (gamepad1.left_bumper) {
                sDepositRot.setPosition(.19);
            }
            if (gamepad1.left_trigger > .5) {
                sDepositRot.setPosition(.31);
            }

            if (gamepad2.a) {
                sDepositRot.setPosition(.44);
            }
            if (gamepad2.b) {
                sDepositRot.setPosition(.56);
            }
            if (gamepad2.y) {
                sDepositRot.setPosition(.69);
            }
            if (gamepad2.x) {
                sDepositRot.setPosition(.81);
            }
            if (gamepad2.dpad_up) {
                sDepositRot.setPosition(.94);
            }
            if (gamepad2.dpad_left) {
                sDepositRot.setPosition(.16);
            }
            if (gamepad2.dpad_down) {
                sDepositRot.setPosition(.17);
            }
            if (gamepad2.dpad_right) {
                sDepositRot.setPosition(.18);
            }
            if (gamepad2.right_bumper) {
                sDepositRot.setPosition(.19);
            }
            if (gamepad2.right_trigger > .5) {
                sDepositRot.setPosition(.20);
            }
            if (gamepad2.left_bumper) {
                sDepositRot.setPosition(.21);
            }
            if (gamepad2.left_trigger > .5) {
                sDepositRot.setPosition(.22);
            }
        }


    }
}