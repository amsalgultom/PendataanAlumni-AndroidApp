package pnj.remedial.bangkitamsal.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import pnj.remedial.bangkitamsal.DetailBeritaActivity;
import pnj.remedial.bangkitamsal.R;
import pnj.remedial.bangkitamsal.adapter.AdapterBerita;
import pnj.remedial.bangkitamsal.model.BeritaModel;

public class BeritaFragment extends Fragment {
    ListView listView;
    AdapterBerita adapterBerita;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment_berita, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.listView);
        adapterBerita = new AdapterBerita(getActivity(),R.layout.item_list_berita);
        listView.setAdapter(adapterBerita);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                BeritaModel datas = (BeritaModel) parent.getAdapter().getItem(position);

                Intent intent = new Intent(getActivity(), DetailBeritaActivity.class);
                intent.putExtra("judul", datas.getTitle());
                intent.putExtra("deskripsi", datas.getDeskripsi());
                intent.putExtra("image", datas.getImage());

                startActivity(intent);

            }
        });

        buatDataBerita();
    }

    void buatDataBerita() {
        String[] judul = {
                "Pasien Corona RI yang Sembuh Tembus 10 Ribu",
                "Gojek Mau PHK Karyawan",
                "Sebar Data Pribadi di Medsos Demi Giveaway",
                "Pro-Kontra Olahraga Pakai Masker"
        };

        String[] deskripsi = {
                "Jakarta - Jumlah pasien virus Corona COVID-19 yang sembuh di Indonesia bertambah 591 menjadi 10.498. Ini adalah rekor penambahan jumlah pasien sembuh terbanyak sejauh ini.\n" +
                        "Rekor sebelumnya dicatatkan pada jumat (5/6/2020) dengan penambahan sebanyak 551 kasus, dan Sabtu (30/5/2020) sebanyak 523 kasus.",
                "Jakarta - Perusahaan ride-hailing raksasa Asia Tenggara, Gojek berencana melakukan pemutusan hubungan kerja (PHK) karyawan. Rencana itu akan diumumkan minggu ini.\n" +
                        "\n" +
                        "Demikian dilaporkan Reuters, Selasa (23/6/2020), berdasarkan sumber yang tidak mau disebutkan namanya. Belum diketahui berapa jumlah karyawan yang mau di PHK.",
                "Jakarta - Akhir pekan ini Twitter diramaikan dengan seorang pengguna yang mengadakan giveaway saldo dompet digital OVO. Tapi untuk mengikuti giveaway tersebut salah satu syarat yang harus dipenuhi adalah dengan memberikan nama ibu dan foto.\n" +
                        "Cuitan dari pengguna dengan username @xbege_ ini langsung mendapat respons beragam dari netizen. Ada yang tulus mengikuti giveaway tapi ada juga yang mengingatkan untuk tidak membagikan data pribadi seperti nama ibu kandung di media sosial.",
                "Jakarta - Perdebatan soal olahraga pakai masker masih memanas di tengah pandemi virus Corona COVID-19. Walau bisa mengurangi risiko penularan virus Corona COVID-19 di tempat umum, pakai masker dinilai bikin tidak nyaman saat berolahraga.\n" +
                        "Artis yang juga pegiat olahraga lari, Melanie Putria, memilih untuk tidak menggunakan masker saat sedang menjalankan program latihan. Alasannya, saat melakukan aktivitas fisik dengan intensitas tinggi, kebutuhan oksigen meningkat dan masker akan menghalangi pertukaran udara.\n" +
                        "\n" +
                        "Melanie memberi catatan pada kondisi tertentu misalnya punya penyakit jantung bawaan ataupun masalah pernapasan. Berbagai kondisi tersebut, faktanya tidak selalu disadari oleh seseorang. Apalagi, yang baru mulai suka olahraha.\n" +
                        "\n"
        };

        String[] image = {
                "https://akcdn.detik.net.id/community/media/visual/2020/04/15/c096e353-b7e3-4dbf-ae86-532fef480115_169.jpeg?w=700&q=90",
                "https://akcdn.detik.net.id/community/media/visual/2019/07/22/9e80bdca-e9cd-4ce0-a03f-94059f08a0c1_169.jpeg?w=700&q=80",
                "https://akcdn.detik.net.id/community/media/visual/2016/03/10/413c7b46-124c-45c8-90bf-6ae8cd314614_43.jpg?w=700&q=90",
                "https://akcdn.detik.net.id/community/media/visual/2020/06/07/lari-vs-gowes-pakai-masker_169.jpeg?w=700&q=90"

        };

        ArrayList<BeritaModel> data = new ArrayList<>();

        for (int i=0; i < image.length; i++) {
            BeritaModel model = new BeritaModel();
            model.setImage(image[i]);
            model.setTitle(judul[i]);
            model.setDeskripsi(deskripsi[i]);
            data.add(model);
        }

        adapterBerita.addAll(data);
        adapterBerita.notifyDataSetChanged();

    }
}

