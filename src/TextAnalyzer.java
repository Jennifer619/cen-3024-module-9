import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;

/**
 * This class implements a text analyzer for the poem 'The Raven' to count the occurrence of each word within the poem. 
 * @author Jennifer Miller
 *
 */
public class TextAnalyzer {
	/**
	 * This method pulls from the text file to generate the output of the list of words and their number of occurrences. 
	 * @param args
	 */
	public static void main(String args[]) {
		Map<String, Integer> wordMap = buildWordMap("src//TheRaven.txt");
		List<Entry<String, Integer>> list = sortByValueInDecreasingOrder(wordMap);
		System.out.println("List of repeated words");
		for (Map.Entry<String, Integer> entry : list) {
			if (entry.getValue() > 1) {
				System.out.println(entry.getKey() + " => " + entry.getValue());
			}
		}
	}
	/**
	 * This method creates the pattern for the words in the poem and compiles all of the words to lowercase. 
	 * @param fileName
	 * @return compiled map
	 */
	private static Map<String, Integer> buildWordMap(String fileName) {
		// TODO Auto-generated method stub
		Map<String, Integer> wordMap = new HashMap<>();
		try (FileInputStream fis = new FileInputStream(fileName);
		DataInputStream dis = new DataInputStream(fis);
		BufferedReader br = new BufferedReader(new InputStreamReader(dis))) {
			Pattern pattern = Pattern.compile("\\s+");
			String line = null;
			while ((line = br.readLine()) != null) {
				line = line.toLowerCase();
				String[] words = pattern.split(line);
				for (String word : words) {
					if (wordMap.containsKey(word)) {
						wordMap.put(word, (wordMap.get(word) + 1));
					} else {
					wordMap.put(word, 1);
					}
				}
			}
		} catch (IOException ioex) {
		ioex.printStackTrace();
		}
		return wordMap;
	}
	/**
	 * This method sorts the list of words by the number of their occurrences in decreasing order. 
	 * @param wordMap
	 * @return sorted list
	 */
	private static List<Entry<String, Integer>> sortByValueInDecreasingOrder(Map<String, Integer> wordMap) {
		// TODO Auto-generated method stub
		Set<Entry<String, Integer>> entries = wordMap.entrySet();
		List<Entry<String, Integer>> list = new ArrayList<>(entries);
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});
		return list;
	}
}
