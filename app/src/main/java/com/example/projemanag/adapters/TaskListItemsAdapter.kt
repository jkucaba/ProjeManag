package com.example.projemanag.adapters

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.projemanag.R
import com.example.projemanag.models.Task

open class TaskListItemsAdapter(
    private val context: Context,
    private var list: ArrayList<Task>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /**
     * Inflates the item views which is designed in xml layout file
     *
     * create a new
     * {@link ViewHolder} and initializes some private fields to be used by RecyclerView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false)
        // Here the layout params are converted dynamically according to the screen size as width is 70% and height is wrap_content.
        val layoutParams = LinearLayout.LayoutParams(
            (parent.width * 0.7).toInt(),
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        // Here the dynamic margins are applied to the view.
        layoutParams.setMargins((15.toDp()).toPx(), 0, (40.toDp()).toPx(), 0)
        view.layoutParams = layoutParams

        return MyViewHolder(view)
    }

    /**
     * Binds each item in the ArrayList to a view
     *
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]

        if (holder is MyViewHolder) {

            if (position == list.size - 1) {
                holder.itemView.findViewById<TextView>(R.id.tv_add_task_list).visibility = View.VISIBLE
                holder.itemView.findViewById<LinearLayout>(R.id.ll_task_item).visibility = View.GONE
            } else {
                holder.itemView.findViewById<TextView>(R.id.tv_add_task_list).visibility = View.GONE
                holder.itemView.findViewById<LinearLayout>(R.id.ll_task_item).visibility = View.VISIBLE
            }

            holder.itemView.findViewById<TextView>(R.id.tv_task_list_title).text = model.title
            holder.itemView.findViewById<TextView>(R.id.tv_add_task_list).setOnClickListener{
                holder.itemView.findViewById<TextView>(R.id.tv_add_task_list).visibility = View.GONE
                holder.itemView.findViewById<CardView>(R.id.cv_add_task_list_name).visibility = View.VISIBLE
            }

            holder.itemView.findViewById<ImageButton>(R.id.ib_close_list_name).setOnClickListener{
                holder.itemView.findViewById<TextView>(R.id.tv_add_task_list).visibility = View.VISIBLE
                holder.itemView.findViewById<CardView>(R.id.cv_add_task_list_name).visibility = View.GONE
            }
            holder.itemView.findViewById<ImageButton>(R.id.ib_done_list_name).setOnClickListener{
                //TODO create entry in DB
            }
        }
    }

    /**
     * Gets the number of items in the list
     */
    override fun getItemCount(): Int {
        return list.size
    }

    /**
     * A function to get density pixel from pixel
     */
    private fun Int.toDp(): Int =
        (this / Resources.getSystem().displayMetrics.density).toInt()

    /**
     * A function to get pixel from density pixel
     */
    private fun Int.toPx(): Int =
        (this * Resources.getSystem().displayMetrics.density).toInt()

    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     */
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
}