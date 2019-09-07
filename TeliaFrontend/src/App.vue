<template>
  <div id="app">
    <div>
      <h1><i class="far fa-list-alt"></i> Telia Todos</h1>
    </div>
    <TodoList/>
</div>
</template>

<script>

import TodoList from "./components/TodoList";

export default {
  name: "app",
  components: {
    TodoList
  }
};

</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

h1 {
  background-color: teal;
  color: white;
  padding: 10px;
}
</style>
