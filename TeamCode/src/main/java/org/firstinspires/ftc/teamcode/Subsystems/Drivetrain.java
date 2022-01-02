package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Libraries.Subsystem;

public class Drivetrain extends Subsystem {
    private float speedMod = 1;
    DcMotorEx leftFront, leftRear, rightFront, rightRear;
    public Drivetrain(HardwareMap hwMap) {
        /* This part initializes each motor
        hwMap.get() tells the program to look for a specific piece of hardware
        The first parameter `DcMotorEx.class` tells the program what type of hardware is being used
        The second parameter is a string containing the name of the piece of hardware as defined in configuration
         */
        leftFront = hwMap.get(DcMotorEx.class, "leftFront"); //See configuration Google doc
        leftRear = hwMap.get(DcMotorEx.class, "leftRear");
        rightFront = hwMap.get(DcMotorEx.class, "rightFront");
        rightRear = hwMap.get(DcMotorEx.class, "leftRear");
        leftFront.setDirection(DcMotorEx.Direction.FORWARD);
        leftRear.setDirection(DcMotorEx.Direction.FORWARD);
        rightFront.setDirection(DcMotorEx.Direction.REVERSE); //Multiplies all values to these motors by -1
        rightRear.setDirection(DcMotorEx.Direction.REVERSE);

    }
    public void goXYR(double x, double y, double r) { //Makes the robot move based on an x, y, and r value
        // Values x, y, and r come from the joystick during driver controlled
        // Calculate the power
        double leftFrontPower = (y + x + r) * speedMod;
        double leftRearPower = (y - x + r) * speedMod;
        double rightFrontPower = (y - x - r) * speedMod;
        double rightRearPower = (y + x - r) * speedMod;
        // Send calculated power to wheels
        // Range.clip() makes sure that the power will never be greater than 1 or less than -1
        leftFront.setPower(Range.clip(leftFrontPower,-1, 1));
        leftRear.setPower(Range.clip(leftRearPower,-1, 1));
        rightFront.setPower(Range.clip(rightFrontPower,-1, 1));
        rightRear.setPower(Range.clip(rightRearPower,-1, 1));
    }
    public void goPolarDegrees(double angle, double magnitude){ // Same as goPolarRadians, but in degrees
        goPolarRadians(Math.toRadians(angle), magnitude);
    }
    public void goPolarRadians(double angle, double magnitude){ // Strafes the robot at a specified angle
        double piOver4 = Math.PI / 4; //see Seamonsters mecanum site for math
        double leftSlant = Math.sin(angle - piOver4) * Range.clip(magnitude, -1, 1);
        double rightSlant = Math.sin(angle + piOver4) * Range.clip(magnitude, -1, 1);
        leftFront.setPower(leftSlant);
        rightRear.setPower(leftSlant);
        rightFront.setPower(rightSlant);
        rightRear.setPower(rightSlant);
    }

    /* # Setter and Getter methods # */
    public void setSpeedMod(float speedMod){
        this.speedMod = speedMod;
    }
    public float getSpeedMod(){
        return speedMod;
    }

}

