package org.firstinspires.ftc.teamcode.OpModes.DriverControlled;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.exception.RobotCoreException;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Systems.Keith;
import org.firstinspires.ftc.teamcode.Systems.Pushbot;


@TeleOp(name="OneDriver",group="Tungsteel 21-22")
public class OneDriver extends LinearOpMode { @Override
    public void runOpMode() { /* hardwareMap DOESN'T EXIST UNTIL THIS METHOD! */
        Keith robot = new Keith(hardwareMap);
        GamepadEx gamepad1ex = new GamepadEx(gamepad1);
        waitForStart();
        while (opModeIsActive()) {
            gamepad1ex.readButtons();
            if(gamepad1ex.wasJustPressed(GamepadKeys.Button.LEFT_BUMPER))
                robot.lift.subPosition();
            if(gamepad1ex.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER))
                robot.lift.addPosition();

            robot.lift.setPower(1);
            robot.drivetrain.goXYR(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

            telemetry.addData("Error", robot.lift.getPositionError());
            telemetry.update();
        }
    }
}

