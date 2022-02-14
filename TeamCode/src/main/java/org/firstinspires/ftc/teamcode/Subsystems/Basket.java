package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Libraries.Subsystem;

public class Basket extends Subsystem {
    private final Servo servo;
    private final double OPEN_POSITION = .5;
    private final double CLOSED_POSITION = 1;
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
    public double getPosition(){
        return servo.getPosition();
    }
    public void getData(Telemetry telemetry){
        telemetry.addLine("Servo:")
                .addData("Is Open", isOpen)
                .addData("Position", servo.getPosition())
                ;
    }
}
