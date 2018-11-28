package com.tryingpfq.dao.provider;

import com.tryingpfq.dao.anno.IdGenerator;
import com.tryingpfq.dao.entity.IEntity;
import com.utils.ReflectUtils;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.persistence.Id;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author tryingpfq
 * @date 2018/11/26 11:54
 */
public class HibernateEntityProvider<T extends IEntity,ID extends Serializable> extends HibernateDaoSupport implements
        BaseEntityProvider<T,ID>{

    private Class<T> entityClass;

    //TODO id生成策略
    private String keyGenerator;

    public HibernateEntityProvider(){
        //获取父类的第一个第一个泛型参数
        this.entityClass = (Class<T>) ReflectUtils.getSuperGenericType(getClass());
        if(entityClass != null){
            try{
                Field[] fields = entityClass.getDeclaredFields();
                for(int i= 0; i< fields.length;i ++){
                    if(fields[i].getAnnotation(Id.class) != null){
                        IdGenerator anno = fields[i].getAnnotation(IdGenerator.class);
                        if(anno != null){
                            keyGenerator = anno.value();
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    protected String getKeyGenerator(){ return keyGenerator; }
    @Override
    public T get(ID id) {
        return this.getHibernateTemplate().execute(session ->{
            Transaction transaction = session.beginTransaction();
            T t = session.get(entityClass,id);
            transaction.commit();
            return t;
        });
    }

    @Override
    public void save(T entity) {
        this.getHibernateTemplate().execute(session -> {
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            session.flush();
            transaction.commit();
            return entity;
        });
    }

    @Override
    public void update(T entity) {
        this.getHibernateTemplate().execute(session -> {
           Transaction transaction = session.beginTransaction();
           session.saveOrUpdate(entity);
           session.flush();
           transaction.commit();
           return entity;
        });
    }

    @Override
    public T loadAndCreate(ID id, ICreator<T, ID> creator) {
        T t = get(id);
        if(t != null){
            return t;
        }
        t = creator.create(id);
        save(t);
        return t;
    }

    @Override
    public void saveOrUpdate(T entity) {
        this.getHibernateTemplate().execute(session -> {
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            session.flush();
            transaction.commit();
            return entity;
        });
    }

    @Override
    public void delete(T entity) {
        this.getHibernateTemplate().execute(session -> {
            Transaction transaction = session.beginTransaction();
            session.delete(entity);
            session.flush();
            transaction.commit();
            return entity;
        });
    }

    @Override
    public void delete(ID id) {
        this.getHibernateTemplate().execute(session -> {
            Transaction transaction = session.beginTransaction();
            session.delete(id);
            session.flush();
            transaction.commit();
            return null;
        });
    }

    @Override
    public List<T> query(String sql, Object... params) {
        return this.getHibernateTemplate().execute(session -> {
            Transaction transaction = session.beginTransaction();
            Query nameQuery = session.getNamedQuery(sql);
            int i = 0;
            for(Object param : params){
                nameQuery.setParameter(i++,param);
            }
            List list = nameQuery.list();
            return list;
        });
    }
}
