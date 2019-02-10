package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems2.Collector2;
import org.firstinspires.ftc.teamcode.subsystems2.Drivebase2;
@Disabled
@TeleOp(group="yes", name="Simplified TeleOp")
public class TeleOpSimple extends LinearOpMode
{
    Drivebase2 drive;
    Collector2 collector;

    public void runOpMode()
    {
        drive = new Drivebase2(hardwareMap);
        collector = new Collector2(hardwareMap);

        waitForStart();

        while(opModeIsActive())
        {
            drive.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
        }
    }
}
