// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.drivefunctions.TeleopDrive;
import frc.robot.commands.groups.Slolom;
import frc.robot.commands.controller.CancelAll;
import frc.robot.subsystems.sensors.ColorSense;
import frc.robot.subsystems.hardware.DriveTrain;
import frc.robot.subsystems.sensors.IMU_Gyro;
import frc.robot.subsystems.input.Input;
import frc.robot.commands.controller.DriveMode;
import frc.robot.commands.drivefunctions.Decelerate;
import frc.robot.commands.drivefunctions.GyroStraight;
import frc.robot.commands.drivefunctions.GyroTurn;
import frc.robot.commands.ouput.DashManager;

public class RobotContainer {
  //automated update functions
  public static Dynamics dynamics = new Dynamics();

  //subsystem instance creation
  public static DriveTrain db_main = new DriveTrain();
  public static ColorSense colorsrc = new ColorSense();
  public static IMU_Gyro imu = new IMU_Gyro();
  //public static CameraArray camarr = new CameraArray();
  //public static UltrasonicArray sonic = new UltrasonicArray();
  public static Input input = new Input(Input.InputType.Xbox);
  
  //commmand instance creation
  public static Decelerate decelerate;
  public static DashManager dashboard;
  public static Slolom slolom;
  public static TeleopDrive teleop_drive;
  public static CancelAll stop;
  public static DriveMode drivemode_left;
  public static DriveMode drivemode_right;
  public static GyroTurn testurn;
  public static GyroStraight straight;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    registerSubsystems();
    instantiateCommands();
    // Configure the button bindings
    configureButtonBindings();
  }

  private void registerSubsystems(){
    if(Constants.DriveBase.periodic){
      db_main.register();
    }
    // if(Dynamics.colorsrc_periodic){
    //   colorsrc.register();
    // }
    if(Constants.IMU.periodic){
      imu.register();
    }
    if(Constants.GeneralInput.periodic){
      input.register();
    }
  }

  private void instantiateCommands(){
    decelerate = new Decelerate(Constants.deceleration_const);
    straight = new GyroStraight(0.2, 0.2);
    testurn = new GyroTurn(0.2, -0.2, 10);
    drivemode_left = new DriveMode(true, false);
    drivemode_right = new DriveMode(false, true);
    slolom = new Slolom(null);
    teleop_drive = new TeleopDrive();
    stop = new CancelAll();
    dashboard = new DashManager();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //input.obj.getButton1().whenPressed(stop);
    input.getInput().getUtility1().whenPressed(drivemode_left);
    input.getInput().getUtility2().whenPressed(drivemode_right);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  //   // An ExampleCommand will run in autonomous
  //   return m_autoCommand;
  // }
}