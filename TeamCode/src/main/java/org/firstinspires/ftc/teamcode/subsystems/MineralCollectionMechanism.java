package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MineralCollectionMechanism {

    public DcMotor mExtendHoriz;
    public DcMotor mExtendVert;
    public DcMotor mCollect;

    public MineralCollectionMechanism(HardwareMap hardwareMap, Telemetry telemetry) {
        mExtendHoriz = hardwareMap.get(DcMotor.class, "mExtendHoriz");
        mExtendVert = hardwareMap.get(DcMotor.class,"mExtendVert");
        mCollect = hardwareMap.get(DcMotor.class,"mCollect");
    }
    
}
