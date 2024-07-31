package mohsin.code.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mohsin.code.fragments.adapters.ItemAdapter
import java.util.Locale

class SongListFragment : Fragment() {

    private var mList = mutableListOf<Songs>()
    private lateinit var adapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.songslist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize mList with actual data
        mList = Constants.getSongList().toMutableList()

        // Initialize the adapter and set it to the fragment's adapter property
        adapter = ItemAdapter(mList as ArrayList<Songs>) { song ->
            // Handle item click
            val bundle = Bundle().apply {
                putString("songName", song.songName)
                putString("singerName", song.singerName)
            }
            findNavController().navigate(R.id.action_song_list_fragment_to_song_details, bundle)
        }

        val rvItem: RecyclerView = view.findViewById(R.id.rvItem)
        rvItem.layoutManager = LinearLayoutManager(context)
        rvItem.adapter = adapter

        val searchView: SearchView = view.findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterText(newText)
                return true
            }
        })
    }

    private fun filterText(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<Songs>()
            for (i in mList) {
                if (i.songName.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT))
                    || i.singerName.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT))) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(context, "No Data Found", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }
}