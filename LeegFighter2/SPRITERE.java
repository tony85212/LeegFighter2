import java.awt.image.BufferedImage;
import java.awt.Graphics;
import javax.swing.JLabel;

public class SPRITERE {
		
	private static SPRITERE INSTANCE = new SPRITERE(); //singleton
	
	private SPRITERE() {}
	
	public static SPRITERE GetInstance() { return INSTANCE; }
	
	public void renderSprites(Graphics g, JLabel[] hp_label) {
		for(DynamicObject obj : DOM.GetInstance().getAllDynamicObjects()) {
			Player p = DOM.GetInstance().GetPlayer(TCPCM.GetInstance().GetClientIndex()), p_ = DOM.GetInstance().GetPlayer(obj.GetNo());
			if(obj.IsItem() == false && p.GetCharacter().GetHp() != 0 && obj.GetNo() != TCPCM.GetInstance().GetClientIndex()) {
				int hurt = 0;
				int i = p_.GetCharacter().GetId();
				if(i == 1)
					hurt += People1_Skill.GetHurt(TCPCM.GetInstance().GetClientIndex(), obj.GetNo());
				else if(i == 2)
					hurt += People2_Skill.GetHurt(TCPCM.GetInstance().GetClientIndex(), obj.GetNo());
				else if(i == 3)
					hurt += People3_Skill.GetHurt(TCPCM.GetInstance().GetClientIndex(), obj.GetNo());
				else if(i == 4)
					hurt += People4_Skill.GetHurt(TCPCM.GetInstance().GetClientIndex(), obj.GetNo());
				if(hurt > 0) {
					System.out.println(hurt);
					p.SetHp(p.GetCharacter().GetHp()-hurt); //
				}
			}
			obj.draw(g);
			hp_label[obj.GetNo()-1].setText("  P"+obj.GetNo()+"   HP : "+p_.GetCharacter().GetHp());
			//obj.Print();
		}
	}
}