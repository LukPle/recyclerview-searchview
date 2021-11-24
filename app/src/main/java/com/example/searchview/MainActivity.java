package com.example.searchview;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * The MainActivity creates a Shopping List using a ArrayList with a RecyclerView.
 * The RecyclerView in combination with an Adapter is the recommended way for creating lists in Android.
 * It is possible to search for specific items of the shopping list by using a search field.
 *
 * The documentation focuses on the SearchView with its filter functionality.
 * For Javadoc information about the RecyclerView and Adapter check out the specific project.
 *
 * Layout File for the activity: activity_main.xml
 * Layout File for the menu: menu_search.xml
 *
 * @author Lukas Plenk
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter adapter;
    private ArrayList<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemList = new ArrayList<>();
        fillItemList();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new Adapter(itemList);
        recyclerView.setAdapter(adapter);
    }

    private void fillItemList() {

        itemList.add(new Item("Noodles", "1"));
        itemList.add(new Item("Cheese", "1"));
        itemList.add(new Item("Pepper", "2"));
        itemList.add(new Item("Onions", "4"));
        itemList.add(new Item("Carrots", "2"));
        itemList.add(new Item("Tomato", "1"));
        itemList.add(new Item("Fish", "4"));
        itemList.add(new Item("Grill Sauce", "1"));
        itemList.add(new Item("Baguette", "1"));
        itemList.add(new Item("Mushrooms", "6"));
        itemList.add(new Item("Cooking Oil", "1"));
    }

    /**
     * The onCreateOptionsMenu method handles every step for the creation of the menu.
     * The Layout for the menu gets assigned by a MenuInflater.
     * The MenuItem searchItem gets created and assigned with the correct part in the UI.
     * A SearchView is being created with the information from the searchItem.
     * And the Filter is being called from the Adapter.
     * @param menu is an predefined interface for the menu.
     * @return true for finishing the setup process of the menu.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                // The Filter that handles the search gets called from the Adapter
                adapter.getFilter().filter(s);
                return false;
            }
        });

        return true;
    }
}