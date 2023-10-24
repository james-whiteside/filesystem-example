import java.util.Set;

public class UserGroup implements Name.Key, GroupOwnership.Owned, ResourceOwnership.Owner {
    Name name;
    Ownership ownership;
    Set<Ownership> ownerships;

    UserGroup(Name name, GroupOwnership.Owner owner) {
        this.name = name;
        new GroupOwnership(this, owner);
    }

    UserGroup(String name, GroupOwnership.Owner owner) {
        this(new Name(name), owner);
    }

    @Override
    public void setId(Name name) {
        this.name = name;
    }

    @Override
    public Name getId() {
        return this.name;
    }

    @Override
    public void setOwnership(GroupOwnership ownership) {
        this.ownership = ownership;
    }

    @Override
    public Ownership getOwnership() {
        return this.ownership;
    }

    @Override
    public void addResourceOwnership(ResourceOwnership ownership) {
        ownerships.add(ownership);
    }

    @Override
    public Set<Ownership> getOwnerships() {
        return this.ownerships;
    }
}
