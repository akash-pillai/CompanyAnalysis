package org.example;

import java.util.List;

public interface Policy<T> {
    List<String> evaluate(Organization org);
}
