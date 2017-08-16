package panekpawel.pl.samplespaceitemdecoration

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import panekpawel.pl.samplespaceitemdecoration.utils.DummyDataCreator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val gridLayoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = gridLayoutManager

        val itemDecoration = SpaceItemDecoration()
        recyclerView.addItemDecoration(itemDecoration)

        val dummyDataCreator = DummyDataCreator()
        dummyDataCreator.createData()
        val data = dummyDataCreator.data

        val adapter = RecyclerAdapter(data)
        recyclerView.adapter = adapter
    }
}
