package org.firstinspires.ftc.teamcode.OpModes.DriverControlled;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Libraries.Subsystem;
import org.firstinspires.ftc.teamcode.OpModes.Autonomous.Paths;
import org.firstinspires.ftc.teamcode.Systems.Keith;


@TeleOp(name="TwoDriver",group="Tungsteel 21-22")
public class TwoDriver extends LinearOpMode { @Override
    public void runOpMode() { /* hardwareMap is within the scope of this method, not outside!! */
        Keith robot = new Keith(hardwareMap);
        Paths paths = new Paths(robot);
        GamepadEx gamepad1ex = new GamepadEx(gamepad1);
        GamepadEx gamepad2ex = new GamepadEx(gamepad2);
        robot.lift.setPower(1);
        waitForStart();
        while (opModeIsActive()) {
            gamepad1ex.readButtons();

            /* Gamepad 1 == Lift Operator */
            if(gamepad1ex.wasJustPressed(GamepadKeys.Button.LEFT_BUMPER))
                robot.lift.subPosition();
            if(gamepad1ex.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER))
                robot.lift.addPosition();
            if(gamepad1ex.wasJustPressed(GamepadKeys.Button.A))
                robot.basket.toggle();
            if(gamepad1ex.wasJustPressed(GamepadKeys.Button.X))
                robot.duckSpinner.toggle();
            robot.intake.spinTake(gamepad1.right_trigger - gamepad1.left_trigger);

            /* Gamepad 2 == Drivetrain operator */
            robot.drivetrain.setWeightedDrivePower(
                    new Pose2d(
                            -gamepad2.left_stick_y,
                            -gamepad2.left_stick_x,
                            -gamepad2.right_stick_x
                    )
            );
            if(gamepad2ex.wasJustPressed(GamepadKeys.Button.A)) robot.drivetrain.followTrajectorySequence(
                    paths.goToHub(robot)
            );
            if(gamepad2ex.wasJustPressed(GamepadKeys.Button.B)) robot.drivetrain.followTrajectorySequence(
                    paths.goHome(robot)
            );
            /* Telemetry */
            robot.getData(telemetry);
            telemetry.update();
        }
    }
}

