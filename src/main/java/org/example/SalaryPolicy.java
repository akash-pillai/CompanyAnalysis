package org.example;

import java.util.ArrayList;
import java.util.List;

public class SalaryPolicy implements Policy<String> {

    @Override
    public List<String> evaluate(Organization org) {
        List<String> issues = new ArrayList<>();

        for (Employee mgr : org.getAllEmployees()) {
            var subs = mgr.getDirectReports();
            if (subs.isEmpty()) continue;

            double avg = subs.stream().mapToInt(Employee::getSalary).average().orElse(0);
            double min = 1.2 * avg;
            double max = 1.5 * avg;

            if (mgr.getSalary() < min) {
                issues.add(String.format(
                        "%s earns %.2f less than minimum threshold",
                        mgr, min - mgr.getSalary()
                ));
            } else if (mgr.getSalary() > max) {
                issues.add(String.format(
                        "%s exceeds maximum threshold by %.2f",
                        mgr, mgr.getSalary() - max
                ));
            }
        }

        return issues;
    }
}
