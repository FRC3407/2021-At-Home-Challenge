// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.Input;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class TeleopDrive extends CommandBase {

	private final Robot.DriveBase drivebase;
	private final Input input;

	/** Creates a new TeleopDrive. */
	public TeleopDrive(Robot.DriveBase drivebase, Input input) {
		this.drivebase = drivebase;
		this.input = input;

		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(this.drivebase);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		this.drivebase.modeDrive(this.input);
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		// call decelerate command here
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}
