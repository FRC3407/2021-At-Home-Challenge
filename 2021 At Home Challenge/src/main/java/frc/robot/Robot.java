// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  public static RobotContainer robotContainer;

  @Override
  public void robotInit() {
    robotContainer = new RobotContainer(); 
  }
  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    Dynamics.periodtime = getPeriod();
  }

  
  //normal operation mode
  @Override
  public void teleopInit() {
    RobotContainer.teleop_drive.schedule();
  }
  @Override
  public void teleopPeriodic() {}


  //autonomous mode
  @Override
  public void autonomousInit() {
    RobotContainer.slolom.schedule();
  }
  @Override
  public void autonomousPeriodic() {}
}
