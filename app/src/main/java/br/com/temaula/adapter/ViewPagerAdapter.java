package usuario.app.temaula.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import usuario.app.temaula.activitys.PlaceholderFragment;

/**
 * Created by Dennis Viana on 11/07/2016.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public static String[] getSemana() {
        return ViewPagerAdapter.semana;
    }

    public void setSemana(String[] semana) {
        this.semana = semana;
    }

    public static String[] semana = new String[]{"SEG","TER","QUA","QUI","SEX","SÁB"};
    private Context context;

    public ViewPagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context = context;

    }

    @Override
    public Fragment getItem(int position) {

        return PlaceholderFragment.newInstance(position);
    }

    @Override
    public int getCount() {

        return semana.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return semana[position];
    }
}
