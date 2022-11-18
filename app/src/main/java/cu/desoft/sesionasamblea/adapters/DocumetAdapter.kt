package cu.desoft.sesionasamblea.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cu.desoft.sesionasamblea.data.entity.Document
import cu.desoft.sesionasamblea.databinding.ItemDocumentBinding
import cu.desoft.sesionasamblea.utils.ItemClick

class DocumetAdapter (val listDocument: List<Document>, val itemClick: ItemClick): RecyclerView.Adapter<DocumetAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: ItemDocumentBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDocumentBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(listDocument[position]){
                binding.documentName.text = this.nameDocuments
                binding.noteDocument.setOnClickListener {
                    itemClick.clicked(position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return listDocument.size
    }
}