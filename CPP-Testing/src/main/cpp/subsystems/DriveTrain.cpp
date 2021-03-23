// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include "subsystems/DriveTrain.h"
#include "Constants.h"

using namespace drivetrain;

DriveTrain::DriveTrain() = default;

    frc::PWMVictorSPX frontleft(frontleft_port);
    frc::PWMVictorSPX frontright(frontright_port);
    frc::PWMVictorSPX backleft(backleft_port);
    frc::PWMVictorSPX backright(backright_port);

    frc::SpeedControllerGroup left(frontleft, backleft);
    frc::SpeedControllerGroup right(frontright, backright);

    frc::DifferentialDrive db_main(left, right);

    void tankdrive(double leftspeed, double rightspeed){
        db_main.TankDrive(leftspeed, rightspeed, default_sqr);
    }

    void arcadedrive(){
        
    }

    void racedrive(){

    }

    void triggerdrive(){
        
    }
    

// This method will be called once per scheduler run
void DriveTrain::Periodic() {}
