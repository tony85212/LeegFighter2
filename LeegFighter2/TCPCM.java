import java.net.InetAddress;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TCPCM implements Runnable
{
	private final static TCPCM INSTANCE = new TCPCM();
    public static TCPCM GetInstance() { return INSTANCE; }
	
	private Socket socket;
	private Scanner reader;
	private PrintWriter writer;
	private int client_index = 0;
	private boolean running;
	private int player_size = 0;
	
	public Socket getSocket() {
		return socket;
	}
	
	public int GetClientIndex() {
		return client_index;
	}
	
	public boolean connectSever(InetAddress InterAddr) {
		try {
			socket = new Socket(InterAddr, 1111);
			return true;
		} catch(IOException e) { return false; }
	}
	
	public TCPCM() {}
	
	public void start() {
		Thread thread = new Thread(this);
		thread.start();
		client_index = 1;
	}
	
	public void stop() {
		running = false;
		writer.close();
		reader.close();
		try { socket.close(); }
		catch(Exception e) {}
		player_size = 0;
	}

	@Override
	public void run() {
		try {
			OutputStream outputStream = socket.getOutputStream();
			writer = new PrintWriter(outputStream);			
			InputStream inputStream = socket.getInputStream();
			reader = new Scanner(inputStream);
				
			running = true;
			while(running) {
				try {
					String msg = reader.next();
					switch(msg) {
						case "index":{
							client_index = reader.nextInt();
							player_size = reader.nextInt();
							//System.out.println(msg + " " + client_index + " "+ player_size);
							break;
						}
						case "allconnect":{
							SelectCharacter.GetInstance().confirm_button.setEnabled(true);
							break;
						}
						case "select" :{
							int msg2 = reader.nextInt(); //client index
							int msg3 = reader.nextInt(); //character index
							DOM.GetInstance().addVirtualCharacter(msg2);
							DOM.GetInstance().updateVirtualCharacter(msg2, msg3, 0);
							System.out.println("#Player "+msg2+" select no."+msg3);
							if(DOM.GetInstance().GetCharacterSize() == player_size) {
								send("ready");
								System.out.println("#Player "+msg2+" is ready.");
							}
							break;
						}
						case "start":{
							LeegFighter2.GetInstance().Fighting();
							break;
						}
						case "position":{
							int no = reader.nextInt();
							if(no != TCPCM.GetInstance().GetClientIndex())
								DOM.GetInstance().updateVirtualCharacterPosition(no, reader.nextInt(), reader.nextInt(), reader.nextInt(), reader.nextInt());
							break;
						}
						case "hp":{
							int no = reader.nextInt();
							if(no != TCPCM.GetInstance().GetClientIndex())
								DOM.GetInstance().updateVirtualCharacterHp(no, reader.nextInt());
							break;
						}
						case "attack":{
							int no = reader.nextInt();
							if(no != TCPCM.GetInstance().GetClientIndex())
								DOM.GetInstance().updateVirtualCharacterAttack(no, reader.nextInt());
							break;
						}
						case "disconnect":{
							DOM.GetInstance().Clear();
							LeegFighter2.GetInstance().Gameover();
							TCPSM.GetInstance().stop();
							this.stop();
							break;
						}
					}
				} catch(NoSuchElementException e) {
					System.out.println("Oops! server has closed.");
					DOM.GetInstance().Clear();
					LeegFighter2.GetInstance().Gameover();
					TCPSM.GetInstance().stop();
					this.stop();
					break;
				}
			}
		} catch(Exception e) { e.printStackTrace(); }
	}
	
	public void inputMoves(int MoveCode) {
		String msg = "";
		switch(MoveCode)
		{			
			case 1:
				msg = "TURNEAST";
				break;
			case 2:
				msg = "TURNSOUTH";
				break;
			case 3:
				msg = "TURNNORTH";
				break;
			case 4:
				msg = "TURNWEST";
				break;
			case 5:
				msg = "GET";
				break;				
		}				
		writer.println(msg);
		writer.flush();
	}
	
	public void send(String msg) {
		writer.println(msg);
		writer.flush();
	}
	
	public int GetPlayerSize() {
		return player_size;
	}
}
