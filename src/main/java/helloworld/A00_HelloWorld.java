package helloworld;


import com.tinkerforge.*;

public final class A00_HelloWorld {
	private static final String HOST = "localhost";
	private static final int PORT = 4223;

	// TODO: Set correct UID of your Segment Display 4x7 Bricklet
	private static final String UID = "wLa";

	public static void main(String[] args) throws Exception {
		IPConnection ipcon = new IPConnection(); // Create IP connection
		BrickletSegmentDisplay4x7 sd = new BrickletSegmentDisplay4x7(UID, ipcon); // Create device object

		ipcon.connect(HOST, PORT); // Connect to brickd
		// Don't use device before ipcon is connected

		// http://www.tinkerforge.com/en/doc/Software/Bricklets/SegmentDisplay4x7_Bricklet_Java.html#segment-display-4x7-bricklet-java-examples
		short[] segments = {0b01110110,0b01111001,0b00111000,0b00111000};
		sd.setSegments(segments, (short)3, true);
		System.out.println(sd.getSegments());
		
		ipcon.disconnect();
	}

}
