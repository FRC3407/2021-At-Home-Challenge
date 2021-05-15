package frc.robot;

import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.subsystems.input.Input;

public class Maths {
    //returns 1 or -1
    public int signVector(double value){
        return (int)(value/Math.abs(value));
    }


    /** * * * * * * * * * * * * * * * * * * * * * * *
     * https://www.desmos.com/calculator/s0qabhtdrh *
     *                                              *
     * * * * * * * * * * * * * * * * * * * * * * * */ 


    //utilizes default curve, assumes no boost
    public double driveMath(double input){
        double offset = (Constants.GeneralInput.sensitivity*Math.pow(Constants.GeneralInput.deadzone, Constants.GeneralInput.smoothing))/(1-Math.pow(Constants.GeneralInput.deadzone, Constants.GeneralInput.smoothing));
        return MathUtil.clamp(signVector(input)*((Constants.GeneralInput.sensitivity+offset)*Math.abs(Math.pow(input, Constants.GeneralInput.smoothing))-offset), -Constants.speed_limit, Constants.speed_limit);
    }

    //uses settings contained in GeneralInput by default
    public double driveMath(double input, boolean boost){
        if(boost){
            double offsetb = ((Constants.speed_limit-Constants.boost)*Math.pow(Constants.GeneralInput.deadzone, Constants.GeneralInput.smoothing))/(1-Math.pow(Constants.GeneralInput.deadzone, Constants.GeneralInput.smoothing));
            return MathUtil.clamp(signVector(Constants.GeneralInput.sensitivity)*signVector(input)*((Constants.speed_limit-(Constants.boost-offsetb))*Math.abs(Math.pow(input, Constants.GeneralInput.smoothing))+(Constants.boost-offsetb)), -Constants.speed_limit, Constants.speed_limit);
        }else{
            double offset = (Constants.GeneralInput.sensitivity*Math.pow(Constants.GeneralInput.deadzone, Constants.GeneralInput.smoothing))/(1-Math.pow(Constants.GeneralInput.deadzone, Constants.GeneralInput.smoothing));
            return MathUtil.clamp(signVector(input)*((Constants.GeneralInput.sensitivity+offset)*Math.abs(Math.pow(input, Constants.GeneralInput.smoothing))-offset), -Constants.speed_limit, Constants.speed_limit);
        }
    }

    //'Theoretically' uses settings from the inputtype specified, assumes no boost(default curve)
    public double driveMath(double input, Input.InputType type){
        return 0;
    }

    //'Theoretically' uses settings from the inputtype specified
    public double driveMath(double input, Input.InputType type, boolean boost){
        return 0;
    }

    //uses settings provided, assumes no boost and utilizes default curve
    public double driveMath(double input, double deadzone, double mult, double power, double limit){
        double offset = (mult*Math.pow(deadzone, power))/(1-Math.pow(deadzone, power));
        return MathUtil.clamp(signVector(input)*((mult+offset)*Math.abs(Math.pow(input, power))-offset), -limit, limit);
    }

    //uses settings provided and takes in boost option
    public double driveMath(double input, double deadzone, double mult, double power, double limit, double boostval, boolean boost){
        if(boost){
            double offsetb = ((limit-boostval)*Math.pow(deadzone, power))/(1-Math.pow(deadzone, power));
            return MathUtil.clamp(signVector(mult)*signVector(input)*((limit-(boostval-offsetb))*Math.abs(Math.pow(input, power))+(boostval-offsetb)), -limit, limit);
        }else{
            double offset = (mult*Math.pow(deadzone, power))/(1-Math.pow(deadzone, power));
            return MathUtil.clamp(signVector(input)*((mult+offset)*Math.abs(Math.pow(input, power))-offset), -limit, limit);
        }
    }
}
