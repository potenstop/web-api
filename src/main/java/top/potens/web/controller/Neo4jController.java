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
import top.potens.web.model.Area;
import top.potens.web.model.neo4j.AreaNeo4j;
import top.potens.web.model.neo4j.MemberNeo4j;
import top.potens.web.request.MemberNeo4jRequest;
import top.potens.web.service.AreaService;
import top.potens.web.service.noe4j.Neo4jService;

import javax.validation.Valid;
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
        if (true) {
            throw new ApiException(CodeEnums.PARAM_ERROR.getCode(), CodeEnums.PARAM_ERROR.getMsg());
        }
        AppUtil.info("start-controller-initArea");
        ApiResult<Boolean> result = new ApiResult<>();
        List<Area> areas = areaService.selectCities();
        areas.forEach(area -> {
            AreaNeo4j areaNeo4j = new AreaNeo4j();
            areaNeo4j.setRank(area.getRank());
            areaNeo4j.setAreaName(area.getAreaName());
            areaNeo4j.setAreaCode(area.getAreaCode());
            neo4JService.insertArea(areaNeo4j);
        });
        result.setData(true);
        AppUtil.info("end-controller-initArea result:[{}]", JSON.toJSONString(result));
        return result;
    }
    @PostMapping("/member/add")
    @ApiOperation(value = "添加用户")
    public ApiResult<Boolean> memberAdd(@RequestBody @Valid MemberNeo4jRequest request) {
        AppUtil.info("start-controller-memberAdd");
        ApiResult<Boolean> result = new ApiResult<>();
        MemberNeo4j memberNeo4j = new MemberNeo4j();
        memberNeo4j.setMemberId(request.getMemberId());
        memberNeo4j.setMemberName(request.getMemberName());
        memberNeo4j.setCreateTime(request.getCreateTime());
        neo4JService.insertMember(memberNeo4j, request.getPartCityCode());
        result.setData(true);
        AppUtil.info("end-controller-memberAdd result:[{}]", JSON.toJSONString(result));
        return result;
    }
}
