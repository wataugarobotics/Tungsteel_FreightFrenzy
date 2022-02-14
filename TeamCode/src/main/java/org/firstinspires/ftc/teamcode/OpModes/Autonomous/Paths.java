package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.Libraries.Roadrunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Systems.Keith;

import java.util.Vector;

public class Paths {
    Keith robot;

    public Paths(Keith robot) {
        this.robot = robot;
    }

    public Pose2d shippingHub = new Pose2d(12, 24);
    public Pose2d sideHome;
    public Pose2d inFrontOfHub = new Pose2d(12, 38, Math.toRadians(-90));
    public Pose2d duckPlace = new Pose2d(54, 48, Math.toRadians(0));
    public Pose2d parkingSpace = new Pose2d(-36,60,Math.toRadians(180));
    public Pose2d readyToGo = new Pose2d(-13, 58, Math.toRadians(180));
    public Pose2d home;


    public void setHome(Pose2d home) {
        this.home = home;
        sideHome = new Pose2d(home.getX(), home.getY(), Math.toRadians(180));
    }


    public TrajectorySequence placeBlock(Keith robot, int level) {
        return robot.drivetrain.trajectorySequenceBuilder(robot.drivetrain.getPoseEstimate())
                .lineToLinearHeading(this.inFrontOfHub)
                .addSpatialMarker(new Vector2d(-12,45),() ->
                        robot.lift.setLevel(level == 0 ? 3 : level)
                )
                .forward(4)
                .addTemporalMarker(() ->
                        robot.basket.open()
                )
                .back(4)
                .addTemporalMarker(()->robot.basket.close())
                .waitSeconds(.2)
                .addTemporalMarker(() -> {
                            robot.lift.setPosition(0);
                        }
                )
                .build();
    }
    public TrajectorySequence goToHub(Keith robot){
        return robot.drivetrain.trajectorySequenceBuilder(robot.drivetrain.getPoseEstimate())
                .lineToLinearHeading(this.inFrontOfHub)
                .build();
    }
    public TrajectorySequence goHome(Keith robot){
        return robot.drivetrain.trajectorySequenceBuilder(robot.drivetrain.getPoseEstimate())
                .lineToLinearHeading(this.readyToGo)
                .strafeRight(3)
                .build();
    }
    TrajectorySequence duckSpin(Keith robot){
        return robot.drivetrain.trajectorySequenceBuilder(robot.drivetrain.getPoseEstimate())
                .lineToLinearHeading(duckPlace)
                .addTemporalMarker(()->robot.duckSpinner.setPower(.8))
                .waitSeconds(3.5)
                .addTemporalMarker(()->robot.duckSpinner.setPower(0))
                .build();
    }
    TrajectorySequence park(Keith robot) {
       return robot.drivetrain.trajectorySequenceBuilder(robot.drivetrain.getPoseEstimate())
                .lineToLinearHeading(parkingSpace)
                .build();
    }
    Trajectory lineToPosition(Keith robot, Pose2d endPose){
        return robot.drivetrain.trajectoryBuilder(robot.drivetrain.getPoseEstimate())
                .lineToLinearHeading(endPose)
                .build();
    }


}
