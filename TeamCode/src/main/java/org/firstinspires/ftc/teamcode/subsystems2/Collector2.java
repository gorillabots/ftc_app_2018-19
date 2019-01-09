package org.firstinspires.ftc.teamcode.subsystems2;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class Collector2
{
    private DcMotor extend;
    private DcMotor ingest;

    private Servo angle;
    private Servo backstop;

    private TouchSensor extendStop;
    
    private static final double EXTEND_LENGTH = 5;

    public static final double ANGLE_INIT = 0;
    public static final double ANGLE_COLLECT = 0.79;
    public static final double ANGLE_DUMP = 0.94;

    public static final double BACKSTOP_INIT = 0.72;
    public static final double BACKSTOP_CLOSED = 0.72;
    public static final double BACKSTOP_OPEN = 0.3;

    private double extendLimitIn;
    private double extendLimitOut;
    
    public Collector2(HardwareMap hm)
    {
        extend = hm.get(DcMotor.class, "mExtendHoriz");
        ingest = hm.get(DcMotor.class, "mCollect");

        backstop = hm.get(Servo.class, "sBackstopCol");
        angle = hm.get(Servo.class, "sCollectionRot");

        extendStop = hm.get(TouchSensor.class , "horizTouch");

        backstop.setPosition(BACKSTOP_INIT);
        angle.setPosition(ANGLE_INIT);
    }

    public void updateLimit()
    {
        if(extendStop.isPressed())
        {
            extendLimitIn = extend.getCurrentPosition();
            extendLimitOut = extendLimitIn + EXTEND_LENGTH;
        }
    }
    
    public void extendOut(double power)
    {
        if(extend.getCurrentPosition() < extendLimitOut)
        {
            extend.setPower(power);
        }
        else
        {
            extend.setPower(0);
        }
    }

    public void extendIn(double power)
    {
        if(extend.getCurrentPosition() > extendLimitIn)
        {
            extend.setPower(-power);
        }
        else
        {
            extend.setPower(0);
        }
    }
    
    public void setIngest(double power)
    {
        ingest.setPower(power);
    }
    
    public void setAngle(double pos)
    {
        angle.setPosition(pos);
    }

    public void setBackstopPos(double pos)
    {
        backstop.setPosition(pos);
    }
}
