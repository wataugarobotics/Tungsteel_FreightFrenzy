package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

public class Drivetrain extends Subsystem {
    float speedMod = 1;
    DcMotorEx leftFront, leftBack, rightFront, rightBack;
    public Drivetrain(HardwareMap hwMap) {
        leftFront = hwMap.get(DcMotorEx.class, "leftFront");
        leftBack = hwMap.get(DcMotorEx.class, "leftBack");
        rightFront = hwMap.get(DcMotorEx.class, "rightFront");
        rightBack = hwMap.get(DcMotorEx.class, "leftRightBack");
        leftFront.setDirection(DcMotorEx.Direction.FORWARD);
        leftBack.setDirection(DcMotorEx.Direction.FORWARD);
        rightFront.setDirection(DcMotorEx.Direction.REVERSE);
        rightBack.setDirection(DcMotorEx.Direction.REVERSE);
    }
    public void goXYR(double x, double y, double r) {
        // Calculate the power
        double leftFrontPower = Range.clip(y + x + r, -1.0, 1.0) / speedMod;
        double leftBackPower = Range.clip(y - x + r, -1.0, 1.0) / speedMod;
        double rightFrontPower = Range.clip(y - x - r, -1.0, 1.0) / speedMod;
        double rightBackPower = Range.clip(y + x - r, -1.0, 1.0) / speedMod;
        // Send calculated power to wheels
        leftFront.setPower(leftFrontPower);
        leftBack.setPower(leftBackPower);
        rightFront.setPower(rightFrontPower);
        rightBack.setPower(rightBackPower);
    }
    public void goPolarDegrees(double angle, double magnitude){
        goPolarRadians(Math.toRadians(angle), magnitude);
    }
    public void goPolarRadians(double angle, double magnitude){ // See
        double piOver4 = Math.PI / 4;
        double leftSlant = Math.sin(angle - piOver4) * Range.clip(magnitude, -1, 1);
        double rightSlant = Math.sin(angle + piOver4) * Range.clip(magnitude, -1, 1);
        leftFront.setPower(leftSlant);
        rightBack.setPower(leftSlant);
        rightFront.setPower(rightSlant);
        rightBack.setPower(rightSlant);
    }

}

