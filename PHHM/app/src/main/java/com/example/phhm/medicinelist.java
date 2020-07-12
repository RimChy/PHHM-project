package com.example.phhm;





import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class medicinelist extends ArrayAdapter<Medicine>{

    private Activity context;
    private List<Medicine>medicineList1;
    public medicinelist(Activity context,List<Medicine>medicineList1){
        super(context,R.layout.list_layout,medicineList1);
        this.context=context;
        this.medicineList1=medicineList1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);
        TextView textViewtype=(TextView) listViewItem.findViewById(R.id.textViewtype);
        TextView textViewtime=(TextView) listViewItem.findViewById(R.id.textViewtime);

        Medicine medicine=medicineList1.get(position);
        textViewtype.setText(medicine.getId());
        textViewtime.setText(medicine.getTime());
        return listViewItem;
    }
}
