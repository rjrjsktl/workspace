const filterAll = document.getElementById("filterAll");
const filterActive = document.getElementById("filterActive");
const filterComplete = document.getElementById("filterComplete");
const allcheck = document.getElementById("allcheck");
addFilterClickListener(filterAll, "all");
addFilterClickListener(filterActive, "active");
addFilterClickListener(filterComplete, "complete");
let currentIndex = 1;

function updateSelectedItemsCount() {
	
	const taskBoxes = document.querySelectorAll('.task-box');
	const unselectedItemsCount = Array.from(taskBoxes).reduce((count, taskBox) => {
		const newsp = taskBox.querySelector('.newsp');
		const isComplete = window.getComputedStyle(newsp).getPropertyValue('color') === 'rgb(0, 128, 0)';
		return count + (isComplete ? 0 : 1);
	}, 0);
	document.getElementById("items").innerText = unselectedItemsCount;
}

function resetBorderColor() {
	[filterAll, filterActive, filterComplete].forEach(filter => filter.style.borderColor = "");
}

function filterTasks(filter) {
	const taskBoxes = document.querySelectorAll('.task-box');
	taskBoxes.forEach(taskBox => {
		const newsp = taskBox.querySelector('.newsp');
		const isComplete = window.getComputedStyle(newsp).getPropertyValue('color') === 'rgb(0, 128, 0)';
		if ((filter === "all") || (filter === "active" && !isComplete) || (filter === "complete" && isComplete)) {
			taskBox.style.display = "flex";
		} else {
			taskBox.style.display = "none";
		}
	});
}

function addFilterClickListener(filterElement, filterType) {
	filterElement.addEventListener("click", function () {
		resetBorderColor();
		filterElement.style.borderColor = "green";
		filterTasks(filterType);
	});
}

allcheck.addEventListener("click", function () {
	const allSpans = document.querySelectorAll(".task-box span");
	const allInputs = document.querySelectorAll(".task-box input");
	const toggleAll = this.style.color !== "green";
	this.style.color = toggleAll ? "green" : "lightgray";

	allSpans.forEach((span, index) => {
		const input = allInputs[index];
		const isChecked = span.style.color === "green";
		if (toggleAll !== isChecked) {
			span.style.color = toggleAll ? "green" : "lightgray";
			span.style.borderColor = toggleAll ? "green" : "lightgray";
			input.style.textDecoration = toggleAll ? "line-through" : "none";
			input.style.color = toggleAll ? "green" : "lightgray";
			
			$.ajax({
		url: 'updateAll',
		method: 'POST',
		dataType: 'json',
		data: {
			action: toggleAll ? 'Y' : 'N'
		},
		success: function (data) {
			console.log(data);
		},
		error: function (err) {
			console.log(err);
		}
	});
	
		}
	});

	

	updateSelectedItemsCount();
});


document.addEventListener('DOMContentLoaded', function () {
	
	const addInput = document.getElementById('add');
	const addBox = document.getElementById('addBox');
	
	
	addInput.addEventListener('keypress', function (event) {
		if (event.key === 'Enter') {
			event.preventDefault();
			
			if (addInput.value.trim() === "") {
				addInput.value = "";
				return;
			}
			
			const newTaskWrapper = document.createElement('div');
			
			$.ajax({
				url: "todo",
				type: "post",
				data: {
					"currentIndex" : currentIndex,
					"addInput": addInput.value
				},
				success: function (result) {
					console.log(result);
					updateSelectedItemsCount();
				},
				error: function () {
					console.log("실패");
				}
			});

			const newsp = document.createElement('span');
			const deleteBtn = document.createElement('button');
			const taskInput = document.createElement('input');
			taskInput.classList.add('inputval');
			taskInput.style.color = "lightgray"; 
			taskInput.classList.add('inputval');
			taskInput.value = addInput.value;
			taskInput.readOnly = true;
			addInput.value = "";
			taskInput.addEventListener('dblclick', handleTaskInputEvent);
			taskInput.addEventListener('blur', handleTaskInputEvent);
			taskInput.addEventListener('keypress', handleTaskInputEvent);

			function handleTaskInputEvent(event) {
				if (event.type === 'dblclick') {
					taskInput.readOnly = !taskInput.readOnly;
					if (!taskInput.readOnly) taskInput.focus();
				} else if (event.type === 'blur') {
					taskInput.readOnly = true;
					
				} else if (event.type === 'keypress' && event.key === 'Enter') {
					event.preventDefault();
					taskInput.blur();
				}
			
			}
			
			newsp.classList.add('newsp');
			newsp.innerText = "✓";
			newsp.style.color = "lightgray";
			newsp.style.borderColor = "lightgray";

			newsp.addEventListener('click', function () {
			    let isComplete = this.style.color === "green";
				isComplete = !isComplete
				this.style.color = isComplete ? "green" : "lightgray";
				this.style.borderColor = isComplete ? "green" : "lightgray";
				taskInput.style.textDecoration = isComplete ? "line-through" : "none";
				taskInput.style.color = isComplete ? "green" : "lightgray";
				
				updateSelectedItemsCount();
			});

			deleteBtn.classList.add('delBtn')
			deleteBtn.textContent = "X";
			deleteBtn.addEventListener('click', function () {
				newTaskWrapper.remove();
				updateSelectedItemsCount();
			});

			newTaskWrapper.classList.add('task-box');
			newTaskWrapper.setAttribute('data-todonum', currentIndex);
			newTaskWrapper.append(newsp, taskInput, deleteBtn);
			addBox.appendChild(newTaskWrapper);
			currentIndex++;


			updateSelectedItemsCount();
		}

	});

});

function displayTodos(todos) {

    let maxTodoNum = 0;

    for (let i = 0; i < todos.length; i++) {
        let todo = todos[i];

        const todoNum = todo.todonum;
  
        const newTaskWrapper = document.createElement('div');
        newTaskWrapper.classList.add('task-box');
		newTaskWrapper.setAttribute('data-todonum', todoNum);
        const newsp = document.createElement('span');
        const deleteBtn = document.createElement('button');

        const taskInput = document.createElement('input');
        taskInput.classList.add('inputval');
        taskInput.style.color = todo.flag === 'Y' ? 'green' : 'lightgray';
        taskInput.style.textDecoration = todo.flag === 'Y' ? 'line-through' : 'none';
        taskInput.value = todo.content;
        taskInput.readOnly = true;

        taskInput.addEventListener('dblclick', handleTaskInputEvent);
        taskInput.addEventListener('blur', handleTaskInputEvent);
        taskInput.addEventListener('keypress', handleTaskInputEvent);

        function handleTaskInputEvent(event) {
    if (event.type === 'dblclick') {
        taskInput.readOnly = !taskInput.readOnly;
        if (!taskInput.readOnly) taskInput.focus();
    } else if (event.type === 'blur') {
        taskInput.readOnly = true;
        
        
        $.ajax({
            url: 'changeContents',
            method: 'POST',
            data: {
                currentIndex: newTaskWrapper.getAttribute('data-todonum'),
                "taskinput": taskInput.value,
            },
            success: function (data) {
                console.log(data);
            },
            error: function (err) {
                console.log(err);
            }
        });
        
        
    } else if (event.type === 'keypress' && event.key === 'Enter') {
        event.preventDefault();
        taskInput.blur();
    }
}

        deleteBtn.classList.add('delBtn')
        deleteBtn.textContent = "X";
        deleteBtn.addEventListener('click', function () {
            newTaskWrapper.remove();
            updateSelectedItemsCount();
        });

        newsp.classList.add('newsp');
        newsp.innerText = "✓";
        newsp.style.color = todo.flag === 'Y' ? 'green' : 'lightgray';
        newsp.style.borderColor = todo.flag === 'Y' ? 'green' : 'lightgray';

        newsp.addEventListener('click', function () {
            const isComplete = this.style.color === "green";
            const action2 = isComplete ? 'N' : 'Y';
            this.style.color = isComplete ? "lightgray" : "green";
            this.style.borderColor = isComplete ? "lightgray" : "green";
            taskInput.style.textDecoration = isComplete ? "none" : "line-through";
            taskInput.style.color = isComplete ? "lightgray" : "green";


            $.ajax({
                url: 'updateOne',
                method: 'POST',
                data: {
					 data_todonum: newTaskWrapper.getAttribute('data-todonum'),
                    action2: action2
                },
        success: function (data) {
                console.log(data);
            },
            error: function (err) {
                console.log(err);
            }
        });
        
        updateSelectedItemsCount();
        
    });

   maxTodoNum = Math.max(maxTodoNum, todoNum);
    newTaskWrapper.append(newsp, taskInput, deleteBtn);
    addBox.appendChild(newTaskWrapper);
}
currentIndex = maxTodoNum + 1;  
updateSelectedItemsCount();
}




window.onload = function () {
	const clear = document.getElementById("clear");
	clear.addEventListener("click", function () {
		const taskBoxes = document.querySelectorAll('.task-box');
		taskBoxes.forEach(function (taskBox) {
			const newsp = taskBox.querySelector('.newsp');
			const taskInput = taskBox.querySelector('input');
			const isComplete = window.getComputedStyle(newsp).getPropertyValue('color') === 'rgb(0, 128, 0)' && taskInput.style.textDecoration === 'line-through';

			if (isComplete) {
				taskBox.remove();
				allcheck.style.color = "lightgray";
			}

			$.ajax({
				url: 'deleteAll',
				method: 'POST',
				dataType: 'json',
				success: function (data) {
					console.log(data);
				},
				error: function (err) {
					console.log(err);
				}
			});

		});

		updateSelectedItemsCount();
	});
	
	$.ajax({
		url: 'memberTodo',
		method: 'POST',
		dataType: 'json',
		success: function (data) {
			 displayTodos(data);
		},
		error: function (err) {
			console.log(err);
		}
	});
	
};	
