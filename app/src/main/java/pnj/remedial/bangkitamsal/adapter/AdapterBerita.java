package pnj.remedial.bangkitamsal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import pnj.remedial.bangkitamsal.R;
import pnj.remedial.bangkitamsal.model.BeritaModel;

public class AdapterBerita extends ArrayAdapter<BeritaModel> {
    Context context;
    int resource;

    public AdapterBerita(@NonNull Context context, int resource) {
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
            convertView = LayoutInflater.from(context).inflate(resource,parent, false);

            holder.imgBerita = convertView.findViewById(R.id.imgBerita);
            holder.txtJudul = convertView.findViewById(R.id.txtJudul);
            holder.txtDeskripsi = convertView.findViewById(R.id.txtDeskripsi);
            convertView.setTag(holder);

        }else{
            holder = (Holder) convertView.getTag();
        }

        holder.txtJudul.setText(getItem(position).getTitle());
        holder.txtDeskripsi.setText(getItem(position).getDeskripsi());
        Picasso.get().load(getItem(position).getImage()).into(holder.imgBerita);

        return convertView;
    }

    class Holder {
        ImageView imgBerita;
        TextView txtJudul, txtDeskripsi;
    }
}

