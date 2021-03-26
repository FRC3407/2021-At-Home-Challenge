// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#pragma once

#include <frc/GenericHID.h>
#include <frc/XboxController.h>
#include <cmath>

#include "Constants.h"

class UserInput : public frc::GenericHID {
 public:
  explicit UserInput(int port);

  double GetX(JoystickHand hand) const override;

  double GetY(JoystickHand hand) const override;

  double GetAxis_offset(int axis, double offset);

  double GetAxis_multiply(int axis, double mult);

  double GetAxis_exponential(int axis, int power);

  double largeconvert_deadcut(int axis, double mult);

  double largeconvert_deadstart(int axis, double mult);

 private:
  // Components (e.g. motor controllers and sensors) should generally be
  // declared private and exposed only through public methods.
};
