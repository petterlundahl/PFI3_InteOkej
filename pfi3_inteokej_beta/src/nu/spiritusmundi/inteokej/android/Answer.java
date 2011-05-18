package nu.spiritusmundi.inteokej.android;

import java.util.ArrayList;
import java.util.Date;

public class Answer {
	
	private String content;
	private String userName;
	private ArrayList<String> usersWhoLikeThis;
	private ArrayList<String> usersWhoFlaggedThis;
	private Date date;
	
	public Answer(String content, String userName) {
		this.content = content;
		this.userName = userName;
		
		usersWhoLikeThis = new ArrayList<String>();
		usersWhoFlaggedThis = new ArrayList<String>();
		
		date = new Date();
	}
	
	
	public String getContent()
	{
		return content;
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public void like(String userName)
	{
		usersWhoLikeThis.add(userName);
	}
	
	public void flag(String userName)
	{
		usersWhoFlaggedThis.add(userName);
	}
	
	public ArrayList<String> getUsersWhoLikeThis()
	{
		return usersWhoLikeThis;
	}
	
	public ArrayList<String> getUsersWhoFlaggedThis()
	{
		return usersWhoFlaggedThis;
	}
	public Date getDate()
	{
		return date;
	}
}
