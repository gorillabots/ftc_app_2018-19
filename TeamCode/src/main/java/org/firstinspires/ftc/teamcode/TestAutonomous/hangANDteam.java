package org.firstinspires.ftc.teamcode.TestAutonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AutonomousOpMode;

/**
 * Created by xiax on 4/23/2018.
 */

@Autonomous(name = "Hang Down Only and team", group = "Autonomous")
public class hangANDteam extends AutonomousOpMode {


    @Override
    public void runOpMode() {

        initializeAutonomous();

        waitForStart();

        unhangWithExtension();


    }
}
