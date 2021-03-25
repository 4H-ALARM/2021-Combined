// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveSub;


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoDriveDS extends SequentialCommandGroup {
  /** Creates a new AutoDriveDS. */
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  public AutoDriveDS(DriveSub subsystems) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());

    addCommands(
      new DriveStraight(subsystems).withTimeout(0.5),
      //new DriveStraight(subsystems).withTimeout(0.5)
      //new DriveBackwards(subsystems).withTimeout(0.5),
      //new TurnLeft(subsystems).withTimeout(0.5),
      new TurnAngle(subsystems, -100.0).withTimeout(2.0), 
      new DriveStraight(subsystems).withTimeout(1.0),
      new TurnAngle(subsystems, -80.0).withTimeout(2.0),
      new DriveStraight(subsystems).withTimeout(0.60),
      new TurnAngle(subsystems, -50.0).withTimeout(2.0)
      );


  }
}
