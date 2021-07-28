package database;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

public class viewModel extends AndroidViewModel {
    private repository reposit;
    private LiveData<List<setAlarm>> liveData;
    public viewModel(Application application) {
        super(application);
        reposit=new repository(application);
        liveData=reposit.showAll();
    }
    LiveData<List<setAlarm>> showAll(){
        return liveData;
    }
    public void insert (setAlarm alm){
        reposit.insert(alm);
    }
}
