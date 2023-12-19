import java.util.Set;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Instantiate an admin with the specified email.
        Admin cedric = new Admin("cedric@vaticle.com");

        // Instantiate a user with the specified email.
        User jimmy = new User("jimmy@vaticle.com");

        // Instantiate a user group with the specified name, and assign the admin Cedric as its owner.
        UserGroup engineers = new UserGroup("engineers", cedric);

        // Instantiate a file with the specified path, and assign the user Jimmy as its owner.
        File benchmark = new File("/jimmy/benchmark-results.xlsx", jimmy);

        // Instantiate a file with the specified path, and assign the Engineers group as its owner.
        File roadmap = new File("/vaticle/feature-roadmap.pdf", engineers);

        // Retrieve the ID of the feature roadmap's owner.
        System.out.println(((Id.Key<?>) roadmap.getOwner()).getId().value);

        // Retrieve the IDs of all resources owned by Jimmy.
        System.out.println(jimmy.getOwnedResources().stream()
                .map(resource -> ((Id.Key<?>) resource).getId().value)
                .collect(Collectors.toSet())
        );

        // Make a collection of all filesystem objects.
        Set<Id.Key<?>> fileSystemObjects = Set.of(cedric, jimmy, engineers, benchmark, roadmap);

        // Retrieve the type and ID of every object in the filesystem.
        fileSystemObjects.stream()
                .map(object -> Map.of(
                        "object-type", object.getClass().getSimpleName(),
                        "object-id", ((Id.Key<?>) object).getId().value
                ))
                .forEach(System.out::println);

        // Retrieve the details of every ownership in the filesystem.
        fileSystemObjects.stream()
                .filter(object -> object instanceof Ownership.Owned)
                .map(object -> (Ownership.Owned) object)
                .map(owned -> Map.of(
                        "owned-id", ((Id.Key<?>) owned).getId().value,
                        "owned-type", owned.getClass().getSimpleName(),
                        "owner-id", (((Id.Key<?>) owned.getOwner()).getId()).value,
                        "owner-type", owned.getOwner().getClass().getSimpleName(),
                        "ownership-type", owned.getOwnership().getClass().getSimpleName()
                ))
                .forEach(System.out::println);
    }
}
