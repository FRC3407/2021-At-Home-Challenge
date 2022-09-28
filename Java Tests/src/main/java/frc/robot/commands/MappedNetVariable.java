// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class MappedNetVariable extends CommandBase {

	private MappedNetVariable() {}

	private static MappedNetVariable instance = new MappedNetVariable();
	public static MappedNetVariable getInstance() {
		return MappedNetVariable.instance;
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		NetworkTable table = NetworkTableInstance.getDefault().getTable("Vision");
		table.getEntry("INDEX").setDouble(table.getEntry("INDEX").getDouble(0) + 1);
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
		return true;
	}
}
