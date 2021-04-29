public class SceneDataModule {
	private int width,height;
	private int spwanX,spwanY;
	private int[][] tiles;
	
	private final static SceneDataModule Instance = new SceneDataModule();
	
	public static SceneDataModule GetInstance(){return Instance;}

	private SceneDataModule(){}
	
	public void loadMap(String path){
		assert path != null;
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");  //put each char to array
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spwanX = Utils.parseInt(tokens[2]);
		spwanY = Utils.parseInt(tokens[3]);
		
		if(width < 0) System.out.println("MapInput Error ! [MapWidth] can't be negative !");
		if(height < 0) System.out.println("MapInput Error ! [MapHeight] can't be negative !");
		if(spwanX < 0) System.out.println("MapInput Error ! [spwanX] can't be negative !");
		if(spwanY < 0) System.out.println("MapInput Error ! [spwanY] can't be negative !");
			
		tiles = new int [width][height];
		for(int y=0; y<height; y++)
		{
			for(int x=0; x<width; x++)
			{
				tiles[x][y] = Utils.parseInt(tokens[(x + y*width) + 4]);
			}
		}
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public int[][] getTiles(){
		return tiles;
	}
}
