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
        if (position == null || parent == null)
            return

        val grid = parent.layoutManager as GridLayoutManager
        val spanSize = grid.spanSizeLookup.getSpanSize(position)
        val itemCols = arrayListOf<DecorationItem>()

        for (i: Int in 0..position) {
            val currentSpanSize = grid.spanSizeLookup.getSpanSize(i)
            if (i == 0) {
                itemCols.add(i, DecorationItem(i, currentSpanSize))
                Log.e("TAG", "position: ${itemCols[i].position} col: ${itemCols[i].colNumber}")
            } else {
                var coll = 0
                if (itemCols[i - 1].colNumber == NUMBER_OF_COLUMNS) {
                    coll = currentSpanSize
                } else {
                    if (itemCols[i - 1].colNumber + currentSpanSize <= NUMBER_OF_COLUMNS) {
                        coll = itemCols[i - 1].colNumber + currentSpanSize
                    } else {
                        coll = currentSpanSize
                    }
                }

                itemCols.add(i, DecorationItem(i, coll))
                Log.e("TAG", "position: ${itemCols[i].position} col: ${itemCols[i].colNumber}")
            }
        }

        if (spanSize == NUMBER_OF_COLUMNS) {
            outRect?.right = space
        } else {
            var allSpanSize = 0
            for (i: Int in IntRange(0, position)) {
                allSpanSize += grid.spanSizeLookup.getSpanSize(i)
            }
            val currentModuloResult = allSpanSize % NUMBER_OF_COLUMNS
            if (currentModuloResult == 0) {
                outRect?.right = space
            } else {
                if (parent.childCount > position + 1) {

                }
            }
        }
        outRect?.left = space
        outRect?.top = space
    }

    private data class DecorationItem(val position: Int, val colNumber: Int)
}