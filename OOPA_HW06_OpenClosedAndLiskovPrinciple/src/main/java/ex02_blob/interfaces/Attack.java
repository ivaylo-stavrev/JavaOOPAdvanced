package ex02_blob.interfaces;

import ex02_blob.models.Blob;

public interface Attack {
    void execute(Blob attacker, Blob target);
}
