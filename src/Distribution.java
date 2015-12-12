import java.util.LinkedList;

public class Distribution {
	private Action action;
	private LinkedList<StateProbability> stateProbabilities;
	
	
	
	public Distribution(Action action, LinkedList<StateProbability> stateProbabilities) {
		this.action = action;
		this.stateProbabilities = stateProbabilities;
	}
	
	public Action getAction() {
		return action;
	}
	
	public LinkedList<StateProbability> getStateProbabilities() {
		return stateProbabilities;
	}
}
