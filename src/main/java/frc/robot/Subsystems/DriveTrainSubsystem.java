// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/// 1212 primarily uses a Swerve Drive Train; however, they are incredibly complex
/// While learning, it is best to start with the simple tank drivetrain
/// If you've done VEX, you've likely worked with tank drive before
/// Tank drive has one motor on the left and right of the robot, each controlling a front and back wheel
/// We have a mecanum drive train chassey, which has one motor for each wheel
/// However this is easy to program as a tank drive
/// Later, we will make the two back motors "follow" the front motors, making it practically a tank drive
/// This allows us to benefit from the increased power of two motors instead of one
public class DriveTrainSubsystem extends SubsystemBase {
  /** Creates a new DriveTrainSubsystem. */

  //As always we start by naming our instance variables for the subsystem.
  private final SparkMax flMotor;
  private final SparkMax fRMotor;
  private final SparkMax bLMotor;
  private final SparkMax bRMotor;

  // Note how we only make two encoders as two back motors will only be following the front two
  private final RelativeEncoder leftEncoder;
  private final RelativeEncoder rightEncoder;
  
  public DriveTrainSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
