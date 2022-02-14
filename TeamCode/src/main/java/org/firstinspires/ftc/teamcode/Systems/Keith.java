package org.firstinspires.ftc.teamcode.Systems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Libraries.Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Basket;
import org.firstinspires.ftc.teamcode.Subsystems.CameraHardware;
import org.firstinspires.ftc.teamcode.Subsystems.CameraHardwareAprilTag;
import org.firstinspires.ftc.teamcode.Subsystems.DuckSpinner;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Lift;
import org.firstinspires.ftc.teamcode.Subsystems.SampleMecanumDrive;

public class Keith {
    public SampleMecanumDrive drivetrain;
    public Lift lift;
    public Basket basket;
    public Intake intake;
    public DuckSpinner duckSpinner;
    public CameraHardwareAprilTag vision;
    Subsystem[] subsystems;

    public Keith(HardwareMap hwMap){
        drivetrain = new SampleMecanumDrive(hwMap);
        lift = new Lift(hwMap);
        intake = new Intake(hwMap);
        basket = new Basket(hwMap);
        duckSpinner = new DuckSpinner(hwMap);
        vision = new CameraHardwareAprilTag(hwMap);
        subsystems = new Subsystem[]{lift, basket, intake, duckSpinner, vision};
    }
    public void getData(Telemetry telemetry){
        for(Subsystem sub : subsystems){
            sub.getData(telemetry);
        }
    }

}
