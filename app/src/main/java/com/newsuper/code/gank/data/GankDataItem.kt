package com.newsuper.code.gank.data

data class GankDataItem(val gank: Gank): GankItem(){
    init {
        type = ITEM_DATA
    }
}