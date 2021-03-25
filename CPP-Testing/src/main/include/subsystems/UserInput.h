// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#pragma once

#include <frc2/command/SubsystemBase.h>
#include <frc/XboxController.h>
#include <cmath>

#include "Constants.h"

class UserInput : public frc2::SubsystemBase {
 public:
  UserInput();

  double axis_raw(int axis);

  double axis_offset(int axis, double offset);

  double axis_multiply(int axis, double mult);

  double axis_exponential(int axis, int power);

  double largeconvert_deadcut(int axis, double mult);

  double largeconvert_deadstart(int axis, double mult);

  /**
   * Will be called periodically whenever the CommandScheduler runs.
   */
  void Periodic() override;

 private:
  // Components (e.g. motor controllers and sensors) should generally be
  // declared private and exposed only through public methods.

  frc::XboxController Controller{controller::controller_port};
};
