package tw.cheyingwu.ckip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CKIPConnection {
	static Logger logger = LogManager.getLogger(CKIPConnection.class.getName());
	
	private String ip;
	private int port;
	
	private Socket socket;
	private PrintWriter writer;
	private BufferedReader reader;
	
	public CKIPConnection(String ip, int port){
		this.ip = ip;
		this.port = port;
	}
	
	public void open(){
		try {
			socket = new Socket(this.ip, this.port);
			writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "Big5"));
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "Big5"));
			logger.info("connected to: " + this.ip + ":" + this.port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String send(String ckipMsg){
		String returnMsg = "";
		try {
			
			writer.println(ckipMsg);
			writer.flush();
			logger.info("send the message to ckip server.");
			// get return text
			returnMsg = reader.readLine();
			logger.debug("the return message: " + returnMsg);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnMsg;
	}
	
	public void close(){
		try {
			writer.close();
			reader.close();
			socket.close();
			logger.info("connection closed.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
