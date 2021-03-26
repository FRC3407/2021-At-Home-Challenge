// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include "subsystems/UserInput.h"

UserInput::UserInput(int port) : GenericHID(port) {};

double UserInput::GetY(JoystickHand hand) const {
    //idk what to put here yet
}

double UserInput::GetX(JoystickHand hand) const {
    //idk what to put here yet
}

double UserInput::GetAxis_offset(int axis, double offset){
    return GetRawAxis(axis) + offset;
}

double UserInput::GetAxis_multiply(int axis, double mult){
    return GetRawAxis(axis)*mult;
}

double UserInput::GetAxis_exponential(int axis, int power){
    double raw = GetRawAxis(axis);
    double ret = 1;
    for(int i=0; i<power;i++){
        ret *= raw;
    }
    return ret;
}

double UserInput::largeconvert_deadcut(int axis, double mult){
    double raw = GetRawAxis(axis);
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
    double raw = GetRawAxis(axis);
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