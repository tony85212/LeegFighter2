import java.awt.image.BufferedImage;

public class Assets {
	
	private static int def_int= 50;
	private static final int width = def_int, height = def_int;
	private static int offset = def_int;
	
	public static BufferedImage grass1, grass2, grass3, grass4, grass5, grass6, grass7, grass8, grass9;
	public static BufferedImage carpet1, carpet2, carpet3, carpet4, carpet5, carpet6, carpet7, carpet8, carpet9;
	public static BufferedImage grassWall1, grassWall2, grassWall3 , grassWall4, grassWall5, grassWall6, grassWall7, grassWall8, grassWall9;
	public static BufferedImage grassWallS1, grassWallS2, grassWallS3, grassWallS4, grassWallS5, grassWallS6, grassWallS7, grassWallS8, grassWallS9;
	public static BufferedImage floor1, floor2, floor3, floor4, floor5, floor6, floor7, floor8, floor9;
	public static BufferedImage fence1, fence2, fence3, fence4, fence6, fence7, fence8, fence9;
	public static BufferedImage wall1, wall2, wall3, wall4, wall5, wall6, wall7, wall8, wall9;
	public static BufferedImage wallS1, wallS2, wallS3, wallS4, wallS5, wallS6, wallS7, wallS8, wallS9;
	public static BufferedImage wallT2_1, wallT2_2;
	public static BufferedImage rock;
	public static BufferedImage floorT1_1, floorT1_2;
	
	public static void init(){
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("asset/map/textures/Tile50.png"));
		
		grass1 = sheet.block(offset*5, offset, width, height);
		grass2 = sheet.block(offset*6, offset, width, height);
		grass3 = sheet.block(offset*7, offset, width, height);
		grass4 = sheet.block(offset*5, offset*2, width, height);
		grass5 = sheet.block(offset*6, offset*2, width, height);
		grass6 = sheet.block(offset*7, offset*2, width, height);
		grass7 = sheet.block(offset*5, offset*3, width, height);
		grass8 = sheet.block(offset*6, offset*3, width, height);
		grass9 = sheet.block(offset*7, offset*3, width, height);
		
		grassWall1 = sheet.block(offset*21, offset*9, width, height);
		grassWall2 = sheet.block(offset*22, offset*9, width, height);
		grassWall3 = sheet.block(offset*23, offset*9, width, height);
		grassWall4 = sheet.block(offset*21, offset*10, width, height);
		grassWall5 = sheet.block(offset*22, offset*10, width, height);
		grassWall6 = sheet.block(offset*23, offset*10, width, height);
		grassWall7 = sheet.block(offset*21, offset*11, width, height);
		grassWall8 = sheet.block(offset*22, offset*11, width, height);
		grassWall9 = sheet.block(offset*23, offset*11, width, height);
		
		grassWallS1 = sheet.block(offset*21, offset*12, width, height);
		grassWallS2 = sheet.block(offset*22, offset*12, width, height);
		grassWallS3 = sheet.block(offset*23, offset*12, width, height);
		grassWallS4 = sheet.block(offset*21, offset*13, width, height);
		grassWallS5 = sheet.block(offset*22, offset*13, width, height);
		grassWallS6 = sheet.block(offset*23, offset*13, width, height);
		grassWallS7 = sheet.block(offset*21, offset*14, width, height);
		grassWallS8 = sheet.block(offset*22, offset*14, width, height);
		grassWallS9 = sheet.block(offset*23, offset*14, width, height);
		
		floor1 = sheet.block(offset*13, offset*1, width, height);
		floor2 = sheet.block(offset*14, offset*1, width, height);
		floor3 = sheet.block(offset*15, offset*1, width, height);
		floor4 = sheet.block(offset*13, offset*2, width, height);
		floor5 = sheet.block(offset*14, offset*2, width, height);
		floor6 = sheet.block(offset*15, offset*2, width, height);
		floor7 = sheet.block(offset*13, offset*3, width, height);
		floor8 = sheet.block(offset*14, offset*3, width, height);
		floor9 = sheet.block(offset*15, offset*3, width, height);
		
		fence1 = sheet.block(offset*17, offset*1, width, height);
		fence2 = sheet.block(offset*18, offset*1, width, height);
		fence3 = sheet.block(offset*19, offset*1, width, height);
		fence4 = sheet.block(offset*17, offset*2, width, height);
		fence6 = sheet.block(offset*19, offset*2, width, height);
		fence7 = sheet.block(offset*17, offset*3, width, height);
		fence8 = sheet.block(offset*18, offset*3, width, height);
		fence9 = sheet.block(offset*19, offset*3, width, height);
		
		wall1 = sheet.block(offset*25, offset*9, width, height);
		wall2 = sheet.block(offset*26, offset*9, width, height);
		wall3 = sheet.block(offset*27, offset*9, width, height);
		wall4 = sheet.block(offset*25, offset*10, width, height);
		wall5 = sheet.block(offset*26, offset*10, width, height);
		wall6 = sheet.block(offset*27, offset*10, width, height);
		wall7 = sheet.block(offset*25, offset*11, width, height);
		wall8 = sheet.block(offset*26, offset*11, width, height);
		wall9 = sheet.block(offset*27, offset*11, width, height);
		
		wallS1 = sheet.block(offset*25, offset*12, width, height);
		wallS2 = sheet.block(offset*26, offset*12, width, height);
		wallS3 = sheet.block(offset*27, offset*12, width, height);
		wallS4 = sheet.block(offset*25, offset*13, width, height);
		wallS5 = sheet.block(offset*26, offset*13, width, height);
		wallS6 = sheet.block(offset*27, offset*13, width, height);
		wallS7 = sheet.block(offset*25, offset*14, width, height);
		wallS8 = sheet.block(offset*26, offset*14, width, height);
		wallS9 = sheet.block(offset*27, offset*14, width, height);
		
		
		floorT1_1 = sheet.block(offset*9, offset*12, width, height);
		floorT1_2 = sheet.block(offset*9, offset*13, width, height);
		
		rock = sheet.block(offset*14, offset*2, width, height);
		
		carpet1 = sheet.block(offset*17, offset*5, width, height);
		carpet2 = sheet.block(offset*18, offset*5, width, height);
		carpet3 = sheet.block(offset*19, offset*5, width, height);
		carpet4 = sheet.block(offset*17, offset*6, width, height);
		carpet5 = sheet.block(offset*18, offset*6, width, height);
		carpet6 = sheet.block(offset*19, offset*6, width, height);
		carpet7 = sheet.block(offset*17, offset*7, width, height);
		carpet8 = sheet.block(offset*18, offset*7, width, height);
		carpet9 = sheet.block(offset*19, offset*7, width, height);
		
		
	}
}
