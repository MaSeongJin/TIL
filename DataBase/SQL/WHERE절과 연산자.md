## WHERE 절
- 원하는 행만 검색할 때에는 행을 제한하는 조건을 select 문에 where 절을 추가한다.
- 조건식 안에는 (= 같다, > 크다, < 작다, >= 크거나 같다, <= 작거나 같다, != 같지 않다)를 사용한다.
- SQL에서 문자열이나 날짜는 반드시 작은따옴표 안에 작성해야 하며, 테이블 내에 저장된 값은 대소문자를 구분한다.
```SQL
SELECT * [column, ...]
FROM table_name
WHERE 조건식;

-- 나이가 24인 항목
SELECT *
FROM name
WHERE age = 24;

-- 이름이 홍길동인 항목
SELECT *
FROM name
WHERE ename = '홍길동';

-- 나이가 24이상인 항목
SELECT *
FROM name
WHERE age >= 24;

-- 나이가 24가 아닌 항목
SELECT *
FROM name
WHERE age != 24;
```
## 논리 연산자(AND, OR, NOT)
```SQL
-- 나이가 24이면서, 직업이 선생님인 사람 확인
SELECT *
FROM name
WHERE age = 24 AND job = 'Teacher';

-- 나이가 24이거나 직업이 선생님인 사람 확인
SELECT *
FROM name
WHERE age = 24 OR job = 'Teacher';

-- 나이가 24가 아닌 사람 확인
SELECT *
FROM name
WHERE NOT age = 24;
```
## BETWEEN A AND B 연산자
```SQL
-- 나이가 24이상이고 40세 이하인 사람 확인
SELECT *
FROM name
WHERE age BETWEEN 24 AND 40;

-- 나이가 24이상이고 40세 이하이지 않은 사람 확인
SELECT *
FROM name
WHERE age NOT BETWEEN 24 AND 40;
```
## IN 연산자
```SQL
-- 나이가 35이거나, 45이거나, 55인 사람 확인
SELECT *
FROM name
WHERE age IN(35, 45, 55);
```
## LIKE 연산자와 와일드카드
```SQL
-- % : 0개 이상의 문자	-- _ : 임의의 단일문자
-- name 테이블에서 이름이 M으로 시작하는 모든 항목 확인
SELECT *
FROM name
WHERE ename LIKE 'M%';

-- name 테이블에서 이름에 O가 들어가는 모든 항목 확인
SELECT *
FROM name
WHERE ename LIKE '%O%';

-- name 테이블에서 이름이 K로 시작하면서 5글자인 항목 확인
SELECT *
FROM name
WHERE ename LIKE 'K____' ;	// _ 4개 사용

-- name 테이블에서 이름에 A를 포함하지 않는 사람 검색
SELECT *
FROM name
WHERE ename NOT LIKE '%A%';

-- name 테이블에서 직업의 값이 NULL인 항목 확인
SELECT *
FROM name
WHERE job IS NULL;

-- name 테이블에서 직업의 값이 NULL이 아닌 항목 확인
SELECT *
FROM name
WHERE job IS NOT NULL;
```
## 연결 연산자(concatenation)
여러 개의 칼럼을 연결할 때 사용하며, 리터럴은 SELECT 리스트에 포함된 문자, 숫자 또는 날짜이다. 숫자 리터럴은 그냥 사용해도 되지만, 문자 및 날짜 리터럴은 ' '(작은 따옴표)로 작성해야 한다.
```SQL
SELECT ename || ' is a ' || job 
FROM name;		-- 결과값 예시 : ename is a job

SELECT ename || ' 은(는) ' || job || ' 이다' 
FROM name;
```
