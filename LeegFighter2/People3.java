public class People3 extends Character {
	
	//³z©ú¤H
	
	public People3() {
		this(2);
	}
	
	public People3(double s) {
		super(s, "asset/character/people3/");
		id = 3;
		hp = 51;
		attack = 10;
		speed = 2;
		skill = new People3_Skill();
	}
}