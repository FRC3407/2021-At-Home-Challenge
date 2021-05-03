// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drivefunctions;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Dynamics;
import frc.robot.RobotContainer;
import frc.robot.Constants.DriveModes;

public class TeleopDrive extends CommandBase {
  // private double decleft, decright;
  public TeleopDrive() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.db_main);
    addRequirements(RobotContainer.input);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double lstick_Y = RobotContainer.input.getInput().getPriY();
    double rstick_Y = RobotContainer.input.getInput().getSecY();
    double lstick_X = RobotContainer.input.getInput().getPriX();
    double rstick_X = RobotContainer.input.getInput().getSecX();
    double ltrigger = RobotContainer.input.getInput().getPriTrigger();
    double rtrigger = RobotContainer.input.getInput().getSecTrigger();
    boolean boostButton = RobotContainer.input.getInput().getButton1().get();
    if(Dynamics.drivemode == DriveModes.Tank){
      RobotContainer.db_main.tank_drive(lstick_Y, rstick_Y, boostButton);
      // lstick_Y = decleft;
      // rstick_Y = decright;
    }else if(Dynamics.drivemode == DriveModes.Arcade){
      RobotContainer.db_main.arcade_drive(rstick_Y, rstick_X, boostButton);
      // double out[] = arcadeConversion(rstick_X, rstick_Y);
      // decleft = out[0];
      // decright = out[1];
    }else if(Dynamics.drivemode == DriveModes.Race){
      RobotContainer.db_main.race_drive(ltrigger, rtrigger, rstick_X, boostButton);
      // double comb = ltrigger - rtrigger;
      // double out[] = arcadeConversion(comb, rstick_X);
      // decleft = out[1];
      // decright = out[0];
    }else if(Dynamics.drivemode == DriveModes.Trigger){
      RobotContainer.db_main.trigger_drive(ltrigger, rtrigger, boostButton);
      // ltrigger = decleft;
      // rtrigger = decright;
    }else{
      System.out.println("Drivemode Error");
    }
   }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // System.out.println(String.valueOf(decleft));
    // System.out.println(String.valueOf(decright));
    RobotContainer.decelerate.schedule();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
