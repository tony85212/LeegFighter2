public class Skill {
	
	protected int animation_count = 0;
	protected int[] pic_num = new int[5], skill_num = new int[5], skill_count = new int[5];
	protected int pic_x = 0;
	private int num = 0, pic_y = 0;
	private Player player;
	private boolean is_end = false;
	
	protected static int range = 10;
	
	private sound attack = new sound();
	
	public int GetCount() { return animation_count; }
	
	public boolean IsEnd() { return is_end; }
	
	public void SetAct(int clientno, int n) {
		if(skill_count[n] == 0) {
			attack.play("asset/sound/attack.wav");
			num = n;
			animation_count = pic_num[num];
			skill_count[n] = skill_num[num]*10;
			if(animation_count > 0) {
				player = DOM.GetInstance().GetPlayer(clientno);
				pic_x = player.GetPicX()+(num*2);
				pic_y = pic_num[num]-1;
			}
			this.SetEffect(clientno, n);
		}
	}
	
	public void SetEffect(int clientno, int n) {}
	
	public void NextAnimation() {
		animation_count -= 1;
		player.SetPicture(pic_x, pic_y=(pic_y+1)%pic_num[num]);
		if(animation_count == 0)
			is_end = true;
	}
	
	public void ReduceCD() {
		for(int i = 1; i <= 4; ++i) {
			if(skill_count[i] > 0)
				skill_count[i] -= 1;
		}
	}
	
	public void ToWalk() {
		player.SetDirection(pic_x-(num*2));
		is_end = false;
	}
	
	public int GetCD(int n) { return skill_count[n]; }
}