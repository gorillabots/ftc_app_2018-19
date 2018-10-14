package org.firstinspires.ftc.teamcode.Autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutonomousOpMode;

/**
 * Created by xiax on 4/23/2018.
 */

@Autonomous(name = "Blue Depot", group = "Autonomous")
public class BlueDepot extends AutonomousOpMode {

    public void initialize()
    {

    }

    public void run()
    {
        moveUntil(colors.goUntilBlue, 0);
    }
}
