// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.subsystems.sensors;

// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.Constants;
// import edu.wpi.first.wpilibj.Ultrasonic;
// import edu.wpi.first.wpilibj.AnalogInput;
// import edu.wpi.first.wpilibj.MedianFilter;

// public class UltrasonicArray extends SubsystemBase {
//   //private Ultrasonic sonic1 = new Ultrasonic(1,2);
//   private AnalogInput sonic1 = new AnalogInput(Constants.Ultrasonic.port);
//   private MedianFilter filter10 = new MedianFilter(10);
//   private double raw_median_distance = filter10.calculate(sonic1.getVoltage());
//   private double stableCM = raw_median_distance/0.0048828125;
//   private double stableIN = raw_median_distance/0.01240234;

//   /** Creates a new UltrasonicArray. */
//   public UltrasonicArray() {}

//   @Override
//   public void periodic() {
//     // This method will be called once per scheduler run
//   }
// }