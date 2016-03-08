package m1isi.apptest;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Accueil extends AppCompatActivity {

    private static ListView listTache;
    private static ListView listProjet;
    private static ListView listNotification;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        AccueilDB accueilDB = new AccueilDB(Accueil.this);
        accueilDB.eGetProjet();


    }
    public void setListItemProjet(List<ItemProjet> myList){
        listProjet = (ListView) findViewById(R.id.listProjet);
        ProjetAdapter adapter = new ProjetAdapter(this, myList);
        listProjet.setAdapter(adapter);
    }
    //TODO : pour les taches et notifications
    /*public void setListItemTache(List<ItemProjet> myList){
        listTache = (ListView) findViewById(R.id.listTache);
        ProjetAdapter adapter = new ProjetAdapter(this, myList);
        listProjet.setAdapter(adapter);
    }
    public void setListItemNotification(List<ItemProjet> myList){
        listNotification = (ListView) findViewById(R.id.listNotification);
        ProjetAdapter adapter = new ProjetAdapter(this, myList);
        listProjet.setAdapter(adapter);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    //getParent().setTitle("Projets");
                    return ProjetFragment.newInstance(0);
                case 1:
                    //getParent().setTitle("Tâches");
                    return  TacheFragment.newInstance(1);
                case 2:
                    //getParent().setTitle("Notifications");
                    return  NotificationFragment.newInstance(2);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Projets";
                case 1:
                    return "Taches";
                case 2:
                    return "Notifications";
            }
            return null;
        }
    }

    public static class ProjetFragment extends Fragment{

        private static final String ARG_SECTION_NUMBER = "section_number";
        public static ProjetFragment newInstance(int sectionNumber) {
            ProjetFragment fragment = new ProjetFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstancesState){
            super.onCreate(savedInstancesState);
        }




        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            View view = inflater.inflate(R.layout.fragment_projet, container, false);
            TextView textView = (TextView) view.findViewById(R.id.section_label);
            textView.setText("Projets récents");
            return view;
        }
    }

    public static class TacheFragment extends Fragment{

        private static final String ARG_SECTION_NUMBER = "section_number";
        public static TacheFragment newInstance(int sectionNumber) {
            TacheFragment fragment = new TacheFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstancesState){
            super.onCreate(savedInstancesState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            View view = inflater.inflate(R.layout.fragment_tache, container, false);
            TextView textView = (TextView) view.findViewById(R.id.section_label);
            textView.setText("Tâches récentes");
            return view;
        }
    }

    public static class NotificationFragment extends Fragment{

        private static final String ARG_SECTION_NUMBER = "section_number";
        public static NotificationFragment newInstance(int sectionNumber) {
            NotificationFragment fragment = new NotificationFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstancesState){
            super.onCreate(savedInstancesState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            View view = inflater.inflate(R.layout.fragment_notification, container, false);
            TextView textView = (TextView) view.findViewById(R.id.section_label);
            textView.setText("Notifications récentes");

            return view;
        }
    }

    public static class ProjetAdapter extends BaseAdapter {

        private List<ItemProjet> myList;
        private Context myContext;
        private LayoutInflater myLayout;


        public ProjetAdapter(Context context, List<ItemProjet> list){
            myContext = context;
            myList = list;
            myLayout = LayoutInflater.from(myContext);
        }

        @Override
        public int getCount() {
            return myList.size();
        }

        @Override
        public Object getItem(int position) {
            return myList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return myList.get(position).id_projet;//retourne l'id du projet et pas sa position
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout layoutItem;
            if (convertView == null) {
                layoutItem = (LinearLayout) myLayout.inflate(R.layout.list_item_project, parent, false);
            } else {
                layoutItem = (LinearLayout) convertView;
            }
            TextView txt_titre = (TextView)layoutItem.findViewById(R.id.txt_pro_titre);
            TextView txt_desc = (TextView)layoutItem.findViewById(R.id.txt_pro_desc);
            txt_titre.setText(myList.get(position).pro_titre);
            txt_desc.setText(myList.get(position).pro_desc);
            return layoutItem;
        }
    }
}
