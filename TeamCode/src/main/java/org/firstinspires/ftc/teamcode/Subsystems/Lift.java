package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Libraries.Subsystem;

public class Lift extends Subsystem {
    private final DcMotorEx liftMotor;
    private int level = 0; //changed 'position' to 'level' for clarity

    int[] LEVEL_HEIGHT = {0, 230, 700, 1300};
    double KPup = 10;
    double KPdown = 1.2;
    double KI = 0;
    double KD = .8;
    double KF = 10.996;
    double POSITION_COEFFICIENT = 5.0;


    public Lift(HardwareMap hwMap) {
        liftMotor = hwMap.get(DcMotorEx.class, "liftMotor");
        liftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        liftMotor.setVelocityPIDFCoefficients(KPup, KI, KD, KF);
        liftMotor.setPositionPIDFCoefficients(POSITION_COEFFICIENT);
        liftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        liftMotor.setTargetPositionTolerance(200);
        //liftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setTargetPosition(0);
    }

    public void addPosition() {
        if(level < 3) {
            level++;
            setPosition(LEVEL_HEIGHT[level]);
            liftMotor.setVelocityPIDFCoefficients(KPup, KI, KD, KF);
        }
    }

    public void subPosition() {
        if(level > 0) {
            level--;
            setPosition(LEVEL_HEIGHT[level]);
            liftMotor.setVelocityPIDFCoefficients(KPdown, KI, KD, KF);
            setPower(1);

        }
    }

    public void setPosition(int newPos) {
        liftMotor.setTargetPosition(newPos);
    }
    public void setPower(double power) {
        if(liftMotor.isBusy()) liftMotor.setPower(power);
        else liftMotor.setPower(0); // This will either work, or it won't. If not, just delete the line it should be fine

    }
    public double getPositionError(){
        return liftMotor.getTargetPosition()- liftMotor.getCurrentPosition();
    }
}