package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.AutonomousOpMode;


@TeleOp(name = "servoTestTeamMarkerRot", group = "a")
public class ServoTest4 extends AutonomousOpMode {

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
                sTeamMarkerRot.setPosition(0);
            }
            if (gamepad1.b) {
                sTeamMarkerRot.setPosition(0.5);
            }
            if (gamepad1.y) {
                sTeamMarkerRot.setPosition(1);
            }
            if (gamepad1.x) {
                sTeamMarkerRot.setPosition(.25);
            }
            if (gamepad1.dpad_up) {
                sTeamMarkerRot.setPosition(.75);
            }
            if (gamepad1.dpad_left) {
                sTeamMarkerRot.setPosition(.13);
            }
            if (gamepad1.dpad_down) {
                sTeamMarkerRot.setPosition(.38);
            }
            if (gamepad1.dpad_right) {
              sTeamMarkerRot.setPosition(.63);
            }
            if (gamepad1.right_bumper) {
                sTeamMarkerRot.setPosition(.88);
            }
            if (gamepad1.right_trigger > .5) {
                sTeamMarkerRot.setPosition(.06);
            }
            if (gamepad1.left_bumper) {
                sTeamMarkerRot.setPosition(.19);
            }
            if (gamepad1.left_trigger > .5) {
                sTeamMarkerRot.setPosition(.31);
            }

            if (gamepad2.a) {
                sTeamMarkerRot.setPosition(.44);
            }
            if (gamepad2.b) {
                sTeamMarkerRot.setPosition(.56);
            }
            if (gamepad2.y) {
                sTeamMarkerRot.setPosition(.69); //.69 dump
            }
            if (gamepad2.x) {
                sTeamMarkerRot.setPosition(.81);
            }
            if (gamepad2.dpad_up) {
                sTeamMarkerRot.setPosition(.94);
            }
            if (gamepad2.dpad_left) {
                sTeamMarkerRot.setPosition(.12);
            }
            if (gamepad2.dpad_down) {
                sTeamMarkerRot.setPosition(.11);
            }
            if (gamepad2.dpad_right) {
                sTeamMarkerRot.setPosition(.10);
            }
            if (gamepad2.right_bumper) {
                sTeamMarkerRot.setPosition(.19);
            }
            if (gamepad2.right_trigger > .5) {
                sTeamMarkerRot.setPosition(.20);
            }
            if (gamepad2.left_bumper) {
                sTeamMarkerRot.setPosition(.21);
            }
            if (gamepad2.left_trigger > .5) {
                sTeamMarkerRot.setPosition(.22);
            }
        }
    }
}
