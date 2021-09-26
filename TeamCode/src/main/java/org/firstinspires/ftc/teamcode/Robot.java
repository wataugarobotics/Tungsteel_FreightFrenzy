package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Grabber;

public class Robot {
    HardwareMap hwMap;
    public Grabber grabber;
    public Robot(HardwareMap hwMap){
        grabber = new Grabber(hwMap);
    }

    public void init(){

    }
}
