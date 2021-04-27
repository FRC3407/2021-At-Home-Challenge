// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.controller;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Dynamics;
import frc.robot.Robot;
import frc.robot.RobotContainer;
/**
 * THIS COMMAND DOES NOT WORK BECAUSE THE USERINPUT CLASS DOES NOT DYNAMICALLY UPDATE WITH THE LAYOUT VARIABLES.
 */
public class SwapController extends CommandBase {
  private boolean finished = false;
  /** Creates a new SwapController. */
  public SwapController() {
    // Use addRequirements() here to declare subsystem dependencies.
    //addRequirements(RobotContainer.input);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.teleop_drive.cancel();
    if(Dynamics.controllerlayout.equals("xbox")){
      Dynamics.controllerlayout = "logitech";
    }else if(Dynamics.controllerlayout.equals("logitech")){
      Dynamics.controllerlayout = "xbox";
    }
    //RobotContainer.dynamics.setmode(Dynamics.controllerlayout);
    //RobotContainer.input.updateButtons();
    System.out.println("layout swaped");
    RobotContainer.teleop_drive.schedule();
    finished = true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
