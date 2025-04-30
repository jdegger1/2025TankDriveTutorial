// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Commands.Autos;
import frc.robot.Commands.TankDriveJoystickCmd;
import frc.robot.Constants.OIConstants;
import frc.robot.Subsystems.DriveTrainSubsystem;

public class RobotContainer {

  private final DriveTrainSubsystem driveTrainSubsystem;

  private final Joystick driveStick;

  /// We don't want to have to redeploy the robot code every time we want to change an autonomous
  /// So we create a selector for our autonomouses(? autonomi?, autonomeese?) autos
  /// We access this selector in our smart dashboard
  /// We create a sendable chooser object which we will put a chooser on the smart dashboard that we can send data back to the robot.
  private final SendableChooser<Command> autoChooser;

  // We create a command object for each of our autos
  private final Command driveAuto;
  private final Command driveAndTurnAuto;

  public RobotContainer() {
    driveTrainSubsystem = new DriveTrainSubsystem();

    driveStick = new Joystick(OIConstants.kDriveStickPort);

    // we create the default command as the joystick command, creating three suppliers for the axis and fine driving button
    driveTrainSubsystem.setDefaultCommand(new TankDriveJoystickCmd(
      driveTrainSubsystem,
      () -> driveStick.getRawAxis(OIConstants.kDriveAxis),
      () -> driveStick.getRawAxis(OIConstants.kTurnAxis),
      () -> driveStick.getRawButton(OIConstants.kFineDrivingButton)
    ));

    // we create our sendable chooser auto chooser and assign the two commands we will put in the chooser
    autoChooser = new SendableChooser<>();
    driveAuto = Autos.driveAuto(driveTrainSubsystem);
    driveAndTurnAuto = Autos.driveAndTurnAuto(driveTrainSubsystem);

    // we set our drive auto as our default for our chooser and add the other autos as options
    autoChooser.setDefaultOption("Drive Auto", driveAuto);
    autoChooser.addOption("Drive and Turn Auto", driveAndTurnAuto);

    configureBindings();
  }

  // We don't have any additional commands to run so we won't need to configure any buttons
  private void configureBindings() {}

  public Command getAutonomousCommand() {
    // we return whatever command is selected in our chooser at the beginning of autonomous.
    return autoChooser.getSelected();
  }
}
