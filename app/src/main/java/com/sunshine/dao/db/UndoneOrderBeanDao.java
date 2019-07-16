package com.sunshine.dao.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.coolu.nokelock.bike.bean.UndoneOrderBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "UNDONE_ORDER_BEAN".
*/
public class UndoneOrderBeanDao extends AbstractDao<UndoneOrderBean, Long> {

    public static final String TABLENAME = "UNDONE_ORDER_BEAN";

    /**
     * Properties of entity UndoneOrderBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "_id");
        public final static Property Barcode = new Property(1, String.class, "barcode", false, "BARCODE");
        public final static Property Ordernum = new Property(2, String.class, "ordernum", false, "ORDERNUM");
        public final static Property Lat = new Property(3, String.class, "lat", false, "LAT");
        public final static Property Lng = new Property(4, String.class, "lng", false, "LNG");
        public final static Property Lockstate = new Property(5, String.class, "lockstate", false, "LOCKSTATE");
        public final static Property Power = new Property(6, String.class, "power", false, "POWER");
        public final static Property Result = new Property(7, String.class, "result", false, "RESULT");
        public final static Property Locktype = new Property(8, String.class, "locktype", false, "LOCKTYPE");
        public final static Property Mac = new Property(9, String.class, "mac", false, "MAC");
        public final static Property StartTime = new Property(10, String.class, "startTime", false, "START_TIME");
        public final static Property StopTime = new Property(11, String.class, "stopTime", false, "STOP_TIME");
        public final static Property Shebeiid = new Property(12, String.class, "shebeiid", false, "SHEBEIID");
    };


    public UndoneOrderBeanDao(DaoConfig config) {
        super(config);
    }
    
    public UndoneOrderBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"UNDONE_ORDER_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: id
                "\"BARCODE\" TEXT," + // 1: barcode
                "\"ORDERNUM\" TEXT," + // 2: ordernum
                "\"LAT\" TEXT," + // 3: lat
                "\"LNG\" TEXT," + // 4: lng
                "\"LOCKSTATE\" TEXT," + // 5: lockstate
                "\"POWER\" TEXT," + // 6: power
                "\"RESULT\" TEXT," + // 7: result
                "\"LOCKTYPE\" TEXT," + // 8: locktype
                "\"MAC\" TEXT," + // 9: mac
                "\"START_TIME\" TEXT," + // 10: startTime
                "\"STOP_TIME\" TEXT," + // 11: stopTime
                "\"SHEBEIID\" TEXT);"); // 12: shebeiid
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"UNDONE_ORDER_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UndoneOrderBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String barcode = entity.getBarcode();
        if (barcode != null) {
            stmt.bindString(2, barcode);
        }
 
        String ordernum = entity.getOrdernum();
        if (ordernum != null) {
            stmt.bindString(3, ordernum);
        }
 
        String lat = entity.getLat();
        if (lat != null) {
            stmt.bindString(4, lat);
        }
 
        String lng = entity.getLng();
        if (lng != null) {
            stmt.bindString(5, lng);
        }
 
        String lockstate = entity.getLockstate();
        if (lockstate != null) {
            stmt.bindString(6, lockstate);
        }
 
        String power = entity.getPower();
        if (power != null) {
            stmt.bindString(7, power);
        }
 
        String result = entity.getResult();
        if (result != null) {
            stmt.bindString(8, result);
        }
 
        String locktype = entity.getLocktype();
        if (locktype != null) {
            stmt.bindString(9, locktype);
        }
 
        String mac = entity.getMac();
        if (mac != null) {
            stmt.bindString(10, mac);
        }
 
        String startTime = entity.getStartTime();
        if (startTime != null) {
            stmt.bindString(11, startTime);
        }
 
        String stopTime = entity.getStopTime();
        if (stopTime != null) {
            stmt.bindString(12, stopTime);
        }
 
        String shebeiid = entity.getShebeiid();
        if (shebeiid != null) {
            stmt.bindString(13, shebeiid);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UndoneOrderBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String barcode = entity.getBarcode();
        if (barcode != null) {
            stmt.bindString(2, barcode);
        }
 
        String ordernum = entity.getOrdernum();
        if (ordernum != null) {
            stmt.bindString(3, ordernum);
        }
 
        String lat = entity.getLat();
        if (lat != null) {
            stmt.bindString(4, lat);
        }
 
        String lng = entity.getLng();
        if (lng != null) {
            stmt.bindString(5, lng);
        }
 
        String lockstate = entity.getLockstate();
        if (lockstate != null) {
            stmt.bindString(6, lockstate);
        }
 
        String power = entity.getPower();
        if (power != null) {
            stmt.bindString(7, power);
        }
 
        String result = entity.getResult();
        if (result != null) {
            stmt.bindString(8, result);
        }
 
        String locktype = entity.getLocktype();
        if (locktype != null) {
            stmt.bindString(9, locktype);
        }
 
        String mac = entity.getMac();
        if (mac != null) {
            stmt.bindString(10, mac);
        }
 
        String startTime = entity.getStartTime();
        if (startTime != null) {
            stmt.bindString(11, startTime);
        }
 
        String stopTime = entity.getStopTime();
        if (stopTime != null) {
            stmt.bindString(12, stopTime);
        }
 
        String shebeiid = entity.getShebeiid();
        if (shebeiid != null) {
            stmt.bindString(13, shebeiid);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public UndoneOrderBean readEntity(Cursor cursor, int offset) {
        UndoneOrderBean entity = new UndoneOrderBean( //
            cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // barcode
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // ordernum
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // lat
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // lng
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // lockstate
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // power
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // result
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // locktype
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // mac
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // startTime
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // stopTime
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12) // shebeiid
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UndoneOrderBean entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setBarcode(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setOrdernum(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setLat(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setLng(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setLockstate(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setPower(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setResult(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setLocktype(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setMac(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setStartTime(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setStopTime(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setShebeiid(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(UndoneOrderBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(UndoneOrderBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
