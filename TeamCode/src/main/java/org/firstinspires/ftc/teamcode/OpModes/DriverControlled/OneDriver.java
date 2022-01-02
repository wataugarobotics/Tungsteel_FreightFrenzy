package org.firstinspires.ftc.teamcode.OpModes.DriverControlled;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Pushbot;


@TeleOp(name="OneDriver",group="Tungsteel 21-22")
public class OneDriver extends LinearOpMode {
    Pushbot robot = new Pushbot(hardwareMap);
    GamepadEx driverPad = new GamepadEx(gamepad1);
    @Override
    public void runOpMode() {

        waitForStart();
        while (opModeIsActive()) {
            //if(driverPad.wasJustPressed(GamepadKeys.Button.A)) //servo.toggle() ;
            robot.drivetrain.goXYR(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
        }
    }
}

