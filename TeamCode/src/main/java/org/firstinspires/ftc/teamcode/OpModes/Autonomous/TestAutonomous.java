package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Libraries.Roadrunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Systems.Keith;
import org.openftc.apriltag.AprilTagPose;

@Autonomous(name="Auto Test",group="Tungsteel 21-22")

public class TestAutonomous extends LinearOpMode {
    Keith robot;
    Paths paths;
    AprilTagPose lastPose = null;


    @Override

    public void runOpMode() throws InterruptedException {
        robot = new Keith(hardwareMap);
        paths = new Paths(robot);
        int level = 0;
        if(robot.vision.getSingleDetection() != null) level = robot.vision.whichDuck();

        paths.setHome(new Pose2d(-12,58,Math.toRadians(-90)));
        robot.drivetrain.setPoseEstimate(paths.home);
       // while(level == 0){
         //   level = robot.cameraHardware.whichDuck();
        //}

        waitForStart();


        if(isStopRequested()) return;
        robot.lift.setLevel(0);
        robot.basket.close();


        robot.drivetrain.followTrajectorySequence(paths.placeBlock(robot, level));
        robot.drivetrain.followTrajectorySequence(paths.duckSpin(robot));
        robot.drivetrain.followTrajectorySequence(paths.park(robot));

        driverPortion();
    }
    void driverPortion(){
        while (!isStopRequested()) {
            robot.drivetrain.setWeightedDrivePower(
                    new Pose2d(
                            -gamepad1.left_stick_y,
                            -gamepad1.left_stick_x,
                            -gamepad1.right_stick_x
                    )
            );

            robot.drivetrain.update();

            Pose2d poseEstimate = robot.drivetrain.getPoseEstimate();
            telemetry.addData("y", poseEstimate.getY());

            telemetry.addData("x", poseEstimate.getX());
            telemetry.addData("heading", poseEstimate.getHeading());
            telemetry.update();
        }
    }
    void relocalize(){
        Pose2d relocalizedPose = new Pose2d(
                robot.drivetrain.getPoseEstimate().getX(),
                64,
                180
        );
        robot.drivetrain.setPoseEstimate(relocalizedPose);
    }

}
