package com.example.interneeapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private RecyclerView recyclerView;
    private InternshipAdapter adapter;
    private List<Internship> internships = new ArrayList<>();
    private List<Internship> courses = new ArrayList<>();
    private TextView aboutUsTextView;
    private TextView contactDetailsTextView;
    private TextView pageTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        pageTitle = findViewById(R.id.pageTitle);

        // Initialize About Us and Contact details TextViews
        aboutUsTextView = findViewById(R.id.aboutUsTextView);
        contactDetailsTextView = findViewById(R.id.contactDetailsTextView);

        initializeData(); // Populate internships and courses lists

        adapter = new InternshipAdapter(internships);
        recyclerView.setAdapter(adapter);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_internships) {
                adapter.updateData(internships);
                recyclerView.setVisibility(View.VISIBLE);
                findViewById(R.id.aboutUsTextView).setVisibility(View.GONE);
                findViewById(R.id.contactDetailsTextView).setVisibility(View.GONE);
                pageTitle.setText("Internships");
                pageTitle.setVisibility(View.VISIBLE);
            } else if (id == R.id.nav_courses) {
                adapter.updateData(courses);
                recyclerView.setVisibility(View.VISIBLE);
                findViewById(R.id.aboutUsTextView).setVisibility(View.GONE);
                findViewById(R.id.contactDetailsTextView).setVisibility(View.GONE);
                pageTitle.setText("Courses");
                pageTitle.setVisibility(View.VISIBLE);
            } else if (id == R.id.nav_about) {
                recyclerView.setVisibility(View.GONE);
                findViewById(R.id.aboutUsTextView).setVisibility(View.VISIBLE);
                findViewById(R.id.contactDetailsTextView).setVisibility(View.VISIBLE);
                pageTitle.setVisibility(View.GONE);
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    private void initializeData() {
        internships.add(new Internship("Data Analytics", "Learn data analytics with hands-on projects", R.drawable.data_analytics));
        internships.add(new Internship("Cloud Computing", "Work on cloud computing platforms", R.drawable.cloud_computing));
        internships.add(new Internship("Flutter Development", "Build cross-platform apps", R.drawable.flutter));
        internships.add(new Internship("Graphic Design", "Create stunning graphics", R.drawable.graphic_design));
        internships.add(new Internship("Chatbot Development", "Develop chatbots using AI", R.drawable.chatbot_development));

        // Populate courses list
        courses.add(new Internship("Java Programming", "Learn the fundamentals of Java", R.drawable.java_course));
        courses.add(new Internship("Android Development", "Develop Android apps from scratch", R.drawable.flutter));
        courses.add(new Internship("Machine Learning", "Understand the basics of ML", R.drawable.ml_course));
        courses.add(new Internship("Web Development", "Build responsive websites", R.drawable.web_development));
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
