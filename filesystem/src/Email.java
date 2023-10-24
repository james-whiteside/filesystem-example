public class Email extends Id {
    protected Email(String value) {
        super(value);
    }

    interface Key extends Id.Key<Email> {
        void setId(Email email);
        Email getId();
        default void setEmail(Email email) {
            setId(email);
        }

        default Email getEmail() {
            return getId();
        }
    }
}
