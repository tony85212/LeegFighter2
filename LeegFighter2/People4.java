import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class People4 extends Character {
	
	//¤p¬õ´U«ê«ê
	
	public People4() {
		this(2);
	}
	
	public People4(double s) {
		super(s, "asset/character/people4/");
		id = 4;
		hp = 80;
		attack = 3;
		speed = 0.8;
		error_y = 10;
		skill = new People4_Skill();
		
		try {
			BufferedImage img;
			//Skill2 => (4,5)0~1
			for(int y = 0; y < 2; ++y) {
				for(int x = 4; x <= 5; ++x) {
					img = ImageIO.read(new File("asset/character/people4/"+x+y+".png"));
					move[x][y] = new ImageIcon(img.getScaledInstance(this.GetWidth(), this.GetHeight(), Image.SCALE_SMOOTH));
				}
			}
		} catch(Exception e) {}
	}
}