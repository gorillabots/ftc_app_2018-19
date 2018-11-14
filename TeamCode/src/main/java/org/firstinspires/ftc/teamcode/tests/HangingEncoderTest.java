package org.firstinspires.ftc.teamcode.tests;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.AutonomousOpMode;
import org.firstinspires.ftc.teamcode.subsystems.Hanging;

/**
 * Created by xiax on 4/23/2018.
 */

@TeleOp(name = "Hang Encoder Test", group = "test")
public class HangingEncoderTest extends AutonomousOpMode {

    Hanging hang;

    @Override
    public void runOpMode() {

        hang = new Hanging(hardwareMap, telemetry);

        waitForStart();

        hang.isEncoderMode(true);

        while (opModeIsActive()) {
            if (gamepad1.right_bumper) {

                int original = hang.mHang.getCurrentPosition();
                int start = hang.mHang.getCurrentPosition();
                int end = start - 25;

                hang.setHangingPower(-.5);

                hang.mHang.setTargetPosition(end);

                while (hang.mHang.isBusy() && opModeIsActive()) {
                    telemetry.addData("orig", original);
                    telemetry.addData("end", end);
                    telemetry.addData("difference", end - original);
                    telemetry.update();
                }
                hang.setHangingPower(0);
            }
            if (gamepad1.left_bumper) {

                int original = hang.mHang.getCurrentPosition();
                int start = hang.mHang.getCurrentPosition();
                int end = start - 1;

                hang.setHangingPower(-.5);

                hang.mHang.setTargetPosition(end);

                while (hang.mHang.isBusy() && opModeIsActive()) {
                    telemetry.addData("orig", original);
                    telemetry.addData("end", end);
                    telemetry.addData("difference", end - original);
                    telemetry.update();
                }
                hang.setHangingPower(0);
            }


        }

    }
}
