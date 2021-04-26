// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ControlHID extends SubsystemBase {
    public ControlClass obj;

    public ControlHID(ControlType type){
        switch(type){
            case Xbox : 
                System.out.println("Xbox Controller Mode"); 
                obj = new Xbox(); 
                break;
            case Logitech : 
                System.out.println("Logitech Controller Mode"); 
                obj = new Logitech(); 
                break;
            case Attack3 : 
                System.out.println("Attack3 Arcade Stick Mode"); 
                obj = new Attack3(0); 
                break;
            case Extreme3d : 
                System.out.println("Extreme3d Arcade Stick Mode"); 
                obj = new Extreme3d(); 
                break;
            case ControlBoard :  
                System.out.println("Arcade Stick Board Mode"); 
                obj = new ControlBoard(); 
                break;
            default : 
                System.out.println("ControlType Error");
        }
    };

    @Override
    public void periodic(){}

    public enum ControlType{
        Xbox, 
        Logitech, 
        Attack3, 
        Extreme3d, 
        ControlBoard;
    }

    public class Xbox extends ControlClass{
        public Xbox(){super(Constants.Ports.Xbox);}            
        
        @Override
        public double getPriX(double deadzone, double multiplier, int power){
            return obj.getAxis_largeconvert(Constants.Xbox.LX, deadzone, multiplier, power);
        }

        @Override
        public double getSecX(double deadzone, double multiplier, int power){
            return obj.getAxis_largeconvert(Constants.Xbox.RX, deadzone, multiplier, power);
        }

        @Override
        public double getPriY(double deadzone, double multiplier, int power){
            return obj.getAxis_largeconvert(Constants.Xbox.LY, deadzone, multiplier, power);
        }

        @Override
        public double getSecY(double deadzone, double multiplier, int power){
            return obj.getAxis_largeconvert(Constants.Xbox.RY, deadzone, multiplier, power);
        }

        @Override
        public double getPriTrigger(double deadzone, double multiplier, int power){
            return obj.getAxis_largeconvert(Constants.Xbox.LT, deadzone, multiplier, power);
        }

        @Override
        public double getSecTrigger(double deadzone, double multiplier, int power){
            return obj.getAxis_largeconvert(Constants.Xbox.RT, deadzone, multiplier, power);
        }

        @Override
        public JoystickButton getButton1(){
            return obj.getButton(Constants.Xbox.A);
        }            

        @Override
        public JoystickButton getButton2(){
            return obj.getButton(Constants.Xbox.B);
        }            

        @Override
        public JoystickButton getButton3(){
            return obj.getButton(Constants.Xbox.X);
        }

        @Override
        public JoystickButton getButton4(){
            return obj.getButton(Constants.Xbox.Y);
        }

        @Override
        public JoystickButton getUtility1(){
            return obj.getButton(Constants.Xbox.LB);
        }

        @Override
        public JoystickButton getUtility2(){
            return obj.getButton(Constants.Xbox.RB);
        }

        public JoystickButton getUtility3(){
            return obj.getButton(Constants.Xbox.Home);
        }

        public JoystickButton getUtility4(){
            return obj.getButton(Constants.Xbox.Menu);
        }
    }

    public class Logitech extends ControlClass {
        public Logitech(){super(Constants.Ports.Logitech);}

        @Override
        public double getPriX(double deadzone, double multiplier, int power) {
            return obj.getAxis_largeconvert(Constants.Logi.LX, deadzone, multiplier, power);
        }

        @Override
        public double getSecX(double deadzone, double multiplier, int power) {
            return obj.getAxis_largeconvert(Constants.Logi.RX, deadzone, multiplier, power);
        }

        @Override
        public double getPriY(double deadzone, double multiplier, int power) {
            return obj.getAxis_largeconvert(Constants.Logi.LY, deadzone, multiplier, power);
        }

        @Override
        public double getSecY(double deadzone, double multiplier, int power) {
            return obj.getAxis_largeconvert(Constants.Logi.RY, deadzone, multiplier, power);
        }

        @Override
        public double getPriTrigger(double deadzone, double multiplier, int power) {
            int var = obj.getRawButton(Constants.Logi.LT) ? 1:0;
            return var*(obj.getAxis_largeconvert(Constants.Logi.LY, deadzone, multiplier, power));
        }

        @Override
        public double getSecTrigger(double deadzone, double multiplier, int power) {
            int var = obj.getRawButton(Constants.Logi.RT) ? 1:0;
            return var*(obj.getAxis_largeconvert(Constants.Logi.RY, deadzone, multiplier, power));       
        }

        @Override
        public JoystickButton getButton1() {
            return obj.getButton(Constants.Logi.A);
        }

        @Override
        public JoystickButton getButton2() {
            return obj.getButton(Constants.Logi.B);
        }

        @Override
        public JoystickButton getButton3() {
            return obj.getButton(Constants.Logi.X);
        }

        @Override
        public JoystickButton getButton4() {
            return obj.getButton(Constants.Logi.Y);
        }

        @Override
        public JoystickButton getUtility1() {
            return obj.getButton(Constants.Logi.LB);
        }

        @Override
        public JoystickButton getUtility2() {
            return obj.getButton(Constants.Logi.RB);
        }

        public JoystickButton getUtility3(){
            return obj.getButton(Constants.Logi.Home);
        }

        public JoystickButton getUtility4(){
            return obj.getButton(Constants.Logi.Menu);
        }
    }

    public class Attack3 extends ControlClass{
        public Attack3(int index){super(Constants.Ports.Attack3[index]);}

        @Override
        public double getPriX(double deadzone, double multiplier, int power) {
            return obj.getAxis_largeconvert(Constants.Atk3.X_Axis, deadzone, multiplier, power);
        }

        @Override
        public double getSecX(double deadzone, double multiplier, int power) {
            return obj.getAxis_largeconvert(Constants.Atk3.X_Axis, deadzone, multiplier, power);
        }

        @Override
        public double getPriY(double deadzone, double multiplier, int power) {
            return obj.getAxis_largeconvert(Constants.Atk3.Y_Axis, deadzone, multiplier, power);
        }

        @Override
        public double getSecY(double deadzone, double multiplier, int power) {
            return obj.getAxis_largeconvert(Constants.Atk3.Y_Axis, deadzone, multiplier, power);
        }

        @Override
        public double getPriTrigger(double deadzone, double multiplier, int power) {
            int var = obj.getRawButton(Constants.Atk3.Trigger) ? 1:0;
            return var*(obj.getAxis_largeconvert(Constants.Atk3.S_Axis, deadzone, multiplier, power));
        }

        @Override
        public double getSecTrigger(double deadzone, double multiplier, int power) {
            int var = obj.getRawButton(Constants.Atk3.Trigger) ? 1:0;
            return var*(obj.getAxis_largeconvert(Constants.Atk3.S_Axis, deadzone, multiplier, power));
        }

        @Override
        public JoystickButton getButton1() {
            return obj.getButton(Constants.Atk3.Top_Top);
        }

        @Override
        public JoystickButton getButton2() {
            return obj.getButton(Constants.Atk3.Top_Bottom);
        }

        @Override
        public JoystickButton getButton3() {
            return obj.getButton(Constants.Atk3.Top_Left);
        }

        @Override
        public JoystickButton getButton4() {
            return obj.getButton(Constants.Atk3.Top_Right);
        }

        @Override
        public JoystickButton getUtility1() {
            return obj.getButton(Constants.Atk3.B1);
        }

        @Override
        public JoystickButton getUtility2() {
            return obj.getButton(Constants.Atk3.B2);
        }

        //fill out all buttons later >>
    }

    public class Extreme3d extends ControlClass {
        public Extreme3d(){super(Constants.Ports.Extreme3d);}

        @Override
        public double getPriX(double deadzone, double multiplier, int power) {
            return obj.getAxis_largeconvert(Constants.Ex3d.X_Axis, deadzone, multiplier, power);
        }

        @Override
        public double getSecX(double deadzone, double multiplier, int power) {
            return obj.getAxis_largeconvert(Constants.Ex3d.X_Axis, deadzone, multiplier, power);
        }

        @Override
        public double getPriY(double deadzone, double multiplier, int power) {
            return obj.getAxis_largeconvert(Constants.Ex3d.Y_Axis, deadzone, multiplier, power);
        }

        @Override
        public double getSecY(double deadzone, double multiplier, int power) {
            return obj.getAxis_largeconvert(Constants.Ex3d.Y_Axis, deadzone, multiplier, power);
        }

        @Override
        public double getPriTrigger(double deadzone, double multiplier, int power) {
            int var = obj.getRawButton(Constants.Ex3d.Trigger) ? 1:0;
            return var*(obj.getAxis_largeconvert(Constants.Ex3d.S_Axis, deadzone, multiplier, power));
        }

        @Override
        public double getSecTrigger(double deadzone, double multiplier, int power) {
            int var = obj.getRawButton(Constants.Ex3d.Trigger) ? 1:0;
            return var*(obj.getAxis_largeconvert(Constants.Ex3d.S_Axis, deadzone, multiplier, power));
        }

        @Override
        public JoystickButton getButton1() {
            return obj.getButton(Constants.Ex3d.Top_Left_Top);
        }

        @Override
        public JoystickButton getButton2() {
            return obj.getButton(Constants.Ex3d.Top_Right_Top);
        }

        @Override
        public JoystickButton getButton3() {
            return obj.getButton(Constants.Ex3d.Top_Left_Bottom);
        }

        @Override
        public JoystickButton getButton4() {
            return obj.getButton(Constants.Ex3d.Top_Right_Bottom);
        }

        @Override
        public JoystickButton getUtility1() {
            return obj.getButton(Constants.Ex3d.B7);
        }

        @Override
        public JoystickButton getUtility2() {
            return obj.getButton(Constants.Ex3d.B8);
        }

        //fill out all buttons later >>
    }

    public class ControlBoard extends ControlClass {
        public ControlBoard(){super(Constants.Ports.Extreme3d, Constants.Ports.Attack3[0], Constants.Ports.Attack3[1]);}

        @Override
        public double getPriX(double deadzone, double multiplier, int power){
            return obj.getAxis_largeconvert(Constants.Ex3d.X_Axis, deadzone, multiplier, power);
        }

        @Override
        public double getSecX(double deadzone, double multiplier, int power){
            return obj2.getAxis_largeconvert(Constants.Atk3.X_Axis, deadzone, multiplier, power);
        }

        public double getTirX(double deadzone, double multiplier, int power){
            return obj3.getAxis_largeconvert(Constants.Atk3.X_Axis, deadzone, multiplier, power);
        }

        @Override
        public double getPriY(double deadzone, double multiplier, int power){
            return obj.getAxis_largeconvert(Constants.Ex3d.Y_Axis, deadzone, multiplier, power);
        }

        @Override
        public double getSecY(double deadzone, double multiplier, int power){
            return obj2.getAxis_largeconvert(Constants.Atk3.Y_Axis, deadzone, multiplier, power);
        }

        public double getTirY(double deadzone, double multiplier, int power){
            return obj3.getAxis_largeconvert(Constants.Atk3.Y_Axis, deadzone, multiplier, power);
        }

        @Override
        public double getPriTrigger(double deadzone, double multiplier, int power){
            int var = (obj.getRawButton(Constants.Ex3d.Trigger)) ? 1:0;
            return var*(obj.getAxis_largeconvert(Constants.Ex3d.S_Axis, deadzone, multiplier, power));
        }

        @Override
        public double getSecTrigger(double deadzone, double multiplier, int power){
            int var = (obj2.getRawButton(Constants.Atk3.Trigger)) ? 1:0;
            return var*(obj2.getAxis_largeconvert(Constants.Atk3.S_Axis, deadzone, multiplier, power));
        }

        public double getTirTrigger(double deadzone, double multiplier, int power){
            int var = obj3.getRawButton(Constants.Atk3.Trigger) ? 1:0;
            return var*(obj3.getAxis_largeconvert(Constants.Atk3.S_Axis, deadzone, multiplier, power));
        }

        //change to make better >>> 

        @Override
        public JoystickButton getButton1(){
            return obj.getButton(Constants.Ex3d.Trigger);
        }        
        
        @Override
        public JoystickButton getButton2(){
            return obj.getButton(Constants.Ex3d.Side);
        }        
        
        @Override
        public JoystickButton getButton3(){
            return obj2.getButton(Constants.Atk3.Trigger);
        }

        @Override
        public JoystickButton getButton4(){
            return obj3.getButton(Constants.Atk3.Trigger);
        }

        @Override
        public JoystickButton getUtility1(){
            return obj2.getButton(Constants.Atk3.Top_Top);
        }

        @Override
        public JoystickButton getUtility2(){
            return obj3.getButton(Constants.Atk3.Top_Top);
        }
    }

    public class ControlClass implements ControlInterface {
        protected Input obj;
        protected Input obj2;
        protected Input obj3;
        
        public ControlClass(int port){obj = new Input(port);}    
        public ControlClass(int port, int port2){obj = new Input(port); obj2 = new Input(port2);}
        public ControlClass(int port, int port2, int port3){obj = new Input(port); obj2 = new Input(port2); obj3 = new Input(port3);}        
        
        public double getPriX(double deadzone, double multiplier, int power){return 0;}
        public double getSecX(double deadzone, double multiplier, int power){return 0;}
        public double getPriY(double deadzone, double multiplier, int power){return 0;}
        public double getSecY(double deadzone, double multiplier, int power){return 0;}
        public double getPriTrigger(double deadzone, double multiplier, int power){return 0;}
        public double getSecTrigger(double deadzone, double multiplier, int power){return 0;}

        public JoystickButton getButton1(){return obj.getButton(0);}            
        public JoystickButton getButton2(){return obj.getButton(0);}            
        public JoystickButton getButton3(){return obj.getButton(0);}
        public JoystickButton getButton4(){return obj.getButton(0);}
        public JoystickButton getUtility1(){return obj.getButton(0);}
        public JoystickButton getUtility2(){return obj.getButton(0);}
    }

    public class Input extends GenericHID {
        public Input(int port){super(port);}
    
        @Override
        public double getX(Hand hand){return 0;}
    
        @Override
        public double getY(Hand hand){return 0;} 
    
    
        public JoystickButton getButton(int button){
            return new JoystickButton(this, button); //make sure use of "this" is correct here
        }

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