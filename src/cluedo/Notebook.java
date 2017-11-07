package cluedo;

public class Notebook 
{

	private String activityLog;
	
	public Notebook()
	{
		this.activityLog = "";
	}
	
	public void makeEntry(String hypothesis)
	{
		this.activityLog += hypothesis + "\n";
	}
	
	public String getContents()
	{
		return this.activityLog;
	}
	
}
