package org.firstinspires.ftc.teamcode.tests;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousOpMode;

/**
 * Created by xiax on 4/23/2018.
 */

@Autonomous(name = "Unhangwithtouch", group = "aAutonomous")
public class Unhangwithtouch extends AutonomousOpMode {

    @Override
    public void runOpMode() {

        initializeAutonomous();

        int yellow = detectYellowTensor(); //detect where yellow is

        unhangWithTouch(); //detach from lander

        hanging.setHangingPower(-1);
        sleep(500);
        hanging.setHangingPower(0);

    }

}