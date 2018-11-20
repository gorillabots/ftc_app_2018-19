package org.firstinspires.ftc.teamcode.Autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutonomousOpMode;

/**
 * Created by xiax on 4/23/2018.
 */

@Autonomous(name = "Blue Crater", group = "Autonomous")
public class BlueCrater extends AutonomousOpMode {

    @Override
    public void runOpMode() {

        initializeAutonomous();

        int yellowposition = detectYellow(true);

        unHangWithEncoder();

        if (yellowposition == 1) {  //turn into correct sample
            Turn(-15);
        } else if (yellowposition == 2) {

        } else if (yellowposition == 3) {
            Turn(15);
        }

        MoveToByEncoder(10,270,.5);

        MoveToByEncoder(5, 90, .5);

        Turn(-90);

        MoveToByEncoder(15, 90,.5);

        Turn(-45);

        MoveToByTime(3000,0,.5);

        MoveToByEncoder(30,90,1);

        //deposit team marker

        MoveToByEncoder(40,270,1);
    }
}
