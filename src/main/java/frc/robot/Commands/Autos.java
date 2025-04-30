// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Constants.AutoConstants;
import frc.robot.Subsystems.DriveTrainSubsystem;

//We create an empty class named Autos to store different autos
public final class Autos {

    /// We create a method that returns a command to be run during autonomous
    /// The first autonomous we will create is a drive forward autonomous
    public static final Command driveAuto( DriveTrainSubsystem driveSubsystem){
        // we tell it to drive forward a certain speed and end after a certain time if it hasn't already ended
        return new AutoDriveCmd(driveSubsystem, AutoConstants.kDDriveForwardSpeed, 0.0).withTimeout(AutoConstants.kDDriveForwardTime);
    }
    public static final Command driveAndTurnAuto( DriveTrainSubsystem driveSubsystem){
        /// we create a sequentce of commands which holds a series of commands as one command
        /// This sequence creates a command that drives us forward, turn, and then drive again
        /// (Imo the constants get a little much, but they make editing later easier)
        /// DAT stands for Drive and turn
        return Commands.sequence(
            new AutoDriveCmd(driveSubsystem, AutoConstants.kDATDriveForwardSpeed, 0).withTimeout(AutoConstants.kDATDriveForwardTime),
            new AutoDriveCmd(driveSubsystem, 0, AutoConstants.kDATDriveTurnSpeed).withTimeout(AutoConstants.kDATDriveTurnTime),
            new AutoDriveCmd(driveSubsystem, AutoConstants.kDATDriveForwardSpeed, 0).withTimeout(AutoConstants.kDATDriveForwardSpeed)
        );
    }
}
