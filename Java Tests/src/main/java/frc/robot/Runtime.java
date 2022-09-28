// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.*;

import edu.wpi.first.wpilibj.networktables.*;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/** Add your docs here. */
public class Runtime extends TimedRobot {

	private final Robot.DriveBase drivebase = new Robot.DriveBase(Constants.drivebase_map);
	private final Robot.Dumper dumper = new Robot.Dumper(4, 0);
	private final Input input = new Input(0);

	public Runtime() {
		System.out.println("RUNTIME INITIALIZATION");
		System.out.println("Drivebase: " + drivebase + "\nInput Device: " + input);
	}


    
	@Override
	public void robotInit() {
		this.dumper.bindDump(this.input.getDumpActuate());
		this.dumper.bindReset(this.input.getDumpReset());
		this.input.getEStop().whenPressed(EStop.getInstance());
		this.input.netVarIncrement().whenPressed(MappedNetVariable.getInstance());
	}

	@Override
	public void robotPeriodic() {
		CommandScheduler.getInstance().run();
		getPeriod();
	}

	/** This function is called once each time the robot enters Disabled mode. */
	@Override
	public void disabledInit() {}

	@Override
	public void disabledPeriodic() {}

	/** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
	@Override
	public void autonomousInit() {
		CommandScheduler.getInstance().cancelAll();		// only for non-competition
		Autonomous auto = new Autonomous(this.drivebase);
		CommandScheduler.getInstance().schedule(auto.withTimeout(3));
	}

	/** This function is called periodically during autonomous. */
	@Override
	public void autonomousPeriodic() {}

	@Override
	public void teleopInit() {}

	/** This function is called periodically during operator control. */
	@Override
	public void teleopPeriodic() {
		this.drivebase.modeDrive(this.input);
	}

	@Override
	public void testInit() {
		// Cancels all running commands at the start of test mode.
		CommandScheduler.getInstance().cancelAll();
	}

	/** This function is called periodically during test mode. */
	@Override
	public void testPeriodic() {}
}