package system;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.*;

public class MetaData {

    public static void printCreationTime(Path file) throws IOException {
        BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
        System.out.println("Creation Time: " + attr.creationTime());
    }

    public static void printLastAccessTime(Path file) throws IOException {
        BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
        System.out.println("Last Access Time: " + attr.lastAccessTime());
    }

    public static void printLastModifiedTime(Path file) throws IOException {
        BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
        System.out.println("Last Modified Time: " + attr.lastModifiedTime());
    }

    public static void printIsDirectory(Path file) throws IOException {
        BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
        System.out.println("Is Directory: " + attr.isDirectory());
    }


    public static void printSize(Path file) throws IOException {
        BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
        System.out.println("Size: " + attr.size());
    }


    public static void printFileStoreAttributes(Path file) throws IOException {
        FileStore store = Files.getFileStore(file);

        long total = store.getTotalSpace() / 1024;
        long used = (store.getTotalSpace() - store.getUnallocatedSpace()) / 1024;
        long avail = store.getUsableSpace() / 1024;

        System.out.println("Total Space: " + total + " KB");
        System.out.println("Used Space: " + used + " KB");
        System.out.println("Available Space: " + avail + " KB");
    }
}
