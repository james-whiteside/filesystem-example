import java.util.Set;
import java.util.stream.Collectors;

public class GroupOwnership extends Ownership {
    GroupOwnership(Owned owned, Owner owner) {
        super(owned, owner);
        owned.setOwnership(this);
        owner.addGroupOwnership(this);
    }

    Owned owned() {
        return (Owned) this.owned;
    }

    Owner owner() {
        return (Owner) this.owner;
    }

    public interface Owned extends Ownership.Owned {
        void setOwnership(GroupOwnership ownership);
    }

    public interface Owner extends Ownership.Owner {
        void addGroupOwnership(GroupOwnership ownership);

        default Set<GroupOwnership> getGroupOwnerships() {
            return getOwnerships().stream()
                    .filter(ownership -> ownership instanceof GroupOwnership)
                    .map(ownership -> (GroupOwnership) ownership)
                    .collect(Collectors.toSet());
        }

        default Set<Owned> getOwnedGroups() {
            return getGroupOwnerships().stream()
                    .map(GroupOwnership::owned)
                    .collect(Collectors.toSet());
        }
    }
}
