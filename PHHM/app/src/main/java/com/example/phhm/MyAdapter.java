package com.example.phhm;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private List<Upload> uploadList;
    private OnItemClickListener listener;

    public MyAdapter(Context context, List<Upload> uploadList) {
        this.context = context;
        this.uploadList = uploadList;
    }

    @NonNull
    @Override

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_layout, viewGroup, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Upload upload = uploadList.get(i);
        myViewHolder.textView.setText(upload.getImageName());
        Picasso.get().load(upload.getImageUrl()).placeholder(R.mipmap.ic_launcher_round).fit().centerCrop().into(myViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        int n = 0;
        return uploadList == null ? 0 : uploadList.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        TextView textView;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.cardTextViewId);
            imageView = itemView.findViewById(R.id.cardImageViewId);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener!=null)
            {
                int position=getAdapterPosition() ;
                if(position!=RecyclerView.NO_POSITION)
                {
                    listener.onItemClick(position);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo contextMenuInfo)
        {
            menu.setHeaderTitle("Choose an action");
            MenuItem doAnyTask =menu.add(Menu.NONE,1,1," do any task ");
            MenuItem delete =menu.add(Menu.NONE,2,2," delete ");
            doAnyTask.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {

            if (listener!=null)
            {
                int position=getAdapterPosition() ;
                if(position!=RecyclerView.NO_POSITION)
                {
                    switch (item.getItemId())
                    {
                        case 1:
                            listener.onDoAnyTask(position);
                            return true;

                        case 2:
                            listener.onDelete(position);
                            return true;

                    }
                }
            }

            return false;
        }
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
        void onDoAnyTask(int position);
        void onDelete(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


}


