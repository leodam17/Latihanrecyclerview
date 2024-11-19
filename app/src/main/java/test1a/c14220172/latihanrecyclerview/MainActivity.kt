package test1a.c14220172.latihanrecyclerview

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        _rvWayang = findViewById<RecyclerView>(R.id.rvWayang)

        fun SiapkanData(){
            _nama = resources.getStringArray(R.array.namaWayang).toMutableList()
            _deskripsi = resources.getStringArray(R.array.deskripsiWayang).toMutableList()
            _karakter = resources.getStringArray(R.array.karakterUtamaWayang).toMutableList()
            _gambar = resources.getStringArray(R.array.gambarWayang).toMutableList()
        }

        fun TambahData(){
            arwayang.clear()
            for (position in _nama.indices){
                val data = wayang(
                    _gambar[position],
                    _nama[position],
                    _karakter[position],
                    _deskripsi[position]
                )
                arwayang.add(data)
            }
        }

        fun TampilkanData(){
            _rvWayang.layoutManager = LinearLayoutManager(this)

            val adapterWayang = adapterRevView(arwayang)
            _rvWayang.adapter = adapterWayang

            adapterWayang.setOnItemClickCallback(object : adapterRevView.OnitemClickCallback{
                override fun onItemClicked(data: wayang) {
                    val intent = Intent(this@MainActivity,detWayang::class.java)
                    intent.putExtra("kirimData",data)
                    startActivity(intent)
                }

                override fun delData(pos: Int) {
                    AlertDialog.Builder(this@MainActivity)
                        .setTitle("HAPUS DATA")
                        .setMessage("Apakah Benar Data "+ _nama[pos] + " akan dihapus ?")
                        .setPositiveButton(
                            "HAPUS",
                            DialogInterface.OnClickListener{dialog, which ->
                                _gambar.removeAt(pos)
                                _nama.removeAt(pos)
                                _deskripsi.removeAt(pos)
                                _karakter.removeAt(pos)
                                TambahData()
                                TampilkanData()

                            }
                        )
                        .setNegativeButton(
                            "BATAL",
                            DialogInterface.OnClickListener{dialog, which ->
                                Toast.makeText(
                                    this@MainActivity,
                                    "Data Batal Dihapus",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        ).show()
                }
            })

        }

        _rvWayang = findViewById<RecyclerView>(R.id.rvWayang)
        SiapkanData()
        TambahData()
        TampilkanData()
    }
    private lateinit var  _nama : MutableList<String>
    private lateinit var _karakter : MutableList<String>
    private lateinit var _deskripsi : MutableList<String>
    private lateinit var _gambar : MutableList<String>

    private  var arwayang = arrayListOf<wayang>()
    private lateinit var _rvWayang : RecyclerView
}