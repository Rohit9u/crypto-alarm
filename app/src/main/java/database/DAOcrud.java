package database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

public interface DAOcrud {
    @Insert
    void insert(setAlarm alarm);
    @Query("DELETE FROM alarm_table")
    void deleteAll();
    @Query("DELETE FROM alarm_table where id= :id")
    int deleteNode(int id);
    @Query("UPDATE alarm_table SET coin_price= :price where id= :id ")
    int update(String price,int id);
    @Query("SELECT * FROM alarm_table order by id")
    LiveData<List<setAlarm>> showAll();



}
