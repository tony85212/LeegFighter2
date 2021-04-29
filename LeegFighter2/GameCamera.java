import java.awt.Canvas;

public class GameCamera {
	
	private float offsetX, offsetY;
	private int[][] tiles;
	private int mapWidth,mapHeight;
	private int framWidth,framHeight;
	
	public GameCamera(int[][] tiles, float offsetX, float offsetY, int framWidth, int framHeight){
		this.tiles = tiles;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.mapWidth = tiles.length;
		this.mapHeight = tiles[0].length;
		this.framWidth = framWidth;
		this.framHeight = framHeight;
	}
	
	public void checkBlankSpace(){
		if(offsetX < 0){
			offsetX = 0;
		}else if( offsetX > mapWidth*Tile.TILEWIDTH - framWidth){
			offsetX = mapWidth * Tile.TILEWIDTH - framWidth;
		}
		if(offsetY < 0){
			offsetY = 0;
		}else if( offsetY > mapHeight * Tile.TILEHEIGHT - framHeight){
			offsetY = mapHeight * Tile.TILEHEIGHT - framHeight;
		}
	}
	
	/*public void centerOnEntity(Entity e){
		offsetX = e.getX() - display.getWidth()/2 + e.getWidth()/2;
		offsetY = e.getY() - display.getHeight()/2 + e.getHeight()/2;
		checkBlankSpace();
	}*/
	
	public void centerOnPoint(float CharX, float CharY){
		offsetX = CharX - framWidth/2;
		offsetY = CharY - framHeight/2;
		checkBlankSpace();
	}
	
	
	public void move(float xAmt, float yAmt){
		offsetX += xAmt;
		offsetY += yAmt;
		checkBlankSpace();
	}
	
	public float getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(float offsetX) {
		this.offsetX = offsetX;
	}

	public float getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(float offsetY) {
		this.offsetY = offsetY;
	}
}
