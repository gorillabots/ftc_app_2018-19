package org.firstinspires.ftc.teamcode.tests.encoder;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousOpMode;

/**
 * Created by xiax on 4/23/2018.
 */

@TeleOp(name = "Vert Extension Using Motor", group = "test")
public class EncoderStuffVert extends AutonomousOpMode {

    @Override
    public void runOpMode() {

        initializeAutonomous();

        minerals.isEncoderModeVert(false);

        waitForStart();
        int add = 0;
        boolean addWatch = false;

        boolean run = false;
        boolean runWatch = false;

        while (opModeIsActive()) {

            minerals.mExtendVert.setPower(-gamepad1.right_stick_y);


        }
    }

}
