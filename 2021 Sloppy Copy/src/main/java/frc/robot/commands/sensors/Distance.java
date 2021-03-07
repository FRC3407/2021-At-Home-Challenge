// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.sensors;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Dynamics;

public class Distance extends CommandBase {
  private double velocityX = 0, velocityY = 0, distanceX = 0, distanceY = 0, Ttime = 0;
  private boolean avg, rio;

  /**
   * @param avgbetween - boolean on wheather or not to use an average of the imu and internal accelerometer for an input
   * @param rio - boolean for wheather or not to use the rio as the default accelerometer -> for if the above is false
   */
  public Distance(boolean avgbetween, boolean rio) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.imu);
    this.avg = avgbetween;
    this.rio = rio;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    updateCycle();
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  private void updateCycle(){
    double x, y, velx, vely, distx, disty;
    double time = Dynamics.periodtime;
    Ttime += time;
    if(avg){
      x = RobotContainer.imu.avgX();
      y = RobotContainer.imu.avgY();
    }else{
      if(rio){
        x = RobotContainer.imu.rioX();
        y = RobotContainer.imu.rioY();
      }else{
        x = RobotContainer.imu.imuX();
        y = RobotContainer.imu.imuY();
      }
    }
    velx = x*time;
    vely = y*time;
    distx = ((velocityX + velx)*time)/2;
    disty = ((velocityY + vely)*time)/2;
    distanceX += distx;
    distanceY += disty;
    velocityX += velx;
    velocityY += vely;
  }
}
