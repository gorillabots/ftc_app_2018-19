package org.firstinspires.ftc.teamcode.Autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutonomousOpMode;

/**
 * Created by xiax on 4/23/2018.
 */

@Autonomous(name = "Blue Crater", group = "Autonomous")
public class BlueCrater extends AutonomousOpMode
{

    @Override
    public void runOpMode() {

        initializeAutonomous();

        waitForStart();

        moveUntil(colors.goUntilRed, 0, 1);
    }
}