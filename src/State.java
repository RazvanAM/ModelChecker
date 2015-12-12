
public class State {
	private Long ID;
	
	public State(long ID)
	{
		this.ID = ID;
	}
	
	public long getID()
	{
		return ID;
	}
	
	public boolean equals(Object o)
	{
		if (o==null)
			return false;
		else
			return ID == ((State) o).getID();
	}
	
	public int hashCode()
	{
		return ID.hashCode();
	}
	
	public String toString()
	{
		return "s" + ID;
	}
}
