package top.potens.wechat.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import top.potens.wechat.service.TokenService;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className TokenServiceImpl
 * @projectName web-api
 * @date 2019/12/17 16:59
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenServiceImpl implements TokenService {
    private String wxmpAppId = "wx0c91dc1ced40a0b0";
    private String wxmpAppSecret = "b4d8ee1713a04ed655c191a24360df8a";
    private final RestTemplate restTemplate;
    @Override
    public String getWxmpToken() {
        return  null;
//        restTemplate.getForObject()
    }
}
