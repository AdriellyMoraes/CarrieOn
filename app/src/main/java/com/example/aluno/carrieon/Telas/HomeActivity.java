package com.example.aluno.carrieon.Telas;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.aluno.carrieon.R;
import com.example.aluno.carrieon.Telas.Fragments.CalendarioFragment;
import com.example.aluno.carrieon.Telas.Fragments.EstatisticasFragment;
import com.example.aluno.carrieon.Telas.Fragments.MaisFragment;
import com.example.aluno.carrieon.Telas.Fragments.RegistroFragment;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private Fragment registroFragment;
    private Fragment calendarioFragment;
    private EstatisticasFragment estatisticasFragment;
    private Fragment maisFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);
        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);

        registroFragment = new RegistroFragment();
        calendarioFragment = new CalendarioFragment();
        estatisticasFragment = new EstatisticasFragment();
        maisFragment = new MaisFragment();

        setFragment(registroFragment);

        if(registroFragment.getContext() == null){

        }

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                
                switch (item.getItemId()){
                    
                    case R.id.nav_registro:
                        setFragment(registroFragment);
                        return true;

                    case R.id.nav_calend:
                        setFragment(calendarioFragment);
                        return true;

                    case R.id.nav_grafico:
                        setFragment(estatisticasFragment);
                        return true;

                    case R.id.nav_mais:
                        setFragment(maisFragment);
                        return true;
                        
                        default:
                            return false;
                }
            }
        });
    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();

    }


}
