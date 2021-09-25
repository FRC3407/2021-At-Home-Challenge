package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.Button;
//import edu.wpi.first.wpilibj.DriverStation;

import static edu.wpi.first.wpilibj.util.ErrorMessages.requireNonNullParam;

import frc.robot.commands.*;

public class Input {
    //this class maps integers above the normal button counts to any POV's on the device so that they can be used as normal buttons
    public class XButton extends Button{
        private final GenericHID device;
        private final int button;
        private final boolean use_pov;

        public XButton(GenericHID device, int button) {
            requireNonNullParam(device, "joystick", "JoystickButton");

            System.out.println("Buttons: " + device.getButtonCount());
            System.out.println("POV's: " + device.getPOVCount());

            this.device = device;
            this.use_pov = ((button > device.getButtonCount()) && (button <= device.getButtonCount() + (device.getPOVCount() * 4)));
            if (use_pov) {
                this.button = button - device.getButtonCount();
            } else {
                this.button = button;
            }
            System.out.println(use_pov);
        }

        @Override
        public boolean get() {
            if (use_pov) {
                return (this.device.getPOV(button / 4) / 90.0 + 1) == this.button;
            } else {
                return device.getRawButton(button);
            }
        }
    }

    // public interface RequiredControl {
    //     double getAxis(int axis);

    //     XButton hatchPistonIn();
    //     XButton hatchPistonOut();
        
    //     XButton hatchGrabOpen();
    //     XButton hatchGrabClose();
        
    //     XButton armsPistonToggle();

    //     XButton cargoIn();
    //     XButton cargoOut();

    //     XButton elevatorUp();
    //     XButton elevatorDown();

    //     XButton speedToggle();
    // }

    // private class Xbox implements RequiredControl { //TODO - the keybinds of this class are hard-coded, put them in constants
    //     private XboxController device;
    //     public Xbox(int port) {
    //         device = new XboxController(port);
    //     }

    //     @Override
    //     public double getAxis(int axis) {
    //         return this.device.getRawAxis(axis);
    //     }

    //     @Override 
    //     public XButton hatchPistonIn() {
    //         return new XButton(this.device, 13);    //bottom of POV
    //     }
    //     @Override
    //     public XButton hatchPistonOut() {
    //         return new XButton(this.device, 11);        //top of POV
    //     }
    //     @Override
    //     public XButton hatchGrabOpen() {
    //         return new XButton(this.device, 12);        //right of POV
    //     }
    //     @Override
    //     public XButton hatchGrabClose() {
    //         return new XButton(this.device, 14);        //left of POV
    //     }
    //     @Override
    //     public XButton armsPistonToggle() {
    //         return new XButton(this.device, 2);         //B
    //     }
    //     @Override
    //     public XButton cargoIn() {
    //         return new XButton(this.device, 3);         //X
    //     }
    //     @Override
    //     public XButton cargoOut() {
    //         return new XButton(this.device, 4);         //Y
    //     }
    //     @Override
    //     public XButton elevatorUp() {
    //         return new XButton(this.device, 6);         //RB
    //     }
    //     @Override
    //     public XButton elevatorDown() {
    //         return new XButton(this.device, 5);         //LB
    //     }
    //     @Override
    //     public XButton speedToggle() {
    //         return new XButton(this.device, 1);         //A
    //     }
    // }
    // private class ControlBoard implements RequiredControl {
    //     private Joystick stickL, stickR, stickE;
    //     private int ac_l, ac_r, ac_e;
    //     public ControlBoard(int s_left, int s_right) {
    //         this.stickL = new Joystick(s_left);
    //         this.stickR = new Joystick(s_right);
    //         this.stickE = null;

    //         ac_l = stickL.getAxisCount();
    //         ac_r = stickR.getAxisCount();
    //         ac_e = 0;
    //     }
    //     public ControlBoard(int s_left, int s_right, int s_extra) {
    //         this.stickL = new Joystick(s_left);
    //         this.stickR = new Joystick(s_right);
    //         this.stickE = new Joystick(s_extra);

    //         ac_l = stickL.getAxisCount();
    //         ac_r = stickR.getAxisCount();
    //         ac_e = stickE.getAxisCount();
    //     }

    //     @Override
    //     public double getAxis(int axis) {
    //         if (axis <= ac_l) {
    //             return stickL.getRawAxis(axis);
    //         } else if ((axis - ac_l) <= ac_r) {
    //             return stickR.getRawAxis(axis - ac_l);
    //         } else if (stickE != null && (axis - (ac_l + ac_r) <= ac_e)) {
    //             return stickE.getRawAxis(axis - (ac_l + ac_r));
    //         } else {
    //             System.out.println("ControlBoard could not find axis " + axis);
    //             return 0;
    //         }
    //     }

    //     @Override 
    //     public XButton hatchPistonIn() {
    //         return new XButton(this.stickL, Constants.hatchPistonIn);    
    //     }
    //     @Override
    //     public XButton hatchPistonOut() {
    //         return new XButton(this.stickL, Constants.hatchPistonOut);        
    //     }
    //     @Override
    //     public XButton hatchGrabOpen() {
    //         return new XButton(this.stickR, Constants.hatchGrabOpen1);        
    //     }
    //     @Override
    //     public XButton hatchGrabClose() {
    //         return new XButton(this.stickR, Constants.hatchGrabOpen1);        
    //     }
    //     @Override
    //     public XButton armsPistonToggle() {
    //         return new XButton(this.stickL, Constants.armsPistonToggle);         
    //     }
    //     @Override
    //     public XButton cargoIn() {
    //         return new XButton(this.stickL, Constants.cargoIn);         
    //     }
    //     @Override
    //     public XButton cargoOut() {
    //         return new XButton(this.stickL, Constants.cargoOut);         
    //     }
    //     @Override
    //     public XButton elevatorUp() {
    //         return new XButton(this.stickR, Constants.elevatorUp);         
    //     }
    //     @Override
    //     public XButton elevatorDown() {
    //         return new XButton(this.stickR, Constants.elevatorDown);         
    //     }
    //     @Override
    //     public XButton speedToggle() {
    //         return new XButton(this.stickR, Constants.speedToggle);         
    //     }
    // }

    //instantiate commands here (create variables for each one)
    // private CargoIntake cargo_in = new CargoIntake();
    // private CargoOutput cargo_out = new CargoOutput();
    // private CargoToggle cargo_arms = new CargoToggle();
    // private ElevatorUp elevator_up = new ElevatorUp();
    // private ElevatorDown elevator_down = new ElevatorDown();
    // private HatchClose close_hatch = new HatchClose();
    // private HatchOpen open_hatch = new HatchOpen();
    // private HatchToggle hatch_out = new HatchToggle(true);
    // private HatchToggle hatch_in = new HatchToggle(false);
    // private SpeedToggle speed_toggle = new SpeedToggle();
    // public TeleopDrive teleop_control = new TeleopDrive();

    // private RequiredControl control;
    // private void setupKeybinds() {  //setup what buttons do what inside here
    //     this.control.hatchGrabOpen().whileHeld(this.open_hatch);
    //     this.control.hatchGrabClose().whileHeld(this.close_hatch);
    //     this.control.hatchPistonIn().whenPressed(this.hatch_in);
    //     this.control.hatchPistonOut().whenPressed(this.hatch_out);

    //     this.control.armsPistonToggle().whenPressed(this.cargo_arms);
    //     this.control.cargoIn().whileHeld(this.cargo_in);
    //     this.control.cargoOut().whileHeld(this.cargo_out);

    //     this.control.elevatorUp().whileHeld(this.elevator_up);
    //     this.control.elevatorDown().whileHeld(this.elevator_down);
    //     this.control.speedToggle().whenPressed(this.speed_toggle);
    // }

    private TestCommand 
        a = new TestCommand("A"),
        rb = new TestCommand("RB"),
        pov_180 = new TestCommand("POV_BOTTOM");

    public XboxController input;
    private XButton t1, t2, t3;
    public Input(int port) {
        // for (int i = 0; i < DriverStation.kJoystickPorts; i++) {}
        // this.control = new Xbox(port);
        // setupKeybinds();

        input = new XboxController(port);

        t1 = new XButton(input, 1);
        t2 = new XButton(input, 6);
        t3 = new XButton(input, 13);

        t1.whileHeld(a);
        t2.whileHeld(rb);
        t3.whileHeld(pov_180);
    }
    // public Input(int portl, int portr) {
    //     // this.control = new ControlBoard(portl, portr);
    //     // setupKeybinds();
    // }
    // public Input(int portl, int portr, int porte) {
    //     // this.control = new ControlBoard(portl, portr, porte);
    //     // setupKeybinds();
    // }

    // public RequiredControl getController() {
    //     return this.control;
    // }
}