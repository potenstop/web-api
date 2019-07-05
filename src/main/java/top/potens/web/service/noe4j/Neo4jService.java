package top.potens.web.service.noe4j;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.framework.exception.ApiException;
import top.potens.web.common.constant.AreaConstant;
import top.potens.web.common.enums.CodeEnums;
import top.potens.web.dao.neo4j.AreaNeo4jRepository;
import top.potens.web.dao.neo4j.MemberNeo4jRepository;
import top.potens.web.dao.neo4j.OrderNeo4jRepository;
import top.potens.web.dao.neo4j.ProductNeo4jRepository;
import top.potens.web.model.neo4j.AreaNeo4j;
import top.potens.web.model.neo4j.MemberNeo4j;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className OrderServiceNeo4j
 * @projectName web-api
 * @date 2019/7/5 10:38
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Neo4jService {
    private final OrderNeo4jRepository orderNeo4jRepository;
    private final AreaNeo4jRepository areaNeo4jRepository;
    private final MemberNeo4jRepository memberNeo4jRepository;
    private final ProductNeo4jRepository productNeo4jRepository;

    public AreaNeo4j insertArea(AreaNeo4j areaNeo4jParams) {
        AreaNeo4j areaNeo4j = areaNeo4jRepository.findByAreaCode(areaNeo4jParams.getAreaCode());
        if (areaNeo4j == null) {
            areaNeo4j = areaNeo4jRepository.save(areaNeo4jParams);
        }
        return areaNeo4j;
    }

    public MemberNeo4j insertMember(MemberNeo4j memberNeo4jParams, String partCityCode) {
        MemberNeo4j memberNeo4j = memberNeo4jRepository.findByMemberId(memberNeo4jParams.getMemberId());
        AreaNeo4j areaNeo4j = areaNeo4jRepository.findByAreaCode(partCityCode);
        if (areaNeo4j == null || !AreaConstant.Rank.CITY.equals(areaNeo4j.getRank())) {
            throw new ApiException(CodeEnums.CITY_NOT_FOUND.getCode(), CodeEnums.CITY_NOT_FOUND.getMsg());
        }
        memberNeo4jParams.setCityAreaNeo4j(areaNeo4j);
        if (memberNeo4j == null) {
            return memberNeo4jRepository.save(memberNeo4jParams);
        }
        if (memberNeo4j.getCityAreaNeo4j() == null || !areaNeo4j.getAreaCode().equals(memberNeo4j.getCityAreaNeo4j().getAreaCode())) {
            memberNeo4jRepository.deletePartCity(memberNeo4j.getMemberId());
            memberNeo4j.setCityAreaNeo4j(areaNeo4j);
            return memberNeo4jRepository.save(memberNeo4j);
        }
        return memberNeo4j;
    }
}
