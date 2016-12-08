package usuario.app.temaula.activitys;

import android.content.Intent;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Calendar;
import java.util.GregorianCalendar;

import usuario.app.temaula.R;
import usuario.app.temaula.adapter.ViewPagerAdapter;
import usuario.app.temaula.dialogs.DialogAdicionarHorarios;

public class Activity_Horarios extends AppCompatActivity {

    private Calendar calendar;
    private ViewPagerAdapter mSectionsPagerAdapter;
    public static String tabTitle;
    private TabLayout tabLayout;
    private static int pageAtual;
    private final int DOMINGO=1, SEGUNDA=2, TERCA=3,
            QUARTA=4, QUINTA=5, SEXTA=6, SABADO=7;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private Intent intent;

    public void TabSelected(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios_tabs);
        intent = new Intent(this,DialogAdicionarHorarios.class);

        if(savedInstanceState != null){
            int tab = savedInstanceState.getInt("tab");
            mViewPager.setCurrentItem(tab);
            PlaceholderFragment.newInstance(tab);
        }

        calendar = new GregorianCalendar();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        Toolbar toolbar = (Toolbar) findViewById(R.id.layout_toolbar);

        toolbar.setTitle("Horários");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),Activity_Horarios.this));
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if(PlaceholderFragment.actionMode!=null){
                    PlaceholderFragment.actionMode.finish();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        switch (dayOfWeek){
            case SEGUNDA:
                mViewPager.setCurrentItem(0);
                break;
            case TERCA:
                mViewPager.setCurrentItem(1);
                break;
            case QUARTA:
                mViewPager.setCurrentItem(2);
                break;
            case QUINTA:
                mViewPager.setCurrentItem(3);
                break;
            case SEXTA:
                mViewPager.setCurrentItem(4);
                break;
            case SABADO:
                mViewPager.setCurrentItem(5);
                PlaceholderFragment.newInstance(mViewPager.getCurrentItem());
                break;
            case DOMINGO:
                mViewPager.setCurrentItem(0);
                break;
        }

        //TitlePageIndicator titlepage = (TitlePageIndicator) findViewById(R.id.titles);

      FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
      fab.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Bundle bundle = new Bundle();
              bundle.putInt("position",mViewPager.getCurrentItem());
              intent.putExtras(bundle);
              startActivity(intent);
          }
      });

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("tab",mViewPager.getCurrentItem());
        String titulo = String.valueOf(mViewPager.getAdapter().getPageTitle(mViewPager.getCurrentItem()));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_teste_tabs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
