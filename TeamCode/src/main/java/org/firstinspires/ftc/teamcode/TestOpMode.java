package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(group="Tests", name="TestOpMode")
public class TestOpMode extends LinearOpMode
{
    public void runOpMode()
    {
        status("Initializing");
        DcMotor m1 = hardwareMap.dcMotor.get("m1");
        status("Initialized");

        waitForStart();

        status("Going");
        m1.setPower(1);
        sleep(5000);
        m1.setPower(0);
        status("Done");
    }

    private void status(String status)
    {
        telemetry.addData("Status", status);
        telemetry.update();
    }
}
