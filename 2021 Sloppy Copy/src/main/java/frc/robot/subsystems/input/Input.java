// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.input;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Input extends SubsystemBase {
    private InputBase Controller;

    public Input(InputType type){
        switch(type){
            case Xbox : 
                System.out.println("Xbox Controller Mode"); 
                Controller = new Xbox(); 
                break;
            case Logitech : 
                System.out.println("Logitech Controller Mode"); 
                Controller = new Logitech(); 
                break;
            case Attack3 : 
                System.out.println("Attack3 Arcade Stick Mode"); 
                Controller = new Attack3(0); 
                break;
            case Extreme3d : 
                System.out.println("Extreme3d Arcade Stick Mode"); 
                Controller = new Extreme3d(); 
                break;
            case ControlBoard :  
                System.out.println("Arcade Stick Board Mode"); 
                Controller = new ControlBoard(); 
                break;
            default : 
                System.out.println("InputType Error");
        }
    };

    @Override
    public void periodic(){}

    public InputBase getInput(){
        return Controller;
    }

    public enum InputType{
        Xbox, 
        Logitech, 
        Attack3, 
        Extreme3d, 
        ControlBoard;
    }

    public class Xbox extends InputBase{
        public Xbox(){super(Constants.Ports.Xbox);} 
        
        @Override
        public double getPriX(double deadzone, double multiplier, int power){return Controller[0].getAxis_largeconvert(Constants.Xbox.LX, deadzone, multiplier, power);}
        @Override
        public double getSecX(double deadzone, double multiplier, int power){return Controller[0].getAxis_largeconvert(Constants.Xbox.RX, deadzone, multiplier, power);}
        @Override
        public double getPriY(double deadzone, double multiplier, int power){return Controller[0].getAxis_largeconvert(Constants.Xbox.LY, deadzone, multiplier, power);}
        @Override
        public double getSecY(double deadzone, double multiplier, int power){return Controller[0].getAxis_largeconvert(Constants.Xbox.RY, deadzone, multiplier, power);}
        @Override
        public double getPriTrigger(double deadzone, double multiplier, int power){return Controller[0].getAxis_largeconvert(Constants.Xbox.LT, deadzone, multiplier, power);}
        @Override
        public double getSecTrigger(double deadzone, double multiplier, int power){return Controller[0].getAxis_largeconvert(Constants.Xbox.RT, deadzone, multiplier, power);}

        @Override
        public JoystickButton getButton1(){return Controller[0].getButton(Constants.Xbox.A);}            
        @Override
        public JoystickButton getButton2(){return Controller[0].getButton(Constants.Xbox.B);}            
        @Override
        public JoystickButton getButton3(){return Controller[0].getButton(Constants.Xbox.X);}
        @Override
        public JoystickButton getButton4(){return Controller[0].getButton(Constants.Xbox.Y);}
        @Override
        public JoystickButton getUtility1(){return Controller[0].getButton(Constants.Xbox.LB);}
        @Override
        public JoystickButton getUtility2(){return Controller[0].getButton(Constants.Xbox.RB);}
        public JoystickButton getUtility3(){return Controller[0].getButton(Constants.Xbox.Home);}
        public JoystickButton getUtility4(){return Controller[0].getButton(Constants.Xbox.Menu);}
        public JoystickButton getExtra1() {return Controller[0].getButton(Constants.Xbox.LS);}
        public JoystickButton getExtra2() {return Controller[0].getButton(Constants.Xbox.RS);}
    }

    public class Logitech extends InputBase {
        public Logitech(){super(Constants.Ports.Logitech);}

        @Override
        public double getPriX(double deadzone, double multiplier, int power) {return Controller[0].getAxis_largeconvert(Constants.Logi.LX, deadzone, multiplier, power);}
        @Override
        public double getSecX(double deadzone, double multiplier, int power) {return Controller[0].getAxis_largeconvert(Constants.Logi.RX, deadzone, multiplier, power);}
        @Override
        public double getPriY(double deadzone, double multiplier, int power) {return Controller[0].getAxis_largeconvert(Constants.Logi.LY, deadzone, multiplier, power);}
        @Override
        public double getSecY(double deadzone, double multiplier, int power) {return Controller[0].getAxis_largeconvert(Constants.Logi.RY, deadzone, multiplier, power);}
        @Override
        public double getPriTrigger(double deadzone, double multiplier, int power) {
            int var = Controller[0].getRawButton(Constants.Logi.LT) ? 1:0;
            return var*(Controller[0].getAxis_largeconvert(Constants.Logi.LY, deadzone, multiplier, power));
        }
        @Override
        public double getSecTrigger(double deadzone, double multiplier, int power) {
            int var = Controller[0].getRawButton(Constants.Logi.RT) ? 1:0;
            return var*(Controller[0].getAxis_largeconvert(Constants.Logi.RY, deadzone, multiplier, power));
        }

        @Override
        public JoystickButton getButton1() {return Controller[0].getButton(Constants.Logi.A);}
        @Override
        public JoystickButton getButton2() {return Controller[0].getButton(Constants.Logi.B);}
        @Override
        public JoystickButton getButton3() {return Controller[0].getButton(Constants.Logi.X);}
        @Override
        public JoystickButton getButton4() {return Controller[0].getButton(Constants.Logi.Y);}
        @Override
        public JoystickButton getUtility1() {return Controller[0].getButton(Constants.Logi.LB);}
        @Override
        public JoystickButton getUtility2() {return Controller[0].getButton(Constants.Logi.RB);}
        public JoystickButton getUtility3() {return Controller[0].getButton(Constants.Logi.Home);}
        public JoystickButton getUtility4() {return Controller[0].getButton(Constants.Logi.Menu);}
        public JoystickButton getExtra1() {return Controller[0].getButton(Constants.Logi.LS);}
        public JoystickButton getExtra2() {return Controller[0].getButton(Constants.Logi.RS);}
    }

    public class Attack3 extends InputBase{
        public Attack3(int index){super(Constants.Ports.Attack3[index]);}

        @Override
        public double getPriX(double deadzone, double multiplier, int power) {return Controller[0].getAxis_largeconvert(Constants.Atk3.X_Axis, deadzone, multiplier, power);}
        @Override
        public double getSecX(double deadzone, double multiplier, int power) {return Controller[0].getAxis_largeconvert(Constants.Atk3.X_Axis, deadzone, multiplier, power);}
        @Override
        public double getPriY(double deadzone, double multiplier, int power) {return Controller[0].getAxis_largeconvert(Constants.Atk3.Y_Axis, deadzone, multiplier, power);}
        @Override
        public double getSecY(double deadzone, double multiplier, int power) {return Controller[0].getAxis_largeconvert(Constants.Atk3.Y_Axis, deadzone, multiplier, power);}
        @Override
        public double getPriTrigger(double deadzone, double multiplier, int power) {
            int var = Controller[0].getRawButton(Constants.Atk3.Trigger) ? 1:0;
            return var*(Controller[0].getAxis_largeconvert(Constants.Atk3.S_Axis, deadzone, multiplier, power));
        }
        @Override
        public double getSecTrigger(double deadzone, double multiplier, int power) {
            int var = Controller[0].getRawButton(Constants.Atk3.Trigger) ? 1:0;
            return var*(Controller[0].getAxis_largeconvert(Constants.Atk3.S_Axis, deadzone, multiplier, power));
        }

        public JoystickButton getTrigger() {return Controller[0].getButton(Constants.Atk3.Trigger);}

        @Override
        public JoystickButton getButton1() {return Controller[0].getButton(Constants.Atk3.Top_Top);}
        @Override
        public JoystickButton getButton2() {return Controller[0].getButton(Constants.Atk3.Top_Bottom);}
        @Override
        public JoystickButton getButton3() {return Controller[0].getButton(Constants.Atk3.Top_Left);}
        @Override
        public JoystickButton getButton4() {return Controller[0].getButton(Constants.Atk3.Top_Right);}
        @Override
        public JoystickButton getUtility1() {return Controller[0].getButton(Constants.Atk3.B1);}
        @Override
        public JoystickButton getUtility2() {return Controller[0].getButton(Constants.Atk3.B2);}
        public JoystickButton getUtility3() {return Controller[0].getButton(Constants.Atk3.B3);}
        public JoystickButton getUtility4() {return Controller[0].getButton(Constants.Atk3.B4);}
        public JoystickButton getUtility5() {return Controller[0].getButton(Constants.Atk3.B5);}
        public JoystickButton getUtility6() {return Controller[0].getButton(Constants.Atk3.B6);}
    }

    public class Extreme3d extends InputBase {
        public Extreme3d(){super(Constants.Ports.Extreme3d);}

        @Override
        public double getPriX(double deadzone, double multiplier, int power) {return Controller[0].getAxis_largeconvert(Constants.Ex3d.X_Axis, deadzone, multiplier, power);}
        @Override
        public double getSecX(double deadzone, double multiplier, int power) {return Controller[0].getAxis_largeconvert(Constants.Ex3d.X_Axis, deadzone, multiplier, power);}
        @Override
        public double getPriY(double deadzone, double multiplier, int power) {return Controller[0].getAxis_largeconvert(Constants.Ex3d.Y_Axis, deadzone, multiplier, power);}
        @Override
        public double getSecY(double deadzone, double multiplier, int power) {return Controller[0].getAxis_largeconvert(Constants.Ex3d.Y_Axis, deadzone, multiplier, power);}
        @Override
        public double getPriTrigger(double deadzone, double multiplier, int power) {
            int var = Controller[0].getRawButton(Constants.Ex3d.Trigger) ? 1:0;
            return var*(Controller[0].getAxis_largeconvert(Constants.Ex3d.S_Axis, deadzone, multiplier, power));
        }
        @Override
        public double getSecTrigger(double deadzone, double multiplier, int power) {
            int var = Controller[0].getRawButton(Constants.Ex3d.Side) ? 1:0;
            return var*(Controller[0].getAxis_largeconvert(Constants.Ex3d.S_Axis, deadzone, multiplier, power));
        }
        
        public JoystickButton getTrigger() {return Controller[0].getButton(Constants.Ex3d.Trigger);}
        public JoystickButton getSide() {return Controller[0].getButton(Constants.Ex3d.Side);}

        @Override
        public JoystickButton getButton1() {return Controller[0].getButton(Constants.Ex3d.Top_Left_Top);}
        @Override
        public JoystickButton getButton2() {return Controller[0].getButton(Constants.Ex3d.Top_Right_Top);}
        @Override
        public JoystickButton getButton3() {return Controller[0].getButton(Constants.Ex3d.Top_Left_Bottom);}
        @Override
        public JoystickButton getButton4() {return Controller[0].getButton(Constants.Ex3d.Top_Right_Bottom);}
        @Override
        public JoystickButton getUtility1() {return Controller[0].getButton(Constants.Ex3d.B7);}
        @Override
        public JoystickButton getUtility2() {return Controller[0].getButton(Constants.Ex3d.B8);}
        public JoystickButton getUtility3() {return Controller[0].getButton(Constants.Ex3d.B9);}
        public JoystickButton getUtility4() {return Controller[0].getButton(Constants.Ex3d.B10);}
        public JoystickButton getUtility5() {return Controller[0].getButton(Constants.Ex3d.B11);}
        public JoystickButton getUtility6() {return Controller[0].getButton(Constants.Ex3d.B12);}
    }

    public class ControlBoard extends InputBase {
        public ControlBoard(){super(Constants.Ports.Extreme3d, Constants.Ports.Attack3[0], Constants.Ports.Attack3[1]);}

        @Override
        public double getPriX(double deadzone, double multiplier, int power){return Controller[0].getAxis_largeconvert(Constants.Ex3d.X_Axis, deadzone, multiplier, power);}
        @Override
        public double getSecX(double deadzone, double multiplier, int power){return Controller[1].getAxis_largeconvert(Constants.Atk3.X_Axis, deadzone, multiplier, power);}
        public double getTirX(double deadzone, double multiplier, int power){return Controller[2].getAxis_largeconvert(Constants.Atk3.X_Axis, deadzone, multiplier, power);}
        @Override
        public double getPriY(double deadzone, double multiplier, int power){return Controller[0].getAxis_largeconvert(Constants.Ex3d.Y_Axis, deadzone, multiplier, power);}
        @Override
        public double getSecY(double deadzone, double multiplier, int power){return Controller[1].getAxis_largeconvert(Constants.Atk3.Y_Axis, deadzone, multiplier, power);}
        public double getTirY(double deadzone, double multiplier, int power){return Controller[2].getAxis_largeconvert(Constants.Atk3.Y_Axis, deadzone, multiplier, power);}
        @Override
        public double getPriTrigger(double deadzone, double multiplier, int power){
            int var = (Controller[0].getRawButton(Constants.Ex3d.Trigger)) ? 1:0;
            return var*(Controller[0].getAxis_largeconvert(Constants.Ex3d.S_Axis, deadzone, multiplier, power));
        }
        @Override
        public double getSecTrigger(double deadzone, double multiplier, int power){
            int var = (Controller[1].getRawButton(Constants.Atk3.Trigger)) ? 1:0;
            return var*(Controller[1].getAxis_largeconvert(Constants.Atk3.S_Axis, deadzone, multiplier, power));}
        public double getTirTrigger(double deadzone, double multiplier, int power){
            int var = Controller[2].getRawButton(Constants.Atk3.Trigger) ? 1:0;
            return var*(Controller[2].getAxis_largeconvert(Constants.Atk3.S_Axis, deadzone, multiplier, power));
        }

        //change to make better >>> 
        @Override
        public JoystickButton getButton1(){return Controller[0].getButton(Constants.Ex3d.Trigger);}            
        @Override
        public JoystickButton getButton2(){return Controller[0].getButton(Constants.Ex3d.Side);}        
        @Override
        public JoystickButton getButton3(){return Controller[1].getButton(Constants.Atk3.Trigger);}
        @Override
        public JoystickButton getButton4(){return Controller[2].getButton(Constants.Atk3.Trigger);}
        @Override
        public JoystickButton getUtility1(){return Controller[1].getButton(Constants.Atk3.Top_Top);}
        @Override
        public JoystickButton getUtility2(){return Controller[2].getButton(Constants.Atk3.Top_Top);}
    }

    public class InputBase {
        protected InputDevice[] Controller = new InputDevice[Constants.maximum_controlobjs];
        
        public InputBase(int port){Controller[0] = new InputDevice(port); Controller[1] = null; Controller[2] = null;}    
        public InputBase(int port, int port2){Controller[0] = new InputDevice(port); Controller[1] = new InputDevice(port2); Controller[2] = null;}
        public InputBase(int port, int port2, int port3){Controller[0] = new InputDevice(port); Controller[1] = new InputDevice(port2); Controller[2] = new InputDevice(port3);}  

        public InputDevice[] getDevices(){return Controller;}
        public int getActiveDevices(){return Controller[2] != null ? 3:(Controller[1] != null ? 2:(Controller[0] != null ? 1:0));}
        
        public double getPriX(double deadzone, double multiplier, int power){return 0;}
        public double getSecX(double deadzone, double multiplier, int power){return 0;}
        public double getPriY(double deadzone, double multiplier, int power){return 0;}
        public double getSecY(double deadzone, double multiplier, int power){return 0;}
        public double getPriTrigger(double deadzone, double multiplier, int power){return 0;}
        public double getSecTrigger(double deadzone, double multiplier, int power){return 0;}

        public JoystickButton getButton1(){return Controller[0].getButton(0);}            
        public JoystickButton getButton2(){return Controller[0].getButton(0);}            
        public JoystickButton getButton3(){return Controller[0].getButton(0);}
        public JoystickButton getButton4(){return Controller[0].getButton(0);}
        public JoystickButton getUtility1(){return Controller[0].getButton(0);}
        public JoystickButton getUtility2(){return Controller[0].getButton(0);}
    }

    public class InputDevice extends GenericHID {
        public InputDevice(int port){super(port);}
    
        @Override
        public double getX(Hand hand){return 0;}
        @Override
        public double getY(Hand hand){return 0;} 
    
        public JoystickButton getButton(int button){return new JoystickButton(this, button);}

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
         * the deadzone value (if this number is bigger than 1 it will automatically cut off the "mixed number part" so it will be less than 1), 
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