// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include "subsystems/UserInput.h"

UserInput::UserInput() = default;

double UserInput::axis_raw(int axis){
    return Controller.GetRawAxis(axis);
}

double UserInput::axis_offset(int axis, double offset){
    return Controller.GetRawAxis(axis) + offset;
}

double UserInput::axis_multiply(int axis, double mult){
    return Controller.GetRawAxis(axis)*mult;
}

double UserInput::axis_exponential(int axis, int power){
    double raw = Controller.GetRawAxis(axis);
    double ret = 1;
    for(int i=0; i<power;i++){
        ret *= raw;
    }
    return ret;
}

double UserInput::largeconvert_deadcut(int axis, double mult){
    double raw = Controller.GetRawAxis(axis);
    double threshold = abs(controller::deadzone - (int)controller::deadzone);
    double ex = 1;
    for(int i=0;i<controller::power;i++){
        ex*=raw;
    }
    double ret = mult*copysign(abs(ex), raw);
    if(abs(raw)<threshold){
        ret - 0.0;
    }
    return ret;
}

double UserInput::largeconvert_deadstart(int axis, double mult){
    double raw = Controller.GetRawAxis(axis);
    double threshold = abs(controller::deadzone - (int)controller::deadzone);
    double exa = 1;
    double ex = 1;
    for(int i=0; i<controller::power; i++){
        ex *= raw;
        exa *= threshold;
    }
    double ret = copysign((abs(mult)*copysign(abs(ex), raw) - copysign(exa, raw)), mult*raw);
    if(abs(raw)<threshold){
        ret = 0.0;
    }
    return ret;
}

// This method will be called once per scheduler run
void UserInput::Periodic() {}
