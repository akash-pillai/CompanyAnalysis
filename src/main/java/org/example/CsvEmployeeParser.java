package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class CsvEmployeeParser {

    public Organization parse(Path path) throws IOException {
        Map<String, Employee> map = new HashMap<>();

        try (BufferedReader br = Files.newBufferedReader(path)) {
            br.readLine(); // header
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",", -1);
                Employee e = new Employee(
                        p[0].trim(), p[1].trim(), p[2].trim(),
                        Integer.parseInt(p[3].trim()),
                        p[4].trim().isEmpty() ? null : p[4].trim()
                );
                map.put(e.getId(), e);
            }
        }

        // build hierarchy
        for (Employee e : map.values()) {
            if (e.getManagerId() != null) {
                Employee mgr = map.get(e.getManagerId());
                if (mgr != null) mgr.addDirectReport(e);
            }
        }

        return new Organization(map);
    }
}
