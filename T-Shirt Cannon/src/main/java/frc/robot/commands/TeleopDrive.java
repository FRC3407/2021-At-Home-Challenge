// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.Constants.DriveModes;

public class TeleopDrive extends CommandBase {
  
  public TeleopDrive() {}

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if(Constants.default_mode == DriveModes.Tank){
      RobotContainer.db_main.tank_drive(RobotContainer.input.getInput().getPriY(), RobotContainer.input.getInput().getSecY());
    }else if(Constants.default_mode == DriveModes.Arcade){
      RobotContainer.db_main.arcade_drive(RobotContainer.input.getInput().getSecY(), RobotContainer.input.getInput().getSecX());
    }else{
      System.out.println("Drivemode Error");
    }
  }

  @Override
  public void end(boolean interrupted) {
    RobotContainer.db_main.tank_drive(0, 0);
    //RobotContainer.decelerate.schedule();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}