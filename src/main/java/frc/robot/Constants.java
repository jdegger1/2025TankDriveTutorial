package frc.robot;

public class Constants {
    public static class DriveTrainConstants {

        // remember to set the ids 
        public static final int kFLMotorID = 0;
        public static final int kFRMotorID = 1;
        public static final int kBLMotorID = 2;
        public static final int kBRMotorID = 3;
     
        //Increase these values to make the robot drive slower in fine driving
        public static final double kFineDrivingSpeed = 2;
        public static final double kFineTurningSpeed = 2;
    }

    public static class OIConstants {

        // Joystick port
        public static final int kDriveStickPort = 0;

        /// Joystick axis
        /// Left Joystick Up/Down = 0
        /// Left Joystick Right/Left = 1
        /// Right Joystick Up/Down = 4
        /// Right Joystick Right/Left = 5
        public static final int kDriveAxis = 0;
        public static final int kTurnAxis = 5;

        public static final int kFineDrivingButton = 1;

        // remember to adjust the deadband
        public static final double kDeadband = 0.05;
        
    }

    public static class AutoConstants {
        //////// Drive Auto Constants ////////////

        //remember that speeds are in percentages of max speed
        public static final double kDDriveForwardSpeed = 0.1;

        //time is in seconds
        public static final double kDDriveForwardTime = 1;

        /////////////////////////////////////////////
        
        //////// Drive And Turn Auto Constants ////////////
        
        // you may want different speeds for different autonomous
        public static final double kDATDriveForwardSpeed = 0.1;
        public static final double kDATDriveForwardTime = 1;
        //rember that positive turning is clockwise (I believe (I may be wrong)).
        public static final double kDATDriveTurnSpeed = 0.1;
        public static final double kDATDriveTurnTime = 0.1;
    }
}
