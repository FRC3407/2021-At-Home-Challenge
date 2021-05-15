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

/** This file containens all variables used that are CONSTANT. These should not be changed when the program is running, those variables are in DYNAMICS. 
 *  Variables that are likely to be changed most often (between robot runs) are at the top, variables that will likely never be changed are at the bottom. 
 */
public final class Constants{

    public static class GeneralInput {
        public static final double sensitivity = 0.5;
        public static final double deadzone = 0.3;
        public static final int smoothing = 2; //(power)

        public static final boolean periodic = false;
        public int smoothing(){return 2;}
        public static double deadzone(){return 0.2;}
        public static double sensitivity(){return -0.5;}

        public static final boolean periodic() {
            return false;
        }
    }

    public static final boolean default_smoothing = false;
    public static final DriveModes default_mode = DriveModes.Tank;
    public static final double deceleration_const = 0.98;
    public static final double speed_limit = 0.8;
    public static final double boost = 0.3;

    public enum DriveModes {
        Tank, Arcade, Race, Trigger;
    }

    public static final class DriveBase {
        public static final int frontleft = 3;
        public static final int frontright = 1;
        public static final int backleft = 2;
        public static final int backright = 0;

        public static final boolean invert_left = true;
        public static final boolean invert_right = true;

        public static final boolean periodic = false;
    }

    public static final class Attachments {
        public static final int falcon1_canid = 0;
        public static final int falcon2_canid = 1;
    }

    public static final class Cameras {
        public static final int cam1_port = 0;
        public static final int cam2_port = 1;
        public static final int cam3_port = 2;
    }

    public static final class ColorSensor {
        public static final I2C.Port location = I2C.Port.kOnboard;
        public static final ColorSensorResolution resolution = ColorSensorResolution.kColorSensorRes20bit;
        public static final ColorSensorMeasurementRate samplerate = ColorSensorMeasurementRate.kColorRate25ms;
        public static final GainFactor gain = GainFactor.kGain1x;

        public static final boolean periodic = false;
    }

    public static final class Ultrasonic {
        public static final int port = 0;
    }

    public static final class IMU {
        public static final IMUAxis yaw = IMUAxis.kZ;
        public static final ADIS16470CalibrationTime calibrationtime = ADIS16470CalibrationTime._8s;

        public static final boolean periodic = false;
    }

    // Xbox controller layout - can also apply to the logitech controller if it is
    // switched that way with the switch on the back of it
    public static final class Xbox extends GeneralInput {
        public static final int Port = 0;

        @Override
        public int smoothing(){return 0;}

        public static final int LX = 0;
        public static final int LY = 1;
        public static final int RX = 4;
        public static final int RY = 5;
        public static final int LT = 2;
        public static final int RT = 3;

        public static final int A = 1;
        public static final int B = 2;
        public static final int X = 3;
        public static final int Y = 4;
        public static final int LB = 5;
        public static final int RB = 6;
        public static final int Home = 7; //*
        public static final int Menu = 8; //*
        public static final int LS = 9;
        public static final int RS = 10;
    }

    //The layout for the logitech controller when not in xbox layout
    public static final class Logi{
        public static final int Port = 1;

        public static final double sensitivity = GeneralInput.sensitivity;
        public static final double deadzone = GeneralInput.deadzone;
        public static final int smoothing = GeneralInput.smoothing;

        public static final int LX = 0;
        public static final int LY = 1;
        public static final int RX = 2;
        public static final int RY = 3;

        public static final int LT = 4; //*
        public static final int RT = 5; //*

        public static final int A = 2;
        public static final int B = 3;
        public static final int X = 1;
        public static final int Y = 4;
        public static final int LB = 5;
        public static final int RB = 6;
        public static final int Home = 10; //*
        public static final int Menu = 9; //*
        public static final int LS = 7;
        public static final int RS = 8;
    }

    //"Logitech Atk3" arcade stick layout - "Top" means that the button is on the head of the stick, S-Axis is the slider on the back
    public static final class Atk3{
        public static final int[] Port = {3, 4};

        public static final double sensitivity = GeneralInput.sensitivity;
        public static final double deadzone = GeneralInput.deadzone;
        public static final int smoothing = GeneralInput.smoothing;

        public static final int X_Axis = 0;
        public static final int Y_Axis = 1;
        public static final int S_Axis = 2;

        public static final int Trigger = 1;

        public static final int Top_Bottom = 2;
        public static final int Top_Top = 3;
        public static final int Top_Left = 4;
        public static final int Top_Right = 5;
        public static final int B1 = 6;
        public static final int B2 = 7;
        public static final int B3 = 8;
        public static final int B4 = 9;
        public static final int B5 = 10;
        public static final int B6 = 11;
    }

    //"Extreme 3D Pro" arcade stick layout - "Top" means on the top of the stick, S-Axis is the slider on the back
    public final class Ex3d{
        public static final int Port = 2;

        public static final double sensitivity = GeneralInput.sensitivity;
        public static final double deadzone = GeneralInput.deadzone;
        public static final int smoothing = GeneralInput.smoothing;

        public static final int X_Axis = 0;
        public static final int Y_Axis = 1;
        public static final int Z_Axis = 2; //rotation
        public static final int S_Axis = 3;

        public static final int Trigger = 1;
        public static final int Side = 2;

        public static final int Top_Left_Bottom = 3;
        public static final int Top_Right_Bottom = 4;
        public static final int Top_Left_Top = 5;
        public static final int Top_Right_Top = 6;
        public static final int B7 = 7;
        public static final int B8 = 8;
        public static final int B9 = 9;
        public static final int B10 = 10;
        public static final int B11 = 11;
        public static final int B12 = 12;
    }
}