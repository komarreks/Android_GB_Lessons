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

class QuizeFragment : Fragment() {

    private var countCorrectAnswers = 0
    private lateinit var questions:MutableList<QuizeQuestion>
    private var currentStep = 0
    private var currentAnswer = -1

    private var _binding:FragmentQuizeBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizeBinding.inflate(inflater)

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initQuestions()
        initActionsRadioButtons()
        initClickButtons()
        loadQuestion()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun loadQuestion(){
        _binding!!.question.text = questions[currentStep].question()
        _binding!!.var1.text = questions[currentStep].answers()[0]
        _binding!!.var2.text = questions[currentStep].answers()[1]

        _binding!!.nextButton.isEnabled = false
    }

    fun initActionsRadioButtons(){
        _binding!!.var1.setOnCheckedChangeListener { button, isChecked ->
            if (isChecked) {
                currentAnswer = 0
                _binding!!.nextButton.isEnabled = true
            }
        }

        _binding!!.var2.setOnCheckedChangeListener { button, isChecked ->
            if (isChecked) {
                currentAnswer = 1
                _binding!!.nextButton.isEnabled = true
            }
        }
    }

    fun initClickButtons(){
        _binding!!.nextButton.setOnClickListener {
            if (currentAnswer == questions[currentStep].correctAnswer()){
                countCorrectAnswers++
            }
            if (currentStep + 1 == questions.size){
                _binding!!.question.text = resources.getString(R.string.finish_label_go_to_results)
                _binding!!.variants.isVisible = false
                _binding!!.nextButton.isVisible = false
                _binding!!.finishButton.isVisible = true
            }else{
                _binding!!.variants.clearCheck()
                currentStep++
                loadQuestion()
            }
        }
        _binding!!.finishButton.setOnClickListener {
            val bundle = Bundle().apply {
                putInt(ResultFragment.ARG_PARAM1, countCorrectAnswers)
                putInt(ResultFragment.ARG_PARAM2, questions.size)
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
}