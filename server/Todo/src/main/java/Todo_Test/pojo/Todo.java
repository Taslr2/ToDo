package Todo_Test.pojo;

public class Todo {
    private int id;
    private String title;
    private String details;
    private int isCompleted;
    private String completionDate;
    private String category;
    private int isDeleted;
    private String expectedCompletionDate;
    private int isImportant;
    private int isUrgent;

    public Todo() {
    }

    public Todo(int id, String title, String details, int isCompleted, String completionDate, String category, int isDeleted, String expectedCompletionDate, int isImportant, int isUrgent) {
        this.id = id;
        this.title = title;
        this.details = details;
        this.isCompleted = isCompleted;
        this.completionDate = completionDate;
        this.category = category;
        this.isDeleted = isDeleted;
        this.expectedCompletionDate = expectedCompletionDate;
        this.isImportant = isImportant;
        this.isUrgent = isUrgent;
    }

    /**
     * 获取
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取
     * @return details
     */
    public String getDetails() {
        return details;
    }

    /**
     * 设置
     * @param details
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * 获取
     * @return isCompleted
     */
    public int getIsCompleted() {
        return isCompleted;
    }

    /**
     * 设置
     * @param isCompleted
     */
    public void setIsCompleted(int isCompleted) {
        this.isCompleted = isCompleted;
    }

    /**
     * 获取
     * @return completionDate
     */
    public String getCompletionDate() {
        return completionDate;
    }

    /**
     * 设置
     * @param completionDate
     */
    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
    }

    /**
     * 获取
     * @return category
     */
    public String getCategory() {
        return category;
    }

    /**
     * 设置
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 获取
     * @return isDeleted
     */
    public int getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置
     * @param isDeleted
     */
    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 获取
     * @return expectedCompletionDate
     */
    public String getExpectedCompletionDate() {
        return expectedCompletionDate;
    }

    /**
     * 设置
     * @param expectedCompletionDate
     */
    public void setExpectedCompletionDate(String expectedCompletionDate) {
        this.expectedCompletionDate = expectedCompletionDate;
    }

    public String toString() {
        return "Todo{title = " + title + ", details = " + details + ", isCompleted = " + isCompleted + ", completionDate = " + completionDate + ", category = " + category + ", isDeleted = " + isDeleted + ", expectedCompletionDate = " + expectedCompletionDate + "}";
    }

    /**
     * 获取
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取
     * @return isImportant
     */
    public int getIsImportant() {
        return isImportant;
    }

    /**
     * 设置
     * @param isImportant
     */
    public void setIsImportant(int isImportant) {
        this.isImportant = isImportant;
    }

    /**
     * 获取
     * @return isUrgent
     */
    public int getIsUrgent() {
        return isUrgent;
    }

    /**
     * 设置
     * @param isUrgent
     */
    public void setIsUrgent(int isUrgent) {
        this.isUrgent = isUrgent;
    }
}
