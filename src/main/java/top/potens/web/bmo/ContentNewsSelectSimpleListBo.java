package top.potens.web.bmo;

import lombok.Data;

import java.util.Date;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className ContentNewsSelectSimpleListBo
 * @projectName web-api
 * @date 2019/9/25 12:47
 */
@Data
public class ContentNewsSelectSimpleListBo {
    private Integer contentId;

    private Date createTimeStart;

    private Date createTimeEnd;

    private Date updateTimeStart;

    private Date updateTimeEnd;
}
