// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;

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
    imu.setYawAxis(Constants.imu_yaw);
    imu.configCalTime(Constants.imu_caltime);
    // Dynamics.initAngle = imu.getAngle();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    updatevars_AVG();
  }

  // //Since proven inaccurate
  // public void updatevars(){
  //   accelerationX = imuAccX_si();
  //   accelerationY = imuAccY_si();
  //   velocityX += (accelerationX*Dynamics.periodtime);
  //   velocityY += (accelerationY*Dynamics.periodtime);
  //   distanceX += (velocityX*Dynamics.periodtime);
  //   distanceY += (velocityY*Dynamics.periodtime);
  // }

  //look into filter for this
  public void updatevars_AVG(){
    double accX = imuAccX_si();
    double accY = imuAccY_si();
    distanceX += (velocityX + (((accelerationX + accX)/2) * Dynamics.periodtime))*Dynamics.periodtime;
    distanceY += (velocityY + (((accelerationY + accY)/2) * Dynamics.periodtime))*Dynamics.periodtime;
    accelerationX = accX;
    accelerationY = accY;
    velocityX += (accelerationX*Dynamics.periodtime);
    velocityY += (accelerationY*Dynamics.periodtime);
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
  public double imuAccX(){
    return imu.getAccelInstantX();
  }

  /**
   * @return returns in G-FORCES, so make sure to convert to something useful
   */
  public double imuAccY(){
    return imu.getAccelInstantY();
  }

  /**
   * @return returns in G-FORCES, so make sure to convert to something useful
   */
  public double imuAccZ(){
    return imu.getAccelInstantZ();
  }

  public double imuAccX_si(){
    return imu.getAccelInstantX() * 9.81;
  }

  public double imuAccY_si(){
    return imu.getAccelInstantY() * 9.81;
  }

  public double imuAccZ_si(){
    return imu.getAccelInstantZ() * 9.81;
  }

  /**
   * @return returns in G-FORCES, so make sure to convert to something useful
   */
  public double rioAccX(){
    return rio.getX();
  }

  /**
   * @return returns in G-FORCES, so make sure to convert to something useful
   */
  public double rioAccY(){
    return rio.getY();
  }

  /**
   * @return returns in G-FORCES, so make sure to convert to something useful
   */
  public double rioAccZ(){
    return rio.getZ();
  }

  public double rioAccX_si(){
    return rio.getX() * 9.81;
  }

  public double rioAccY_si(){
    return rio.getY() * 9.81;
  }

  public double rioAccZ_si(){
    return rio.getZ() * 9.81;
  }

  public double avgAccX(){
    return (rio.getX() + imu.getAccelInstantX()) /2;
  }

  public double avgAccY(){
    return (rio.getY() + imu.getAccelInstantY()) /2;
  }

  public double avgAccZ(){
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
}