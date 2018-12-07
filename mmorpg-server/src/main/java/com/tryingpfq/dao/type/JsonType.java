package com.tryingpfq.dao.type;

import com.alibaba.druid.support.json.JSONUtils;
import com.tryingpfq.common.utils.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * @author tryingpfq
 * @date 2018/12/7 16:01
 */
public class JsonType implements UserType {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonType.class);

    private static final int[] SQL_TYPES = {Types.VARCHAR,Types.VARCHAR};

    /**
     * 在生成DDL时队列采用什么样的SQL语法
     * @return
     */
    @Override
    public int[] sqlTypes() {
        return new int[]{Types.CLOB};
    }

    /** 返回怎样的映射类型 **/
    @Override
    public Class returnedClass() {
        return Object.class;
    }

    @Override
    public boolean equals(Object o, Object o1) throws HibernateException {
        if( o == o1)
            return true;
        if(o == null || o1 == null){
            return false;
        }
        return ObjectUtils.nullSafeEquals(o,o1);
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        return o.hashCode();
    }

    /**
     * 读取数据，并组装成对象，name
     * @param rs
     * @param names 参数顺序按照映射文件定义顺序
     * @param ssc
     * @param o
     * @return
     * @throws HibernateException
     * @throws SQLException
     */
    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor ssc, Object o) throws HibernateException, SQLException {
        if(rs.wasNull())
            return null;

        String json = rs.getString(names[0]);
        if(StringUtils.isBlank(json))
            return null;

        return JSONUtils.parse(json);
    }

    /** 保存数据
     * @param st
     * @param o
     * @param index 按照映射文件定义的顺序 从0 开始
     * @param ssc
     * @throws HibernateException
     * @throws SQLException
     */
    @Override
    public void nullSafeSet(PreparedStatement st, Object o, int index, SharedSessionContractImplementor ssc) throws HibernateException, SQLException {
        if(o == null)
            st.setNull(index,Types.VARBINARY);
        else
            st.setString(index,JSONUtils.toJSONString(o));
    }

    @Override
    public Object deepCopy(Object o) throws HibernateException {
        String str = JSONUtils.toJSONString(o);
        return JSONUtils.parse(str);
    }

    /** 表明这个类的实例在创建以后就不可以改变属性，
     * hibernate可以为不可改变的类做一些性能优化
     * @return
     */
    @Override
    public boolean isMutable() {
        return true;
    }

    /** 当把JsonType写入二级缓存时，该方法被调用 **/
    @Override
    public Serializable disassemble(Object o) throws HibernateException {
        return (Serializable) deepCopy(o);
    }

    /** 当从二级缓存读取JsonType类型时，该方法被调用 **/
    @Override
    public Object assemble(Serializable serializable, Object o) throws HibernateException {
        return deepCopy(serializable);
    }

    @Override
    public Object replace(Object o, Object o1, Object o2) throws HibernateException {
        return null;
    }
}
