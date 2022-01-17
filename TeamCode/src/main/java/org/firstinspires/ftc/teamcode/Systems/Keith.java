package org.firstinspires.ftc.teamcode.Systems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Basket;
import org.firstinspires.ftc.teamcode.Subsystems.CameraHardware;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.DuckSpinner;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Lift;

public class Keith {
    public Drivetrain drivetrain;
    public Lift lift;
    public Basket basket;
    public Intake intake;
    public DuckSpinner duckSpinner;
    public CameraHardware cameraHardware;

    public Keith(HardwareMap hwMap){
        drivetrain = new Drivetrain(hwMap);
        lift = new Lift(hwMap);
    }
}
