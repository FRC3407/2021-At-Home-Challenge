// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drivefunctions;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class Decelerate extends CommandBase {
  private double lspeed, rspeed, mult;
  private boolean finished = false;

  /**
  The multiplier used that will determine the speed at which the robot decelerates. Anywhere between 0.96 and 0.99 will give a "deceleration" result, but a good starting value is 0.98. 
  */
  public Decelerate(double decfactor) {
    this.mult = decfactor;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.db_main);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //get initial speeds automatically somehow (encoders)?
    lspeed = RobotContainer.db_main.leftspeed();
    rspeed = RobotContainer.db_main.rightspeed();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Graph to show at what (multiplier below) it will take more than one second to stop: https://www.desmos.com/calculator/sncflgeq8v
    //From the graph: 0.97-0.98 should be the best -> above 1.5 seconds to slow down -> 9.6 for the safest value below that, and 0.99 for the closest deceleration to a linear one
    lspeed *= mult;
    rspeed *= mult;
    RobotContainer.db_main.tank_drive(lspeed, rspeed);
    if((lspeed<0.1)&&(rspeed<0.1)){
      finished = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.db_main.tank_drive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
