package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Libraries.Subsystem;

public class Intake extends Subsystem {
    DcMotorEx intakeMotor;
    public Intake(HardwareMap hwMap){
        intakeMotor = hwMap.get(DcMotorEx.class, "intakeMotor");
    }
    public void spinTake(double power){
        intakeMotor.setPower(Range.clip(power, -1, 1));
    }
}
