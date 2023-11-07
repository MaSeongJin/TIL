<template>
    <div>
        <h2>UserView</h2>
        <!-- 아래와 같이 접근 가능하지만, 권장하지 않는다. -->
        <p>{{ $route.params.id }}</p>
        <!-- 아래와 같이 사용하는 것을 권장 -->
        <p>{{ userId }}</p>
        <p>{{ userId2 }}</p>
        <hr>
        <button @click="goHome">홈</button>
    </div>
</template>

<script setup>
    import { onBeforeRouteLeave, onBeforeRouteUpdate, useRoute, useRouter } from 'vue-router';
    import { ref, watch } from 'vue';
    const route = useRoute();
    const userId = ref(route.params.id);
    const userId2 = ref(route.params.id);
    const router = useRouter();
    const goHome = function() {
        // 경로를 직접 작성 가능 / 이름으로 작성 가능
        // router.push('/');
        // router.push({name: 'about'});
        router.replace({name:'home'});
    };
    watch(() =>route.params.id, (newId)=>{
        userId2.value = newId;
    });

    onBeforeRouteLeave(()=>{
        const answer = confirm("정말로 떠나겠습니까?");
        if(!answer) return false;
    });

    onBeforeRouteUpdate((to)=>{
        userId.value = to.params.id;
    });
</script>

<style scoped>

</style>