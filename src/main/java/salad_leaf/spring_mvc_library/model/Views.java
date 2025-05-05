package salad_leaf.spring_mvc_library.model;

public class Views {
    public interface BookSummary {}
    public interface BookDetails extends  BookSummary {}
    public interface AuthorSummary {}
    public interface AuthorDetails extends AuthorSummary {}
}
