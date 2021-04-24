// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;

import frc.robot.subsystems.DriveSub;

public class JoystickDrive extends CommandBase {
  /** Creates a new JoystickDrive. */
  private final DriveSub driveSub;

  private final DoubleSupplier m_yPosition;
  private final DoubleSupplier m_xPosition;

  public JoystickDrive(DriveSub driveSub, final DoubleSupplier yPosition, final DoubleSupplier xPosition) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.driveSub = driveSub;
    this.m_yPosition = yPosition;
    this.m_xPosition = xPosition;

    addRequirements(driveSub);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveSub.arcade_drive(-1 * m_yPosition.getAsDouble() , -1 * m_xPosition.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
