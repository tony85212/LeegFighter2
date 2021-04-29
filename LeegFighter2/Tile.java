import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	//STATIC STUFF
	
	public static Tile[] tiles = new Tile[256];
	
	public static Tile grass1 = new Grass1(1);
	public static Tile grass2 = new Grass2(2);
	public static Tile grass3 = new Grass3(3);
	public static Tile grass4 = new Grass4(4);
	public static Tile grass5 = new Grass5(5);
	public static Tile grass6 = new Grass6(6);
	public static Tile grass7 = new Grass7(7);
	public static Tile grass8 = new Grass8(8);
	public static Tile grass9 = new Grass9(9);
	
	public static Tile grassWall1 = new GrassWall1(11);
	public static Tile grassWall2 = new GrassWall2(12);
	public static Tile grassWall3 = new GrassWall3(13);
	public static Tile grassWall4 = new GrassWall4(14);
	public static Tile grassWall5 = new GrassWall5(15);
	public static Tile grassWall6 = new GrassWall6(16);
	public static Tile grassWall7 = new GrassWall7(17);
	public static Tile grassWall8 = new GrassWall8(18);
	public static Tile grassWall9 = new GrassWall9(19);
	
	public static Tile grassWallS1 = new GrassWallS1(21);
	public static Tile grassWallS2 = new GrassWallS2(22);
	public static Tile grassWallS3 = new GrassWallS3(23);
	public static Tile grassWallS4 = new GrassWallS4(24);
	public static Tile grassWallS5 = new GrassWallS5(25);
	public static Tile grassWallS6 = new GrassWallS6(26);
	public static Tile grassWallS7 = new GrassWallS7(27);
	public static Tile grassWallS8 = new GrassWallS8(28);
	public static Tile grassWallS9 = new GrassWallS9(29);
	
	public static Tile floor1 = new Floor1(31);
	public static Tile floor2 = new Floor2(32);
	public static Tile floor3 = new Floor3(33);
	public static Tile floor4 = new Floor4(34);
	public static Tile floor5 = new Floor5(35);
	public static Tile floor6 = new Floor6(36);
	public static Tile floor7 = new Floor7(37);
	public static Tile floor8 = new Floor8(38);
	public static Tile floor9 = new Floor9(39);
	
	public static Tile fence1 = new Fence1(41);
	public static Tile fence2 = new Fence2(42);
	public static Tile fence3 = new Fence3(43);
	public static Tile fence4 = new Fence4(44);
	public static Tile fence6 = new Fence6(46);
	public static Tile fence7 = new Fence7(47);
	public static Tile fence8 = new Fence8(48);
	public static Tile fence9 = new Fence9(49);
	
	public static Tile wall1 = new Wall1(51);
	public static Tile wall2 = new Wall2(52);
	public static Tile wall3 = new Wall3(53);
	public static Tile wall4 = new Wall4(54);
	public static Tile wall5 = new Wall5(55);
	public static Tile wall6 = new Wall6(56);
	public static Tile wall7 = new Wall7(57);
	public static Tile wall8 = new Wall8(58);
	public static Tile wall9 = new Wall9(59);
	
	public static Tile wallS1 = new WallS1(61);
	public static Tile wallS2 = new WallS2(62);
	public static Tile wallS3 = new WallS3(63);
	public static Tile wallS4 = new WallS4(64);
	public static Tile wallS5 = new WallS5(65);
	public static Tile wallS6 = new WallS6(66);
	public static Tile wallS7 = new WallS7(67);
	public static Tile wallS8 = new WallS8(68);
	public static Tile wallS9 = new WallS9(69);
	
	
	
	
	//CLASS
	public static final int TILEWIDTH = 50, TILEHEIGHT = 50;
	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture, int id){
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void update(){
		
	}
	
	public void render(Graphics g, int x, int y){
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT,null);
	}
	
	public boolean isSolid(){
		return false;
	}
	
	public int getId(){
		return id;
	}
}

//#############################################################################################//

class Grass1 extends Tile{ public Grass1(int id) { super(Assets.grass1, id); } }
class Grass2 extends Tile{ public Grass2(int id) { super(Assets.grass2, id); } }
class Grass3 extends Tile{ public Grass3(int id) { super(Assets.grass3, id); } }
class Grass4 extends Tile{ public Grass4(int id) { super(Assets.grass4, id); } }
class Grass5 extends Tile{ public Grass5(int id) { super(Assets.grass5, id); } }
class Grass6 extends Tile{ public Grass6(int id) { super(Assets.grass6, id); } }
class Grass7 extends Tile{ public Grass7(int id) { super(Assets.grass7, id); } }
class Grass8 extends Tile{ public Grass8(int id) { super(Assets.grass8, id); } }
class Grass9 extends Tile{ public Grass9(int id) { super(Assets.grass9, id); } }

class GrassWall1 extends Tile{ public GrassWall1(int id) { super(Assets.grassWall1, id); } public boolean isSolid(){ return true; }}
class GrassWall2 extends Tile{ public GrassWall2(int id) { super(Assets.grassWall2, id); } public boolean isSolid(){ return true; }}
class GrassWall3 extends Tile{ public GrassWall3(int id) { super(Assets.grassWall3, id); } public boolean isSolid(){ return true; }}
class GrassWall4 extends Tile{ public GrassWall4(int id) { super(Assets.grassWall4, id); } public boolean isSolid(){ return true; }}
class GrassWall5 extends Tile{ public GrassWall5(int id) { super(Assets.grassWall5, id); } public boolean isSolid(){ return true; }}
class GrassWall6 extends Tile{ public GrassWall6(int id) { super(Assets.grassWall6, id); } public boolean isSolid(){ return true; }}
class GrassWall7 extends Tile{ public GrassWall7(int id) { super(Assets.grassWall7, id); } public boolean isSolid(){ return true; }}
class GrassWall8 extends Tile{ public GrassWall8(int id) { super(Assets.grassWall8, id); } public boolean isSolid(){ return true; }}
class GrassWall9 extends Tile{ public GrassWall9(int id) { super(Assets.grassWall9, id); } public boolean isSolid(){ return true; }}

class GrassWallS1 extends Tile{ public GrassWallS1(int id) { super(Assets.grassWallS1, id); } public boolean isSolid(){ return true; }}
class GrassWallS2 extends Tile{ public GrassWallS2(int id) { super(Assets.grassWallS2, id); } public boolean isSolid(){ return true; }}
class GrassWallS3 extends Tile{ public GrassWallS3(int id) { super(Assets.grassWallS3, id); } public boolean isSolid(){ return true; }}
class GrassWallS4 extends Tile{ public GrassWallS4(int id) { super(Assets.grassWallS4, id); } public boolean isSolid(){ return true; }}
class GrassWallS5 extends Tile{ public GrassWallS5(int id) { super(Assets.grassWallS5, id); } public boolean isSolid(){ return true; }}
class GrassWallS6 extends Tile{ public GrassWallS6(int id) { super(Assets.grassWallS6, id); } public boolean isSolid(){ return true; }}
class GrassWallS7 extends Tile{ public GrassWallS7(int id) { super(Assets.grassWallS7, id); } public boolean isSolid(){ return true; }}
class GrassWallS8 extends Tile{ public GrassWallS8(int id) { super(Assets.grassWallS8, id); } public boolean isSolid(){ return true; }}
class GrassWallS9 extends Tile{ public GrassWallS9(int id) { super(Assets.grassWallS9, id); } public boolean isSolid(){ return true; }}

class Floor1 extends Tile{ public Floor1(int id) { super(Assets.floor1, id); } }
class Floor2 extends Tile{ public Floor2(int id) { super(Assets.floor2, id); } }
class Floor3 extends Tile{ public Floor3(int id) { super(Assets.floor3, id); } }
class Floor4 extends Tile{ public Floor4(int id) { super(Assets.floor4, id); } }
class Floor5 extends Tile{ public Floor5(int id) { super(Assets.floor5, id); } }
class Floor6 extends Tile{ public Floor6(int id) { super(Assets.floor6, id); } }
class Floor7 extends Tile{ public Floor7(int id) { super(Assets.floor7, id); } }
class Floor8 extends Tile{ public Floor8(int id) { super(Assets.floor8, id); } }
class Floor9 extends Tile{ public Floor9(int id) { super(Assets.floor9, id); } }

class Fence1 extends Tile{ public Fence1(int id) { super(Assets.fence1, id); } public boolean isSolid(){ return true; }}
class Fence2 extends Tile{ public Fence2(int id) { super(Assets.fence2, id); } public boolean isSolid(){ return true; }}
class Fence3 extends Tile{ public Fence3(int id) { super(Assets.fence3, id); } public boolean isSolid(){ return true; }}
class Fence4 extends Tile{ public Fence4(int id) { super(Assets.fence4, id); } public boolean isSolid(){ return true; }}
class Fence6 extends Tile{ public Fence6(int id) { super(Assets.fence6, id); } public boolean isSolid(){ return true; }}
class Fence7 extends Tile{ public Fence7(int id) { super(Assets.fence7, id); } public boolean isSolid(){ return true; }}
class Fence8 extends Tile{ public Fence8(int id) { super(Assets.fence8, id); } public boolean isSolid(){ return true; }}
class Fence9 extends Tile{ public Fence9(int id) { super(Assets.fence9, id); } public boolean isSolid(){ return true; }}

class Wall1 extends Tile{ public Wall1(int id) { super(Assets.wall1, id); } public boolean isSolid(){ return true; }}
class Wall2 extends Tile{ public Wall2(int id) { super(Assets.wall2, id); } public boolean isSolid(){ return true; }}
class Wall3 extends Tile{ public Wall3(int id) { super(Assets.wall3, id); } public boolean isSolid(){ return true; }}
class Wall4 extends Tile{ public Wall4(int id) { super(Assets.wall4, id); } public boolean isSolid(){ return true; }}
class Wall5 extends Tile{ public Wall5(int id) { super(Assets.wall5, id); } public boolean isSolid(){ return true; }}
class Wall6 extends Tile{ public Wall6(int id) { super(Assets.wall6, id); } public boolean isSolid(){ return true; }}
class Wall7 extends Tile{ public Wall7(int id) { super(Assets.wall7, id); } public boolean isSolid(){ return true; }}
class Wall8 extends Tile{ public Wall8(int id) { super(Assets.wall8, id); } public boolean isSolid(){ return true; }}
class Wall9 extends Tile{ public Wall9(int id) { super(Assets.wall9, id); } public boolean isSolid(){ return true; }}

class WallS1 extends Tile{ public WallS1(int id) { super(Assets.wallS1, id); } public boolean isSolid(){ return true; }}
class WallS2 extends Tile{ public WallS2(int id) { super(Assets.wallS2, id); } public boolean isSolid(){ return true; }}
class WallS3 extends Tile{ public WallS3(int id) { super(Assets.wallS3, id); } public boolean isSolid(){ return true; }}
class WallS4 extends Tile{ public WallS4(int id) { super(Assets.wallS4, id); } public boolean isSolid(){ return true; }}
class WallS5 extends Tile{ public WallS5(int id) { super(Assets.wallS5, id); } public boolean isSolid(){ return true; }}
class WallS6 extends Tile{ public WallS6(int id) { super(Assets.wallS6, id); } public boolean isSolid(){ return true; }}
class WallS7 extends Tile{ public WallS7(int id) { super(Assets.wallS7, id); } public boolean isSolid(){ return true; }}
class WallS8 extends Tile{ public WallS8(int id) { super(Assets.wallS8, id); } public boolean isSolid(){ return true; }}
class WallS9 extends Tile{ public WallS9(int id) { super(Assets.wallS9, id); } public boolean isSolid(){ return true; }}

