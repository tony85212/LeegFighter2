import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Canvas;
import java.awt.Color;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
import java.io.File;
import javax.imageio.ImageIO;

public class LeegFighter2 extends JFrame implements Runnable {
	
	private static LeegFighter2 INSTANCE = new LeegFighter2();
	public static LeegFighter2 GetInstance() { return INSTANCE; }
	
	private Canvas fight_scene = new Canvas();
	private JLabel result_label = new JLabel();
	private BufferStrategy bs;
	private Graphics g;
	private JButton skill_button = new JButton();
	private ImageIcon[] skill2 = new ImageIcon[4];

	//private int PlayerSize = TCPCM.GetInstance().GetPlayerSize();
	
	static public int width = 1200, height = 800;
	private boolean running=true;
	private JLabel[] hp_label = new JLabel[4];
	public static boolean[] keyboard = new boolean[200];
	
	private int end_count = 0;
	private ImageIcon win, lose;
	
	private sound click = new sound();
	public sound bgm = new sound();
	
    static public void main(String[] args) {
		LeegFighter2.GetInstance().Show();
    }
	
	private LeegFighter2() {
		setTitle("LeegFighter2");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		SceneDataModule.GetInstance().loadMap("asset/map/worlds/happy_farm.txt");
		Assets.init();
	}
	
	public void Show() {
		setSize(width, height);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setLayout(null);
		ChangeLabel(HomeScreen.GetInstance());
	}
	
	public void ChangeLabel(JLabel label) {
		getContentPane().removeAll();
		add(label);
		repaint();
	}
	
	public void Fighting() {
		try {
			BufferedImage bi;
			bi = ImageIO.read(new File("asset/Fighting/victory.png"));
			win = new ImageIcon(bi.getScaledInstance(width, height, Image.SCALE_SMOOTH));
			bi = ImageIO.read(new File("asset/Fighting/defeat.png"));
			lose = new ImageIcon(bi.getScaledInstance(width, height, Image.SCALE_SMOOTH));
			for(int i = 0; i < 4; ++i) {
				bi = ImageIO.read(new File("asset/character/people"+Integer.toString(i+1)+"/skill2.png"));
				skill2[i] = new ImageIcon(bi.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
			}
		} catch (Exception e) {}
		
		System.out.println("Start Fighting!!");
		getContentPane().removeAll();
		
		Color[] c = new Color[4];
		c[0]=Color.red;
		c[1]=Color.green;
		c[2]=Color.blue;
		c[3]=Color.orange;
		for(int i=0;i<4;i++)
		{	hp_label[i] = new JLabel("  [ No Player"+(i+1)+" ]", SwingConstants.LEFT);
			hp_label[i].setFont(new Font("Arial", Font.PLAIN, 32));
			hp_label[i].setBounds(i*300, 700, 300, 60);
			hp_label[i].setForeground(c[i]);
			add(hp_label[i]);
		}
		add(result_label);
		skill_button.setBounds(1100, 50, 50, 50);
		skill_button.setIcon(skill2[DOM.GetInstance().GetPlayer(TCPCM.GetInstance().GetClientIndex()).GetCharacter().GetId()-1]);
		
		
		add(skill_button);
		
		fight_scene.setBounds(0, 0, LeegFighter2.width, LeegFighter2.height);
		fight_scene.addKeyListener( new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyboard[e.getKeyCode()] = true;
			}
			public void keyReleased(KeyEvent e) {
				keyboard[e.getKeyCode()] = false;
			}
			public void keyTyped(KeyEvent e) {}
		});
		add(fight_scene);
		
		render();
		
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
		running = true;
		while(running) {
			try {
				if(DOM.GetInstance().GetPlayer(TCPCM.GetInstance().GetClientIndex()).GetSkill().GetCD(2) != 0)
					skill_button.setEnabled(false);
				else
					skill_button.setEnabled(true);
				if(end_count > 0) {
					if(end_count%10 == 0)
						System.out.println(end_count/10);
					end_count -= 1;
					if(end_count == 0) {
						TCPCM.GetInstance().send("disconnect");
						SelectCharacter.GetInstance().fightbgm.stop();
						bgm.loop("asset/sound/main.wav");
					}
				}
				else
					DOM.GetInstance().keyGETPressed();
				
				fight_scene.getBufferStrategy().getDrawGraphics().clearRect(0, 0, LeegFighter2.width, LeegFighter2.height);
				
				if(end_count == 0 && DOM.GetInstance().IsGameOver()) {
					end_count = 50;
					result_label.setBounds(0, 0, width, height);
					if(DOM.GetInstance().GetPlayer(TCPCM.GetInstance().GetClientIndex()).GetHp() > 0) {
						try {
							BufferedImage bi = ImageIO.read(new File("asset/Fighting/victory.png"));
							result_label.setIcon(new ImageIcon(bi.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
						} catch (Exception e) {}
					}
					else {
						try {
							BufferedImage bi = ImageIO.read(new File("asset/Fighting/defeat.png"));
							result_label.setIcon(new ImageIcon(bi.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
						} catch (Exception e) {}
					}
					
					
				}
				
				render();
				
				Thread.sleep(100);
			} catch(Exception e) {}
		}
	}
	
	public void Gameover() {
		running = false;
		this.ChangeLabel(Connect.GetInstance());
	}
	
	private void render() {
		bs = fight_scene.getBufferStrategy();
		if(bs == null) {
			fight_scene.createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		SceneRenderEngine.GetInstance().renderScene(g, (float)DOM.GetInstance().getVirtualCharacterXY().getX(), (float)DOM.GetInstance().getVirtualCharacterXY().getY());
		SPRITERE.GetInstance().renderSprites(g, hp_label);
		
		bs.show();
		g.dispose();
	}

	/*
	// listening keyboard
	public void processKeyEvent(KeyEvent e) {
		if(e.getID() == KeyEvent.KEY_PRESSED)
			keyboard[e.getKeyCode()] = true;
		if(e.getID() == KeyEvent.KEY_RELEASED)
			keyboard[e.getKeyCode()] = false;
	}
	*/
}
