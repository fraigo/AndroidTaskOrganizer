package me.franciscoigor.taskorganizer.data;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

public class ListItemCursorWrapper extends CursorWrapper {

    public ListItemCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public ItemModel getItem() {
        String uuidString = getString(getColumnIndex(DbSchema.ItemTable.Columns.UUID));
        String title = getString(getColumnIndex(DbSchema.ItemTable.Columns.TITLE));
        long date = getLong(getColumnIndex(DbSchema.ItemTable.Columns.DATE));
        int isSolved = getInt(getColumnIndex(DbSchema.ItemTable.Columns.SOLVED));

        ItemModel item = new ItemModel() {
            @Override
            public UUID getUUID() {
                return UUID.randomUUID();
            }
        };

        return item;
    }


}
