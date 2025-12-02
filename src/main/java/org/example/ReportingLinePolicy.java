package org.example;

import java.util.ArrayList;
import java.util.List;

public class ReportingLinePolicy implements Policy<String> {

    private final int threshold;

    public ReportingLinePolicy(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public List<String> evaluate(Organization org) {

        List<String> issues = new ArrayList<>();

        for (Employee e : org.getAllEmployees()) {
            int count = countManagersBetween(e, org);

            if (count > threshold) {
                issues.add(String.format(
                        "%s has %d managers between them and the CEO",
                        e, count
                ));
            }
        }
        return issues;
    }


    private int countManagersBetween(Employee e, Organization org) {
        int count = 0;
        String mgrId = e.getManagerId();

        while (mgrId != null) {
            Employee mgr = org.getById(mgrId);
            if (mgr == null) break;
            if (mgr.getManagerId() == null) break; // reached CEO, don't count CEO
            count++;
            mgrId = mgr.getManagerId();
        }
        return count;
    }

}
