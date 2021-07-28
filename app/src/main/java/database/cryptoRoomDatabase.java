package database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = setAlarm.class,version = 1)
public abstract class cryptoRoomDatabase extends RoomDatabase {
    private static volatile cryptoRoomDatabase INSTANCE;
    public abstract DAOcrud daOcrud();
    static cryptoRoomDatabase getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (cryptoRoomDatabase.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),cryptoRoomDatabase.class,"All_alarm_history_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
