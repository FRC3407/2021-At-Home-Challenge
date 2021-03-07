// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.sensors;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SensorDebug extends CommandBase {
  private double redarr[] = {0.0, 0.0, 0.0};
  private double greenarr[] = {0.0, 0.0, 0.0};
  private double bluearr[] = {0.0, 0.0, 0.0};
  private double rgb[] = RobotContainer.colorsrc.rawcolors(1);
  private int count = 0;

  private double initangle, currentangle;


  /** Creates a new PeriodiColor. */
  public SensorDebug() {
    // Use addRequirements() here to declare subsystem dependencies.
    //addRequirements(RobotContainer.colorsrc);
    //addRequirements(RobotContainer.camarr);
    addRequirements(RobotContainer.imu);
    //addRequirements(RobotContainer.input);
    //addRequirements(RobotContainer.sonic);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    initangle = RobotContainer.imu.currentAngle();
    //coloravg_setup();
    SmartDashboard.putNumber("Initial Angle:", initangle);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //coloravg_periodic();  
    //coloravg_lowfreq(25); 
    //colorvalues(1); 
    currentangle = RobotContainer.imu.currentAngle();
    SmartDashboard.putNumber("Current Angle:", currentangle);
    SmartDashboard.putNumber("Current Angular Rate:", RobotContainer.imu.currentRate());
    SmartDashboard.putNumber("IMU AccX:", RobotContainer.imu.imuX_si());
    SmartDashboard.putNumber("IMU AccY:", RobotContainer.imu.imuY_si());
    SmartDashboard.putNumber("IMU AccZ:", RobotContainer.imu.imuZ_si());
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("Interrupted");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  /**
   * @param output - the place where coutputs will show - 0 is the terminal, 1 is smartdashboard/shuffleboard
   */
  private void colorvalues(int output){
    if(output == 0){
      System.out.println(String.valueOf(RobotContainer.colorsrc.red()));
      System.out.println(String.valueOf(RobotContainer.colorsrc.green()));
      System.out.println(String.valueOf(RobotContainer.colorsrc.blue()));
    }else if(output == 1){
      SmartDashboard.putNumber("Raw Red:", RobotContainer.colorsrc.red());
      SmartDashboard.putNumber("Raw Green:", RobotContainer.colorsrc.green());
      SmartDashboard.putNumber("Raw Blue:", RobotContainer.colorsrc.blue());
    }
  }

  private void coloravg_setup(){
    redarr[0] = redarr[1] = redarr[2] = rgb[0];
    greenarr[0] = greenarr[1] = greenarr[2] = rgb[1];
    bluearr[0] = bluearr[1] = bluearr[2] = rgb[2];
  }

  private double[] periodic_avg(double value, double[] avgarr){
    double running_avg = avgarr[0];
    double high = avgarr[1];
    double low = avgarr[2];
    double avg = (value + running_avg)/2;
    double ret[] = {avg, high, low};
    if(value > high){ret[1] = value;}
    if(value < low){ret[2] = value;}
    return ret;
  }

  private void coloravg_periodic(){
    double[] red = periodic_avg(RobotContainer.colorsrc.red(), redarr);
    double[] green = periodic_avg(RobotContainer.colorsrc.green(), greenarr);
    double[] blue = periodic_avg(RobotContainer.colorsrc.blue(), bluearr);
    redarr[0] = red[0];
    redarr[1] = red[1];
    redarr[2] = red[2];
    greenarr[0] = green[0];
    greenarr[1] = green[1];
    greenarr[2] = green[2];
    bluearr[0] = blue[0];
    bluearr[1] = blue[1];
    bluearr[2] = blue[2];
    //output high, low, and avg for red, green, and blue
    SmartDashboard.putNumber("Average Red: ", red[0]);
    SmartDashboard.putNumber("Average Green: ", green[0]);
    SmartDashboard.putNumber("Average Blue: ", blue[0]);
    SmartDashboard.putNumber("Highest Red: ", red[1]);
    SmartDashboard.putNumber("Highest Green: ", green[1]);
    SmartDashboard.putNumber("Highest Blue: ", blue[1]);
    SmartDashboard.putNumber("Lowest Red: ", red[2]);
    SmartDashboard.putNumber("Lowest Green: ", green[2]);
    SmartDashboard.putNumber("Lowest Blue: ", blue[2]);
  }

  /**
   * @param counts - the number of periodic runs that it will take to update colorsensor values - ex. if counts = 25, then the colorsenosr will update 2 times per second
   */
  private void coloravg_lowfreq(int counts){
    if(count == counts){
      coloravg_periodic();
      count = 0;
    }else{
      count += 1;
    }
  }
}
