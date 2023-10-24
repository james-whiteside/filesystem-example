public class Admin extends User implements GroupOwnership.Owner {
    Admin(Email email) {
        super(email);
    }

    Admin(String email) {
        this(new Email(email));
    }

    @Override
    public void addGroupOwnership(GroupOwnership ownership) {
        ownerships.add(ownership);
    }
}
