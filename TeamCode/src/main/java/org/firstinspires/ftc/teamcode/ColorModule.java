package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ColorModule
{
    ColorSensor color;

    ColorModule(HardwareMap hardwareMap)
    {
        color = hardwareMap.get(ColorSensor.class, "color");
    }

    interface EndInterface
    {
        boolean fn();
    }

    public EndInterface goUntilRed = new EndInterface()
    {
        @Override
        public boolean fn()
        {
            return color.red() > 1000;
        }
    };

    public EndInterface goUntilBlue = new EndInterface()
    {
        @Override
        public boolean fn()
        {
            return color.blue() > 1000;
        }
    };
}
