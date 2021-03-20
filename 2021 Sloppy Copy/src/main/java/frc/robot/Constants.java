// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.analog.adis16470.frc.ADIS16470_IMU.ADIS16470CalibrationTime;
import com.analog.adis16470.frc.ADIS16470_IMU.IMUAxis;
import com.revrobotics.ColorSensorV3.ColorSensorMeasurementRate;
import com.revrobotics.ColorSensorV3.ColorSensorResolution;
import com.revrobotics.ColorSensorV3.GainFactor;

import edu.wpi.first.wpilibj.I2C;

public final class Constants{
    /** This file containens all variables used that are CONSTANT. These should not be changed when the program is running, those variables are in DYNAMICS. 
     *  Variables that are likely to be changed most often (between robot runs) are at the top, variables that will likely never be changed are at the bottom. 
     */

    // * * * * Controller Stick Input Modification * * * * 

        //multipliers for each stick axis
        public static final double c1_left_Y_mult = -0.5;
        public static final double c1_right_Y_mult = -0.5;
        public static final double c1_left_X_mult = -0.5;
        public static final double c1_right_X_mult = -0.5;

        //deadzone on controller sticks -> only set for drivebase control atm
        public static final double deadzone = 0.4;
        
        //takes the controller stick output value to the power of this number (along with multiplying it by the above values)
        public static final int power = 2;

        //the control mode controller mode that will be started with
        public static final String defaultcontrolmode = "xbox";

    // * * * * Other software values * * * * 

        //deceleration multiplier
        public static final double deceleration_mult = 0.98;

    
    // * * * * PERIODIC SENSOR TOGGLES * * * *
        public static boolean colorsrc_periodic = true;
        public static boolean imu_periodic = true;
        public static boolean db_periodic = true;
        public static boolean input_periodic = true;


    // * * * * HARDWARE PORT CONFIG  * * * *   
    
        //controller port
        public static final int controller_port = 0;
        
        //drivebase motors (channels)
        public static final int front_left_chan = 3;
        public static final int front_right_chan = 1;
        public static final int back_left_chan = 2;
        public static final int back_right_chan = 0;

        //Talon SRX's (can id's)
        public static final int falcon1_canid = 0;
        public static final int falcon2_canid = 1;

        //camera array (ports)
        public static final int cam1_port = 0;
        public static final int cam2_port = 1;
        public static final int cam3_port = 2;

        //ultrasonic(s?)
        public static final int ultrasonic1_port = 0;

        //colorsensor - figure out how multiple colorsensor identification works
        public static final I2C.Port colorsensor_port = I2C.Port.kOnboard;
        public static final ColorSensorResolution color_res = ColorSensorResolution.kColorSensorRes20bit;
        public static final ColorSensorMeasurementRate color_rate = ColorSensorMeasurementRate.kColorRate25ms;      // <- set to 50ms if there is a problem, but try not to go lower than that
        public static final GainFactor color_gain = GainFactor.kGain1x;         // <- find out what this is, make sure 1 is the default
        //public static final I2C.Port colorsensor2_port = I2C.Port.kOnboard;
                
        //IMU/gyro
        public static final IMUAxis imu_yaw = IMUAxis.kZ;
        public static final ADIS16470CalibrationTime imu_caltime = ADIS16470CalibrationTime._8s;


    // * * * * Colorsensor Calibration Values * * * *

        // note: switch to colorsensing/matching library

        //colorsensor calibration arrays
        public static final double[] yellow = {0.582, 0.382, 0.957, 0.757, 0.082, 0.282};     //calibration arrays are in the form of the 
        public static final double[] green = {0.49, 0.49, 0.86, 0.79, 0.38, 0.17};            //lowest followed by the highest 
        public static final double[] cyan = {0.48, 0.18, 0.8, 0.67, 0.75, 0.4};               //values of the individual RGB('s) that make 
        public static final double[] red = {0.82, 0.48, 0.8, 0.54, 0.75, 0.18};               //up the color that they are calibrating

        //autonomous line following
        public static final double linecolor[] = {0.48, 0.18, 0.8, 0.67, 0.75, 0.4};
        public static final double floorcolor[] = {0.0, 0.1, 0.0, 0.1, 0.0, 0.1};
        public static final double linespeed = 0.25;


    // * * * * Controller Layouts * * * * 
    
        //drivemodes
        public static String drivemodes[] = {"tank", "arcade", "race", "trigger"};

        //xbox controller keybinds
        public static final int x_controllerButton_A = 1;
        public static final int x_controllerButton_B = 2;
        public static final int x_controllerButton_X = 3;
        public static final int x_controllerButton_Y = 4;
        public static final int x_controllerButton_menu = 8;
        public static final int x_controllerButton_home = 7;
        public static final int x_controllerButton_lb = 5;
        public static final int x_controllerButton_rb = 6;
        public static final int x_controllerButton_ls = 9;
        public static final int x_controllerButton_rs = 10;
        public static final int x_controllerStick_ly = 1;
        public static final int x_controllerStick_lx = 0;
        public static final int x_controllerStick_ry = 5;
        public static final int x_controllerStick_rx = 4;
        public static final int x_controllerTrigger_l = 2;
        public static final int x_controllerTrigger_r = 3;
        //logitech controller keybinds
        public static final int l_controllerButton_A = 1;
        public static final int l_controllerButton_B = 2;
        public static final int l_controllerButton_X = 0;
        public static final int l_controllerButton_Y = 3;
        public static final int l_controllerButton_menu = 9;
        public static final int l_controllerButton_home = 10;
        // public static final int l_controllerButton_lb = ;
        // public static final int l_controllerButton_rb = ;
        // public static final int l_controllerButton_ls = ;
        // public static final int l_controllerButton_rs = ;
        public static final int l_controllerStick_ly = 1;
        public static final int l_controllerStick_lx = 0;
        public static final int l_controllerStick_ry = 3;
        public static final int l_controllerStick_rx = 2;
        public static final int l_controllerTrigger_l = 0;
        public static final int l_controllerTrigger_r = 0;

}