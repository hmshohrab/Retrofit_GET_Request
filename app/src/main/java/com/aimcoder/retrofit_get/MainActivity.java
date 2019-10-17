package com.aimcoder.retrofit_get;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * @author SHOHRAB
 * @date 17-Oct-19
 */
public class MainActivity extends AppCompatActivity {

    private TextView city, country, asn_org;
    private EditText ipAddress;
    private KProgressHUD hud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city = findViewById(R.id.cityId);
        country = findViewById(R.id.countryId);
        asn_org = findViewById(R.id.asn_orgId);
        ipAddress = findViewById(R.id.ipAddressId);

    }

    public void showMyIp(View view) {
        ipAddress.setText("");
        city.setText("");
        country.setText("");
        asn_org.setText("");
        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f);
        hud.show();
        ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);

        Call<ServerResponse> call = apiInterface.getMyIp();
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse serverResponse = response.body();
                hud.dismiss();
                if (response.code()==200 && serverResponse!=null){
                    ipAddress.setText(serverResponse.getIp());
                    city.setText(serverResponse.getCity());
                    country.setText(serverResponse.getCountry());
                    asn_org.setText(serverResponse.getAsn_org());
                }
                else {
                    ipAddress.setText(response.message());
                    city.setText("");
                    country.setText("");
                    asn_org.setText("");
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                hud.dismiss();
                ipAddress.setText(t.toString());


            }
        });
    }
}
