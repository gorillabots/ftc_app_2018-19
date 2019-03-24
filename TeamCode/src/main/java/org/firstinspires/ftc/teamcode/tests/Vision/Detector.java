package org.firstinspires.ftc.teamcode.tests.Vision;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.hardware.Camera;
import android.os.Environment;

import org.firstinspires.ftc.robotcontroller.for_camera_opmodes.LinearOpModeCamera;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;


public class Detector {
    public enum YellowCubePosition {
        LEFT,
        CENTER,
        RIGHT,
        UNKNOWN
    }

    final int ds2 = 2;
    private int netWidth = 10;

    private int DiffEdge = 70;
    private int yellowDiff = 50;
    private int whiteDiff = 150;

    private int XEdgeMin = 10;
    private int DistDiff = 10;
    private int centerLine = 32;

    // reference to linear opmode
    LinearOpModeCamera camOp;
    // result
    YellowCubePosition yellowCubePosition;
    // for Logging
    private static final String TAG = "GorillaBots-2018";

    public Detector(LinearOpModeCamera camOp) {
        this.camOp = camOp;
        yellowCubePosition = YellowCubePosition.UNKNOWN;

        // read initial data
        String fileName = "InitialData.txt";
        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard, fileName);
        if (!file.exists()) {
            CreateInitialFile(file);
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] value = line.split(",");
                if (value.length > 1) {
                    switch (value[0].toString().toLowerCase()) {
                        case "diffedge":
                            DiffEdge = Integer.parseInt((value[1].toString()));
                            break;
                        case "yellowdiff":
                            yellowDiff = Integer.parseInt((value[1].toString()));
                            break;
                        case "whitediff":
                            whiteDiff = Integer.parseInt((value[1].toString()));
                            break;
                        default:
                            break;
                    }
                }
             /* for (String each: line.split(",")) {
                        telemetry.addData("?", each );
                    }
             */
            }
            br.close();
        } catch (IOException e) {
            //You'll need to add proper error handling here
        }

    }

    private void CreateInitialFile(File myFile) {
        try {
            myFile.createNewFile();
            // Initial value, final is from the file
            String msg;
            msg = "diffedge," + DiffEdge + System.lineSeparator();
            msg = msg + "yellowdiff," + yellowDiff + System.lineSeparator();
            msg = msg + "whitediff," + whiteDiff + System.lineSeparator();

            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(msg);
            myOutWriter.close();
            fOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startCamera() {
        camOp.setCameraDownsampling(8);
        camOp.startCamera(Camera.CameraInfo.CAMERA_FACING_BACK);
    }

    public YellowCubePosition computeYellowCubePosition(boolean isCrater) {
        if (!camOp.imageReady()) return yellowCubePosition;

        Bitmap rgbImage;
        rgbImage = camOp.convertYuvImageToRgb(camOp.yuvImage, camOp.width, camOp.height, ds2);

        //left top (0,0), x from left to right; y from top to bottom
        //width,height (480, 640)

        YellowCubePosition result = YellowCubePosition.UNKNOWN;
//Convert bigger net area, in which is calculation happening

        RGBArray arrayD = new RGBArray(rgbImage, netWidth);
//  get Max X point for calculating
        int[] XEdge;
        if (isCrater) {
            XEdge = getXEdgeForCrater(arrayD);
        } else {
            XEdge = getXEdgeForDepot(arrayD);
        }
        int height = XEdge.length;
        camOp.telemetry.addData("Edge T & B: ", "" + XEdge[0] + " " + XEdge[height - 1]);

// Compute value for RG/2-B for yellow
        int[][] RG_B = arrayD.RG_B();
        YellowCubePosition resultRG_B;
        //resultRG_B=YellowCubePosition.UNKNOWN;
        resultRG_B = DetemineByRG_B(RG_B, XEdge, height);
// Compute value for R+G+B-avg for white
        int[][] RGB_Sum = arrayD.RGB_Sum();
        YellowCubePosition resultRGB_SUM = DetemineByRGB_SUM(RGB_Sum, XEdge, height);

        //outputFrom10points(rgbImage, XEdge);
        return resultRG_B;
    }

    private YellowCubePosition DetemineByRG_B(int[][] RG_B, int[] XEdge, int picH) {
        YellowCubePosition result = YellowCubePosition.UNKNOWN;

        // Compute value for RG-B for yellow
        Sort2DArray Sort2DRG_B = new Sort2DArray(RG_B, XEdge, picH);
        int totalPoint = Sort2DRG_B.totalPoint;
        int[] DRG_B_1D;
        DRG_B_1D = Sort2DRG_B.value;
        // Max value for RG-B
        int MaxRG_B = DRG_B_1D[totalPoint - 1];

        int P10th = totalPoint - 11;
        int P100th = totalPoint - 101;
        camOp.telemetry.addData("total points: ", "" + totalPoint);
        camOp.telemetry.addData("RG-B Max X Y Avg: ", "" + MaxRG_B + " " + Sort2DRG_B.X[totalPoint - 1] + " " + Sort2DRG_B.Y[totalPoint - 1] + " " + Sort2DRG_B.average);
        camOp.telemetry.addData("RG-B 10th X Y: ", "" + Sort2DRG_B.value[P10th] + " " + Sort2DRG_B.X[P10th] + " " + Sort2DRG_B.Y[P10th]);
        camOp.telemetry.addData("RG-B 100th X Y: ", "" + Sort2DRG_B.value[P100th] + " " + Sort2DRG_B.X[P100th] + " " + Sort2DRG_B.Y[P100th]);
        // Compare first sevaral points value
        int[] sortX = new int[totalPoint];
        int[] sortY = new int[totalPoint];
        for (int i = 0; i < P10th; i++) {
            sortX[i] = 0;
            sortY[i] = 0;
        }

        for (int i = P10th; i < totalPoint; i++) {
            sortX[i] = Sort2DRG_B.X[i];
            sortY[i] = Sort2DRG_B.Y[i];
        }
        Arrays.sort(sortX);
        Arrays.sort(sortY);

        camOp.telemetry.addData("X Max Min Diff:", "" + sortX[totalPoint - 1] + " " + sortX[P10th] + " " + (sortX[totalPoint - 1] - sortX[P10th]));
        camOp.telemetry.addData("Y Max Min Diff:", "" + sortY[totalPoint - 1] + " " + sortY[P10th] + " " + (sortY[totalPoint - 1] - sortY[P10th]));


        //RG- B for yellow
        if (Sort2DRG_B.value[P10th] - Sort2DRG_B.average > yellowDiff) {
            // Higher points distance are close?
            if ((sortX[totalPoint - 1] - sortX[P10th]) < DistDiff && (sortY[totalPoint - 1] - sortY[P10th]) < DistDiff) {
                if (sortY[totalPoint - 1] < centerLine) {
                    result = YellowCubePosition.LEFT;
                } else {
                    result = YellowCubePosition.CENTER;
                }
            }
        }
        if (result == YellowCubePosition.UNKNOWN) {
            //result = YellowCubePosition.RIGHT;
        }
        return result;
    }

    private YellowCubePosition DetemineByRGB_SUM(int[][] RGB_Sum, int[] XEdge, int picH) {

        YellowCubePosition result = YellowCubePosition.UNKNOWN;
        // Compute value for R+G+B-avg for white
        Sort2DArray Sort2DRGB_Sum = new Sort2DArray(RGB_Sum, XEdge, picH);
        int totalPoint = Sort2DRGB_Sum.totalPoint;
        int[] DRGB_Sum_1D;
        DRGB_Sum_1D = Sort2DRGB_Sum.value;
        int RGB_avg = Sort2DRGB_Sum.average;
        // Max value for RGB Sum
        int MaxRGB_Sum = DRGB_Sum_1D[totalPoint - 1];

        int firstP10 = 10;
        int firstP100 = 100;

        //Make two areas
        int[] topArea = new int[totalPoint];
        int[] topAreaX = new int[totalPoint];
        int[] topAreaY = new int[totalPoint];

        int[] bottomArea = new int[totalPoint];
        int[] bottomAreaX = new int[totalPoint];
        int[] bottomAreaY = new int[totalPoint];

        int topAreaNum = 0;
        int bottomAreaNum = 0;

        for (int i = 0; i < totalPoint; i++) {
            if (Sort2DRGB_Sum.Y[i] < centerLine) {
                topArea[topAreaNum] = Sort2DRGB_Sum.value[i];
                topAreaX[topAreaNum] = Sort2DRGB_Sum.X[i];
                topAreaY[topAreaNum] = Sort2DRGB_Sum.Y[i];
                topAreaNum = topAreaNum + 1;
            } else {
                bottomArea[bottomAreaNum] = Sort2DRGB_Sum.value[i];
                bottomAreaX[bottomAreaNum] = Sort2DRGB_Sum.X[i];
                bottomAreaY[bottomAreaNum] = Sort2DRGB_Sum.Y[i];
                bottomAreaNum = bottomAreaNum + 1;
            }
        }


        camOp.telemetry.addData("RGBSum topAreaNum : ", "" + topAreaNum);
        camOp.telemetry.addData("RGBSum Top Max X Y Avg: ", "" + topArea[topAreaNum - 1] + " " + topAreaX[topAreaNum - 1] + " " + topAreaY[topAreaNum - 1] + " " + RGB_avg);
        camOp.telemetry.addData("RGBSum Top10th Max X Y: ", "" + topArea[topAreaNum - 1 - firstP10] + " " + topAreaX[topAreaNum - 1 - firstP10] + " " + topAreaY[topAreaNum - 1 - firstP10]);
        camOp.telemetry.addData("RGBSum Top100th Max X Y: ", "" + topArea[topAreaNum - 1 - firstP100] + " " + topAreaX[topAreaNum - 1 - firstP100] + " " + topAreaY[topAreaNum - 1 - firstP100]);


        int[] Xsort;
        Xsort = sort(topAreaX, topAreaNum - 1 - firstP10, topAreaNum - 1);

        int[] Ysort;
        Ysort = sort(topAreaY, topAreaNum - 1 - firstP10, topAreaNum - 1);

        camOp.telemetry.addData("Top X Max Min Diff:", "" + Xsort[topAreaNum - 1] + " " + Xsort[topAreaNum - 1 - firstP10] + " " + (Xsort[topAreaNum - 1] - Xsort[topAreaNum - 1 - firstP10]));
        camOp.telemetry.addData("Top Y Max Min Diff:", "" + Ysort[topAreaNum - 1] + " " + Ysort[topAreaNum - 1 - firstP10] + " " + (Ysort[topAreaNum - 1] - Ysort[topAreaNum - 1 - firstP10]));


        boolean isWhiteBallTopArea = false;
        if (topArea[topAreaNum - 1 - firstP10] - RGB_avg > whiteDiff) {
            if ((Xsort[topAreaNum - 1] - Xsort[topAreaNum - 1 - firstP10] < DistDiff) && ((Ysort[topAreaNum - 1] - Ysort[topAreaNum - 1 - firstP10]) < DistDiff)) {
                isWhiteBallTopArea = true;
            }
        }

        //int firstP10 =10;
        camOp.telemetry.addData("RGBSum bottomAreaNum : ", "" + bottomAreaNum);
        camOp.telemetry.addData("RGBSum bottom Max X Y : ", "" + bottomArea[bottomAreaNum - 1] + " " + bottomAreaX[bottomAreaNum - 1] + " " + bottomAreaY[bottomAreaNum - 1] + " " + RGB_avg);
        camOp.telemetry.addData("RGBSum bottom10th Max X Y: ", "" + bottomArea[bottomAreaNum - 1 - firstP10] + " " + bottomAreaX[bottomAreaNum - 1 - firstP10] + " " + bottomAreaY[bottomAreaNum - 1 - firstP10]);
        camOp.telemetry.addData("RGBSum bottom100th Max X Y: ", "" + bottomArea[bottomAreaNum - 1 - firstP100] + " " + bottomAreaX[bottomAreaNum - 1 - firstP100] + " " + bottomAreaY[bottomAreaNum - 1 - firstP100]);

        int[] XXsort;
        XXsort = sort(bottomAreaX, bottomAreaNum - 1 - firstP10, bottomAreaNum - 1);

        int[] YYsort;
        YYsort = sort(bottomAreaY, bottomAreaNum - 1 - firstP10, bottomAreaNum - 1);

        camOp.telemetry.addData("bottom X Max Min Diff", "" + XXsort[bottomAreaNum - 1] + " " + XXsort[bottomAreaNum - 1 - firstP10] + " " + (XXsort[bottomAreaNum - 1] - XXsort[bottomAreaNum - 1 - firstP10]));
        camOp.telemetry.addData("bottom Y Max Min Diff", "" + YYsort[bottomAreaNum - 1] + " " + YYsort[bottomAreaNum - 1 - firstP10] + " " + (YYsort[bottomAreaNum - 1] - YYsort[bottomAreaNum - 1 - firstP10]));

        boolean isWhiteBallBottomArea = false;
        if (bottomArea[bottomAreaNum - 1 - firstP10] - RGB_avg > whiteDiff) {
            if ((XXsort[bottomAreaNum - 1] - XXsort[bottomAreaNum - 1 - firstP10] < DistDiff) && (YYsort[bottomAreaNum - 1] - YYsort[bottomAreaNum - 1 - firstP10] < DistDiff)) {
                isWhiteBallBottomArea = true;
            }
        }

        if (isWhiteBallTopArea && isWhiteBallBottomArea) {
            result = YellowCubePosition.RIGHT;
        }
        if (isWhiteBallTopArea && !isWhiteBallBottomArea) {
            result = YellowCubePosition.CENTER;
        }
        if (!isWhiteBallTopArea && isWhiteBallBottomArea) {
            result = YellowCubePosition.LEFT;
        }
        return result;
    }

    private YellowCubePosition GetYellowPosition(Bitmap rgbImage) {
        YellowCubePosition result = YellowCubePosition.UNKNOWN;
//Convert bigger net area, in which is calculation happening
        int netWidth = 10;
        RGBArray arrayD = new RGBArray(rgbImage, netWidth);
//  get Max X point for calculating
        int[] XEdge;
        XEdge = getXEdgeForCrater(arrayD);
        int height = XEdge.length;
        camOp.telemetry.addData("Edge T & B: ", "" + XEdge[0] + " " + XEdge[height - 1]);

// Compute value for RG/2-B for yellow
        int[][] RG_B = arrayD.RG_B();
        YellowCubePosition resultRG_B;
        //resultRG_B=YellowCubePosition.UNKNOWN;
        resultRG_B = DetemineByRG_B(RG_B, XEdge, height);
// Compute value for R+G+B-avg for white
        int[][] RGB_Sum = arrayD.RGB_Sum();
        YellowCubePosition resultRGB_SUM = DetemineByRGB_SUM(RGB_Sum, XEdge, height);

        //outputFrom10points(rgbImage, XEdge);
        return resultRG_B;
    }

    private int[] getXEdgeForCrater(RGBArray arrayD) {
        int picW = arrayD.width;
        int picH = arrayD.height;

        int[][] R = arrayD.RedArray();
        int[][] G = arrayD.GreenArray();
        int[][] B = arrayD.BlueArray();
        int[] XEdge = new int[picH];


        // Initial Area is whole area
        int Xtop = picW;
        int Xbottom = picW;

        // calculate the top and Bottom of Black line for the edge
        int row = 0;
        int dStep = 5;
        for (int x = 0; x < picW - dStep; x++) {
            int total = R[x][row] + G[x][row] + B[x][row];
            int total2 = R[x + 2][row] + G[x + 2][row] + B[x + 2][row];
            int total10 = R[x + dStep][row] + G[x + dStep][row] + B[x + dStep][row];
            if ((total - total2 > DiffEdge) && (total - total10 > DiffEdge)) {
                Xtop = x + 1;
                break;
            }
        }
        row = picH - 2;
        for (int x = 0; x < picW - dStep; x++) {
            int total = R[x][row] + G[x][row] + B[x][row];
            int total2 = R[x + 2][row] + G[x + 2][row] + B[x + 2][row];
            int total10 = R[x + dStep][row] + G[x + dStep][row] + B[x + dStep][row];
            if ((total - total2 > DiffEdge) && (total - total10 > DiffEdge)) {
                Xbottom = x + 1;
                break;
            }
        }
        Xtop = Xtop + dStep;
        Xbottom = Xbottom + dStep;
        if (Xtop < XEdgeMin) Xtop = XEdgeMin;
        if (Xbottom < XEdgeMin) Xbottom = XEdgeMin;
        if (Xtop > picW) Xtop = picW;
        if (Xbottom > picW) Xbottom = picW;

        // Only field area is the calculating area
        for (int i = 0; i < picH; i++) {
            XEdge[i] = Xtop + i * (Xbottom - Xtop) / (picH - 1);
        }
        return XEdge;
    }

    private int[] getXEdgeForDepot(RGBArray arrayD) {
        int picW = arrayD.width;
        int picH = arrayD.height;

        int[][] R = arrayD.RedArray();
        int[][] G = arrayD.GreenArray();
        int[][] B = arrayD.BlueArray();
        int[] XEdge = new int[picH];

// calculate the top and Bottom of Black line for the edge
        // Initial Area is whole area
        int Xtop = picW;
        int Xbottom = picW;

        int row = 0;
        for (int x = 0; x < picW - 2; x++) {
            int total = R[x][row] + G[x][row] + B[x][row];
            int total2 = R[x + 2][row] + G[x + 2][row] + B[x + 2][row];
            if (total - total2 > DiffEdge) {
                Xtop = x + 1;
                break;
            }
        }
        row = picH - 2;
        for (int x = 0; x < picW - 2; x++) {
            int total = R[x][row] + G[x][row] + B[x][row];
            int total2 = R[x + 2][row] + G[x + 2][row] + B[x + 2][row];
            if (total - total2 > DiffEdge) {
                Xbottom = x + 1;
                break;
            }
        }
        if (Xtop < XEdgeMin) Xtop = XEdgeMin;
        if (Xbottom < XEdgeMin) Xbottom = XEdgeMin;
        // Only field area is the calculating area
        for (int i = 0; i < picH; i++) {
            XEdge[i] = Xtop + i * (Xbottom - Xtop) / (picH - 1);
        }
        return XEdge;
    }

    private int[] sort(int[] P, int begin, int end) {
        int[] sortArray = new int[end + 1];
        for (int i = 0; i < begin; i++) {
            sortArray[i] = 0;
        }

        for (int i = begin; i < end + 1; i++) {
            sortArray[i] = P[i];
        }
        Arrays.sort(sortArray);
        return sortArray;
    }

    private YellowCubePosition computeFromBitmap(Bitmap rgbImage) {
        //left top (0,0), x from left to right; y from top to bottom
        //width,height (480, 640)
        final int width = rgbImage.getWidth();
        final int heights = rgbImage.getHeight();

        int x, y;

        y = heights / 2;
        x = width / 2;
        int pixel = rgbImage.getPixel(x, y);
        double valR = Color.red(pixel);
        double valB = Color.blue(pixel);

        double valG = Color.green(pixel);

        String msg = "" + width + "," + heights;
        // telemetry or logs
        camOp.telemetry.addData(" W,H: ", msg);
        msg = "" + valR + "," + valB + "," + valG;
        camOp.telemetry.addData("Center R B g: ", msg);


        y = 50;
        x = 50;
        pixel = rgbImage.getPixel(x, y);
        valR = Color.red(pixel);
        valB = Color.blue(pixel);
        valG = Color.green(pixel);
        msg = "" + valR + "," + valB + "," + valG;
        camOp.telemetry.addData("0 0 R B g: ", msg);

        y = 50;
        x = width - 50;
        pixel = rgbImage.getPixel(x, y);
        valR = Color.red(pixel);
        valB = Color.blue(pixel);
        valG = Color.green(pixel);
        msg = "" + valR + "," + valB + "," + valG;
        camOp.telemetry.addData("1000 0 R B g: ", msg);

        y = heights - 50;
        x = 50;
        pixel = rgbImage.getPixel(x, y);
        valR = Color.red(pixel);
        valB = Color.blue(pixel);
        valG = Color.green(pixel);
        msg = "" + valR + "," + valB + "," + valG;
        camOp.telemetry.addData("0 1000 R B g: ", msg);

        y = heights - 50;
        x = width - 50;
        pixel = rgbImage.getPixel(x, y);
        valR = Color.red(pixel);
        valB = Color.blue(pixel);
        valG = Color.green(pixel);
        msg = "" + valR + "," + valB + "," + valG;
        camOp.telemetry.addData("1000 1000 R B g: ", msg);

        //detectFromBitmap(rgbImage);
        //detectFrom10oint(rgbImage);
        //outputFrom10points(rgbImage);
        return YellowCubePosition.UNKNOWN;
    }

    private void outputToFile(String msg, String filename) {
        //String filename="ImageRGBData.txt";
        try {
            File myFile = new File("/sdcard/" + filename);
            if (!myFile.exists()) {
                myFile.createNewFile();
            }
            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(msg);
            myOutWriter.close();
            fOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String headTitle(String msg, int y, int picW) {
        if (y == 0) {
            for (int x = 0; x < picW - 1; x++) {
                msg = msg + x + ",";
            }
            msg = msg + System.lineSeparator();
        }
        msg = msg + y + ",";
        return msg;
    }

    public void stopCamera() {
        camOp.stopCamera();
    }
}
