package com.example.mikebanks.bankscorpfinancial;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;  // Note: Use AlertDialog from the appcompat library
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
public class LaunchActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.login_frm_content, new LoginFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getBaseContext(), R.string.account_cancelled, Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }
    public void removeUpButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    public void showUpButton() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            displayHelpDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    private void displayHelpDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        
        builder.setTitle("Help")
                .setMessage("This Bank App Demo was made by Mike Banks. Soon, this dialog will give the user help, depending on where they are in the app");

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //TODO: NOTE: This code can sometimes run multiple times if login is pressed quickly in succession
    public void login() {
        Intent intent = new Intent(getApplicationContext(), DrawerActivity.class);
        startActivity(intent);
        finish();
    }

    public void profileCreated(Bundle bundle) {

        Toast.makeText(this, R.string.account_success, Toast.LENGTH_SHORT).show();

        LoginFragment loginFragment = new LoginFragment();
        loginFragment.setArguments(bundle);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.login_frm_content, loginFragment).commit();
    }
}
