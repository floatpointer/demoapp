<template>
<div class="inputForm">
	<div class="errors" v-if="todos.length === 10">Database is full</div>
	<div class="errors" v-if="errorMessage">{{errorMessage}}</div>
	<form v-on:submit="handleSubmit" method="POST">
		<input type="hidden" v-model="formdata.id"/>
		<div class="container">
			<div class="cell">
				<label>Header</label>
				<input autofocus="true" v-bind="selectedHeading" v-model="formdata.heading" type="text" id="header"/>
			</div>
			<div class="cell">
				<label>Todo</label>
				<input v-model="formdata.text" size="70" type="text" id="todo"/>
			</div>
			<input v-model="formdata.id" type="hidden"/>
			<div class="cell">
				<input :disabled="isDisabled" type="submit" value="Save"/>
			</div>
			<div class="cell">
    			<progress-bar
      				:options="options"
      				:value="getProgress"
      			/>
	  			</div>
		</div>
	</form>
</div>
</template>

<script>
	import axios from "axios"

	// Stolen from https://vuejsexamples.com/progressbar-for-vue-js-2/
	import ProgressBar from 'vuejs-progress-bar'

	export default {
	name: 'InputForm',
	props: {
    	todos: {
      		type: Array,
     		required: true
		},
		selectedHeading: String
	},
	data() {
			return {
				errorMessage: null,
				formdata: {
					id: -1, 
					heading: "", 
					text: "", 
				},
				options: {
  					text: {
    				color: '#FFFFFF',
    				shadowEnable: true,
    				shadowColor: '#000000',
    				fontSize: 14,
					fontFamily: 'Helvetica',
					dynamicPosition: false,
					hideText: false
					},
					progress: {
						color: '#2dbd2d',
						backgroundColor: '#C0C0C0'
					},
					layout: {
						height: 40,
						width: 150,
						verticalTextAlign: 61,
						horizontalTextAlign: 43,
						zeroOffset: 0,
						strokeWidth: 30,
						progressPadding: 0,
						type: 'line'
					}
				}
			}
		},
	computed: {
  		isDisabled: function(){
			return this.todos.length === 10;
		},
		getProgress: function() {
			return this.todos.length * 10;
		}

    },
	methods: {

		updateTodos(responseObject) {
			this.$emit("updateTodoArray", responseObject) ;
		},

		handleSubmit(event) {

			event.preventDefault();

			const form = new FormData();
			form.append("heading", this.formdata.heading);
			form.append("text", this.formdata.text);

			axios.post("http://localhost:9090/todos/", form)
			.then(response => {
				this.formdata.heading = "";
				this.formdata.text = "";
				this.formdata.id= -1;
				this.selectedTodo = -1;

				this.updateTodos(response.data);
			})
  			.catch(e => {
				this.errorMessage = e;
			});
		}
	}
}
</script>

<style>
	input[type=text], select, textarea{
		padding: 12px;
		border: 1px solid #ccc;
		border-radius: 4px;
		box-sizing: border-box;
		resize: vertical;
	}

	input[type=submit] {
		background-color: #4CAF50;
		color: white;
		padding: 12px 20px;
		border: none;
		border-radius: 4px;
		cursor: pointer;
	}

	input[type=submit]:disabled {
		background-color: #ccc;
	}

	/* Style the label to display next to the inputs */
	label {
		padding: 12px 12px 12px 0;
		display: inline-block;
	}

	.inputForm {
		padding-bottom: 5px;
	}

	.container {
		width: 100%;
		float: left;
		background-color: #ddd;
		border-bottom: 1px solid #aaa;
		border-top: 1px solid #aaa;
		margin-bottom: 5px;
	}

	.errors {
		background-color: lightsalmon;
		color: white;
		margin-bottom: 10px;
		padding-top: 10px;
		padding-bottom: 10px;
	}

	.cell {
		margin: 7px;
		float: left;
	}
</style>