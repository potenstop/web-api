package top.potens.web.dao.neo4j;

import org.neo4j.ogm.session.Session;
import org.springframework.data.neo4j.repository.support.SimpleNeo4jRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Created by wenshao on 2019/7/6.
 */
public class MyRepositoryImpl<T, ID extends Serializable> extends SimpleNeo4jRepository<T, ID> {
    private Session session;
    /**
     * Creates a new {@link SimpleNeo4jRepository} to manage objects of the given domain type.
     *
     * @param domainClass must not be {@literal null}.
     * @param session     must not be {@literal null}.
     */
    public MyRepositoryImpl(Class<T> domainClass, Session session) {
        super(domainClass, session);
        this.session = session;
    }

    @Transactional
    public <S extends T> S save(S entity) {
        return super.save(entity);
    }

}