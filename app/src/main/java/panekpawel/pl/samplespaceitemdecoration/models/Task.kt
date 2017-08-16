package panekpawel.pl.samplespaceitemdecoration.models

data class Task(val title: String, val description: String?) {

    constructor(title: String): this(title, null)
}