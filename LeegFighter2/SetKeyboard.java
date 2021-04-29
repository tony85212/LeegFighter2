import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.awt.Image;
import java.awt.image.BufferedImage; 

import java.io.File;
import javax.imageio.ImageIO;

public class SetKeyboard extends JLabel {

	private final static SetKeyboard INSTANCE = new SetKeyboard();
	public static SetKeyboard GetInstance() { return INSTANCE; }
	
	private JLabel[] key_name = new JLabel[8];
	private TextF[] input = new TextF[8];
	private JButton ok_button = new JButton();
	private ImageIcon ok = null;
	
	private sound click = new sound();
		
	private SetKeyboard() {
		
		setBounds(0, 0, LeegFighter2.width, LeegFighter2.height);
		try {
			BufferedImage img=ImageIO.read(new File("asset/Setting/Setting_BG.png"));
			this.setIcon(new ImageIcon(img));
		} catch(Exception e){}
		
	
		
		BufferedImage bio;
		try{
			bio = ImageIO.read(new File("asset/Setting/Setting_OK.png"));
			ok = new ImageIcon(bio.getScaledInstance(150, 50, Image.SCALE_SMOOTH));
		} catch(Exception e){}
		ok_button.setIcon(ok);
		ok_button.setBounds(750, 660, 142, 50);
		ok_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				click.play("asset/sound/click.wav");
				LeegFighter2.GetInstance().ChangeLabel(HomeScreen.GetInstance());
			}
        });
		add(ok_button);
		
		/*default settings*/
		String[] name = {"左", "右", "上", "下", "一技", "二技", "三技", "絕技"};
		for(int i = 0, col = 500, row = 300; i < 8; row+=50, ++i) {
			key_name[i] = new JLabel(name[i], SwingConstants.CENTER);
			key_name[i].setFont(new Font("華康斧劈體W7", Font.PLAIN, 15));
			key_name[i].setBounds(col, row, 100, 33);
			key_name[i].setOpaque(true);
			key_name[i].setBackground(Color.BLACK);
			key_name[i].setForeground(Color.WHITE);
			add(key_name[i]);
			
			input[i] = new TextF(i, col+100, row, 100, 33);
			add(input[i].GetTextField());
		}	
	}
	
	public int GetKey(int i) { return TextF.k[i]; }
}

class TextF extends JTextField {
	
	public int id = 0;
	public JTextField textfield = new JTextField("");
	
	public static int[] k = { KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN, 
							  KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3, KeyEvent.VK_4 };
	
	TextF(int i, int x, int y, int w, int h) {
		textfield = new JTextField(KeyEvent.getKeyText(k[id = i]));
		textfield.addKeyListener( new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				((JTextField)e.getSource()).setText(null);
			}
			public void keyReleased(KeyEvent e) {
				((JTextField)e.getSource()).setText(KeyEvent.getKeyText(k[id] = e.getKeyCode()));
			}
			public void keyTyped(KeyEvent e) {}
		});
		textfield.setFont(new Font("華康斧劈體W7", Font.PLAIN, 15));
		textfield.setHorizontalAlignment(JTextField.CENTER);
		textfield.setBounds(x, y, w, h);
		textfield.setOpaque(true);
		textfield.setBackground(new Color(255, 200, 200));
		textfield.setForeground(Color.BLACK);
	}
	
	public JTextField GetTextField() { return textfield; }
}