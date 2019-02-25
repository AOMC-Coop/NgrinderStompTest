package com.stomp.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Message implements Serializable {
    private int message_idx;
    private String content;
    private String nickname;
    private int channel_idx;
    private int user_idx;
    private String image;
    private String send_date;
    private String send_time;
    private String send_db_date;
    private String file_url;

    private String type;

	public int getMessage_idx() {
		return message_idx;
	}

	public void setMessage_idx(int message_idx) {
		this.message_idx = message_idx;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getChannel_idx() {
		return channel_idx;
	}

	public void setChannel_idx(int channel_idx) {
		this.channel_idx = channel_idx;
	}

	public int getUser_idx() {
		return user_idx;
	}

	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSend_date() {
		return send_date;
	}

	public void setSend_date(String send_date) {
		this.send_date = send_date;
	}

	public String getSend_time() {
		return send_time;
	}

	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}

	public String getSend_db_date() {
		return send_db_date;
	}

	public void setSend_db_date(String send_db_date) {
		this.send_db_date = send_db_date;
	}

	public String getFile_url() {
		return file_url;
	}

	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

//    private File file;
//    private Reply reply;
    
    


}
