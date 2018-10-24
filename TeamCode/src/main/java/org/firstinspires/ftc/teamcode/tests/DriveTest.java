package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.modules.MecanumDrive;

@TeleOp(group="tests", name="DriveTest")
public class DriveTest extends LinearOpMode
{
    public void runOpMode()
    {
        MecanumDrive drive = new MecanumDrive(hardwareMap, telemetry);
        DcMotor lift = hardwareMap.dcMotor.get("lift");

        waitForStart();

        while(opModeIsActive() && !gamepad1.back)
        {
            drive.move(-gamepad1.left_stick_y, -gamepad1.left_stick_x, gamepad1.right_stick_x);
            if(gamepad1.dpad_up)
            {
                lift.setPower(-0.5);
            }
            else if(gamepad1.dpad_down)
            {
                lift.setPower(0.5);
            }
            else
            {
                lift.setPower(0);
            }
        }

        drive.stop();
        lift.setPower(0);
    }
}
