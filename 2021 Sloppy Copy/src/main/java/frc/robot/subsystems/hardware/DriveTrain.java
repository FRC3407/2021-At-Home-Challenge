// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.hardware;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  //DriveBase motors and groups setup
  private SpeedController db_fl = new PWMVictorSPX(Constants.DriveBase.frontleft);
  private SpeedController db_fr = new PWMVictorSPX(Constants.DriveBase.frontright);
  private SpeedController db_bl = new PWMVictorSPX(Constants.DriveBase.backleft);
  private SpeedController db_br = new PWMVictorSPX(Constants.DriveBase.backright);

  private SpeedControllerGroup db_left = new SpeedControllerGroup(db_fl, db_bl);
  private SpeedControllerGroup db_right = new SpeedControllerGroup(db_fr, db_br);
  
  private DifferentialDrive drive_main = new DifferentialDrive(db_left, db_right);

//Constructor method
  /** Creates a new DriveTrain. */
  public DriveTrain(){
    db_right.setInverted(Constants.DriveBase.invert_right);
    db_left.setInverted(Constants.DriveBase.invert_left);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**https://www.desmos.com/calculator/x7irxhldzv
   * **/
  public double boostAlg(double input){
    return MathUtil.clamp((input + Math.copySign(input, Constants.boost))/MathUtil.clamp(((Constants.boost + Math.abs(Constants.GeneralInput.sensitivity))/Constants.speed_limit), 0.5, 1.375), -Constants.speed_limit, Constants.speed_limit);
  }

  public void tank_drive(double left_speed, double right_speed){
    drive_main.tankDrive(left_speed, right_speed, Constants.default_smoothing);
  }

  public void tank_drive(double left_speed, double right_speed, boolean boost){
    if(boost){
      drive_main.tankDrive(boostAlg(left_speed), boostAlg(right_speed), Constants.default_smoothing);
    }else{
      drive_main.tankDrive(left_speed, right_speed, Constants.default_smoothing);
    }
  }

  public void arcade_drive(double x_axis, double y_axis){
    drive_main.arcadeDrive(x_axis, y_axis, Constants.default_smoothing);
  }

  public void arcade_drive(double x_axis, double y_axis, boolean boost){
    if(boost){
      drive_main.arcadeDrive(boostAlg(x_axis), boostAlg(y_axis), Constants.default_smoothing);
    }else{
      drive_main.arcadeDrive(x_axis, y_axis, Constants.default_smoothing);
    }
  }

  /**The point of this method is for the controller triggers to be the forward and backwards parameters, and the rotation to be the x-axis of one of the sticks (left and right) */
  public void race_drive(double forward, double backward, double rotation){
    double cumulative = forward - backward;
    drive_main.arcadeDrive(cumulative, rotation, Constants.default_smoothing);
  }

  public void race_drive(double forward, double backward, double rotation, boolean boost){
    double cumulative = forward - backward;
    if(boost){
      drive_main.arcadeDrive(boostAlg(cumulative), boostAlg(rotation), Constants.default_smoothing);
    }else{
      drive_main.arcadeDrive(cumulative, rotation, Constants.default_smoothing);
    }
  }

  public void trigger_drive(double ltrig, double rtrig){
    drive_main.tankDrive(ltrig, rtrig, Constants.default_smoothing);
  }

  public void trigger_drive(double ltrig, double rtrig, boolean boost){
    if(boost){
      drive_main.tankDrive(boostAlg(ltrig), boostAlg(rtrig), Constants.default_smoothing);
    }else{
      drive_main.tankDrive(ltrig, rtrig, Constants.default_smoothing);
    }
  }

  //basic side control
  public void leftmotors(double speed){
    db_left.set(speed);
  }

  public void rightmotors(double speed){
    db_right.set(speed);
  }

  public double leftspeed(){
    return db_left.get();
  }

  public double rightspeed(){
    return db_right.get();
  }
}