package io.github.yasumtl.dicoquebecois.model

import com.google.gson.annotations.SerializedName

data class Expression(
    @SerializedName("feed") var feed: Feed
)

data class Feed(
        @SerializedName("entry") var entry:List<Entry>
)

data class Entry(
    @SerializedName("gsx\$quebecois") var expressionQC: Quebecois,
    @SerializedName("gsx\$francais") var expressionFR: Francais
)

data class Quebecois(
    @SerializedName("\$t") var phraseQC: String
)

data class Francais(
    @SerializedName("\$t") var phraseFR: String
)