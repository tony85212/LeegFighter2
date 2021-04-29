import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.net.URL;
import javax.swing.*;
import javax.swing.text.Segment;
import java.net.InetAddress;
import javax.imageio.ImageIO;

import java.io.*;

import javax.imageio.ImageIO;

import org.w3c.dom.Document;

import java.awt.image.BufferedImage; 

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

public class SelectCharacter extends JLabel{
	private final static SelectCharacter INSTANCE = new SelectCharacter();
	public static SelectCharacter GetInstance() { return INSTANCE; }
	
	private int choose = 0;
	
	private ImageIcon[] img = new ImageIcon[4];
	private ImageIcon left = null, right = null, wait = null, lock = null;
	
	private JLabel[] text_label = new JLabel[4], image_label = new JLabel[4];
	private JButton left_button = new JButton(), right_button = new JButton();
	
	public JButton go_button = new JButton(),  confirm_button = new JButton();
	private ImageIcon confirm = null;
	private ImageIcon start = null;
	
	private sound click = new sound();
	public sound fightbgm = new sound();
	
	private SelectCharacter(){
		
		setBounds(0, 0, LeegFighter2.width, LeegFighter2.height);
		
		try {
			BufferedImage bi;
			
			bi = ImageIO.read(new File("asset/SelectCharacter/SelectCharacter_BG.png"));
			this.setIcon(new ImageIcon(bi));
			
			bi = ImageIO.read(new File("asset/SelectCharacter/button_left.png"));
			left = new ImageIcon(bi.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
			
			bi = ImageIO.read(new File("asset/SelectCharacter/button_right.png"));
			right = new ImageIcon(bi.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
			
			bi = ImageIO.read(new File("asset/SelectCharacter/choose_wait.png"));
			wait = new ImageIcon(bi.getScaledInstance(175, 175, Image.SCALE_SMOOTH));
			
			bi = ImageIO.read(new File("asset/SelectCharacter/choose_lock.png"));
			lock = new ImageIcon(bi.getScaledInstance(175, 175, Image.SCALE_SMOOTH));
			
			bi = ImageIO.read(new File("asset/SelectCharacter/SelectCharacter_OK.png"));
			confirm = new ImageIcon(bi.getScaledInstance(150, 50, Image.SCALE_SMOOTH));
			
			bi = ImageIO.read(new File("asset/SelectCharacter/SelectCharacter_Start.png"));
			start = new ImageIcon(bi.getScaledInstance(150, 50, Image.SCALE_SMOOTH));
			
			for(int i = 0; i < 4; ++i) {
				bi = ImageIO.read(new File("asset/character/people"+Integer.toString(i+1)+"/choose.png"));
				img[i] = new ImageIcon(bi.getScaledInstance(175, 175, Image.SCALE_SMOOTH));
			}
		} catch(Exception e) {}
		
		for(int i = 0; i < 4; ++i) {
			text_label[i] = new JLabel("player " + Integer.toString(i+1), SwingConstants.CENTER);
			Font myfont = new Font("verdana", Font.BOLD, 15);
			text_label[i].setFont(myfont);
			text_label[i].setBounds(200+195*i, 425, 175, 20);
			text_label[i].setOpaque(true);
			text_label[i].setBackground(new Color(0, 0, 255));
			text_label[i].setForeground(Color.WHITE);
			add(text_label[i]);
			
			image_label[i] = new JLabel();
			image_label[i].setBounds(200+195*i, 250, 175, 175);
			image_label[i].setOpaque(true);
			image_label[i].setBackground(new Color(100, 0, 100));
			add(image_label[i]);
		}
		
		left_button.setIcon(left);
		left_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				click.play("asset/sound/click.wav");
				image_label[TCPCM.GetInstance().GetClientIndex()-1].setIcon(img[choose=(choose-1+4)%4]);
				repaint();
			}
		});
		add(left_button);
				
		right_button.setIcon(right);
		right_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				click.play("asset/sound/click.wav");
				image_label[TCPCM.GetInstance().GetClientIndex()-1].setIcon(img[choose=(choose+1)%4]);
				repaint();
			}
		});
		add(right_button);
				
		confirm_button.setIcon(confirm);
		confirm_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				click.play("asset/sound/click.wav");
				left_button.setEnabled(false);
				right_button.setEnabled(false);
				confirm_button.setEnabled(false);
				TCPCM.GetInstance().send("select "+TCPCM.GetInstance().GetClientIndex()+" "+Integer.toString(choose+1));
			}
		});
		add(confirm_button);
					
		go_button.setIcon(start);
		go_button.setBounds(800, 600, 142, 50);
		go_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				click.play("asset/sound/click.wav");
				HomeScreen.GetInstance().bgm.stop();
				fightbgm.loop("asset/sound/fighting.wav");
				go_button.setEnabled(false);
				TCPSM.GetInstance().broadcast("start");
			}
		});
		
		this.Reset();
	}
	
	public void Reset() {
		
		remove(go_button);
		
		while(TCPCM.GetInstance().GetPlayerSize() == 0 || TCPCM.GetInstance().GetClientIndex() == 0)
			System.out.println("PlayerSize = "+TCPCM.GetInstance().GetPlayerSize()+"  /  ClientIndex = "+TCPCM.GetInstance().GetClientIndex());
		
		for(int i = 0; i < 4; ++i) {
			try {
				if(i == TCPCM.GetInstance().GetClientIndex()-1)
					image_label[i].setIcon(img[0]);
				else if(i < TCPCM.GetInstance().GetPlayerSize())
					image_label[i].setIcon(wait);
				else
					image_label[i].setIcon(lock);
			} catch(Exception e) {}
			
			if(TCPCM.GetInstance().GetClientIndex()-1 == i) {
				left_button.setBounds(200+195*i, 325, 30, 30);
				left_button.setEnabled(true);
				
				right_button.setBounds(345+195*i, 325, 30, 30);
				right_button.setEnabled(true);
				
				confirm_button.setBounds(215+195*i, 450, 142, 50);
				confirm_button.setEnabled(false);
				
				if(Connect.GetInstance().IsServer()) {
					go_button.setEnabled(false);
					add(go_button);
				}
			}
		}
	}
}


