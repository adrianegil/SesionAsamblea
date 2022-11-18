package cu.desoft.sesionasamblea.ui.notepad


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cu.desoft.sesionasamblea.R
import cu.desoft.sesionasamblea.logic.Assistance



class AssistanceAdapter(private val mList: List<Assistance>) : RecyclerView.Adapter<AssistanceAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_assistance, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.name.setText(ItemsViewModel.name);

        // sets the text to the textview from our itemHolder class
        holder.number.setText(ItemsViewModel.number);

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val name: TextView = itemView.findViewById(R.id.text_name)
        val number: TextView = itemView.findViewById(R.id.text_number)
    }
}