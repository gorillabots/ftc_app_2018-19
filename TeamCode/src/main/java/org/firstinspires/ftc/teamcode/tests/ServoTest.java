package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.AutonomousOpMode;
import org.firstinspires.ftc.teamcode.subsystems.Servos;


@TeleOp(name = "servoTest", group = "a")
public class ServoTest extends AutonomousOpMode {

  //   Servos servo;
    public Servo sCan;


    @Override
    public void runOpMode() {
        //  servo = new Servos(hardwareMap);
        sCan = hardwareMap.servo.get("sCan ");
     //   servo = new Servos(hardwareMap,telemetry);
initializeAutonomous();
        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.a) {
                sCan.setPosition(0);// init
            }
            if (gamepad1.b) {
                sCan.setPosition(0.1); //dump
            }
            if (gamepad1.y) {
                sCan.setPosition(.05); //.06 dumpppppppppppp
            }
            if (gamepad1.x) {
                sCan.setPosition(.03); //.03 collecttttttttttttt
            }
            if (gamepad1.dpad_up) {
        //        servo.setCanPosition(true);
            }
            if (gamepad1.dpad_left) {
          //      servo.initializeServos();
            }
            if (gamepad1.dpad_down) {
            //    servo.moveCollector(true, false);
            }
            if (gamepad1.dpad_right) {
                dumpTeamMarker();
            }
            if (gamepad1.right_bumper) {
                sCan.setPosition(.07);
            }
            if (gamepad1.right_trigger > .5) {
                sCan.setPosition(.08);
            }
            if (gamepad1.left_bumper) {
                sCan.setPosition(.09);
            }
            if (gamepad1.left_trigger > .5) {
                sCan.setPosition(.10);
            }


            if (gamepad2.a) {
                sCan.setPosition(.11); //halfway up
            }
            if (gamepad2.b) {
                sCan.setPosition(.12); //auto init .48
            }
            if (gamepad2.y) {
                sCan.setPosition(.13);
            }
            if (gamepad2.x) {
                sCan.setPosition(.14);
            }
            if (gamepad2.dpad_up) {
                sCan.setPosition(.15);
            }
            if (gamepad2.dpad_left) {
                sCan.setPosition(.16);
            }
            if (gamepad2.dpad_down) {
                sCan.setPosition(.17);
            }
            if (gamepad2.dpad_right) {
                sCan.setPosition(.18);
            }
            if (gamepad2.right_bumper) {
                sCan.setPosition(.19);
            }
            if (gamepad2.right_trigger > .5) {
                sCan.setPosition(.20);
            }
            if (gamepad2.left_bumper) {
                sCan.setPosition(.21);
            }
            if (gamepad2.left_trigger > .5) {
                sCan.setPosition(.22); //.58
            }
        }


    }
}
