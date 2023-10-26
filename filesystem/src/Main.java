public class Main {
    public static void main(String[] args) {
        Admin naomi = new Admin("naomi@vaticle.com");
        User amos = new User("amos@vaticle.com");
        UserGroup engineers = new UserGroup("engineers", naomi);
        File benchmark = new File("/amos/benchmark-results.xlsx", amos);
        File roadmap = new File("/vaticle/feature-roadmap.pdf", engineers);
    }
}
