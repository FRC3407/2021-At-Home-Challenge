// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#pragma once

#include <frc2/command/SubsystemBase.h>
#include <frc/SpeedControllerGroup.h>
#include <frc/drive/DifferentialDrive.h>
#include <frc/PWMVictorSPX.h>
#include "Constants.h"

class DriveTrain : public frc2::SubsystemBase {
 public:
  DriveTrain();

  void tankdrive(double leftspeed, double rightspeed);

  void arcadedrive(double speed, double rotation);

  void racedrive(double forward, double backward, double rotation);

  void triggerdrive(double left_trigger, double right_trigger);

  void setleft(double speed);

  void setright(double speed);

  double leftspeed();

  double rightspeed();

  /**
   * Will be called periodically whenever the CommandScheduler runs.
   */
  void Periodic() override;

 private:
  // Components (e.g. motor controllers and sensors) should generally be
  // declared private and exposed only through public methods.

  frc::PWMVictorSPX frontleft;
  frc::PWMVictorSPX frontright;
  frc::PWMVictorSPX backleft;
  frc::PWMVictorSPX backright;

  frc::SpeedControllerGroup left{frontleft, backleft};
  frc::SpeedControllerGroup right{frontright, backright};

  frc::DifferentialDrive drive_main{left, right};
};