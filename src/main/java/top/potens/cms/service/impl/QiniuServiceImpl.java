package top.potens.cms.service.impl;

import com.qiniu.util.Auth;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.potens.web.config.ApolloConfiguration;
import top.potens.cms.service.QiniuService;

/**
 * 功能描述:
 *
 * @author yanshaowen
 * @className QiniuServiceImpl
 * @projectName web-api
 * @date 2019/9/27 12:50
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class QiniuServiceImpl implements QiniuService {
    private final ApolloConfiguration apolloConfiguration;
    @Override
    public String token() {
        Auth auth = Auth.create(apolloConfiguration.getQiniuAccessKey(), apolloConfiguration.getQiniuAccessKey());
        return auth.uploadToken(apolloConfiguration.getQiniuBucketLocal());
    }
}
