package org.myapplication.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.myapplication.R;
import org.myapplication.models.Transaction;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    private List<Transaction> items;
    private OnItemTransactionListener listener;

    public TransactionAdapter(List<Transaction> items, OnItemTransactionListener listener) {
        this.items = items;
        this.listener = listener;
    }

    public interface OnItemTransactionListener{
        void onTransactionClicked(int index, Transaction item);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_transaction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaction item = items.get(position);
        holder.bind(position, item);
    }

    @Override
    public int getItemCount() {
        return (items != null) ? items.size() : 0;
    }

    public void clear() {
        int size = items.size();
        items.clear();
        notifyItemRangeRemoved(0, size);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView descriptionText;
        TextView amountText;

        public void bind(final int index, final Transaction item) {
            descriptionText.setText(item.getDescription());
//            Locale localeID = new Locale("in", "ID");
//            NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
//            amountText.setText(formatRupiah.format(item.getAmount()));
            amountText.setText(String.valueOf(item.getAmount()));
            // TODO: Tambahkan interaksi click di sini
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onTransactionClicked(index, item);
                }
            });

            String trans = amountText.getText().toString();

            //TODO : Change it to the type in recyclerview---------------------
            //tiap tambahin dari Transaction tambahin juga di sini .
            if (item.getType() == Transaction.Type.GUN){
                amountText.setTextColor(Color.parseColor("#00ff1a"));
                amountText.setText("qty Gun \t: " + trans);
            } else if (item.getType() == Transaction.Type.HEAL){
                amountText.setTextColor(Color.parseColor("#00ff1a"));
                amountText.setText("qty Heal \t: " + trans);
            } else if(item.getType() == Transaction.Type.DAGING){
                amountText.setTextColor(Color.parseColor("#00ff1a"));
                amountText.setText("qty Daging \t: " + trans);
            } else if(item.getType() == Transaction.Type.SWORD){
                amountText.setTextColor(Color.parseColor("#00ff1a"));
                amountText.setText("qty Sword \t: " + trans);
            } else if(item.getType() == Transaction.Type.CHEST){
                amountText.setTextColor(Color.parseColor("#00ff1a"));
                amountText.setText("qty Chest \t: " + trans);
            }
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            descriptionText = itemView.findViewById(R.id.text_description);
            amountText = itemView.findViewById(R.id.text_amount);
        }
    }
}
