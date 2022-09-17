package adapter

import `interface`.cardlistener
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.a706012110039.peternakan.R
import com.a706012110039.peternakan.databinding.HewanbannerBinding
import database.globalvar
import model.hewan

class rvadapter(val listhewan: ArrayList<hewan>, val cardlistener: cardlistener):
    RecyclerView.Adapter<rvadapter.ViewHolder>(){
    class ViewHolder(val itemView: View, val cardlistener: cardlistener):
        RecyclerView.ViewHolder(itemView) {

        val binding = HewanbannerBinding.bind(itemView)

        fun setData(data: hewan) {
            binding.Namahewan.text = data.namahewan
            binding.jenishewan.text = data.jenishewan
            binding.usiahewan.text = data.usiahewan.toString()
            if (data.imageuri!!.isNotEmpty()) {
                binding.imageView3.setImageURI(Uri.parse(data.imageuri))
            }
            binding.delete.setOnClickListener {
                cardlistener.onCardClick(false, position)
            }
            binding.edit.setOnClickListener {
                cardlistener.onCardClick(true, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.hewanbanner, parent, false)
        return ViewHolder(view, cardlistener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(listhewan[position])
    }

    override fun getItemCount(): Int {
        return listhewan.size
    }

}