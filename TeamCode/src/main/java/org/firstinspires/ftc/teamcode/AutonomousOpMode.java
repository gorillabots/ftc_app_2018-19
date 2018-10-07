package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.old.OldAutonomousOpMode;

public abstract class AutonomousOpMode extends OldAutonomousOpMode
{
    protected ColorModule colors;

    public void initializeAutonomous()
    {
        colors =  new ColorModule(hardwareMap);
    }

    public void moveUntil(ColorModule.EndInterface end, double direction, double power)
    {
        while (!end.fn() && opModeIsActive())
        {
            motors.MoveTo(direction, power);
        }
        motors.stopMotors();
    }

}
