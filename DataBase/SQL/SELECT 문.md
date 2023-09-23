## SELECT문
SELECT 문은 데이터를 조회하기 위한 SQL 명령어로 반드시 SELECT와 FROM이라는 2개의 키워드로 구성되어야 한다.
### 기본 형식
```SQL
SELECT {*, column, ...}	-- 조회할 열 이름이나 데이터 또는 *로 전체 열 지정
FROM table_name;	-- 조회할 테이블 이름
```
### 전체 열 조회
```SQL
SELECT * FROM name;	-- name 테이블의 전체 열 조회
```
### 특정 열 조회
```SQL
SELECT cola, colb
FROM name;		-- name 테이블의 cola, colb 열 조회
```
### 산술 연산자
```SQL
SELECT cola + 10 FROM name;		-- 더하기
SELECT cola - 10 FROM name;		-- 빼기
SELECT cola * 10 FROM name;		-- 곱하기
SELECT cola / 10 FROM name;		-- 나누기
SELECT cola + colb FROM name;		-- 특정 열의 합
```
### 열 중복 제거
```SQL
SELECT DISTINCT cola		-- 열이 한개일 경우
FROM name;			-- cola열 중에서 중복 행을 한개 남겨두고 제거
SELECT DISTINCT cola, colb	-- 열이 여러개일 경우
FROM name;			-- cola, colb열 모두 중복 데이터일 경우 한 행을 남겨두고 제거
```
### 별칭 지정
- 열 머리글의 이름을 변경할 때 사용
- 컬럼 이름 대신 별칭을 출력하고자 하면 컬럼을 기술한 바로 뒤에 as 키워드를 사용한 후 별칭을 기술한다.
- 별칭을 부여할 때 대소문자를 섞어서 기술하면, 결과는 대문자로 출력된다.
- 대소문자를 구별하고 싶으면 " "을 사용한다.
```SQL
SELECT cola as "연봉" from name;	-- cola 대신 연봉으로 출력
```
### ORDER BY
- 데이터를 정렬할 때 사용
- SELECT 문의 맨 마지막에 사용
- ASC(오름차순), DESC(내림차순)
```SQL
SELECT *
FROM name
ORDER BY age;			-- name 테이블의 나이를 기준으로 오름차순 정렬(기본은 오름차순)
SELECT *
FROM name
ORDER BY age DESC;		-- name 테이블의 나이를 기준으로 내림차순 정렬
SELECT *
FROM name
ORDER BY age DESC, height ASC;	-- name 테이블의 나이를 기준으로 내림차순 정렬하고, 나이가 같으면 키를 기준으로 오름차순
```
