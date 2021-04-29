import java.awt.event.*;
import java.awt.*;
import java.net.URL;
import javax.swing.*;
import java.net.*;

import javax.imageio.ImageIO;

import java.io.*;
import java.awt.image.BufferedImage;

public class Connect extends JLabel
{

    private final static Connect INSTANCE = new Connect();
    public static Connect GetInstance() { return INSTANCE; }
	private TCPSM server = null;
    private TCPCM client = null;
	private JLabel ip_label = new JLabel("IP");
    private JButton create_button = new JButton(), connect_button = new JButton(), back_button = new JButton();
	private ImageIcon connect = null;
	private ImageIcon create = null;
	private ImageIcon back = null;
	
	private sound click = new sound();

    private Connect ()
    {
        setBounds(0, 0, LeegFighter2.width, LeegFighter2.height);
        try
        {
            BufferedImage img=ImageIO.read(new File("asset/Networking/Networking_BG.png"));
            this.setIcon(new ImageIcon(img));
        }
        catch(Exception e) {}


        try
        {
            ip_label = new JLabel("IP : "+getIp(), SwingConstants.CENTER);
            ip_label.setFont(new Font("華康斧劈體W7", Font.PLAIN, 18));
			ip_label.setForeground(Color.WHITE);
            ip_label.setBounds(475, 350, 200, 50);
            add(ip_label);
        }
        catch(Exception e) {}

		
		BufferedImage bic;
		try{
			bic = ImageIO.read(new File("asset/Networking/Networking_create.png"));
			create = new ImageIcon(bic.getScaledInstance(142, 50, Image.SCALE_SMOOTH));
		} catch(Exception e){}
		create_button.setIcon(create);
        create_button.setBounds(500, 400, 142, 50);
        create_button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
				click.play("asset/sound/click.wav");
                JTextField input_size = new JTextField();

                JPanel myPanel =new JPanel();
                myPanel.add(input_size);
                myPanel.setLayout(new GridLayout(1,1));
                int result = JOptionPane.showConfirmDialog(null, myPanel,"請輸入人數", JOptionPane.OK_CANCEL_OPTION);
				int room_size=Integer.valueOf(input_size.getText());//房間人數
				while(result==0&&(room_size<=0||room_size>=5))
				{
					result = JOptionPane.showConfirmDialog(null, myPanel,"請輸入人數", JOptionPane.OK_CANCEL_OPTION);
					room_size=Integer.valueOf(input_size.getText());
				}
                if(result==0)
                {
                    
                    try
                    {
						server = TCPSM.GetInstance();
						server.SetRoomSize(room_size);
                        server.iniTCPSever();

                        server.start(); // Start Accept Thread
                        System.out.println("Server has started.");
                        
                    }
                    catch(Exception e) {
						System.out.println("CANNOT START SERVER!	");
					}

                    try
                    {
                        String str = "127.0.0.1";
                        InetAddress ip = InetAddress.getByName(str);
						
						client = TCPCM.GetInstance();
                        if(client.connectSever(ip))
                            System.out.println("Connected");
                        else
                            System.out.println("fail");

                        client.start();
						SelectCharacter.GetInstance().Reset();
                        LeegFighter2.GetInstance().ChangeLabel(SelectCharacter.GetInstance());
                    }
                    catch(Exception e) {}
                }
                else
                {}
            }
        });
        add(create_button);

		try{
			bic = ImageIO.read(new File("asset/Networking/Networking_connect.png"));
			connect = new ImageIcon(bic.getScaledInstance(142, 50, Image.SCALE_SMOOTH));
		} catch(Exception e){}
		connect_button.setIcon(connect);
        connect_button.setBounds(500, 475, 142, 50);
   
        connect_button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
				click.play("asset/sound/click.wav");
                try
                {
                    JTextField input_ip = new JTextField();

                    JPanel myPanel = new JPanel();
                    myPanel.setLayout(new GridLayout(1,1));
                    myPanel.add(input_ip);

                    int result = JOptionPane.showConfirmDialog(null, myPanel,"請輸入IP", JOptionPane.OK_CANCEL_OPTION);
                    if(result==0)
                    {
                        InetAddress ip = InetAddress.getByName(input_ip.getText());
						
						client = TCPCM.GetInstance();
                        if(client.connectSever(ip))
                            System.out.println("Connected");
                        else
                            System.out.println("fail");

                        client.start();
                        LeegFighter2.GetInstance().ChangeLabel(SelectCharacter.GetInstance());
                    }
                    else
                    {}
                }
                catch(Exception e) {}
            }
        });
        add(connect_button);

		try{
			bic = ImageIO.read(new File("asset/Networking/Networking_back.png"));
			connect = new ImageIcon(bic.getScaledInstance(142, 50, Image.SCALE_SMOOTH));
		} catch(Exception e){}
		back_button.setIcon(connect);
        back_button.setBounds(500, 550, 142, 50);
        back_button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
				click.play("asset/sound/click.wav");
                LeegFighter2.GetInstance().ChangeLabel(HomeScreen.GetInstance());
            }
        });
        add(back_button);
    }

    public static String getIp()throws Exception
    {
        /*URL whatismyip;
        BufferedReader in = null;
        try {
        	whatismyip = new URL("http://checkip.amazonaws.com");
            in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));
            String ip = in.readLine();
            return ip;
        } catch(Exception e) {
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Not Found!";*/
        return InetAddress.getLocalHost().getHostAddress().toString();
    }
	
	public boolean IsServer() { return server!=null; }
}
