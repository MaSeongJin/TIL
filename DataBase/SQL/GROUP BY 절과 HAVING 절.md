### **GROUP BY 절**

그룹 함수를 사용하되 어떤 칼럼 값을 기준으로 그룹 함수를 적용할 수 있다. 합계, 평균 등을 어떠한 컬럼을 기준으로 그 칼럼의 값 별로 보고자 할 때 GROUP BY 절 뒤에 해당 칼럼명을 작성하면 된다. GROUP BY 절 다음에는 컬럼의 별칭을 사용할 수 없고, 반드시 컬럼명을 사용해야 한다.

```sql
SELECT 컬럼명, 그룹 함수
FROM 테이블명
WHERE 조건
GROUP BY 컬럼명

-- 부서번호로 그룹
SELECT deptno
FROM emp
GROUP BY deptno;

-- 부서별 평균 급여
SELECT deptno, AVG(sal)
FROM emp
GROUP BY deptno;

-- 부서별 최대, 최소 급여
SELECT deptno, MAX(sal), MIN(sal)
FROM emp
GROUP BY deptno;

-- ORDER BY는 항상 맨 마지막에 작성한다
SELECT deptno, count(*), COUNT(comm)
FROM emp
GROUP BY deptno
ORDER BY deptno ASC;
```

### **HAVING 절**

그룹의 결과를 제한할 때 HAVING 절을 사용한다.

```sql
-- 그룹 지어진 부서별 평균 급여가 2000 이상인 부서의 번호와 평균 급여를 출력
SELECT deptno, AVG(sal)
FROM emp
GROUP BY deptno
HAVING AVG(sal) >= 2000;

-- 전체 월급이 4000 을 초과하는 각 업무에 대해서 업무와 월급여 합계를 출력하는 쿼리문을 작성
-- 단, 판매원은 제외하고 월급여 합계로 내림차순 정렬 합니다
SELECT job, SUM(sal)
FROM emp
WHERE job NOT LIKE 'SALE%'
GROUP BY job
HAVING SUM(sal) >= 4000
ORDER BY SUM(sal) DESC;
```
