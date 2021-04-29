import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Item extends DynamicObject {
	
	private int owner = 0, count, move_x = 0;
	private static int id = 0;
	
	public Item(String link, int m, int c) {
		picture = new JLabel();
		picture.setSize(50, 50);
		try {
			System.out.println(link);
			BufferedImage img = ImageIO.read(new File(link));
			picture.setIcon(new ImageIcon(img.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		} catch(Exception e) {}
		no = id;
		id = (id+1)%10000;
		move_x = m;
		count = c*10;
		is_item = true;
	}
	
	public int GetOwner() { return owner; }
	
	public int GetCount() { return count; }
	
	public void SetOwner(int o) { owner = o; }
	
	public void SetXY(int x, int y) { pos_x = x; pos_y = y; }
	
	public void Act() {
		if(count > 0) {
			count -= 1;
			pos_x += move_x;
		}
	}
	
	public void Print() { System.out.println("Item : "+no); }
}