package nr.nms.fusionapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CheckBoxView {
    public static MyDatabase myDatabase;
    private RecyclerView recyclerView;
    private SearchView et_search;
    private TodoAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_items);
        et_search = findViewById(R.id.et_search);

        myDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "infodb").allowMainThreadQueries().build();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        et_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //  getDealsFromDb(query);
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);

                return false;
            }
        });
        getData();
    }

    private void getData() {
        class GetData extends AsyncTask<Void, Void, List<ItemsList>> {

            @Override
            protected List<ItemsList> doInBackground(Void... voids) {
                List<ItemsList> myDataLists = myDatabase.myDao().getItemsData();
                return myDataLists;

            }

            @Override
            protected void onPostExecute(List<ItemsList> myDataList) {
                adapter = new TodoAdapter(myDataList,MainActivity.this,MainActivity.this);
                recyclerView.setAdapter(adapter);
                super.onPostExecute(myDataList);
            }
        }
        GetData gd = new GetData();
        gd.execute();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Intent i = new Intent(this, AddItemsActivity.class);
                this.startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onChecked(int id, boolean value) {
        myDatabase.myDao().update(id,value);
        getData();
    }

    @Override
    public void onUncheckded(int id, boolean value) {

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
};

