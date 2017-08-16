package panekpawel.pl.samplespaceitemdecoration.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.holder_ad.view.*
import kotlinx.android.synthetic.main.holder_header.view.*
import kotlinx.android.synthetic.main.holder_task.view.*
import panekpawel.pl.samplespaceitemdecoration.R
import panekpawel.pl.samplespaceitemdecoration.models.Ad
import panekpawel.pl.samplespaceitemdecoration.models.Header
import panekpawel.pl.samplespaceitemdecoration.models.RecyclerAdapterData
import panekpawel.pl.samplespaceitemdecoration.models.Task
import panekpawel.pl.samplespaceitemdecoration.utils.AD_VIEW_TYPE
import panekpawel.pl.samplespaceitemdecoration.utils.COMPLEX_TASK_VIEW_TYPE
import panekpawel.pl.samplespaceitemdecoration.utils.HEADER_VIEW_TYPE
import panekpawel.pl.samplespaceitemdecoration.utils.SIMPLE_TASK_VIEW_TYPE


class RecyclerAdapter(val data: ArrayList<RecyclerAdapterData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        when (viewType) {
            HEADER_VIEW_TYPE -> {
                val headerView = inflater.inflate(R.layout.holder_header, parent, false)
                return HeaderHolder(headerView)
            }
            AD_VIEW_TYPE -> {
                val routeView = inflater.inflate(R.layout.holder_ad, parent, false)
                return AdHolder(routeView)
            }
            SIMPLE_TASK_VIEW_TYPE, COMPLEX_TASK_VIEW_TYPE-> {
                val busStopView = inflater.inflate(R.layout.holder_task, parent, false)
                return TaskHolder(busStopView)
            }
            else -> throw IllegalArgumentException("viewType returned unexpected type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        when (getItemViewType(position)) {
            HEADER_VIEW_TYPE -> {
                val headerHolder = holder as HeaderHolder
                headerHolder.bindView(position)
            }
            AD_VIEW_TYPE -> {
                val adHolder = holder as AdHolder
                adHolder.bindView(position)
            }
            SIMPLE_TASK_VIEW_TYPE, COMPLEX_TASK_VIEW_TYPE-> {
                val taskHolder = holder as TaskHolder
                taskHolder.bindView(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].viewType
    }

    inner class HeaderHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        fun bindView(position: Int) {
            val header = data[position].model as Header
            itemView.headerTitle.text = header.title
        }
    }

    inner class AdHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        fun bindView(position: Int) {
            val ad = data[position].model as Ad
            itemView.adTitle.text = ad.title
            itemView.adDescription.text = ad.description
            //TODO add Glide to bind image
        }
    }

    inner class TaskHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        fun bindView(position: Int) {
            val task = data[position].model as Task
            itemView.taskTitle.text = task.title
            if (task.description != null){
                itemView.taskDescription.text = task.description
            }
        }
    }
}