import java.util.*;
import java.io.*;

public class day9 {
	private static final byte X = 0, Y = 1;

	private static boolean adjacent(final int[] head, final int[] tail) {
		return Math.abs(head[X] - tail[X]) < 1 && Math.abs(head[Y] - tail[Y]) < 1;
	}

	private static int horiz_delta(final int[] head, int[] tail) {
		return head[X] - tail[X];
	}

	private static int vert_delta(final int[] head, int[] tail) {
		return head[Y] - tail[Y];
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("../testin"));
		// both start at 0,0
		// positions are not matrix but coordinates relative to origin
		int[] head = { 0, 0 }, tail = { 0, 0 };
		ArrayList<int[]> tail_positions = new ArrayList<>();

		while (in.hasNextLine()) {
			String direction = in.next();
			int distance = in.nextInt();
			in.nextLine();

			switch (direction) {
				case "R": {
					for (int i = 0; i < distance; i++, head[X]++) {
						if (!adjacent(head, tail))
							tail[X]++;
						if (!adjacent(head, tail))
							tail[Y] += vert_delta(head, tail);
						tail_positions.add(Arrays.copyOf(tail, tail.length));
					}
					break;	
				}

				case "U": {
					for (int i = 0; i < distance; i++, head[Y]++) {
						if (!adjacent(head, tail))
							tail[Y]++;
						if (!adjacent(head, tail))
							tail[X] += horiz_delta(head, tail);
						tail_positions.add(Arrays.copyOf(tail, tail.length));
					}
					break;
				}

				case "L": {
					for (int i = 0; i < distance; i++, head[X]--) {
						if (!adjacent(head, tail))
							tail[X]--;
						if (!adjacent(head, tail))
							tail[Y] += vert_delta(head, tail);
						tail_positions.add(Arrays.copyOf(tail, tail.length));
					}
					break;
				}

				case "D": {
					for (int i = 0; i < distance; i++, head[Y]--) {
						if (!adjacent(head, tail))
							tail[Y]--;
						if (!adjacent(head, tail))
							tail[X] += horiz_delta(head, tail);
						tail_positions.add(Arrays.copyOf(tail, tail.length));
					}
					break;
				}
			}

			System.out.println(" --- " + direction + " " + distance);
			for (int[] pos: tail_positions)
				System.out.println(Arrays.toString(pos));
			tail_positions.clear();
			System.out.println("\n\n");
		}

		// // System.out.println(tail_positions.size());
		// for (int[] pos: tail_positions)
		// 	System.out.println(Arrays.toString(pos));
	}
}
