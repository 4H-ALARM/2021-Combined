// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

import frc.robot.subsystems.ShooterSub;


public class HoldandShoot extends ParallelCommandGroup {
   /** Creates a new Aim. */
   @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
   private final ShooterSub m_subsystem;

  /** Creates a new HoldandShoot. */
  public HoldandShoot(ShooterSub subsystem) {
    m_subsystem = subsystem;

      // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_subsystem);

    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new AimHold(m_subsystem), 
                new Shoot(m_subsystem));
  }
}
