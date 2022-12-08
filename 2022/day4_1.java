import java.util.*;
import java.io.*;

public class day4_1 {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input"));
		long total = 0; 
		while (in.hasNextLine()) {
			ArrayList<Integer> a = new ArrayList(), b = new ArrayList();
			int[] elfranges = Arrays.stream(in.nextLine().split(",|-")).mapToInt(Integer::parseInt).toArray();
			for (int i = elfranges[0]; i <= elfranges[1]; i++)
				a.add(i);
			for (int i = elfranges[2]; i <= elfranges[3]; i++)
				b.add(i);

			// // day 1 
			// if (a.containsAll(b) || b.containsAll(a))
			// 	total++;

			// day 2
			boolean contains = false;
			for (int n: a)
				if (b.contains(n))
					contains = true;
			for (int n: b)
				if (a.contains(n))
					contains = true;
			if (contains)
				total++;
		}
		System.out.println(total);
	}
}