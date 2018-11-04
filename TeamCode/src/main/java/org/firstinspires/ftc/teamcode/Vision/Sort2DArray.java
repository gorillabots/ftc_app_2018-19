package org.firstinspires.ftc.teamcode.Vision;

public class Sort2DArray {

    public int totalPoint;
    public int [] value;
    public int [] X;
    public int [] Y;
    public  int average;

    public Sort2DArray(int [][] value2D,int [] XEdge, int height){
        // get total point
        totalPoint = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < XEdge[y]; x++) {
                totalPoint = totalPoint+1;
            }
        }
        value = new int[totalPoint];
        X = new int[totalPoint];
        Y = new int[totalPoint];
        // convert to one-dimension
        int index =0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < XEdge[y]; x++) {
                value[index] = value2D[x][y];
                X[index]=x;
                Y[index]=y;
                index = index+1;
            }
        }
        // sort in ascending order
        for (int i = 0; i < totalPoint; i++)
        {
            for (int j = i + 1; j < totalPoint; j++)
            {
                if (value[i] > value[j])
                {
                    int temp = value[i];
                    value[i] = value[j];
                    value[j] = temp;

                    int tempX=X[i];
                    X[i] = X[j];
                    X[j] = tempX;

                    int tempY=Y[i];
                    Y[i] = Y[j];
                    Y[j] = tempY;
                }
            }
        }
        int sum=0;
        for (int i = 0; i < totalPoint; i++) {
            sum = sum + value[i];
        }
        average = sum/totalPoint;
    }
}
