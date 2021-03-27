// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;

import frc.robot.subsystems.DriveSub;

public class Drive extends CommandBase {
  /** Creates a new Drive. */
  private final DriveSub driveSub;

  private final DoubleSupplier leftSpeed;
  private final DoubleSupplier rightSpeed;

  public Drive(DriveSub driveSub, final DoubleSupplier leftSpeed, final DoubleSupplier rightSpeed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.driveSub = driveSub;
    this.leftSpeed = leftSpeed;
    this.rightSpeed = rightSpeed;

    addRequirements(driveSub);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveSub.drive(leftSpeed.getAsDouble() , rightSpeed.getAsDouble() );
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
