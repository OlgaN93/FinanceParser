package constants;

public enum DatePatterns {
    DDMMYY ("dd.MM.yy");

    private final String title;

    DatePatterns(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
