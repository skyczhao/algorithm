CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  DECLARE offset INT DEFAULT 0;
  SET offset = N - 1;
  RETURN (
      # Write your MySQL query statement below.
       select Salary from Employee group by Salary order by Salary desc limit offset,1
  );
END