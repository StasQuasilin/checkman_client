package checkman.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import checkman.client.R;
import checkman.utils.connection.SocketConnector;

public class ParentActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    private long backPressedTime;

    private Toast backToast;

    public void onBackPressed() {
        if (this.backPressedTime + 2000L > System.currentTimeMillis()) {
            backToast.cancel();
            finish();
        } else {
            backToast = Toast.makeText(getBaseContext(), R.string.press_back, Toast.LENGTH_SHORT);
            backToast.show();
        }
        this.backPressedTime = System.currentTimeMillis();
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.main);

        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));

        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        appBarConfiguration = (new AppBarConfiguration.Builder(R.id.transport)).setDrawerLayout(drawerLayout).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        SocketConnector.getInstance().connect();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(@NotNull MenuItem paramMenuItem) {
        return super.onOptionsItemSelected(paramMenuItem);
    }

    public boolean onSupportNavigateUp() {
        return (NavigationUI.navigateUp(Navigation.findNavController((Activity)this, R.id.nav_host_fragment), appBarConfiguration) || super.onSupportNavigateUp());
    }
}
