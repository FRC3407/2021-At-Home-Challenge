// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.hardware;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

public class MotorAttatchments extends SubsystemBase {
  //add motors here when needed
  private TalonSRX falcon1 = new TalonSRX(Constants.falcon1_canid);
  private TalonSRX falcon2 = new TalonSRX(Constants.falcon2_canid);


  /** Creates a new MotorAttatchments. */
  public MotorAttatchments() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  //create methods for the motors based on structures that are built on the robot>>>
}
