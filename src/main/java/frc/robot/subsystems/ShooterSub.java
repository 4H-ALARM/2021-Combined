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
  /*static boolean wasUp = true; // -1 = not moved and not used; 0 = isX; 1 = isY
  static double position = 0; // position of lifter 
  static double targetPosition;
  static boolean firstCall = true;
  */ // for aiming to pos
  static int count = 0;
  boolean upFlag = false;
  boolean downFlag = false;
  boolean intakeMotorToggle = false;
  double targetPosition = k_targetPositions[0];
  int currentPos = 0;
  double customSpeed = -1000;



  public ShooterSub() {
    configurePositionMotor();
    //targetPosition = positionMotor.getSelectedSensorPosition();
    System.out.println(positionMotor.getSelectedSensorPosition());
  }

  @Override
  public void periodic() {
    if (count <= 5){
      positionMotor.set(ControlMode.PercentOutput, 0);
      count += 1;
    }
    else{
      double position = positionMotor.getSelectedSensorPosition();

      if(upFlag){
        if(position >= k_maxLimit){
          positionMotor.set(ControlMode.PercentOutput, -1);
          targetPosition -= 1;
        }
      }
      else if(downFlag){
        if(position <= k_minLimit){
          positionMotor.set(ControlMode.PercentOutput, 1);
          targetPosition += 1;
        }
      }

      else {
        if (position <= targetPosition - 2){
        positionMotor.set(ControlMode.PercentOutput, 1);
        }
        else if (position >= targetPosition + 2){
        positionMotor.set(ControlMode.PercentOutput, -1);
        }
      }



      System.out.println("Speed: "+ customSpeed + " position: " + position);//targetPosition
      System.out.println("");


      upFlag = false;
      downFlag = false;
      count = 0;
    }

    // This method will be called once per scheduler run
    // System.out.println(positionMotor.getSelectedSensorPosition());
  }

  // turns on the upper and lower motor of shooter
  public void shooterMotorOn( ) {
    upperMotor.set(ControlMode.Velocity, customSpeed);
    lowerMotor.set(ControlMode.Velocity, customSpeed);
  }

  // turns off the upper and lower motor of the shooter
  public void shooterMotorOff() {
    upperMotor.set(ControlMode.PercentOutput, 0.0);
    lowerMotor.set(ControlMode.PercentOutput, 0.0);
  }

  // turns on feeder motor
  public void feederMotorOn() {
    feedMotor.set(1);
  }

  public void IntakeToggle() {
    if(intakeMotorToggle == true){
      intakeMotorToggle = false;
      intakeMotor.set(-1);
    }
    else{
      intakeMotorToggle = true;
      intakeMotor.stopMotor();
    }
  }

  public void modifySpeed(boolean upOrDown) {
    if(upOrDown){
      customSpeed += k_shooterSpeedStep;
    }
    else{
      customSpeed -= k_shooterSpeedStep;
    }
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

  // Change motor speed
  public void Shuffle(){
    if(currentPos == 2){
      currentPos = 0;
    }
    else{
      currentPos += 1;
    }
    targetPosition = k_targetPositions[currentPos];
    customSpeed = k_ShooterSpeed[currentPos];

  }

  public void SetFlags(Boolean whatflag){
    if(whatflag){
      upFlag = true;
      System.out.println("IM ON **************");
    }
    else{
      downFlag = true;
    }
  }
  
  // turns on position motor at specific position 
  /*public void positionMotorOnAim(Boolean isUp) { // Not used
    if(firstCall){
      targetPosition = positionMotor.getSelectedSensorPosition();
      firstCall = false;
    }

    if ((isUp && targetPosition > -1060) || (!isUp && targetPosition < -1020)){ // Safe position
      
      if(isUp == wasUp){ // do not need to wind
        if(isUp){
          targetPosition -= 0.01; // wind up
        }
        else{
          targetPosition += 0.01; // wind down 
        }
        positionMotor.set(ControlMode.Position,targetPosition);
      }


      else{ // Does need to wind
        position = positionMotor.getSelectedSensorPosition();
        if(isUp){
          positionMotor.set(ControlMode.Position, (position - K_WindFactor)); // wind up   
        }
        else{
          //positionMotor.set(ControlMode.Position, (position + K_WindFactor)); // wind down
        }
      }
    }
    wasUp = isUp;
    System.out.print(targetPosition);
    System.out.print(isUp);
    System.out.println(positionMotor.getSelectedSensorPosition());
  }
  */
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
    
    //positionMotor.setInverted(true);
    positionMotor.configPeakOutputForward(k_AimPeakForOutput);
    positionMotor.configPeakOutputReverse(k_AimPeakRevOutput);
    positionMotor.setSensorPhase(true);
    positionMotor.config_kP(k_AimSlotIdx, k_AimP);
    positionMotor.config_kI(k_AimSlotIdx, k_AimI);
    positionMotor.config_kD(k_AimSlotIdx, k_AimD);
    positionMotor.configAllowableClosedloopError(k_AimSlotIdx, k_allowableClosedLoopError);
    positionMotor.setNeutralMode(NeutralMode.Brake);
    positionMotor.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.Analog,k_AimSlotIdx,0);
  }
}
/*

    if(wasY == isY){ // Does not need to wind
      if (isY == 1){ // wind up
        if(targetPosition < -1000){
          targetPosition -= 0.0001;
        }
        if(targetPosition > -1035){
          targetPosition += 0.0001;
        }
      } 
      else{
        targetPosition += 0.0001;
      }
      positionMotor.set(ControlMode.Position,targetPosition);
    }
    else{ 
      position = positionMotor.getSelectedSensorPosition();
      if (isY == 1){ // wind up
        positionMotor.set(ControlMode.Position, (position - K_WindFactor));
      } 
      else{ // wind down
        positionMotor.set(ControlMode.Position, (position + K_WindFactor));

      }
    }
    */