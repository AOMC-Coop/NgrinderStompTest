package com.stomp.client;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import org.springframework.web.socket.sockjs.frame.Jackson2SockJsMessageCodec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stomp.model.Message;

/**
 * Created by nick on 30/09/2015.
 */
public class StompClient {

	private final static WebSocketHttpHeaders headers = new WebSocketHttpHeaders();

	private StompSession stompSession = null;

	public int connect(String url) throws InterruptedException, ExecutionException {

		Transport webSocketTransport = new WebSocketTransport(new StandardWebSocketClient());
		List<Transport> transports = Collections.singletonList(webSocketTransport);

		SockJsClient sockJsClient = new SockJsClient(transports);
		sockJsClient.setMessageCodec(new Jackson2SockJsMessageCodec());

		WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);

		ListenableFuture<StompSession> f = stompClient.connect(url, headers, new MyHandler(), "localhost", 8080);
		this.stompSession = f.get();
		return 1;

	}

	public void subscribeGreetings(String topic) throws ExecutionException, InterruptedException {
		this.stompSession.subscribe(topic, new StompFrameHandler() {

			public Type getPayloadType(StompHeaders stompHeaders) {
				return byte[].class;
			}

			public void handleFrame(StompHeaders stompHeaders, Object o) {
				// logger.info("Received greeting " + new String((byte[]) o));
			}
		});
	}

	public int sendMsg(String msg, String chatroom_idx) throws JsonProcessingException {
//		String sendMsg = "{" + "\"sender\":1,\"msg\":\"" + msg + "\",\"msg_type\":\"m\"}";
//		this.stompSession.send("/chatroom/" + chatroom_idx, sendMsg.getBytes());
		
		Message message = new Message();
		message.setContent("yunjae-message");
		message.setChannel_idx(1202);
		message.setUser_idx(23);
		message.setNickname("yunyun");
		
//		var send_date = now.format("dddd, MMMM Do").toString();
//		var send_time = now.format("LT").toString();
//		var send_db_date = moment(new Date()).format("YYYY-MM-DD HH:mm:ss");
		
		Date send_date = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("dddd, MMMM D");
		String str_send_date = transFormat.format(send_date);

		message.setSend_date(str_send_date);
		
		Date send_db_date = new Date();
		SimpleDateFormat transFormat2 = new SimpleDateFormat("L");
		String str_send_db_date = transFormat2.format(send_db_date);
		message.setSend_db_date(str_send_db_date);
		
		
		Date send_time = new Date();
		SimpleDateFormat transFormat3 = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
		String str_send_time = transFormat3.format(send_time);
		message.setSend_time(str_send_time);
		
		ObjectMapper mapper = new ObjectMapper();

		//Object to JSON in String
		String str_message = mapper.writeValueAsString(message);
		
		this.stompSession.send("/app/chat", str_message.getBytes());
		
		return 1;
	}

	private class MyHandler extends StompSessionHandlerAdapter {
		public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {
			// logger.info("Now connected");
		}
	}

	public void disconnect() {
		this.stompSession.disconnect();
	}

//	public static void main(String[] args) throws Exception {
//		StompClient helloClient = new StompClient();
//		helloClient.connect("http://localhost:8083/socketconnect");
//
//		// logger.info("Subscribing to greeting topic using session " +
//		// stompSession);
//		// helloClient.subscribeGreetings(stompSession);
//
//		// logger.info("Sending hello message" + stompSession);
//		helloClient.sendMsg("msh", "156");
//		helloClient.disconnect();
//	}

}
