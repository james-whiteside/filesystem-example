import java.util.HashSet;
import java.util.Set;

public class User implements Email.Key, ResourceOwnership.Owner {
    Email email;
    Set<Ownership<ResourceOwnership, ResourceOwnership.Owned, ResourceOwnership.Owner>> resourceOwnerships;

    User(Email email) {
        setEmail(email);
        resourceOwnerships = new HashSet<>();
    }

    User(String email) {
        this(new Email(email));
    }

    @Override
    public void setId(Email email) {
        this.email = email;
    }

    @Override
    public Email getId() {
        return this.email;
    }

    @Override
    public void addOwnership(Ownership<ResourceOwnership, ResourceOwnership.Owned, ResourceOwnership.Owner> ownership) {
        resourceOwnerships.add(ownership);
    }

    @Override
    public Set<Ownership<ResourceOwnership, ResourceOwnership.Owned, ResourceOwnership.Owner>> getOwnerships() {
        return resourceOwnerships;
    }
}
