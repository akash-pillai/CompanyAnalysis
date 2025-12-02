1. CSV Format Assumptions

        The CSV file always contains the following headers in this order:
        Id,firstName,lastName,salary,managerId
        
        managerId is either:
              an empty string → meaning this employee is the CEO, or
              a valid employee ID present somewhere in the same file.
        
        No commas appear inside the fields.
        (Simple .split(",") is safe without a CSV library.)
        
        Salary values are integers and valid.


 2.Organization Structure Assumptions

    There is exactly one CEO in the input dataset.
    
    The reporting structure forms a tree (no cycles).
    
    If an employee references a manager ID that does not exist in the CSV, the chain is considered broken at that point and evaluation stops for that chain.

3. Salary Policy Assumptions

        A “manager” is defined strictly as an employee who has at least one direct report.
        
        Salary rule uses direct subordinates only, not full hierarchy.
        
        Average subordinate salary = arithmetic mean of direct reports.
        
        Valid salary range =
        
        Minimum = 1.2 × average subordinate salary
        
        Maximum = 1.5 × average subordinate salary
        
        If salary is outside the range, the output reports by how much it deviates from the allowable limit.
4. Reporting Line (Hierarchy Depth) Assumptions

        “Number of managers between an employee and the CEO” counts only intermediate managers:
        
        Does not count the employee
        
        Does not count the CEO
        
        The requirement “more than 4 managers between them and the CEO” means:
        
        If the chain has 5 or more intermediate managers → flag the employee
        
        If a reporting chain breaks due to missing manager in the CSV, the chain is terminated and treated as shorter.
5. Input / Execution Assumptions
    
        The program takes exactly one command-line argument: path to the CSV file.
        
        Errors such as unreadable files terminate execution with a meaningful message.