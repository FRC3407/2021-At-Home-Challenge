// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include "subsystems/DriveTrain.h"

DriveTrain::DriveTrain() : frontleft{drivetrain::frontleft_port}, frontright{drivetrain::frontright_port}, backleft{drivetrain::backleft_port}, backright{drivetrain::backright_port}
{
    DriveTrain::left.SetInverted(drivetrain::leftinvt);
    DriveTrain::right.SetInverted(drivetrain::rightinvt);
}

    void DriveTrain::tankdrive(double leftspeed, double rightspeed){
        drive_main.TankDrive(leftspeed, rightspeed, drivetrain::default_sqr);
    }

    void DriveTrain::arcadedrive(double speed, double rotation){
        drive_main.ArcadeDrive(speed, rotation, drivetrain::default_sqr);
    }

    void DriveTrain::racedrive(double forward, double backward, double rotation){
        double cumulative = forward - backward;
        drive_main.ArcadeDrive(cumulative, rotation, drivetrain::default_sqr);
    }

    void DriveTrain::triggerdrive(double left_trigger, double right_trigger){
        drive_main.TankDrive(left_trigger, right_trigger);        
    }

    void DriveTrain::setleft(double speed){
        left.Set(speed);
    }

    void DriveTrain::setright(double speed){
        right.Set(speed);
    }

    double DriveTrain::leftspeed(){
        return left.Get();
    }

    double DriveTrain::rightspeed(){
        return right.Get();
    }
  

// This method will be called once per scheduler run
void DriveTrain::Periodic() {}
