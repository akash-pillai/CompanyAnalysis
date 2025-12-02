package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReportingLinePolicyTest {




    @Test
    void detectsTooLongReportingLines() {
        Employee ceo = new Employee("1","CEO","X",100000,null);
        Employee m1 = new Employee("2","M1","X",90000,"1");
        Employee m2 = new Employee("3","M2","X",80000,"2");
        Employee m3 = new Employee("4","M3","X",70000,"3");
        Employee m4 = new Employee("5","M4","X",60000,"4");
        Employee emp = new Employee("6","E","X",50000,"5");

        ceo.addDirectReport(m1);
        m1.addDirectReport(m2);
        m2.addDirectReport(m3);
        m3.addDirectReport(m4);
        m4.addDirectReport(emp);

        Organization org = new Organization(
                Map.of("1",ceo,"2",m1,"3",m2,"4",m3,"5",m4,"6",emp)
        );

        ReportingLinePolicy p = new ReportingLinePolicy(3);
        List<String> result = p.evaluate(org);

        assertEquals(1, result.size());
        assertTrue(result.get(0).contains("has"));
    }
}
