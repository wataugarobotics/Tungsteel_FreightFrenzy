package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Systems.CameraBot;
import org.openftc.apriltag.AprilTagPose;

@Autonomous(name="Camera Test",group="Tungsteel 21-22")
public class CameraTest extends LinearOpMode {
    CameraBot robot;
    public void runOpMode() throws InterruptedException {
        robot = new CameraBot(hardwareMap);
        AprilTagPose lastPose = null;
        waitForStart();
        while(opModeIsActive()){
            if(robot.vision.getDetections() != null && robot.vision.getDetections().length !=0) lastPose = robot.vision.getSingleDetection().pose;
            if(lastPose != null) {
                telemetry.addData("X", (int) (lastPose.x*1000));
                telemetry.addData("Y", (int) (lastPose.y*1000));
                telemetry.addData("Z", (int) (lastPose.z*1000));
                telemetry.addData("Roll", (int) (lastPose.roll*1000));
                telemetry.addData("Pitch", (int) (lastPose.pitch*1000));
                telemetry.addData("Yaw", (int) (lastPose.yaw*1000));
                //telemetry.addData("Center point", robot.vision.getDetections()[0].center.toString());
                telemetry.update();
            }


        }
    }
}
