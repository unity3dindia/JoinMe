package com.sjl.joinme.user_message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.sjl.joinme.database.JoinMeConnection;

public class UserMessageDAO {
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private Connection conn = null;

	public boolean sendUserMessage(int sender_id, int reciever_id, String message) {
		boolean flag = false;
		if (conn == null) {
			conn = JoinMeConnection.getConnection();
		}

		try {
			String query = "insert into user_message(sender_id,receiver_id,message) values(?,?,?)";
			ps = conn.prepareStatement(query);
			ps.setInt(1, sender_id);
			ps.setInt(2, reciever_id);
			ps.setString(3, message);
			if (ps.executeUpdate() > 0) {
				flag = true;
			}

		} catch (Exception e) {
			System.out.println("+++Exception at SendUserMessage" + e);
		} finally {
			conn = null;
			ps = null;
			return flag;

		}
	}

	public boolean editUserMessageStatus(int user_message_id, String newStatus) {
		boolean flag = false;
		if (conn == null) {
			conn = JoinMeConnection.getConnection();
		}
		try {
			String query = "update user_message set status=? where user_message_id=?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, user_message_id);
			ps.setString(2, newStatus);
			if (ps.executeUpdate() > 0) {
				flag = true;
			}

		} catch (Exception e) {
			System.out.println("+++Exception at editUserMessageStatus");
		} finally {
			conn = null;
			ps = null;
			return flag;
		}
	}

	/* message,sender_id,receiver_id */
	public UserMessageDTO recieveUserMessage(int sender_id, int receiver_id, String status, String dateFrom,
			String dateTo) {
		ArrayList<UserMessageDTO> list = new ArrayList<>();
		UserMessageDTO dto = null;

		if (conn == null) {
			conn = JoinMeConnection.getConnection();
		}
		try {/* created_datetime */
			String query = "select message,user_message_id from user_message where created_datetime>? and created_datetime<? and status=? ";
			ps = conn.prepareStatement(query);
			ps.setString(1, dateFrom);
			ps.setString(2, dateTo);
			ps.setString(3, status);
			rs = ps.executeQuery();
			while (rs.next()) {
				dto = new UserMessageDTO();
				dto.setMessage(rs.getString("message"));
				dto.setUser_message_id(rs.getInt("user_message_id"));
				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println("+++Exception at recieveUserMessage");
		} finally {
			if (list.isEmpty()) {
				return null;
			}
			conn = null;
			ps = null;
			rs = null;
			return dto;
		}

	}

}
