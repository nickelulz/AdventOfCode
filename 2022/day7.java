import java.util.*;
import java.util.stream.*;
import java.io.*;

class Data {
	HashSet<String> subfiles;
	String name;
	long size;

	Data(final HashSet<String> subfiles, final String name, final long size) {
		this.subfiles = subfiles;
		this.name = name;
		this.size = size;
	}
}

public class day7 {
	public static void main(String[] args) throws IOException {	
		Scanner in = new Scanner(new File("testin"));
		Stack<String> cwd = new Stack();
		Data filesystem = new Data(new HashSet<String>(), "/", 0);

		while (in.hasNextLine()) {
			String[] tokens = in.nextLine().split(" ");
			System.out.println("COMMAND: " + Arrays.toString(tokens));
			if (tokens[0].equals("$")) {
				switch (tokens[1]) {
				case "cd":
					switch(tokens[2]) {
					case "..":
						cwd.pop();
						break;
					case "/":
						cwd.clear();
						cwd.push("/");
						break;
					default:
						cwd.push(tokens[2]);
						break;
					}
					break;
				case "ls":
					Data folderLocation = filesystem;
					while (!folderLocation.subfiles.contains(cwd.peek()))

					String[] file = in.nextLine().split(" ");
					while (in.hasNextLine() && !file[0].equals("$")) {
						System.out.println("FILE: " + Arrays.toString(file));
						file = in.nextLine().split(" ");

					}

				}
			}
		}

		System.out.println(cwd);
	}
}