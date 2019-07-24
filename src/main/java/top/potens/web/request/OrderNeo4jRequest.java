package top.potens.web.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className UserNeo4jRequest
 * @projectName web-api
 * @date 2019/7/5 14:49
 */
@Data
@ApiModel(description = "user order 对象")
public class OrderNeo4jRequest {
    @NotNull
    @ApiModelProperty(value = "用户id", example = "1")
    private Integer userId;

    @NotNull
    @ApiModelProperty(value = "订单id", example = "1")
    private Integer orderId;

    @NotNull
    @ApiModelProperty(value = "下单时间", example = "2019-01-01 10:10:10.0")
    private Date createTime;

    @NotNull
    @ApiModelProperty(value = "订单金额", example = "10.11")
    private BigDecimal paidAmount;

    @NotNull
    @ApiModelProperty(value = "产品快照列表")
    private List<OrderProductRequest> orderProductRequests;

    @ApiModelProperty(value = "订单直接推广人", example = "2")
    private Integer directUser;

    @ApiModelProperty(value = "订单间接推广人", example = "3")
    private Integer indirectUser;


    @Valid
    @Data
    public static class OrderProductRequest {
        @NotNull
        @ApiModelProperty(value = "产品id", example = "1")
        private Integer productId;

        @NotNull
        @ApiModelProperty(value = "产品名称", example = "测试产品")
        private String productName;

        @NotNull
        @ApiModelProperty(value = "产品购买分数", example = "2")
        private Integer productCount;
    }
}
