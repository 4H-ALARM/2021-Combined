// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveSub extends SubsystemBase {
  /** Creates a new DriveSub. */
  //private final WPI_VictorSPX leftFrontDrive = new WPI_VictorSPX(k_leftFrontDriveTalon);
  private final WPI_TalonSRX leftFrontDrive = new WPI_TalonSRX(k_leftFrontDriveTalon);
  //private final WPI_VictorSPX leftRearDrive = new WPI_VictorSPX(k_leftRearDriveTalon);
  private final WPI_TalonSRX leftRearDrive = new WPI_TalonSRX(k_leftRearDriveTalon);
  private final SpeedControllerGroup m_left = new SpeedControllerGroup(leftFrontDrive, leftRearDrive);
  //private final WPI_VictorSPX rightFrontDrive = new WPI_VictorSPX(k_rightFrontDriveTalon);
  private final WPI_TalonSRX rightFrontDrive = new WPI_TalonSRX(k_rightFrontDriveTalon);
  //private final WPI_VictorSPX rightRearDrive = new WPI_VictorSPX(k_rightRearDriveTalon);
  private final WPI_TalonSRX rightRearDrive = new WPI_TalonSRX(k_rightRearDriveTalon);
  private final SpeedControllerGroup m_right = new SpeedControllerGroup(rightFrontDrive, rightRearDrive);
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_left, m_right); 

  // new varibales for gyro
  private final ADXRS450_Gyro m_gyro = new ADXRS450_Gyro();

  private double m_gyroReading = 0.0;


  // robot drive
  public DifferentialDrive robotDrive() {
    return m_robotDrive;
  }


  // method def w/ gyro
  public DriveSub() {
      m_gyro.calibrate();
  }

  // return gyro value
  public double getAngle() {
    return m_gyroReading;
  }

  // set to striaght
  public void setStraight() {
    m_gyro.reset();
  }

  // drive with two speeds
  public void drive(final double lSpeed, final double rSpeed) {
    m_robotDrive.tankDrive(lSpeed, rSpeed, true);
   }

   //drive with arcade drive 
   public void arcade_drive(double y_pos, double x_pos){
      m_robotDrive.arcadeDrive(y_pos, x_pos);
   }

   

  @Override
  public void periodic() {

    m_gyroReading = m_gyro.getAngle();

    updateDash();

   /* // This method will be called once per scheduler run
    double gyroAng = m_gyro.getAngle();

    // get the recet rate
    double gyroRate = m_gyro.getRate();

    DecimalFormat df = new DecimalFormat("#.###");
    df.setRoundingMode(RoundingMode.CEILING);

    //System.out.println(df.format(gyroAng));

    System.out.println(df.format(gyroRate));
    */
  
  }

  private void updateDash(){
    SmartDashboard.putNumber("gyro Measurement", m_gyro.getAngle());
    //SmartDashboard.putNumber("left speed", m_robotDrive.get());
  }

}
