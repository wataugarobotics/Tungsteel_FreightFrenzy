package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;

public class Robot {
    HardwareMap hwMap;
    public Drivetrain drivetrain;
    public Robot(HardwareMap hwMap){
        drivetrain = new Drivetrain(hwMap);
    }

    public void init(){

    }
}
