// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.hardware;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import frc.robot.Constants;
import frc.robot.Dynamics;
import frc.robot.RobotContainer;

public class DriveTrain extends SubsystemBase {
  //DriveBase motors and groups setup
  private SpeedController db_fl = new PWMVictorSPX(Constants.front_left_chan);
  private SpeedController db_fr = new PWMVictorSPX(Constants.front_right_chan);
  private SpeedController db_bl = new PWMVictorSPX(Constants.back_left_chan);
  private SpeedController db_br = new PWMVictorSPX(Constants.back_right_chan);
  private SpeedControllerGroup db_left = new SpeedControllerGroup(db_fl, db_bl);
  private SpeedControllerGroup db_right = new SpeedControllerGroup(db_fr, db_br);
  private DifferentialDrive drive_main = new DifferentialDrive(db_left, db_right);

//Constructor method
  /** Creates a new DriveTrain. */
  public DriveTrain(){
    db_right.setInverted(Dynamics.db_right_invt);
    db_left.setInverted(Dynamics.db_left_invt);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }


  // * * * * * Start of custom methods * * * * * 

  public void tank_drive(double left_speed, double right_speed){
    drive_main.tankDrive(left_speed, right_speed, Dynamics.default_squareinp);
  }

  public void arcade_drive(double x_axis, double y_axis){
    drive_main.arcadeDrive(x_axis, y_axis, Dynamics.default_squareinp);
  }

  /**The point of this method is for the controller triggers to be the forward and backwards parameters, and the rotation to be the x-axis of one of the sticks (left and right) */
  public void race_drive(double forward, double backward, double rotation){
    double cumulative = forward - backward;
    drive_main.arcadeDrive(cumulative, rotation, Dynamics.default_squareinp);
  }

  public void trigger_drive(double ltrig, double rtrig){
    drive_main.tankDrive(ltrig, rtrig, Dynamics.default_squareinp);
  }

  //basic side control
  public void leftmotors(double speed){
    db_left.set(speed);
  }

  public void rightmotors(double speed){
    db_right.set(speed);
  }

  //timing? - yep, under construction due to scheduler timing integration needs
  public void decelerate(double init_speed){
    double speed = init_speed;
    while(speed>0.1){
      speed *= 0.9;
      drive_main.tankDrive(speed, speed);
      System.out.println("happened");
    }
    drive_main.tankDrive(0, 0);
  }



//default periodic functions
  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}