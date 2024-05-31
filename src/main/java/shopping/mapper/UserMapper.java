package shopping.mapper;

import org.apache.ibatis.annotations.Mapper;

import shopping.domain.MemberDTO;

@Mapper
public interface UserMapper {
	public int userInsert(MemberDTO dto);
	public int userCheckUpdate(String email);
}
