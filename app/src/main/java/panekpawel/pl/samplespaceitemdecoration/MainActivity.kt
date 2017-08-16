package panekpawel.pl.samplespaceitemdecoration

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import panekpawel.pl.samplespaceitemdecoration.adapter.RecyclerAdapter
import panekpawel.pl.samplespaceitemdecoration.utils.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val colsCount = resources.getInteger(R.integer.colsCount)
        val gridLayoutManager = GridLayoutManager(this, 4)
        recyclerView.layoutManager = gridLayoutManager

        val itemDecoration = SpaceItemDecoration()
        recyclerView.addItemDecoration(itemDecoration)

        val dummyDataCreator = DummyDataCreator()
        dummyDataCreator.createData()
        val data = dummyDataCreator.data

        val adapter = RecyclerAdapter(data)
        recyclerView.adapter = adapter

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                when(adapter.getItemViewType(position)) {
                    HEADER_VIEW_TYPE -> return 4//resources.getInteger(R.integer.headerSpanSize)
                    AD_VIEW_TYPE -> return 3//resources.getInteger(R.integer.adSpanSize)
                    SIMPLE_TASK_VIEW_TYPE -> return 1//resources.getInteger(R.integer.simpleTaskSpanSize)
                    COMPLEX_TASK_VIEW_TYPE -> return 2//resources.getInteger(R.integer.complexTaskSpanSize)
                    else -> return 1
                }
            }
        }
    }
}
