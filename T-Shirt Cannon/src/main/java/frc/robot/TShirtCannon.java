package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class TShirtCannon extends TimedRobot {

    private static final boolean
        DRIVETRAIN_INVERT_RIGHT = true,
        DRIVETRAIN_USE_EXP2_SMOOTHING = false;
    private static final int
        DRIVEPORT_LEFT = 0,
        DRIVEPORT_RIGHT = 1;
    private static final double
        SOLENOID_PULSE_DURATION = 1.0,      // in seconds
        DRIVETRAIN_OUTPUT_CONSTANT = -1.0,  // make negative if wanting to invert both sides
        DRIVETRAIN_INPUT_DEADBAND = 0.02;


    private XboxController
        input = new XboxController(0);

    private DifferentialDrive
        drive_train = new DifferentialDrive(
            new PWMVictorSPX(DRIVEPORT_LEFT),
            new PWMVictorSPX(DRIVEPORT_RIGHT)
        )
    ;

    private Compressor
        compressor = new Compressor();
    private Solenoid[]
        solenoids = new Solenoid[4];


    public TShirtCannon() {
        this.drive_train.setMaxOutput(DRIVETRAIN_OUTPUT_CONSTANT);
        this.drive_train.setRightSideInverted(DRIVETRAIN_INVERT_RIGHT);
        this.drive_train.setDeadband(DRIVETRAIN_INPUT_DEADBAND);
        for(int i = 0; i < 4; i++) {
            this.solenoids[i] = new Solenoid(i);
            this.solenoids[i].setPulseDuration(SOLENOID_PULSE_DURATION);
        }
    }

    @Override public void robotInit() {}
    @Override public void robotPeriodic() {}

    @Override public void teleopInit() {
        this.compressor.setClosedLoopControl(true);
    }
    @Override public void teleopPeriodic() {
        this.drive_train.tankDrive(
            this.input.getRawAxis(XboxController.Axis.kLeftY.value),
            this.input.getRawAxis(XboxController.Axis.kRightY.value),
            DRIVETRAIN_USE_EXP2_SMOOTHING
        );
        for(int i = 0; i < 4; i++) {
            if(this.input.getRawButtonPressed(i + 1)) {
                this.solenoids[i].startPulse();
                System.out.println("Triggered Solenoid " + (i + 1));
            }
        }
    }

    @Override public void autonomousInit() {
        this.compressor.setClosedLoopControl(true);
    }
    @Override public void disabledInit() {
        this.compressor.setClosedLoopControl(false);
        this.drive_train.stopMotor();
    }


    public static class Test extends TimedRobot {

        private Solenoid[] solenoids = new Solenoid[8];
        private int counter = 0;
        private static final int
            COUNTS = 10,
            DELAY = 100;


        public Test() {
            for(int i = 0; i < 8; i++) {
                this.solenoids[i] = new Solenoid(i);
                this.solenoids[i].setPulseDuration(1);
            }
        }

        @Override public void robotInit() {}
        @Override public void robotPeriodic() {}
    
        @Override public void teleopInit() {
            this.counter = 0;
        }
        @Override public void teleopPeriodic() {
            if(this.counter % DELAY == 0 /*&& this.counter < COUNTS * DELAY*/) {
                for(int i = 0; i < 8; i++) {
                    this.solenoids[i].startPulse();
                }
                System.out.println("Set Outputs");
            }
            counter++;
        }


    }


}