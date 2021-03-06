package m1isi.apptest;

import android.content.Context;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Accueil extends AppCompatActivity {

    private static ListView listTache;
    private static ListView listProjet;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    private static AccueilDB accueilDB;
    private int id_identifiant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        initialize();
    }

    private void initialize() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        id_identifiant = getIntent().getExtras().getInt("id_identifiant");
        accueilDB = new AccueilDB(Accueil.this, getIntent().getExtras().getInt("id_identifiant"));
        accueilDB.eGetProjet();

    }


    public void setListItemProjet(List<ItemProjet> myList){
        listProjet = (ListView) findViewById(R.id.listProjet);
        ProjetAdapter adapter = new ProjetAdapter(this, myList);
        listProjet.setAdapter(adapter);
        listProjet.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Accueil.this, ProjetAccueil.class);
                intent.putExtra("id_projet", ((int) id));
                intent.putExtra("id_identifiant", id_identifiant);
                startActivity(intent);
            }
        });
    }


    public void setListItemTache(List<ItemTache> myList){
        listTache = (ListView) findViewById(R.id.listTache);
        TacheAdapter adapter = new TacheAdapter(this, myList);
        listTache.setAdapter(adapter);
        listTache.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Accueil.this, AccueilTache.class);
                intent.putExtra("idTache", id);
                startActivity(intent);
            }
        });
    }


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
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Projets";
                case 1:
                    return "Taches";
            }
            return null;
        }
    }

    public static class ProjetFragment extends Fragment{

        private FloatingActionButton fabNewProjet;
        private Intent projetCreate;
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
            projetCreate = new Intent(this.getContext(), ProjetCreate.class);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            View view = inflater.inflate(R.layout.fragment_projet, container, false);

            TextView textView = (TextView) view.findViewById(R.id.section_label);
            textView.setText("Projets");

            fabNewProjet = (FloatingActionButton) view.findViewById(R.id.fabNewProjet);
            fabNewProjet.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    projetCreate.putExtra("id_identifiant",getActivity().getIntent().getExtras().getString("id_identifiant"));
                    startActivity(projetCreate);
                    getActivity().finish();
                }
            });
            return view;
        }
    }

    public static class TacheFragment extends Fragment{


        private Intent tacheCreate;
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
            tacheCreate = new Intent(this.getContext(), TacheCreate.class);

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            View view = inflater.inflate(R.layout.fragment_tache, container, false);
            TextView textView = (TextView) view.findViewById(R.id.section_label);
            textView.setText("Tâches");
            return view;
        }
    }

    public static class TacheAdapter extends BaseAdapter {

        private List<ItemTache> myList;
        private Context myContext;
        private LayoutInflater myLayout;


        public TacheAdapter(Context context, List<ItemTache> list){
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
            return myList.get(position).id_tache;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout layoutItem;
            if (convertView == null) {
                layoutItem = (LinearLayout) myLayout.inflate(R.layout.list_item_tache, parent, false);
            } else {
                layoutItem = (LinearLayout) convertView;
            }
            TextView txt_titre = (TextView)layoutItem.findViewById(R.id.txt_tac_titre);
            TextView txt_desc = (TextView)layoutItem.findViewById(R.id.txt_tac_desc);
            txt_titre.setText(myList.get(position).tac_titre);
            txt_desc.setText(myList.get(position).tac_desc);
            return layoutItem;
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
