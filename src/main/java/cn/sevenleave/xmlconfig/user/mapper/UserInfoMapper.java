package cn.sevenleave.xmlconfig.user.mapper;

import cn.sevenleave.xmlconfig.user.model.UserInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserInfoMapper extends Mapper<UserInfo> {
}