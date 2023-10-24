import java.util.Set;
import java.util.stream.Collectors;

public abstract class Ownership<
        OWNERSHIP extends Ownership<OWNERSHIP, OWNED, OWNER>,
        OWNED extends Ownership.Owned<OWNERSHIP, OWNED, OWNER>,
        OWNER extends Ownership.Owner<OWNERSHIP, OWNED, OWNER>
        > {
    OWNED owned;
    OWNER owner;

    Ownership(OWNED owned, OWNER owner) {
        this.owned = owned;
        this.owner = owner;
        owned.setOwnership(this);
        owner.addOwnership(this);
    }

    interface Owned<
            OWNERSHIP extends Ownership<OWNERSHIP, OWNED, OWNER>,
            OWNED extends Owned<OWNERSHIP, OWNED, OWNER>,
            OWNER extends Owner<OWNERSHIP, OWNED, OWNER>
            > {
        void setOwnership(Ownership<OWNERSHIP, OWNED, OWNER> ownership);
        Ownership<OWNERSHIP, OWNED, OWNER> getOwnership();

        default Owner<OWNERSHIP, OWNED, OWNER> getOwner() {
            return getOwnership().owner;
        }
    }

    interface Owner<
            OWNERSHIP extends Ownership<OWNERSHIP, OWNED, OWNER>,
            OWNED extends Owned<OWNERSHIP, OWNED, OWNER>,
            OWNER extends Owner<OWNERSHIP, OWNED, OWNER>
            > {
        void addOwnership(Ownership<OWNERSHIP, OWNED, OWNER> ownership);
        Set<Ownership<OWNERSHIP, OWNED, OWNER>> getOwnerships();

        default Set<Owned<OWNERSHIP, OWNED, OWNER>> getOwned() {
            return getOwnerships().stream()
                    .map(ownership -> ownership.owned)
                    .collect(Collectors.toSet());
        }
    }
}
