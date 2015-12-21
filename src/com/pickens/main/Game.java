package com.pickens.main;

import static com.pickens.util.Constants.GAME_STATE;
import static com.pickens.util.Constants.MENU_STATE;
import static com.pickens.util.Constants.MODE_STATE;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.pickens.states.MenuState;
import com.pickens.states.ModeState;
import com.pickens.states.PlayState;
import com.pickens.util.Constants;
import com.pickens.util.Images;

public class Game extends StateBasedGame {

	public static final String VERSION = "v0.0.8";
	public static boolean ONLINE = false;
	private static Game g;
	
	// Launcher GUI
	private static JFrame frame;
	private static JEditorPane jumbotron;
	private static JCheckBox fullscreen;
	private static JComboBox<String> resolution;
	private static JButton launch;
	
	public Game(String name) {
		super(name);
		
		this.addState(new ModeState());
		this.addState(new MenuState());
		this.addState(new PlayState());
	}
	
	public static void setState(int state) {
		g.enterState(state);
	}

	public void initStatesList(GameContainer gc) throws SlickException {
		Images.load();
		Constants.loadClasses();
		
		this.getState(MODE_STATE).init(gc, this);
		this.getState(MENU_STATE).init(gc, this);
		this.getState(GAME_STATE).init(gc, this);
		
		this.enterState(MODE_STATE);
	}
	
	public static void main(String[] args) {		
		frame = new JFrame("Floodgate Launcher " + VERSION);
		frame.setSize(1000, 563);
		FlowLayout f = new FlowLayout();
		f.setVgap(0);
		frame.setLayout(f);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		jumbotron = new JEditorPane();
		jumbotron.setEditable(false);
		try {
			jumbotron.setPage("http://73.9.75.150:8886/floodgate/index.html");
			ONLINE = true;
		} catch (IOException e) {
			jumbotron.setContentType("text/html");
			jumbotron.setText("<html><br/><br/><center><strong>You're offline.<strong><center></html>");
			ONLINE = false;
		}
		Dimension size = new Dimension(frame.getWidth(), (int) ((int)frame.getHeight()/1.11));
		jumbotron.setBounds(0, 0, (int)size.getWidth(), (int)size.getHeight());
		jumbotron.setPreferredSize(size);
		
		Font font = new Font("arial", Font.PLAIN, 24);
		fullscreen = new JCheckBox("Fullscreen?");
		fullscreen.setFont(font);
		fullscreen.setSelected(true);
		
		String[] values = {"1000x563", "1500x844", "2000x1125"};
		resolution = new JComboBox<String>(values);
		resolution.setFont(font);
		resolution.setEnabled(false);
		
		launch = new JButton("Launch");
		launch.setFont(font);
		
		fullscreen.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if(fullscreen.isSelected()) {
					resolution.setEnabled(false);
				} else {
					resolution.setEnabled(true);
				}
			}
			
		});
		
		launch.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				launchGame();
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		});
		
		// Adding to frame
		frame.add(jumbotron);
		frame.add(fullscreen);
		frame.add(resolution);
		frame.add(launch);
		
		frame.setVisible(true);
	}
	
	private static void launchGame() {
		frame.setVisible(false);
		frame.dispose();
		
        if(fullscreen.isSelected()) {
        	Constants.WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    		Constants.HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
        } else {
        	String selectedString = (String) resolution.getSelectedItem();
    	    switch(selectedString) {
    	    case "2000x1125":
    	    	Constants.WIDTH = 2000;
        		Constants.HEIGHT = 1125;
    	    	break;
    	    case "1500x844":
    	    	Constants.WIDTH = 1500;
        		Constants.HEIGHT = 844;
    	    	break;
    	    case "1000x563":
    	    	Constants.WIDTH = 1000;
        		Constants.HEIGHT = 563;
    	    	break;
    	    }
        }
        
        g = new Game("Floodgate " + VERSION);
        
		AppGameContainer appgc;
		try {
			appgc = new AppGameContainer(g);
			appgc.setDisplayMode(Constants.WIDTH, Constants.HEIGHT, fullscreen.isSelected());
			appgc.setVSync(true);
			appgc.setTargetFrameRate(60);
			appgc.setAlwaysRender(true);
			appgc.setVerbose(false);
			appgc.start();
		} catch(SlickException e) {
			e.printStackTrace();
		}
	}
	
}
