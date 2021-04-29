public class People3_Skill extends Skill {
	
	public People3_Skill() {
		pic_num[1] = 2;
		pic_num[2] = 0;
		pic_num[3] = 0;
		pic_num[4] = 0;
		skill_num[1] = 0;
		skill_num[2] = 3;
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
			if(p.GetPicX() == 0)
				p.SetLocation((int)p.GetXY().getX()-200, (int)p.GetXY().getY());
			if(p.GetPicX() == 1)
				p.SetLocation((int)p.GetXY().getX()+200, (int)p.GetXY().getY());
			TCPCM.GetInstance().send("position "+clientno+" "+(int)p.GetXY().getX()+" "+(int)p.GetXY().getY()+" "+p.GetPicX()+" "+p.GetPicY());
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
			if((enemy.GetPicX() == 2 && enemy_x-my_x > 0 && enemy_x-my_x < 45*sc) ||
						(enemy.GetPicX() == 3 && my_x-enemy_x > 0 && my_x-enemy_x < 45*sc)) {
				//check Y
				if(Math.abs(my_y+my.GetCharacter().GetErrorY()-enemy_y-enemy.GetCharacter().GetErrorY()) <= range*sc)
					return enemy.GetCharacter().GetAttack();
			}
		}
		return 0;
	}
}