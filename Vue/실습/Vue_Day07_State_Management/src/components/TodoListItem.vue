<template>
    <div>
        <span class="click-cursor" @click="store.updateTodo(todo.id)"
            :class="{'is-done': todo.isDone}">{{ todo.text }}</span>
        <button @click="store.deleteTodo(todo.id)">X</button>
    </div>
</template>

<script setup>
import { useTodosStore } from '@/stores/todos';

const store = useTodosStore();

defineProps({
    todo: Object
})
</script>

<style scoped>
    .is-done {
        text-decoration: line-through;
    }
    .click-cursor {
        cursor: pointer;
    }
</style>