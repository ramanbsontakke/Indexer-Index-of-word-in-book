package project1;

import java.util.Arrays;
import java.util.List;

public class Main extends Page{
	public Main(int pageNumber, String content) {
		super(pageNumber, content);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
        List<String> pageFiles = Arrays.asList("Page1.txt", "Page2.txt", "Page3.txt");
        String excludeWordsFile = "exclude-words.txt";
        String indexFile = "index.txt";
        

        BookIndexer indexer = new BookIndexer();
        indexer.readExcludeWords(excludeWordsFile);
        indexer.readPages(pageFiles);
        indexer.generateIndex();
        indexer.writeIndexToFile(indexFile);
        System.out.println("indexfile");
    }
}


