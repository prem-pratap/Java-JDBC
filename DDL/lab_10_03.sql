CREATE TABLE EMP (id NUMBER(7) CONSTRAINT emp_id_pk PRIMARY KEY, 
                    last_name VARCHAR2(25) NOT NULL ,first_name VARCHAR2(25), 
                    dept_id NUMBER(7) ,CONSTRAINT  dept_id_fk FOREIGN KEY(dept_id) REFERENCES dept(dept_id));

DESCRIBE EMP;

INSERT INTO emp VALUES(101,'Sam','Sundar',10);

INSERT INTO emp VALUES(102,'Gopi',null,40);

INSERT INTO emp VALUES(103,null,'ram',20);/*it will not run as not null value cannot be set as null*/

SELECT * FROM emp;

