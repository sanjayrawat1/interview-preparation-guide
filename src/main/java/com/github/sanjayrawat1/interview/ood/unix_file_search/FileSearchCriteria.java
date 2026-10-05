package com.github.sanjayrawat1.interview.ood.unix_file_search;

import com.github.sanjayrawat1.interview.ood.unix_file_search.clause.Predicate;
import com.github.sanjayrawat1.interview.ood.unix_file_search.filesystem.File;

/**
 * <p>
 * The FileSearchCriteria class acts as a bridge between FileSearch and the predicate-based conditions that determine
 * which files match, delegating the evaluation to a Predicate, whether a SimplePredicate or CompositePredicate.
 * <p>
 * Wrapper class that encapsulates a search condition for file matching
 *
 * @author sanjayrawat1
 */
public class FileSearchCriteria {

    // The predicate that defines what makes a file match
    private final Predicate predicate;

    public FileSearchCriteria(Predicate predicate) {
        this.predicate = predicate;
    }

    public boolean isMatch(final File inputFile) {
        return predicate.isMatch(inputFile);
    }
}
