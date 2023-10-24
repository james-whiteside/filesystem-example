public class Name extends Id {
    protected Name(String value) {
        super(value);
    }

    interface Key extends Id.Key<Name> {
        void setId(Name name);
        Name getId();

        default void setName(Name name) {
            setId(name);
        }

        default Name getName() {
            return getId();
        }
    }
}
