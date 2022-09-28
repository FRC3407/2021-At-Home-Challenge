// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class Constants {
    //                            descriptions >>  {fl fr bl br} {inversion settings}
    public static final DB4 drivebase_map = new DB4(3, 1, 2, 0, Inversions.LEFT);  // 4 motor system (ex. 2020/2021 bot)
    //                              
    public static final DBS drivebase_settings = new DBS(DriveMode.TANK, Deceleration._98, false, new DriveMode[]{DriveMode.TANK, DriveMode.ARCADE});

// ***************** TYPES and CONTAINERS *****************

    // This defines all the functionality that we require from any input system for all commands to work
    public static interface Controls {
        public double getAnalogLX();
        public double getAnalogLY();
        public double getAnalogRX();
        public double getAnalogRY();

        public Input.XButton getSpeedToggle();

        public Input.XButton getDumpActuate();
        public Input.XButton getDumpReset();

        public Input.XButton getEStop();

        public Input.XButton netVarIncrement();
        public Input.XButton netVarDecrement();
    }

    public static enum Inversions {
        NEITHER (false, false),
        LEFT    (true, false),
        BOTH    (true, true),
        RIGHT   (false, true);

        public final boolean left, right;
        Inversions(boolean l, boolean r) {
            this.left = l;
            this.right = r;
        }

        public String toString() {
            return "Inversions@" + this.hashCode() + ": {Left:" + this.left + " Right:" + this.right + "}";
        }
    }
    public static enum DriveMode {
        TANK		(0),
        ARCADE		(1),
        RACE		(2),
        TRIGGER		(3),
        CURVATURE	(4);

        private static DriveMode[] i_options = values();   // THIS DOESN'T WORK
        private int index;
        private DriveMode(int i) {
            this.index = i;
        }

        public int index() {
            return this.index;
        }

        public DriveMode[] getOptions() {
            return this.i_options;
        }
        public void setOptions(DriveMode[] o) {
            this.i_options = o;
        }
        public void resetOptions() {
            this.i_options = values();
        }

        public DriveMode increment() {
            if (this.index + 1 < this.i_options.length) {
                this.index++;
                return this.i_options[this.index];
            } else {
                return this;
            }
        }
        public DriveMode increment(int v) {
            if (this.index + v < this.i_options.length) {
                this.index += v;
                return this.i_options[this.index];
            } else {
                return this;
            }
        }
        public DriveMode decrement() {
            if (this.index > 0) {
                this.index--;
                return this.i_options[this.index];
            } else {
                return this;
            }
        }
        public DriveMode decrement(int v) {
            if (this.index - v >= 0) {
                this.index -= v;
                return this.i_options[this.index];
            } else {
                return this;
            }
        }
    }
    public static enum Deceleration {
        _96	(0.96),
        _97 (0.97),
        _98	(0.98),
        _99	(0.99);

        public final double value;
        private Deceleration(double v) {
            this.value = v;
        }
    }

    public static final class DB2 {     // a drivebase map containing two sides, each with one motor port
        public final int 
            left, 
            right;
        public final Inversions 
            invert;
        
        public DB2(int l, int r) {
            this.left = l;
            this.right = r;
            this.invert = Inversions.NEITHER;
        }
        public DB2(int l, int r, Inversions i) {
            this.left = l;
            this.right = r;
            this.invert = i;
        }

        public String toString() {
            return "DB2@" + this.hashCode() + ": {Left port:" + this.left + " Right port:" + this.right + "}\n >> " + this.invert.toString();
        }
    }
    public static final class DB4 {     // a drivebase map containing two sides, each with two motor ports
        public final int 
            front_left,
            front_right,
            back_left,
            back_right;
        public final Inversions
            invert;

        public DB4(int fl, int fr, int bl, int br) {
            this.front_left = fl;
            this.front_right = fr;
            this.back_left = bl;
            this.back_right = br;
            this.invert = Inversions.NEITHER;
        }
        public DB4(int fl, int fr, int bl, int br, Inversions i) {
            this.front_left = fl;
            this.front_right = fr;
            this.back_left = bl;
            this.back_right = br;
            this.invert = i;
        }

        public String toString() {
            return "DB4@" + this.hashCode() + ": {Front left port:" + this.front_left + " Front right port:" + this.front_right + 
                " Back left port:" + this.back_left + " Back right port:" + this.back_right + "}\n >> " + this.invert.toString();
        }
    }

    public static final class DBS {     // a container for all drivebase settings
        public final DriveMode default_mode;
        public final DriveMode[] mode_options;
        public final boolean default_squaring;
        public final Deceleration s_deceleration;

        public DBS(DriveMode dmode, Deceleration gradient, boolean squaring) {
            this.default_mode = dmode;
            this.s_deceleration = gradient;
            this.default_squaring = squaring;
            this.mode_options = DriveMode.values();
        }

        public DBS(DriveMode dmode, Deceleration gradient, boolean squaring, DriveMode[] options) {
            this.default_mode = dmode;
            this.mode_options = options;
            this.s_deceleration = gradient;
            this.default_squaring = squaring;
        }
    }
}