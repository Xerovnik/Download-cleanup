package SideProject1.SideProject1;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class App {

	public static void main(String[] args)  {
		// File file = new File();

		File[] files = new File("C:\\Users\\Student\\Downloads").listFiles();
		showFiles(files);
	}

	public static void showFiles(File[] files)  {
		// List all files
		for (File file : files) {
			if (file.isDirectory()) {
				// System.out.println("Directory: " + file.getName());
				// showFiles(file.listFiles()); // Calls same method again.
			} else {
				System.out.println("File: " + file.getName());
			}
		}
		// find all file types in folder
		//use Set
		ArrayList<String> extList = new ArrayList<String>();
		for (File ext : files) {
			String fileName = ext.toString();
			String[] tokens = fileName.split("\\.(?=[^\\.]+$)");

			if (!extList.contains(tokens[tokens.length - 1]) && (tokens[tokens.length - 1].length() <= 3)) {
				extList.add(tokens[tokens.length - 1]);
				// System.out.println(tokens[tokens.length - 1]);
			}
		}

		// determine if folders exist.
		try {
			for (int j = 0; j < extList.size(); j++) {
				Path path = Paths.get("C:/Users/Student/Downloads/", extList.get(j));
				Files.createDirectories(path);
			}
		} catch (EOFException e) {
			System.err.println("unable to creat directory!");
		}catch(IOException e) {
			System.err.println("unable to creat directory!");
		}

		// Move files into folders
		for (File file : files) {
			String fileName = file.toString();
			String[] tokens = fileName.split("\\.(?=[^\\.]+$)");
			// System.out.println(file.getName());
			Path source = Paths.get("C:/Users/Student/Downloads/", file.getName());
			if ((!source.toString().contains(".")) || (source.toString().contains("@"))) {
				continue;
			} else {
				Path target = Paths.get("C:/Users/Student/Downloads/" + tokens[tokens.length - 1],
						source.getFileName().toString());
				try {
				Files.copy(source, target, REPLACE_EXISTING, COPY_ATTRIBUTES, NOFOLLOW_LINKS);
				}catch(IOException e) {
					System.err.println("Unable to move files!");
				}
			}

		}

	}
}
