// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.sensors;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CIEColor;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3.RawColor;
import frc.robot.Dynamics;

public class ColorSense extends SubsystemBase {
  
  private ColorSensorV3 colorsrc = new ColorSensorV3(Constants.colorsensor_port);
  private ColorMatch colormatcher = new ColorMatch();
  private double redarr[] = {0.0, 0.0, 0.0};
  private double greenarr[] = {0.0, 0.0, 0.0};
  private double bluearr[] = {0.0, 0.0, 0.0};
  double count = 0;

  /** Creates a new ColorSense. */
  public ColorSense() {
    colorsrc.configureColorSensor(Constants.color_res, Constants.color_rate, Constants.color_gain);
    coloravg_setup();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    updatevars();
  }

  public void updatevars(){
    Dynamics.red = colorsrc.getRed();
    Dynamics.green = colorsrc.getGreen();
    Dynamics.blue = colorsrc.getBlue();
    Dynamics.infrared = colorsrc.getIR();
    Dynamics.proximity = colorsrc.getProximity();
    Dynamics.closestColor = colorsrc.getColor();
    Dynamics.colorposition = colorsrc.getCIEColor(); 
  }

  public void addMatchColor(double red, double green, double blue){
    colormatcher.addColorMatch(ColorMatch.makeColor(red, green, blue));
  }

  /**
   * Make sure to add colors to match to with the addMatchColor method before running this!
   * @return Returns a proprietary "ColorMatchResult" object, which contains rgb data, along with the confidence of the match. Pass this into "colormatchdata()" to get access to the data. 
   */
  public ColorMatchResult runcolormatch(){
    return colormatcher.matchClosestColor(colorsrc.getColor());
  }

  /**
   * @return Returns an array of doubles with an index of 4 - the first 3 indexes are the rgb values (of the color that it found a match to, 
   * so compare these to the values you added to addMatchColor to find out what colr it is), the last is the confidence of the match. 
   */
  public double[] colormatchdata(ColorMatchResult match){
    double ret[] = {0, 0, 0, 0};
    Color data = match.color;
    ret[0] = data.red;
    ret[1] = data.green;
    ret[2] = data.blue;
    ret[3] = match.confidence;
    return ret;
  }

  public RawColor rawColor(){
    return colorsrc.getRawColor();
  }

  /**
   * @return Returns the "most likely color" from a (farely large) enum of preset rgb colorvalues. 
   */
  public Color colorOut(){
    return colorsrc.getColor();
  }

  /**
   * @return Returns the colorspace in a cubic model (XYZ). 
   */
  public CIEColor cubeColor(){
    return colorsrc.getCIEColor();
  }

  /**
   * @return Returns an 11-bit (0-2047) integer of the proportion of the proximity of whatever it is measuring. 
   */
  public int proximity(){
    return colorsrc.getProximity();
  }

  public int red(){
    return colorsrc.getRed();
  }

  public int green(){
    return colorsrc.getGreen();
  }

  public int blue(){
    return colorsrc.getBlue();
  }

  public int infrared(){
    return colorsrc.getIR();
  }


// * * * * DEBUG COLOR * * * * (likely of low value in practice)

/**
 * run this only once
 */
private void coloravg_setup(){
  redarr[0] = redarr[1] = redarr[2] = colorsrc.getRed();
  greenarr[0] = greenarr[1] = greenarr[2] = colorsrc.getGreen();
  bluearr[0] = bluearr[1] = bluearr[2] = colorsrc.getBlue();
}

private double[] periodic_avg(double value, double[] avgarr){
  double running_avg = avgarr[0];
  double high = avgarr[1];
  double low = avgarr[2];
  double avg = (value + running_avg)/2;
  double ret[] = {avg, high, low};
  if(value > high){ret[1] = value;}
  if(value < low){ret[2] = value;}
  return ret;
}

private void coloravg_periodic(){
  double[] red = periodic_avg(colorsrc.getRed(), redarr);
  double[] green = periodic_avg(colorsrc.getGreen(), greenarr);
  double[] blue = periodic_avg(colorsrc.getBlue(), bluearr);
  redarr[0] = red[0];
  redarr[1] = red[1];
  redarr[2] = red[2];
  greenarr[0] = green[0];
  greenarr[1] = green[1];
  greenarr[2] = green[2];
  bluearr[0] = blue[0];
  bluearr[1] = blue[1];
  bluearr[2] = blue[2];
}

/**
 * @param counts - the number of periodic runs that it will take to update colorsensor values - ex. if counts = 25, then the colorsenosr will update 2 times per second
 */
private void coloravg_lowfreq(int counts){
  if(count == counts){
    coloravg_periodic();
    count = 0;
  }else{
    count += 1;
  }
}
  

// * * * * OLD METHODS * * * * 

  private double magnitude(double red, double green, double blue){
    return Math.sqrt(red*red + blue*blue + green*green);
  }

  public double redadv(boolean mag){
    double red = colorsrc.getRed();
    double green = colorsrc.getGreen();
    double blue = colorsrc.getBlue();
    double cmag = magnitude(red, green, blue);
    double ret = 0;
    if(!mag){ret = red;}
    else if(mag){ret = red/cmag;}
    return ret;
  }

  public double greenadv(boolean mag){
    double red = colorsrc.getRed();
    double green = colorsrc.getGreen();
    double blue = colorsrc.getBlue();
    double cmag = magnitude(red, green, blue);
    double ret = 0;
    if(!mag){ret = green;}
    else if(mag){ret = green/cmag;}
    return ret;  
  }

  public double blueadv(boolean mag){
    double red = colorsrc.getRed();
    double green = colorsrc.getGreen();
    double blue = colorsrc.getBlue();
    double cmag = magnitude(red, green, blue);
    double ret = 0;
    if(!mag){ret = blue;}
    else if(mag){ret = blue/cmag;}
    return ret;
  }

  /**
   * Get the raw colors from the colorsensor
   * @param - can be 0 or 1, mode 0 returns the raw output from the colorsensor, while mode 1 returns the output after dividing the colors by the magnitude
   * @return an array of doubles, the first index being the red value, the second the green value, and the third being the blue value;
   */
  public double[] rawcolors(int mode){
    double red = colorsrc.getRed();
    double green = colorsrc.getGreen();
    double blue = colorsrc.getBlue();
    double cmag = magnitude(red, green, blue);
    double[] ret = {0.0, 0.0, 0.0};
    if(mode == 0){
    ret[0] = red;
    ret[1] = green;
    ret[2] = blue;
    }else if(mode == 1){
    ret[0] = red/cmag;
    ret[1] = green/cmag;
    ret[2] = blue/cmag;
    }
    return ret;
  }

  //WILL NEED FIXING IF THERE ARE ACTUALLY TWO COLORSENSORS
  // public double[] rawcolors2(){
  //   double[] ret = {0.0, 0.0, 0.0};
  //   ret[0] = colorsrc2.getRed();
  //   ret[1] = colorsrc2.getGreen();
  //   ret[2] = colorsrc2.getBlue();
  //   return ret;
  // }

  //needs some work, and thought on what should be returned, where, and how this will integrate elsewhere
  //Unsure about if using the "double arrays" for the color calibration will work at this point, further testing needed
  public String colorCompare(){
    double[] ycal = Constants.yellow;
    double[] gcal = Constants.green;
    double[] ccal = Constants.cyan;
    double[] rcal = Constants.red;
    double red1 = redadv(true);
    double green1 = greenadv(true);
    double blue1 = blueadv(true);
    if (red1<ycal[0] & red1>ycal[1] & green1<ycal[2] & green1>ycal[3] & blue1>ycal[4] & blue1<ycal[5]){
    return "Yellow";
    }else if (red1<gcal[0] & red1>gcal[1] & green1<gcal[2] & green1>gcal[3] & blue1<gcal[4] & blue1>gcal[5]){
    return "Green";
    }else if (red1<ccal[0] & red1>ccal[1] & green1<ccal[2] & green1>ccal[3] & blue1<ccal[4] & blue1>ccal[5]){
    return "Cyan";
    }else if (red1<rcal[0] & red1>rcal[1] & green1<rcal[2] & green1>rcal[3] & blue1<rcal[4] & blue1>rcal[5]){
    return "Red";
    }else{
    return "None";
    }
  }
}

