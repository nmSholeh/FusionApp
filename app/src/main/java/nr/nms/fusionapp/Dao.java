package nr.nms.fusionapp;

import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Insert
    Void AddData(ItemsList itemsList);

    @Query("select * from Itemslist ORDER BY isDone")
    List<ItemsList> getItemsData();

    @Query("UPDATE Itemslist SET isDone=:isFinished WHERE id = :rowId")
    void update(int rowId, boolean isFinished);


}
