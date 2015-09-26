package ua.kulku.xyrality.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import retrofit.Retrofit;
import ua.kulku.xyrality.R;
import ua.kulku.xyrality.data.api.XyralityApi;
import ua.kulku.xyrality.data.model.WorldsListRequest;

public class WorldsListActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://backend1.lordsandknights.com";
    public static final String E_EMAIL = "email extra";
    public static final String E_PASSWORD = "password extra";
    private XyralityApi mXyralityApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worlds_list);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        mXyralityApi = retrofit.create(XyralityApi.class);

        loadWorldsList();
    }

    private void loadWorldsList() {
        String login = getIntent().getStringExtra(E_EMAIL);
        String password = getIntent().getStringExtra(E_PASSWORD);
        String deviceType = null;
        String deviceId = null;
        WorldsListRequest request = WorldsListRequest.builder()
                .login(login)
                .password(password)
                .deviceId(deviceId)
                .deviceType(deviceType)
                .build();
        mXyralityApi.listWorlds(request);
    }
}
