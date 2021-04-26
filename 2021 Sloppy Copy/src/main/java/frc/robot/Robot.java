// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  //RobotContainer basically acts as another "Robot" class, so everything is set up there, then accessed through its class
  public static RobotContainer robotContainer;

  //Robot mode command scheduling
    //basic robot functions
    @Override
    public void robotInit() {
      robotContainer = new RobotContainer(); 
    }
    //remember to add "CommandScheduler.getInstance().run();" inside as this is necessary for anything depending on the scheduler to run
    @Override
    public void robotPeriodic() {
      Dynamics.periodtime = getPeriod();
      CommandScheduler.getInstance().run();
    }

    
    //normal operation mode
    @Override
    public void teleopInit() {
      //RobotContainer.dashboard.schedule();
      RobotContainer.teleop_drive.schedule();
      //RobotContainer.distance.schedule();
    }
    @Override
    public void teleopPeriodic() {}


    //autonomous mode
    @Override
    public void autonomousInit() {
      RobotContainer.dashboard.schedule();
      //RobotContainer.slolom.schedule();
      //RobotContainer.sense_periodic.schedule();     //debug
      //RobotContainer.auto_routine.schedule();
      RobotContainer.testurn.schedule();
      //RobotContainer.straight.schedule();
    }
    @Override
    public void autonomousPeriodic() {}




    //test mode
    @Override
    public void testInit() {}
    @Override
    public void testPeriodic() {}
    //disabled mode
    @Override
    public void disabledInit() {}
    @Override
    public void disabledPeriodic() {}

}
