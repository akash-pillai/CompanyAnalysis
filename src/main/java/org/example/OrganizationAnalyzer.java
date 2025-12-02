package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrganizationAnalyzer {

    private final List<Policy<String>> policies = new ArrayList<>();

    public OrganizationAnalyzer addPolicy(Policy<String> p) {
        policies.add(p);
        return this;
    }

    public List<String> analyze(Organization org) {
        return policies.stream()
                .flatMap(p -> p.evaluate(org).stream())
                .collect(Collectors.toList());
    }
}
