package cn.huanzi.qch.baseadmin.tender.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * <Description> <br>
 *
 * @author liss
 * @createTime 2021年03月30日 22:16:00
 */
@Entity
@Table(name = "tender_user")
@Data
public class TenderUser implements Serializable {

    @Id
    private String id;//用户id
    private String userName;
    private String phone;
    private String idCard;
    private String tenderNo;
    private Date createTime;//创建时间
    private Date updateTime;//修改时间

}
