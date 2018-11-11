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

        if (yellowposition == 1) {
            Turn(-15);
        } else if (yellowposition == 2) {

        } else if (yellowposition == 3) {
            Turn(15);
        }

        MoveToByEncoder(10,180,1);
    }
}
