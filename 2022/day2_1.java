import java.util.*;
import java.io.*;

public class day2_1 {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input"));
		Map<String, String> equiv = Map.of("A", "X", "B", "Y", "C", "Z");
		Map<String, String> win = Map.of("A", "Y", "B", "Z", "C", "X");
		Map<String, Integer> vals = Map.of("A", 1, "X", 1, "B", 2, "Y", 2, "C", 3, "Z", 3);
		long opp = 0, you = 0;
		while (in.hasNextLine()) {
			String opp_mv = in.next(), you_mv = in.nextLine().trim();
			you += vals.get(you_mv);
			opp += vals.get(opp_mv);
			if (win.get(opp_mv).equals(you_mv))
				you += 6;
			else if (equiv.get(opp_mv).equals(you_mv)) {
				you += 3;
				opp += 3;
			}
			else {
				opp += 6;
			}
		}
		System.out.println(you);
	}
}