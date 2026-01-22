package jp.co.sss.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.crud.entity.Department;
import jp.co.sss.crud.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	//ログイン
	Employee findByEmpIdAndEmpPass(Integer empId, String empPass);
	
	//並び替え全件検索
	List<Employee> findAllByOrderByEmpIdAsc();
	
	//社員名検索
	List<Employee> findByEmpNameContainingOrderByEmpIdAsc(String empName);
	
	//部署検索
	List<Employee> findByDepartmentOrderByEmpIdAsc(Department department);
	
	//ID検索（更新、削除用）
	Employee findByEmpId(Integer empId);
}
