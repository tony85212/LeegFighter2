import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.io.*;
import java.awt.*; 

public class HomeScreen extends JLabel {
	
	private final static HomeScreen INSTANCE = new HomeScreen();
	public static HomeScreen GetInstance() { return INSTANCE; }
	
	private static final long serialVersionUID = 1L;
	private JButton connect_button = new JButton(), setting_button = new JButton();
	
	private ImageIcon connect = null;
	private ImageIcon setting = null;
	
	private sound click = new sound();
	public sound bgm = new sound();
	
	private HomeScreen() {
		bgm.loop("asset/sound/main.wav");
		setBounds(0, 0, LeegFighter2.width, LeegFighter2.height);
		try {
			BufferedImage img=ImageIO.read(new File("asset/HomeScr/HomeScr_BG.png"));
			this.setIcon(new ImageIcon(img));
		} catch(Exception e) {}
		
		BufferedImage bic;
		try{
			bic = ImageIO.read(new File("asset/HomeScr/HomeScr_connect.png"));
			connect = new ImageIcon(bic.getScaledInstance(150, 50, Image.SCALE_SMOOTH));
		} catch(Exception e){}
		connect_button.setIcon(connect);
		connect_button.setBounds(500, 400, 142, 50);
		connect_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
				click.play("asset/sound/click.wav");
                LeegFighter2.GetInstance().ChangeLabel(Connect.GetInstance());
			}
        });
		add(connect_button);
		
		
		BufferedImage bis;
		try{
			bis = ImageIO.read(new File("asset/HomeScr/HomeScr_setting.png"));
			setting = new ImageIcon(bis.getScaledInstance(150, 50, Image.SCALE_SMOOTH));
		} catch(Exception e){}
		setting_button.setIcon(setting);
		setting_button.setBounds(500,470, 142, 50);
		setting_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
				click.play("asset/sound/click.wav");
                LeegFighter2.GetInstance().ChangeLabel(SetKeyboard.GetInstance());
			}
        });
		add(setting_button);
	}
	
}
