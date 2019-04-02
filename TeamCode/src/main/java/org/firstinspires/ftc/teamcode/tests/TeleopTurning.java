package org.firstinspires.ftc.teamcode.tests;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teleop.TeleOpOpMode;

/**
 * Created by xiax on 4/23/2018.
 */

@TeleOp(name = "TeleopTurning", group = "test")
public class TeleopTurning extends TeleOpOpMode {

    @Override
    public void runOpMode() {

        initializeTeleop();

        waitForStart();

        int operations = 0; //0-a,1-b,2-x,3-y

        /*
        * 0: front left
        * 1: front right
        * 2: back right
        * 3: back left
        * */

        boolean increaseWatch = false;
        boolean decreaseWatch = false;

        double flPower = .5;
        double frPower = .5;
        double brPower = .5;
        double blPower = .5;

        while (opModeIsActive()) {

            if (gamepad1.a) {
                operations = 0;
            } else if (gamepad1.b) {
                operations = 1;
            } else if (gamepad1.x) {
                operations = 2;
            } else if (gamepad1.y) {
                operations = 3;
            } else {

            }

            telemetry.addData("operations",operations);

            if (gamepad1.right_bumper && !increaseWatch) {
                if (operations == 0){
                    flPower = flPower + .1;
                }
                else if (operations == 1){
                    frPower = frPower + .1;
                }
                else if (operations == 2){
                    brPower = brPower + .1;
                }
                else if (operations == 3){
                    blPower = blPower + .1;
                }
                else{}
            }
            increaseWatch = gamepad1.right_bumper;

            if (gamepad1.left_bumper && !decreaseWatch) {

                if (operations == 0){
                    flPower = flPower - .1;
                }
                else if (operations == 1){
                    frPower = frPower - .1;
                }
                else if (operations == 2){
                    brPower = brPower - .1;
                }
                else if (operations == 3){
                    blPower = blPower - .1;
                }
                else{}
            }
            decreaseWatch = gamepad1.left_bumper;

            telemetry.addData("flPower",flPower );
            telemetry.addData("frPower",frPower );
            telemetry.addData("blPower",blPower );
            telemetry.addData("brPower",brPower );

            if (gamepad1.right_stick_y > .5){
                drive.setDrivePowers(flPower,frPower ,blPower ,brPower );
            }
            else{
                drive.setDrivePowers(0,0 ,0 ,0);
            }

            telemetry.update();
        }
    }

}
