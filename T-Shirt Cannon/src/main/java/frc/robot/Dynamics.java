package frc.robot;

import frc.robot.Constants.DriveModes;

/**This file contains variables that can be updated and changed while the robot is running, so they can be used to help things update after button presses, help commands communicate, etc. 
 */
public final class Dynamics {
    //the time between scheduler runs (normally should be 0.02, but why not get the actual value) -> updated from RobotPeriodic
    public static double periodtime;

    public static int dmode_index = 0;
    public static DriveModes drivemode = Constants.default_mode;

}