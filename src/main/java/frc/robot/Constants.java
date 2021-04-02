// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // drive talons
    public final static int k_leftFrontDriveTalon = 8;
    public final static int k_leftRearDriveTalon = 11;
    public final static int k_rightFrontDriveTalon = 6;
    public final static int k_rightRearDriveTalon = 2;
    //shooter talons
    public final static int k_upperMotor = 3;
    public final static int k_lowerMotor = 4;
    public final static int k_positionMotor = 5;
    public final static int k_feedMotor = 7;
    public final static int k_intakeMotor = 10;
    // encoder
    public final static int k_encoderDIO_1 = 0;
    public final static int k_encoderDIO_2 = 1;
    // xbox controller usb port
    public final static int k_xboxController = 0;
    // joystick control buttons
    public final static int k_LBbutton = 5;
    public final static int k_RBbutton = 6;
    public final static int k_Ybutton = 4;
    public final static int k_Abutton = 1;
    public final static int k_Xbutton = 3;
    public final static int k_Bbutton = 2;
    public final static int k_LJDbutton = 9;
    public final static int k_RJDbutton = 10;
    // feed count 
    public final static int k_feedCount = 100;
    // drive straight gyro reading constants
    public final static double k_kP = 0.005;
    public final static double k_AngleSetPoint = 0.0;
    // Talon FX control mode velocity
    public final static int k_UnitsPerRevolution = 2048;
    // speed settings
    public final static double k_aimSpeedDown = -0.1;
    public final static double k_aimSpeedUp = 0.55;

    // aimer configuration 
    public final static int k_AimSlotIdx = 0;
    public final static double k_AimP = 100.0;
    public final static double k_AimI = 0.10;
    public final static double k_AimD = 0.0;
    public final static double K_WindFactor = 60; 
    public final static double K_MAX = -1000;
    public final static double K_MED = -1017.5;
    public final static double K_MIN = -1035;
    public final static double k_allowableClosedLoopError = 2;
    public final static double k_AimPeakForOutput = 0.2;
    public final static double k_AimPeakRevOutput = -0.6;

    public final static double k_targetPositions[] = {-1025,-1035,-1045};
    public final static double k_ShooterSpeed[] = {550,700,1000};

    public final static double k_maxLimit = -1060;
    public final static double k_minLimit = -1020;

    public final static double k_shooterSpeedStep = 10;

}
