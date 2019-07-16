package com.sunshine.dao.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.coolu.nokelock.bike.bean.BikeOrderBean;
import com.coolu.nokelock.bike.bean.OrderBean;
import com.coolu.nokelock.bike.bean.SearchBean;
import com.coolu.nokelock.bike.bean.UndoneOrderBean;
import com.coolu.nokelock.bike.bean.UseBean;
import com.coolu.nokelock.bike.bean.UserEntityBean;

import com.sunshine.dao.db.BikeOrderBeanDao;
import com.sunshine.dao.db.OrderBeanDao;
import com.sunshine.dao.db.SearchBeanDao;
import com.sunshine.dao.db.UndoneOrderBeanDao;
import com.sunshine.dao.db.UseBeanDao;
import com.sunshine.dao.db.UserEntityBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig bikeOrderBeanDaoConfig;
    private final DaoConfig orderBeanDaoConfig;
    private final DaoConfig searchBeanDaoConfig;
    private final DaoConfig undoneOrderBeanDaoConfig;
    private final DaoConfig useBeanDaoConfig;
    private final DaoConfig userEntityBeanDaoConfig;

    private final BikeOrderBeanDao bikeOrderBeanDao;
    private final OrderBeanDao orderBeanDao;
    private final SearchBeanDao searchBeanDao;
    private final UndoneOrderBeanDao undoneOrderBeanDao;
    private final UseBeanDao useBeanDao;
    private final UserEntityBeanDao userEntityBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        bikeOrderBeanDaoConfig = daoConfigMap.get(BikeOrderBeanDao.class).clone();
        bikeOrderBeanDaoConfig.initIdentityScope(type);

        orderBeanDaoConfig = daoConfigMap.get(OrderBeanDao.class).clone();
        orderBeanDaoConfig.initIdentityScope(type);

        searchBeanDaoConfig = daoConfigMap.get(SearchBeanDao.class).clone();
        searchBeanDaoConfig.initIdentityScope(type);

        undoneOrderBeanDaoConfig = daoConfigMap.get(UndoneOrderBeanDao.class).clone();
        undoneOrderBeanDaoConfig.initIdentityScope(type);

        useBeanDaoConfig = daoConfigMap.get(UseBeanDao.class).clone();
        useBeanDaoConfig.initIdentityScope(type);

        userEntityBeanDaoConfig = daoConfigMap.get(UserEntityBeanDao.class).clone();
        userEntityBeanDaoConfig.initIdentityScope(type);

        bikeOrderBeanDao = new BikeOrderBeanDao(bikeOrderBeanDaoConfig, this);
        orderBeanDao = new OrderBeanDao(orderBeanDaoConfig, this);
        searchBeanDao = new SearchBeanDao(searchBeanDaoConfig, this);
        undoneOrderBeanDao = new UndoneOrderBeanDao(undoneOrderBeanDaoConfig, this);
        useBeanDao = new UseBeanDao(useBeanDaoConfig, this);
        userEntityBeanDao = new UserEntityBeanDao(userEntityBeanDaoConfig, this);

        registerDao(BikeOrderBean.class, bikeOrderBeanDao);
        registerDao(OrderBean.class, orderBeanDao);
        registerDao(SearchBean.class, searchBeanDao);
        registerDao(UndoneOrderBean.class, undoneOrderBeanDao);
        registerDao(UseBean.class, useBeanDao);
        registerDao(UserEntityBean.class, userEntityBeanDao);
    }
    
    public void clear() {
        bikeOrderBeanDaoConfig.getIdentityScope().clear();
        orderBeanDaoConfig.getIdentityScope().clear();
        searchBeanDaoConfig.getIdentityScope().clear();
        undoneOrderBeanDaoConfig.getIdentityScope().clear();
        useBeanDaoConfig.getIdentityScope().clear();
        userEntityBeanDaoConfig.getIdentityScope().clear();
    }

    public BikeOrderBeanDao getBikeOrderBeanDao() {
        return bikeOrderBeanDao;
    }

    public OrderBeanDao getOrderBeanDao() {
        return orderBeanDao;
    }

    public SearchBeanDao getSearchBeanDao() {
        return searchBeanDao;
    }

    public UndoneOrderBeanDao getUndoneOrderBeanDao() {
        return undoneOrderBeanDao;
    }

    public UseBeanDao getUseBeanDao() {
        return useBeanDao;
    }

    public UserEntityBeanDao getUserEntityBeanDao() {
        return userEntityBeanDao;
    }

}
