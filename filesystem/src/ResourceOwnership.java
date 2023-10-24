import java.util.Set;
import java.util.stream.Collectors;

public class ResourceOwnership extends Ownership<
        ResourceOwnership,
        ResourceOwnership.Owned,
        ResourceOwnership.Owner
        > {
    <OWNER extends Owner, OWNED extends Owned> ResourceOwnership(OWNED owned, OWNER owner) {
        super(owned, owner);
    }

    public interface Owned extends Ownership.Owned<ResourceOwnership, Owned, Owner> { }

    public interface Owner extends Ownership.Owner<ResourceOwnership, Owned, Owner> {
        default Set<ResourceOwnership> getResourceOwnerships() {
            return getOwnerships().stream()
                    .filter(ownership -> ownership instanceof ResourceOwnership)
                    .map(ownership -> (ResourceOwnership) ownership)
                    .collect(Collectors.toSet());
        }

        default Set<Owned> getOwnedResources() {
            return getResourceOwnerships().stream()
                    .map(ownership -> ownership.owned)
                    .collect(Collectors.toSet());
        }
    }
}
