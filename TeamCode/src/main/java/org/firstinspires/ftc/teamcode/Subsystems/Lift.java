package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Libraries.Subsystem;

public class Lift extends Subsystem {
    DcMotorEx liftMotor;

    public Lift(HardwareMap hwMap) {
        liftMotor = hwMap.get(DcMotorEx.class, "singleMotor");
        liftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        liftMotor.setVelocityPIDFCoefficients(1.20, .170, 0, 10.996);
        liftMotor.setPositionPIDFCoefficients(5.0);
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotor.setTargetPositionTolerance(200);
        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setTargetPosition(0);
    }

    public void setLiftPosition(int position) {
        liftMotor.setTargetPosition(0);
    }

}