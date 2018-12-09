package org.firstinspires.ftc.teamcode.Autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutonomousOpMode;

import java.util.Random;

/**
 * Created by xiax on 4/23/2018.
 */

@Autonomous(name = "Hang Down Only", group = "Autonomous")
public class hang extends AutonomousOpMode {


    @Override
    public void runOpMode() {

        initializeAutonomous();

        waitForStart();

        unHangWithEncoder();


    }
}
