package top.potens.web.service.noe4j;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.framework.exception.ApiException;
import top.potens.framework.util.DateUtil;
import top.potens.web.code.CommonCode;
import top.potens.web.code.UserCode;
import top.potens.web.common.constant.AreaConstant;
import top.potens.web.dao.neo4j.AreaNeo4jRepository;
import top.potens.web.dao.neo4j.UserNeo4jRepository;
import top.potens.web.dao.neo4j.OrderNeo4jRepository;
import top.potens.web.dao.neo4j.OrderProductNeo4jRepository;
import top.potens.web.model.neo4j.AreaNeo4j;
import top.potens.web.model.neo4j.UserNeo4j;
import top.potens.web.model.neo4j.OrderNeo4j;

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
    private final UserNeo4jRepository userNeo4JRepository;
    private final OrderProductNeo4jRepository orderProductNeo4JRepository;

    public AreaNeo4j insertArea(AreaNeo4j areaNeo4jParams) {
        AreaNeo4j areaNeo4j = areaNeo4jRepository.findByAreaCode(areaNeo4jParams.getAreaCode());
        if (areaNeo4j == null) {
            areaNeo4j = areaNeo4jRepository.save(areaNeo4jParams);
        }
        return areaNeo4j;
    }

    public UserNeo4j insertUser(UserNeo4j userNeo4JParams, String partCityCode) {
        AreaNeo4j areaNeo4j = areaNeo4jRepository.findByAreaCode(partCityCode);
        if (areaNeo4j == null || !AreaConstant.Rank.CITY.equals(areaNeo4j.getRank())) {
            throw new ApiException(CommonCode.CITY_NOT_FOUND);
        }
        userNeo4JParams.setCityAreaNeo4j(areaNeo4j);
        userNeo4JRepository.createOrUpdate(userNeo4JParams.getUserId(), userNeo4JParams.getUserName(), DateUtil.getLocalDateStr(userNeo4JParams.getCreateTime()));
        UserNeo4j userNeo4J = userNeo4JRepository.findByUserId(userNeo4JParams.getUserId());
        if (userNeo4J.getCityAreaNeo4j() == null || !areaNeo4j.getAreaCode().equals(userNeo4J.getCityAreaNeo4j().getAreaCode())) {
            userNeo4JRepository.deletePartCity(userNeo4J.getUserId());
            userNeo4J.setCityAreaNeo4j(areaNeo4j);
            return userNeo4JRepository.save(userNeo4J);
        }
        return userNeo4J;
    }
    public OrderNeo4j insertOrder(OrderNeo4j orderNeo4jParams, Integer userId, Integer directUser, Integer indirectUser) {
        UserNeo4j userNeo4J = userNeo4JRepository.findByUserId(userId);
        if (userNeo4J == null) {
            throw new ApiException(UserCode.USER_NOT_FOUND);
        }
        if (directUser != null) {
            UserNeo4j userNeo4JD = userNeo4JRepository.findByUserId(directUser);
            orderNeo4jParams.setDirectUser(userNeo4JD);
        }
        if (indirectUser != null) {
            UserNeo4j userNeo4JI = userNeo4JRepository.findByUserId(indirectUser);
            orderNeo4jParams.setIndirectUser(userNeo4JI);
        }
        orderNeo4jParams.setUserNeo4J(userNeo4J);
        return orderNeo4jRepository.save(orderNeo4jParams);
    }
}
