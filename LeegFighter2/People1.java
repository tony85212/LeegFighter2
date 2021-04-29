import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class People1 extends Character {
	
	//¬õ¾v·À±þ®V
	
	public People1() {
		this(2);
	}
	
	public People1(double s) {
		super(s, "asset/character/people1/");
		id = 1;
		hp = 100;
		attack = 4;
		speed = 1;
		skill = new People1_Skill();
		
		try {
			BufferedImage img;
			//Skill2 => (4,5)0~3
			for(int y = 0; y < 4; ++y) {
				for(int x = 4; x <= 5; ++x) {
					img = ImageIO.read(new File("asset/character/people1/"+x+y+".png"));
					move[x][y] = new ImageIcon(img.getScaledInstance(this.GetWidth(), this.GetHeight(), Image.SCALE_SMOOTH));
				}
			}
		} catch(Exception e) {}
	}
}