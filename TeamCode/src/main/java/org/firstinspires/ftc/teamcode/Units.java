package org.firstinspires.ftc.teamcode;

public class Units {
    public static double toIn(double mm){
        return mm * Math.pow(25.4,-1);
    }
    public static double toMm(double in){
        return in * 25.4;
    }
}
