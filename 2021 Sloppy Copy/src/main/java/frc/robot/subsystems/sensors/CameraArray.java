// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.subsystems.sensors;

// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.Constants;
// import edu.wpi.cscore.UsbCamera;
// import edu.wpi.first.cameraserver.CameraServer;

// public class CameraArray extends SubsystemBase {
//   private UsbCamera cam1 = new UsbCamera("Camera 1", Constants.Cameras.cam1_port);
//   private UsbCamera cam2 = new UsbCamera("Camera 2", Constants.Cameras.cam2_port);
//   private UsbCamera cam3 = new UsbCamera("Camera 3", Constants.Cameras.cam3_port);


//   /** Creates a new CameraArray. */
//   public CameraArray() {}

//   @Override
//   public void periodic() {
//     // This method will be called once per scheduler run
//   }

//   public void start_streams(boolean cam_1, boolean cam_2, boolean cam_3){
//     if(cam_1){
//       CameraServer.getInstance().startAutomaticCapture(cam1);
//     }if(cam_2){
//       CameraServer.getInstance().startAutomaticCapture(cam2);
//     }if(cam_3){
//       CameraServer.getInstance().startAutomaticCapture(cam3);
//     }
//   }
// }