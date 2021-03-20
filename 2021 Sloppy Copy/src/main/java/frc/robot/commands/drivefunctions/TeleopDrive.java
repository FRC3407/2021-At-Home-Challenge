// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drivefunctions;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Dynamics;
import frc.robot.RobotContainer;

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
    double lstick_Y = RobotContainer.input.OPControllerFunc1(Dynamics.controllerStick_ly, Dynamics.deadzone, Dynamics.c1_left_Y_mult, Dynamics.power);
    double rstick_Y = RobotContainer.input.OPControllerFunc1(Dynamics.controllerStick_ry, Dynamics.deadzone, Dynamics.c1_right_Y_mult, Dynamics.power);
    double lstick_X = RobotContainer.input.OPControllerFunc1(Dynamics.controllerStick_lx, Dynamics.deadzone, Dynamics.c1_left_X_mult, Dynamics.power);
    double rstick_X = RobotContainer.input.OPControllerFunc1(Dynamics.controllerStick_rx, Dynamics.deadzone, Dynamics.c1_right_X_mult, Dynamics.power);
    double ltrigger = RobotContainer.input.OPControllerFunc1(Dynamics.controllerTrigger_l, 0, 1, 2);
    double rtrigger = RobotContainer.input.OPControllerFunc1(Dynamics.controllerTrigger_r, 0, 1, 2);
    if(Dynamics.drivemode == "tank"){
      RobotContainer.db_main.tank_drive(lstick_Y, rstick_Y);
      // lstick_Y = decleft;
      // rstick_Y = decright;
    }else if(Dynamics.drivemode == "arcade"){
      RobotContainer.db_main.arcade_drive(rstick_X, rstick_Y);
      // double out[] = arcadeConversion(rstick_X, rstick_Y);
      // decleft = out[0];
      // decright = out[1];
    }else if(Dynamics.drivemode == "race"){
      RobotContainer.db_main.race_drive(ltrigger, rtrigger, rstick_X);
      // double comb = ltrigger - rtrigger;
      // double out[] = arcadeConversion(comb, rstick_X);
      // decleft = out[1];
      // decright = out[0];
    }else if(Dynamics.drivemode == "trigger"){
      RobotContainer.db_main.trigger_drive(ltrigger, rtrigger);
      // ltrigger = decleft;
      // rtrigger = decright;
    }else{
      System.out.println("drivemode error");
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
