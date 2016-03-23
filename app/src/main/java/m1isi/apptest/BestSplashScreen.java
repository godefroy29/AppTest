package m1isi.apptest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BestSplashScreen extends Activity {
    ProgressDialog progressDialog;
    TextView txtName;
    TextView txtPwd;

    Intent accueil;

    Button btnAdmin;
    Button btnConnexion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_best_splash_screen);

        txtName = (TextView) findViewById(R.id.txtName);
        txtPwd = (TextView) findViewById(R.id.txtPwd);
        accueil = new Intent(this, Accueil.class);
        btnAdmin = (Button) findViewById(R.id.btnAdmin);
        btnConnexion = (Button) findViewById(R.id.btnConnexion);

        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(accueil);
                finish();
            }
        });
        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = ProgressDialog.show(BestSplashScreen.this, "Connecting", "Trying to verify your informations", true, false);
                BestSplashScreenDB testId = new BestSplashScreenDB(BestSplashScreen.this);
                testId.testId(txtName.getText().toString(), txtPwd.getText().toString());
            }
        });
    }

    public void testIdValide(boolean idValide){
        progressDialog.dismiss();
        if (idValide){
            startActivity(accueil);
            finish();
        }else{
            Toast.makeText(BestSplashScreen.this, R.string.bestSplashScreen_connect_not_found, Toast.LENGTH_LONG).show();
        }
    }

    public void testIdError(){
        progressDialog.dismiss();

        Toast.makeText(BestSplashScreen.this, R.string.bestSplashScreen_connect_error, Toast.LENGTH_LONG).show();
    }

}
