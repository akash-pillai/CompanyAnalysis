package org.example;

import java.util.Collection;
import java.util.Map;

public class Organization {
    private final Map<String, Employee> employees;
    private final Employee ceo;

    public Organization(Map<String, Employee> employees) {
        this.employees = employees;
        this.ceo = employees.values().stream()
                .filter(e -> e.getManagerId() == null)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No CEO found"));
    }

    public Employee getCeo() { return ceo; }
    public Collection<Employee> getAllEmployees() { return employees.values(); }
    public Employee getById(String id) { return employees.get(id); }
}
