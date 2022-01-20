package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Libraries.Subsystem;

public class Basket extends Subsystem {
    private final Servo servo;
    final double OPEN_POSITION = 1;
    final double CLOSED_POSITION = 0;
    private boolean isOpen;
    public Basket(HardwareMap hwMap){
        servo = hwMap.get(Servo.class, "servo");
        isOpen = false;
    }
    public void setPosition(double position){
        servo.setPosition(position);
    }
    public void open(){
        setPosition(OPEN_POSITION);
        isOpen = true;
    }
    public void close(){
        setPosition(CLOSED_POSITION);
        isOpen=false;
    }
    public void toggle(){
        if (isOpen) {
            close();
        } else {
            open();
        }
    }

}
