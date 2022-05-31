package com.ranggacikal.challengechapter5.ui.presenter

import com.ranggacikal.challengechapter5.ui.model.HasilValidasiPlayerData
import com.ranggacikal.challengechapter5.ui.model.HistoryData

interface HistoryView {
    fun historyList(historyData: HistoryData)
}