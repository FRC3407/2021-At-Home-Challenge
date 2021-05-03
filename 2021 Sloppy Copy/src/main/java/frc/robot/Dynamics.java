package frc.robot;

import com.revrobotics.CIEColor;

import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Constants.DriveModes;

/**This file contains variables that can be updated and changed while the robot is running, so they can be used to help things update after button presses, help commands communicate, etc. 
 */
public final class Dynamics {
    //the time between scheduler runs (normally should be 0.02, but why not get the actual value) -> updated from RobotPeriodic
    public static double periodtime;

    public static int dmode_index = 0;
    public static DriveModes drivemode = Constants.default_mode;

    public static final class DataBus{
        //colorsrc
        public static double red;
        public static double green;
        public static double blue;
        public static double infrared;
        public static double proximity;
        public static Color closestColor;
        public static CIEColor colorposition;

        //imu
        public static double initAngle;
        public static double currentAngle;
        public static double accelerationX;
        public static double accelerationY;
        public static double velocityX;
        public static double velocityY;
        public static double distanceX;
        public static double distanceY;
        
        //ultrasonic

        //userinput?
    }
}