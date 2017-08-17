package panekpawel.pl.samplespaceitemdecoration

import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View

class SpaceItemDecoration : RecyclerView.ItemDecoration() {

    private var space: Int? = -1
    private var NUMBER_OF_COLUMNS = -1

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)

        setupSpace(parent)
        setupSpanCount(parent)
        addSpaceToView(outRect, parent?.getChildAdapterPosition(view), parent)
    }

    private fun setupSpace(parent: RecyclerView?) {
        if (space == -1) {
            val context = parent?.context
            val resources = context?.resources
            space = resources?.getDimension(R.dimen.recyclerItemSpace)?.toInt()
        }
    }

    private fun setupSpanCount(parent: RecyclerView?) {
        if (NUMBER_OF_COLUMNS == -1) {
            if (parent?.layoutManager !is GridLayoutManager) {
                throw IllegalArgumentException("SearchResultItemDecoration accepts only GridLayoutManager " +
                        "as a RecyclerView's layout manager")
            }
            val gridManager = parent.layoutManager as GridLayoutManager
            NUMBER_OF_COLUMNS = gridManager.spanCount
        }
    }

    private fun addSpaceToView(outRect: Rect?, position: Int?, parent: RecyclerView?) {
        if (position == null || parent == null) return

        val itemPositionByCols = arrayListOf<DecorationItem>()
        val gridLayout = parent.layoutManager as GridLayoutManager
        for (i: Int in 0..position) {
            val PREVIOUS_ITEM = i - 1
            val currentSpanSize = gridLayout.spanSizeLookup.getSpanSize(i)
            if (i == 0) {
                itemPositionByCols.add(i, DecorationItem(i, currentSpanSize))
            } else {
                var coll: Int
                if (itemPositionByCols[PREVIOUS_ITEM].colNumber == NUMBER_OF_COLUMNS) {
                    coll = currentSpanSize
                } else {
                    if (itemPositionByCols[PREVIOUS_ITEM].colNumber + currentSpanSize <= NUMBER_OF_COLUMNS) {
                        coll = itemPositionByCols[PREVIOUS_ITEM].colNumber + currentSpanSize
                    } else {
                        coll = currentSpanSize
                    }
                }
                itemPositionByCols.add(i, DecorationItem(i, coll))
            }
        }

        if (position + 1 == parent.childCount){
            outRect?.bottom = space
        }
        if (itemPositionByCols[position].colNumber == NUMBER_OF_COLUMNS) {
            outRect?.right = space
        }
        outRect?.left = space
        outRect?.top = space
    }



    private data class DecorationItem(val position: Int, val colNumber: Int)
}