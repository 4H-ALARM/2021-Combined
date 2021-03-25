// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.*;

import com.ctre.phoenix.motorcontrol.ControlMode;

import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;


public class ShooterSub extends SubsystemBase {
  /** Creates a new ShooterSub. */
  private final WPI_TalonFX upperMotor = new WPI_TalonFX(k_upperMotor);
  private final WPI_TalonFX lowerMotor = new WPI_TalonFX(k_lowerMotor);
  private final WPI_TalonSRX positionMotor = new WPI_TalonSRX(k_positionMotor);
  private final WPI_VictorSPX feedMotor = new WPI_VictorSPX(k_feedMotor);
  private final WPI_VictorSPX intakeMotor = new WPI_VictorSPX(k_intakeMotor);
 
  
  public ShooterSub() {
    // configurePositionMotor();
 
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    System.out.println(positionMotor.getSelectedSensorPosition());
  }

  // turns on the upper and lower motor of shooter
  public void shooterMotorOn() {
    upperMotor.set(ControlMode.PercentOutput, -1.0);
    lowerMotor.set(ControlMode.PercentOutput,-1.0);
  }

  // turns off the upper and lower motor of the shooter
  public void shooterMotorOff() {
    upperMotor.set(ControlMode.PercentOutput, 0.0);
    lowerMotor.set(ControlMode.PercentOutput, 0.0);
  }

  // turns on feeder motor
  public void feederMotorOn() {
    feedMotor.set(0.5);
  }

  // turns feeder motor off
  public void feederMotorOff() {
    feedMotor.stopMotor();
  }

  // trun on intake motor
  public void intakeMotorOn(){
    intakeMotor.set(-1);
  }

  //turn off intake motor
  public void intakeMotorOff(){
    intakeMotor.stopMotor();
  }

  // turns on position motor to move at a speed
  public void positionMotorOn(double speed) {
    positionMotor.set(ControlMode.PercentOutput, speed);
  }
  
  // turns on position motor at specific position
  public void positionMotorOnAim(double position) {
    positionMotor.set(ControlMode.Position, position);
  }

  // holds the position motor at current position
  public void positionMotorHold() {
    double position = positionMotor.getSelectedSensorPosition();
    positionMotor.set(ControlMode.Position, position);
  }
  // turns off position motor
  public void positionMotorOff() {
    positionMotor.stopMotor();
  }

  // turns all motors off
  public void allOff() {
    upperMotor.set(ControlMode.PercentOutput, 0.0);
    lowerMotor.set(ControlMode.PercentOutput, 0.0);
    feedMotor.stopMotor();
    positionMotor.stopMotor();
  }

  private void configurePositionMotor() {
    
    positionMotor.setInverted(true);
    positionMotor.config_kP(k_AimFeedBackId, k_AimP);
    positionMotor.config_kI(k_AimFeedBackId, k_AimI);
    positionMotor.config_kD(k_AimFeedBackId, k_AimD);
    positionMotor.setNeutralMode(NeutralMode.Brake);
    positionMotor.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.Analog,k_AimFeedBackId,0);
  }
}
