package ethan.demo.myfirstapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class EthanRecycleAdapter extends RecyclerView.Adapter<EthanRecycleAdapter.ViewHolder> {
    private ArrayList<String> sampleData;
    public EthanRecycleAdapter(ArrayList<String> array){
        sampleData = array;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rowView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem,viewGroup,false);
        return new ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
    viewHolder.textViewSample.setText(sampleData.get(i));
    }

    @Override
    public int getItemCount() {
        return sampleData.size();
    }
    public void removeData(int position){
        sampleData.remove(position);
        notifyItemRemoved(position);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewSample;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewSample = (TextView) itemView.findViewById(R.id.text_view);
        }
    }
}

