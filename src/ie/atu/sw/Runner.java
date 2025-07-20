package ie.atu.sw;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author ZIGAREEN K
 * @version 24.0.1
 * @since 1.8 The class Runner is a class in package ie.atu.sw that decodes and
 *        encodes statements that are provided in a text file using words and
 *        letters from a csv file. The letters and suffixes are <i>stored<i> in
 *        a map and the text file is then read in. Searching and comparison
 *        takes place and the encoded/decoded output is saved to an external
 *        file.
 */
public class Runner {

	public static void main(String[] args) throws Exception {
		// variable declaration
		int option;
		String filePath = "";
		boolean isEncoding = false;

		Scanner scanner = new Scanner(System.in);
		/*
		 * declare tree-maps to store the key and values of the words and numbers when
		 * encoding and decoding. treeMap is the best choice because the keys are
		 * already sorted which makes it easier to search for the values Store the
		 * values mapped to the keys in arrayList for easy access during runtime
		 */
		List<String> words = new ArrayList<>();
		List<Integer> nums = new ArrayList<>();
		Map<Integer, String> deco = new TreeMap<>();// map declaration for encoding the words
		Map<String, Integer> enco = new TreeMap<>();// map declaration for decoding the integers

		// You should put the following code into a menu or Menu class
		System.out.println(ConsoleColour.WHITE);
		System.out.println("************************************************************");
		System.out.println("*     ATU - Dept. of Computer Science & Applied Physics    *");
		System.out.println("*                                                          *");
		System.out.println("*              Encoding Words with Suffixes                *");
		System.out.println("*                                                          *");
		System.out.println("************************************************************");
		System.out.println("(1) Specify Mapping File");
		System.out.println("(2) Specify Text File to Encode");
		System.out.println("(3) Specify Output File (default: ./out.txt)");
		System.out.println("(4) Configure Options");
		System.out.println("(5) Encode Text File");
		System.out.println("(6) Decode Text File");
		System.out.println("(7) Quit");

		// Output a menu of options and solicit text from the user
		System.out.print(ConsoleColour.BLACK_BOLD_BRIGHT);
		System.out.print("Select Option [1-?]>");
		System.out.flush();
		option = scanner.nextInt();
		scanner.nextLine();
		System.out.println();
		// You may want to include a progress meter in you assignment!
		System.out.print(ConsoleColour.YELLOW); // Change the colour of the console text
		int size = 100; // The size of the meter. 100 equates to 100%
		for (int i = 0; i < size; i++) { // The loop equates to a sequence of processing steps
			printProgress(i + 1, size); // After each (some) steps, update the progress meter
			System.out.println("\n");
			Thread.sleep(10); // Slows things down so the animation is visible
		}

		// continue looping until the user chooses to quit
		while (option != 7) {

			/*
			 * The if-code block shows building of the treeMap and its functionality read-in
			 * file and store the elements in the map-with sorted keys and associated values
			 * the runtime is O(log(n)) and it uses binary Search tree algorithm which makes
			 * it faster and easier to search for an item
			 */
			if (option == 1) {
				System.out.println("Enter the file path: ");
				String encodeFile = scanner.nextLine();

				try (BufferedReader br = new BufferedReader(new FileReader(encodeFile))) {
					String next = null;
					while ((next = br.readLine()) != null) {
						String[] pair = next.split(",");
						String key = pair[0];// store the keys-words from the file
						int value = Integer.parseInt(pair[1]);// store the value -numbers from the file
						enco.put(key, value);
						deco.put(value, key);
						// enco.forEach((k, v) -> System.out.println(k + " => " + v));
					}

				} catch (Exception e) {
					throw new Exception("[ERROR] Encountered a problem reading the csv file. " + e.getMessage());
				}

			}

			/*
			 * Read in the text file from the user that will be encode/decoded split the
			 * words when there is a space or commas and trim them if encoding, store the
			 * words into a string array if,decoding,store the numbers into an integer array
			 */
			else if (option == 2) {
				System.out.println("Input the text file for the text file to decode:");

				if (scanner.hasNextLine()) {
					scanner.nextLine();
				} // clear the buffer

				String textFile = scanner.nextLine();

				System.out.println("Encoding(1) or decoding(2)");
				int choice = scanner.nextInt();
				isEncoding = (choice == 1);
				scanner.nextLine();

				try (BufferedReader br = new BufferedReader(
						new InputStreamReader(new FileInputStream(textFile), "UTF-8"))) {
					// System.out.println("Inside loop");DEBUG
					String line;
					while ((line = br.readLine()) != null) {
						String[] values = line.split("[ ,]+");
						for (String w : values) {

							if (isEncoding) {
								words.add(w.trim());
							} else {
								int number = Integer.parseInt(w);
								nums.add(number);
							}
						}
					}
					// System.out.println(nums);//(DEBUG)
				} catch (Exception e) {
					throw new Exception("[ERROR] Encountered a problem reading the text file. " + e.getMessage());
				}
			}
			// input the file path to where you are to write to the information
			else if (option == 3) {
				System.out.println("Input the outputFile: ");

				if (scanner.hasNextLine()) {
					scanner.nextLine();
				} // clear the buffer
				filePath = scanner.nextLine();

				if (filePath == " ") {
					filePath = "./out.txt";// default route
				}

			}

			/*
			 * Search and Compare the words stored in the arrayList the big O-notation will
			 * be O(1) because they are stored in an array and its a contiguous block of
			 * memory. After finding the right key,then get the value associated to it Then
			 * write the encoded value to a text-file
			 */
			else if (option == 5) {

				// filePath = "./out.txt";
				try (FileWriter fw = new FileWriter(filePath)) {

					for (String word : words) {
						word = word.trim().toLowerCase();
						if (enco.containsKey(word)) { // compare the key and word
							int result = enco.get(word);
							// System.out.println(result);
							fw.write(String.valueOf(result) + " ,");// cast the value to a string
						}

					}
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();// try-with-resources makes sure the file closes
				}
			}

			/*
			 * Search and Compare the words stored in the arrayList the big O-notation will
			 * be O(1) because they are stored in an array and its a contiguous block of
			 * memory. After finding the right key,then get the value associated to it Then
			 * write the decoded value to a text-file
			 */
			else if (option == 6) {
				// filePath = "./out.txt";
				isEncoding = false;
				try (FileWriter fw = new FileWriter(filePath)) {
					for (int key : nums) {
						if (deco.containsKey(key)) {
							String result = deco.get(key); // compare the key and word
							// System.out.println(result);
							fw.write(String.valueOf(result) + " ");
						}

					}
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			System.out.println("Choose an option");
			option = scanner.nextInt();// ask the option again
		}

	}// end of main method

	/*
	 * Terminal Progress Meter ----------------------- You might find the progress
	 * meter below useful. The progress effect works best if you call this method
	 * from inside a loop and do not call System.out.println(....) until the
	 * progress meter is finished.
	 * 
	 * Please note the following carefully:
	 * 
	 * 1) The progress meter will NOT work in the Eclipse console, but will work on
	 * Windows (DOS), Mac and Linux terminals.
	 * 
	 * 2) The meter works by using the line feed character "\r" to return to the
	 * start of the current line and writes out the updated progress over the
	 * existing information. If you output any text between calling this method,
	 * i.e. System.out.println(....), then the next call to the progress meter will
	 * output the status to the next line.
	 * 
	 * 3) If the variable size is greater than the terminal width, a new line escape
	 * character "\n" will be automatically added and the meter won't work properly.
	 * 
	 * 
	 */

	public static void printProgress(int index, int total) {
		if (index > total)
			return; // Out of range
		int size = 50; // Must be less than console width
		char done = '█'; // Change to whatever you like.
		char todo = '░'; // Change to whatever you like.

		// Compute basic metrics for the meter
		int complete = (100 * index) / total;
		int completeLen = size * complete / 100;

		/*
		 * A StringBuilder should be used for string concatenation inside a loop.
		 * However, as the number of loop iterations is small, using the "+" operator
		 * may be more efficient as the instructions can be optimized by the compiler.
		 * Either way, the performance overhead will be marginal.
		 */
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < size; i++) {
			sb.append((i < completeLen) ? done : todo);
		}

		/*
		 * The line feed escape character "\r" returns the cursor to the start of the
		 * current line. Calling print(...) overwrites the existing line and creates the
		 * illusion of an animation.
		 */
		System.out.print("\r" + sb + "] " + complete + "%");

		// Once the meter reaches its max, move to a new line.
		if (done == total)
			System.out.println("\n");
	}
}