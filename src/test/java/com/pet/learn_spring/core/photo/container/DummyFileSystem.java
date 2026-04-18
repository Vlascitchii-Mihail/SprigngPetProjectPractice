package com.pet.learn_spring.core.photo.container;

import com.pet.learn_spring.core.FileSystem;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class DummyFileSystem extends FileSystem {
    public static final byte[] MINIMAL_JPG = Base64.getDecoder().decode(
            "/9j/4AAQSkZJRgABAQEASABIAAD"
                    + "/2wBDAP////////////////////////////////////////////////////////////"
                    + "//////////////////////////wgALCAABAAEBAREA/8QAFBABAAAAAAAAAAAAAAAAA"
                    + "AAAAP/aAAgBAQABPxA=");

    @Override public long getFreeDiskSpace() { return 1; }
    @Override public byte[] load(String fileName) { return MINIMAL_JPG; }
    @Override public void store(String fileName, byte[] data) {}

}
