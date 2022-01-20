package org.firstinspires.ftc.teamcode.Systems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.CameraHardware;
import org.firstinspires.ftc.teamcode.Subsystems.CameraHardwareAprilTag;

public class CameraBot {
    public CameraHardwareAprilTag vision;
    public CameraBot(HardwareMap hwMap){
        vision = new CameraHardwareAprilTag(hwMap);
    }
}
