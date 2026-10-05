package com.github.sanjayrawat1.interview.ood.unix_file_search.clause;

import com.github.sanjayrawat1.interview.ood.unix_file_search.filesystem.File;

/**
 *
 * @author sanjayrawat1
 */
public interface Predicate {

    boolean isMatch(File inputFile);
}
