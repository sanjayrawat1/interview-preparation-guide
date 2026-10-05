package com.github.sanjayrawat1.interview.ood.unix_file_search.operator;

/**
 *
 * @author sanjayrawat1
 */
public interface ComparisonOperator<T> {

    boolean isMatch(final T attributeValue, final T expectedValue);
}
