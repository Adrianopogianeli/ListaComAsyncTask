package br.com.adriano.listacomasynctask.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.adriano.listacomasynctask.R;
import br.com.adriano.listacomasynctask.listener.OnItemClickListener;
import br.com.adriano.listacomasynctask.model.Android;

/**
 * Created by logonrm on 29/01/2018.
 */

public class AndroidAdapter extends
        RecyclerView.Adapter<AndroidAdapter.AdroidItemViewHolder>{

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Android> androids;
    private OnItemClickListener listener;

    public AndroidAdapter(Context context, List<Android> androids){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public AdroidItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_android,parent, false);
        return new AdroidItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AndroidAdapter.AdroidItemViewHolder holder, int position) {
        Android android = androids.get(position);
        holder.tvApi.setText(android.getApi());
        holder.tvNome.setText(android.getNome());
        holder.tvVersao.setText(android.getVersao());

        Picasso.with(context)
                .load(android.getUrlImagem())
                .error(R.drawable.error)
                .placeholder(R.drawable.loading)
                .into(holder.ivLogo);
    }
    public void setClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
    public Android getItem(int position){
        return androids.get(position);
    }

    @Override
    public int getItemCount() {
        return androids.size();
    }

    protected class AdroidItemViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        private ImageView ivLogo;
        private TextView tvNome;
        private TextView tvApi;
        private TextView tvVersao;


        public AdroidItemViewHolder(View itemView) {
            super(itemView);
            ivLogo = itemView.findViewById(R.id.ivLogo);
            tvNome = itemView.findViewById(R.id.tvNome);
            tvApi = itemView.findViewById(R.id.tvApi);
            tvVersao = itemView.findViewById(R.id.tvVersao);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) listener.onClick(v,
                    getAdapterPosition() );
        }
    }

}
