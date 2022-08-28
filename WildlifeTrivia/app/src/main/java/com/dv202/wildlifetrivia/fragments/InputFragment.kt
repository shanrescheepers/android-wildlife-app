import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quiz.databinding.FragmentInputBinding
import com.example.quiz.models.QuestionDto
import com.example.quiz.services.DataService

class InputFragment : Fragment() {

    private lateinit var _binding: FragmentInputBinding
    private var question: QuestionDto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.question = DataService.getNextQuestion()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInputBinding.inflate(inflater, container, false)
        _binding.inputQuestionText.text = this.question?.question
        _binding.inputScore.text = "Score: ${DataService.getTotalPoints()}"

        _binding.inputSubmitButton.setOnClickListener {
            val input = _binding.answerInput.text.toString().lowercase()
            val answer = question!!.answer!!.lowercase()

            DataService.setAnswer(input)

            if (input == answer) {
                DataService.incrementPoints()
            }

            val nextQuestionIsMulti = DataService.checkNextQuestionType()

            if (nextQuestionIsMulti != null) {
                if (nextQuestionIsMulti) findNavController().navigate(R.id.multipleChoiceFragment) else findNavController().navigate(R.id.inputFragment)
            } else {
                findNavController().navigate(R.id.summaryFragment)
            }

        }

        return _binding.root
    }
}