package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Libraries.Subsystem;

public class Lift extends Subsystem {
    private final DcMotorEx liftMotor;
    private int position = 0;

    static final int[] LEVEL_HEIGHT = {0, 230, 700, 1300};
    static final double KP = 1.2;
    static final double KI = .170;
    static final double KD = 0;
    static final double KF = 10.996;
    static final double POSITION_COEFFICIENT = 5.0;


    public Lift(HardwareMap hwMap) {
        liftMotor = hwMap.get(DcMotorEx.class, "singleMotor");
        liftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        liftMotor.setVelocityPIDFCoefficients(KP, KI, KD, KF);
        liftMotor.setPositionPIDFCoefficients(POSITION_COEFFICIENT);
        liftMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        liftMotor.setTargetPositionTolerance(200);
        liftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setTargetPosition(0);
    }

    public void addPosition() {
        if(position < 4) {
            position++;
            setPosition(LEVEL_HEIGHT[position]);
        }
    }

    public void subPosition() {
        if(position > 0) {
            position--;
            setPosition(LEVEL_HEIGHT[position]);
        }
    }

    public void setPosition(int newPos) {
        liftMotor.setTargetPosition(newPos);
    }
}