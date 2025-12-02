package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SalaryPolicyTest {

    @Test
    void flagsUnderpaidManager() {
        Employee mgr = new Employee("1","A","B",40000,"");
        Employee s1 = new Employee("2","C","D",50000,"1");
        mgr.addDirectReport(s1);

        Organization org = new Organization(
                Map.of("1", mgr, "2", s1)
        );

        SalaryPolicy policy = new SalaryPolicy();
        List<String> result = policy.evaluate(org);

        assertEquals(1, result.size());
        assertTrue(result.get(0).contains("earns"));
    }
}
