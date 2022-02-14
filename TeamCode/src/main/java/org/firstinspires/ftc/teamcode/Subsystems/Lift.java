package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.robocol.TelemetryMessage;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Libraries.Subsystem;

public class Lift extends Subsystem {
    private final DcMotorEx liftMotor;
    private int level = 0; //changed 'position' to 'level' for clarity

    int[] LEVEL_HEIGHT = {0, 230, 700, 1300};
    double KP = 1.2;
    double KI = 0;
    double KD = .8;
    double KF = 10.996;
    double POSITION_COEFFICIENT = 5.0;


    public Lift(HardwareMap hwMap) {
        liftMotor = hwMap.get(DcMotorEx.class, "liftMotor");
        liftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        liftMotor.setVelocityPIDFCoefficients(KP, KI, KD, KF);
        liftMotor.setPositionPIDFCoefficients(POSITION_COEFFICIENT);
        liftMotor.setTargetPosition(0);
        liftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        liftMotor.setTargetPositionTolerance(60);
    }
    public void setLevel(int newLevel){
        level = newLevel;
        setPosition(LEVEL_HEIGHT[level]);
        setPower(1);
    }
    public void addPosition() {
        if(level < 3) {
            level++;
            setPosition(LEVEL_HEIGHT[level]);
            setPower(1);
        }
    }

    public void subPosition() {
        if(level > 0) {
            level--;
            setPosition(LEVEL_HEIGHT[level]);
            setPower(.5);

        }
    }


    public void setPosition(int newPos) {
        liftMotor.setTargetPosition(newPos);
    }
    public void setPower(double power) {
        liftMotor.setPower(power);

    }

    public double getPositionError(){
        return liftMotor.getTargetPosition()- liftMotor.getCurrentPosition();
    }
    public void getData(Telemetry telemetry){
         telemetry.addLine("Lift:")
                .addData("Lift Level", level)
                .addData("Target Position", liftMotor.getTargetPosition())
                .addData("Position Error", getPositionError())
                .addData("Current Position", liftMotor.getCurrentPosition())
                ;
    }
}