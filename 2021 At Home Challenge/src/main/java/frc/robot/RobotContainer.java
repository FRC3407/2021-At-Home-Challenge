package frc.robot;

import frc.robot.commands.drivefunctions.TeleopDrive;
import frc.robot.commands.drivefunctions.Decelerate;
import frc.robot.commands.groups.Slolom;
import frc.robot.commands.controller.CancelAll;
import frc.robot.commands.controller.DriveMode;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.UserInput;
import frc.robot.subsystems.IMU_Gyro;

public class RobotContainer {
  //automated update functions
  public static Dynamics dynamics = new Dynamics();

  //subsystem instance creation
  public static DriveTrain db_main = new DriveTrain();
  public static UserInput input = new UserInput();
  public static IMU_Gyro imu = new IMU_Gyro();
  
  //commmand declaration
  public static TeleopDrive teleop_drive;
  public static Decelerate decelerate;
  public static DriveMode drivemode_left;
  public static DriveMode drivemode_right;
  public static Slolom slolom;
  public static CancelAll stop;

  public RobotContainer() {
    registerSubsystems();
    instantiateCommands();
    configureButtonBindings();
  }

  private void instantiateCommands(){
    decelerate = new Decelerate(Dynamics.deceleration_mult);
    drivemode_left = new DriveMode(true, false);
    drivemode_right = new DriveMode(false, true);
    slolom = new Slolom(null);
    teleop_drive = new TeleopDrive();
    stop = new CancelAll();
  }

  private void configureButtonBindings(){
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
