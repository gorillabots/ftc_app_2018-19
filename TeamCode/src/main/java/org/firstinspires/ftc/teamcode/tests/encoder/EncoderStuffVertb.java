package org.firstinspires.ftc.teamcode.tests.encoder;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousOpMode;

/**
 * Created by xiax on 4/23/2018.
 */

@TeleOp(name = "Encoder Test Vert b", group = "test")
public class EncoderStuffVertb extends AutonomousOpMode {

    @Override
    public void runOpMode() {

        initializeAutonomous();

        waitForStart();
        int add = 0;
        boolean addWatch = false;

        boolean run = false;
        boolean runWatch = false;

        while (opModeIsActive()) {

            if (gamepad1.a && !addWatch) {
                add = add + 75;
            }
            addWatch = gamepad1.a;

            telemetry.addData("fast?", add);

            if (gamepad1.y && !runWatch) {

                minerals.isEncoderModeVert(false);
                minerals.isEncoderModeVert(true);

                int start = minerals.mExtendVert.getCurrentPosition();
                int end = start - add;

                minerals.mExtendVert.setPower(-1);

                minerals.mExtendVert.setTargetPosition(end);

                while (minerals.mExtendVert.isBusy() && opModeIsActive()) {

                }
              //  minerals.mExtendVert.setPower(0);
             //   minerals.isEncoderModeVert(false);

            }

            runWatch = gamepad1.y;
            if (gamepad1.b) {
                setVertExtensionDown();
            }
            telemetry.update();


        }
    }

}
