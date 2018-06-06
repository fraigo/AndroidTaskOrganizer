package me.franciscoigor.taskorganizer.app;

import java.util.UUID;

import me.franciscoigor.taskorganizer.data.ItemModel;

public class TaskItem implements ItemModel {

    private UUID uuid;
    private static String TABLE_NAME = "tasks";

    public TaskItem(){
        uuid = UUID.randomUUID();
    }

    @Override
    public UUID getUUID() {
        return null;
    }

    @Override
    public String getModelName() {
        return TABLE_NAME;
    }
}
