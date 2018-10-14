package org.firstinspires.ftc.teamcode.modules;

public class Abstractions
{
    public interface ControlInterface
    {
        double fn(Object[] args);
    }

    public interface DriveInterface
    {
        void move(double fb, double rl, double rot);
        void stop();
    }
}
