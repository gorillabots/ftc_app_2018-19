package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


@Autonomous(group="test", name="SensorTesting")
public class SensorTesting extends LinearOpMode
{
    //private DistanceSensor distance;
    private ColorSensor color;
    @Override
    public void runOpMode()
    {
      //  distance = hardwareMap.get(DistanceSensor.class, "distance");
        color = hardwareMap.get(ColorSensor.class,"color");
        waitForStart();
        while(opModeIsActive())
        {
       //     telemetry.addData("D", distance.getDistance(DistanceUnit.INCH));
            telemetry.addData("B", color.blue());
            telemetry.addData("R", color.red());
            telemetry.addData("G", color.green());
            telemetry.addData("alpha", color.alpha());
            telemetry.addData("ARGB", color.argb());
            telemetry.update();
        }
    }
}
