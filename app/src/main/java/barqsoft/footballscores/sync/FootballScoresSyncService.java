package barqsoft.footballscores.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by ryan on 8/31/2015.
 */
public class FootballScoresSyncService extends Service
{
    private static final String LOG_TAG = FootballScoresSyncService.class.getSimpleName();

    private static final Object mSyncAdapterLock = new Object();

    private static FootballScoresSyncAdapter mFootballScoresSyncAdapter = null;

    @Override
    public void onCreate()
    {
        Log.d(LOG_TAG, "onCreate - FootballScoresSyncService");

        synchronized (mSyncAdapterLock)
        {
            if (mFootballScoresSyncAdapter == null)
            {
                mFootballScoresSyncAdapter = new FootballScoresSyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return mFootballScoresSyncAdapter.getSyncAdapterBinder();
    }
}
