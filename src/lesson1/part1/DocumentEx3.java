package lesson1.part1;

public class DocumentEx3 {

    String title;
    String content;

    public DocumentEx3(String _title, String _content) {
        title = _title;
        content = _content;
    }

    public DocumentEx3() {
    }

    void printInfo() {
        System.out.println(title + System.lineSeparator() + content);
    }




    /* ---------------------------------- */
    public static void main(String[] args) {
//        DocumentEx3 document1 = new DocumentEx3("document1", "Content of document1");
        new DocumentEx3("document1", "Content of document1").printInfo();

//        DocumentEx3 document2 = new DocumentEx3("document2", "Content of document2");
        new DocumentEx3("document2", "Content of document2").printInfo();
    }
}


