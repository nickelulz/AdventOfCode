import java.util.*;
import java.io.*;

public class day2_2 {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input"));
		Map<String, String> equiv = Map.of("A", "X", "B", "Y", "C", "Z");
		Map<String, String> win = Map.of("A", "Y", "B", "Z", "C", "X");
		Map<String, String> lose = Map.of("A", "Z", "B", "X", "C", "Y");
		Map<String, Integer> vals = Map.of("A", 1, "X", 1, "B", 2, "Y", 2, "C", 3, "Z", 3);
		long opp = 0, you = 0;
		while (in.hasNextLine()) {
			String opp_mv = in.next(), you_mv = in.nextLine().trim();

			switch (you_mv) {
			case "X":
				you += 0 + vals.get(lose.get(opp_mv));
				break;
			case "Y":
				you += 3 + vals.get(equiv.get(opp_mv));
				break;
			case "Z":
				you += 6 + vals.get(win.get(opp_mv));
				break;
			}
		}
		System.out.println(you);
	}
}