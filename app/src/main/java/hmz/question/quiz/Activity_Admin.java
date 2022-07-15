package hmz.question.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import hmz.question.quiz.Database.Class_Dao.User_Dao;
import hmz.question.quiz.Fragments.Fragment_Category;
import hmz.question.quiz.Fragments.Fragment_Exam.Fragment_List_Exam;
import hmz.question.quiz.Fragments.Fragment_Users.Fragment_List_Users;

public class Activity_Admin extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;

    private FragmentManager manager;
   private FragmentTransaction transaction;

    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        username = getIntent().getStringExtra(User_Dao.Column_Username);
        setTitle(username);

        Fragment_Category fragment_category = new Fragment_Category();
        replace_Fragment(fragment_category);
        fragment_category.manager = manager;

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_admin);
        navigationView = (NavigationView) findViewById(R.id.navigation_view_admin);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.app_open,R.string.app_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Integer id = item.getItemId();
                switch (id)
                {
                    case R.id.item_categorys:
                        Fragment_Category fragment_category = new Fragment_Category();
                        replace_Fragment(fragment_category);
                        fragment_category.manager = manager;
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.item_exams:
                        replace_Fragment(new Fragment_List_Exam());
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.item_users:
                        replace_Fragment(new Fragment_List_Users());
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.item_sign_out_admin:
                        AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Admin.this);
                        builder.setTitle("Sing Out").setMessage("Are you sue to sing out ?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        moveTaskToBack(true);
                                        android.os.Process.killProcess(android.os.Process.myPid());
                                        System.exit(1);
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                }).show();
                        break;
                }
                return false;
            }
        });

    }//fin on crete
    private void replace_Fragment(Fragment fragment)
    {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.container_fragments_admin,fragment);
        transaction.commit();
    }
}