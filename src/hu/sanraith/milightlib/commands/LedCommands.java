package hu.sanraith.milightlib.commands;

public class LedCommands {
    // White LEDs
    public static final byte[] AllOn = new byte[]{0x35, 0x0, 0x55};
    public static final byte[] AllOff = new byte[]{0x39, 0x0, 0x55};
    public static final byte[] BrightnessUp = new byte[]{0x3C, 0x0, 0x55};
    public static final byte[] BrightnessDown = new byte[]{0x34, 0x0, 0x55};
    public static final byte[] ColorTempDown = new byte[]{0x3F, 0x0, 0x55};
    public static final byte[] Group1On = new byte[]{0x38, 0x0, 0x55};
    public static final byte[] Group1Off = new byte[]{0x3B, 0x0, 0x55};
    public static final byte[] Group2On = new byte[]{0x3D, 0x0, 0x55};
    public static final byte[] Group2Off = new byte[]{0x33, 0x0, 0x55};
    public static final byte[] Group3On = new byte[]{0x37, 0x0, 0x55};
    public static final byte[] Group3Off = new byte[]{0x3A, 0x0, 0x55};
    public static final byte[] Group4On = new byte[]{0x32, 0x0, 0x55};
    public static final byte[] Group4Off = new byte[]{0x36, 0x0, 0x55};
    public static final byte[] AllFull = new byte[]{(byte) 0xB5, 0x0, 0x55};
    public static final byte[] Group1Full = new byte[]{(byte) 0xB8, 0x0, 0x55};
    public static final byte[] Group2Full = new byte[]{(byte) 0xBD, 0x0, 0x55};
    public static final byte[] Group3Full = new byte[]{(byte) 0xB7, 0x0, 0x55};
    public static final byte[] Group4Full = new byte[]{(byte) 0xB2, 0x0, 0x55};
    public static final byte[] AllNight = new byte[]{(byte) 0xB9, 0x0, 0x55};
    public static final byte[] Group1Night = new byte[]{(byte) 0xBB, 0x0, 0x55};
    public static final byte[] Group2Night = new byte[]{(byte) 0xB3, 0x0, 0x55};
    public static final byte[] Group3Night = new byte[]{(byte) 0xBA, 0x0, 0x55};
    public static final byte[] Group4Night = new byte[]{(byte) 0xB6, 0x0, 0x55};
    public static final byte[] ColorTempUp = new byte[]{0x3E, 0x0, 0x55};

    // RGB LEDs
    public static final byte[] RGBOn = new byte[]{0x22, 0x0, 0x55};
    public static final byte[] RGBOff = new byte[]{0x21, 0x0, 0x55};
    public static final byte[] RGBBrightnessUp = new byte[]{0x23, 0x0, 0x55};
    public static final byte[] RGBBrightnessDown = new byte[]{0x24, 0x0, 0x55};
    public static final byte[] RGBSpeedUp = new byte[]{0x25, 0x0, 0x55};
    public static final byte[] RGBSpeedDown = new byte[]{0x26, 0x0, 0x55};
    public static final byte[] RGBDiscoNext = new byte[]{0x27, 0x0, 0x55};
    public static final byte[] RGBDiscoLast = new byte[]{0x28, 0x0, 0x55};
    public static final byte[] RGBColour = new byte[]{0x20, 0x0, 0x55};

    // RGBW LEDs
    public static final byte[] RGBWOff = new byte[]{0x41, 0x0, 0x55};
    public static final byte[] RGBWOn = new byte[]{0x42, 0x0, 0x55};
    public static final byte[] RGBWDiscoSpeedSlower = new byte[]{0x43, 0x0, 0x55};
    public static final byte[] RGBWDiscoSpeedFaster = new byte[]{0x44, 0x0, 0x55};
    public static final byte[] RGBWGroup1AllOn = new byte[]{0x45, 0x0, 0x55};
    public static final byte[] RGBWGroup1AllOff = new byte[]{0x46, 0x0, 0x55};
    public static final byte[] RGBWGroup2AllOn = new byte[]{0x47, 0x0, 0x55};
    public static final byte[] RGBWGroup2AllOff = new byte[]{0x48, 0x0, 0x55};
    public static final byte[] RGBWGroup3AllOn = new byte[]{0x49, 0x0, 0x55};
    public static final byte[] RGBWGroup3AllOff = new byte[]{0x4A, 0x0, 0x55};
    public static final byte[] RGBWGroup4AllOn = new byte[]{0x4B, 0x0, 0x55};
    public static final byte[] RGBWGroup4AllOff = new byte[]{0x4C, 0x0, 0x55};
    public static final byte[] RGBWDiscoMode = new byte[]{0x4D, 0x0, 0x55};
    public static final byte[] SetColorToWhite = new byte[]{(byte) 0xC2, 0x0, 0x55};
    public static final byte[] SetColorToWhiteGroup1 = new byte[]{(byte) 0xC5, 0x0, 0x55};
    public static final byte[] SetColorToWhiteGroup2 = new byte[]{(byte) 0xC7, 0x0, 0x55};
    public static final byte[] SetColorToWhiteGroup3 = new byte[]{(byte) 0xC9, 0x0, 0x55};
    public static final byte[] SetColorToWhiteGroup4 = new byte[]{(byte) 0xCB, 0x0, 0x55};
    public static final byte[] RGBWBrightness = new byte[]{0x4E, 0x0, 0x55};
    public static final byte[] RGBWColor = new byte[]{0x40, 0x0, 0x55};
}
