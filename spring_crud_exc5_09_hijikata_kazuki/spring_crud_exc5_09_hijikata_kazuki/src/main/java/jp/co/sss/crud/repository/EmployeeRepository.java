package jp.co.sss.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.sss.crud.entity.Department;
import jp.co.sss.crud.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	//	//ログイン
	//	Employee findByEmpIdAndEmpPass(Integer empId, String empPass);
	//	
	//	//並び替え全件検索
	//	List<Employee> findAllByOrderByEmpIdAsc();
	//	
	//	//社員名検索
	//	List<Employee> findByEmpNameContainingOrderByEmpIdAsc(String empName);
	//	
	//	//部署検索
	//	List<Employee> findByDepartmentOrderByEmpIdAsc(Department department);
	//	
	//	//ID検索（更新、削除用）
	//	Employee findByEmpId(Integer empId);
	//	

	/**
	 * 以下、論理削除用
	 */
	//ログイン
	@Query("SELECT e FROM Employee e WHERE e.empId = ?1 AND e.empPass = ?2 AND e.deleteFlag = 0")
	Employee findByEmpIdAndEmpPass(Integer empId, String empPass);

	//全件検索
	@Query("SELECT e FROM Employee e WHERE e.deleteFlag = 0")
	List<Employee> findAllByOrderByEmpIdAsc();

	//社員名検索
	@Query("SELECT e FROM Employee e WHERE e.deleteFlag = 0 AND e.empName LIKE %:empName%")
	List<Employee> findByEmpNameContainingOrderByEmpIdAsc(@Param("empName") String empName);

	//部署検索
	@Query("SELECT e FROM Employee e WHERE e.deleteFlag = 0 AND e.department = :department")
	List<Employee> findByDepartmentOrderByEmpIdAsc(@Param("department") Department department);

	//ID検索（更新、削除用）
	@Query("SELECT e FROM Employee e WHERE e.deleteFlag = 0 AND e.empId = :empId")
	Employee findByEmpId(@Param("empId")Integer empId);

}
