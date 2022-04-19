package com.example.kotlinbasics.android_architecture.mvp_architecture

class CounterActivityPresenter(currentView: View): Presenter {

    private var view: View = currentView
    private var model: Model = CounterActivityModel()

    init {
        view.initializeView()
    }

    override fun incrementCounter() {
        model.incrementCounter()
        view.updateView(true)
    }

    override fun decrementCounter() {
        model.decrementCounter()
        view.updateView(false)
    }

    override fun getCounter(): String = model.getCounter()
}