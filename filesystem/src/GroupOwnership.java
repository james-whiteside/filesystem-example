import java.util.Set;
import java.util.stream.Collectors;

public class GroupOwnership extends Ownership<
        GroupOwnership,
        GroupOwnership.Owned,
        GroupOwnership.Owner
        > {
    <OWNER extends Owner, OWNED extends Owned> GroupOwnership(OWNED owned, OWNER owner) {
        super(owned, owner);
    }

    public interface Owned extends Ownership.Owned<GroupOwnership, Owned, Owner> { }

    public interface Owner extends Ownership.Owner<GroupOwnership, Owned, Owner> {
        default Set<GroupOwnership> getGroupOwnerships() {
            return getOwnerships().stream()
                    .filter(ownership -> ownership instanceof GroupOwnership)
                    .map(ownership -> (GroupOwnership) ownership)
                    .collect(Collectors.toSet());
        }

        default Set<Owned> getOwnedGroups() {
            return getGroupOwnerships().stream()
                    .map(ownership -> ownership.owned)
                    .collect(Collectors.toSet());
        }
    }
}
