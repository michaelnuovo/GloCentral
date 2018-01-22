package cas.michael.glocentral.ServerActivity;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cas.michael.glocentral.BleDevices.GattAttribute.Inheritance.GattAttribute;
import cas.michael.glocentral.MainActivity.*;
import cas.michael.glocentral.R;

public class AttributeAdapter extends RecyclerView.Adapter<AttributeAdapter.ViewHolder> {

    private static final String TAG = LeDeviceAdapter.class.getSimpleName();

    private List<GattAttribute> mAttributes;
    private MVP.Presenter mPresenter;

    public void resetData(List<GattAttribute> mData){
        mAttributes = mData;
        notifyDataSetChanged();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        MVP.Presenter mPresenter;

        @BindView(R.id.tv_attribute_type) TextView mTvAttType;
        @BindView(R.id.tv_attribute_uuid) TextView mTvAttUuid;

        // each data item is just a string in this case
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

        @Override
        public void onClick(View view){
            Log.i(TAG,"onClick");
            switch (view.getId()){

            }
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AttributeAdapter(List<GattAttribute> myDataset,
                            Presenter presenter) {
        mAttributes = myDataset;
        mPresenter = presenter;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AttributeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.server_activity_recycler_view_list_item, parent,
                        false);

        AttributeAdapter.ViewHolder vh = new AttributeAdapter.ViewHolder(v);
        vh.mPresenter = mPresenter;

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Log.i(TAG,"onBindViewHolder()");

        GattAttribute att = mAttributes.get(position);
        holder.mTvAttType.setText(att.getType());
        holder.mTvAttUuid.setText(att.getUuid());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mAttributes.size();
    }
}
