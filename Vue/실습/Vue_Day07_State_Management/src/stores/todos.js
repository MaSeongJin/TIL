import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useTodosStore = defineStore('todos', () => {
  let id = 0;
  // state
  const todos = ref([
    {id: id++, text: '수업듣기', isDone: false},
    {id: id++, text: '점심먹기', isDone: false},
    {id: id++, text: '춤추기', isDone: false},
  ])
  // actions
  const addTodo = function (todoText){
    todos.value.push({
      id: id++,
      text: todoText,
      isDone: false,
    })
  }

  const deleteTodo = function(todoId) {
    const idx = todos.value.findIndex((todo) =>{
      return todo.id === todoId
    })
    // console.log(id)
    todos.value.splice(idx, 1)
  }

  const updateTodo = function(todoId) {
    todos.value = todos.value.map((todo) => {
      if(todo.id === todoId) {
        todo.isDone = !todo.isDone
      }
      return todo
    })
  }

  const doneTodoCount = computed(() => {
    return todos.value.filter((todo)=> todo.isDone).length
  })

  return { todos, addTodo, deleteTodo, updateTodo, doneTodoCount }
}, {persist:true})
