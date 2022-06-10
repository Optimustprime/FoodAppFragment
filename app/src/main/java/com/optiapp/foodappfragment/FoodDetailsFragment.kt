package com.optiapp.foodappfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import com.optiapp.foodappfragment.databinding.FragmentFoodDetailsBinding
import com.optiapp.foodappfragment.databinding.FragmentFoodListBinding
import java.math.BigInteger
import kotlin.properties.Delegates

class FoodDetailsFragment : Fragment() {
    private var _binding: FragmentFoodDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var navCon: NavController
    private lateinit var letterId:String
//    private lateinit var letterCode:String
    private var letterCode by Delegates.notNull<Int>()


    companion object{
//        private const val code: Int = 0
        const val CODE="code"
        const val LETTER="letter"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFoodDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var image=binding.imageView
        var text=binding.textView2
//        Log.v("test",letterId.toString())

        arguments?.let{
            letterCode=it.getInt(CODE)
            letterId=it.getString(LETTER).toString()
        }

//        var item = listOf<String>(List<Declaration>)
//        Log.v("test1",item.stringResource.toString())

//        val code = intent?.extras?.getInt("code")
//        Log.v("test1",code.toString())
//
        if (letterCode != null) {
            image.setImageResource(letterCode)
        }
        if(letterId=="0"){

            text.setText("Juicy Jollof rice, Fried Chicken and Fried Plantain finished With our Special Source. The Original Simple favourite")

        }
        else{
            text.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Quis ipsum suspendisse ultrices gravida. Risus commodo viverra maecenas accumsan lacus vel facilisis")
        }

    }
}