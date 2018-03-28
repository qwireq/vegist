package org.greenpad.vegist.anouncements;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenpad.vegist.R;
import org.json.JSONArray;
import org.json.JSONObject;


public class MyAnounceRecyclerViewAdapter extends RecyclerView.Adapter<MyAnounceRecyclerViewAdapter.ViewHolder> {

    private final JSONArray mValues;
    private final AnounceFragment.OnListFragmentInteractionListener mListener;

    public MyAnounceRecyclerViewAdapter(JSONArray items, AnounceFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_anouncement, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        try{
            holder.mItem = mValues.getJSONObject(position);
            holder.mIdView.setText(mValues.getJSONObject(position).getString("date"));
            holder.mContentView.setText(mValues.getJSONObject(position).getString("title"));

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        // Notify the active callbacks interface (the activity, if the
                        // fragment is attached to one) that an item has been selected.
                        mListener.onListFragmentInteraction(holder.mItem);
                    }
                }
            });
        }catch (Exception e){
            Log.e("MyANON", e.toString());
        }

    }

    @Override
    public int getItemCount() {
        return mValues.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public JSONObject mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
