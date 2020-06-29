package nr.nms.fusionapp;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ItemsList.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract Dao myDao();
}
