package project1;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class BookIndexer 
{
	private List<Page> pages;
    private Set<String> excludeWords;
    private Map<String, Set<Integer>> index;

    public BookIndexer() {
        pages = new ArrayList<>();
        excludeWords = new HashSet<>();
        index = new TreeMap<>();
    }
    public void readPages(List<String> pageFiles) {
        for (int i = 0; i < pageFiles.size(); i++) {
            String fileName = pageFiles.get(i);
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    content.append(line).append(" ");
                }
                Page page = new Page(i + 1, content.toString().toLowerCase());
                pages.add(page);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void readExcludeWords(String excludeWordsFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(excludeWordsFile))) {
            String word;
            while ((word = br.readLine()) != null) {
                excludeWords.add(word.toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void generateIndex() {
        for (Page page : pages) {
            String[] words = page.getContent().split("\\W+");
            Set<Integer> uniquePages = new HashSet<>();
            for (String word : words) {
                word = word.toLowerCase();
                if (!excludeWords.contains(word)) {
                    uniquePages.add(page.getPageNumber());
                    index.putIfAbsent(word, new HashSet<>());
                    index.get(word).add(page.getPageNumber());
                }
            }
        }
    }
    public void writeIndexToFile(String indexFile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(indexFile))) {
            for (String word : index.keySet()) {
                Set<Integer> pagesWithWord = index.get(word);
                StringBuilder pageList = new StringBuilder();
                for (int page : pagesWithWord) {
                    pageList.append(page).append(",");
                }
                pageList.deleteCharAt(pageList.length() - 1); 
                bw.write(word + " : " + pageList.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



