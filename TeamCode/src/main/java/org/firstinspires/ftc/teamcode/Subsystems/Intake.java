package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Libraries.Subsystem;

public class Intake extends Subsystem {
    DcMotorEx intakeMotor;
    public Intake(HardwareMap hwMap){
        intakeMotor = hwMap.get(DcMotorEx.class, "intakeMotor");
        intakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void spinTake(double power){
        intakeMotor.setPower(Range.clip(power, -1, 1));
    }
    public void getData(Telemetry telemetry){
        telemetry.addLine("Intake:")
                .addData("Velocity", intakeMotor.getVelocity())
                ;
    }
}
