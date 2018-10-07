package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutonomousOpMode;


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
            telemetry.addData("B", color.blue());
            telemetry.addData("R", color.red());
            telemetry.addData("G", color.green());
            telemetry.addData("alpha", color.alpha());
            telemetry.addData("ARGB", color.argb());
            telemetry.addData("isRedGround" , isRedGround());
            telemetry.addData("isBlueGround", isBlueGround());
            telemetry.update();

        }
    }
}
