package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

import com.qualcomm.hardware.rev.

@Autonomous(group="test", name="SensorTesting")
public class SensorTesting extends LinearOpMode
{
    public void runOpMode()
    {
        DistanceSensor sensorRange = hardwareMap.get(DistanceSensor.class, "sensor_range");
        Rev2mDistanceSensor sensorTimeOfFlight = (Rev2mDistanceSensor)sensorRange;
        waitForStart();
        while(opModeIsActive())
        {
            telemetry.addData("Raw", distance.getRawLightDetected());
            telemetry.addData("Normal", distance.getLightDetected());
            telemetry.update();
        }
    }
}
