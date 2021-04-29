public class People2_Skill extends Skill {
	
	public People2_Skill() {
		pic_num[1] = 2;
		pic_num[2] = 4;
		pic_num[3] = 0;
		pic_num[4] = 0;
		skill_num[1] = 0;
		skill_num[2] = 5;
		skill_num[3] = 0;
		skill_num[4] = 0;
		
		animation_count = 0;
		skill_count[1] = 0;
		skill_count[2] = 0;
		skill_count[3] = 0;
		skill_count[4] = 0;
	}
	
	//enemy attack me
	public static int GetHurt(int my_no, int enemy_no) {
		Player my = DOM.GetInstance().GetPlayer(my_no), enemy = DOM.GetInstance().GetPlayer(enemy_no);
		int my_x = (int)my.GetXY().getX(), my_y = (int)my.GetXY().getY(),
			enemy_x = (int)enemy.GetXY().getX(), enemy_y = (int)enemy.GetXY().getY();
		double sc = enemy.GetCharacter().GetScale();
		
		//skill 1-left
		if(enemy.GetPicX() == 2 && enemy.GetPicY() == 1 && enemy_x-my_x > 0 && enemy_x-my_x < 50*sc) {
			if(Math.abs(my_y+my.GetCharacter().GetErrorY()-enemy_y-enemy.GetCharacter().GetErrorY()) <= range*sc)
				return enemy.GetCharacter().GetAttack();
		}
		//skill 1-right
		if(enemy.GetPicX() == 3 && enemy.GetPicY() == 1 && my_x-enemy_x > 0 && my_x-enemy_x < 50*sc) {
			if(Math.abs(my_y+my.GetCharacter().GetErrorY()-enemy_y-enemy.GetCharacter().GetErrorY()) <= range*sc)
				return enemy.GetCharacter().GetAttack();
		}
		//skill 2-left
		if(enemy.GetPicX() == 4 && enemy.GetPicY() == 3 && enemy_x-my_x > 0 && enemy_x-my_x < 50*sc) {
			if(Math.abs(my_y+my.GetCharacter().GetErrorY()-enemy_y-enemy.GetCharacter().GetErrorY()) <= range*sc)
				return enemy.GetCharacter().GetAttack()*3;
		}
		//skill 2-left
		if(enemy.GetPicX() == 5 && enemy.GetPicY() == 3 && my_x-enemy_x > 0 && my_x-enemy_x < 50*sc) {
			if(Math.abs(my_y+my.GetCharacter().GetErrorY()-enemy_y-enemy.GetCharacter().GetErrorY()) <= range*sc)
				return enemy.GetCharacter().GetAttack()*3;
		}
		return 0;
	}
}