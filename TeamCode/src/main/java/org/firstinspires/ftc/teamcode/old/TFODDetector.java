package org.firstinspires.ftc.teamcode.old;

import android.content.res.AssetManager;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class TFODDetector {  //unsued
    public enum GoldPosition {
        LEFT,
        CENTER,
        RIGHT,
        UNKNOWN
    }
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";

    HardwareMap hardMap;
    Telemetry tele;
    GoldPosition goldPosition;

    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;

    public TFODDetector(HardwareMap hMap, Telemetry telemetry) {
        tele = telemetry;
        hardMap = hMap;

    }

    public GoldPosition getGoldPosition() {
        return goldPosition;
    }
    public void recognizeGoldPosition() {
        if (tfod != null) {
            // getUpdatedRecognitions() will return null if no new information is available since
            // the last time that call was made.
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                tele.addData("# Object Detected", updatedRecognitions.size());
                if (updatedRecognitions.size() == 2) {
                    //Assume Right Two items
                    int goldMineralX = -1;
                    int silverMineral1X = -1;
                    int silverMineral2X = -1;
                    for (Recognition recognition : updatedRecognitions) {
                        if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                            goldMineralX = (int) recognition.getLeft();
                        } else if (silverMineral1X == -1) {
                            silverMineral1X = (int) recognition.getLeft();
                        } else {
                            silverMineral2X = (int) recognition.getLeft();
                        }
                    }
                    tele.addData("G,S1,S2 Positions", "" + goldMineralX + "," + silverMineral1X + "," + silverMineral2X);
                    if (goldMineralX != -1 && silverMineral1X != -1) {
                        if (goldMineralX < silverMineral1X) {
                            goldPosition = GoldPosition.CENTER;
                            //telemetry.addData("Gold Mineral Position", "Center");
                        } else if (goldMineralX > silverMineral1X) {
                            //telemetry.addData("Gold Mineral Position", "Right");
                            goldPosition = GoldPosition.RIGHT;
                        }
                    } else if (silverMineral1X != -1 && silverMineral2X != -1) {
                        //telemetry.addData("Gold Mineral Position", "Left");
                        goldPosition = GoldPosition.LEFT;
                    }
                }
                //telemetry.addData("Gold Position", goldPositon);
                //telemetry.update();
            }
        }
    }
    public void stop() {
        if (tfod != null) {
            tfod.shutdown();
        }
    }
    public void activate() {
        /** Activate Tensor Flow Object Detection. */
        if(tfod !=null)
        {
            tfod.activate();
        }
    }
    public void init() {
        goldPosition = GoldPosition.UNKNOWN;
        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.
        initVuforia();

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            tele.addData("Sorry!", "This device is not compatible with TFOD");
        }
    }
    /**
     * Initialize the Vuforia localization engine.
     */

    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = getVuforiaKey();
        parameters.cameraName = hardMap.get(WebcamName.class, "webcam");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
    }

    /**
     * Initialize the Tensor Flow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }

    private String getVuforiaKey()
    {
        try
        {
            AssetManager am = hardMap.appContext.getAssets();
            InputStream is = am.open("vuforiakey.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String key = br.readLine();
            return key;
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
