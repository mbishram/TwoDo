package id.ac.unhas.twodo.dummy

import id.ac.unhas.twodo.model.Todo

object DummyData {
    private val todoNames = arrayOf(
        "Take the Groceries Out",
        "Take the Groceries Out Part 2",
        "Lorem ipsum dolor sit amet, consectetur",
        "Morbi nec euismod nibh, rhoncus",
        "In risus metus, blandit eu dictum et, lobortis et dolor.",
        "Morbi sit amet sodales tortor.",
        "finibus luctus magna ullamcor",
        "Nullam justo tortor, mattis vel"
    )

    private val todoDesc = arrayOf(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi gravida velit vitae orci euismod, in ultricies enim congue. Aenean at turpis lorem. Ut faucibus pharetra neque eu porttitor.",
        "Morbi ullamcorper est ac volutpat porttitor. Morbi sodales mollis massa. Donec sed risus magna. Curabitur at cursus massa. Vivamus ultricies odio vitae diam accumsan, sit amet ultricies felis consequat. Vivamus vulputate facilisis massa mattis tincidunt. Sed sodales egestas dapibus. Phasellus tincidunt, massa non consectetur dignissim, purus felis cursus odio, eu accumsan tortor tortor quis enim. In ultricies aliquet commodo.",
        "Sed imperdiet sapien ipsum, in cursus est scelerisque eu. Etiam non ante at ipsum posuere fringilla.",
        "In risus metus, blandit eu dictum et, lobortis et dolor.",
        "Sed luctus in odio facilisis sodales. Maecenas id cursus eros, non commodo nisl. Vivamus rutrum congue orci, at tincidunt metus molestie non.",
        "Proin mattis vel elit ut faucibus. Phasellus elementum mi purus, non ullamcorper nulla interdum at.",
        "Phasellus viverra tortor vel ligula lacinia viverra. Duis faucibus libero id arcu cursus, condimentum tristique metus accumsan. Aenean pretium, lorem et placerat luctus",
        "id fringilla justo diam in sem. "
    )

    private val todoDue = arrayOf(
        "12 Maret 2001",
        "42 Maret 3924",
        "1 April 2020",
        "12 Agustus 1945",
        "2 Januari 2012",
        "5 September 2101",
        "4 Agustus 2929",
        "13 Desember 2019"
    )

    val listData: ArrayList<Todo>
        get() {
            val list = arrayListOf<Todo>()
            for (position in todoNames.indices) {
                val todo = Todo()
                todo.name = todoNames[position]
                todo.desc = todoDesc[position]
                todo.dueDate = todoDue[position]
                list.add(todo)
            }
            return list
        }
}