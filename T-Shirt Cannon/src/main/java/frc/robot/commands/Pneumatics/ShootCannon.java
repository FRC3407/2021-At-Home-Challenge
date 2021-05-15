// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Pneumatics;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;

public class ShootCannon extends SequentialCommandGroup {
  public ShootCannon(int id) {
    addCommands(
      new OpenSolenoid(id).withTimeout(Constants.Pneumatics.releasetime),
      new CloseSolenoid(id)
    );
  }
}