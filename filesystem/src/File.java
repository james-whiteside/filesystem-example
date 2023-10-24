public class File extends Resource<Path> implements Path.Key {
    Path path;
    Ownership ownership;

    File(Path path, ResourceOwnership.Owner owner) {
        this.path = path;
        new ResourceOwnership(this, owner);
    }

    File(String path, ResourceOwnership.Owner owner) {
        this(new Path(path), owner);
    }

    @Override
    public void setId(Path path) {
        this.path = path;
    }

    @Override
    public Path getId() {
        return path;
    }

    @Override
    public void setOwnership(ResourceOwnership ownership) {
        this.ownership = ownership;
    }

    @Override
    public Ownership getOwnership() {
        return this.ownership;
    }
}
