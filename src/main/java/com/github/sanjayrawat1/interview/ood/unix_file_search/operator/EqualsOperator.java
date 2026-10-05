package com.github.sanjayrawat1.interview.ood.unix_file_search.operator;

import java.util.Objects;

/**
 *
 * @author sanjayrawat1
 */
public class EqualsOperator<T> implements ComparisonOperator<T> {

    @Override
    public boolean isMatch(T attributeValue, T expectedValue) {
        return Objects.equals(attributeValue, expectedValue);
    }
}
