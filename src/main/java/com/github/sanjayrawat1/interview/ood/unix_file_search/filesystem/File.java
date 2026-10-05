package com.github.sanjayrawat1.interview.ood.unix_file_search.filesystem;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * The Predicate interface defines the contract for evaluating whether a File satisfies a search condition,
 * serving as the cornerstone of our system’s filtering logic.
 *
 * @author sanjayrawat1
 */
public class File {

    private final boolean isDirectory;

    private final int size;

    private final String owner;

    private final String filename;

    private final Set<File> entries = new HashSet<>();

    public File(boolean isDirectory, int size, String owner, String filename) {
        this.isDirectory = isDirectory;
        this.size = size;
        this.owner = owner;
        this.filename = filename;
    }

    public Object extract(FileAttribute attributeName) {
        return switch (attributeName) {
            case IS_DIRECTORY -> isDirectory;
            case SIZE -> size;
            case OWNER -> owner;
            case FILENAME -> filename;
        };
    }

    public void addEntry(File entry) {
        entries.add(entry);
    }

    public Set<File> getEntries() {
        return entries;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public int getSize() {
        return size;
    }

    public String getOwner() {
        return owner;
    }

    public String getFilename() {
        return filename;
    }
}
