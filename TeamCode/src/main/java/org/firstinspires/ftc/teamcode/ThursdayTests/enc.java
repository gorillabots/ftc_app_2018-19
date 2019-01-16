package org.firstinspires.ftc.teamcode.ThursdayTests;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutonomousOpMode;

/**
 * Created by xiax on 4/23/2018.
 */

@Autonomous(name = "enc", group = "Autonomous")
public class enc extends AutonomousOpMode {


    @Override
    public void runOpMode() {

        initializeAutonomous();



        waitForStart();

    extendHorizToEncoder(800);

    }
}
