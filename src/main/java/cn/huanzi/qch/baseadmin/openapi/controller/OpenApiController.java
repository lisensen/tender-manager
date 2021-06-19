package cn.huanzi.qch.baseadmin.openapi.controller;

import cn.huanzi.qch.baseadmin.common.pojo.Result;
import cn.huanzi.qch.baseadmin.openapi.service.OpenApiService;
import cn.huanzi.qch.baseadmin.openapi.vo.TenderReq;
import cn.huanzi.qch.baseadmin.tender.service.TenderUserService;
import cn.huanzi.qch.baseadmin.tender.vo.TenderUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/openApi/")
public class OpenApiController {

    @Autowired
    private OpenApiService openApiService;

    @Autowired
    private TenderUserService tenderUserService;

    @PostMapping("h5")
    public Result<String> test(@RequestBody TenderReq req) {
        return openApiService.test();
    }

    @PostMapping("tender")
    public Result test1(@RequestBody TenderReq req) {
        TenderUserVo tenderUserVo = new TenderUserVo();
        tenderUserVo.setUserName(req.getUserName());
        tenderUserVo.setPhone(req.getPhone());
        tenderUserVo.setIdCard(req.getIdCard());
        tenderUserVo.setTenderNo(req.getTenderNo());
        tenderUserVo.setCreateTime(new Date());
        return tenderUserService.save(tenderUserVo);
    }

    @PostMapping("h5/data")
    public Result tender(@RequestBody TenderReq req) {
//        TenderUserVo tenderUserVo = new TenderUserVo();
//        tenderUserVo.setUserName(req.getUserName());
//        tenderUserVo.setPhone(req.getPhone());
//        tenderUserVo.setIdCard(req.getIdCard());
//        tenderUserVo.setCreateTime(new Date());
//        return tenderUserService.save(tenderUserVo);
        return openApiService.test();
    }
}
