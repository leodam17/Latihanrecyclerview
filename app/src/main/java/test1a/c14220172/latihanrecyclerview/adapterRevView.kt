package test1a.c14220172.latihanrecyclerview

import android.provider.ContactsContract.RawContacts.Data
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class adapterRevView (private val listwayang:ArrayList<wayang>) : RecyclerView.Adapter<adapterRevView.ListViewHolder> () {

    private lateinit var onItemClickCallback: OnitemClickCallback

    interface OnitemClickCallback {
        fun onItemClicked(data:wayang)

        fun delData(pos: Int)
    }

    fun setOnItemClickCallback(onitemClickCallback: OnitemClickCallback){
        this.onItemClickCallback = onitemClickCallback
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var _namaWayang = itemView.findViewById<TextView>(R.id.namaWayang)
        var _karakterWayang = itemView.findViewById<TextView>(R.id.karakterWayang)
        var _deskripsiWayang = itemView.findViewById<TextView>(R.id.deskrpsiWayang)
        var _gambarWayang = itemView.findViewById<ImageView>(R.id.gambarWayang)
        var _btnHapus = itemView.findViewById<Button>(R.id.btnHapus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listwayang.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var wayang = listwayang[position]

        holder._namaWayang.setText(wayang.nama)
        holder._deskripsiWayang.setText(wayang.deskripsi)
        holder._karakterWayang.setText(wayang.karakter)

        Log.d("TEST", wayang.foto)
        Picasso.get()
            .load(wayang.foto)
            .into(holder._gambarWayang)

        holder._gambarWayang.setOnClickListener {
            onItemClickCallback.onItemClicked(listwayang[position])
        }

        holder._btnHapus.setOnClickListener{
            onItemClickCallback.delData(position)
        }
    }
}