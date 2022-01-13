package org.firstinspires.ftc.teamcode.Systems;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Lift;

public class Pushbot {
    //public Drivetrain drivetrain;
    public Lift lift;
    public Pushbot(HardwareMap hwMap) {
        //drivetrain = new Drivetrain(hwMap);
        lift = new Lift(hwMap);

    }
}
