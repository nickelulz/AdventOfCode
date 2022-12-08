import java.util.*;
import java.io.*;

public class day1_2 {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input"));
		ArrayList<Long> vals = new ArrayList<>(); 
		long total = 0;
		while (in.hasNextLine()) {
			String line = in.nextLine();
			if (line.isEmpty()) {
				vals.add(total);
				total = 0;
			}
			else {
				total += Long.parseLong(line);
			}
		}
		Collections.sort(vals, Collections.reverseOrder());
		System.out.println(vals.get(0) + vals.get(1) + vals.get(2));
	}
}