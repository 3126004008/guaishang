package com.houwei.guaishang.database.entity;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "HOME_TOPIC_CACHE_DATA".
*/
public class HomeTopicCacheDataDao extends AbstractDao<HomeTopicCacheData, Long> {

    public static final String TABLENAME = "HOME_TOPIC_CACHE_DATA";

    /**
     * Properties of entity HomeTopicCacheData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Data = new Property(1, String.class, "data", false, "DATA");
        public final static Property Type = new Property(2, int.class, "type", false, "TYPE");
    }


    public HomeTopicCacheDataDao(DaoConfig config) {
        super(config);
    }
    
    public HomeTopicCacheDataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"HOME_TOPIC_CACHE_DATA\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"DATA\" TEXT," + // 1: data
                "\"TYPE\" INTEGER NOT NULL );"); // 2: type
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"HOME_TOPIC_CACHE_DATA\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, HomeTopicCacheData entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String data = entity.getData();
        if (data != null) {
            stmt.bindString(2, data);
        }
        stmt.bindLong(3, entity.getType());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, HomeTopicCacheData entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String data = entity.getData();
        if (data != null) {
            stmt.bindString(2, data);
        }
        stmt.bindLong(3, entity.getType());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public HomeTopicCacheData readEntity(Cursor cursor, int offset) {
        HomeTopicCacheData entity = new HomeTopicCacheData( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // data
            cursor.getInt(offset + 2) // type
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, HomeTopicCacheData entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setData(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setType(cursor.getInt(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(HomeTopicCacheData entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(HomeTopicCacheData entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(HomeTopicCacheData entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}