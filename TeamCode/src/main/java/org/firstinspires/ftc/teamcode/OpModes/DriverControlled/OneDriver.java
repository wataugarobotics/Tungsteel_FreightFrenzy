package org.firstinspires.ftc.teamcode.OpModes.DriverControlled;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.Keith;


@TeleOp(name="OneDriver",group="Tungsteel 21-22")
public class OneDriver extends LinearOpMode { @Override
    public void runOpMode() { /* hardwareMap is within the scope of this method, not outside!! */
        Keith robot = new Keith(hardwareMap);
        GamepadEx gamepad1ex = new GamepadEx(gamepad1);
        robot.lift.setPower(1);
        waitForStart();
        while (opModeIsActive()) {
            gamepad1ex.readButtons();
            if(gamepad1ex.wasJustPressed(GamepadKeys.Button.LEFT_BUMPER))
                robot.lift.subPosition();
            if(gamepad1ex.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER))
                robot.lift.addPosition();
            if(gamepad1ex.wasJustPressed(GamepadKeys.Button.A))
                robot.basket.toggle();
            if(gamepad1ex.wasJustPressed(GamepadKeys.Button.X))
                robot.duckSpinner.toggle();


            robot.drivetrain.goXYR(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
            robot.intake.spinTake(gamepad1.right_trigger - gamepad1.left_trigger);

            /* Telemetry */
            telemetry.addData("Lift Error", robot.lift.getPositionError());
            telemetry.update();
        }
    }
}

