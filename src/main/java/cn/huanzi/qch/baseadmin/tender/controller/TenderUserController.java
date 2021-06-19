package cn.huanzi.qch.baseadmin.tender.controller;

import cn.huanzi.qch.baseadmin.common.controller.CommonController;
import cn.huanzi.qch.baseadmin.common.pojo.PageInfo;
import cn.huanzi.qch.baseadmin.common.pojo.Result;
import cn.huanzi.qch.baseadmin.tender.domain.TenderUser;
import cn.huanzi.qch.baseadmin.tender.service.TenderUserService;
import cn.huanzi.qch.baseadmin.tender.vo.TenderExcelData;
import cn.huanzi.qch.baseadmin.tender.vo.TenderUserVo;
import cn.huanzi.qch.baseadmin.util.DateUtil;
import com.alibaba.excel.EasyExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * <Description> <br>
 *
 * @author liss
 * @createTime 2021年03月30日 21:53:00
 */
@RestController
@RequestMapping("/tender/")
public class TenderUserController extends CommonController<TenderUserVo, TenderUser, String> {

    @Autowired
    private TenderUserService tenderUserService;

    @GetMapping("info")
    public ModelAndView info() {
        return new ModelAndView("tender/info/user", "user", null);
    }

    @Override
    public Result<PageInfo<TenderUserVo>> page(TenderUserVo entityVo) {
        entityVo.setSidx("createTime");
        entityVo.setSord("desc");
        return super.page(entityVo);
    }

    /**
     * 文件下载（失败了会返回一个有部分数据的Excel）
     * <p>1. 创建excel对应的实体对象 参照{@link TenderExcelData}
     * <p>2. 设置返回的 参数
     * <p>3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
     */
    @GetMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode(DateFormat.getInstance().format(new Date()), "UTF-8");

        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        TenderUserVo vo = new TenderUserVo();
        Result<List<TenderUserVo>> list = tenderUserService.list(vo);
        EasyExcel.write(response.getOutputStream(), TenderUserVo.class).sheet("模板").doWrite(list.getData());
    }

}
