package org.firstinspires.ftc.teamcode.tests.encoder;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousOpMode;

/**
 * Created by xiax on 4/23/2018.
 */

@Autonomous(name = "Extend Horizontal to 800", group = "Autonomous")
public class enc extends AutonomousOpMode {


    @Override
    public void runOpMode() {

        initializeAutonomous();



        waitForStart();

    extendHorizToEncoder(800);

    }
}
