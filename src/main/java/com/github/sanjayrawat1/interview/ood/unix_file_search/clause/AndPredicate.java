package com.github.sanjayrawat1.interview.ood.unix_file_search.clause;

import com.github.sanjayrawat1.interview.ood.unix_file_search.filesystem.File;

import java.util.List;

/**
 *
 * @author sanjayrawat1
 */
public class AndPredicate implements CompositePredicate {

    private final List<Predicate> operands;

    public AndPredicate(List<Predicate> operands) {
        this.operands = operands;
    }

    @Override
    public boolean isMatch(File inputFile) {
        return operands.stream().allMatch(predicate -> predicate.isMatch(inputFile));
    }
}
