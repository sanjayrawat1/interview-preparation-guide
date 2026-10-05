package com.github.sanjayrawat1.interview.ood.unix_file_search.clause;

import com.github.sanjayrawat1.interview.ood.unix_file_search.filesystem.File;

import java.util.List;

/**
 *
 * @author sanjayrawat1
 */
public class OrPredicate implements CompositePredicate {

    private final List<Predicate> operands;

    public OrPredicate(List<Predicate> operands) {
        this.operands = operands;
    }

    @Override
    public boolean isMatch(File inputFile) {
        return operands.stream().anyMatch(predicate -> predicate.isMatch(inputFile));
    }
}
