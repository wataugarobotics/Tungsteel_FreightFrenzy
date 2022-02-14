package org.firstinspires.ftc.teamcode.OpModes.DriverControlled;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name="RPM Test",group="")
public class RPMTest extends LinearOpMode {
    // Pre-init
    
    @Override
    public void runOpMode() {
        // Init
        DcMotorEx leftFront, leftRear, rightFront, rightRear;
        // Pre-run
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront"); //See configuration Google doc
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        rightRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        DcMotorEx[] motors = {leftFront, leftRear, rightFront, rightRear};
        waitForStart();
        while(opModeIsActive()) {
            // TeleOp loop
            for(DcMotorEx motor: motors){
                motor.setPower(0);
            }
            if(gamepad1.a)
                leftFront.setPower(1);
            if(gamepad1.b) rightFront.setPower(1);
            if(gamepad1.x) leftRear.setPower(1);
            if(gamepad1.y) rightRear.setPower(1);
            for (DcMotorEx motor: motors) {
                double tpsVel = motor.getVelocity();
                double rpsVel = tpsVel / 537.7;
                telemetry.addLine(motor.getConnectionInfo());
                telemetry.addData("TPS", tpsVel);
                telemetry.addData("RPS", rpsVel);
            }
            telemetry.update();
        }
    }
}
