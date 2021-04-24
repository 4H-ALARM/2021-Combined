// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.DriveStraight;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.ShooterSub;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSub;
import frc.robot.commands.AutoDriveDS;
import frc.robot.commands.Drive;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.commands.Feed;
import frc.robot.commands.FeedStop;
import frc.robot.commands.HoldandShoot;
import frc.robot.commands.Intake;
import frc.robot.commands.IntakeOff;
import frc.robot.commands.Shoot;
import frc.robot.commands.ShooterAllStop;
import frc.robot.commands.StopShoot;
import frc.robot.commands.Aim;
import frc.robot.commands.AimToPos;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.StopAim;
import static frc.robot.Constants.*;
import frc.robot.commands.JoystickDrive;

// added ths comment as a test of source control

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  private final DriveSub m_drivesub = new DriveSub();

  private final ShooterSub m_shootersub = new ShooterSub();

  private final DriveStraight m_ds = new DriveStraight(m_drivesub);

  private final AutoDriveDS m_aDS = new AutoDriveDS(m_drivesub);

  //private final XboxController xboxController = new XboxController(k_xboxController);
  private final Joystick joyStick = new Joystick(k_joystick);

  private final JoystickDrive driveCommand;



  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    driveCommand = new JoystickDrive(m_drivesub, () -> joyStick.getY(), () -> joyStick.getX());
    

    //driveCommand = new Drive(m_drivesub, () -> xboxController.getY(Hand.kLeft), () -> xboxController.getY(Hand.kRight));
    m_drivesub.setDefaultCommand(driveCommand);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // 5 is the LB left back
    // for the shooter
    /*
    new JoystickButton(xboxController, k_LBbutton)
    .whenPressed(new HoldandShoot(m_shootersub));
    new JoystickButton(xboxController, k_LBbutton)
    .whenReleased(new ShooterAllStop(m_shootersub));
    */
    /*new JoystickButton(xboxController, k_LBbutton)
    .whenPressed(new Shoot(m_shootersub));
    new JoystickButton(xboxController, k_LBbutton)
    .whenReleased(new StopShoot(m_shootersub));
  
    // 6 is the RB right back
    // for the feeder
    new JoystickButton(xboxController, k_RBbutton)
    .whenPressed(new Feed(m_shootersub));
    new JoystickButton(xboxController, k_RBbutton)
    .whenReleased(new FeedStop(m_shootersub));
    // 2 is the B button
    // for the intake motor
    new JoystickButton(xboxController, k_Bbutton)
    .whenPressed(new Intake(m_shootersub));
    new JoystickButton(xboxController, k_Bbutton)
    .whenReleased(new IntakeOff(m_shootersub));
    // 4 is for the Y
    // aims it up when true and down if false
    //new JoystickButton(xboxController, k_Abutton)
    //.whenPressed(new Aim(m_shootersub, true));
    //new JoystickButton(xboxController, k_Ybutton) 
    //.whenReleased(new StopAim(m_shootersub)); 
    // 1 is for A
    // aims it down
    new JoystickButton(xboxController, k_Ybutton)
    .whenPressed(new AimToPos(m_shootersub, 1));
    new JoystickButton(xboxController, k_Abutton)
    .whenPressed(new AimToPos(m_shootersub, 0));

    //new JoystickButton(xboxController, k_Xbutton)
   // .whenPressed(new AimToPos(m_shootersub, false));
   // new JoystickButton(xboxController, k_Xbutton)
   // .whenReleased(new StopAim(m_shootersub));

  */
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public DriveStraight getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_ds;
  }

  //for auto ds
  public AutoDriveDS getAutoCommandDS() {
    // An ExampleCommand will run in autonomous
    return m_aDS;
  }
}
