package cn.huanzi.qch.baseadmin.tender.service;

import cn.huanzi.qch.baseadmin.common.pojo.PageInfo;
import cn.huanzi.qch.baseadmin.common.pojo.Result;
import cn.huanzi.qch.baseadmin.common.service.CommonServiceImpl;
import cn.huanzi.qch.baseadmin.tender.domain.TenderUser;
import cn.huanzi.qch.baseadmin.tender.vo.TenderUserVo;
import cn.huanzi.qch.baseadmin.util.SqlUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * <Description> <br>
 *
 * @author liss
 * @createTime 2021年03月30日 22:23:00
 */
@Service
public class TenderUserServiceImpl extends CommonServiceImpl<TenderUserVo, TenderUser, String> implements TenderUserService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Result<PageInfo<TenderUserVo>> page(TenderUserVo entityVo) {
        //根据实体、Vo直接拼接全部SQL
        StringBuilder sql = SqlUtil.joinSqlByEntityAndVo(TenderUser.class,entityVo);

        //设置SQL、映射实体，以及设置值，返回一个Query对象
        Query query = em.createNativeQuery(sql.toString(), TenderUser.class);

        //分页设置，page从0开始
        PageRequest pageRequest = PageRequest.of(entityVo.getPage() - 1, entityVo.getRows());

        //获取最终分页结果
        Result<PageInfo<TenderUserVo>> result = Result.of(PageInfo.of(PageInfo.getJPAPage(query,pageRequest,em), TenderUserVo.class));

        return result;
    }
}
