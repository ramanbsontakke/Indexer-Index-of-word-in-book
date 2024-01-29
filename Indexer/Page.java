package project1;

public class Page extends BookIndexer {
	

	private int pageNumber;
    private String content;

    public Page(int pageNumber, String content) {
        this.pageNumber = pageNumber;
        this.content = content;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public String getContent() {
        return content;
    }
}
