
public class StateProbability {
	private State state;
	private double probability;
	
	public StateProbability(State state, double probability)
	{
		this.state = state;
		this.probability = probability;
	}
	
	public State getState()
	{
		return state;
	}
	
	public double getProbability()
	{
		return probability;
	}
}
