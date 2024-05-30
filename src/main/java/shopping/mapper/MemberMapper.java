package shopping.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import shopping.domain.MemberDTO;
import shopping.domain.StartEndPageDTO;

@Mapper
public interface MemberMapper {
	public void memberInsert(MemberDTO dto);

	public String memberAutoNum();
	public List<MemberDTO> selectAll(StartEndPageDTO sepDTO);
	public int memberCount(String searchWord);
}
