select * from title;

-- 해당 직책을 가지고 있는 사원 목록을 검색 
select empname, empno from employee e join title t on e.title = t.tno where tno = 1;

desc employee;
desc title;
select * from title;
desc department;

select empname, empno from employee e join department d on e.dept = d.deptNo where dept= 1;


-- passwd 길이 확인 (단방향 해시함수(MD5)로 암호화) << 그러면 비밀번호는 41이 되어야됨
-- 해시함수는 키값을 찾아내기 힘듦
-- 비밀번호 길이 달라져도 해시함수 길이는 항상 41(byte)
select password('aaa'), length(password('aaa')) from dual;
select password('asdfasdgasdhashdsd'), length(password('asdfsadfasdffasdf')) from dual;

select * from emp_detail;

delete from emp_detail where empno = 1003;

