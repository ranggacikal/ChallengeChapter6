package com.ranggacikal.challengechapter5.ui.presenter

import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.room.Room
import com.ranggacikal.challengechapter5.db.HistoryDatabase
import com.ranggacikal.challengechapter5.ui.LeaderBoardFragment
import com.ranggacikal.challengechapter5.ui.model.HistoryData
import kotlinx.coroutines.*

class HistoryPresenter(val data: HistoryView) {

    private var db: HistoryDatabase? = null

    fun getListHistory(context: Context){
        db = HistoryDatabase.getInstance(context)
        GlobalScope.launch {
            val historyList = db?.historyDao()?.getAllHistory()
            historyList.let {
                val historyData = HistoryData()
                historyData.historyData = it
                data.historyList(historyData)
            }
        }
    }

}