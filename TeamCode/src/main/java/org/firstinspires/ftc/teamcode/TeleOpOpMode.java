package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.old.OldHolonomicDrivebase;
import org.firstinspires.ftc.teamcode.subsystems.Hanging;
import org.firstinspires.ftc.teamcode.subsystems.MineralCollectionMechanism;
import org.firstinspires.ftc.teamcode.subsystems.Sensors;
import org.firstinspires.ftc.teamcode.subsystems.Servos;

import static java.lang.Math.abs;

public abstract class TeleOpOpMode extends LinearOpMode {

    public Hanging hang;
    public Servos servos;
    public MineralCollectionMechanism minerals;
    public OldHolonomicDrivebase drive;
    public Sensors sensors;
    public ElapsedTime timer;

    public static final int ENCODER_TO_DEPOSITUP = 3675;
    public static final int ENCODER_TO_DEPOSITUP_MORE = 3800;

    public static final int ENCODER_HORIZ_CRATERWALL = 0;
    public static final int ENCODER_HORIZ_FOUR_IN = 0; //interval


    public void initializeTeleop() {

        servos = new Servos(hardwareMap, telemetry);
        minerals = new MineralCollectionMechanism(hardwareMap, telemetry);
        drive = new OldHolonomicDrivebase(hardwareMap, telemetry);
        hang = new Hanging(hardwareMap, telemetry);
        sensors = new Sensors(hardwareMap, telemetry);
        timer = new ElapsedTime();
        timer.seconds();

        servos.initializeServos();

    }

    public void setVertExtention(boolean up) {

        if (up) {
            minerals.isEncoderModeVert(false);
            minerals.isEncoderModeVert(true);

            int start = minerals.mExtendVert.getCurrentPosition();
            int end = start - ENCODER_TO_DEPOSITUP;

            minerals.mExtendVert.setPower(-1);

            minerals.mExtendVert.setTargetPosition(end);

            while (minerals.mExtendVert.isBusy() && opModeIsActive()) {

            }
            minerals.mExtendVert.setPower(0);
            minerals.isEncoderModeVert(false);
        } else {
            minerals.isEncoderModeVert(false);
            minerals.isEncoderModeVert(true);

            int start = minerals.mExtendVert.getCurrentPosition();
            int end = start + ENCODER_TO_DEPOSITUP;

            minerals.mExtendVert.setPower(1);

            minerals.mExtendVert.setTargetPosition(end);

            while (minerals.mExtendVert.isBusy() && opModeIsActive()) {

            }
            minerals.mExtendVert.setPower(0);
            minerals.isEncoderModeVert(false);
        }
    }

    public void setHorizontalPos(int encoderCounts){
        minerals.isEncoderModeHoriz(false);
        minerals.isEncoderModeHoriz(true);

        int start = minerals.mExtendHoriz.getCurrentPosition();
        int end = start - encoderCounts;

        minerals.mExtendHoriz.setPower(-1);

        minerals.mExtendHoriz.setTargetPosition(end);

        while (minerals.mExtendHoriz.isBusy() && opModeIsActive()) {

        }
        minerals.mExtendHoriz.setPower(0);
        minerals.isEncoderModeHoriz(false);
    }

    //---------FULL AUTONOMOUS---------
    public void setToScoringPosition(int encoderHoriz){
        setHorizontalPos(ENCODER_HORIZ_CRATERWALL);
        servos.setCollectionCollect(true);
        setVertExtention(true);
        servos.setDepositDump(true);
        servos.setBackstopColOpen(false);
        minerals.mCollect.setPower(1);
        setHorizontalPos(encoderHoriz);
        servos.setDepositDump(false);
    }
    public void setToInterNalPosition(int encoderHorizBack){
        servos.setCollectionCollect(false);
        setVertExtention(false);
        setHorizontalPos(encoderHorizBack);
        minerals.mCollect.setPower(-1);
        servos.setBackstopColOpen(true);
    }
    // --------------- FULL AUTONOMOUS ----------------
    // --------------- PARTIAL AUTONOMOUS--------------
    public void setDriverInternalHelp(){

        servos.setCollectionCollect(false);
        servos.setBackstopColOpen(true);
        minerals.mCollect.setPower(-1);
        sleep(400);
        setVertExtention(true);
        minerals.mCollect.setPower(0);

    }
}
