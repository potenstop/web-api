package top.potens.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.potens.framework.exception.ApiException;
import top.potens.framework.log.AppUtil;
import top.potens.framework.model.ApiResult;
import top.potens.framework.serialization.JSON;
import top.potens.web.common.enums.CodeEnums;
import top.potens.web.model.neo4j.UserNeo4j;
import top.potens.web.model.neo4j.OrderNeo4j;
import top.potens.web.model.neo4j.OrderProductNeo4j;
import top.potens.web.request.UserNeo4jRequest;
import top.potens.web.request.OrderNeo4jRequest;
import top.potens.web.service.AreaService;
import top.potens.web.service.noe4j.Neo4jService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className Neo4jController
 * @projectName web-api
 * @date 2019/7/5 13:36
 */
@RestController
@RequestMapping("/neo4j")
@Api(description = "用户管理操作")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Neo4jController {
    private final AreaService areaService;
    private final Neo4jService neo4JService;

    @PostMapping("/init/area")
    @ApiOperation(value = "初始化area")
    public ApiResult<Boolean> initArea() {
        throw new ApiException(CodeEnums.PARAM_ERROR.getCode(), CodeEnums.PARAM_ERROR.getMsg());
    }
    @PostMapping("/user/add")
    @ApiOperation(value = "添加用户")
    public ApiResult<Boolean> userAdd(@RequestBody @Valid UserNeo4jRequest request) {
        AppUtil.info("start-controller-userAdd");
        ApiResult<Boolean> result = new ApiResult<>();
        UserNeo4j userNeo4J = new UserNeo4j();
        userNeo4J.setUserId(request.getUserId());
        userNeo4J.setUserName(request.getUserName());
        userNeo4J.setCreateTime(request.getCreateTime());
        neo4JService.insertUser(userNeo4J, request.getPartCityCode());
        result.setData(true);
        AppUtil.info("end-controller-userAdd result:[{}]", JSON.toJSONString(result));
        return result;
    }
    @PostMapping("/order/add")
    @ApiOperation(value = "添加订单")
    public ApiResult<Boolean> orderAdd(@RequestBody @Valid OrderNeo4jRequest request) {
        AppUtil.info("start-controller-orderAdd");
        ApiResult<Boolean> result = new ApiResult<>();
        OrderNeo4j orderNeo4j = new OrderNeo4j();
        orderNeo4j.setOrderId(request.getOrderId());
        orderNeo4j.setPaidAmount(request.getPaidAmount());
        orderNeo4j.setCreateTime(request.getCreateTime());
        List<OrderProductNeo4j> orderProductNeo4js = new ArrayList<>();

        List<OrderNeo4jRequest.OrderProductRequest> orderProductRequests = request.getOrderProductRequests();
        orderProductRequests.forEach(value -> {
            OrderProductNeo4j orderProductNeo4j = new OrderProductNeo4j();
            orderProductNeo4j.setProductId(value.getProductId());
            orderProductNeo4j.setProductName(value.getProductName());
            orderProductNeo4j.setProductCount(value.getProductCount());
            orderProductNeo4js.add(orderProductNeo4j);
        });
        HashSet<OrderProductNeo4j> orderProductNeo4js1 = new HashSet<>(orderProductNeo4js);
        orderNeo4j.setProducts(orderProductNeo4js1);
        neo4JService.insertOrder(orderNeo4j, request.getUserId(), request.getDirectUser(), request.getIndirectUser());
        result.setData(true);
        AppUtil.info("end-controller-orderAdd result:[{}]", JSON.toJSONString(result));
        return result;
    }
}
