package frc.robot;

import com.revrobotics.CIEColor;

import edu.wpi.first.wpilibj.util.Color;

public final class Dynamics {
    /**This file contains variables that can be updated and changed while the robot is running, so they can be used to help things update after button presses, help commands communicate, etc. 
     */

    // * * * * GLOBAL DYNAMIC VARIABLES * * * * 
    //these are variables are for communication between commands dynamically

        //controller button variables -> this this makes it more simple to just access a controller button, and let the actual layout used be determined elsewhere
        public static int controllerButton_A;
        public static int controllerButton_B;
        public static int controllerButton_X;
        public static int controllerButton_Y;
        public static int controllerButton_menu;
        public static int controllerButton_home; 
        public static int controllerButton_lb;
        public static int controllerButton_rb;
        public static int controllerButton_ls;
        public static int controllerButton_rs;
        public static int controllerStick_ly;
        public static int controllerStick_lx;
        public static int controllerStick_ry;
        public static int controllerStick_rx;
        public static int controllerTrigger_l;
        public static int controllerTrigger_r;

        //the time between scheduler runs (normally should be 0.02, but why not get the actual value) -> updated from RobotPeriodic
        public static double periodtime;
        
        // * * * * SUBSYSTEM PERIODIC * * * * 

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
        
        
    // * * * * Controller input modification * * * * 

        //controller layout startup stuff
        public static String controllerlayout = Constants.defaultcontrolmode;
        public static int dmode_index = 0;
        public static String drivemode = Constants.drivemodes[dmode_index];
                    
        //sets automatic squaring inside the tankdrive and arcadedrive functions (turned off if it is aready done in controller input function)
        public static boolean default_squareinp = false;
        //invert boolean for each side
        public static boolean db_left_invt = false;
        public static boolean db_right_invt = true;
        //multipliers for each stick axis
        public static double c1_left_Y_mult = -0.5;
        public static double c1_right_Y_mult = -0.5;
        public static double c1_left_X_mult = -0.5;
        public static double c1_right_X_mult = -0.5;
        //deadzone on controller sticks -> only set for drivebase control atm
        public static double deadzone = 0.4;
        //takes the controller stick output value to the power of this number (along with multiplying it by the above values)
        public static int power = 2;

    // * * * * DYNAMIC SETTINGS * * * *
        public static boolean colorsrc_periodic = Constants.colorsrc_periodic;
        public static boolean imu_periodic = Constants.imu_periodic;
        public static boolean db_periodic = Constants.db_periodic;
        public static boolean input_periodic = Constants.input_periodic;

        public static double deceleration_mult = Constants.deceleration_mult;


    // * * * * METHODS * * * * 

        //constructor -> make sure controller buttons have a value before anything tries to access them
        public Dynamics(){}
}