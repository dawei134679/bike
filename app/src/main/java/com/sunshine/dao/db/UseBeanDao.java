package com.sunshine.dao.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.coolu.nokelock.bike.bean.UseBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USE_BEAN".
*/
public class UseBeanDao extends AbstractDao<UseBean, Void> {

    public static final String TABLENAME = "USE_BEAN";

    /**
     * Properties of entity UseBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Nickname = new Property(0, String.class, "nickname", false, "NICKNAME");
        public final static Property Username = new Property(1, String.class, "username", false, "USERNAME");
        public final static Property Idcard = new Property(2, String.class, "idcard", false, "IDCARD");
        public final static Property Deposit = new Property(3, String.class, "deposit", false, "DEPOSIT");
        public final static Property DepositDefault = new Property(4, String.class, "depositDefault", false, "DEPOSIT_DEFAULT");
        public final static Property Balance = new Property(5, String.class, "balance", false, "BALANCE");
        public final static Property Picurl = new Property(6, String.class, "picurl", false, "PICURL");
        public final static Property Userstate = new Property(7, String.class, "userstate", false, "USERSTATE");
        public final static Property State = new Property(8, String.class, "state", false, "STATE");
        public final static Property Lockid = new Property(9, String.class, "lockid", false, "LOCKID");
        public final static Property Barcode = new Property(10, String.class, "barcode", false, "BARCODE");
        public final static Property Locktype = new Property(11, String.class, "locktype", false, "LOCKTYPE");
        public final static Property Lockmac = new Property(12, String.class, "lockmac", false, "LOCKMAC");
        public final static Property Data = new Property(13, String.class, "data", false, "DATA");
        public final static Property Ordernum = new Property(14, String.class, "ordernum", false, "ORDERNUM");
        public final static Property Starttime = new Property(15, String.class, "starttime", false, "STARTTIME");
        public final static Property Endtime = new Property(16, String.class, "endtime", false, "ENDTIME");
        public final static Property Money = new Property(17, String.class, "money", false, "MONEY");
    };


    public UseBeanDao(DaoConfig config) {
        super(config);
    }
    
    public UseBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USE_BEAN\" (" + //
                "\"NICKNAME\" TEXT," + // 0: nickname
                "\"USERNAME\" TEXT," + // 1: username
                "\"IDCARD\" TEXT," + // 2: idcard
                "\"DEPOSIT\" TEXT," + // 3: deposit
                "\"DEPOSIT_DEFAULT\" TEXT," + // 4: depositDefault
                "\"BALANCE\" TEXT," + // 5: balance
                "\"PICURL\" TEXT," + // 6: picurl
                "\"USERSTATE\" TEXT," + // 7: userstate
                "\"STATE\" TEXT," + // 8: state
                "\"LOCKID\" TEXT," + // 9: lockid
                "\"BARCODE\" TEXT," + // 10: barcode
                "\"LOCKTYPE\" TEXT," + // 11: locktype
                "\"LOCKMAC\" TEXT," + // 12: lockmac
                "\"DATA\" TEXT," + // 13: data
                "\"ORDERNUM\" TEXT," + // 14: ordernum
                "\"STARTTIME\" TEXT," + // 15: starttime
                "\"ENDTIME\" TEXT," + // 16: endtime
                "\"MONEY\" TEXT);"); // 17: money
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USE_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UseBean entity) {
        stmt.clearBindings();
 
        String nickname = entity.getNickname();
        if (nickname != null) {
            stmt.bindString(1, nickname);
        }
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(2, username);
        }
 
        String idcard = entity.getIdcard();
        if (idcard != null) {
            stmt.bindString(3, idcard);
        }
 
        String deposit = entity.getDeposit();
        if (deposit != null) {
            stmt.bindString(4, deposit);
        }
 
        String depositDefault = entity.getDepositDefault();
        if (depositDefault != null) {
            stmt.bindString(5, depositDefault);
        }
 
        String balance = entity.getBalance();
        if (balance != null) {
            stmt.bindString(6, balance);
        }
 
        String picurl = entity.getPicurl();
        if (picurl != null) {
            stmt.bindString(7, picurl);
        }
 
        String userstate = entity.getUserstate();
        if (userstate != null) {
            stmt.bindString(8, userstate);
        }
 
        String state = entity.getState();
        if (state != null) {
            stmt.bindString(9, state);
        }
 
        String lockid = entity.getLockid();
        if (lockid != null) {
            stmt.bindString(10, lockid);
        }
 
        String barcode = entity.getBarcode();
        if (barcode != null) {
            stmt.bindString(11, barcode);
        }
 
        String locktype = entity.getLocktype();
        if (locktype != null) {
            stmt.bindString(12, locktype);
        }
 
        String lockmac = entity.getLockmac();
        if (lockmac != null) {
            stmt.bindString(13, lockmac);
        }
 
        String data = entity.getData();
        if (data != null) {
            stmt.bindString(14, data);
        }
 
        String ordernum = entity.getOrdernum();
        if (ordernum != null) {
            stmt.bindString(15, ordernum);
        }
 
        String starttime = entity.getStarttime();
        if (starttime != null) {
            stmt.bindString(16, starttime);
        }
 
        String endtime = entity.getEndtime();
        if (endtime != null) {
            stmt.bindString(17, endtime);
        }
 
        String money = entity.getMoney();
        if (money != null) {
            stmt.bindString(18, money);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UseBean entity) {
        stmt.clearBindings();
 
        String nickname = entity.getNickname();
        if (nickname != null) {
            stmt.bindString(1, nickname);
        }
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(2, username);
        }
 
        String idcard = entity.getIdcard();
        if (idcard != null) {
            stmt.bindString(3, idcard);
        }
 
        String deposit = entity.getDeposit();
        if (deposit != null) {
            stmt.bindString(4, deposit);
        }
 
        String depositDefault = entity.getDepositDefault();
        if (depositDefault != null) {
            stmt.bindString(5, depositDefault);
        }
 
        String balance = entity.getBalance();
        if (balance != null) {
            stmt.bindString(6, balance);
        }
 
        String picurl = entity.getPicurl();
        if (picurl != null) {
            stmt.bindString(7, picurl);
        }
 
        String userstate = entity.getUserstate();
        if (userstate != null) {
            stmt.bindString(8, userstate);
        }
 
        String state = entity.getState();
        if (state != null) {
            stmt.bindString(9, state);
        }
 
        String lockid = entity.getLockid();
        if (lockid != null) {
            stmt.bindString(10, lockid);
        }
 
        String barcode = entity.getBarcode();
        if (barcode != null) {
            stmt.bindString(11, barcode);
        }
 
        String locktype = entity.getLocktype();
        if (locktype != null) {
            stmt.bindString(12, locktype);
        }
 
        String lockmac = entity.getLockmac();
        if (lockmac != null) {
            stmt.bindString(13, lockmac);
        }
 
        String data = entity.getData();
        if (data != null) {
            stmt.bindString(14, data);
        }
 
        String ordernum = entity.getOrdernum();
        if (ordernum != null) {
            stmt.bindString(15, ordernum);
        }
 
        String starttime = entity.getStarttime();
        if (starttime != null) {
            stmt.bindString(16, starttime);
        }
 
        String endtime = entity.getEndtime();
        if (endtime != null) {
            stmt.bindString(17, endtime);
        }
 
        String money = entity.getMoney();
        if (money != null) {
            stmt.bindString(18, money);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public UseBean readEntity(Cursor cursor, int offset) {
        UseBean entity = new UseBean( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // nickname
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // username
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // idcard
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // deposit
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // depositDefault
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // balance
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // picurl
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // userstate
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // state
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // lockid
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // barcode
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // locktype
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // lockmac
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // data
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // ordernum
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // starttime
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // endtime
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17) // money
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UseBean entity, int offset) {
        entity.setNickname(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setUsername(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setIdcard(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDeposit(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setDepositDefault(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setBalance(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setPicurl(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setUserstate(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setState(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setLockid(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setBarcode(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setLocktype(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setLockmac(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setData(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setOrdernum(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setStarttime(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setEndtime(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setMoney(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(UseBean entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(UseBean entity) {
        return null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
