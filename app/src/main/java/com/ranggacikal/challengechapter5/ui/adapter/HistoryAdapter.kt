package com.ranggacikal.challengechapter5.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ranggacikal.challengechapter5.R
import com.ranggacikal.challengechapter5.db.History

class HistoryAdapter(val history: List<History>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {


    class HistoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val txtWaktu: TextView = itemView.findViewById(R.id.tv_item_time)
        val txtNama: TextView = itemView.findViewById(R.id.tv_item_nama)
        val txtHasil: TextView = itemView.findViewById(R.id.tv_item_hasil)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val itemHistory = history[position]
        holder.txtWaktu.text = itemHistory.date
        holder.txtNama.text = itemHistory.namePlayer
        holder.txtHasil.text = itemHistory.resultMatch
        Log.d("cekAdapter", "onBindViewHolder: $history")
    }

    override fun getItemCount(): Int {
        return history.size
    }
}