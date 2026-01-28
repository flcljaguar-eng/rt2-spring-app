package jp.co.sss.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.crud.entity.Department;


/**
 * 部署情報にアクセスするリポジトリ
 * Departmentエンティティに対する検索機能を提供する
 * 
 * @author k-hijikata
 */
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
