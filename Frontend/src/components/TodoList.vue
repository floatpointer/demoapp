	<template>
		<div class="outer">
			
			{{errorMessage}}

			<div class="inputForm">
				<InputForm 
				v-bind:selectedId="selectedId"
				v-bind:selectedHeading="selectedHeading"
				v-bind:selectedText="selectedText"
				v-bind:todos="todos" 
				v-on:updateTodoArray="onInsert"/>
			</div>

			<div class="todolist">
				<div v-if="todos" class="listoftodos">
					<table v-for="item in todos" :key="item.id">
						<tr class="row">
							<td class="cell1">{{item.heading}}</td>
							<td class="cell2">{{item.text}}</td>
							<td class="cell3" v-on:click="deleteTodo(item.id)"><i class="far fa-trash-alt"></i> Delete</td>
							<td class="cell3" v-on:click="editTodo(item)"><i class="far fa-edit"></i> Edit</td>
						</tr>
						<tr>
							<td colspan="4" class="delimiter"></td>
						</tr>
					</table>
				</div>	
			</div>
		</div>
	</template>

	<script>
		import axios from "axios"
		import InputForm from "./InputForm";

		export default {
		name: 'TodoList',
		components: {
    		InputForm
  		},
		data() {
				return {
					errorMessage: null,
					todos: [],
					selectedId: -1,
					selectedText: null,
					selectedHeading: null
				}
		},

		created() {
			this.loadTodos();
		},

		methods: {
			onInsert(newTodo) {

				this.todos = this.todos.filter(item => item.id !== newTodo.id);
				this.todos.push(newTodo);

				this.selectedId = -1;
				this.selectedText = null;
				this.selectedHeading = null;
			},

			loadTodos() {
				this.errorMessage = null;
				var temp = new Array();
				axios.get("http://localhost:9090/todos")
				.then( response => {
					for(const item of response.data) {
						temp.push(item);
					}
					this.todos = temp;
				})
			},

			deleteTodo(id) {
				this.errorMessage = null;
				if(confirm("Do you really want to delete?")) {
					axios.get("http://localhost:9090/deletetodo/" + id)
					.then(response => {
						this.todos = this.todos.filter(item => item.id !== id);

					}) 
					.catch(e => {
						this.errorMessage = e;
					})
				}
			},

			editTodo(item) {
				this.selectedHeading = item.heading;
				this.selectedText = item.text;
				this.selectedId = item.id;
			},

		}
	};
	</script>

	<style scoped>
	.errors {
		color: #fff;
		background-color: red;
		padding: 100px;
		margin-top: 10px;
		margin-bottom: 10px;
	}

	.listoftodos {
		margin-top: 30px;
		width: 100%;
	}
	.row  {
		display: table-row;
		text-align: left;
		border: 10px solid #666;
		word-wrap: none;
	}

	.delimiter {
		border-top: 1px solid #ccc;
	}

	.cell1 {
		width: 400px;
		display: table-cell;
	}

	.cell2 {
		display: table-cell;
		width: 70%;
	}

	.cell3 {
		display: table-cell;
		width: 75px;

		background-color: #4CAF50;
		color: white;
		padding: 3px 3px;
		border: none;
		border-radius: 4px;
		cursor: pointer;
		margin-left: 10px;
		word-wrap: none;
	}
	</style>