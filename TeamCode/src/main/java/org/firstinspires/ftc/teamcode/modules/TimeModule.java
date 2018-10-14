package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class TimeModule {

    ElapsedTime timer;

    public TimeModule(HardwareMap hardwareMap) {
        timer = new ElapsedTime();
    }

    public Abstractions.ControlInterface goUntilTime = new Abstractions.ControlInterface() {
        @Override
        public double fn(Object[] args) {
            double power = (double) args[0];
            long time = (long) args[1];

            timer.reset();
            timer.startTime();
            if (timer.seconds()>time){
                return 0;
            }
            else{
                return power;
            }

        }




    };
}