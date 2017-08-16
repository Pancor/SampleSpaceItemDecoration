package panekpawel.pl.samplespaceitemdecoration.utils

import panekpawel.pl.samplespaceitemdecoration.models.Ad
import panekpawel.pl.samplespaceitemdecoration.models.Header
import panekpawel.pl.samplespaceitemdecoration.models.RecyclerAdapterData
import panekpawel.pl.samplespaceitemdecoration.models.Task


class DummyDataCreator {

    val data = arrayListOf<RecyclerAdapterData>()

    fun createData() {
        add(Header("Monday"), HEADER_VIEW_TYPE)
            add(Task("Go out with dog"), SIMPLE_TASK_VIEW_TYPE)
            add(Task("Make dinner"), SIMPLE_TASK_VIEW_TYPE)
            add(Task("Go to gym"), SIMPLE_TASK_VIEW_TYPE)
            add(Task("Do homework"), SIMPLE_TASK_VIEW_TYPE)
            add(Ad("Developers hates him!",
                    "One little trick to be the best programmer in world",
                    "https://cdn.pixabay.com/photo/2011/12/13/17/07/earth-11048_960_720.jpg"),
                    AD_VIEW_TYPE)
            add(Task("Piano lesson"), SIMPLE_TASK_VIEW_TYPE)
        add(Header("Wednesday"), HEADER_VIEW_TYPE)
            add(Task("Wake up"), SIMPLE_TASK_VIEW_TYPE)
            add(Task("Go to sleep"), SIMPLE_TASK_VIEW_TYPE)
            add(Task("Garden", "-cut grass \n-collect apples"), COMPLEX_TASK_VIEW_TYPE)

    }

    private fun add(model: Any, viewType: Int) {
        data.add(RecyclerAdapterData(model, viewType))
    }
}