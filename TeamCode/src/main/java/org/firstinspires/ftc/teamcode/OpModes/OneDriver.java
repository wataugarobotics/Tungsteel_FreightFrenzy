package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Robot;

@TeleOp(name="OneDriver",group="Tungsteel 21-22")
public class OneDriver extends LinearOpMode {

    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap);
    
        waitForStart();
    
        // Pre-run
        boolean buttonPressed = false;
        while (opModeIsActive()) {
            if(gamepad1.a && !buttonPressed){
                buttonPressed = true;
                robot.grabber.toggle();
            }else if(!gamepad1.a){
                buttonPressed = false;
            }

        }
    }
}
