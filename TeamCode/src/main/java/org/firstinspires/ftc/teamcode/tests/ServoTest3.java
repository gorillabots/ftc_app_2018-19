package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousOpMode;
import org.firstinspires.ftc.teamcode.subsystems.Servos;

import static org.firstinspires.ftc.teamcode.subsystems.Servos.DEPOSIT_INIT;


@TeleOp(name = "servoTestDepositRot", group = "a")
public class ServoTest3 extends AutonomousOpMode {


    Servo sCollectionRot;
    Servo sDepositRot;
    Servo sTeamMarkerRot;
    Servos servos;

    @Override
    public void runOpMode() { //.64 around test
        //.13 deposit position

        sCollectionRot = hardwareMap.servo.get("sCollectionRot");
        sDepositRot = hardwareMap.servo.get("sDepositRot");
        sTeamMarkerRot = hardwareMap.servo.get("sTeamMarkerRot");
        servos = new Servos(hardwareMap, telemetry);


        double position;
        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.a) {
                sDepositRot.setPosition(0);
            }
            if (gamepad1.b) {
                sDepositRot.setPosition(0.5); //0.5 going down
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
                sDepositRot.setPosition(.13); //.13 deposit angle
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
                sDepositRot.setPosition(1);
            }
            if (gamepad1.left_trigger > .5) {
                sDepositRot.setPosition(.60);
            }



            if (gamepad2.a) {
                sDepositRot.setPosition(.44); //.46 init
            }
            if (gamepad2.b) {
                sDepositRot.setPosition(.56);
            }
            if (gamepad2.y) {
                sDepositRot.setPosition(.61);
            }
            if (gamepad2.x) {
                sDepositRot.setPosition(.62);
            }
            if (gamepad2.dpad_up) {
                sDepositRot.setPosition(.63);
            }
            if (gamepad2.dpad_left) {
                sDepositRot.setPosition(.64); //collection
            }
            if (gamepad2.dpad_down) {
                sDepositRot.setPosition(.54);
            }
            if (gamepad2.dpad_right) {
                sDepositRot.setPosition(.55);
            }
            if (gamepad2.right_bumper) {
                servos.sDepositRot.setPosition(.22);
            }
            if (gamepad2.right_trigger > .5) {
                servos.setDepositDump(true);
            }
            if (gamepad2.left_bumper) {
                servos.setDepositDump(false);
            }
            if (gamepad2.left_trigger > .5) {
                servos.sDepositRot.setPosition(DEPOSIT_INIT);
            }

            position = sDepositRot.getPosition();

            telemetry.addData("position", position);
            telemetry.update();
        }


    }
}
