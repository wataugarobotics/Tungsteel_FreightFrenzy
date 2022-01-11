package org.firstinspires.ftc.teamcode.Systems;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;

public class Robot {
    HardwareMap hwMap;
    public Drivetrain drivetrain;
    public Pushbot(HardwareMap hwMap){
        drivetrain = new Drivetrain(hwMap);
    }

    public void init(){

    }
}
