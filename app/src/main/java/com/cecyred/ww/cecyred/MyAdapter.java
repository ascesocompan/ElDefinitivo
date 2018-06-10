package com.cecyred.ww.cecyred;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Collections;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {



    private Context context;
    private LayoutInflater inflater;
    List<listitem> data= Collections.emptyList();
    listitem current;
    int currentPos=0;

    public MyAdapter(Context context, List<listitem> data) {
        this.context=context;
        inflater=LayoutInflater.from(context);
        this.data=data;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= inflater.inflate(R.layout.list_item,parent,false);
        ViewHolder holder=new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViewHolder myholder=(ViewHolder) holder;
        listitem current=data.get(position);
        myholder.textViewHead.setText(current.name);
        myholder.textViewDesc.setText(current.desc);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewHead;
        private TextView textViewDesc;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewHead=itemView.findViewById(R.id.textviewHead);
            textViewDesc=itemView.findViewById(R.id.textviewDescripcion);
        }
    }
}
