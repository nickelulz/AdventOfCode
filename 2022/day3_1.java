import java.util.*;
import java.io.*;
import java.util.stream.*;

public class day3_1 {
	static int value(char c) {
		return 1 + (Character.isLowerCase(c) ? c % 97 : c % 65 + 26);
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input"));
		long total = 0;
		ArrayList<HashSet<Character>> group = new ArrayList();

		while (in.hasNextLine()) {
			String line = in.nextLine();

			group.add(new HashSet<Character>(
				line.chars()
				.mapToObj(c -> (char) c)
				.collect(Collectors.toList())
			));

			if (group.size() == 3) {
				group.get(0).retainAll(group.get(1));
				group.get(0).retainAll(group.get(2));
				for (Character c: group.get(0))
					total += value(c.charValue());
				group.clear();
			}	
		}

		System.out.println(total);
	}
}