import java.util.*;
import java.io.*;
import static java.lang.System.*;
public class day8 {
	private static boolean check(final int[][] trees, int r, int c) {
		if (r == 0 || r == trees.length-1)
			return true;
		if (c == 0 || c == trees[r].length-1)
			return true;

		// System.out.println("--- " + "(" + r + ", " + c + "): " + trees[r][c]);

		// right
		boolean right = true;
		for (int i = c+1; i < trees[r].length; i++) {
			// System.out.print(trees[r][i]);
			if (trees[r][c] <= trees[r][i])
				right = false;
		}
		// System.out.println(" " + right);

		// left
		boolean left = true;
		for (int i = c-1; i >= 0; i--) {
			// System.out.print(trees[r][i]);
			if (trees[r][c] <= trees[r][i])
				left = false;
		}
		// System.out.println(" " + left);

		// up
		boolean up = true;
		for (int i = r-1; i >= 0; i--) {
			// System.out.print(trees[i][c]);
			if (trees[r][c] <= trees[i][c])
				up = false;
		}
		// System.out.println(" " + up);

		// down
		boolean down = true;
		for (int i = r+1; i < trees.length; i++) {
			// System.out.print(trees[i][c]);
			if (trees[r][c] <= trees[i][c])
				down = false;
		}
		// System.out.println(" " + down + "\n\n");

		return right || left || up || down;
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

		int count = 0;
		for (int r = 0; r < trees.length; r++)
			for (int c = 0; c < trees[0].length; c++)
				if (check(trees, r, c))
					count++;

		System.out.println(count);
	}
}