package ie.ucd.cluedo;

public class Notebook 
{

	// Notebook attributes
	private String activityLog;
	
	// Notebook constructor
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
