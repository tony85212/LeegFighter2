public class People4_Skill extends Skill {
	
	private Item fire_left;
	
	public People4_Skill() {
		pic_num[1] = 2;
		pic_num[2] = 2;
		pic_num[3] = 0;
		pic_num[4] = 0;
		skill_num[1] = 0;
		skill_num[2] = 10;
		skill_num[3] = 0;
		skill_num[4] = 0;
		
		animation_count = 0;
		skill_count[1] = 0;
		skill_count[2] = 0;
		skill_count[3] = 0;
		skill_count[4] = 0;
	}
	
	public void SetEffect(int clientno, int n) {
		Player p = DOM.GetInstance().GetPlayer(clientno);
		if(n == 2) {
			if(p.GetCharacter().GetHp() < 80)
				p.GetCharacter().SetHp(p.GetCharacter().GetHp() < 60 ? p.GetCharacter().GetHp()+20 : 80);
			TCPCM.GetInstance().send("hp "+clientno+" "+p.GetCharacter().GetHp());
		}
	}
	
	//enemy attack me
	public static int GetHurt(int my_no, int enemy_no) {
		Player my = DOM.GetInstance().GetPlayer(my_no), enemy = DOM.GetInstance().GetPlayer(enemy_no);
		int my_x = (int)my.GetXY().getX(), my_y = (int)my.GetXY().getY(),
			enemy_x = (int)enemy.GetXY().getX(), enemy_y = (int)enemy.GetXY().getY();
		double sc = enemy.GetCharacter().GetScale();
		if(enemy.GetPicY() == 1) {
			//check dir & X
			if((enemy.GetPicX() == 2 && enemy_x-my_x > 0 && enemy_x-my_x < 55*sc) ||
						(enemy.GetPicX() == 3 && my_x-enemy_x > 0 && my_x-enemy_x < 55*sc)) {
				//check Y
				if(Math.abs(my_y+my.GetCharacter().GetErrorY()-enemy_y-enemy.GetCharacter().GetErrorY()) <= range*sc)
					return enemy.GetCharacter().GetAttack();
			}
		}
		return 0;
	}
}