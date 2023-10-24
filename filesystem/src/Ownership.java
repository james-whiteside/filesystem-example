import java.util.Set;
import java.util.stream.Collectors;

public abstract class Ownership {
    Owned owned;
    Owner owner;

    Ownership(Owned owned, Owner owner) {
        this.owned = owned;
        this.owner = owner;
    }

    interface Owned {
        Ownership getOwnership();

        default Owner getOwner() {
            return getOwnership().owner;
        }
    }

    interface Owner {
        Set<Ownership> getOwnerships();

        default Set<Owned> getOwned() {
            return getOwnerships().stream()
                    .map(ownership -> ownership.owned)
                    .collect(Collectors.toSet());
        }
    }
}
