package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public interface ControlInterface {
    public double getPriX(double deadzone, double multiplier, int power);
    public double getSecX(double deadzone, double multiplier, int power);
    public double getPriY(double deadzone, double multiplier, int power);
    public double getSecY(double deadzone, double multiplier, int power);
    public double getPriTrigger(double deadzone, double multiplier, int power);
    public double getSecTrigger(double deadzone, double multiplier, int power);
    public JoystickButton getButton1();
    public JoystickButton getButton2();
    public JoystickButton getButton3();
    public JoystickButton getButton4();
    public JoystickButton getUtility1();
    public JoystickButton getUtility2();
}