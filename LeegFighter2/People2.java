import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class People2 extends Character {
	
	//´Ö¤ì»e³·¨à
	
	public People2() {
		this(2);
	}
	
	public People2(double s) {
		super(s, "asset/character/people2/");
		id = 2;
		hp = 120;
		attack = 5;
		speed = 1.2;
		error_y = 2;
		skill = new People2_Skill();
		
		try {
			BufferedImage img;
			//Skill2 => (4,5)0~3
			for(int y = 0; y < 4; ++y) {
				for(int x = 4; x <= 5; ++x) {
					img = ImageIO.read(new File("asset/character/people2/"+x+y+".png"));
					move[x][y] = new ImageIcon(img.getScaledInstance(this.GetWidth(), this.GetHeight(), Image.SCALE_SMOOTH));
				}
			}
		} catch(Exception e) {}
	}
}