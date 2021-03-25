// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

#include <string>

#pragma once

/**
 * The Constants header provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants.  This should not be used for any other
 * purpose.
 *
 * It is generally a good idea to place constants into subsystem- or
 * command-specific namespaces within this header, which can then be used where
 * they are needed.
 */

namespace global{

}

namespace drivetrain{
    constexpr int frontleft_port = 3;
    constexpr int frontright_port = 1;
    constexpr int backleft_port = 2;
    constexpr int backright_port = 0;

    constexpr bool default_sqr = false;

    constexpr double deceleration_const = 0.98;

    constexpr bool leftinvt = false;
    constexpr bool rightinvt = false;
}

namespace controller{
    constexpr double leftmult_Y = -0.5;
    constexpr double rightmult_Y = -0.5;
    constexpr double leftmult_X = -0.5;
    constexpr double rightmult_X = -0.5;

    constexpr double deadzone = 0.4;

    constexpr int power = 2;

    constexpr int controller_port = 0;

    //string drivemodes[] = {"tank", "arcade", "race", "trigger"};

    //xbox controller keybinds
    constexpr int x_controllerButton_A = 1;
    constexpr int x_controllerButton_B = 2;
    constexpr int x_controllerButton_X = 3;
    constexpr int x_controllerButton_Y = 4;
    constexpr int x_controllerButton_menu = 8;
    constexpr int x_controllerButton_home = 7;
    constexpr int x_controllerButton_lb = 5;
    constexpr int x_controllerButton_rb = 6;
    constexpr int x_controllerButton_ls = 9;
    constexpr int x_controllerButton_rs = 10;
    constexpr int x_controllerStick_ly = 1;
    constexpr int x_controllerStick_lx = 0;
    constexpr int x_controllerStick_ry = 5;
    constexpr int x_controllerStick_rx = 4;
    constexpr int x_controllerTrigger_l = 2;
    constexpr int x_controllerTrigger_r = 3;
    //logitech controller keybinds
    constexpr int l_controllerButton_A = 1;
    constexpr int l_controllerButton_B = 2;
    constexpr int l_controllerButton_X = 0;
    constexpr int l_controllerButton_Y = 3;
    constexpr int l_controllerButton_menu = 9;
    constexpr int l_controllerButton_home = 10;
    // int l_controllerButton_lb = ;
    // int l_controllerButton_rb = ;
    // int l_controllerButton_ls = ;
    // int l_controllerButton_rs = ;
    constexpr int l_controllerStick_ly = 1;
    constexpr int l_controllerStick_lx = 0;
    constexpr int l_controllerStick_ry = 3;
    constexpr int l_controllerStick_rx = 2;
    constexpr int l_controllerTrigger_l = 0;
    constexpr int l_controllerTrigger_r = 0;
}