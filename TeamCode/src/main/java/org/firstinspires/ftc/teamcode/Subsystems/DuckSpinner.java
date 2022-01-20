package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Libraries.Subsystem;

public class DuckSpinner extends Subsystem {
    private DcMotorEx duckSpinner;
    private final double POWER = 1;
    public DuckSpinner(HardwareMap hwMap){
        duckSpinner = hwMap.get(DcMotorEx.class, "parallelEncoder");
    }
    public void setPower(double power){
        duckSpinner.setPower(power);
    }
    public void toggle(){
        if(duckSpinner.getPower() == 0)
            duckSpinner.setPower(POWER);
        else
            duckSpinner.setPower(0);
    }
}
