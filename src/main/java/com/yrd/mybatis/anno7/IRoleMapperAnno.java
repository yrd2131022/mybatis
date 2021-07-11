package com.yrd.mybatis.anno7;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yrd.mybatis.domain.Role;

public interface IRoleMapperAnno {

	@Select("SELECT *,r.t_id role_id FROM tbl_user_role ur,tbl_role r WHERE  r.t_id = ur.t_roleid AND ur.t_userid=#{uid}")
	public List<Role> findByUid(int uid);
}
