package top.potens.web.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className OrderNeo4jRequest
 * @projectName web-api
 * @date 2019/7/5 12:26
 */
@Data
public class OrderNeo4jRequest {
    private Integer memberId;
    private String memberName;
    private String areaCode;
    private String areaName;
    private Integer orderId;
    private BigDecimal paidAmount;
    private Integer productId;
    private String productName;
}
