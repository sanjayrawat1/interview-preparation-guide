package com.github.sanjayrawat1.interview.ood.unix_file_search.clause;

import com.github.sanjayrawat1.interview.ood.unix_file_search.filesystem.File;

/**
 *
 * @author sanjayrawat1
 */
public class NotPredicate implements CompositePredicate {

    private final Predicate operand;

    public NotPredicate(Predicate operand) {
        this.operand = operand;
    }

    @Override
    public boolean isMatch(File inputFile) {
        return !operand.isMatch(inputFile);
    }
}
