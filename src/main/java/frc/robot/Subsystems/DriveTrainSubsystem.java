// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveTrainConstants;

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

  /// As always we start by naming our instance variables for the subsystem.
  /// f stands for front, b stands for back, l stands for left, r stands for right
  private final SparkMax fLMotor;
  private final SparkMax fRMotor;
  private final SparkMax bLMotor;
  private final SparkMax bRMotor;

  // Note how we only make two encoders as two back motors will only be following the front two
  private final RelativeEncoder leftEncoder;
  private final RelativeEncoder rightEncoder;
  
  /// Starting in 2025, First uses these config objects to configure motors,
  /// they can be used to assign a motor as a follower.
  /// It can also be used for setting brake or coast, and for inverting the direction of the motor
  /// Amongst other things
  private final SparkMaxConfig leftConfig;
  private final SparkMaxConfig rightConfig;

  /// The DifferentialDrive object is used to control the drivetrain motors simultaneously.
  /// This helps streamline the code and is easier to read.
  private final DifferentialDrive driveTrain;

  public DriveTrainSubsystem() {
    // we assign each motor to there motor controller
    fLMotor = new SparkMax(DriveTrainConstants.kFLMotorID, MotorType.kBrushless);
    fRMotor = new SparkMax(DriveTrainConstants.kFRMotorID, MotorType.kBrushless);
    bLMotor = new SparkMax(DriveTrainConstants.kBLMotorID, MotorType.kBrushless);
    bRMotor = new SparkMax(DriveTrainConstants.kBRMotorID, MotorType.kBrushless);

    // we assign our encoders to the corresponding motors
    leftEncoder = fLMotor.getEncoder();
    rightEncoder = fRMotor.getEncoder();

    // we create two config objects for our back motors
    leftConfig = new SparkMaxConfig();
    rightConfig = new SparkMaxConfig();

    //we tell the config that we want whatever motor we're configuring to follow the front left motor
    leftConfig
      .follow(fLMotor);
    
      // we assign the config object to the back left motor to configure the back left motor
    bLMotor.configure(leftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters); 
    /// it now follows the front left motor
    /// when you write the reset mode, it tends to import the wrong thing,
    /// make sure it imports resetmode from sparkbase 

    // we do the same with the back right motor and config
    rightConfig
      .follow(fRMotor);
    
    bRMotor.configure(rightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);  

    /// we create our differential drive object, 
    /// telling it that the front left motor controls the left of the robot
    /// and the front right motor controls the right of the robot
    driveTrain = new DifferentialDrive(fLMotor, fRMotor);
  }

  public void arcadeDrive(double speed, double twist){
    /// we create a method that allows us to drive our robot by feeding in a speed value and a turning (twist) value
    /// A tank drive can go forward and backward, and can rotate(turn) clockwise and counterclockwise.
    /// speed is its linear speed
    /// twist is its angular speed
    driveTrain.arcadeDrive(speed, twist);
    // our driveTrain object converts our speed and twist values into speeds to feed into our left and right motors
  }


  /// These methods are used to get and set the position of our two used encoders
  /// They are called accessors and mutators
  /// I call them getters and setters
  public double getLeftEncoder(){
    return leftEncoder.getPosition();
  }
  public double getRightEncoder(){
    return rightEncoder.getPosition();
  }

  public void setLeftEncoder(double encoderVal){
    leftEncoder.setPosition(encoderVal);
  }
  public void setRightEncoder(double encoderVal){
    rightEncoder.setPosition(encoderVal);
  }

  public void resetLeftEncoder(){
    setLeftEncoder(0);
  }

  public void resetRightEncoder(){
    setRightEncoder(0);
  }

  public void resetEncoders(){
    setLeftEncoder(0);
    setRightEncoder(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Left Encoder Value", getLeftEncoder());
    SmartDashboard.putNumber("Right Encoder Value", getRightEncoder());
  }
}
