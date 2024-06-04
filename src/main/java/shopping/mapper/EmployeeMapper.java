package shopping.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import shopping.domain.EmployeeDTO;
import shopping.domain.StartEndPageDTO;

@Mapper
public interface EmployeeMapper {
	public String autoNum();

	public Integer employeeInsert(EmployeeDTO dto);

	public List<EmployeeDTO> employeeAllSelect(StartEndPageDTO sepDTO);

	public int employeeCount(String searchWord);
}
