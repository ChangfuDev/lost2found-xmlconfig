package cn.sevenleave.xmlconfig.business.user.mapper;

import cn.sevenleave.xmlconfig.business.user.model.SysUser;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface SysUserMapper extends Mapper<SysUser> {
}