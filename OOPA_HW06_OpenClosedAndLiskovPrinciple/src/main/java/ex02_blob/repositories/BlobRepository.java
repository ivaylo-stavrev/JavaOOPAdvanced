package ex02_blob.repositories;

import ex02_blob.interfaces.Repository;
import ex02_blob.models.Blob;

import java.util.*;

public class BlobRepository implements Repository<Blob> {
    Map<String, Blob> blobs;

    public BlobRepository() {
        this.blobs = new LinkedHashMap<>();
    }

    @Override
    public void add(Blob blob) {
        this.blobs.put(blob.getName(), blob);
    }

    @Override
    public Blob getByName(String name) {
        return this.blobs.get(name);
    }

    @Override
    public Collection<Blob> findAll() {
        return this.blobs.values();
    }
}
