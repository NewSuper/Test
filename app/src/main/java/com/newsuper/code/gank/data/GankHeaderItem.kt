package com.newsuper.code.gank.data

data class GankHeaderItem(val name: String) : GankItem() {
    init {
        type = ITEM_HEADER
    }
}