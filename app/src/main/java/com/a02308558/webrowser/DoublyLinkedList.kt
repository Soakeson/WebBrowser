package com.a02308558.webrowser

import java.lang.Exception
import java.util.*

class DoublyLinkedList<E>() {
    var head: Node<E>? = null
    var tail: Node<E>? = null
    var curr: Node<E>? = null

    fun enqueue(data: E): DoublyLinkedList<E> {
        if (head == null) {
            head = Node<E>(data, tail, null)
            curr = head
        } else if (tail == null) {
            tail = Node<E>(data, null, head)
            head?.next = tail
            curr = tail
        } else {
            tail = curr
            tail?.next = Node<E>(data, null, tail)
            tail = tail?.next;
            curr = tail
        }
        return this
    }

    fun dequeue(): Node<E>? {
        val n = head
        head = n?.next
        return n
    }

    fun back(): Node<E>? {
        if (curr?.previous != null) {
            curr = curr?.previous
            return curr
        }
        return null
    }

    fun next(): Node<E>? {
        if (curr?.next != null) {
            curr = curr?.next
            return curr
        }
        return null
    }
}

class Node<E>(var data: E, var next: Node<E>?, var previous: Node<E>?) {
}
