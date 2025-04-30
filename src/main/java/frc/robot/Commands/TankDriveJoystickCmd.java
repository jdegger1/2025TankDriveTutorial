// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.DriveTrainConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.Subsystems.DriveTrainSubsystem;

public class TankDriveJoystickCmd extends Command {
  /** Creates a new TankDriveJoystickCmd. */

  // we create our drive train object
  private final DriveTrainSubsystem tankDrive;

  /// to drive the drivetrain, we need to supply a speed value and twist value
  /// we will create a supplier for the speed and twist
  /// It will use two axis on the joystick in robot container
  private final Supplier<Double> speedFunction;
  private final Supplier<Double> twistFunction;

  /// This is optional but it will help when we do swerve drive
  /// We will add a button that cuts down the speed of the robot when we want more precise driving
  /// We do this by adding a boolean supplier that will take in the state of a button we pass through
  private final Supplier<Boolean> fineDrivingFunction;

  // we can spreed the constructor across multiple lines to make our code easier to read
  public TankDriveJoystickCmd(
    DriveTrainSubsystem tDrive, 
    Supplier<Double> speedFx, 
    Supplier<Double> twistFx, 
    Supplier<Boolean> fineDrivingFx) {

      tankDrive = tDrive;
      speedFunction = speedFx;
      twistFunction = twistFx;
      fineDrivingFunction = fineDrivingFx;
      
      // Use addRequirements() here to declare subsystem dependencies.
      addRequirements(tankDrive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    //we get the current value of our suppliers
    double speed = speedFunction.get();
    double twist = twistFunction.get();

    // Here we create the deadbands for the speeds
    if ( Math.abs(speed) < OIConstants.kDeadband){
      speed = 0;
    }

    // If you want, this syntax simplifies the above three lines of code into one conditional equation
    twist = Math.abs(twist) > OIConstants.kDeadband ? twist : 0;
    /// Essentially if we are bigger than the deadband, we set twist to twist, else, we set twist to 0
    /// You can do the normal way if you want, but you may see this out in the wild

    /// Here we check to see if our boolean supplier is true (our button is pressed)
    /// If true, we divide our speed and twist down by a set factor
    if (fineDrivingFunction.get()){
      //Both divide the value by the constant
      speed = speed / DriveTrainConstants.kFineDrivingSpeed;
      // we are setting speed to speed divided by the constant

      twist /= DriveTrainConstants.kFineTurningSpeed;
      // we are dividing twist by the constant 
    }
    tankDrive.arcadeDrive(speed, twist);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //we want the drive to stop when it ends
    tankDrive.arcadeDrive(0, 0);
    // although because this will be our default command we dont really end
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
