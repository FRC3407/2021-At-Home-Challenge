// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include "TeleopDrive.h"

TeleopDrive::TeleopDrive(DriveTrain* subsystem, UserInput* controller) : db_main{subsystem}, input{controller}{
  // Use addRequirements() here to declare subsystem dependencies.
  //addRequirements(DriveTrain);
  //addRequirements();
}

// Called when the command is initially scheduled.
void TeleopDrive::Initialize() {}

// Called repeatedly when this Command is scheduled to run
void TeleopDrive::Execute() {
  double lsticky = input->largeconvert_deadstart(controller::x_controllerStick_ly, controller::stickmult);
  double rsticky = input->largeconvert_deadstart(controller::x_controllerStick_ry, controller::stickmult);
  db_main->tankdrive(lsticky, rsticky);
}

// Called once the command ends or is interrupted.
void TeleopDrive::End(bool interrupted) {}

// Returns true when the command should end.
bool TeleopDrive::IsFinished() {
  return false;
}
