package org.firstinspires.ftc.teamcode.tests.encoder;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.autonomous.AutonomousOpMode;

/**
 * Created by xiax on 4/23/2018.
 */

@TeleOp(name = "Horizontal Encoder Test if Things Are Breaking", group = "test")
public class encoderHardTest extends AutonomousOpMode {

    @Override
    public void runOpMode() {

        initializeAutonomous();

        waitForStart();

        minerals.isEncoderModeHoriz(true);

        minerals.mExtendHoriz.setPower(.4);
        minerals.mExtendHoriz.setTargetPosition(100);
        while (opModeIsActive() && minerals.mExtendHoriz.isBusy()) {
        }
        minerals.mExtendHoriz.setPower(0);
    }

}
