import java.awt.Canvas;
import java.awt.Graphics;

public class SceneRenderEngine {
	
	private GameCamera gameCamera;
	private int mapWidth, mapHeight;
	private int framWidth = LeegFighter2.width, framHeight = LeegFighter2.height;
	private int spwanX, spwanY;
	private int[][] tiles;
	
	public final static SceneRenderEngine Instance = new SceneRenderEngine();
	
	public static SceneRenderEngine GetInstance(){return Instance;}
	
	private SceneRenderEngine(){
		SceneDataModule SDM = SceneDataModule.GetInstance();
		this.tiles = SDM.getTiles();
		this.mapWidth = SDM.getWidth();
		this.mapHeight = SDM.getHeight();
		this.gameCamera = new GameCamera(SDM.getTiles(), 0, 0, framWidth, framHeight);
	}
	
	public void update(){
		
	}
	
	public void renderScene(Graphics g, float CharX, float CharY){
		
		gameCamera.centerOnPoint(CharX, CharY);
		int xStart = (int)Math.max(0, gameCamera.getOffsetX() / Tile.TILEWIDTH);
		int xEnd = (int)Math.min(mapWidth, (gameCamera.getOffsetX() + framWidth) / Tile.TILEWIDTH +1);
		int yStart = (int)Math.max(0, gameCamera.getOffsetY() / Tile.TILEHEIGHT);
		int yEnd = (int)Math.min(mapHeight, ( gameCamera.getOffsetY() + framHeight) / Tile.TILEHEIGHT+1);
		for(int y= yStart; y < yEnd; y++)
		{
			for(int x = xStart; x < xEnd; x++)
			{
				getTile(x,y).render(g, (int) (x*Tile.TILEWIDTH - gameCamera.getOffsetX()), 
										(int) (y*Tile.TILEHEIGHT - gameCamera.getOffsetY()));
			}
		}
	}
	
	public Tile getTile(int x, int y){
		if(x<0||y<0||x>=mapWidth||y>=mapHeight)
			return Tile.grass1;
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null)
			return Tile.grass1;
		return t;
	}
	
	public int getWidth(){
		return mapWidth;
	}
	
	public int getHeight(){
		return mapHeight;
	}
	
	public GameCamera GetGameCamera() { return gameCamera; }
}
