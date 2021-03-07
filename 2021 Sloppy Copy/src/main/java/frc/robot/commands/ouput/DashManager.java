// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ouput;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Dynamics;
import frc.robot.Constants;

/**
 * 
 * COPY HERE TO MAKE LIFE EASY(er): SmartDashboard.putNumber, SmartDashboard.putString, SmartDashboard.putBoolean
 * 
 */

public class DashManager extends CommandBase {
  private boolean imu = false, color = false, input = false, sonic = false, config = false, ports = false, camerafeed = false, sensor_only = false; 
  
  /**
   * Use the booleans to determine what data should be output to SmartDashboard 
  */
  public DashManager(boolean sensordata_only, boolean imu, boolean colorsensor, boolean inputdata, boolean ultrasonic, boolean config, boolean cameras, boolean ports) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.imu = imu;
    this.color = colorsensor;
    this.input = inputdata;
    this.sonic = ultrasonic;
    this.config = config;
    this.camerafeed = cameras;
    this.ports = ports;
    this.sensor_only = sensordata_only;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(!sensor_only){
      if(imu){
        SmartDashboard.putString("IMU Yaw Axis", String.valueOf(Constants.imu_yaw));
        SmartDashboard.putString("IMU Calibration Time", String.valueOf(Constants.imu_caltime));
      }
      if(color){
        SmartDashboard.putString("Colorsensor 'Port'", String.valueOf(Constants.colorsensor_port));
      }
      if(input){
        
      }
      if(sonic){
        SmartDashboard.putNumber("Ultrasonic Port", Constants.ultrasonic1_port);
      }
      if(config){
        SmartDashboard.putString("Controller Layout", Dynamics.controllerlayout);
        SmartDashboard.putString("Drive Mode", Dynamics.drivemode);
        SmartDashboard.putBoolean("Default Input Squaring?", Dynamics.default_squareinp);
        SmartDashboard.putBoolean("Left Motors Inverted?", Dynamics.db_left_invt);
        SmartDashboard.putBoolean("Right Motors Inverted?", Dynamics.db_right_invt);
        SmartDashboard.putNumber("Left Stick Y Axis Multiplier", Dynamics.c1_left_Y_mult);
        SmartDashboard.putNumber("Left Stick X Axis Multiplier", Dynamics.c1_left_X_mult);
        SmartDashboard.putNumber("Right Stick Y Axis Multiplier", Dynamics.c1_right_Y_mult);
        SmartDashboard.putNumber("Right Stick X Axis Multiplier", Dynamics.c1_right_X_mult);
        SmartDashboard.putNumber("Controller Stick Deadzone", Dynamics.deadzone);
        SmartDashboard.putNumber("Stick Input [Power Of]", Dynamics.power);      
      }
      if(camerafeed){
        SmartDashboard.putNumber("Camera 1 port", Constants.cam1_port);
        SmartDashboard.putNumber("Camera 2 port", Constants.cam2_port);
        SmartDashboard.putNumber("Camera 3 port", Constants.cam3_port);
      }
      if(ports){
        SmartDashboard.putNumber("DriveBase Front Left", Constants.front_left_chan);
        SmartDashboard.putNumber("DriveBase Front Right", Constants.front_right_chan);
        SmartDashboard.putNumber("DriveBase Back Left", Constants.back_left_chan);
        SmartDashboard.putNumber("DriveBase Back Right", Constants.back_right_chan);
        SmartDashboard.putNumber("Controller Port", Constants.controller_port);
        //SmartDashboard.putNumber("Falcon _ CanID", Constants.falcon1_canid);
        //SmartDashboard.putNumber("Falcon _ CanID", Constants.falcon2_canid);
      }
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(imu){
      SmartDashboard.putNumber("Initial Angle", Dynamics.initAngle);
      SmartDashboard.putNumber("IMU Angle", Dynamics.currentAngle);
      SmartDashboard.putNumber("Distance X(axis)", Dynamics.distanceX);
      SmartDashboard.putNumber("Distance Y(axis)", Dynamics.distanceY);
      SmartDashboard.putNumber("Acceleration X", Dynamics.accX);
      SmartDashboard.putNumber("Acceleration Y", Dynamics.accY);
      SmartDashboard.putNumber("Acceleration Z", Dynamics.accZ);
    }
    if(color){
      SmartDashboard.putNumber("Colorsensor Red", Dynamics.red);
      SmartDashboard.putNumber("Colorsensor Green", Dynamics.green);
      SmartDashboard.putNumber("Colorsensor Blue", Dynamics.blue);
    }
    if(input){
      
    }
    if(sonic){
      
    }
    if(config){
            
    }
    if(camerafeed){
      
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
