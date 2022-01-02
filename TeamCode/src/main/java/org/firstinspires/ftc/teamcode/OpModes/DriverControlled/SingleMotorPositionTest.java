package org.firstinspires.ftc.teamcode.OpModes.DriverControlled;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Systems.Pushbot;

@TeleOp(name="SingleMotorPositionTest",group="Tungsteel 21-22")
public class SingleMotorPositionTest extends LinearOpMode {
    DcMotorEx motor;
    Servo servo;
    double currentVelocity;
    double maxVelocity = 0.0;
    double targetVelocity = 0;
    double currentPos;
    double maxPos = 0.0;
    @Override
    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.get(DcMotorEx.class, "singleMotor");
        servo = hardwareMap.get(Servo.class, "servo");
        motor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        motor.setVelocityPIDFCoefficients(1.20,.220, 0,10.996);
        motor.setPositionPIDFCoefficients(5.0);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setTargetPositionTolerance(200);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setTargetPosition(0);
        waitForStart();
        // Pre-run
        while (opModeIsActive()) {
            if(gamepad1.left_bumper) targetVelocity = 1;
            if(gamepad1.right_bumper) targetVelocity = -1;
            //TODO: Servos
            if(gamepad1.a) motor.setTargetPosition(0);
            if(gamepad1.x) motor.setTargetPosition(230);
            if(gamepad1.b) motor.setTargetPosition(700);
            if(gamepad1.y) motor.setTargetPosition(1300);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor.setPower(1);
//            motor.setVelocity(targetVelocity
            currentVelocity = motor.getVelocity();
            currentPos = motor.getCurrentPosition();
            if (currentVelocity > maxVelocity)
                maxVelocity = currentVelocity;
            telemetry.addData("current velocity", currentVelocity);
            telemetry.addData("current position", currentPos);
            telemetry.addData("position delta", currentPos-motor.getTargetPosition());
            telemetry.addData("power", motor.getPower());
            telemetry.update();



        }
    }
}
