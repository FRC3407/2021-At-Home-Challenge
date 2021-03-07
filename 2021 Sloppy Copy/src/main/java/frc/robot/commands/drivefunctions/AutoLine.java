// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

//SPLIT THIS FILE UP WHEN POSSIBLE, TO CREATE A COMMAND FOR EACH DIFFERENT AUTONOMOUS PROFILE
package frc.robot.commands.drivefunctions;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants;
import java.util.Timer;
import java.util.TimerTask;

public class AutoLine extends CommandBase {
  private Timer timeout = new Timer();
  private TimerTask stop = new task();
  private boolean cancel = false;
  private boolean timer = false;
  //private int fails = 0;

  private class task extends TimerTask{
      public void run(){
        //System.out.println("Timeout reached, Canceling...");
        cancel = true;
      }
  }

  /** Creates a new AutonomousRoutine. */
  public AutoLine() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.db_main);
    addRequirements(RobotContainer.colorsrc);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("AUTONOMOUS MODE");      //debug
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    linefollow1(Constants.linecolor, Constants.floorcolor, Constants.linespeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.db_main.tank_drive(0, 0);
    //debug
    //System.out.println(String.valueOf(fails));
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return cancel;
  }

  /**
   * UNTESTED. This function is to be used with a single colorsensor, as instead of following "the line" it will follow the edge of the line where it meets the floor
   * @param linecolor - an array of doubles correlating to the ranges of the RGB values of the color of line to follow
   * @param floorcolor - an array of double correlating to the ranges of the RGB values of the color of floor
   * @param speed - the speed at which the robot will move when line-following
   */
  private void linefollow1(double linecolor[], double floorcolor[], double speed){
    double redsrc = RobotContainer.colorsrc.red();
    double greensrc = RobotContainer.colorsrc.green();
    double bluesrc = RobotContainer.colorsrc.blue();
    if(
      (linecolor[0] < redsrc && redsrc < linecolor[1]) && 
      (linecolor[2] < greensrc && greensrc < linecolor[3]) && 
      (linecolor[4] < bluesrc && bluesrc < linecolor[5])
    ){
      if(timer){
        timeout.cancel();
        timer = false;
      }
      RobotContainer.db_main.tank_drive((speed/2), speed);
    }else if(
      (floorcolor[0] < redsrc && redsrc < floorcolor[1]) &&
      (floorcolor[2] < greensrc && greensrc < floorcolor[2]) && 
      (floorcolor[4] < bluesrc && bluesrc < floorcolor[5])
    ){
      RobotContainer.db_main.tank_drive(speed, (speed/2));
      if(!timer){
        timeout.schedule(stop, 5000);
        timer = true;
      }
    }else{
      if(!timer){
        timeout.schedule(stop, 2500);
        timer = true;
      }
      //comment this out if it isnt helpful, this is to maybe help the robot find the line if it is lost, or not starting exactly on the line
      RobotContainer.db_main.tank_drive((speed/2), (speed/2));
      //debug
      //fails += 1;
      System.out.println("Line edge not detected");
      // System.out.println(String.valueOf(redsrc));
      // System.out.println(String.valueOf(greensrc));
      // System.out.println(String.valueOf(bluesrc));
    }
  }

  // private void linefollow2(double linecolor[], double speed){
  //     //this function is setup assuming the first colorsensor is on the front left of the robot
  //     double colors1[] = RobotContainer.colorsrc.rawcolors(1);
  //     double colors2[] = RobotContainer.colorsrc.rawcolors2();
  //     double red1 = colors1[0];
  //     double red2 = colors2[0];
  //     double green1 = colors1[1];
  //     double green2 = colors2[1];
  //     double blue1 = colors1[2];
  //     double blue2 = colors2[2];
  //     if(
  //       (linecolor[0] < red1 && red1 < linecolor[1]) &&
  //       (linecolor[2] < green1 && green1 < linecolor[3]) &&
  //       (linecolor[4] < blue1 && blue1 < linecolor[5])
  //     ){
  //       RobotContainer.db_main.tank_drive((speed/2), speed);
  //     }else if(
  //       (linecolor[0] < red2 && red2 < linecolor[1]) &&
  //       (linecolor[2] < green2 && green2 < linecolor[3]) &&
  //       (linecolor[4] < blue2 && blue2 < linecolor[5])
  //     ){
  //       RobotContainer.db_main.tank_drive(speed,(speed/2));
  //     }else{
  //       RobotContainer.db_main.tank_drive((speed/2),(speed/2));
  //       System.out.println("No line found");
  //       System.out.println(String.valueOf(red1));
  //       System.out.println(String.valueOf(green1));
  //       System.out.println(String.valueOf(blue1));
  //       System.out.println(String.valueOf(red2));
  //       System.out.println(String.valueOf(green2));
  //       System.out.println(String.valueOf(blue2));
  //     }
  // }
}
