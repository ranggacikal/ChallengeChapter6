package com.ranggacikal.challengechapter5.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.ranggacikal.challengechapter5.R
import com.ranggacikal.challengechapter5.databinding.FragmentGameBinding
import com.ranggacikal.challengechapter5.databinding.FragmentLeaderBoardBinding
import com.ranggacikal.challengechapter5.db.History
import com.ranggacikal.challengechapter5.db.HistoryDatabase
import com.ranggacikal.challengechapter5.ui.adapter.HistoryAdapter
import com.ranggacikal.challengechapter5.ui.model.HistoryData
import com.ranggacikal.challengechapter5.ui.presenter.GamePresenter
import com.ranggacikal.challengechapter5.ui.presenter.HistoryPresenter
import com.ranggacikal.challengechapter5.ui.presenter.HistoryView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LeaderBoardFragment : Fragment(), HistoryView {

    lateinit var binding: FragmentLeaderBoardBinding
    lateinit var adapter: HistoryAdapter
    lateinit var presenter: HistoryPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLeaderBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = HistoryPresenter(this)
        presenter.getListHistory(requireContext())
    }

    override fun historyList(historyData: HistoryData) {
        activity?.runOnUiThread {
            binding.rvHistory.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = historyData.historyData?.let { HistoryAdapter(it) }!!
            binding.rvHistory.adapter = adapter
        }
    }
}