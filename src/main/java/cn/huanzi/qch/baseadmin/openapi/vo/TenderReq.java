package cn.huanzi.qch.baseadmin.openapi.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * <Description> <br>
 *
 * @author liss
 * @createTime 2021年03月30日 23:20:00
 */
@Data
public class TenderReq implements Serializable {

    private String userName;
    private String phone;
    private String idCard;
    private String tenderNo;

}
