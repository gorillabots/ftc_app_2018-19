package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TimeModule
{
    ElapsedTime timer;

    public TimeModule(HardwareMap hardwareMap, Telemetry telemetry)
    {
        timer = new ElapsedTime();
    }

    public Abstractions.ControlInterface goUntilTime = new Abstractions.ControlInterface()
    {
        @Override
        public void init()
        {
            timer.reset();
            timer.startTime();
        }

        @Override
        public double fn(Object[] args)
        {
            double power = (double) args[0];
            long time = (long) args[1];
            
            if (timer.seconds()>time)
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