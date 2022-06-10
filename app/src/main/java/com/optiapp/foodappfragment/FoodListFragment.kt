package com.optiapp.foodappfragment

import android.os.Bundle
import android.view.*
import android.view.View.inflate
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ComplexColorCompat.inflate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.optiapp.foodappfragment.databinding.ActivityMainBinding.inflate
import com.optiapp.foodappfragment.databinding.FragmentFoodListBinding
import com.optiapp.foodappfragment.data.DataSource
import com.optiapp.foodlist.adapter.itemAdapter
import java.util.zip.Inflater

class FoodListFragment : Fragment() {
    private var _binding: FragmentFoodListBinding? = null
    private val binding get() = _binding!!
    private lateinit var navCon: NavController
    private lateinit var recyclerView: RecyclerView
    private var isGridLayoutManager=true

    var myDataSource= DataSource().loadDelarations()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFoodListBinding.inflate(inflater, container, false)
        navCon = NavHostFragment.findNavController(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.foodlist
//        recyclerView.adapter=itemAdapter(this,myDataSource)
        change()
    }

    private fun change() {
        if(isGridLayoutManager){
            recyclerView.layoutManager= GridLayoutManager(requireContext(),1)

        }
        else{
            recyclerView.layoutManager= StaggeredGridLayoutManager(3, LinearLayoutManager.HORIZONTAL)
            var toast= Toast.makeText(requireContext(),"StaggaredGrid: Swipe Left", Toast.LENGTH_LONG)
            toast.show()

        }
        recyclerView.adapter= itemAdapter(requireContext(),myDataSource)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
    private fun seticon(menuItem: MenuItem){
        if(menuItem==null){
            return
        }
        menuItem.icon=
            if(isGridLayoutManager)
                ContextCompat.getDrawable(requireContext(),R.drawable.ic_baseline_view_quilt_24)

            else{
                ContextCompat.getDrawable(requireContext(),R.drawable.ic_baseline_view_list_24)
            }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
        inflater.inflate(R.menu.layout_menu,menu)
        val layoutButton= menu?.findItem(R.id.action_switch)
        if (layoutButton != null) {
            seticon(layoutButton)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_switch ->{
                isGridLayoutManager= !isGridLayoutManager
                change()
                seticon(item)
                return true

            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}