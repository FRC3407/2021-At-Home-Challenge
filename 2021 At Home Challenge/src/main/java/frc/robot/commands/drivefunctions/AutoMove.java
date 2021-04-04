// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drivefunctions;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class AutoMove extends CommandBase {
  private double lspeed, rspeed;

  public AutoMove(double leftspeed, double rightspeed) {
    addRequirements(RobotContainer.db_main);
    lspeed = leftspeed;
    rspeed = rightspeed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //smoothen out?
    //RobotContainer.db_main.tank_drive(lspeed/2, rspeed/2);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.db_main.tank_drive(lspeed, rspeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
