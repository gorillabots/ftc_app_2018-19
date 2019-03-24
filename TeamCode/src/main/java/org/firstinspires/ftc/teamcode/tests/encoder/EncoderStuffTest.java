package org.firstinspires.ftc.teamcode.tests.encoder;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousOpMode;

/**
 * Created by xiax on 4/23/2018.
 */

@TeleOp(name = "Encoder Test", group = "test")
public class EncoderStuffTest extends AutonomousOpMode {

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
                add = add + 25;
            }
            addWatch = gamepad1.a;

            telemetry.addData("fast?", add);

            if(gamepad1.y && !runWatch){
                extendHorizToEncoder(add);
            }

            runWatch  =gamepad1.y;
            if (gamepad1.b){
                retractHoriz();
            }
            telemetry.update();

        }
    }

}
