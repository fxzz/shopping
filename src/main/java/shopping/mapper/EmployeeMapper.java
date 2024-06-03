package shopping.mapper;

import org.apache.ibatis.annotations.Mapper;

import shopping.domain.EmployeeDTO;

@Mapper
public interface EmployeeMapper {
	public String autoNum();

	public Integer employeeInsert(EmployeeDTO dto);
}
