// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems.sensors;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.analog.adis16470.frc.ADIS16470_IMU;
import com.analog.adis16470.frc.ADIS16470_IMU.ADIS16470CalibrationTime;
import com.analog.adis16470.frc.ADIS16470_IMU.IMUAxis;
import frc.robot.Constants;
import frc.robot.Dynamics;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IMU_Gyro extends SubsystemBase {
  private ADIS16470_IMU imu = new ADIS16470_IMU();
  private BuiltInAccelerometer rio = new BuiltInAccelerometer(); 
  
  private double accelerationX=0, accelerationY=0, velocityX=0, velocityY=0, distanceX=0, distanceY=0;

  /** Creates a new IMU_Gyro. */
  public IMU_Gyro() {
    imu.setYawAxis(Constants.IMU.yaw);
    imu.configCalTime(Constants.IMU.calibrationtime);
    Dynamics.DataBus.initAngle = currentAngle();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    updatevars_AVG();
    dynamic_update();
  }

  //this is not the correct way to do it, but I left it in anyway
  public void updatevars(){
    accelerationX = imuX_si();
    accelerationY = imuY_si();
    velocityX += (accelerationX*Dynamics.periodtime);
    velocityY += (accelerationY*Dynamics.periodtime);
    distanceX += (velocityX*Dynamics.periodtime);
    distanceY += (velocityY*Dynamics.periodtime);
  }

  public void updatevars_AVG(){
    double accX = imuX_si();
    double accY = imuY_si();
    distanceX += (velocityX + (((accelerationX + accX)/2) * Dynamics.periodtime))*Dynamics.periodtime;
    distanceY += (velocityY + (((accelerationY + accY)/2) * Dynamics.periodtime))*Dynamics.periodtime;
    accelerationX = accX;
    accelerationY = accY;
    velocityX += (accelerationX*Dynamics.periodtime);
    velocityY += (accelerationY*Dynamics.periodtime);
  }

  public void dynamic_update(){
    Dynamics.DataBus.currentAngle = currentAngle();
    Dynamics.DataBus.accelerationX = accelerationX;
    Dynamics.DataBus.accelerationY = accelerationY;
    Dynamics.DataBus.velocityX = velocityX;
    Dynamics.DataBus.velocityY = velocityY;
    Dynamics.DataBus.distanceX = distanceX;
    Dynamics.DataBus.distanceY = distanceY;
  }

  public void outputdata(){
    SmartDashboard.putNumber("Acceleration X", accelerationX);
    SmartDashboard.putNumber("acceleration Y", accelerationY);
    SmartDashboard.putNumber("Velocity X", velocityX);
    SmartDashboard.putNumber("Velocity Y", velocityY);
    SmartDashboard.putNumber("Distance X", distanceX);
    SmartDashboard.putNumber("Distance Y", distanceY);
    SmartDashboard.putNumber("Angular Rate", currentRate());
    SmartDashboard.putNumber("Current Angle", currentAngle());
  }

  /**
   * @return rotation in DEGREES per SECOND
   */
  public double currentRate(){ 
    return imu.getRate();
  }

  /**
   * @return the CURRENT ANGLE (total since last calibration) of the YAW axis (this can be changed in Constants)
   */
  public double currentAngle(){
    //note: a positive angle is in the clockwise direction
    return imu.getAngle();
  }

  /**
   * @return returns in G-FORCES, so make sure to convert to something useful
   */
  public double imuX(){
    return imu.getAccelInstantX();
  }

  /**
   * @return returns in G-FORCES, so make sure to convert to something useful
   */
  public double imuY(){
    return imu.getAccelInstantY();
  }

  /**
   * @return returns in G-FORCES, so make sure to convert to something useful
   */
  public double imuZ(){
    return imu.getAccelInstantZ();
  }

  public double imuX_si(){
    return imu.getAccelInstantX() * 9.81;
  }

  public double imuY_si(){
    return imu.getAccelInstantY() * 9.81;
  }

  public double imuZ_si(){
    return imu.getAccelInstantZ() * 9.81;
  }

  /**
   * @return returns in G-FORCES, so make sure to convert to something useful
   */
  public double rioX(){
    return rio.getX();
  }

  /**
   * @return returns in G-FORCES, so make sure to convert to something useful
   */
  public double rioY(){
    return rio.getY();
  }

  /**
   * @return returns in G-FORCES, so make sure to convert to something useful
   */
  public double rioZ(){
    return rio.getZ();
  }

  public double rioX_si(){
    return rio.getX() * 9.81;
  }

  public double rioY_si(){
    return rio.getY() * 9.81;
  }

  public double rioZ_si(){
    return rio.getZ() * 9.81;
  }

  public double avgX(){
    return (rio.getX() + imu.getAccelInstantX()) /2;
  }

  public double avgY(){
    return (rio.getY() + imu.getAccelInstantY()) /2;
  }

  public double avgZ(){
    return (rio.getZ() + imu.getAccelInstantZ()) /2;
  }

  public double imuVelX(){
    return velocityX;
  }

  public double imuVelY(){
    return velocityY;
  }

  public double imuDistX(){
    return distanceX;
  }

  public double imuDistY(){
    return distanceY;
  }

  /**
   * Make sure the robot doesn't move while recalibrating
   */
  public void recalibrate(IMUAxis axis, ADIS16470CalibrationTime time){
    imu.setYawAxis(axis);
    imu.configCalTime(time);
    imu.calibrate();
  }





  // public double[] accelDist_v1(double period_time){
  //   double[] pos = {0.0, 0.0, 0.0};
  //   double accelX[] = {0,0,0,0,0,0,0,0,0,0};
  //   double accelY[] = {0,0,0,0,0,0,0,0,0,0};
  //   double accelZ[] = {0,0,0,0,0,0,0,0,0,0};
  //   int i = 0;
  //   double distanceX = 0;
  //   double distanceY = 0;
  //   double distanceZ = 0;
  //   double sum;
  //   double dt = period_time;
  //   dt = 0.02;

  //   //run a loop of ten
  //   while(i++ <=9){
  //     //fill up array with values from imu (there are ten spots and the loop runs 10 times)
  //     accelX[i] = imu.getAccelInstantX() * 9.81;
  //     accelY[i] = imu.getAccelInstantY() * 9.81;
  //     accelZ[i] = imu.getAccelInstantZ() * 9.81;
  //   }

  //   //X-axis -> average all values and store in distanceX 
  //   sum = 0;
  //   for(int j=0; j<10; j++){
  //     sum += accelX[j];
  //   }
  //   sum /= 10;
  //   distanceX = sum * dt;

  //   //Y-axis -> average all values and store in distanceY
  //   sum = 0;
  //   for(int j=0; j<10; j++){
  //     sum += accelY[j];
  //   }
  //   sum /= 10;
  //   distanceY = sum * dt;

  //   //Z-axis -> average all values and store in disanceZ
  //   sum = 0;
  //   for(int j=0; j<10; j++){
  //     sum += accelZ[j];
  //   }
  //   sum /= 10;
  //   distanceZ = sum * dt;

  //   //set places in the array that will be returned equal to the values gotten above
  //   pos[0] = distanceX;
  //   pos[1] = distanceY;
  //   pos[2] = distanceZ;  

  //   //return positions
  //   return pos;
  // }

}