package nu.spiritusmundi.inteokej.android;
import java.util.ArrayList;
import java.util.Date;


public class Question {
	
	private String title;
	private String content;
	private String userName;
	private String tag;
	private ArrayList<String> usersWhoSupportThis;
	private ArrayList<String> usersWhoFlaggedThis;
	private Date date;
	private int numViews;
	
	private ArrayList<Answer> answers;
	
	public Question(String title, String content, String userName, String tag) 
	{
		this.title = title;
		this.content = content;
		this.userName = userName;
		this.tag = tag;
		
		date = new Date();
		answers = new ArrayList<Answer>();
		usersWhoSupportThis = new ArrayList<String>();
		usersWhoFlaggedThis = new ArrayList<String>();
	}
	
	
	
	public String getTitle()
	{
		return title;
	}
	
	public String getContent()
	{
		return content;
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public String getTag()
	{
		return tag;
	}
	
	public ArrayList<String> getSupporters()
	{
		return usersWhoSupportThis;
	}
	
	public ArrayList<Answer> getAnswers()
	{
		return answers;
	}
	
	public void support(String userName)
	{
		usersWhoSupportThis.add(userName);
	}
	
	public void flag(String userName)
	{
		usersWhoFlaggedThis.add(userName);
	}
	
	public ArrayList<String> getUsersWhoSupportsThis()
	{
		return usersWhoSupportThis;
	}
	
	public ArrayList<String> getUsersWhoFlaggedThis()
	{
		return usersWhoFlaggedThis;
	}
	
	public String toString()
	{
		return title;
	}
	
	public Date getDate()
	{
		return date;
	}
	
	public void view()
	{
		numViews ++;
	}
	
	public int getNumViews()
	{
		return numViews;
	}

}
