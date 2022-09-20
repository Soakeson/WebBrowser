package com.a02308558.webrowser

import java.lang.Exception
import java.util.*

class History<E>(data: E) {
    var curr: Node<E> = Node(data, null, null)

    fun enqueue(data: E): History<E> {
        curr.next = Node<E>(data, null, curr)
        curr = curr.next!!
        return this
    }

    fun back(): E {
        if (curr.previous != null) {
            curr = curr.previous!!
            return curr.data
        }
        return curr.data
    }

    fun next(): E {
        if (curr.next != null) {
            curr = curr.next!!
            return curr.data
        }
        return curr.data
    }
}

class Node<E>(var data: E, var next: Node<E>?, var previous: Node<E>?) {
}
