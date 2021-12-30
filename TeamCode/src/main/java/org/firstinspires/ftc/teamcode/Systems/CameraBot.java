package org.firstinspires.ftc.teamcode.Systems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.CameraHardware;

public class CameraBot {
    public CameraHardware vision;
    public CameraBot(HardwareMap hwMap){
        vision = new CameraHardware(hwMap);
    }
}
