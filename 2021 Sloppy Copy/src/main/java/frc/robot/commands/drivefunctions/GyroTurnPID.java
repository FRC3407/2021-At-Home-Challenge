// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drivefunctions;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.RobotContainer;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class GyroTurnPID extends PIDCommand {
  /** Creates a new GyroTurnPID. */
  public GyroTurnPID(double P, double I, double D, double angle, double init) {
    super(
        // The controller that the command will use
        new PIDController(P, I, D),
        // This should return the measurement
        () -> RobotContainer.imu.currentAngle(),
        // This should return the setpoint (can also be a constant)
        () -> angle-init,
        // This uses the output
        output -> {RobotContainer.db_main.arcade_drive(0, output);
          // Use the output here
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    getController().enableContinuousInput(-180, 180);
    getController().setTolerance(0.1, 0.1);
    addRequirements(RobotContainer.db_main, RobotContainer.imu);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
