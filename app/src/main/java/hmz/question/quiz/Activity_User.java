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
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import hmz.question.quiz.Database.Class_Dao.User_Dao;
import hmz.question.quiz.Fragments.Fragment_Users.Fragment_List_Category_User;
import hmz.question.quiz.Fragments.Fragment_Users.Fragment_List_Exam_Passed;
import hmz.question.quiz.Fragments.Fragment_Users.Fragment_List_Users;

public class Activity_User extends AppCompatActivity {

    String username;
    Integer id_User;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;

    private FragmentManager manager;
    private FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        username = getIntent().getStringExtra(User_Dao.Column_Username);
        id_User = getIntent().getIntExtra(User_Dao.Column_Id,1);
        setTitle(username);

        Fragment_List_Category_User fr = new Fragment_List_Category_User();
        Bundle bdl = new Bundle();
        bdl.putInt(User_Dao.Column_Id,id_User);
        fr.setArguments(bdl);
        replace_Fragment(fr);
        fr.manager = manager;

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_user);
        navigationView = (NavigationView) findViewById(R.id.navigation_view_user);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.app_open,R.string.app_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Integer id = item.getItemId();
                switch (id)
                {
                    case R.id.item_Exams_user:
                        Fragment_List_Category_User fr = new Fragment_List_Category_User();
                        Bundle bdl = new Bundle();
                        bdl.putInt(User_Dao.Column_Id,id_User);
                        fr.setArguments(bdl);
                        replace_Fragment(fr);
                        fr.manager = manager;
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.item_exam_pessed:
                        Fragment_List_Exam_Passed fragment_list_exam_passed = new Fragment_List_Exam_Passed();
                        Bundle bundle = new Bundle();
                        bundle.putInt(User_Dao.Column_Id,id_User);
                        fragment_list_exam_passed.setArguments(bundle);
                        replace_Fragment(fragment_list_exam_passed);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.item_sign_out_user:
                        AlertDialog.Builder builder = new AlertDialog.Builder(Activity_User.this);
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

    }//fin on create
    private void replace_Fragment(Fragment fragment)
    {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.container_fragments_user,fragment);
        transaction.commit();
    }
}