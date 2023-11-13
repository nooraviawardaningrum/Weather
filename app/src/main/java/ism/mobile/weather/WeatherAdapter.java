package ism.mobile.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ism.mobile.weather.helper.WeatherCode;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.VHWeather> {
    private List<String> dates;
    private List<Integer> weatherCodes;
    private int size;
    Context context;
    private WeatherCode weatherCode;

    //menginisialisasi variabel anggota dengan nilai yang diterima
    public WeatherAdapter(List<String> dates, List<Integer> weatherCodes, int size, Context context) {
        this.dates = dates;
        this.weatherCodes = weatherCodes;
        this.context = context;
        this.size = size;
        this.weatherCode = new WeatherCode(); // Inisialisasi objek WeatherCode
    }

    @NonNull
    @Override
    //dipanggil ketika recyclerview membutuhkan pembuat tata letak baru
    public VHWeather onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //menggunakan layout inflater untuk menginisialisasi tata letak dari item dalam recylerview
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_item, parent, false);

        return new VHWeather(view);
    }

    @Override
    //dipanggil untuk menetapkan data ke dalam tamplan view di dalam viewholder
    public void onBindViewHolder(@NonNull VHWeather holder, int position) {
        holder.tvDailyWeather.setText(weatherCode.getKondisi(weatherCodes.get(position)));
        holder.tvDailyDate.setText(dates.get(position));
        holder.ivDailyWeather.setImageResource(weatherCode.getCodeIcon(weatherCodes.get(position)));
    }

    @Override
    //mengembalikan jumlah item yang akan ditampilkan di recyclerview, yaitu nilai dari variabel 'size'
    public int getItemCount() {

        return size;
    }

    //menyimpan referensi ke tampilan dalam setiap item recyclerview
    class VHWeather extends RecyclerView.ViewHolder {
        private TextView tvDailyWeather, tvDailyDate;
        private ImageView ivDailyWeather;

        //menerima parameter berupa view, menginisialisasi variabel anggota dengan mengambil
        //referensi ke tampilan yang sesuai
        public VHWeather(@NonNull View itemView) {
            super(itemView);

            tvDailyWeather = itemView.findViewById(R.id.tvKondisi);
            tvDailyDate = itemView.findViewById(R.id.tvTanggal);
            ivDailyWeather = itemView.findViewById(R.id.ivDailyWeather);
        }
    }
}
