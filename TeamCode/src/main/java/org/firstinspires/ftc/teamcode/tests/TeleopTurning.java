package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.AutonomousOpMode;


@TeleOp(name = "servoTestTBackstopDep", group = "a")
public class TeleopTurning extends AutonomousOpMode {

    Servo sBackstopDep;

    @Override
    public void runOpMode() {
initializeAutonomous();

        double position;

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.a) {
                sBackstopDep.setPosition(0); //release position .0 init / open
            }
            if (gamepad1.b) {
               dumpTeamMarker();
            }
            if (gamepad1.y) {
                raiseTeamMarker();
            }
            if (gamepad1.x) {
                sBackstopDep.setPosition(.25);
            }
            if (gamepad1.dpad_up) {
                sBackstopDep.setPosition(.75);
            }
            if (gamepad1.dpad_left) {
                sBackstopDep.setPosition(.13);
            }
            if (gamepad1.dpad_down) {
                sBackstopDep.setPosition(.38);
            }
            if (gamepad1.dpad_right) {
              sBackstopDep.setPosition(.63);
            }
            if (gamepad1.right_bumper) {
                sBackstopDep.setPosition(.88);
            }
            if (gamepad1.right_trigger > .5) {
                sBackstopDep.setPosition(.06);
            }
            if (gamepad1.left_bumper) {
                sBackstopDep.setPosition(.19);
            }
            if (gamepad1.left_trigger > .5) {
                sBackstopDep.setPosition(.31); //.31 closed
            }

            if (gamepad2.a) {
                sBackstopDep.setPosition(.44);
            }
            if (gamepad2.b) {
                sBackstopDep.setPosition(.56);
            }
            if (gamepad2.y) {
                sBackstopDep.setPosition(.69); //.69 dump
            }
            if (gamepad2.x) {
                sBackstopDep.setPosition(.81);
            }
            if (gamepad2.dpad_up) {
                sBackstopDep.setPosition(.94);
            }
            if (gamepad2.dpad_left) {
                sBackstopDep.setPosition(.3);
            }
            if (gamepad2.dpad_down) {
                sBackstopDep.setPosition(.29);
            }
            if (gamepad2.dpad_right) {
                sBackstopDep.setPosition(.28);
            }
            if (gamepad2.right_bumper) {
                sBackstopDep.setPosition(.27);
            }
            if (gamepad2.right_trigger > .5) {
                sBackstopDep.setPosition(.20);
            }
            if (gamepad2.left_bumper) {
                sBackstopDep.setPosition(.21);
            }
            if (gamepad2.left_trigger > .5) {
                sBackstopDep.setPosition(.22);
            }

            position = sBackstopDep.getPosition();

            telemetry.addData("position", position);
            telemetry.update();
        }
    }
}
