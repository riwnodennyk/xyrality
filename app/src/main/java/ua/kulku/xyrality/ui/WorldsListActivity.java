package ua.kulku.xyrality.ui;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.List;

import retrofit.Callback;
import retrofit.JacksonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import ua.kulku.xyrality.R;
import ua.kulku.xyrality.data.api.XyralityApi;
import ua.kulku.xyrality.data.model.World;
import ua.kulku.xyrality.data.model.WorldsListResponse;

public class WorldsListActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://backend1.lordsandknights.com";
    public static final String E_EMAIL = "email extra";
    public static final String E_PASSWORD = "password extra";
    private XyralityApi mXyralityApi;

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worlds_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.worlds_list_content);
        mRecyclerView.setVisibility(View.GONE);
        mProgressBar = ((ProgressBar) findViewById(R.id.worlds_list_loading));
        mProgressBar.setVisibility(View.VISIBLE);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        retrofit.client().interceptors().add(new Interceptor() {
            @Override
            public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                com.squareup.okhttp.Response response = chain.proceed(request);
                return response;
            }
        });
        mXyralityApi = retrofit.create(XyralityApi.class);

        loadWorldsList();
    }

    private void loadWorldsList() {
        String login = getIntent().getStringExtra(E_EMAIL);
        String password = getIntent().getStringExtra(E_PASSWORD);
        String deviceId = macAddress();
        String deviceType = String.format("%s %s", Build.MODEL, Build.VERSION.RELEASE);
        mXyralityApi.listWorlds(
                login,
                password,
                deviceType,
                deviceId
        ).enqueue(new Callback<WorldsListResponse>() {
            @Override
            public void onResponse(Response<WorldsListResponse> response) {
                mProgressBar.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                mRecyclerView.setAdapter(new WorldsListAdapter(response.body().allAvailableWorlds()));
            }

            @Override
            public void onFailure(Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                Snackbar.make(mRecyclerView, t.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private String macAddress() {
        //todo implement api 23 permission request
        WifiManager manager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        return info.getMacAddress();
    }

    private static class WorldsListAdapter extends RecyclerView.Adapter {
        private List<World> mWorlds;

        public WorldsListAdapter(List<World> worlds) {
            mWorlds = worlds;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        }

        @Override
        public int getItemCount() {
            return mWorlds.size();
        }
    }
}
