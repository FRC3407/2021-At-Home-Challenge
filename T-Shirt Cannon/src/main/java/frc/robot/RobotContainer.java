// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.*;
import frc.robot.commands.Pneumatics.*;
import frc.robot.subsystems.*;

public class RobotContainer {
  
  public static DriveTrain db_main = new DriveTrain();
  public static Pneumatics pneumatics = new Pneumatics();
  public static Input input = new Input(Input.InputType.Xbox);

  public static TeleopDrive teleop_drive;

  public RobotContainer() {
    instantiateCommands();
    configureButtonBindings();
  }

  private void instantiateCommands(){
    teleop_drive = new TeleopDrive();
  }

  private void configureButtonBindings(){
    input.getInput().getButton1().whenPressed(new ShootCannon(Constants.Pneumatics.solenoid1));
    input.getInput().getButton2().whenPressed(new ShootCannon(Constants.Pneumatics.solenoid2));
    input.getInput().getButton3().whenPressed(new ShootCannon(Constants.Pneumatics.solenoid3));
    input.getInput().getButton4().whenPressed(new ShootCannon(Constants.Pneumatics.solenoid4));
    input.getInput().getUtility1().whenPressed(new StartCompressor());
    input.getInput().getUtility2().whenPressed(new StopCompressor());
  }
}