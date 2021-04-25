// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ouput;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Dynamics;
import frc.robot.Constants;

public class DashManager extends CommandBase {
  private boolean imu = false, color = false, input = false, sonic = false, config = false, ports = false, camerafeed = false, sensor_only = false; 
  
  /**
   * Use the booleans to determine what data should be output to SmartDashboard 
  */
  public DashManager() {
    // Use addRequirements() here to declare subsystem dependencies.
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(!sensor_only){
      if(imu){
        imustatic();
      }
      if(color){
        colorstatic();
      }
      if(input){
        inputstatic();
      }
      if(sonic){
        sonicstatic();
      }
      if(config){
        configstatic();
      }
      if(camerafeed){
        camerastatic();
      }
      if(ports){
        portsstatic();
      }
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(imu){
      imulive();
    }
    if(color){
      colorlive();
    }
    if(input){
      inputlive();
    }
    if(sonic){
      soniclive();
    }
    if(config){
      configlive();
    }
    if(camerafeed){
      cameralive();
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

  private void num(String desc, double num){
    SmartDashboard.putNumber(desc, num);
  }

  private void bool(String desc, boolean bool){
    SmartDashboard.putBoolean(desc, bool);
  }

  private void str(String desc, String str){
    SmartDashboard.putString(desc, str);
  }

  private void imulive(){
    num("IMU Angle", Dynamics.currentAngle);
    num("Acceleration X", Dynamics.accelerationX);
    num("Acceleration Y", Dynamics.accelerationY);
    num("Velocity X", Dynamics.velocityX);
    num("Velocity Y", Dynamics.velocityY);
    num("Distance X(axis)", Dynamics.distanceX);
    num("Distance Y(axis)", Dynamics.distanceY);
    
  }

  private void colorlive(){
    num("Colorsensor Red", Dynamics.red);
    num("Colorsensor Green", Dynamics.green);
    num("Colorsensor Blue", Dynamics.blue);
  }

  private void inputlive(){

  }

  private void soniclive(){

  }

  private void configlive(){

  }

  private void cameralive(){

  }

// * * * * * * * * * * * * * 

  private void imustatic(){
    num("Initial Angle", Dynamics.initAngle);
    str("IMU Yaw Axis", String.valueOf(Constants.imu_yaw));
    str("IMU Calibration Time", String.valueOf(Constants.imu_caltime));
  }

  private void colorstatic(){
    str("Colorsensor 'Port'", String.valueOf(Constants.colorsensor_port));
  }

  private void inputstatic(){
    num("Controller Port", Constants.Ports.Xbox);
  }

  private void sonicstatic(){
    num("Ultrasonic Port", Constants.ultrasonic1_port);
  }

  private void configstatic(){
    str("Controller Layout", Dynamics.controllerlayout);
    str("Drive Mode", Dynamics.drivemode);
    bool("Default Input Squaring?", Dynamics.default_squareinp);
    bool("Left Motors Inverted?", Dynamics.db_left_invt);
    bool("Right Motors Inverted?", Dynamics.db_right_invt);
    num("Left Stick Y Axis Multiplier", Dynamics.c1_left_Y_mult);
    num("Left Stick X Axis Multiplier", Dynamics.c1_left_X_mult);
    num("Right Stick Y Axis Multiplier", Dynamics.c1_right_Y_mult);
    num("Right Stick X Axis Multiplier", Dynamics.c1_right_X_mult);
    num("Controller Stick Deadzone", Dynamics.deadzone);
    num("Stick Input [Power Of]", Dynamics.power); 
  }

  private void camerastatic(){
    num("Camera 1 port", Constants.cam1_port);
    num("Camera 2 port", Constants.cam2_port);
    num("Camera 3 port", Constants.cam3_port);
  }

  private void portsstatic(){
    num("DriveBase Front Left", Constants.front_left_chan);
    num("DriveBase Front Right", Constants.front_right_chan);
    num("DriveBase Back Left", Constants.back_left_chan);
    num("DriveBase Back Right", Constants.back_right_chan);
    //SmartDashboard.putNumber("Falcon _ CanID", Constants.falcon1_canid);
    //SmartDashboard.putNumber("Falcon _ CanID", Constants.falcon2_canid);
  }
}