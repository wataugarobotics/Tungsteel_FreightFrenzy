package org.firstinspires.ftc.teamcode.OpModes.DriverControlled;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Systems.Pushbot;
@Disabled
@TeleOp(name="SingleMotorTest",group="Tungsteel 21-22")
public class SingleMotorTest extends LinearOpMode {
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
//        motor.setVelocityPIDFCoefficients(1.20, .220, 0, 10.996);
 //       motor.setPositionPIDFCoefficients(5.0);
//        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        motor.setTargetPositionTolerance(200);
//        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        motor.setTargetPosition(0);
        waitForStart();
        // Pre-run
        while (opModeIsActive()) {
            motor.setPower(gamepad1.left_trigger - gamepad1.right_trigger);
            currentVelocity = motor.getVelocity();
            if (currentVelocity > maxVelocity)
                maxVelocity = currentVelocity;
            telemetry.addData("current velocity", currentVelocity);
            telemetry.addData("max velocity", maxVelocity);
            telemetry.addData("power", motor.getPower());
            telemetry.update();



        }
    }
}
