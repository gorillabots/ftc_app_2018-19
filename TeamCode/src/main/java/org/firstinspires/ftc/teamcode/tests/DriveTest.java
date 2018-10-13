package org.firstinspires.ftc.teamcode.tests;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.AutonomousOpMode;
import org.firstinspires.ftc.teamcode.old.OldHolonomicDrivebase;
import org.firstinspires.ftc.teamcode.subsystems.Hanging;

/**
 * Created by xiax on 4/23/2018.
 */

@TeleOp(name = "Drive Test", group = "test")
public class DriveTest extends AutonomousOpMode {

    OldHolonomicDrivebase drive;
    Hanging hang;

    @Override
    public void runOpMode() {

        drive = new OldHolonomicDrivebase(hardwareMap,telemetry);

//        hang = new Hanging(hardwareMap,telemetry);

        waitForStart();

        boolean isSlow = false;
        boolean slowWatch = false;

        while(opModeIsActive()){
            double x1 = Math.copySign(Math.pow(gamepad1.left_stick_x, 1), -gamepad1.left_stick_x);
            double y1 = Math.copySign(Math.pow(gamepad1.left_stick_y, 1), -gamepad1.left_stick_y);
            double x2 = Math.copySign(Math.pow(gamepad1.right_stick_x, 1), -gamepad1.right_stick_x);

            if (isSlow){
            drive.driveArcade(x1,y1,x2,2);}
            else{
                drive.driveArcade(x1,y1,x2,1);}
            if (gamepad1.a && !slowWatch){
                isSlow = !isSlow;
            }
            slowWatch = gamepad1.a;

/*           if (gamepad1.dpad_up && isSlow){
                hang.setHangingPower(.3);
            }
            else if (gamepad1.dpad_down && isSlow){
                hang.setHangingPower(-.3);
            }
            else if(gamepad1.dpad_up){
                hang.setHangingPower(1);
            }
            else if (gamepad1.dpad_down){
                hang.setHangingPower(-1);
            }
            else{
                hang.setHangingPower(0);
            }
*/
        }
    }
}
