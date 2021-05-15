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
  private Solenoid Sol1 = new Solenoid(Constants.Pneumatics.solenoid1);
  private Solenoid Sol2 = new Solenoid(Constants.Pneumatics.solenoid2);
  private Solenoid Sol3 = new Solenoid(Constants.Pneumatics.solenoid3);
  private Solenoid Sol4 = new Solenoid(Constants.Pneumatics.solenoid4);
  
  public Pneumatics() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setSolenoid1(boolean open){
    Sol1.set(open);
  }

  public void setSolenoid2(boolean open){
    Sol2.set(open);
  }

  public void setSolenoid3(boolean open){
    Sol3.set(open);
  }

  public void setSolenoid4(boolean open){
    Sol4.set(open);
  }

  public void setCompressor(boolean status){
    Comp.setClosedLoopControl(status);
  }

  public void safemode(){
    Sol1.set(false);
    Sol2.set(false);
    Sol3.set(false);
    Sol4.set(false);
  }
}