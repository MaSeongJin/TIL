## NVL
컬럼의 값이 null 값인 경우 지정한 값으로 출력하고, null이 아니면 원래 값을 그대로 출력한다.

NVL(컬럼명, 지정값)
```sql
select ename, comm, sal*12+comm, NVL(comm, 0), sal*12+NVL(comm, 0) 
from emp;
```
ORACLE DB에서 사용
 
## IF NULL
MYSQL에서 NVL처럼 사용 가능
