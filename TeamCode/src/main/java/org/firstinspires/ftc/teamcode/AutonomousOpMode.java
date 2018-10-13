package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.modules.Abstractions;
import org.firstinspires.ftc.teamcode.modules.ColorModule;
import org.firstinspires.ftc.teamcode.modules.MecanumDrive;

public abstract class AutonomousOpMode extends LinearOpMode
{
    private MecanumDrive drive;
    protected ColorModule colors;

    public abstract void initialize();
    public abstract void run();

    public void runOpMode()
    {
        initialize();
        waitForStart();
        initializeAutonomous();
        run();
    }

    private void initializeAutonomous()
    {
        drive = new MecanumDrive(hardwareMap);
        colors =  new ColorModule(hardwareMap);
    }

    protected void moveUntil(Abstractions.ControlInterface control, double direction)
    {
        double rads = Math.toRadians(direction);

        double power = control.fn(null);

        while (power != 0 && opModeIsActive())
        {
            double[] cartesian = toCartesian(rads, power);

            drive.mov(cartesian[0], cartesian[1], 0);
            //MoveTo(direction, power);

            sleep(50);

            power = control.fn(null);
        }
        drive.stop();
    }

    private double[] toCartesian(double dir, double pow)
    {
        double[] output = new double[2];

        double sin = Math.sin(dir);
        double cos = Math.cos(dir);

        output[0] = pow * sin;
        output[1] = pow * cos;

        return output;
    }
}
