import java.util.HashSet;

public class SetOperations {
	public static boolean equal(HashSet<?> set1, HashSet<?> set2)
	{
		if (set1.size()!=set2.size())
			return false;
		return set1.containsAll(set2);
	}
}
