package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.AutonomousOpMode;

import static org.firstinspires.ftc.teamcode.subsystems.Servos.COLLECTION_INIT;


@TeleOp(name = "servoTestCollectRot", group = "a")
public class ServoTest2 extends AutonomousOpMode {

    Servo sCollectionRot;
    Servo sDepositRot;
    Servo sTeamMarkerRot;

    @Override
    public void runOpMode() {


        sCollectionRot = hardwareMap.servo.get("sCollectionRot");
        sDepositRot = hardwareMap.servo.get("sDepositRot");
        sTeamMarkerRot = hardwareMap.servo.get("sTeamMarkerRot");

        double position;

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.a) {
                sCollectionRot.setPosition(0);
            }
            if (gamepad1.b) {
                sCollectionRot.setPosition(0.5);
            }
            if (gamepad1.y) {
                sCollectionRot.setPosition(1);
            }
            if (gamepad1.x) {
                sCollectionRot.setPosition(.25);
            }
            if (gamepad1.dpad_up) {
                sCollectionRot.setPosition(.75);
            }
            if (gamepad1.dpad_left) {
                sCollectionRot.setPosition(.13);
            }
            if (gamepad1.dpad_down) {
                sCollectionRot.setPosition(.38);
            }
            if (gamepad1.dpad_right) {
                sCollectionRot.setPosition(.63);
            }
            if (gamepad1.right_bumper) {
                sCollectionRot.setPosition(.88);
            }
            if (gamepad1.right_trigger > .5) {
                sCollectionRot.setPosition(.06);
            }
            if (gamepad1.left_bumper) {
                sCollectionRot.setPosition(.19);
            }
            if (gamepad1.left_trigger > .5) {
                sCollectionRot.setPosition(.31);
            }

            if (gamepad2.a) {
                sCollectionRot.setPosition(.44);
            }
            if (gamepad2.b) {
                sCollectionRot.setPosition(.56);
            }
            if (gamepad2.y) {
                sCollectionRot.setPosition(.69);
            }
            if (gamepad2.x) {
                sCollectionRot.setPosition(.81);
            }
            if (gamepad2.dpad_up) {
                sCollectionRot.setPosition(.94);
            }
            if (gamepad2.dpad_left) {
                sCollectionRot.setPosition(.16);
            }
            if (gamepad2.dpad_down) {
                sCollectionRot.setPosition(.17);
            }
            if (gamepad2.dpad_right) {
                sCollectionRot.setPosition(.18);
            }
            if (gamepad2.right_bumper) {
                sCollectionRot.setPosition(.19);
            }
            if (gamepad2.right_trigger > .5) {
                sCollectionRot .setPosition(.20);
            }
            if (gamepad2.left_bumper) {
                sCollectionRot.setPosition(.21);
            }
            if (gamepad2.left_trigger > .5) {
                sCollectionRot.setPosition(COLLECTION_INIT);
            }
            position = sCollectionRot.getPosition();

            telemetry.addData("position", position);
            telemetry.update();
        }


    }
}
