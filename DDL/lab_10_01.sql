CREATE TABLE DEPT (Dept_id  NUMBER(7) CONSTRAINT dept_dept_id_pk PRIMARY KEY, Dept_name VARCHAR2(20));

DESCRIBE dept;

INSERT INTO dept (SELECT department_id, department_name FROM departments);

SELECT * FROM dept;

INSERT INTO dept VALUES(10,'Accounts');

INSERT INTO dept VALUES (null,'TT');

