package com.example.app_helper_fe.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_helper_fe.data.Medicine
import com.example.app_helper_fe.data.Storage_medicine
import com.example.app_helper_fe.databinding.FragmentSearchBinding
import com.example.app_helper_fe.ui.detail.MedicineDetailClickListener
import java.util.Locale

class SearchFragment : Fragment(), SearchMedicineItemClickListener, MedicineDetailClickListener {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: SearchMedicineListAdapter //검색 기능 추가
    private lateinit var filteredList: ArrayList<Medicine> //검색 기능 추가

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the filtered list with the full medicine list
        filteredList = ArrayList(emptyList())

        // Set up RecyclerView with initial data
        adapter = SearchMedicineListAdapter(filteredList, this, this)
        binding.rvSearchMedicineList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSearchMedicineList.adapter = adapter

        // Set up SearchView
        binding.searchBar.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                filterMedicines(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
    }

    // search에서 - 검색 기능(모든 약품 페이지)
    private fun filterMedicines(query: String) {
        val searchText = query?.lowercase(Locale.getDefault()).orEmpty()
        filteredList.clear()

        if (searchText.isNotEmpty()) {
            Storage_medicine.getQueryMedicineData(query) { medicine ->
                if (medicine != null && medicine.isNotEmpty()) {
                    Log.d("final", "Data loaded: ${medicine}")
                    binding.rvSearchMedicineList.adapter = SearchMedicineListAdapter(medicine, this, this)
                } else {
                    Log.d("final", "No data available or failed to fetch data")
                }
            }
        }

        // Notify the adapter of data changes
        adapter.notifyDataSetChanged()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSearchMedicineClick(medicine: Medicine) {

    }

    // medicine detail page 약품 상세 정보 이동 기능
    override fun onMedicineDetailClick(id:Int) {
        val itemSeq = id.toString()
        val action = SearchFragmentDirections.actionSearchMedicineToMedicineDetail(itemSeq)
        findNavController().navigate(action)

    }


}