// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
//import java.util.function.DoubleSupplier;

import frc.robot.subsystems.ShooterSub;

public class TuneSpeed extends CommandBase {
  /** Creates a new Feed. */
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ShooterSub m_subsystem;
  boolean upOrDown;

  public TuneSpeed(ShooterSub subsystems, boolean upOrDown) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.upOrDown = upOrDown; 
    m_subsystem = subsystems;
    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_subsystem.modifySpeed(upOrDown);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
   return true;
  }
}
