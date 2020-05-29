package id.ac.unhas.twodo.dummy

import id.ac.unhas.twodo.model.Todo

object DummyData {
    private val id = arrayOf(1, 2, 3, 4, 5, 6, 7, 8)

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
        "22/12/2016",
        "22/12/2016",
        "31/11/2023",
        "06/06/2020",
        "23/02/2016",
        "12/10/2018",
        "11/05/2015",
        "18/01/2020"
    )

    private val created = arrayOf(
        "22/11/2016",
        "22/12/2016",
        "31/11/2023",
        "06/06/2020",
        "23/02/2016",
        "12/10/2018",
        "11/05/2015",
        "18/01/2020"
    )

    private val edited = arrayOf(
        "22/11/2016",
        "22/12/2016",
        null,
        "06/06/2020",
        "23/02/2016",
        null,
        null,
        "18/01/2200"
    )

    private val todoTime = arrayOf(
        "12:53",
        "01:00",
        "15:22",
        "03:03",
        "02:20",
        "12:30",
        "10:00",
        "00:00"
    )

    private val remind = arrayOf(
        true,
        true,
        false,
        true,
        false,
        false,
        true,
        true
    )

    val listData: ArrayList<Todo>
        get() {
            val list = arrayListOf<Todo>()
            for (position in todoNames.indices) {
                val todo = Todo()
                todo.id = id[position]
                todo.name = todoNames[position]
                todo.desc = todoDesc[position]
                todo.dueDate = todoDue[position]
                todo.created = created[position]
                todo.edited = edited[position]
                todo.dueTime = todoTime[position]
                todo.remind = remind[position]
                list.add(todo)
            }
            return list
        }
}