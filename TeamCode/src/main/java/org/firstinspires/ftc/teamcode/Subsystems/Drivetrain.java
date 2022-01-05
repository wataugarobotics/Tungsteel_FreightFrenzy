package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Libraries.Subsystem;

public class Drivetrain extends Subsystem {
    private float speedMod = 1;
    DcMotorEx leftFront, leftRear, rightFront, rightRear;
    public Drivetrain(HardwareMap hwMap) {
        leftFront = hwMap.get(DcMotorEx.class, "leftFront");
        leftRear = hwMap.get(DcMotorEx.class, "leftRear");
        rightFront = hwMap.get(DcMotorEx.class, "rightFront");
        rightRear = hwMap.get(DcMotorEx.class, "leftRightRear");
        leftFront.setDirection(DcMotorEx.Direction.FORWARD);
        leftRear.setDirection(DcMotorEx.Direction.FORWARD);
        rightFront.setDirection(DcMotorEx.Direction.REVERSE);
        rightRear.setDirection(DcMotorEx.Direction.REVERSE);
    }
    public void goXYR(double x, double y, double r) {
        // Calculate the power
        double leftFrontPower = Range.clip(y + x + r, -1.0, 1.0) / speedMod;
        double leftRearPower = Range.clip(y - x + r, -1.0, 1.0) / speedMod;
        double rightFrontPower = Range.clip(y - x - r, -1.0, 1.0) / speedMod;
        double rightRearPower = Range.clip(y + x - r, -1.0, 1.0) / speedMod;
        // Send calculated power to wheels
        leftFront.setPower(leftFrontPower);
        leftRear.setPower(leftRearPower);
        rightFront.setPower(rightFrontPower);
        rightRear.setPower(rightRearPower);
    }
    public void goPolarDegrees(double angle, double magnitude){
        goPolarRadians(Math.toRadians(angle), magnitude);
    }
    public void goPolarRadians(double angle, double magnitude){ // See seamonsters mecanum site
        double piOver4 = Math.PI / 4;
        double leftSlant = Math.sin(angle - piOver4) * Range.clip(magnitude, -1, 1);
        double rightSlant = Math.sin(angle + piOver4) * Range.clip(magnitude, -1, 1);
        leftFront.setPower(leftSlant);
        rightRear.setPower(leftSlant);
        rightFront.setPower(rightSlant);
        rightRear.setPower(rightSlant);
    }
    public void setSpeedMod(float speedMod){
        this.speedMod = speedMod;
    }
    public float getSpeedMod(){
        return speedMod;
    }

}

