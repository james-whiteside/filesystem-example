import java.util.HashSet;
import java.util.Set;

public class User implements Email.Key, ResourceOwnership.Owner {
    Email email;
    Set<Ownership> ownerships;

    User(Email email) {
        setEmail(email);
        ownerships = new HashSet<>();
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
    public void addResourceOwnership(ResourceOwnership ownership) {
        ownerships.add(ownership);
    }

    @Override
    public Set<Ownership> getOwnerships() {
        return ownerships;
    }
}
