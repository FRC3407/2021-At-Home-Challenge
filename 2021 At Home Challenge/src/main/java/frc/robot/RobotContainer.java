package frc.robot;

import frc.robot.subsystems.*;
import frc.robot.commands.controller.*;
import frc.robot.commands.drivefunctions.*;
import frc.robot.commands.groups.Slolom;

public class RobotContainer {
  //automated update functions
  public static Dynamics dynamics = new Dynamics();

  //subsystems
  public static DriveTrain db_main = new DriveTrain();
  public static UserInput input = new UserInput();
  public static IMU_Gyro imu = new IMU_Gyro();
  
  //commmands
  public static TeleopDrive teleop_drive = new TeleopDrive();
  public static Decelerate decelerate = new Decelerate(Dynamics.deceleration_mult);
  public static DModeDown drivemode_left = new DModeDown();
  public static DModeUp drivemode_right = new DModeUp();
  public static Slolom slolom = new Slolom(null);
  public static CancelAll stop = new CancelAll();

  public RobotContainer() {
    registerSubsystems();
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    input.menubutton.whenHeld(stop);
    input.leftbutton.whenPressed(drivemode_left);
    input.rightbutton.whenPressed(drivemode_right);
  }

  private void registerSubsystems(){
    if(Dynamics.db_periodic){
      db_main.register();
    }
    // if(Dynamics.colorsrc_periodic){
    //   colorsrc.register();
    // }
    if(Dynamics.imu_periodic){
      imu.register();
    }
    if(Dynamics.input_periodic){
      input.register();
    }
  }
}
