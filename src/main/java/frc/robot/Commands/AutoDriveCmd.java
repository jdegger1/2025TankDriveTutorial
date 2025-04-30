// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.DriveTrainSubsystem;

/// We are creating a command that just tells the robot to drive at a certain speed
/// This allows us to create an autonomous later
public class AutoDriveCmd extends Command {
  /** Creates a new AutoDriveCmd. */

  private final DriveTrainSubsystem tankDrive;

  /// Unlike the joystick command, we don't need suppliers as we pass in the speed and twist at the beginning of the command
  private double speed;
  private double twist;

  public AutoDriveCmd(DriveTrainSubsystem tDrive, double speed, double twist) {

    tankDrive = tDrive;

    this.speed = speed;
    this.twist = twist;

    addRequirements(tankDrive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    tankDrive.arcadeDrive(speed, twist);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // we need the end method as we don't want our robot to keep driving during autonomous when we want it to stop
    tankDrive.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
