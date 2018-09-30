package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


@Autonomous(group="test", name="SensorTesting")
public class SensorTesting extends LinearOpMode
{
    private DistanceSensor distance;
    @Override
    public void runOpMode()
    {
        distance = hardwareMap.get(DistanceSensor.class, "distance");

        waitForStart();
        while(opModeIsActive())
        {
            telemetry.addData("D", distance.getDistance(DistanceUnit.CM));
            telemetry.update();
        }
    }
}
