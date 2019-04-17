package org.firstinspires.ftc.teamcode.tests;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teleop.TeleOpOpMode;

/**
 * Created by xiax on 4/23/2018.
 */

@TeleOp(name = "TeleopSlidesDown", group = "test")
public class TeleopSlidesDown extends TeleOpOpMode {

    @Override
    public void runOpMode() {

        initializeTeleop();

        waitForStart();

        boolean a = false;
        boolean aWatch = false;

        int start;
        int end;

        while(opModeIsActive()){

            aWatch = gamepad1.a;

            if (gamepad1.a && aWatch)
            {
                a = !a;
            }

            if (a){

                minerals.isEncoderModeVert(true);

                start = minerals.mExtendVert.getCurrentPosition();
                end = 1100;

                minerals.mExtendVert.setTargetPosition(start - end);//negative is up

                minerals.mExtendVert.setPower(-1);






            }

        }

    }

}
