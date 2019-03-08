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

        waitForStart();

        while (opModeIsActive()) {


            sCollectionRot = hardwareMap.servo.get("sCollectionRot");
            sDepositRot = hardwareMap.servo.get("sDepositRot");
            sTeamMarkerRot = hardwareMap.servo.get("sTeamMarkerRot");

            double position = 0.5;

            boolean increase = false;
            boolean increaseWatch = false;

            boolean decrease = false;
            boolean decreaseWatch = false;

            waitForStart();

            sCollectionRot.setPosition(0.5);

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

                sCollectionRot.setPosition(position);


                telemetry.addData("position", position);
                telemetry.update();
            }
        }


    }
}
