import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.lang.Math;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Player extends DynamicObject {
	
	private Character character;
	
	private int pic_x = 0, pic_y = 6;
	private int animation_picy = 0, animation_count = 0;
	
	private int[] key = new int[8];
	
	private static final int LEFT   = 0;
	private static final int RIGHT  = 1;
	private static final int UP     = 2;
	private static final int DOWN   = 3;
	private static final int SKILL1 = 4;
	private static final int SKILL2 = 5;
	private static final int SKILL3 = 6;
	private static final int SKILL4 = 7;
	
	private static final int label_h = 20;
	
	public Player(int clientno) {
		no = clientno;
		picture = new JLabel();
		label = new JLabel();
		//this.CreatePeople(1, 0);  // default = People1
	}
	
	public void CreatePeople(int id, int dir) {
		this.SetKey();
		switch(id) {
			case 1:
				character = new People1();
				break;
			case 2:
				character = new People2();
				break;
			case 3:
				character = new People3();
				break;
			case 4:
				character = new People4();
				break;
		}
		
		label.setSize(character.GetWidth(), this.GetLabelHeight());
		try {
			BufferedImage img = ImageIO.read(new File("asset/character/label/"+Integer.toString(no)+".png"));
			label.setIcon( new ImageIcon(img.getScaledInstance(character.GetWidth(), this.GetLabelHeight(), Image.SCALE_SMOOTH)));
		} catch(Exception e) {}
		picture.setSize(character.GetWidth(), character.GetHeight());
		
		int w = SceneDataModule.GetInstance().getWidth()*Tile.TILEWIDTH, h = SceneDataModule.GetInstance().getHeight()*Tile.TILEHEIGHT;
		while(CanMoveTo(pos_x=(int)(Math.random()*SceneDataModule.GetInstance().getWidth()-2)*Tile.TILEWIDTH, pos_y=(int)(Math.random()*SceneDataModule.GetInstance().getHeight()-3)*Tile.TILEHEIGHT) == false);
		this.SetPicture(dir, 0);
		
		//Item fireleft = new Item("asset/item/fire_left.png", -10, 10);
		//fireleft.SetXY(pos_x, pos_y);
		//DOM.GetInstance().addItem(TCPCM.GetInstance().fire_left);
	}
	
	public Character GetCharacter() { return character; }
	
	public Skill GetSkill() { return character.GetSkill(); }
	
	public int GetPicX() { return pic_x; } //direction
	
	public int GetPicY() { return pic_y; }
	
	public Point GetXY() { return new Point(pos_x, pos_y); }
	
	public double GetSpeed() { return character.GetSpeed(); }
	
	public int GetHp() { return character.GetHp(); }
	
	public void SetHp(int hp) {
		if(character.GetHp() == 0)
			return;
		character.SetHp(hp);
		if(character.GetHp() == 0) {
			System.out.println(no+" DEAD!");
			picture.setIcon(character.GetDeadImageIcon());
		}
	}
	
	public void SetAttack(int attack) { character.SetAttack(attack); }
	
	public void SetPicture(int picx, int picy) { picture.setIcon(character.GetMoveImageIcon(pic_x=picx, pic_y=picy)); }
	
	public void SetDirection(int picx) { pic_x=picx; }
	
	public void SetLocation(int x, int y) { pos_x=x; pos_y=y; }
	
	public void SetSpeed(double s) { character.SetSpeed(s); }
	
	public void SetKey() {
		key[LEFT]   = SetKeyboard.GetInstance().GetKey(LEFT);
		key[RIGHT]  = SetKeyboard.GetInstance().GetKey(RIGHT);
		key[UP]     = SetKeyboard.GetInstance().GetKey(UP);
		key[DOWN]   = SetKeyboard.GetInstance().GetKey(DOWN);
		key[SKILL1] = SetKeyboard.GetInstance().GetKey(SKILL1);
		key[SKILL2] = SetKeyboard.GetInstance().GetKey(SKILL2);
		key[SKILL3] = SetKeyboard.GetInstance().GetKey(SKILL3);
		key[SKILL4] = SetKeyboard.GetInstance().GetKey(SKILL4);
	}
	
	public void Act() {
		
		if(character.GetHp() == 0) {
			TCPCM.GetInstance().send("hp "+TCPCM.GetInstance().GetClientIndex()+" 0");
			return;
		}
		
		character.GetSkill().ReduceCD();
		//skill
		if(character.GetSkill().GetCD(1) == 0 && character.GetSkill().GetCount() == 0 && LeegFighter2.keyboard[key[SKILL1]])
			character.GetSkill().SetAct(no, 1);
		if(character.GetSkill().GetCD(2) == 0 && character.GetSkill().GetCount() == 0 && LeegFighter2.keyboard[key[SKILL2]])
			character.GetSkill().SetAct(no, 2);
		//walk
		if(character.GetSkill().GetCount() == 0) {
			int distancex = 25, distancey = 10; //(int)(character.GetWidth()*0.3);
			boolean stop = true, change = true;
			
			if(LeegFighter2.keyboard[key[LEFT]]) {
				stop = false;
				SetMoveXY(-distancex, 0);
				if(change) this.SetPicture(0, (pic_y+1)%6);
				change = false;
			}
			if(LeegFighter2.keyboard[key[RIGHT]]) {
				stop = false;
				SetMoveXY(distancex, 0);
				if(change) this.SetPicture(1, (pic_y+1)%6);
				change = false;
			}
			if(LeegFighter2.keyboard[key[UP]]) {
				stop = false;
				SetMoveXY(0, -distancey);
				if(change) this.SetPicture(pic_x, (pic_y+1)%6);
				change = false;
			}
			if(LeegFighter2.keyboard[key[DOWN]]) {
				stop = false;
				SetMoveXY(0, distancey);
				if(change) this.SetPicture(pic_x, (pic_y+1)%6);
				change = false;
			}
			if(stop)
				this.SetPicture(pic_x, 6);
		}
		if(character.GetSkill().GetCount() > 0) character.GetSkill().NextAnimation();
		TCPCM.GetInstance().send("position "+no+" "+pos_x+" "+pos_y+" "+pic_x+" "+pic_y);
		TCPCM.GetInstance().send("hp "+no+" "+character.GetHp());
		if(character.GetSkill().IsEnd()) character.GetSkill().ToWalk();
	}
	
	private void SetMoveXY(int dx, int dy) {				
		if(CanMoveTo(pos_x+dx, pos_y+dy)) {
			int w = SceneDataModule.GetInstance().getWidth()*Tile.TILEWIDTH, h = SceneDataModule.GetInstance().getHeight()*Tile.TILEHEIGHT;
			if(pos_x+dx >= 0 && pos_x+dx <= w)
				pos_x += dx; 
			if(pos_y+dy >= picture.getHeight()/2 && pos_y+dy <= h-picture.getHeight()/2-60)
				pos_y += dy;
		}
	}
	
	public int GetLabelHeight() { return (int)(label_h*character.GetScale()); }
	
	public void Print() { System.out.println("Player : "+no); }
	
	public boolean CanMoveTo(int go_x, int go_y) {
		int x_min = go_x-picture.getWidth()/4;
		int x_max = go_x+picture.getWidth()/4;
		int y = go_y+(int)(picture.getHeight()*0.4);
		for(int x = x_min; x < x_max; ++x)
			if(SceneRenderEngine.GetInstance().getTile(x/Tile.TILEWIDTH, y/Tile.TILEHEIGHT).isSolid())
				return false;
		return true;
	}
}