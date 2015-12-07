package com.pickens.util;

import java.awt.Font;

import org.newdawn.slick.TrueTypeFont;

public class Fonts {

	private static final Font menuAwt = new Font("Arial", Font.BOLD, 45);
	public static TrueTypeFont MENU_TEXT;
	
	private static final Font classAwt = new Font("Arial", Font.BOLD, 12);
	public static TrueTypeFont CLASS_TEXT;
	
	public static void loadFonts() {
		MENU_TEXT = new TrueTypeFont(menuAwt, true);
		CLASS_TEXT = new TrueTypeFont(classAwt, true);
	}
	
}
