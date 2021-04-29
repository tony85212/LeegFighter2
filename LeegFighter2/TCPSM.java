
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;



import java.util.*;

public class TCPSM implements Runnable
{
	private final static TCPSM INSTANCE = new TCPSM();
    public static TCPSM GetInstance() { return INSTANCE; }
	
	private boolean running;
	private ServerSocket serverSocket;
	private Vector<TCPSM2> channels;
	private int ClientIndex = 1;
	private int player_size = 0;
	private boolean all_connect = true;
	
	private TCPSM() {}
	
	public void iniTCPSever() {
		try { serverSocket = new ServerSocket(1111); }
		catch(Exception e) {}
	}
	
	public void start() {
		Thread thread = new Thread(this);
		thread.start();
	}

	public void stop() {
		running = false;
		try { serverSocket.close(); }
		catch(Exception e) {}
		channels.clear();
		ClientIndex = 1;
		player_size = 0;
		TCPSM2.ready_num=0;
		all_connect=true;
	}
	
	@Override
	public void run()
	{
		channels = new Vector<>();
		running = true;
		while(running) {
			try {
				if(channels.size() < player_size) {
					Socket socket = serverSocket.accept();
					TCPSM2 channel = new TCPSM2(socket, this, ClientIndex);			
					channel.start();
					channels.add(channel);
					channels.get(ClientIndex-1).send("index "+ClientIndex+" "+player_size);
					ClientIndex ++;				
					System.out.println(channels.size());
				} else {
					if(all_connect) {
						broadcast("allconnect");
						all_connect = false;
					}
					if(TCPSM2.ready_num == player_size) {
						SelectCharacter.GetInstance().go_button.setEnabled(true);
					}
				}
			} catch (SocketException e) {
			} catch (IOException e) {}
		}
		
		try {
			for(TCPSM2 channel : channels)
				channel.stop();
			channels.clear();
		} catch(IOException e) { e.printStackTrace(); }
	}

	public void broadcast(String msg) {
		if(!running)
			return;
		for(TCPSM2 channel : channels)
			channel.send(msg);
	}
	
	public void SetRoomSize(int num) {
		player_size = num;
	}
}
