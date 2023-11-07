<template>
    <div>
        <h2>BoardView</h2>
        <router-link to="/board">게시글 목록</router-link>
        <router-link to="/board/create">게시글 생성</router-link>
        <hr>
        <router-view/>
    </div>
</template>

<script setup>

</script>

<style scoped>

</style>