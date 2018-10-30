package org.firstinspires.ftc.teamcode.Autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutonomousOpMode;

import java.util.Random;

/**
 * Created by xiax on 4/23/2018.
 */

@Autonomous(name = "Blue Depot", group = "Autonomous")
public class BlueDepot extends AutonomousOpMode {

    Random random;

    @Override
    public void runOpMode() {

        initializeAutonomous();

        random = new Random();

        waitForStart(); //detect yellow loop

        int a = random.nextInt(2);

        telemetry.addData("random",a);
        telemetry.update();

        MoveToByEncoder(5, 180, .5);

        if (a == 0)
            Turn(15);
        else if (a == 1)
            Turn(0);
        else if (a == 2)
            Turn(-15);
        else
            Turn(0);

        MoveToByTime(6, 180, .75);

    }
}
