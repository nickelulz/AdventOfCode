import java.util.*;
import java.io.*;

public class day1 {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input"));
		long max = 0;
		long total = 0;
		while (in.hasNextLine()) {
			String line = in.nextLine();
			if (line.isEmpty()) {
				max = Math.max(max, total);
				total = 0;
			}
			else {
				total += Long.parseLong(line);
			}
		}
		System.out.println(max);
	}
}