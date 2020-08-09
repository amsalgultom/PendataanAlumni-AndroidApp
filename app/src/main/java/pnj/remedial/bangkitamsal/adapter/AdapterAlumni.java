package pnj.remedial.bangkitamsal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import pnj.remedial.bangkitamsal.R;
import pnj.remedial.bangkitamsal.model.AlumniModel;

public class AdapterAlumni extends ArrayAdapter<AlumniModel> {

    Context context;
    int resource;

    public AdapterAlumni(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Holder holder;
        if(convertView==null){
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
            holder.txtNim = convertView.findViewById(R.id.txtNim);
            holder.txtNamaAlumni = convertView.findViewById(R.id.txtNamaAlumni);
            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }
        holder.txtNim.setText("Nim : "+ getItem(position).getNim());
        holder.txtNamaAlumni.setText("Nama Alumni : "+ getItem(position).getNama_alumni());

        return convertView;
    }
    class Holder {
        TextView txtNim, txtNamaAlumni;
    }
}
