package cn.huanzi.qch.baseadmin.tender.vo;

import cn.huanzi.qch.baseadmin.annotation.Like;
import cn.huanzi.qch.baseadmin.common.pojo.PageCondition;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <Description> <br>
 *
 * @author liss
 * @createTime 2021年03月30日 22:20:00
 */
@Data
public class TenderUserVo extends PageCondition implements Serializable {

    @ExcelIgnore
    private String id;//用户id

    @ExcelProperty("姓名")
    @Like
    private String userName;

    @ExcelProperty("手机号")
    @Like
    private String phone;

    @ExcelProperty("身份证号")
    @Like
    private String idCard;

    @ExcelProperty("投标单号")
    @Like
    private String tenderNo;

    @ExcelProperty("登记时间")
    private Date createTime;//创建时间

    @ExcelIgnore
    private Date updateTime;//修改时间

}
