import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class MDP {
	private HashSet<State> states;
	private State sInit;
	private HashMap<State,HashSet<Distribution>> steps;
	private HashMap<State,HashSet<Label>> L;
	
	public MDP(HashSet<State> states, State sInit, HashMap<State, HashSet<Distribution>> steps,
			HashMap<State, HashSet<Label>> L) {
		super();
		this.states = states;
		this.sInit = sInit;
		this.steps = steps;
		this.L = L;
	}

	public HashSet<State> Smin0(HashSet<State> T)
	{
		HashSet<State> R = new HashSet<State>();
		HashSet<State> RR = new HashSet<State>();
		
		R.addAll(T);
		
		do
		{
			RR.clear();
			RR.addAll(R);
			for (State state:states)
			{
				boolean all = true;
				for (Distribution distribution:steps.get(state))
				{
					boolean exists = false;
					for (StateProbability stateProbability:distribution.getStateProbabilities())
						if (RR.contains(stateProbability.getState()) && stateProbability.getProbability()>0)
							exists = true;
					
					if (!exists)
					{
						all=false;
						break;
					}
				}
				
				if (all)
					R.add(state);
			}
		} while (!SetOperations.equal(R, RR));
		
		HashSet<State> result = new HashSet<State>();
		result.addAll(states);
		result.removeAll(R);
		
		return result;
		
	}
	
	public HashSet<State> Smin1(HashSet<State> smin0) 
	{
		HashSet<State> R = new HashSet<State>();
		HashSet<State> RR = new HashSet<State>();
		
		R.addAll(states);
		R.removeAll(smin0);
		
		do 
		{
			RR.clear();
			RR.addAll(R);
			
			for (State state : RR) 
			{
				boolean exists = false;
				
   break_label: for (Distribution distribution : steps.get(state)) 
				{
					for (StateProbability stateProbability : distribution.getStateProbabilities())
						if (!RR.contains(stateProbability.getState()) && stateProbability.getProbability() > 0)
						{
							exists = true;
							break break_label;
						}	
				}
				
				if (exists) 
					R.remove(state);
			}

		} while (!SetOperations.equal(R, RR));
		
		return R;
	}
	
	
	public static void main(String[] args)
	{
		/* 
		// TEST CASE 1
		State s0,s1,s2,s3;
		s0 = new State(0);
		s1 = new State(1);
		s2 = new State(2);
		s3 = new State(3);
		
		HashSet<State> states = new HashSet<State>();
		states.add(s0);
		states.add(s1);
		states.add(s2);
		states.add(s3);
		
		HashSet<Distribution> distributionsS0 = new HashSet<Distribution>();
		HashSet<Distribution> distributionsS1 = new HashSet<Distribution>();
		HashSet<Distribution> distributionsS2 = new HashSet<Distribution>();
		HashSet<Distribution> distributionsS3 = new HashSet<Distribution>();
		
		LinkedList<StateProbability> distributionsS01List = new LinkedList<StateProbability>();
		LinkedList<StateProbability> distributionsS02List = new LinkedList<StateProbability>();
		LinkedList<StateProbability> distributionsS1List = new LinkedList<StateProbability>();
		LinkedList<StateProbability> distributionsS2List = new LinkedList<StateProbability>();
		LinkedList<StateProbability> distributionsS3List = new LinkedList<StateProbability>();
		
		
		distributionsS01List.add(new StateProbability(s1,0.7));
		distributionsS01List.add(new StateProbability(s2,0.3));
		
		//distributionsS02List.add(new StateProbability(s1,0.2));
		distributionsS02List.add(new StateProbability(s3,1));
		
		distributionsS1List.add(new StateProbability(s2,1));
		
		distributionsS2List.add(new StateProbability(s2,1));
		
		distributionsS3List.add(new StateProbability(s3,1));
		
		distributionsS0.add(new Distribution(new Action("s0a1"),distributionsS01List));
		distributionsS0.add(new Distribution(new Action("s0a2"),distributionsS02List));
		
		distributionsS1.add(new Distribution(new Action("s1a1"),distributionsS1List));
		
		distributionsS2.add(new Distribution(new Action("s2a1"),distributionsS2List));
		
		distributionsS3.add(new Distribution(new Action("s3a1"),distributionsS3List));
		
		HashMap<State,HashSet<Distribution>> steps = new HashMap<State,HashSet<Distribution>>();
		
		steps.put(s0, distributionsS0);
		steps.put(s1, distributionsS1);
		steps.put(s2, distributionsS2);
		steps.put(s3, distributionsS3);
		*/
		
		// TEST CASE 2 (Fig. 2)
		State s0,s1,s2,s3;
		s0 = new State(0);
		s1 = new State(1);
		s2 = new State(2);
		s3 = new State(3);
		
		HashSet<State> states = new HashSet<State>();
		states.add(s0);
		states.add(s1);
		states.add(s2);
		states.add(s3);
		
		HashSet<Distribution> distributionsS0 = new HashSet<Distribution>();
		HashSet<Distribution> distributionsS1 = new HashSet<Distribution>();
		HashSet<Distribution> distributionsS2 = new HashSet<Distribution>();
		HashSet<Distribution> distributionsS3 = new HashSet<Distribution>();
		
		LinkedList<StateProbability> s0Distribution1 = new LinkedList<StateProbability>();
		LinkedList<StateProbability> s1Distribution1 = new LinkedList<StateProbability>();
		LinkedList<StateProbability> s1Distribution2 = new LinkedList<StateProbability>();
		LinkedList<StateProbability> s2Distribution1 = new LinkedList<StateProbability>();
		LinkedList<StateProbability> s3Distribution1 = new LinkedList<StateProbability>();
		LinkedList<StateProbability> s3Distribution2 = new LinkedList<StateProbability>();
		
		// S0 distribution
		s0Distribution1.add(new StateProbability(s1, 1.0));
		
		// S1 distributions
		s1Distribution1.add(new StateProbability(s0, 0.7));
		s1Distribution1.add(new StateProbability(s2, 0.3));
		
		s1Distribution2.add(new StateProbability(s2, 0.5));
		s1Distribution2.add(new StateProbability(s3, 0.5));
		
		// S2 distributions
		s2Distribution1.add(new StateProbability(s2, 1.0));
		
		// S3 distributions 
		s3Distribution1.add(new StateProbability(s3, 1.0));
		
		s3Distribution2.add(new StateProbability(s0, 1.0));
		
		// Set up the actions + their distributions
		distributionsS0.add(new Distribution(new Action("go"), s0Distribution1));
		
		distributionsS1.add(new Distribution(new Action("safe"), s1Distribution1));
		distributionsS1.add(new Distribution(new Action("risk"), s1Distribution2));
		
		distributionsS2.add(new Distribution(new Action("finish"), s2Distribution1));
		
		distributionsS3.add(new Distribution(new Action("stop"), s3Distribution1));
		distributionsS3.add(new Distribution(new Action("reset"), s3Distribution2));
		
		HashMap<State, HashSet<Distribution>> steps = new HashMap<State, HashSet<Distribution>>();
		steps.put(s0, distributionsS0);
		steps.put(s1, distributionsS1);
		steps.put(s2, distributionsS2);
		steps.put(s3, distributionsS3);
		
		
		
		HashSet<State> T = new HashSet<State>();
		T.add(s2);
		MDP mdp = new MDP(states,s0,steps,null);
		
		// Smin0 result
		HashSet<State> s0MinResult = mdp.Smin0(T);
		System.out.println("Smin0: " + s0MinResult); //System.out.println(mdp.Smin0(T));
		
		// Smin1 result
		System.out.println("Smin1: " + mdp.Smin1(s0MinResult));
		
		
	}
}
