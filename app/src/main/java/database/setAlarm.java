package database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import androidx.annotation.NonNull;

@Entity(tableName ="alarm_table")
public class setAlarm {
@PrimaryKey(autoGenerate = true)
    int id;
@NonNull
@ColumnInfo(name = "coin_name")
    private String coinName;
@NonNull
@ColumnInfo(name = "coin_price")
    private String coinPrice;
@NonNull
@ColumnInfo(name="coin_id")
    private String coinId;

    public setAlarm(@NonNull String name,@NonNull String price,@NonNull String id){
        this.coinName=name;
        this.coinPrice=price;
        this.coinId=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(@NonNull String coinName) {
        this.coinName = coinName;
    }

    public String getCoinPrice() {
        return coinPrice;
    }

    public void setCoinPrice(String coinPrice) {
        this.coinPrice = coinPrice;
    }

    public String getCoinId() {
        return coinId;
    }

    public void setCoinId(String coinId) {
        this.coinId = coinId;
    }
}
