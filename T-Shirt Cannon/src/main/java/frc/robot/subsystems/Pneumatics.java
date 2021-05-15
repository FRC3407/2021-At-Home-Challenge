// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;

public class Pneumatics extends SubsystemBase {

  private Compressor Comp = new Compressor(Constants.Pneumatics.compressor);
  private Solenoid[] Cannons = new Solenoid[4];
  
  public Pneumatics() {
    Cannons[0] = new Solenoid(Constants.Pneumatics.solenoid1);
    Cannons[1] = new Solenoid(Constants.Pneumatics.solenoid2);
    Cannons[2] = new Solenoid(Constants.Pneumatics.solenoid3);
    Cannons[3] = new Solenoid(Constants.Pneumatics.solenoid4);
  }

  @Override
  public void periodic() {}

  public void openSolenoid(int id){
    Cannons[id].set(true);
  }

  public void closeSolenoid(int id){
    Cannons[id].set(false);
  }

  public void startCompressor(){
    Comp.setClosedLoopControl(true);
  }

  public void stopCompressor(){
    Comp.setClosedLoopControl(false);
  }

  public void safeMode(){
    for (int i=0; i<=Cannons.length; i++){
      closeSolenoid(i);
    }
    stopCompressor();
  }
}