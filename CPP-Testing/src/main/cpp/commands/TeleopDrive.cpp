// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include "commands/TeleopDrive.h"

TeleopDrive::TeleopDrive(DriveTrain* subsystem) : db_main{subsystem}{
  // Use addRequirements() here to declare subsystem dependencies.
  //addRequirements(DriveTrain);
  //addRequirements();
}

// Called when the command is initially scheduled.
void TeleopDrive::Initialize() {}

// Called repeatedly when this Command is scheduled to run
void TeleopDrive::Execute() {
  db_main->tankdrive(0, 0);
}

// Called once the command ends or is interrupted.
void TeleopDrive::End(bool interrupted) {}

// Returns true when the command should end.
bool TeleopDrive::IsFinished() {
  return false;
}
