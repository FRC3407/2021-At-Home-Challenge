// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Dynamics;
import frc.robot.RobotContainer;
import frc.robot.Constants.DriveModes;

public class TeleopDrive extends CommandBase {
  
  public TeleopDrive() {}

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double lstick_Y = RobotContainer.input.getInput().getPriY();
    double rstick_Y = RobotContainer.input.getInput().getSecY();
    //double lstick_X = RobotContainer.input.getInput().getPriX();
    double rstick_X = RobotContainer.input.getInput().getSecX();
    double ltrigger = RobotContainer.input.getInput().getPriTrigger();
    double rtrigger = RobotContainer.input.getInput().getSecTrigger();
    if(Dynamics.drivemode == DriveModes.Tank){
      RobotContainer.db_main.tank_drive(lstick_Y, rstick_Y);
    }else if(Dynamics.drivemode == DriveModes.Arcade){
      RobotContainer.db_main.arcade_drive(rstick_Y, rstick_X);
    }else if(Dynamics.drivemode == DriveModes.Race){
      RobotContainer.db_main.race_drive(ltrigger, rtrigger, rstick_X);
    }else if(Dynamics.drivemode == DriveModes.Trigger){
      RobotContainer.db_main.trigger_drive(ltrigger, rtrigger);
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
