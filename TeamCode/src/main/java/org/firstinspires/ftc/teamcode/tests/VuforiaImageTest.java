package org.firstinspires.ftc.teamcode.tests;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.vuforia.Frame;
import com.vuforia.Image;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.State;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.util.concurrent.BlockingQueue;

@Autonomous(group="test", name="Image Test")
public class VuforiaImageTest extends LinearOpMode implements Vuforia.UpdateCallbackInterface
{
    private VuforiaLocalizer vuforia;
    private static byte[] image;

    @Override
    public void Vuforia_onUpdate(State state)
    {
        Frame frame = state.getFrame();

        //i = 0, format = 4, grayscale
        //i = 1, format = 2, RGB888

        /*telemetry.addData("Number of images", frame.getNumImages());
        for(int i = 0; i < frame.getNumImages(); i++)
        {
            telemetry.addData("Pixel format", frame.getImage(i).getFormat());
        }*/

        Image img = frame.getImage(1);

        if(img != null)
        {
            ByteBuffer pixelsBB = img.getPixels();
            byte[] pixels = new byte[pixelsBB.remaining()];
            pixelsBB.get(pixels, 0, pixels.length);
            //int width = img.getWidth();
            //int height = img.getHeight();
            //int stride = img.getStride();
            image = pixels;
        }

        //telemetry.update();
    }


    public void runOpMode()
    {
        Context ctx = hardwareMap.appContext;
        Resources resources = ctx.getResources();
        int camera = resources.getIdentifier("cameraMonitorViewId", "id", ctx.getPackageName());

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(camera);
        parameters.vuforiaLicenseKey = getVuforiaKey();
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        Vuforia.setFrameFormat(PIXEL_FORMAT.RGB888, true);
        Vuforia.registerCallback(this);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while(opModeIsActive())
        {
            if (image == null)
            {
                telemetry.addData("Status", "Array Null");
            }
            else
            {
                telemetry.addData("R", String.format("%02x", image[0]));
                telemetry.addData("G", String.format("%02x", image[1]));
                telemetry.addData("B", String.format("%02x", image[2]));
            }

            telemetry.update();
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
