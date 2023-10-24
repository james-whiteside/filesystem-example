public class Main {
    public static void main(String[] args) {
        User user = new User("kevin@vaticle.com");
        File file = new File("hello-world.tql", user);
        System.out.println(file.getOwner());
        System.out.println(user.getOwned());
    }
}
