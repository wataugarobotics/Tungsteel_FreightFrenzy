package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;

public class Controller {
    public boolean a;
    public boolean b;
    public boolean x;
    public boolean y;
    public boolean dpad_down;
    public boolean dpad_left;
    public boolean dpad_up;
    public boolean dpad_right;
    public boolean left_bumper;
    public boolean right_bumper;

    public Controller(Gamepad oldPad) {
        super();
        a = oldPad.a;
        b = oldPad.b;
        x = oldPad.x;
        y = oldPad.y;
        dpad_down = oldPad.dpad_down;
        dpad_left = oldPad.dpad_left;
        dpad_up = oldPad.dpad_up;
        dpad_right = oldPad.dpad_right;
        left_bumper = oldPad.left_bumper;
        right_bumper = oldPad.right_bumper;
    }

}
