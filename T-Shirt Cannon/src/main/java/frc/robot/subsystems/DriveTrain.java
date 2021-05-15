// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  private SpeedController left = new PWMVictorSPX(Constants.DriveBase.left);
  private SpeedController right = new PWMVictorSPX(Constants.DriveBase.right);
  
  private DifferentialDrive drive_main = new DifferentialDrive(left, right);

  public DriveTrain(){
    right.setInverted(Constants.DriveBase.invert_right);
    left.setInverted(Constants.DriveBase.invert_left);
  }

  public void tank_drive(double left_speed, double right_speed){
    drive_main.tankDrive(left_speed, right_speed, Constants.default_smoothing);
  }

  public void arcade_drive(double x_axis, double y_axis){
    drive_main.arcadeDrive(x_axis, y_axis, Constants.default_smoothing);
  }

  public void race_drive(double forward, double backward, double rotation){
    double cumulative = forward - backward;
    drive_main.arcadeDrive(cumulative, rotation, Constants.default_smoothing);
  }

  public void trigger_drive(double ltrig, double rtrig){
    drive_main.tankDrive(ltrig, rtrig, Constants.default_smoothing);
  }

  public void leftmotors(double speed){
    left.set(speed);
  }

  public void rightmotors(double speed){
    right.set(speed);
  }

  public double leftspeed(){
    return left.get();
  }

  public double rightspeed(){
    return right.get();
  }
}