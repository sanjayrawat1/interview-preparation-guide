package com.github.sanjayrawat1.interview.ood.unix_file_search;

import com.github.sanjayrawat1.interview.ood.unix_file_search.filesystem.File;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * <p>
 * The FileSearch class orchestrates the search across the filesystem, starting from a root File and
 * leveraging FileSearchCriteria to identify matching files through recursive traversal. It employs a
 * stack-based approach to explore directories efficiently.
 *
 * @author sanjayrawat1
 */
public class FileSearch {

    public List<File> search(File root, FileSearchCriteria criteria) {
        List<File> result = new ArrayList<>();
        Deque<File> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            File next = stack.pop();
            if (criteria.isMatch(next)) {
                result.add(next);
            }

            for (File entry : next.getEntries()) {
                stack.push(entry);
            }
        }
        return result;
    }
}
