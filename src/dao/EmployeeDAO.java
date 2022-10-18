package dao;

import java.io.IOException;

public interface EmployeeDAO {
	/* 전체 사원 목록 조회 */
	void employeeAllList() throws NumberFormatException, IOException;
}
