package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Grabber extends Subsystem{
    private Servo grabber;
    private boolean grabberDown = false;
    public Grabber(HardwareMap hwMap){
        grabber = hwMap.get(Servo.class, "Servo");
    }
    public void close(){
        grabber.setPosition(0);
        grabberDown = true;
    }
    public void open(){
        grabber.setPosition(1);
        grabberDown = false;
    }
    public void toggle(){
        if(grabberDown) open();
        else if(!grabberDown) close();
        grabberDown = !grabberDown;
    }
    public boolean isGrabbed(){
        return grabberDown;
    }
}
