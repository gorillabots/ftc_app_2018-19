package org.firstinspires.ftc.teamcode.Autonomous.CompetitionAutonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.CraterAutos;

/**
 * Created by xiax on 4/23/2018.
 */

@Autonomous(name = "CRATER", group = "Autonomous")
public class CRATER_UTICA extends CraterAutos {

    @Override
    public void runOpMode() {

        initializeAutonomous();

        int yellow = detectYellowTensor();

        unHangWithEncoder();

        switch(yellow) {
            case 1:
                scoreLeftCrater();
            case 2:
                scoreMiddleCrater();
            case 3:
                scoreRightCrater();
        }


        sleep(10000);
    }
}
