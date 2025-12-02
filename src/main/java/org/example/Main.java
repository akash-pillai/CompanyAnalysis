package org.example;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        Path csv = Paths.get(args[0]);

        Organization org = new CsvEmployeeParser().parse(csv);

        OrganizationAnalyzer analyzer = new OrganizationAnalyzer()
                .addPolicy(new SalaryPolicy())
                .addPolicy(new ReportingLinePolicy(4));

        analyzer.analyze(org).forEach(System.out::println);
    }
}