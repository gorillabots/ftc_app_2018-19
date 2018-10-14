package org.firstinspires.ftc.teamcode.tests;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutonomousOpMode;

/**
 * Created by xiax on 4/23/2018.
 */

@Autonomous(name = "TestAutonomous", group = "Test")
public class TestAutonomous extends AutonomousOpMode
{
    public void initialize()
    {

    }

    public void run()
    {
        moveUntil(colors.goUntilBlue, 0,.75); //function, direction, power
        moveUntil(time.goUntilTime,0,1,10); //function, direction, power, time (seconds)

}
}
