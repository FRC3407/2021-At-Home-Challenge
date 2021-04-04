// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.controller;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class CancelAll extends CommandBase {
  private boolean done = false;

  /** Creates a new CancelAll. */
  public CancelAll() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.teleop_drive.cancel();
    RobotContainer.slolom.cancel();
    done = true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("Canceled all scheduled tasks");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return done;
  }
}