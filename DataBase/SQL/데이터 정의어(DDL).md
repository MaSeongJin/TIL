## 데이터 정의어(DDL)

DDL(Data Definition Language) : 테이블 생성, 수정, 삭제

### **CREATR TABLE**

```sql
-- 데이터를 저장하는 새로운 테이블을 생성
-- EX) CREATE TABLE table_name (
--     column_name, data_type expr,
--     ....
--     );
-- 테이블 이름, 컬럼명, 컬럼 자료형 및 크기를 지정

-- 사원번호, 사원이름, 급여 3개의 칼럼으로 구성된 emp01 테이블 생성
CREATE TABLE emp01 (
empno NUMBER(4),
ename VARCHAR2(10),
sal NUMBER(7, 2)
);

-- CREATE TABLE 문에서 서브 쿼리를 사용하여 이미 있는 테이블과 
-- 동일한 구조의 내용을 갖는 새로운 테이블을 생성할 수 있다
CREATE TABLE emp02
AS
SELECT * FROM emp;

-- 기존 테이블에서 원하는 칼럼만 선택적으로 복사해서 생성 가능
-- 기존 테이블의 데이터까지 복사
CREATE TABLE emp03
AS
SELECT empno, ename FROM emp;

-- WHERE 조건 절에 항상 거짓이 되는 조건을 지정하여 데이터 값을 제외한 칼럼 생성 가능
CREATE TABLE emp04
AS
SELECT * FROM emp WHERE 1=0;
```

### **ALTER TABLE**

기존 테이블의 구조를 변경하기 위해서 사용되는 DDL(Data Definition Language) 명령문으로 칼럼의 추가, 삭제, 타입이나 길이를 변경할 때 사용한다.

#### ADD

- 기존 테이블에 새로운 칼럼을 추가
  
- 새로운 칼럼은 테이블의 맨 마지막에 추가되므로, 자신이 원하는 위치에 만들어 넣을 수 없음
  

```sql
-- ALTER TABLE table_name
-- ADD(column_name, data_type, ....)

-- emp01 테이블에 job 칼럼 추가
ALTER TABLE emp01
ADD(job VARCHAR2(9));
```

#### RENAME

칼럼 이름 수정

```sql
-- ALTER TABLE table_name
-- RENAME COLUMN '현재 칼럼명' to '수정 칼럼명';

ALTER TABLE emp04
RENAME COLUMN mgr to mgrno;
```

#### MODIFY

- 기존 칼럼의 속성을 변경
  
- 속성을 변경한다는 것은 칼럼에 대한 데이터 타입이나 크기 등을 변경하는 것
  

```sql
-- ALTER TABLE table_name
-- MODIFY(column_name, data_type, ....);

-- job 칼럼의 최대 30 글자까지 저장할 수 있게 수정
ALTER TABLE emp01
MODIFY(job VARCHAR2(30));
```

#### DROP

```sql
-- 테이블에서 사용중인 칼럼을 삭제
-- ALTER TABLE table_name
-- DROP COLUMN column_name;

-- emp01 테이블의 jop 칼럼 삭제
ALTER TABLE emp01
DROP COLUMN job;

-- SET UNUSED 옵션
-- 칼럼을 삭제하는 것은 아니지만 칼럼의 사용을 논리적으로 제한
-- UNUSED 실행 후에는 다시 사용할 수 없음

ALTER TABLE emp02
SET UNUSED(sal);

-- 테이블 삭제
-- DROP TABLE '삭제 테이블'

-- emp01 테이블 삭제(휴지통 보관)
DROP TABLE emp01;

-- 휴지통 데이터 조회
SELECT * FROM recyclebin;

-- 휴지통에 있는 테이블 복구
-- FLASHBACK TABLE table_name TO BEFORE DROP;
FLASHBACK TABLE emp01 TO BEFORE DROP;

-- 휴지통 거치지 않고 삭제
-- DROP TABLE table_name PURGE;
DROP TABLE emp01 PURGE;

-- 테이블의 모든 row(행) 삭제
-- TRUNCATE TABLE table_name;

-- emp02 테이블의 모든 행 삭제
TRUNCATE TABLE emp02;
```

### **RENAME**

```sql
-- 테이블 이름 변경
-- RENAME table_name TO new_table_name;

-- emp02 테이블명 변경
RENAME emp02 TO newemp02;
```
