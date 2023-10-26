import java.util.Set;
import java.util.Map;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Admin naomi = new Admin("naomi@vaticle.com");
        User amos = new User("amos@vaticle.com");
        UserGroup engineers = new UserGroup("engineers", naomi);
        File benchmark = new File("/amos/benchmark-results.xlsx", amos);
        File roadmap = new File("/vaticle/feature-roadmap.pdf", engineers);

        Set<Object> fileSystemObjects = Set.of(naomi, amos, engineers, benchmark, roadmap);

        Stream<Map<String, String>> fileSystemOwnerships = fileSystemObjects.stream()
                .filter(object -> object instanceof Ownership.Owned)
                .map(object -> (Ownership.Owned) object)
                .map(owned -> Map.of(
                        "owned", owned,
                        "owner", owned.getOwner(),
                        "ownership", owned.getOwnership()
                        ))
                .filter(ownership -> ownership.get("owned") instanceof Id.Key<?> && ownership.get("owner") instanceof Id.Key<?>)
                .map(ownership -> Map.of(
                        "owned-id", ((Id.Key<?>) ownership.get("owned")).getId().value,
                        "owner-id", (((Id.Key<?>) ownership.get("owner")).getId()).value,
                        "ownership-type", ownership.get("ownership").getClass().getSimpleName()
                        ));

        fileSystemOwnerships.forEach(System.out::println);
    }
}
