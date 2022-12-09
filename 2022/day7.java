import java.util.*;
import java.util.stream.*;
import java.io.*;

public class day7_1 {

	private static class Data implements Comparable<Data> {
		public static final Data ROOT_DIRECTORY = new Data("", new HashSet<Data>(), "/", 0, true);

		public String path;
		public HashSet<Data> files;
		public String name;
		public long size;
		public boolean isFolder;

		public Data(final String path, final HashSet<Data> files, final String name, final long size, final boolean isFolder) {
			this.path = path;
			this.files = files;
			this.name = name;
			this.size = size;
			this.isFolder = isFolder;
		}

		public Data search(final String name) {
			for (Data data: files)
				if (data.name.equals(name))
					return data;
			return null;
		}

		public boolean containsFolders() {
			for (Data next: files)
				if (next.isFolder)
					return true;
			return false;
		}

		public long getSize() {
			if (!isFolder)
				return size;
			long total = 0;
			for (Data data: files)
				total += data.getSize();
			size = total;
			return total;
		}

		public int compareTo(Data other) {
			return this.toString().compareTo(other.toString());
		}

		public String toString() {
			return String.format("%s%s%s = %d%s", path, name, 
				isFolder ? "/" : "", isFolder ? -1 : size, isFolder ? " " + files : "");
		}

		public static Data locateDirectory(final Stack<String> dir) {
			Queue<String> path = new LinkedList<String>(dir);
			path.remove(); // starting at root
			Data current = ROOT_DIRECTORY;
			// System.out.println("Start: " + current);
			while (current != null && !path.isEmpty()) {
				String next_name = path.remove();
				Data next = current.search(next_name);
				// System.out.println(next == null ? "Not found." : "FOUND " + next);
				current = next;
			}
			// System.out.println("End: " + current);
			return current;
		}

		private static TreeSet<Data> file_system;
		private static void compileFileSystem() {
			file_system = new TreeSet<>();
			compileFileSystemRecur(ROOT_DIRECTORY);
			for (Data data: file_system)
				data.getSize();
		}

		private static void compileFileSystemRecur(Data current) {
			file_system.add(current);
			if (current.isFolder)
				for (Data data: current.files)
					compileFileSystemRecur(data);
		}
	}

	private static String stringifyPath(final Stack<String> path) {
		return path.stream().map(e -> e + "/").collect(Collectors.joining()).substring(1);
	}

	public static void main(String[] args) throws IOException {	
		Scanner in = new Scanner(new File("input"));
		Stack<String> cwd = new Stack();

		while (in.hasNextLine()) {
			String[] tokens = in.nextLine().split(" ");
			if (tokens[0].equals("$")) {
				switch (tokens[1]) {

					case "cd": {
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
					}

					case "ls": {
						// System.out.println("Searching: " + stringifyPath(cwd));
						Data folderLocation = Data.locateDirectory(cwd);

						in.hasNext(".*");
						String[] file = new String[] {""};
						while (in.hasNextLine() && !file[0].equals("$")) {
							file = in.match().group(0).trim().split(" ");

							// System.out.println(Arrays.toString(file));

							if (file[0].equals("$"))
								continue;
							in.nextLine();

							folderLocation.files.add(
								new Data(
									stringifyPath(cwd), 
									new HashSet<Data>(), 
									file[1], 
									file[0].equals("dir") ? -1 : Long.parseLong(file[0]), file[0].equals("dir"))
							);
						}

						// System.out.println(folderLocation);
					}

				}
			}
		}

		Data.compileFileSystem();

		// for (Data data: Data.file_system)
		// 	System.out.println(data.path + "/" + data.name + ": " + (data.files.isEmpty() ? data.size : data.files));

		ArrayList<Data> folders = new ArrayList<Data>();
		for (Data data: Data.file_system)
			if (data.isFolder)
				folders.add(data);

		for (Data data: folders)
			System.out.println(data.name + " " + data.size);

		long best_size = Long.MAX_VALUE;
		long required = 30000000 - (70000000 - Data.ROOT_DIRECTORY.size);
		// System.out.println(required);
		for (Data folder: folders) 
			if (folder.size >= required && folder.size < best_size)
				best_size = folder.size;
		System.out.println("Best: " + best_size);
	}
}
