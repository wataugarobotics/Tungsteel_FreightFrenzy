package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.firstinspires.ftc.teamcode.Libraries.AprilTagDetectionPipeline;
import org.firstinspires.ftc.teamcode.Libraries.Subsystem;
import org.opencv.core.Point;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.apriltag.AprilTagPose;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.annotation.CheckForNull;

// Box positions on line 62

class CameraHardware extends Subsystem {
    OpenCvCamera camera;
    DuckFinderPipeline DuckFinderPipeline;
    int framesWithoutDetection = 0;
    /* ***** CONFIGURATION ***** */
    double tagsize = 0.0508; //Meters
    // Lens intrinsics  // CONFIGURED WITH ZEPHYR BY KYLE
    double fx = 1038.02; //
    double fy = 1038.02; //
    double cx = 630.346; //
    double cy = 497.208; //
    //                   //
    /* ************************* */
    public CameraHardware(HardwareMap hwMap){
        // This chunk of code sets up the camera, and feeds the images captured from the camera to the EOCV pipeline
        int cameraMonitorViewId = hwMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hwMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);
        aprilTagDetectionPipeline.setDecimation(3);
        camera.setPipeline(aprilTagDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(1280, 960, OpenCvCameraRotation.UPRIGHT);
            }
            @Override
            public void onError(int errorCode) {
            }
        });
    }

    class DuckFinderPipeline extends OpenCvPipeline {

        public enum DuckPosition { // First is bottom level of the shipping hub
            FIRST,
            SECOND,
            THIRD
        }

        static final Scalar BLUE = new Scalar(0, 0, 255); // Color constant for box drawing
        
        // The following values define the regions
        static final Point REGION1_ANCHOR = new Point(0, 0); // Top left of rectangle
        static final Point REGION2_ANCHOR = new Point(1, 0);
        static final Point REGION3_ANCHOR = new Point(2, 0);
        final int REGION_WIDTH = 1;
        final int REGION_HEIGHT = 1;

        Point region1_pointA = new Point(
            REGION1_ANCHOR.x,
            REGION1_ANCHOR.y);
        Point region1_pointB = new Point(
            REGION1_ANCHOR.x + REGION_WIDTH,
            REGION1_ANCHOR.y + REGION_HEIGHT);
    
        Point region2_pointA = new Point(
            REGION2_ANCHOR.x,
            REGION2_ANCHOR.y);
        Point region2_pointB = new Point(
            REGION2_ANCHOR.x + REGION_WIDTH,
            REGION2_ANCHOR.y + REGION_HEIGHT);

        Point region3_pointA = new Point(
            REGION3_ANCHOR.x,
            REGION3_ANCHOR.y);
        Point region3_pointB = new Point(
            REGION3_ANCHOR.x + REGION_WIDTH,
            REGION3_ANCHOR.y + REGION_HEIGHT);

        Mat region1_Y, region2_Y, region3_Y; // Working variables
        Mat YCrCb = new Mat();
        Mat Y = new Mat();
        int avg1, avg2, avg3;

        private volatile DuckPosition position = DuckPosition.FIRST;

        void inputToY(Mat input) { // Extracts Y channel of input
            Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
            Core.extractChannel(YCrCb, Y, 1);
        }

        @Override
        public void init(Mat FirstFrame) {
            inputToY(FirstFrame);

            region1_Y = Y.submat(new Rect(region1_pointA, region1_pointB));
            region2_Y = Y.submat(new Rect(region2_pointA, region2_pointB));
            region3_Y = Y.submat(new Rect(region3_pointA, region3_pointB));
        }

        public Mat processFrame(Mat input) {
            inputToY(input);

            avg1 = (int) Core.mean(region1_Y).val[0];
            avg2 = (int) Core.mean(region2_Y).val[0];
            avg3 = (int) Core.mean(region3_Y).val[0];

            Imgproc.rectangle(
                input,
                region1_pointA,
                region1_pointB,
                BLUE,
                2);
            Imgproc.rectangle(
                input,
                region2_pointA,
                region2_pointB,
                BLUE,
                2);
            Imgproc.rectangle(
                input,
                region3_pointA,
                region3_pointB,
                BLUE,
                2);

            position = DuckPosition.FIRST;
            if(avg1 > avg2 && avg1 > avg3) {
                position = DuckPosition.FIRST;
            } else if (avg2 > avg1 && avg2 > avg3) {
                position = DuckPosition.SECOND;
            } else {
                position = DuckPosition.THIRD;
            }

            return input;
        }

        public int[] getAnalysis() {
            return {avg1, avg2, avg3};
        }
    }
}
