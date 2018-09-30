package org.firstinspires.ftc.teamcode.tests;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.vuforia.Frame;
import com.vuforia.Image;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.BlockingQueue;

@Autonomous(group="test", name="Image Test")
public class VuforiaImageTest extends LinearOpMode
{
    VuforiaLocalizer vuforia;

    public void runOpMode()
    {
        Vuforia.setFrameFormat(PIXEL_FORMAT.RGBA8888, true);


        Context ctx = hardwareMap.appContext;
        Resources resources = ctx.getResources();
        int camera = resources.getIdentifier("cameraMonitorViewId", "id", ctx.getPackageName());

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(camera);
        parameters.vuforiaLicenseKey = getVuforiaKey();
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        telemetry.addData("Status", "Initialized");

        waitForStart();

        BlockingQueue<VuforiaLocalizer.CloseableFrame> frameQueue = vuforia.getFrameQueue();
        Frame frame;
        Image img;

        while(opModeIsActive()) //Getting hung up somewhere
        {
            try
            {
                frame = frameQueue.take();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
                break;
            }

            img = frame.getImage(1);
            byte[] pxls = img.getPixels().array();

            telemetry.addData("Color", pxls[0] + "," + pxls[1] + "," + pxls[2]);
            telemetry.update();

            sleep(50);
        }
    }

    private String getVuforiaKey()
    {
        try
        {
            AssetManager am = hardwareMap.appContext.getAssets();
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
