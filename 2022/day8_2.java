import java.util.*;
import java.io.*;
// import static java.lang.System.*;
public class day8_2 {
	private static int check(final int[][] trees, int r, int c) {
		if (r == 0 || r == trees.length-1 || c == 0 || c == trees[r].length-1)
			return 0;

		int right = 0, left = 0, up = 0, down = 0;

		// System.out.println("--- " + "(" + r + ", " + c + "): " + trees[r][c]);

		ArrayList<Integer> debug = new ArrayList<>();

		// up
		for (int i = r-1; i >= 0; i--) {
			up++;
			debug.add(trees[i][c]);
			if (trees[r][c] <= trees[i][c])
				break;
		}
		// System.out.println("UP: " + debug + " = " + up);
		debug.clear();

		// left
		for (int i = c-1; i >= 0; i--) {
			left++;
			debug.add(trees[r][i]);
			if (trees[r][c] <= trees[r][i])
				break;
		}
		// System.out.println("LEFT: " + debug + " = " + left);
		debug.clear();		

		// right
		for (int i = c+1; i < trees[r].length; i++) {
			right++;
			debug.add(trees[r][i]);
			if (trees[r][c] <= trees[r][i])
				break;
		}
		// System.out.println("RIGHT: " + debug + " = " + right);
		debug.clear();

		// down
		for (int i = r+1; i < trees.length; i++) {
			down++;
			debug.add(trees[i][c]);
			if (trees[r][c] <= trees[i][c])
				break;
		}
		// System.out.println("DOWN: " + debug + " = " + down);
		debug.clear();

		int total = up * left * right * down;

		// System.out.printf("Up: %d\nLeft: %d\nRight: %d\nDown: %d\n\nTotal: %d\n\n\n",
		// 	right, left, up, down, total);
		// System.out.println("\nTotal: " + total + "\n\n");

		return total;
	}

	private static int[][] input(final String filename) throws IOException {
		Scanner in = new Scanner(new File(filename));
		ArrayList<int[]> raw = new ArrayList<>();
		while (in.hasNextLine())
			raw.add(Arrays.stream(in.nextLine().split("")).mapToInt(Integer::parseInt).toArray());
		int[][] output = new int[raw.size()][0];
		for (int i = 0; i < raw.size(); i++)
			output[i] = raw.get(i);
		return output;
	}

	public static void main(String[] args) throws IOException {
		int[][] trees = input("input");

		int max = 0;
		for (int r = 0; r < trees.length; r++)
			for (int c = 0; c < trees[0].length; c++)
				max = Math.max(max, check(trees, r, c));

		System.out.println(max);
	}
}