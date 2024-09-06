package ru.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import ru.app.databinding.FragmentQuizeBinding
import ru.app.quizemodel.QuizeQuestion

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuizeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var countCorrectAnswers = 0
    private lateinit var questions:MutableList<QuizeQuestion>
    private var currentStep = 0
    private var currentAnswer = -1

    private lateinit var binding:FragmentQuizeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuizeBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initQuestions()
        initActionsRadioButtons()
        initClickButtons()
        loadQuestion()
    }

    fun loadQuestion(){
        binding.question.text = questions[currentStep].question()
        binding.var1.text = questions[currentStep].answers()[0]
        binding.var2.text = questions[currentStep].answers()[1]

        binding.nextButton.isEnabled = false
    }

    fun initActionsRadioButtons(){
        binding.var1.setOnCheckedChangeListener { button, isChecked ->
            if (isChecked) {
                currentAnswer = 0
                binding.nextButton.isEnabled = true
            }
        }

        binding.var2.setOnCheckedChangeListener { button, isChecked ->
            if (isChecked) {
                currentAnswer = 1
                binding.nextButton.isEnabled = true
            }
        }
    }

    fun initClickButtons(){
        binding.nextButton.setOnClickListener {
            if (currentAnswer == questions[currentStep].correctAnswer()){
                countCorrectAnswers++
            }
            if (currentStep + 1 == questions.size){
                binding.question.text = resources.getString(R.string.finish_label_go_to_results)
                binding.variants.isVisible = false
                binding.nextButton.isVisible = false
                binding.finishButton.isVisible = true
            }else{
                binding.variants.clearCheck()
                currentStep++
                loadQuestion()
            }
        }
        binding.finishButton.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("countCorrectAnswers", countCorrectAnswers)
                putInt("countQuestions", questions.size)
            }
            findNavController().navigate(R.id.action_quizeFragment_to_resultFragment, bundle)
        }
    }

    fun initQuestions(){
        questions = mutableListOf()
        questions.add(
            QuizeQuestion(resources.getString(R.string.question_1),
            listOf(resources.getString(R.string.answer_to_1_1), resources.getString(R.string.answer_to_1_2)),
            1))

        questions.add(
            QuizeQuestion(resources.getString(R.string.question_2),
                listOf(resources.getString(R.string.answer_to_2_1), resources.getString(R.string.answer_to_2_2)),
                0))

        questions.add(
            QuizeQuestion(resources.getString(R.string.question_3),
                listOf(resources.getString(R.string.answer_to_3_1), resources.getString(R.string.answer_to_3_2)),
                0))
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuizeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuizeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}