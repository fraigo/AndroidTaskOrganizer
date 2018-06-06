package me.franciscoigor.taskorganizer.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by francisco on 2018-05-28.
 */

public class ItemStorage {

    static ItemStorage sItemStorage;
    private Context mContext;
    private SQLiteDatabase mDatabase;


    private ItemStorage(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new DbHelper(mContext).getWritableDatabase();
        int count=getItems().size();
        if (count==0){
            for (int i=0; i<20; i++){
                ItemModel item=new ItemModel() {
                    @Override
                    public UUID getUUID() {
                        return UUID.randomUUID();
                    }
                };
                addItem(item);
            }
        }
    }

    public static ItemStorage get(Context context){
        if (sItemStorage == null){
            sItemStorage = new ItemStorage(context);
        }
        return sItemStorage;
    }

    public ArrayList<ItemModel> getItems() {
        ArrayList<ItemModel> items = new ArrayList<>();
        ListItemCursorWrapper cursor = queryItems(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                items.add(cursor.getItem());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return items;
    }

    public ItemModel getItem(UUID uuid){
        ListItemCursorWrapper cursor = queryItems(
                DbSchema.ItemTable.Columns.UUID + " = ?",
                new String[] { uuid.toString() }
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getItem();
        } finally {
            cursor.close();
        }
    }

    private static ContentValues getContentValues(ItemModel item) {
        ContentValues values = new ContentValues();
        values.put(DbSchema.ItemTable.Columns.UUID, item.getUUID().toString());

        return values;
    }

    public void addItem(ItemModel item){
        ContentValues values=getContentValues(item);
        mDatabase.insert(DbSchema.ItemTable.NAME, null, values);
    }

    public void updateItem(ItemModel item) {
        String uuidString = item.getUUID().toString();
        ContentValues values = getContentValues(item);
        mDatabase.update(DbSchema.ItemTable.NAME, values,
                DbSchema.ItemTable.Columns.UUID + " = ?",
                new String[] { uuidString });
    }

    public void deleteItem(ItemModel item){
        String uuidString = item.getUUID().toString();
        mDatabase.delete(DbSchema.ItemTable.NAME,
                DbSchema.ItemTable.Columns.UUID + " = ?",
                new String[] { uuidString });
    }

    private ListItemCursorWrapper queryItems(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                DbSchema.ItemTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new ListItemCursorWrapper(cursor);
    }
}
