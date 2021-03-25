// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// test comment

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.*;

import com.ctre.phoenix.motorcontrol.ControlMode;

import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.Encoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;


public class ShooterSub extends SubsystemBase {
  /** Creates a new ShooterSub. */
  private final WPI_TalonFX upperMotor = new WPI_TalonFX(k_upperMotor);
  private final WPI_TalonFX lowerMotor = new WPI_TalonFX(k_lowerMotor);
  //private final WPI_TalonSRX upperMotor = new WPI_TalonSRX(k_upperMotor);
  //private final WPI_TalonSRX lowerMotor = new WPI_TalonSRX(k_lowerMotor);
  private final WPI_TalonSRX positionMotor = new WPI_TalonSRX(k_positionMotor);
  private final WPI_VictorSPX feedMotor = new WPI_VictorSPX(k_feedMotor);
  private final Encoder m_encoder = new Encoder(k_encoderDIO_1, k_encoderDIO_2);
  private final WPI_VictorSPX intakeMotor = new WPI_VictorSPX(k_intakeMotor);
 
  
  final int UnitsPerRevolution = k_UnitsPerRevolution; /* this is constant for Talon FX */

	/**
	 * Decide if positive motor-output/sensor-velocity should be when motor spins
	 * clockwise or counter-clockwise.
	 */
  
	final TalonFXInvertType kInvertType = TalonFXInvertType.CounterClockwise; // <<< What direction you want "forward/up" to be.

	/** electic brake during neutral */
  final NeutralMode kBrakeDurNeutral = NeutralMode.Brake;


  public ShooterSub() {
    m_encoder.setDistancePerPulse(20);
    m_encoder.setMinRate(50);
    m_encoder.reset();
    // configurePositionMotor();
    

    // initiaize the talon fx
    /* newer config API */
		//	TalonFXConfiguration configs = new TalonFXConfiguration();
			/* select integ-sensor for PID0 (it doesn't matter if PID is actually used) */
			//configs.primaryPID.selectedFeedbackSensor = FeedbackDevice.IntegratedSensor;
			/* config all the settings */
    //  upperMotor.configAllSettings(configs);
     // lowerMotor.configAllSettings(configs);
  }

  @Override
  public void periodic() {
    System.out.println(positionMotor.getSelectedSensorPosition());

    // This method will be called once per scheduler run
  }

  // get the encoder 
  public int encoderValue() {
    return m_encoder.getRaw();
  }

  // turns on the upper and lower motor of shooter
  public void shooterMotorOn() {
    upperMotor.set(ControlMode.PercentOutput, -1.0);
    lowerMotor.set(ControlMode.PercentOutput,-1.0);
    //upperMotor.set(1.0);
    //lowerMotor.set(-1.0);
  }

  // turns off the upper and lower motor of the shooter
  public void shooterMotorOff() {
    upperMotor.set(ControlMode.PercentOutput, 0.0);
    lowerMotor.set(ControlMode.PercentOutput, 0.0);
    //upperMotor.stopMotor();
    //lowerMotor.stopMotor();
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
    //upperMotor.stopMotor();
    //lowerMotor.stopMotor();
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
