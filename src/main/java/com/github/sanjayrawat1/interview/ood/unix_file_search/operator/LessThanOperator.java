package com.github.sanjayrawat1.interview.ood.unix_file_search.operator;

/**
 *
 * @author sanjayrawat1
 */
public class LessThanOperator<T extends Number> implements ComparisonOperator<T> {

    @Override
    public boolean isMatch(T attributeValue, T expectedValue) {
        return Double.compare(attributeValue.doubleValue(), expectedValue.doubleValue()) < 0;
    }
}
