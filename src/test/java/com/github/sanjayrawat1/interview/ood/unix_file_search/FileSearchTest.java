package com.github.sanjayrawat1.interview.ood.unix_file_search;

import com.github.sanjayrawat1.interview.ood.unix_file_search.clause.AndPredicate;
import com.github.sanjayrawat1.interview.ood.unix_file_search.clause.Predicate;
import com.github.sanjayrawat1.interview.ood.unix_file_search.clause.SimplePredicate;
import com.github.sanjayrawat1.interview.ood.unix_file_search.filesystem.File;
import com.github.sanjayrawat1.interview.ood.unix_file_search.filesystem.FileAttribute;
import com.github.sanjayrawat1.interview.ood.unix_file_search.operator.EqualsOperator;
import com.github.sanjayrawat1.interview.ood.unix_file_search.operator.RegexMatchOperator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author sanjayrawat1
 */
class FileSearchTest {

    @Test
    void testFileSearch() {
        System.out.println("=== Testing File Search System ===");

        System.out.println("--- Creating File System Structure ---");
        File root = new File(true, 0, "sanjay", "root");
        File a = new File(false, 2000, "rahul", "a");
        File b = new File(false, 3000, "sanjay", "b");
        System.out.println("✓ Created root directory: 'root' (owner: sanjay, size: 0)");
        System.out.println("✓ Created file 'a': (owner: rahul, size: 2000, isDirectory: false)");
        System.out.println("✓ Created file 'b': (owner: sanjay, size: 3000, isDirectory: false)");

        root.addEntry(a);
        root.addEntry(b);
        System.out.println("✓ Added files 'a' and 'b' to root directory");

        System.out.println("--- Building Search Criteria ---");
        System.out.println("✓ Creating AND predicate with two conditions:");
        System.out.println("  1. File must NOT be a directory (isDirectory = false)");
        System.out.println("  2. Owner name must match regex pattern 'ge.*' (starts with 'ge')");
        Predicate notADirectoryPredicate = new SimplePredicate<>(FileAttribute.IS_DIRECTORY, new EqualsOperator<>(), false);
        Predicate ownerNameRegexMatchPredicate = new SimplePredicate<>(FileAttribute.OWNER, new RegexMatchOperator<>(), "sa.*");

        Predicate andPredicate = new AndPredicate(List.of(notADirectoryPredicate, ownerNameRegexMatchPredicate));

        FileSearchCriteria criteria = new FileSearchCriteria(andPredicate);
        System.out.println("✓ Search criteria created successfully");

        System.out.println("--- Executing File Search ---");
        FileSearch fileSearch = new FileSearch();
        List<File> result = fileSearch.search(root, criteria);
        System.out.println("✓ Search completed");
        System.out.println("✓ Found " + result.size() + " file(s) matching criteria");

        System.out.println("--- Analyzing Results ---");
        System.out.println("✓ Expected: Only file 'b' should match because:");
        System.out.println("  - File 'a': owner='rahul' (doesn't match 'ge.*')");
        System.out.println("  - File 'b': owner='sanjay' (matches 'ge.*') and is not a directory");

        assertEquals(1, result.size());
        assertEquals("b", result.getFirst().getFilename());
        System.out.println("✓ Result verified: Found 1 file");
        System.out.println("✓ File name verified: 'b'");
        System.out.println("=== File Search Test Completed Successfully ===");
    }

}