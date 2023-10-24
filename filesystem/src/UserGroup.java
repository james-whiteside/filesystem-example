import java.util.Set;

public class UserGroup implements Name.Key, GroupOwnership.Owned, ResourceOwnership.Owner {
    Name name;
    Ownership<GroupOwnership, GroupOwnership.Owned, GroupOwnership.Owner> ownership;
    Set<Ownership<ResourceOwnership, ResourceOwnership.Owned, ResourceOwnership.Owner>> resourceOwnerships;

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
    public void setOwnership(Ownership<GroupOwnership, GroupOwnership.Owned, GroupOwnership.Owner> ownership) {
        this.ownership = ownership;
    }

    @Override
    public Ownership<GroupOwnership, GroupOwnership.Owned, GroupOwnership.Owner> getOwnership() {
        return this.ownership;
    }

    @Override
    public void addOwnership(Ownership<ResourceOwnership, ResourceOwnership.Owned, ResourceOwnership.Owner> ownership) {
        this.resourceOwnerships.add(ownership);
    }

    @Override
    public Set<Ownership<ResourceOwnership, ResourceOwnership.Owned, ResourceOwnership.Owner>> getOwnerships() {
        return this.resourceOwnerships;
    }
}
