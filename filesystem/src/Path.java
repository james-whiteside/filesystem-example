public class Path extends Id {
    public Path(String value) {
        super(value);
    }

    interface Key extends Id.Key<Path> {
        void setId(Path path);
        Path getId();
        default void setPath(Path path) {
            setId(path);
        }

        default Path getPath() {
            return getId();
        }
    }
}
