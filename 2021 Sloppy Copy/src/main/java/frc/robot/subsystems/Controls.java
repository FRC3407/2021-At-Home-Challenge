// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Controls extends SubsystemBase {

    XboxStyle xbox;
    LegacyStyle legacy;
    ArcadeStick arcadestick;
    DualArcade dualstick;
    ArcadeBoard controlboard;

    controltype controltype;

    public Controls(controltype type, int port){
        controltype = type;
        switch(type) {
            case Xbox : {xbox = new XboxStyle(port); break;}
            case Legacy : {legacy = new LegacyStyle(port); break;}
            case Arcade : {arcadestick = new ArcadeStick(port); break;}
            case Dual : {System.out.println("You need to use the constructor with 2 ports for this to work."); break;}
            case Board : {System.out.println("You need to use the constructor with 3 ports for this to work."); break;}
            default : {System.out.println("An error occurred, no controller available.");}
        }  
    }

    public Controls(controltype type, int port1, int port2){
        controltype = type;
        switch(type) {
            case Xbox : {xbox = new XboxStyle(port1); break;}
            case Legacy : {legacy = new LegacyStyle(port1); break;}
            case Arcade : {arcadestick = new ArcadeStick(port1); break;}
            case Dual : {dualstick = new DualArcade(port1, port2); break;}
            case Board : {System.out.println("You need to use the constructor with 3 ports for this to work."); break;}
            default : {System.out.println("An error occurred, no controller available.");}
        } 
    }

    public Controls(controltype type, int port1, int port2, int port3){
        controltype = type;
        switch(type) {
            case Xbox : {xbox = new XboxStyle(port1); break;}
            case Legacy : {legacy = new LegacyStyle(port1); break;}
            case Arcade : {arcadestick = new ArcadeStick(port1); break;}
            case Dual : {dualstick = new DualArcade(port1, port2); break;}
            case Board : {controlboard = new ArcadeBoard(port1, port2, port3); break;}
            default : {System.out.println("An error occurred, no controller available.");}
        } 
    }

    @Override
    public void periodic(){
        
    }

    public enum controltype {
        Xbox,
        Legacy,
        Arcade,
        Dual,
        Board;
    }

    // any controller that follows the xbox keybinds
    public class XboxStyle {
        
        private Input controller;

        public XboxStyle(int port){
            controller = new Input(port);
        }
    }

    // legacy controls for the logitech controller, hopefully the drive computer can evenutally use the updated layout...
    public class LegacyStyle {

        private Input controller;

        public LegacyStyle(int port){
            controller = new Input(port);
        }
    }

    // a single arcade stick
    public class ArcadeStick {

        private Input stick;

        public ArcadeStick(int port){
            stick = new Input(port);
        }
    }

    // two arcade sticks
    public class DualArcade {

        private Input stick1;
        private Input stick2;

        public DualArcade(int port1, int port2){
            stick1 = new Input(port1);
            stick2 = new Input(port2);
        }
    }

    // the arcade stick board
    public class ArcadeBoard {

        private Input stick1;
        private Input stick2;
        private Input stick3;

        public ArcadeBoard(int port1, int port2, int port3) {
            stick1 = new Input(port1);
            stick2 = new Input(port2);
            stick3 = new Input(port3);
        }
    }

    public class Input extends GenericHID {
        public Input(int port){super(port);}
    
        @Override
        public double getX(Hand hand){return 0;}
    
        @Override
        public double getY(Hand hand){return 0;} 
    
    
        public double getAxis_offset(int axis, double offset){return getRawAxis(axis)+ offset;}
    
        public double getAxis_multiplier(int axis, double mult){return getRawAxis(axis)*(mult);}
        
        public double getAxis_exponential(int axis, int ex){
            double raw = getRawAxis(axis);
            double ret = 1;
            for (int i=0; i<ex; i++){
                ret *= raw;
            }
            return ret;
        }
    
        /**THIS FUNCTION COMBINES THE THREE ABOVE ^^^. Takes in the axis of the desired stick, 
         * the deadzone value (if this number is bigger than 1 it will automatically cut off the "mixed number part"so it will be less than 1), 
         * the multiplier value (if this is negative then it inverts the output), and the power (the number of times the raw stick input
         * will be multiplied by itself to simulate more fine control at lower values, but less control at higher ones). At the moment 
         * this fuction is set up so that instead of cutting off all output values (output) bellow the deadzone (input), the first value above the deadzone (output)
         * range will be very close to zero (simulating the "graph" of the function of the IO in this case being raised above the horizontal 
         * line that is the deadzone). Use this Desmos graph as a reference as a general representation of the behavior: https://www.desmos.com/calculator/hp7hs46fhj */
        public double getAxis_largeconvert(int axis, double deadzone, double multiplier, int ex_power){
            double raw = getRawAxis(axis);
            double threshold = Math.abs(deadzone - (int)deadzone);
            double ex = 1;
            double exa = 1;
            for (int i=0; i<ex_power; i++){
                ex *= raw;
                exa *= threshold;
            }
            double ret = Math.copySign(((Math.abs(multiplier))*(Math.copySign(Math.abs(ex), raw))-(Math.copySign(exa, raw))), multiplier*raw);
            if (Math.abs(raw)<threshold){
                ret = 0.0;
            }
            return ret;
        }
    
        /**This function accomplishes the same thing as "OPControllerFunc1", 
         * except that instead of the function starting just above the deadzone, (the origin of the function is moved to where 
         * the deadzone ends) the function starts at (0,0), and all outputs that would correlate to inputs below the deadzone 
         * are just cut out. This function should be used if deadzones are wanted but the maximum output does not want to be altered.
         * Use this Desmos graph as a reference as a general representation of the behavior: https://www.desmos.com/calculator/hp7hs46fhj 
         * (to visualize, only move the "y=" slider, but not the slider for addition in the main funciton)*/
        public double getAxis_largeconvert2(int axis, double deadzone, double multiplier, int ex_power){
            double raw = getRawAxis(axis);
            double threshold = Math.abs(deadzone - (int)deadzone);
            double ex = 1;
            for (int i=0; i<ex_power; i++){
                ex *= raw;
            }
            double ret = multiplier*(Math.copySign(Math.abs(ex), raw));
            if (Math.abs(raw)<threshold){
                ret = 0.0;
            }
            return ret;
        }
    }
}