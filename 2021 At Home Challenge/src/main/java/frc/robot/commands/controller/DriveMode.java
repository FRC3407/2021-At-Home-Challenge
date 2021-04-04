// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.controller;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Dynamics;

public class DriveMode extends CommandBase {
  private boolean left, right, finished = false;
  /** Creates a new DriveMode. */
  public DriveMode(boolean leftbutton, boolean rightbutton) {
    // Use addRequirements() here to declare subsystem dependencies.
    left = leftbutton;
    right = rightbutton;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    int index = Dynamics.dmode_index;
    int size = (Constants.drivemodes.length)-1;
    //increment index based on input
    if(left){index -=1;}
    else if(right){index +=1;}
    //clamp index to array size limits
    if(index>size){index = size;}
    else if(index<0){index = 0;}
    //save index
    Dynamics.dmode_index = index;
    //set drivemode
    Dynamics.drivemode = Constants.drivemodes[index];
    System.out.println((String.valueOf(Dynamics.drivemode)) + " mode");
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
