package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(group="Tests", name="TestOpMode")
public class TestOpMode extends LinearOpMode
{
    public void runOpMode()
    {
        telemetry.addData("Status", "Initialized");
        waitForStart();
        telemetry.addData("Status", "Started");
        sleep(1000);
        telemetry.addData("Status", "Done");
    }
}
