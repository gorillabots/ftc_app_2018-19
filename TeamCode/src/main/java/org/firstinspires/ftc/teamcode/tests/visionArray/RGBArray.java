package org.firstinspires.ftc.teamcode.tests.visionArray;

import android.graphics.Bitmap;
import android.graphics.Color;

public class RGBArray {
    public int width;
    public int height;
    private int [][] R;
    private int [][] G;
    private int [][] B;
    private int [] XEdge;

    public RGBArray(Bitmap rgbImage, int netWidth){

        int width0 = rgbImage.getWidth();
        int height0 = rgbImage.getHeight();

        width = width0/netWidth -1;
        height = height0/netWidth-1;

        int D = netWidth;

        R = new int[width][height];
        G = new int[width][height];
        B = new int[width][height];

        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++)
            {
                R[x][y] = 0;
                G[x][y] = 0;
                B[x][y] = 0;
                for (int j = y*D; j<y*D+D;j++) {
                    for (int i = x*D; i<x*D+D;i++) {
                        int pixel = rgbImage.getPixel(i, j);
                        R[x][y] = R[x][y] + Color.red(pixel);
                        G[x][y] = G[x][y] + Color.green(pixel);
                        B[x][y] = B[x][y] + Color.blue(pixel);
                    }
                }
                R[x][y] = R[x][y]/(D*D);
                G[x][y] = G[x][y]/(D*D);
                B[x][y] = B[x][y]/(D*D);
            }
        }


        XEdge =new int[height];
        for (int y = 0; y < height; y++){
            XEdge[y]=width;
        }

    }
    public  int [][] RedArray(){
        return R;
    }
    public  int [][] GreenArray(){
        return G;
    }
    public  int [][] BlueArray(){
        return B;
    }

    public  int [][] RGB_Sum(){
        int [][] result = new int[width][height];

        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++)
            {
                result[x][y]=R[x][y]+ G[x][y]+ B[x][y];
            }
        }
        return result;
    }

    public  int [][] RG_B(){
        int [][] result = new int[width][height];

        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++)
            {
                result[x][y]=(R[x][y]+ G[x][y])/2 - B[x][y];
            }
        }
        return result;
    }

}
