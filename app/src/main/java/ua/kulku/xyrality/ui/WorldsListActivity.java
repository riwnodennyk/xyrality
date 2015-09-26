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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.okhttp.Interceptor;

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
        mRecyclerView.setHasFixedSize(true);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        retrofit.client().interceptors().add(new Interceptor() {
            @Override
            public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
                return chain.proceed(chain.request());
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


                WorldsListResponse body = response.body();
                if (body != null) {
                    mRecyclerView.setAdapter(new WorldsListAdapter(body.allAvailableWorlds()));
                } else {
                    String message;
                    try {
                        message = response.code() + ": " + response.errorBody().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                        message = getString(R.string.error);
                    }
                    showError(message);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                String message = t.getLocalizedMessage();
                showError(message);
            }
        });
    }

    private void showError(String message) {
        Snackbar.make(mRecyclerView, message, Snackbar.LENGTH_SHORT).show();
    }

    private String macAddress() {
        //todo implement api 23 permission request
        WifiManager manager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        return info.getMacAddress();
    }

    private static class WorldsListAdapter extends RecyclerView.Adapter {
        private List<World> mWorlds;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView mName;
            private final TextView mStatus;

            public ViewHolder(View v) {
                super(v);
                mName = (TextView) v.findViewById(R.id.world_name);
                mStatus = (TextView) v.findViewById(R.id.world_status);
            }
        }

        public WorldsListAdapter(List<World> worlds) {
            mWorlds = worlds;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_world, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            World world = mWorlds.get(position);

            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.mName.setText(world.name());
            viewHolder.mStatus.setText(world.worldStatus().description());
        }

        @Override
        public int getItemCount() {
            return mWorlds.size();
        }
    }
}
