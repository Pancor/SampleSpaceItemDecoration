package panekpawel.pl.samplespaceitemdecoration.models

data class Task(val title: String) {

    constructor(title: String, description: String): this(title)
}