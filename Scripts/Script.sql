select * from title;

-- 해당 직책을 가지고 있는 사원 목록을 검색 
select empname, empno from employee e join title t on e.title = t.tno where tno = 1;

desc employee;
desc title;
select * from title;
desc department;

select empname, empno from employee e join department d on e.dept = d.deptNo where dept= 1;