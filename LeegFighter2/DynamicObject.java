import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.Graphics;

import java.io.File;
import javax.imageio.ImageIO;

public class DynamicObject {
	
	protected int no = 0;
	protected JLabel label, picture;
	protected int pos_x = 0, pos_y = 0;
	protected boolean is_item = false;
	
	protected DynamicObject() {}
	
	public int GetWidth() { return picture.getWidth(); }
	
	public int GetHeight() { return picture.getHeight(); }
	
	public int GetNo() { return no; }
	
	public boolean IsItem() { return is_item; }
	
	public void Print() { System.out.println("DynamicObject"); }
	
	public void draw(Graphics g) {
		int x = GetMapX(), y = GetMapY();
		g.drawImage(((ImageIcon)picture.getIcon()).getImage(),
						x, y, picture.getWidth(), picture.getHeight(), null);
		if(is_item == false) {
			g.drawImage(((ImageIcon)label.getIcon()).getImage(),
						x, y-label.getHeight(), label.getWidth(), label.getHeight(), null);
		}
	}
	
	private int GetMapX() {
		int w = SceneDataModule.GetInstance().getWidth()*Tile.TILEWIDTH,
			x = LeegFighter2.GetInstance().width/2,
			dx = picture.getWidth()/2;
		
		if(is_item == false && no == TCPCM.GetInstance().GetClientIndex()) {
			if(pos_x < x)
				return pos_x-dx;
			else if(w-pos_x < x)
				return x*2-(w-pos_x)-dx;
			else if(pos_x >= x && w-pos_x >= x)
				return x-dx;
			return pos_x;
		}
		else 
			return pos_x-(int)SceneRenderEngine.GetInstance().GetGameCamera().getOffsetX()-dx;
	}
	
	private int GetMapY() {
		int h = SceneDataModule.GetInstance().getHeight()*Tile.TILEHEIGHT,
			y = LeegFighter2.GetInstance().height/2,
			dy = picture.getHeight()/2;
		if(is_item == false && no == TCPCM.GetInstance().GetClientIndex()) {
			if(pos_y < y)
				return pos_y-dy;
			else if(h-pos_y < y)
				return y*2-(h-pos_y)-dy;
			else if(pos_y >= y && h-pos_y >= y)
				return y-dy;
			return pos_y;
		}
		else
			return pos_y-(int)SceneRenderEngine.GetInstance().GetGameCamera().getOffsetY()-dy;
	}
	
	public int GetCount() { return 0; }
	
	public void Act() {}
}