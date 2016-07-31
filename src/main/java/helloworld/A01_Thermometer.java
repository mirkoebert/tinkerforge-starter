package helloworld;


import com.tinkerforge.*;

public final class A01_Thermometer {
	private static final String HOST = "localhost";
	private static final int PORT = 4223;

	// TODO: Set correct UID of your Segment Display 4x7 Bricklet
	private static final String UID_47 = "wLa";
	private static final String UID_temp = "zk7";

	 private static final byte[] DIGITS = {0x3f,0x06,0x5b,0x4f,
             0x66,0x6d,0x7d,0x07,
             0x7f,0x6f,0x77,0x7c,
             0x39,0x5e,0x79,0x71}; // 0~9,A,b,C,d,E,F
	 
	public static void main(String[] args) throws Exception {
		IPConnection ipcon = new IPConnection(); // Create IP connection
		BrickletSegmentDisplay4x7 sd = new BrickletSegmentDisplay4x7(UID_47, ipcon); // Create device object
		BrickletTemperature temperature = new BrickletTemperature(UID_temp, ipcon);

		ipcon.connect(HOST, PORT); // Connect to brickd
		// Don't use device before ipcon is connected

		float temp = temperature.getTemperature();
		System.out.println("Temp raw:"+temp);
		int displayTemp = Math.round(temp/100);
		System.out.println("Temp raw:"+displayTemp);
		// http://www.tinkerforge.com/en/doc/Software/Bricklets/SegmentDisplay4x7_Bricklet_Java.html#segment-display-4x7-bricklet-java-examples
		short[] segments = {DIGITS[displayTemp/10],DIGITS[displayTemp%10],0b001100011,0b000111001};
		sd.setSegments(segments, (short)3, false);
		System.out.println(sd.getSegments());
		
		ipcon.disconnect();
	}

}
