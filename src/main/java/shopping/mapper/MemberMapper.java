package shopping.mapper;

import org.apache.ibatis.annotations.Mapper;

import shopping.domain.MemberDTO;

@Mapper
public interface MemberMapper {
	public void memberInsert(MemberDTO dto);

	public String memberAutoNum();
}
