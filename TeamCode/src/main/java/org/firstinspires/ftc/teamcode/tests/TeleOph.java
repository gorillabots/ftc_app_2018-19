package org.firstinspires.ftc.teamcode.tests;


import org.firstinspires.ftc.teamcode.Teleop.TeleOpOpMode;
import org.firstinspires.ftc.teamcode.subsystems.Hanging;

/**
 * Created by xiax on 4/23/2018.
 */

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Teleohp", group = "yes")
public class TeleOph extends TeleOpOpMode {


    public Hanging hang;
    @Override
    public void runOpMode() {


        hang = new Hanging(hardwareMap,telemetry);

        waitForStart();

        boolean isSlow = false;
        boolean slowWatch = false;

        boolean isDriveOpposite = false;
        boolean driveOppositeWatch = false;

        boolean isCollectionRotDown = false;
        boolean CollectionRotWatch = false;

        boolean isDepositRotDumping = false;
        boolean DepositWatch = false;

        boolean isAutonomous = false;
        boolean AutonomousWatch = false;

        while (opModeIsActive()) {
            //toggles
            if (gamepad1.x && !slowWatch) {
                isSlow = !isSlow;
            }
            slowWatch = gamepad1.x;
            telemetry.addData("slow?", isSlow);

            if (gamepad1.y && !driveOppositeWatch) {
                isDriveOpposite = !isDriveOpposite;
            }
            driveOppositeWatch = gamepad1.y;
            telemetry.addData("isDriveOpposite?", isDriveOpposite);

            if (gamepad2.y && !AutonomousWatch) {
                isAutonomous = !isAutonomous;
            }
            AutonomousWatch = gamepad1.y;
            telemetry.addData("isAutonomous?", isAutonomous);

            if (gamepad2.left_trigger > .5 && !CollectionRotWatch) {
                isCollectionRotDown = !isCollectionRotDown;
            }
            CollectionRotWatch = gamepad2.left_trigger > .5;
            telemetry.addData("isCollectionRotDown?", isCollectionRotDown);


            if (gamepad2.left_bumper && !DepositWatch) {
                isDepositRotDumping = !isDepositRotDumping;
            }
            DepositWatch = gamepad2.left_bumper;
            telemetry.addData("isCollectionRotDown?", isCollectionRotDown);

                //hang
                if (gamepad1.dpad_up && isSlow) {
                    hang.setHangingPower(.5);
                } else if (gamepad1.dpad_down && isSlow) {
                    hang.setHangingPower(-.5);
                } else if (gamepad1.dpad_up) {
                    hang.setHangingPower(1);
                } else if (gamepad1.dpad_down) {
                    hang.setHangingPower(-1);
                } else {
                    hang.setHangingPower(.0);
                }

            sleep(20);
        }
    }

}
