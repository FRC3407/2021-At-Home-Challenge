// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drivefunctions;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Dynamics;
import frc.robot.RobotContainer;

public class TeleopDrive extends CommandBase {
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
    //implement a "haschanged" type object so we don't have to go through the switch every time
    switch(Dynamics.DModeHandler.getMode()) {
      case TANK:
        double lstick_Y = RobotContainer.input.OPControllerFunc1(Dynamics.controllerStick_ly, Constants.deadzone, Constants.c1_left_Y_mult, Constants.power);
        double rstick_Y = RobotContainer.input.OPControllerFunc1(Dynamics.controllerStick_ry, Constants.deadzone, Constants.c1_right_Y_mult, Constants.power);
        RobotContainer.db_main.tank_drive(lstick_Y, rstick_Y);
        break;
      case ARCADE:
        double lstick_X = RobotContainer.input.OPControllerFunc1(Dynamics.controllerStick_lx, Constants.deadzone, Constants.c1_left_X_mult, Constants.power);
        double lstick_Y = RobotContainer.input.OPControllerFunc1(Dynamics.controllerStick_ly, Constants.deadzone, Constants.c1_left_Y_mult, Constants.power);;
        RobotContainer.db_main.arcade_drive(lstick_X, lstick_Y);
        break;
      case RACE:
        double rstick_X = RobotContainer.input.OPControllerFunc1(Dynamics.controllerStick_rx, Constants.deadzone, Constants.c1_right_X_mult, Constants.power);
        double ltrigger = RobotContainer.input.OPControllerFunc1(Dynamics.controllerTrigger_l, 0, 1, 2);
        double rtrigger = RobotContainer.input.OPControllerFunc1(Dynamics.controllerTrigger_r, 0, 1, 2);
        RobotContainer.db_main.race_drive(ltrigger, rtrigger, rstick_X);
        break;
      case TRIGGER:
        double ltrigger = RobotContainer.input.OPControllerFunc1(Dynamics.controllerTrigger_l, 0, 1, 2);
        double rtrigger = RobotContainer.input.OPControllerFunc1(Dynamics.controllerTrigger_r, 0, 1, 2);
        RobotContainer.db_main.trigger_drive(ltrigger, rtrigger);
        break;
      default:
        System.out.println("Drivemode type error");

    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.decelerate.schedule();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
