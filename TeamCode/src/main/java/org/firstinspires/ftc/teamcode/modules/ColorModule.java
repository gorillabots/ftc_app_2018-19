package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.AutonomousOpMode;

public class ColorModule
{
    ColorSensor color;

    public ColorModule(HardwareMap hardwareMap)
    {
        color = hardwareMap.get(ColorSensor.class, "color");
    }

    public Abstractions.ControlInterface goUntilRed = new Abstractions.ControlInterface()
    {
        @Override
        public void init()
        {

        }

        @Override
        public double fn(Object[] args)
        {
            double power = (double) args[0];

            if(color.red() > 1000)
            {
                return 0;
            }
            else
            {
                return power;
            }
        }
    };

    public Abstractions.ControlInterface goUntilBlue = new Abstractions.ControlInterface()
    {
        @Override
        public void init()
        {

        }

        @Override
        public double fn(Object[] args)
        {
            double power = (double) args[0];

            if(color.blue() > 1000)
            {
                return 0;
            }
            else
            {
                return power;
            }
        }
    };
}
