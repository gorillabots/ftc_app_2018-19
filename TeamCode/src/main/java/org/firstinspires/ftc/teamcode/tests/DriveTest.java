package org.firstinspires.ftc.teamcode.tests;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.AutonomousOpMode;
import org.firstinspires.ftc.teamcode.old.OldHolonomicDrivebase;
import org.firstinspires.ftc.teamcode.subsystems.Hanging;

/**
 * Created by xiax on 4/23/2018.
 */

@TeleOp(name = "Drive Test", group = "test")
public class DriveTest extends AutonomousOpMode {

    @Override
    public void runOpMode() {
    initializeAutonomous();
        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.a) {
                MoveUntilEncoder(10, 0, .5);
            }
            if (gamepad1.b) {
                MoveUntilEncoder(15, 0, .5);
            }
            if (gamepad1.y) {
                MoveUntilEncoder(20, 0, .5);
            }
            if (gamepad1.x) {
                MoveUntilEncoder(25, 0, .5);
            }
            if (gamepad2.a) {
                MoveUntilEncoder(30, 0, .5);
            }
            if (gamepad1.b) {
                MoveUntilEncoder(35, 0, .5);
            }

        }
    }

}
