public class File extends Resource<Path> implements Path.Key {
    Path path;
    Ownership<ResourceOwnership, ResourceOwnership.Owned, ResourceOwnership.Owner> ownership;

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
    public void setOwnership(Ownership<ResourceOwnership, ResourceOwnership.Owned, ResourceOwnership.Owner> ownership) {
        this.ownership = ownership;
    }

    @Override
    public Ownership<ResourceOwnership, ResourceOwnership.Owned, ResourceOwnership.Owner> getOwnership() {
        return this.ownership;
    }
}
