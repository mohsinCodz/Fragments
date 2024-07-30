package mohsin.code.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mohsin.code.fragments.adapters.ItemAdapter

class SongListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.songslist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val songList = Constants.getSongList()

        val itemAdapter = ItemAdapter(songList) { song ->
            // Handle item click
            val bundle = Bundle().apply {
                putString("songName", song.songName)
                putString("singerName", song.singerName)
            }
            findNavController().navigate(R.id.action_song_list_fragment_to_song_details, bundle)
        }

        val rvItem: RecyclerView = view.findViewById(R.id.rvItem)
        rvItem.layoutManager = LinearLayoutManager(context)
        rvItem.adapter = itemAdapter
    }
}