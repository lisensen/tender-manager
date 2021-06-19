package cn.huanzi.qch.baseadmin.tender.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * <Description> <br>
 *
 * @author liss
 * @createTime 2021年03月31日 21:00:00
 */
@Data
public class TenderExcelData {

    @ExcelProperty("姓名")
    private String userName;

    @ExcelProperty("手机号")
    private String phone;

    @ExcelProperty("身份证号")
    private String idCard;

    @ExcelProperty("投标单号")
    private Date tenderNo;

    @ExcelProperty("登记时间")
    private Date createTime;

}
