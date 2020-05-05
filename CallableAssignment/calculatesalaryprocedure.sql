create or replace PROCEDURE calculateSalary( p_empno IN emp.empno%TYPE,final_salary OUT emp.sal%TYPE)
IS p_comm emp.comm %TYPE;
    p_sal emp.sal % TYPE;
    net_salary emp.sal % TYPE;
    gross_salary emp.sal % TYPE;
    it emp.sal % TYPE;

BEGIN
    SELECT comm INTO p_comm FROM emp WHERE empno = p_empno;
    SELECT sal INTO P_sal FROM emp WHERE empno=p_empno;

    IF p_comm IS NULL THEN
		p_comm := 0;
	END IF;

	gross_salary := p_sal + p_comm;

	IF p_comm =0 THEN
		it := gross_salary * 10 / 100;
	ELSIF p_comm< 500 THEN
        it := gross_salary * 15 / 100;
    ELSE
        it := gross_salary * 20 / 100;
    END IF;	

	net_salary := gross_salary - it;
    final_salary:=net_salary;
END;
/
commit;