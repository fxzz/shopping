package shopping.mapper;

import org.apache.ibatis.annotations.Mapper;

import shopping.domain.MemberDTO;

@Mapper
public interface MemberMyMapper {
	public MemberDTO memberInfo(String memberId);
}
