import java.net.InetAddress;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.net.SocketException;

public class TCPSM2 implements Runnable
{
	public static int ready_num = 0;
	
	private Socket socket;
	private TCPSM tcpsm;
	private Scanner reader;
	private PrintWriter writer;
	private int ClientIndex = 0;
	private boolean running;

	public Socket getSocket() {
		return socket;
	}
	
	public int GetClientIndex() {
		return ClientIndex;
	}
	
	public boolean connectSever(InetAddress InterAddr) {
		try {
			socket = new Socket(InterAddr, 1111);
			return true;
		} catch(IOException e) { return false; }
	}
	
	public TCPSM2(Socket socket, TCPSM tcpsm, int ClientIndex) {
		this.socket = socket;
		this.tcpsm = tcpsm;
		this.ClientIndex = ClientIndex;
		try {
			OutputStream outputStream = socket.getOutputStream();
			writer = new PrintWriter(outputStream);			
			InputStream inputStream = socket.getInputStream();
			reader = new Scanner(inputStream);
		} catch (Exception e) {}
	}
	
	public TCPSM2() {}
	
	public void start() {
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void stop() throws IOException {
		running = false;
		
		writer.close();
		reader.close();
		try { socket.close(); }
		catch (Exception e) {}
	}

	@Override
	public void run() {
		try {
			OutputStream outputStream = socket.getOutputStream();
			writer = new PrintWriter(outputStream);			
			InputStream inputStream = socket.getInputStream();
			reader = new Scanner(inputStream);
				
			running = true;
			while(running)
			{
				try	{
					String msg = reader.nextLine();
					if(msg.equals("ready"))
						ready_num += 1;
					else
						tcpsm.broadcast(msg);
				} catch(NoSuchElementException e) {
					tcpsm.broadcast("disconnect");
					tcpsm.stop();
					break;
				}
			}
		} catch(Exception e) { e.printStackTrace(); }
	}
	
	public void send(String msg) {
		writer.println(msg);
		writer.flush();
	}
}
