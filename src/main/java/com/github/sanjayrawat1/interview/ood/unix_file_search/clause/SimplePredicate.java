package com.github.sanjayrawat1.interview.ood.unix_file_search.clause;

import com.github.sanjayrawat1.interview.ood.unix_file_search.filesystem.File;
import com.github.sanjayrawat1.interview.ood.unix_file_search.filesystem.FileAttribute;
import com.github.sanjayrawat1.interview.ood.unix_file_search.operator.ComparisonOperator;

/**
 *
 * @author sanjayrawat1
 */
public class SimplePredicate<T> implements Predicate {

    private final FileAttribute attributeName;

    private final ComparisonOperator<T> operator;

    private final T expectedValue;

    public SimplePredicate(FileAttribute attributeName, ComparisonOperator<T> operator, T expectedValue) {
        this.attributeName = attributeName;
        this.operator = operator;
        this.expectedValue = expectedValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean isMatch(File inputFile) {
        // extract the actual value of the attribute from the file
        Object actualValue = inputFile.extract(attributeName);
        // check if the actual value is of the correct type
        if (expectedValue.getClass().isInstance(actualValue)) {
            // perform the comparison using the specified operator
            return operator.isMatch((T) actualValue, expectedValue);
        }
        return false;
    }
}
