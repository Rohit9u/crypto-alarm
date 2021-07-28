package database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class repository {
    DAOcrud daOcrud;
    LiveData<List<setAlarm>> liveData;
    public repository(Application application){
        cryptoRoomDatabase cryptoDB=cryptoRoomDatabase.getDatabase(application);
        daOcrud=cryptoDB.daOcrud();
        liveData=daOcrud.showAll();
    }
    public LiveData<List<setAlarm>> showAll(){
        return liveData;
    }
    public void insert(setAlarm alarm){
        new insertAsyncTask(daOcrud).execute(alarm);
    }

    private class insertAsyncTask extends AsyncTask<setAlarm,Void,Void> {
        private DAOcrud asyncTask;
        public insertAsyncTask(DAOcrud daOcrud) {
            asyncTask=daOcrud;
        }

        @Override
        protected Void doInBackground(setAlarm... setAlarms) {
            asyncTask.insert(setAlarms[0]);
            return null;
        }
    }
}
