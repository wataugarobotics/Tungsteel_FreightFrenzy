package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class Subsystem {
    HardwareMap hwMap;
    public Subsystem(){
        hwMap = null;
    }
    public Subsystem(HardwareMap hardwareMap){
        hwMap = hardwareMap;
    }
}
