package com.evapps.event.screens.main.views

import android.animation.ValueAnimator
import android.content.Context
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.core.animation.doOnEnd
import androidx.core.view.updateLayoutParams
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.evapps.event.R
import com.evapps.event.constants.Graphic

import com.evapps.event.databinding.SearchFragmentBinding
import com.evapps.event.helpers.MetricsHelper.Companion.toPx
import com.evapps.event.screens.log_in.AuthActivity
import com.evapps.event.screens.log_in.interfaces.AppBarController
import com.evapps.event.screens.main.view_models.SearchViewModel
import com.evapps.event.screens.main.views.calendar.CalendarBarState
import com.evapps.event.screens.main.views.calendar.DayViewContainerLarge
import com.evapps.event.screens.main.views.calendar.DayViewContainerSmall
import com.evapps.event.utils.CalendarBarAnimationHelper
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.InDateStyle
import com.kizitonwose.calendarview.model.ScrollMode
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.utils.Size
import com.kizitonwose.calendarview.utils.yearMonth
import kotlinx.android.synthetic.main.bar_calendar_layout.view.*
import java.lang.ClassCastException
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.util.*
import android.animation.ObjectAnimator
import android.animation.StateListAnimator
import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.view.View.NOT_FOCUSABLE
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.RotateAnimation
import android.view.animation.TranslateAnimation
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce


class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewModel: SearchViewModel
    private var binding: SearchFragmentBinding? = null
    private var selectedDate: LocalDate = LocalDate.now()
    private var appBarController: AppBarController? = null
    private val calendarBarAnimationHelper = CalendarBarAnimationHelper()
    private var currentCalendarState = CalendarBarState.COLLAPSED
    private var yTouch = 0f
    private var screenWidth: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater, R.layout.search_fragment, container, false
        ) as SearchFragmentBinding

        val view = binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        appBarController?.hideTopBar()
        initCalendar()
        initListeners()
    }

    private fun initListeners() {
//        binding!!.calendarBar.container.setOnClickListener {
//            ViewColapseAnimationUtils.expand(binding!!.calendarBar.container, 10)
//        }
        binding!!.calendarBar.container.setOnTouchListener { v, event ->

            when (event.action) {
                MotionEvent.ACTION_DOWN -> yTouch = event.y
                MotionEvent.ACTION_MOVE -> {
                    var yDiff = (event.y - yTouch).toInt()
                    yTouch = event.y
                    calendarBarAnimationHelper.resize(
                        binding?.calendarBar?.container!!,
                        binding?.calendarBar?.calendarWrapper!!,
                        yDiff
                    )
                }
                MotionEvent.ACTION_UP -> {
                    if (calendarBarAnimationHelper.completeIsNeeded(binding?.calendarBar?.container!!)) {
                        calendarBarAnimationHelper.completeResizing(
                            binding?.calendarBar?.calendarWrapper!!,
                            binding?.calendarBar?.container!!,
                            ::changeCalendarBarState
                        )
                    } else changeCalendarBarState(calendarBarAnimationHelper.getCalendarState())
                }
            }

            return@setOnTouchListener true
        }

    }

    private fun initCalendar() {
        val dm = DisplayMetrics()
        val wm = requireContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
        wm.defaultDisplay.getMetrics(dm)
        screenWidth = dm.widthPixels
        setCalendarWeekSize()
        binding!!.calendarBar.calendarView?.dayBinder = setUpDayLargeContainer()

        val currentMonth = YearMonth.now()
        // Value for firstDayOfWeek does not matter since inDates and outDates are not generated.
        binding!!.calendarBar.calendarView?.setup(
            currentMonth.minusMonths(1),
            currentMonth.plusMonths(3),
            DayOfWeek.values().random()
        )
        scrollToCurrentDay()

        var firstDrawing = true
        binding!!.calendarBar.container.viewTreeObserver.addOnGlobalLayoutListener {
            if (firstDrawing) {
                setUpCalendarAnimationHelper()
                firstDrawing = false
            }
        }
    }

    private fun setCalendarWeekSize() {
        val dayWidth = (screenWidth - Graphic.DOUBLE_SCREEN_MARGIN.toPx) / 7
        val dayHeight = getWeekDayByScreen()
        binding!!.calendarBar.calendarView.apply {
            this.daySize = Size(dayWidth.toInt(), dayHeight.toInt())
        }
        setCalendarWrapperSize(dayHeight.toInt())
    }

    private fun setCalendarMonthSize() {
        val size = getDayWidthByScreen()
        binding!!.calendarBar.calendarView.apply {
            this!!.daySize = Size(size, size)
        }
    }

    private fun getWeekDayByScreen(): Int {
        return (getDayWidthByScreen() * 1.25).toInt()
    }


    private fun setCalendarWrapperSize(height: Int) {
        val calendarWrapper = binding!!.calendarBar.calendarWrapper
        val layoutParams = calendarWrapper.layoutParams as ViewGroup.LayoutParams
        layoutParams.height = height
        calendarWrapper.layoutParams = layoutParams
    }

    private fun getDayWidthByScreen(): Int {
        return ((screenWidth - Graphic.DOUBLE_SCREEN_MARGIN.toPx) / 7).toInt()
    }

    private fun setUpCalendarAnimationHelper() {
        val dayMonthHeight = getDayWidthByScreen()
        val minHeight = getWeekDayByScreen()
        val maxHeight = dayMonthHeight * 6
        calendarBarAnimationHelper.setCalendarBarParams(
            binding?.calendarBar?.container!!,
            minHeight,
            maxHeight
        )
    }

    private fun scrollToCurrentDay() {
        binding!!.calendarBar.calendarView?.scrollToDate(LocalDate.now().minusDays(3))
    }

    private fun scrollToCurrentMonth() {
        binding!!.calendarBar.calendarView?.scrollToMonth(LocalDate.now().yearMonth)
    }


    private fun setUpDayLargeContainer(): DayBinder<DayViewContainerLarge> {
        return object : DayBinder<DayViewContainerLarge> {
            override fun create(view: View) = DayViewContainerLarge(
                binding!!.calendarBar.calendarView,
                view, dateController
            )

            override fun bind(container: DayViewContainerLarge, day: CalendarDay) {
                container.bind(day, dateController)
            }
        }
    }

    private fun setUpDaySmallContainer(): DayBinder<DayViewContainerSmall> {
        return object : DayBinder<DayViewContainerSmall> {
            override fun create(view: View) = DayViewContainerSmall(
                binding!!.calendarBar.calendarView,
                view, dateController
            )

            override fun bind(container: DayViewContainerSmall, day: CalendarDay) {
                container.bind(day, dateController)
            }
        }
    }

    private val dateController = object : DateController {
        override fun selectDate(date: LocalDate) {
            selectedDate = date
        }

        override fun getSelectedDate(): LocalDate {
            return selectedDate
        }
    }


    @SuppressLint("WrongConstant")
    private fun changeCalendarBarState(calendarBarState: CalendarBarState) {
        if (calendarBarState == currentCalendarState) return
        currentCalendarState = calendarBarState
        val oneWeekHeight = (binding!!.calendarBar.calendarView.daySize.height * 1.25).toInt()
        val oneMonthHeight = oneWeekHeight * 5
        var oldHeight: Int = 0
        var newHeight: Int = 0
        when (calendarBarState) {
            CalendarBarState.COLLAPSED -> {
                oldHeight = oneMonthHeight
                newHeight = oneWeekHeight
            }
            CalendarBarState.EXPANDED -> {
                oldHeight = oneWeekHeight
                newHeight = oneMonthHeight
            }
        }

        when (calendarBarState) {
            CalendarBarState.COLLAPSED -> {
                binding!!.calendarBar.calendarView.updateMonthConfiguration(
                    inDateStyle = InDateStyle.NONE,
                    maxRowCount = 1,
                    hasBoundaries = true
                )

                scrollToCurrentDay()
                binding!!.calendarBar.calendarView.dayViewResource =
                    R.layout.large_calendar_day_layout
                binding!!.calendarBar.calendarView.dayBinder = setUpDayLargeContainer()
                setCalendarWeekSize()
            }
            CalendarBarState.EXPANDED -> {
                binding!!.calendarBar.calendarView.updateMonthConfiguration(
                    inDateStyle = InDateStyle.ALL_MONTHS,
                    maxRowCount = 6,
                    hasBoundaries = true
                )
                scrollToCurrentMonth()
                binding!!.calendarBar.calendarView.dayViewResource =
                    R.layout.small_calendar_day_layout
                binding!!.calendarBar.calendarView.dayBinder = setUpDaySmallContainer()
                setCalendarMonthSize()
            }
        }

//        val fadeOut: ObjectAnimator =
//            ObjectAnimator.ofFloat(binding!!.calendarBar.calendarView, "alpha", 1f, 0f)
//        fadeOut.duration = 100
//
//        fadeOut.doOnEnd {
//            val fadeIn: ObjectAnimator =
//                ObjectAnimator.ofFloat(binding!!.calendarBar.calendarView, "alpha", 0f, 1f)
//            fadeIn.duration = 100
//            fadeIn.start()
//        }
//        fadeOut.start()
          val calendar = binding!!.calendarBar.calendarWrapper
//        val animationSet = AnimationSet(true)
//        val animRotateOut = TranslateAnimation(
//            Animation.ABSOLUTE, 0.0f, Animation.ABSOLUTE, 0.0f,
//            Animation.ABSOLUTE, 0.0f, Animation.ABSOLUTE, -450.0f
//        )
//
//        animRotateOut.duration = 500
//        animRotateOut.start()
//        calendar.startAnimation(animRotateOut)
//        animationSet.addAnimation(animRotateOut)
    //    Handler(Looper.getMainLooper()).postDelayed ({
            val animRotateIn = SpringAnimation(calendar, DynamicAnimation.TRANSLATION_Y)
            animRotateIn.setStartValue(-150f)
            val animForce = SpringForce()
            animForce.setStiffness(SpringForce.STIFFNESS_LOW)
            animForce.setFinalPosition(0.0f)
            animForce.setDampingRatio(SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY)
            animRotateIn.setSpring(animForce)
            animRotateIn.start()
     //   }, 500)

        // animationSet.addAnimation(animRotateIn)
      //  binding!!.calendarBar.calendarView.startAnimation(animationSet)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            appBarController = context as AuthActivity
        } catch (e: ClassCastException) {
        }
    }
}