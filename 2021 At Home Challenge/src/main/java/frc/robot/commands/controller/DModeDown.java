// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.controller;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Dynamics;

public class DModeDown extends CommandBase {
  public DModeDown() {}

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Dynamics.DModeHandler.decrement();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("Changed drivemode: " + Dynamics.DModeHandler.getMode());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}