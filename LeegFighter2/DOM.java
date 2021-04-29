import java.util.List;
import java.util.Vector;
import java.awt.Point;

public class DOM {
	
	private final static DOM INSTANCE = new DOM(); //singleton
	private List<Player> players = new Vector<Player>();
	private List<Item> items = new Vector<Item>();
	
	private DOM() {}
	
	public static DOM GetInstance() { return INSTANCE; }
	
	public void Clear() {
		players.clear();
		items.clear();
	}
	
	public Player GetPlayer(int clientno) {
		for(Player p : players) {
			if(p.GetNo() == clientno)
				return p;
		}
		return null;
	}
	
	public Item GetItem(int index) {
		for(Item i : items) {
			if(i.GetNo() == index)
				return i;
		}
		return null;
	}
	
	public void addVirtualCharacter(int clientno) {
		boolean found = false;
		for(Player p : players) {
			if(p.GetNo() == clientno) {
				found = true;
				break;
			}
		}
		if(!found)
			players.add(new Player(clientno));
		else
			System.out.println("This clientno has been used.");
	}
	
	public void updateVirtualCharacter(int clientno, int character, int dir) {
		boolean found = false;
		for(Player p : players) {
			if(p.GetNo() == clientno) {
				found = true;
				p.CreatePeople(character, dir);
				break;
			}
		}
		if(!found)
			System.out.println("Can not find this clientno.");
	}
	
	public void updateVirtualCharacterPosition(int clientno, int pos_x, int pos_y, int pic_x, int pic_y) {
		if(pos_x < 0 || pos_y < 0) {
			System.out.println("Location can not be negative.");
			return;
		}
		boolean found = false;
		for(Player p : players) {
			if(p.GetNo() == clientno) {
				found = true;
				p.SetLocation(pos_x, pos_y);
				p.SetPicture(pic_x, pic_y);
				break;
			}
		}
		if(!found)
			System.out.println("Can not find this clientno.");
	}
	
	public void updateVirtualCharacterHp(int clientno, int hp) {
		boolean found = false;
		for(Player p : players) {
			if(p.GetNo() == clientno) {
				found = true;
				p.SetHp(hp);
				break;
			}
		}
		if(!found)
			System.out.println("Can not find this clientno.");
	}
	
	public void updateVirtualCharacterAttack(int clientno, int attack) {
		boolean found = false;
		for(Player p : players) {
			if(p.GetNo() == clientno) {
				found = true;
				p.SetAttack(attack);
				break;
			}
		}
		if(!found)
			System.out.println("Can not find this clientno.");
	}
	
	/*
	public void addItem(Item item) {
		items.add(item);
	}
	
	public void removeItem(int index) {
		for(Item i : items) {
			if(i.GetNo() == index)
				items.remove(i);
		}
	}
	*/
	
	public Vector<DynamicObject> getAllDynamicObjects(){
		Vector<DynamicObject> Objects = new Vector<DynamicObject>();
		for(DynamicObject obj : players)
			Objects.add(obj);
		for(DynamicObject obj : items)
			Objects.add(obj);
		return Objects;
	}
	/*
	public Vector<DynamicObject> getAllVirtualCharacter(){
		Vector<DynamicObject> Objects = new Vector<DynamicObject>();
		for(DynamicObject obj : players)
			Objects.add(obj);
		return Objects;
	}
	*/
	public Point getVirtualCharacterXY(){
		for(Player p : players) {
			if(p.GetNo() == TCPCM.GetInstance().GetClientIndex())
				return p.GetXY();
		}
		System.out.println("Not found your virtual character!");
		return null;
	}
	
	public void keyGETPressed(){
		for(Player p : players) {
			if(p.GetNo() == TCPCM.GetInstance().GetClientIndex())
				p.Act();
		}
	}
	
	public int GetCharacterSize() { return players.size(); }
	
	public boolean IsGameOver() {
		int i = 0;
		for(Player p : players) {
			if(p.GetHp() > 0)
				i += 1;
		}
		return (i < 2);
	}
}