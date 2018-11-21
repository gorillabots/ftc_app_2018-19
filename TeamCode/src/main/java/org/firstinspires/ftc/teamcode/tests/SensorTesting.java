package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.AutonomousOpMode;

@Disabled
@Autonomous(group="test", name="SensorTesting")
public class SensorTesting extends AutonomousOpMode
{
    //private DistanceSensor distance;

    @Override
    public void runOpMode()
    {
        initializeAutonomous();

        waitForStart();

        while(opModeIsActive())
        {
         // telemetry.addData("D", distance.getDistance(DistanceUnit.INCH));
            telemetry.addData("B", sensor.color.blue());
            telemetry.addData("R", sensor.color.red());
            telemetry.addData("G", sensor.color.green());
            telemetry.addData("alpha", sensor.color.alpha());
            telemetry.addData("ARGB", sensor.color.argb());
            telemetry.addData("isRedGround" , sensor.isRedGround());
            telemetry.addData("isBlueGround", sensor.isBlueGround());
            telemetry.update();

        }
    }
}
