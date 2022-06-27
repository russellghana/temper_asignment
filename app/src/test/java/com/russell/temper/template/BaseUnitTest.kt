package com.russell.temper.template

import io.mockk.MockKAnnotations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

abstract class BaseUnitTest {

    /**
     * JUnit rule to change the coroutines Dispatcher from Dispatchers.Main to
     * TestCoroutineDispatcher for unit tests.
     */
    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    open fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @After
    open fun tearDown() {
    }
}

/**
 * JUnit rule to change the coroutines Dispatcher from Dispatchers.Main to
 * TestCoroutineDispatcher for unit tests.
 *
 * The viewModelScope uses the Dispatchers.Main coroutine dispatcher.
 * A CoroutineDispatcher controls how a coroutine runs, including what thread the coroutine code runs on.
 * Dispatcher.Main puts the coroutine on the UI or main thread.
 * This makes sense as a default for ViewModel coroutines, because often, view models manipulate the UI.
 * This works well in production code.
 *
 * But for local tests (tests that run on your local machine in the test source set),
 * the usage of Dispatcher.Main causes an issue: Dispatchers.Main uses Android's Looper.getMainLooper().
 * The main looper is the execution loop for a real application.
 * The main looper is not available (by default) in local tests, because you're not running the full application.
 */
@ExperimentalCoroutinesApi
class CoroutineTestRule : TestRule {
    override fun apply(base: Statement?, description: Description?): Statement =
        object : Statement() {
            override fun evaluate() {
                Dispatchers.setMain(UnconfinedTestDispatcher())
                try {
                    base?.evaluate()
                } finally {
                    Dispatchers.resetMain()
                }
            }
        }
}
