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
            /*telemetry.addData("B", colors.color.blue());
            telemetry.addData("R", colors.color.red());
            telemetry.addData("G", colors.color.green());
            telemetry.addData("alpha", colors.color.alpha());
            telemetry.addData("ARGB", colors.color.argb());
            telemetry.addData("isRedGround" , isRedGround());
            telemetry.addData("isBlueGround", isBlueGround());
            telemetry.update();*/

        }
    }
}
