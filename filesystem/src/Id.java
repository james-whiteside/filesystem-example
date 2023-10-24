public abstract class Id {
    final String value;

    protected Id(String value) {
        this.value = value;
    }

    interface Key<ID extends Id> {
        void setId(ID id);
        ID getId();
    }
}
