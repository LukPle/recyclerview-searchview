package com.example.searchview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * The Adapter acts like a bridge between the ArrayList and the RecyclerView.
 * It gets data from an Array and converts it to views which can be later placed in the RecyclerView.
 * A copy of the normal ArrayList is needed because the filtering deletes items from this list.
 *
 * Layout File for one view: list_item.xml
 */
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements Filterable {

    private ArrayList<Item> itemList;
    private ArrayList<Item> itemListFull;

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView item;
        private TextView quantity;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            item = itemView.findViewById(R.id.text_item);
            quantity = itemView.findViewById(R.id.text_quantity);
        }
    }

    public Adapter(ArrayList<Item> itemList) {

        // This is where the full list is being filled with data
        this.itemList = itemList;
        itemListFull = new ArrayList<>(itemList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Item currentItem = itemList.get(position);
        holder.item.setText(currentItem.getItem());
        holder.quantity.setText("Quantity: " +currentItem.getQuantity());
    }

    @Override
    public int getItemCount() {

        return itemList.size();
    }

    /**
     * This method returns the Filter to the MainActivity.
     * @return itemFilter.
     */
    @Override
    public Filter getFilter() {

        return itemFilter;
    }

    /**
     * A new Filter gets created and assigned.
     */
    private Filter itemFilter = new Filter() {

        /**
         * Background filtering of the list.
         * The user makes a input in the search field.
         * All items that contain this input get returned as a result.
         * @param charSequence is the user input of the search.
         * @return results which contain the anticipated data with the filtering.
         */
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            // This new list is the filtered list that gets all items which are in line with the charSequence
            ArrayList<Item> filteredList = new ArrayList<>();

            // The filtered list contains all list items if there is not any user input
            if(charSequence == null || charSequence.length() == 0) {

                filteredList.addAll(itemListFull);
            }
            // If the user makes input, every item that includes this this input becomes part of the filtered list
            else {

                String filterPattern = charSequence.toString().toLowerCase().trim();
                for(Item item : itemListFull) {

                    if(item.getItem().toLowerCase().contains(filterPattern)) {

                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        /**
         * Automatic Publishing to the UI Thread.
         * The RecyclerView just displays the views filled by data from the ArrayList with Items.
         * This publishing method adjusts the ArrayList with the result from the filtering process.
         * @param charSequence is the user input of the search.
         * @param filterResults is the result of the filtering process.
         */
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            itemList.clear();
            itemList.addAll((ArrayList) filterResults.values);
            notifyDataSetChanged();
        }
    };
}
