// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drivefunctions;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Dynamics;
import frc.robot.RobotContainer;

public class TimedAutoMove extends CommandBase {
  private double lspeed, rspeed, timeout, time;

  /**
   * @param leftspeed - the speed of the left wheels
   * @param rightspeed - the speed of the right wheels
   * @param timeout - the time in seconds to do the menuver
   */
  public TimedAutoMove(double leftspeed, double rightspeed, double timeout) {
    addRequirements(RobotContainer.db_main);
    lspeed = leftspeed;
    rspeed = rightspeed;
    this.timeout = timeout;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.db_main.tank_drive(lspeed, rspeed);
    time += Dynamics.periodtime;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("Completed autonomous movement in [" + String.valueOf(time) + "] seconds");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (time >= timeout);
  }
}
