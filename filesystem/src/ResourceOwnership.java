import java.util.Set;
import java.util.stream.Collectors;

public class ResourceOwnership extends Ownership {
    ResourceOwnership(Owned owned, Owner owner) {
        super(owned, owner);
        owned.setOwnership(this);
        owner.addResourceOwnership(this);
    }

    Owned owned() {
        return (Owned) this.owned;
    }

    Owner owner() {
        return (Owner) this.owner;
    }

    public interface Owned extends Ownership.Owned {
        void setOwnership(ResourceOwnership ownership);
    }

    public interface Owner extends Ownership.Owner {
        void addResourceOwnership(ResourceOwnership ownership);

        default Set<ResourceOwnership> getResourceOwnerships() {
            return getOwnerships().stream()
                    .filter(ownership -> ownership instanceof ResourceOwnership)
                    .map(ownership -> (ResourceOwnership) ownership)
                    .collect(Collectors.toSet());
        }

        default Set<Owned> getOwnedResources() {
            return getResourceOwnerships().stream()
                    .map(ResourceOwnership::owned)
                    .collect(Collectors.toSet());
        }
    }
}
