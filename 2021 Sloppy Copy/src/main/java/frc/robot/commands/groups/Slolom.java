// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivefunctions.AutoMove;
import frc.robot.subsystems.hardware.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Slolom extends SequentialCommandGroup {
  //private double half = 0.5, full = 1, quat = 0.25, three = 0.75;
  
  /** Creates a new Slolom. */
  public Slolom(DriveTrain db_main) {
    // Add your commands in the addCommands() call, e.g.
    // TO MAKE THE ROBOT MOVE, SIMPLY ADD A NEW "AutoMove(left speed, right speed)" AND SPECIFY THE WHEEL SPEEDS. THEN ADD ".withTimeout(seconds)" TO MAKE IT LAST FOR HOWEVER LONG
    addCommands(
      //move forward from start line
      new AutoMove(0.2, 0.2).withTimeout(1.5),

      //turn left, move through cones, turn right (two stages)
      new AutoMove(-0.2, 0.3).withTimeout(1),
      new AutoMove(0.3, 0.3).withTimeout(1.4),
      new AutoMove(0.3, -0.2).withTimeout(0.95),
      new AutoMove(0.2, 0.1).withTimeout(0.2),
      
      //move forward down the feild
      new AutoMove(0.4, 0.4).withTimeout(2.5),

      //turn right, then move forward through cones
      new AutoMove(0.3, -0.2).withTimeout(1.1),
      new AutoMove(0.2, 0.2).withTimeout(1.1),

      //do a 360 around the last cone, 
      new AutoMove(-0.1, 0.3).withTimeout(1.7),
      new AutoMove(0, 0.2).withTimeout(1.5),
      new AutoMove(-0.1, 0.3).withTimeout(1.7),
      new AutoMove(0, 0.2).withTimeout(1.5),
      new AutoMove(-0.1, 0.3).withTimeout(1.7),
      new AutoMove(0, 0.2).withTimeout(1.5),
      new AutoMove(-0.1, 0.3).withTimeout(1.7),

      //move forward to other side
      new AutoMove(0.2, 0.2).withTimeout(0.8),

      //turn right to angle downfeild
      new AutoMove(0.3, -0.2).withTimeout(1.5),
      new AutoMove(0.2, 0.1).withTimeout(0.5),

      //move forward down the feild
      new AutoMove(0.4, 0.4).withTimeout(2),

      // * * * GOT TO HERE IN TESTING * * * 

      //turn left, move through cones, turn right (two stages)
      new AutoMove(-0.2, 0.3).withTimeout(1),
      new AutoMove(0.3, 0.3).withTimeout(1.4),
      new AutoMove(0.3, -0.2).withTimeout(0.95),
      new AutoMove(0.2, 0.1).withTimeout(0.2),

      //move forward... INTO THE GOAL!
      new AutoMove(0.2, 0.2).withTimeout(1.5)
    );
  }
}
