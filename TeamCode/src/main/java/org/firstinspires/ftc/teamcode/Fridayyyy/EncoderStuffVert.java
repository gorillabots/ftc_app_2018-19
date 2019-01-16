package org.firstinspires.ftc.teamcode.Fridayyyy;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.AutonomousOpMode;

/**
 * Created by xiax on 4/23/2018.
 */

@TeleOp(name = "Encoder Test Vert", group = "test")
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
