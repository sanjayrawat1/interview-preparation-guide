package com.github.sanjayrawat1.interview.ood.unix_file_search.operator;

import java.util.regex.Pattern;

/**
 *
 * @author sanjayrawat1
 */
public class RegexMatchOperator<T extends String> implements ComparisonOperator<T> {

    @Override
    public boolean isMatch(T attributeValue, T expectedValue) {
        Pattern p = Pattern.compile(expectedValue);
        return p.matcher(attributeValue).matches();
    }
}
