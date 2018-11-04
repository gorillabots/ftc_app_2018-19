/* Copyright (c) 2018 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcontroller.for_camera_opmodes.LinearOpModeCamera;
import org.firstinspires.ftc.teamcode.Vision.Detector;


@TeleOp(name="Detect from Depot", group ="Concept")
//@Disabled
public class YellowPositionFromCameraDepot extends LinearOpModeCamera {

    private String yellowPosition ="UnKnown";
    @Override public void runOpMode() {

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start tracking");
        telemetry.update();
        Detector.YellowCubePosition tempPosition = Detector.YellowCubePosition.UNKNOWN;
        Detector detector = new Detector(this);
        detector.startCamera();
        waitForStart();
//sleep(5000);

        boolean notSeen = true;

        while (opModeIsActive() && notSeen) {

            tempPosition = detector.computeYellowCubePosition(false);
            if (tempPosition != Detector.YellowCubePosition.UNKNOWN) {

                /* Found an instance of the template. In the actual game, you will probably
                 * loop until this condition occurs, then move on to act accordingly depending
                 * on which VuMark was visible. */
                if(tempPosition == Detector.YellowCubePosition.LEFT ){
                    yellowPosition ="LEFT";
                    telemetry.addData("Camera", "%s visible", "left");
                }

                if( tempPosition == Detector.YellowCubePosition.CENTER){
                    yellowPosition ="CENTER";
                    telemetry.addData("Camera", "%s visible", "center");
                }

                if( tempPosition == Detector.YellowCubePosition.RIGHT){
                    yellowPosition ="RIGHT";
                    telemetry.addData("Camera", "%s visible", "right");
                }
                telemetry.update();
                notSeen = false;
            }
            else {
                telemetry.addData("Camera", "not visible");
                telemetry.update();
            }
        }
        telemetry.addData("Result>", yellowPosition);
        telemetry.update();
        sleep(5000);
        detector.stopCamera();
    }
}
