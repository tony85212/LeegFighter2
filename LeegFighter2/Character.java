import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Character {
	
	protected int id = 0, hp = 100, attack = 1;
	protected double speed = 1;
	protected double scale = 1;
	protected int error_y = 0;
	protected Skill skill;
	
	protected ImageIcon[][] move = new ImageIcon[6][7];
	private ImageIcon dead = new ImageIcon();
	
	private static final int pixel_w = 100;
	private static final int pixel_h = 100;
	
	protected Character(double sc, String s) {
		scale = sc;
		
		// save image
		try {
			BufferedImage img;
			//walk => (0,1)0~6
			for(int y = 0; y < 7; ++y) {
				for(int x = 0; x < 2; ++x) {
					img = ImageIO.read(new File(s+x+y+".png"));
					move[x][y] = new ImageIcon(img.getScaledInstance(this.GetWidth(), this.GetHeight(), Image.SCALE_SMOOTH));
				}
			}
			//skill1 => (2,3)0~1
			for(int y = 0; y < 2; ++y) {
				for(int x = 2; x <= 3; ++x) {
					img = ImageIO.read(new File(s+x+y+".png"));
					move[x][y] = new ImageIcon(img.getScaledInstance(this.GetWidth(), this.GetHeight(), Image.SCALE_SMOOTH));
				}
			}
			//dead => dead
			img = ImageIO.read(new File(s+"dead.png"));
			dead = new ImageIcon(img.getScaledInstance(this.GetWidth(), this.GetHeight(), Image.SCALE_SMOOTH));
		} catch(Exception e) {}
	}
	
	public int GetId() { return id; }
	
	public int GetHp(){ return hp; }
	
	public int GetAttack(){ return attack; }
	
	public double GetSpeed() { return speed; }
	
	public double GetScale() { return scale; }
	
	public int GetWidth() { return (int)(pixel_w*scale); }
	
	public int GetHeight() { return (int)(pixel_h*scale); }
	
	public int GetErrorY() { return (int)(error_y*scale); }
	
	public ImageIcon GetMoveImageIcon(int pic_x, int pic_y) { return move[pic_x][pic_y]; }
	
	public ImageIcon GetDeadImageIcon() { return dead; }
	
	public Skill GetSkill() { return skill; }
	
	public void SetHp(int h){ hp = h < 0 ? 0 : h; }
	
	public void SetAttack(int a){ attack = a; }
	
	public void SetSpeed(double s) { speed = s; }
}