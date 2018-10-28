# Write your MySQL query statement below

# case when 的 is null 或者 if
select case when max1.Id is null then max2.Salary 
    else null 
    end as SecondHighestSalary 
from (select Id, Salary 
      from Employee 
      order by Salary desc, Id asc
      limit 2) max2
    left outer join (
        select Id, Salary
        from Employee 
        order by Salary desc, Id asc
        limit 1) max1
    on max1.Id = max2.Id
order by max2.Salary asc
limit 1;

# where max1.Id is null
# 一定要用is?
# limit 1;


# 使用limit就好了嘛。。
# select (select distinct salary from Employee order by salary desc limit 1,1) as SecondHighestSalary ;
