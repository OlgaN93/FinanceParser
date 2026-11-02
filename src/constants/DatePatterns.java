package constants;

/**
 * Перечисление форматов даты
 */
public enum DatePatterns {
    DDMMYY ("dd.MM.yy");

    private final String title;

    /**
     * Конструктор
     * @param title формат даты
     */
    DatePatterns(String title) {
        this.title = title;
    }

    /**
     * Возвращает шаблон формата даты
     * @return шаблон формата даты
     */
    public String getTitle() {
        return title;
    }
}
