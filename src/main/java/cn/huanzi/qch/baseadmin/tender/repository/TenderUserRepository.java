package cn.huanzi.qch.baseadmin.tender.repository;

import cn.huanzi.qch.baseadmin.common.repository.CommonRepository;
import cn.huanzi.qch.baseadmin.sys.sysuser.pojo.SysUser;
import cn.huanzi.qch.baseadmin.tender.domain.TenderUser;
import org.springframework.stereotype.Repository;

@Repository
public interface TenderUserRepository extends CommonRepository<TenderUser, String> {
}
